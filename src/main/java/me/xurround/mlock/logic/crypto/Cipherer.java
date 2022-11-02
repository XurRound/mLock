package me.xurround.mlock.logic.crypto;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Cipherer
{
    public static String encryptPassword(String password, byte[] key)
    {
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < passwordBytes.length; i++)
            passwordBytes[i] = (byte)(passwordBytes[i] ^ key[i % key.length]);
        return Base64.getEncoder().encodeToString(passwordBytes);
    }

    public static String decryptPassword(String encPassword, byte[] key)
    {
        byte[] passwordBytes = Base64.getDecoder().decode(encPassword);
        for (int i = 0; i < passwordBytes.length; i++)
            passwordBytes[i] = (byte)(passwordBytes[i] ^ key[i % key.length]);
        return new String(passwordBytes, StandardCharsets.UTF_8);
    }

    public static int encryptPasswordLength(int passwordLength, byte[] key)
    {
        return passwordLength ^ (key[0] * key[1]);
    }

    public static int decryptPasswordLength(int passwordLength, byte[] key)
    {
        return passwordLength ^ (key[0] * key[1]);
    }
}
