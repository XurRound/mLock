package me.xurround.mlock.settings;

import java.util.prefs.Preferences;

public class Settings
{
    private static final Preferences preferences;

    static
    {
        preferences = Preferences.userNodeForPackage(Settings.class);
    }

    public static String getString(String resourceName, String defaultValue)
    {
        return preferences.get(resourceName, defaultValue);
    }

    public static boolean isFirstLaunch()
    {
        boolean firstLaunch = preferences.getBoolean("first_launch", true);
        preferences.putBoolean("first_launch", false);
        return firstLaunch;
    }

    public static String getLang()
    {
        if (isFirstLaunch())
            preferences.put("lang", "EN");
        return preferences.get("lang", "EN");
    }

    public static void setLang(String lang)
    {
        preferences.put("lang", lang);
    }
}
