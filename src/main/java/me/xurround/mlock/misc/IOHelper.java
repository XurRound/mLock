package me.xurround.mlock.misc;

import me.xurround.mlock.model.Preferences;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IOHelper
{
    public static Path getWorkingDirectoryPath()
    {
        return (Paths.get("").toAbsolutePath());
    }

    public static String getPreferencesFilePath()
    {
        return getWorkingDirectoryPath() + "/preferences.dat";
    }

    public static Preferences readPreferences() throws IOException, ClassNotFoundException
    {
        FileInputStream fileStream = new FileInputStream(getPreferencesFilePath());
        ObjectInputStream inputStream = new ObjectInputStream(fileStream);
        return (Preferences)inputStream.readObject();
    }

    public static void writePreferences(Preferences preferences) throws IOException
    {
        FileOutputStream fileStream = new FileOutputStream(getPreferencesFilePath());
        ObjectOutputStream inputStream = new ObjectOutputStream(fileStream);
        inputStream.writeObject(preferences);
    }
}
