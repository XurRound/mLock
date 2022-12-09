package me.xurround.mlock.logic.crypto.loader;

import me.xurround.mlock.App;
import me.xurround.mlock.interfaces.IPasswordStorageLoader;
import me.xurround.mlock.logic.crypto.FileCryptoReader;
import me.xurround.mlock.logic.crypto.FileCryptoWriter;
import me.xurround.mlock.misc.exception.InvalidPasswordException;
import me.xurround.mlock.misc.exception.StorageLoaderException;
import me.xurround.mlock.model.AccountRecord;
import me.xurround.mlock.model.PasswordStorage;
import me.xurround.mlock.model.ServiceRecord;

import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.regex.Pattern;

public class CryptoPasswordLoader implements IPasswordStorageLoader
{
    private FileCryptoReader cryptoReader;
    private FileCryptoWriter cryptoWriter;

    private Path dataPath;

    @Override
    public PasswordStorage load(String masterPassword) throws InvalidPasswordException
    {
        PasswordStorage storage = new PasswordStorage();
        try
        {
            dataPath = Path.of(App.getInstance().getProfileManager().getProfile().getDataDir(), "pStorage.dat");
            cryptoReader = new FileCryptoReader(dataPath.toString(), masterPassword);
            cryptoWriter = new FileCryptoWriter(dataPath.toString(), masterPassword);
            if (!App.getInstance().getProfileManager().isAuthorized())
                return storage;
            cryptoReader.open();
            String dataLine = cryptoReader.decryptLine();
            while (dataLine != null && !dataLine.equals(""))
            {
                String[] data = dataLine.split(Pattern.quote("|"));
                AccountRecord account = new AccountRecord(
                    data[1], data[2],
                    Integer.parseInt(data[3]),
                    LocalDate.parse(data[4], DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    Base64.getDecoder().decode(data[5]));
                boolean added = false;
                for (ServiceRecord existingService : storage.getRecords())
                {
                    if (existingService.getServiceName().equals(data[0]))
                    {
                        existingService.getAccounts().add(account);
                        added = true;
                        break;
                    }
                }
                if (!added)
                    storage.getRecords().add(new ServiceRecord(data[0], account));
                dataLine = cryptoReader.decryptLine();
            }
        }
        catch (IOException | ArrayIndexOutOfBoundsException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | InvalidAlgorithmParameterException e)
        {
            if (e.getMessage().contains("BadPaddingException") || e.getMessage().contains("out of bounds for length"))
                throw new InvalidPasswordException();
            System.out.println("Failed to load password storage: " + e.getMessage());
        }
        finally
        {
            try
            {
                if (cryptoReader != null)
                    cryptoReader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return storage;
    }

    @Override
    public void save(PasswordStorage storage)
    {
        if (storage == null || cryptoWriter == null || dataPath == null)
            return; //TODO: replace with exception throwing
        try
        {
            File storagePath = dataPath.toFile();
            if (!storagePath.getParentFile().exists())
                storagePath.getParentFile().mkdir();
            cryptoWriter.open();
            for (ServiceRecord entry : storage.getRecords())
            {
                for (AccountRecord account : entry.getAccounts())
                {
                    cryptoWriter.writeLineEncrypted(entry.getServiceName() + "|" +
                        account.getUsername() + "|" +
                        account.getPassword() + "|" +
                        account.getPasswordLength() + "|" +
                        account.getRegistrationDate() + "|" +
                        Base64.getEncoder().encodeToString(account.getPasswordKey()) + "\n");
                }
            }
            cryptoWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("Failed to save password storage: " + e.getMessage());
        }
        finally
        {
            try
            {
                if (cryptoWriter != null)
                    cryptoWriter.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
