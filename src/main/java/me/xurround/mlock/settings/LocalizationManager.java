package me.xurround.mlock.settings;

import me.xurround.mlock.misc.enums.Language;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationManager
{
    private ResourceBundle localeBundle;

    public LocalizationManager()
    {
        setLanguage(Language.EN); // Default
    }

    public void setLanguage(Language language)
    {
        localeBundle = ResourceBundle.getBundle("me/xurround/mlock/lang/lang", new Locale(language.name().toLowerCase()));
    }

    public String getLocalizedString(String key, String defaultValue)
    {
        if (localeBundle.containsKey(key))
            return localeBundle.getString(key);
        return defaultValue;
    }

    public String getLocalizedString(String key)
    {
        return getLocalizedString(key, "");
    }
}
