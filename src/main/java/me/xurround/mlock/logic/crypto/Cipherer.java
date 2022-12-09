package me.xurround.mlock.logic.crypto;

import me.xurround.mlock.misc.HexUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public static byte[] passwordToKey(String password)
    {
        StringBuilder builder = new StringBuilder();
        while (builder.length() < 16)
            builder.append(password);
        byte[] strBytes = builder.toString().getBytes(StandardCharsets.UTF_8);
        byte[] key = new byte[16];
        System.arraycopy(strBytes, 0, key, 0, 16);
        for (int i = 16; i < strBytes.length; i++)
            key[i % 16] ^= strBytes[i];
        return key;
    }

    public static byte[] passwordToIV(String password) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        return md.digest();
    }

    public static String passwordToHexHash(String password) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        return HexUtils.bytesToHex(md.digest());
    }
}
