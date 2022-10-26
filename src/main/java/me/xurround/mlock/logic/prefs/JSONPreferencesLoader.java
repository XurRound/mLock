package me.xurround.mlock.logic.prefs;

import com.google.gson.Gson;
import me.xurround.mlock.interfaces.IPreferencesLoader;
import me.xurround.mlock.misc.IOHelper;
import me.xurround.mlock.model.Preferences;

import java.io.*;

public class JSONPreferencesLoader implements IPreferencesLoader
{
    @Override
    public Preferences load() throws IOException, ClassNotFoundException
    {
        Gson gson = new Gson();
        return gson.fromJson(new FileReader(IOHelper.getPreferencesFilePath()), Preferences.class);
    }

    @Override
    public void save(Preferences preferences) throws IOException
    {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter(IOHelper.getPreferencesFilePath());
        writer.write(gson.toJson(preferences));
        writer.close();
    }
}
