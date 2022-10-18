package me.xurround.mlock.interfaces;

import me.xurround.mlock.model.Preferences;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PreferencesLoader
{
    Preferences load() throws IOException, ClassNotFoundException;
    void save(Preferences preferences) throws IOException;
}
