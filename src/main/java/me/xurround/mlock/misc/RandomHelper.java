package me.xurround.mlock.misc;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class RandomHelper
{
    public static byte[] generatePasswordKey()
    {
        byte[] key = new byte[24];
        try
        {
            SecureRandom.getInstanceStrong().nextBytes(key);
        }
        catch (NoSuchAlgorithmException e)
        {
            Random random = new Random();
            random.nextBytes(key);
            for (int i = 0; i < 24; i++)
                key[i] ^= 0x7E;
        }
        return key;
    }

    public static String generateRandomHexString(int length)
    {
        Random rnd = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++)
            builder.append(Integer.toHexString(rnd.nextInt(15)));
        return builder.toString();
    }
}
