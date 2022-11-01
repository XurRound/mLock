package me.xurround.mlock.model;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AccountRecord
{
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty registrationDate;

    public AccountRecord(String username, String password, LocalDate date)
    {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.registrationDate = new SimpleStringProperty(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

    public String getUsername()
    {
        return username.get();
    }

    public StringProperty usernameProperty()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username.set(username);
    }

    public String getPassword()
    {
        return password.get();
    }

    public StringProperty passwordProperty()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password.set(password);
    }

    public String getRegistrationDate()
    {
        return registrationDate.get();
    }

    public StringProperty registrationDateProperty()
    {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate)
    {
        this.registrationDate.set(registrationDate);
    }
}
