package me.xurround.mlock.logic;

import me.xurround.mlock.App;
import me.xurround.mlock.logic.crypto.Cipherer;
import me.xurround.mlock.logic.crypto.FileCryptoReader;
import me.xurround.mlock.logic.crypto.FileCryptoWriter;
import me.xurround.mlock.misc.enums.AuthState;
import me.xurround.mlock.misc.enums.RegisterState;
import me.xurround.mlock.model.Preferences;
import me.xurround.mlock.model.Profile;

import java.nio.file.Files;
import java.nio.file.Path;

public class ProfileManager
{
    private Profile authorizedProfile;

    public ProfileManager()
    {
        authorizedProfile = null;
    }

    public AuthState authorize(Profile profile, String masterPassword)
    {
        if (masterPassword.isEmpty())
            return AuthState.INVALID_PASSWORD;
        try
        {
            FileCryptoReader fileCryptoReader = new FileCryptoReader(Path.of(profile.getDataDir(), "pwc.dat").toString(), masterPassword);
            fileCryptoReader.open();
            String hash = fileCryptoReader.tryDecryptLine();
            fileCryptoReader.close();
            if (hash != null && hash.equals(Cipherer.passwordToHexHash(masterPassword)))
            {
                authorizedProfile = profile;
                App.getInstance().getDataManager().loadPasswordStorage(masterPassword);
                return AuthState.OK;
            }
            else
                return AuthState.INVALID_PASSWORD;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return AuthState.UNKNOWN_ERROR;
        }
    }

    public RegisterState register(String profileName, String storagePath, String masterPassword)
    {
        if (Files.exists(Path.of(storagePath)))
            return RegisterState.USER_DIRECTORY_EXISTS;
        for (Profile profile : App.getInstance().getDataManager().getPreferences().getProfiles())
        {
            if (profile.getProfileName().equals(profileName))
                return RegisterState.USER_EXISTS;
        }
        try
        {
            Profile profile = new Profile(profileName, storagePath);
            Path profileStorageDir = Path.of(profile.getDataDir());
            if (!Files.exists(profileStorageDir))
                Files.createDirectories(profileStorageDir);
            FileCryptoWriter fileCryptoWriter = new FileCryptoWriter(Path.of(profile.getDataDir(), "pwc.dat").toString(), masterPassword);
            fileCryptoWriter.open();
            fileCryptoWriter.writeLineEncrypted(Cipherer.passwordToHexHash(masterPassword));
            fileCryptoWriter.close();
            Preferences preferences = App.getInstance().getDataManager().getPreferences();
            preferences.getProfiles().add(profile);
            preferences.setCurrentProfile(profile.getProfileName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return RegisterState.UNKNOWN_ERROR;
        }
        return RegisterState.OK;
    }

    public void logout()
    {
        authorizedProfile = null;
        App.getInstance().getDataManager().savePasswordStorage();
    }

    public boolean isAuthorized()
    {
        return authorizedProfile != null;
    }

    public Profile getProfile()
    {
        return authorizedProfile;
    }
}
