package me.xurround.mlock.model;

import me.xurround.mlock.App;
import me.xurround.mlock.misc.enums.Language;
import me.xurround.mlock.settings.LocalizationManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Preferences implements Serializable
{
    private String currentProfileName;

    private List<Profile> profiles;

    private Language language;

    private Preferences() { }

    public boolean isFirstRun()
    {
        return currentProfileName.equals(Profile.getDefault().getProfileName());
    }

    public Profile getCurrentProfile()
    {
        for (Profile profile : profiles)
            if (profile.getProfileName().equals(currentProfileName))
                return profile;
        return null;
    }

    public void setCurrentProfile(String currentProfileName)
    {
        this.currentProfileName = currentProfileName;
    }

    public void addProfile(Profile profile)
    {
        profiles.add(profile);
    }

    public List<Profile> getProfiles()
    {
        return profiles;
    }

    public Language getLanguage()
    {
        return language;
    }

    public void setLanguage(Language language)
    {
        this.language = language;
        App.getInstance().getLocalizationManager().setLanguage(language);
    }

    public void setProfiles(List<Profile> profiles)
    {
        this.profiles = profiles;
    }

    public static Preferences getDefault()
    {
        Preferences preferences = new Preferences();
        preferences.currentProfileName = Profile.getDefault().getProfileName();
        preferences.profiles = new ArrayList<>();
        preferences.profiles.add(Profile.getDefault());
        preferences.language = Language.EN;
        return preferences;
    }
}
