package me.xurround.mlock.model;

import me.xurround.mlock.misc.enums.Language;

import java.io.Serializable;
import java.util.Objects;

public class Profile implements Serializable
{
    private String name;
    private String dataDir;

    public Profile(String name, String dataDir)
    {
        this.name = name;
        this.dataDir = dataDir;
    }

    public String getDataDir()
    {
        return dataDir;
    }

    public void setDataDir(String dataDir)
    {
        this.dataDir = dataDir;
    }

    public String getProfileName()
    {
        return name;
    }

    public void setProfileName(String profileName)
    {
        this.name = profileName;
    }

    public static Profile getDefault()
    {
        return new Profile("Default", "default");
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Profile profile = (Profile)o;
        return profile.name.equals(name) && profile.dataDir.equals(dataDir);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, dataDir);
    }
}
