package me.xurround.mlock.model;

import java.util.ArrayList;
import java.util.List;

public final class ServiceRecord
{
    private final String serviceName;

    private final List<AccountRecord> accounts;

    public ServiceRecord(String serviceName, List<AccountRecord> accounts)
    {
        this.serviceName = serviceName;
        this.accounts = accounts;
    }

    public ServiceRecord(String serviceName)
    {
        this(serviceName, new ArrayList<>());
    }

    public ServiceRecord(String serviceName, AccountRecord firstAccount)
    {
        this(serviceName);
        accounts.add(firstAccount);
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public List<AccountRecord> getAccounts()
    {
        return accounts;
    }
}
