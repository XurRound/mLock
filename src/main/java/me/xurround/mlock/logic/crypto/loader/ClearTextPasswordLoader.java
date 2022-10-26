package me.xurround.mlock.logic.crypto.loader;

import me.xurround.mlock.interfaces.IPasswordStorageLoader;
import me.xurround.mlock.model.PasswordStorage;

public class ClearTextPasswordLoader implements IPasswordStorageLoader
{
    @Override
    public PasswordStorage load()
    {
        return new PasswordStorage();
    }
}
