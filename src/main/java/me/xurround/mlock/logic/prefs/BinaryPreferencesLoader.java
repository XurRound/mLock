package me.xurround.mlock.logic.prefs;

import me.xurround.mlock.interfaces.PreferencesLoader;
import me.xurround.mlock.misc.IOHelper;
import me.xurround.mlock.model.Preferences;

import java.io.*;

public class BinaryPreferencesLoader implements PreferencesLoader
{
    @Override
    public Preferences load() throws IOException, ClassNotFoundException
    {
        FileInputStream fileStream = new FileInputStream(IOHelper.getPreferencesFilePath());
        ObjectInputStream inputStream = new ObjectInputStream(fileStream);
        return (Preferences)inputStream.readObject();
    }

    @Override
    public void save(Preferences preferences) throws IOException
    {
        FileOutputStream fileStream = new FileOutputStream(IOHelper.getPreferencesFilePath());
        ObjectOutputStream inputStream = new ObjectOutputStream(fileStream);
        inputStream.writeObject(preferences);
    }
}
