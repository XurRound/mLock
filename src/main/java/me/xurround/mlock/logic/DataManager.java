package me.xurround.mlock.logic;

import me.xurround.mlock.interfaces.PreferencesLoader;
import me.xurround.mlock.model.Preferences;

import java.io.IOException;

public class DataManager
{
    private Preferences preferences;

    private final PreferencesLoader preferencesLoader;

    public DataManager(PreferencesLoader preferencesLoader)
    {
        preferences = Preferences.getDefault();
        this.preferencesLoader = preferencesLoader;
        loadPreferences();
    }

    public void loadPreferences()
    {
        try
        {
            preferences = preferencesLoader.load();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
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
