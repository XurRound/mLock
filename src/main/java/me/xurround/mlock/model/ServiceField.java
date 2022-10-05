package me.xurround.mlock.model;

import javafx.beans.property.*;

import java.util.Date;

public class ServiceField
{
    private final StringProperty serviceName;
    private final StringProperty serviceUsername;
    private final StringProperty servicePassword;
    private final LongProperty serviceRegistrationDate;

    public ServiceField(String serviceName, String username, String password, Date date)
    {
        this.serviceName = new SimpleStringProperty(serviceName);
        this.serviceUsername = new SimpleStringProperty(username);
        this.servicePassword = new SimpleStringProperty(password);
        this.serviceRegistrationDate = new SimpleLongProperty(date.getTime());
    }

    public String getServiceName()
    {
        return serviceName.get();
    }

    public StringProperty serviceNameProperty()
    {
        return serviceName;
    }

    public void setServiceName(String serviceName)
    {
        this.serviceName.set(serviceName);
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

    public long getServiceRegistrationDate()
    {
        return serviceRegistrationDate.get();
    }

    public LongProperty serviceRegistrationDateProperty()
    {
        return serviceRegistrationDate;
    }

    public void setServiceRegistrationDate(long serviceRegistrationDate)
    {
        this.serviceRegistrationDate.set(serviceRegistrationDate);
    }
}
