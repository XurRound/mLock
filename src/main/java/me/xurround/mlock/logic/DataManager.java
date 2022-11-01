package me.xurround.mlock.logic;

import me.xurround.mlock.interfaces.IPasswordStorageLoader;
import me.xurround.mlock.interfaces.IPreferencesLoader;
import me.xurround.mlock.model.PasswordStorage;
import me.xurround.mlock.model.Preferences;

import java.io.IOException;

public class DataManager
{
    private Preferences preferences;

    private PasswordStorage passwordStorage;

    private final IPreferencesLoader preferencesLoader;

    private final IPasswordStorageLoader passwordStorageLoader;

    public DataManager(IPreferencesLoader preferencesLoader, IPasswordStorageLoader passwordStorageLoader)
    {
        preferences = Preferences.getDefault();
        this.preferencesLoader = preferencesLoader;
        this.passwordStorageLoader = passwordStorageLoader;
        loadPreferences();
    }

    public void loadPasswordStorage(String masterPassword)
    {
        passwordStorage = passwordStorageLoader.load(masterPassword);
    }

    public void savePasswordStorage()
    {
        passwordStorageLoader.save(passwordStorage);
    }

    public PasswordStorage getPasswordStorage()
    {
        return passwordStorage;
    }

    private void loadPreferences()
    {
        try
        {
            preferences = preferencesLoader.load();
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Error loading preferences: " + e.getMessage());
        }
    }

    public void savePreferences()
    {
        try
        {
            preferencesLoader.save(preferences);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Preferences getPreferences()
    {
        return preferences;
    }
}
