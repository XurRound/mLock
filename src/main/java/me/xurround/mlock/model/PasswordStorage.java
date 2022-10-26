package me.xurround.mlock.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class PasswordStorage
{
    private final ObservableList<ServiceRecord> services;

    public PasswordStorage()
    {
        services = FXCollections.observableArrayList(
            new ServiceRecord("YouTube"),
            new ServiceRecord("Twitter"),
            new ServiceRecord("Amazon"),
            new ServiceRecord("LinkedIn")
        );
        services.get(0).getAccounts().add(new AccountRecord("GAnterAT", "yY7F2Nb78%66", LocalDate.now()));
        services.get(0).getAccounts().add(new AccountRecord("hASTARoU", "4TK0kNg0L!ym", LocalDate.now()));
        services.get(0).getAccounts().add(new AccountRecord("iStERmlO", "m55VQF6&rLR6", LocalDate.now()));
        services.get(1).getAccounts().add(new AccountRecord("hotANtIG", "SuDOLR82P6!$", LocalDate.now()));
        services.get(1).getAccounts().add(new AccountRecord("ENbaBIST", "u4p7Dp536@$#", LocalDate.now()));
    }

    public ObservableList<ServiceRecord> getRecords()
    {
        return services;
    }

    public void addAccount(String serviceName, String username, String password, LocalDate registrationDate)
    {
        AccountRecord newAccount = new AccountRecord(username, password, registrationDate);
        for (ServiceRecord record : services)
        {
            if (record.getServiceName().equals(serviceName))
            {
                record.getAccounts().add(newAccount);
                return;
            }
        }
        services.add(new ServiceRecord(serviceName, newAccount));
    }
}
