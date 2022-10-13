package me.xurround.mlock.model;

import me.xurround.mlock.misc.enums.Language;

import java.io.Serializable;

public class Profile implements Serializable
{
    private String profileName;
    private String dataDir;
    private Language language;

    public Profile(String profileName, String dataPath, Language language)
    {
        this.profileName = profileName;
        this.dataDir = dataPath;
        this.language = language;
    }

    public String getDataDir()
    {
        return dataDir;
    }

    public void setDataDir(String dataDir)
    {
        this.dataDir = dataDir;
    }

    public Language getLanguage()
    {
        return language;
    }

    public void setLanguage(Language language)
    {
        this.language = language;
    }

    public String getProfileName()
    {
        return profileName;
    }

    public void setProfileName(String profileName)
    {
        this.profileName = profileName;
    }
}
