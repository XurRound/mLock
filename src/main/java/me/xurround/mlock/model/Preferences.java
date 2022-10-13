package me.xurround.mlock.model;

import me.xurround.mlock.misc.enums.Language;

import java.io.Serializable;

public class Preferences implements Serializable
{
    private boolean firstRun;

    private Profile currentProfile;

    private Preferences() { }

    public boolean isFirstRun()
    {
        return firstRun;
    }

    public Profile getCurrentProfile()
    {
        return currentProfile;
    }

    public void setCurrentProfile(Profile currentProfile)
    {
        this.currentProfile = currentProfile;
    }

    public static Preferences getDefault()
    {
        Preferences preferences = new Preferences();
        preferences.currentProfile = new Profile("Default", "default", Language.EN);
        preferences.firstRun = true;
        return preferences;
    }
}
