package me.xurround.mlock.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        AccountRecord account = new AccountRecord(username, password, registrationDate);
        for (ServiceRecord existingService : services)
        {
            if (existingService.getServiceName().equals(serviceName))
            {
                existingService.getAccounts().add(account);
                return;
            }
        }
        services.add(new ServiceRecord(serviceName, account));
    }

    public void removeAccount(AccountRecord recordToRemove)
    {
        for (ServiceRecord service : services)
        {
            for (AccountRecord record : service.getAccounts())
            {
                if (record.equals(recordToRemove))
                {
                    service.getAccounts().remove(recordToRemove);
                    if (service.getAccounts().size() <= 0)
                        services.remove(service);
                    return;
                }
            }
        }
    }
}
