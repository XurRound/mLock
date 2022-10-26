package me.xurround.mlock.model;

import me.xurround.mlock.misc.RandomHelper;

import java.util.ArrayList;
import java.util.List;

public final class ServiceRecord
{
    private final String serviceName;

    private final byte[] passwordKey;

    private final List<AccountRecord> accounts;

    public ServiceRecord(String serviceName, List<AccountRecord> accounts)
    {
        this.serviceName = serviceName;
        this.accounts = accounts;
        passwordKey = RandomHelper.generatePasswordKey();
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

    public byte[] getPasswordKey()
    {
        return passwordKey;
    }

    public List<AccountRecord> getAccounts()
    {
        return accounts;
    }
}
