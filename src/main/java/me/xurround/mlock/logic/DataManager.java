package me.xurround.mlock.logic;

import me.xurround.mlock.interfaces.PreferencesLoader;
import me.xurround.mlock.model.PasswordStorage;
import me.xurround.mlock.model.Preferences;

import java.io.IOException;

public class DataManager
{
    private Preferences preferences;

    private PasswordStorage passwordStorage;

    private final PreferencesLoader preferencesLoader;

    public DataManager(PreferencesLoader preferencesLoader)
    {
        preferences = Preferences.getDefault();
        this.preferencesLoader = preferencesLoader;
        loadPreferences();
        loadPasswordStorage();
    }

    private void loadPasswordStorage()
    {
        passwordStorage = new PasswordStorage();
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
