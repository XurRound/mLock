package me.xurround.mlock.settings;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Localization
{
    private static final ArrayList<String> supportedLanguages = new ArrayList<String>();
    private static final ResourceBundle localeBundle;

    private static final String TAG = "";

    static
    {
        supportedLanguages.add("en");
        supportedLanguages.add("ru");

        String lang = Settings.getLang();
        String currentLang = (supportedLanguages.contains(lang) ? lang : "EN");
        localeBundle = ResourceBundle.getBundle("me/xurround/mlock/lang/lang", new Locale(currentLang));
    }

    public static String getLocalizedString(String key, String defaultValue)
    {
        if (localeBundle.containsKey(TAG + key))
            return localeBundle.getString(TAG + key);
        return defaultValue;
    }

    public static String getLocalizedString(String key)
    {
        return getLocalizedString(TAG + key, "");
    }
}
