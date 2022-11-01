package me.xurround.mlock.logic.crypto.loader;

import me.xurround.mlock.App;
import me.xurround.mlock.interfaces.IPasswordStorageLoader;
import me.xurround.mlock.model.AccountRecord;
import me.xurround.mlock.model.PasswordStorage;
import me.xurround.mlock.model.ServiceRecord;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;

public class ClearTextPasswordLoader implements IPasswordStorageLoader
{
    @Override
    public PasswordStorage load(String masterPassword)
    {
        PasswordStorage storage = new PasswordStorage();

        String userDataDir = App.getInstance().getDataManager().getPreferences().getCurrentProfile().getDataDir();

        try
        {
            List<String> entries = Files.readAllLines(Path.of(userDataDir, "pStoragePlain.dat"));
            for (String entry : entries)
            {
                String[] data = entry.split(Pattern.quote("|"));
                boolean added = false;
                for (ServiceRecord existingService : storage.getRecords())
                {
                    if (existingService.getServiceName().equals(data[0]))
                    {
                        existingService.getAccounts().add(new AccountRecord(data[1],
                            new String(Base64.getDecoder().decode(data[2]), StandardCharsets.UTF_8), LocalDate.parse(data[3], DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
                        added = true;
                        break;
                    }
                }
                if (!added)
                {
                    ServiceRecord service = new ServiceRecord(data[0], Base64.getDecoder().decode(data[4]));
                    service.getAccounts().add(new AccountRecord(data[1],
                        new String(Base64.getDecoder().decode(data[2]), StandardCharsets.UTF_8), LocalDate.parse(data[3], DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
                    storage.getRecords().add(service);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return storage;
    }

    @Override
    public void save(PasswordStorage storage)
    {
        if (storage == null)
            return;
        String userDataDir = App.getInstance().getDataManager().getPreferences().getCurrentProfile().getDataDir();
        FileWriter fos = null;
        try
        {
            File storagePath = Path.of(userDataDir, "pStoragePlain.dat").toFile();
            if (!storagePath.getParentFile().exists())
                storagePath.getParentFile().mkdir();
            fos = new FileWriter(storagePath);
            for (ServiceRecord entry : storage.getRecords())
            {
                for (AccountRecord account : entry.getAccounts())
                {
                    fos.write(entry.getServiceName() + "|" +
                        account.getUsername() + "|" +
                        Base64.getEncoder().encodeToString(account.getPassword().getBytes(StandardCharsets.UTF_8)) + "|" +
                        account.getRegistrationDate() + "|" +
                        Base64.getEncoder().encodeToString(entry.getPasswordKey()) + "\n");
                }
            }
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (fos != null)
                    fos.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
