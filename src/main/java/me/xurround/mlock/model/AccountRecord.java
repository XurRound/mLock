package me.xurround.mlock.model;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AccountRecord
{
    private final StringProperty serviceUsername;
    private final StringProperty servicePassword;
    private final StringProperty serviceRegistrationDate;

    public AccountRecord(String username, String password, LocalDate date)
    {
        this.serviceUsername = new SimpleStringProperty(username);
        this.servicePassword = new SimpleStringProperty(password);
        this.serviceRegistrationDate = new SimpleStringProperty(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

    public String getServiceUsername()
    {
        return serviceUsername.get();
    }

    public StringProperty serviceUsernameProperty()
    {
        return serviceUsername;
    }

    public void setServiceUsername(String serviceUsername)
    {
        this.serviceUsername.set(serviceUsername);
    }

    public String getServicePassword()
    {
        return servicePassword.get();
    }

    public StringProperty servicePasswordProperty()
    {
        return servicePassword;
    }

    public void setServicePassword(String servicePassword)
    {
        this.servicePassword.set(servicePassword);
    }

    public LocalDate getServiceRegistrationDate()
    {
        return LocalDate.parse(serviceRegistrationDate.get());
    }

    public StringProperty serviceRegistrationDateProperty()
    {
        return serviceRegistrationDate;
    }

    public void setServiceRegistrationDate(LocalDate serviceRegistrationDate)
    {
        this.serviceRegistrationDate.set(serviceRegistrationDate.toString());
    }
}
