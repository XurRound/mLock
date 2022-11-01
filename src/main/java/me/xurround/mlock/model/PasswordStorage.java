package me.xurround.mlock.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.xurround.mlock.logic.crypto.Cipherer;

import java.time.LocalDate;

public class PasswordStorage
{
    private transient final ObservableList<ServiceRecord> services;

    public PasswordStorage()
    {
        services = FXCollections.observableArrayList();
    }

    public ObservableList<ServiceRecord> getRecords()
    {
        return services;
    }

    public void addAccount(String serviceName, String username, String password, LocalDate registrationDate)
    {
        for (ServiceRecord existingService : services)
        {
            if (existingService.getServiceName().equals(serviceName))
            {
                existingService.getAccounts().add(
                    new AccountRecord(username, Cipherer.encryptPassword(password, existingService.getPasswordKey()), registrationDate));
                return;
            }
        }
        ServiceRecord newService = new ServiceRecord(serviceName);
        newService.getAccounts().add(
            new AccountRecord(username, Cipherer.encryptPassword(password, newService.getPasswordKey()), registrationDate));
        services.add(newService);
    }
}
