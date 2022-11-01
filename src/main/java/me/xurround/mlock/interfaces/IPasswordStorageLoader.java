package me.xurround.mlock.interfaces;

import me.xurround.mlock.model.PasswordStorage;

public interface IPasswordStorageLoader
{
    PasswordStorage load(String masterPassword);
    void save(PasswordStorage storage);
}
