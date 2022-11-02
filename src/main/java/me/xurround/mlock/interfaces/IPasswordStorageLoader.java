package me.xurround.mlock.interfaces;

import me.xurround.mlock.misc.exception.InvalidPasswordException;
import me.xurround.mlock.model.PasswordStorage;

public interface IPasswordStorageLoader
{
    PasswordStorage load(String masterPassword) throws InvalidPasswordException;
    void save(PasswordStorage storage);
}
