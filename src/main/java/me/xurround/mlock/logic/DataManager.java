package me.xurround.mlock.logic;

import me.xurround.mlock.misc.IOHelper;
import me.xurround.mlock.model.Preferences;

import java.io.IOException;

public class DataManager
{
    private Preferences preferences;

    public DataManager()
    {
        preferences = Preferences.getDefault();
        loadPreferences();
    }

    public void loadPreferences()
    {
        try
        {
            preferences = IOHelper.readPreferences();
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
            IOHelper.writePreferences(preferences);
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
