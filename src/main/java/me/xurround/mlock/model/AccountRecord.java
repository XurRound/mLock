package me.xurround.mlock.model;

import javafx.beans.property.*;
import me.xurround.mlock.logic.crypto.Cipherer;
import me.xurround.mlock.misc.RandomHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AccountRecord
{
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty registrationDate;
    private final IntegerProperty passwordLength;
    private final byte[] passwordKey;

    public AccountRecord(String username, String encPassword, int encPasswordLength, LocalDate date, byte[] passwordKey)
    {
        this.username = new SimpleStringProperty(username);
        this.registrationDate = new SimpleStringProperty(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        this.passwordKey = passwordKey;
        this.password = new SimpleStringProperty(encPassword);
        this.passwordLength = new SimpleIntegerProperty(encPasswordLength);
    }

    public AccountRecord(String username, String clearPassword, LocalDate date, byte[] passwordKey)
    {
        this(username, Cipherer.encryptPassword(clearPassword, passwordKey), Cipherer.encryptPasswordLength(clearPassword.length(), passwordKey), date, passwordKey);
    }

    public AccountRecord(String username, String password, LocalDate date)
    {
        this(username, password, date, RandomHelper.generatePasswordKey());
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

    public String getClearPassword()
    {
        return Cipherer.decryptPassword(password.get(), passwordKey);
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

    public byte[] getPasswordKey()
    {
        return passwordKey;
    }

    public int getPasswordLength()
    {
        return passwordLength.get();
    }

    public int getClearPasswordLength()
    {
        return Cipherer.decryptPasswordLength(passwordLength.get(), passwordKey);
    }

    public IntegerProperty passwordLengthProperty()
    {
        return passwordLength;
    }

    public void setPasswordLength(int passwordLength)
    {
        this.passwordLength.set(passwordLength);
    }
}
