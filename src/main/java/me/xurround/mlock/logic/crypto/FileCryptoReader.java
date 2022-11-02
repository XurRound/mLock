package me.xurround.mlock.logic.crypto;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileCryptoReader
{
    private final FileInputStream fileInputStream;
    private final BufferedReader cryptoReader;

    public FileCryptoReader(String path, String password) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, InvalidKeyException
    {
        this.fileInputStream = new FileInputStream(path);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(password.getBytes(StandardCharsets.UTF_8), "AES/CBC/PKCS5Padding"));
        this.cryptoReader = new BufferedReader(new InputStreamReader(new CipherInputStream(fileInputStream, cipher)));
    }

    public byte[] readBytesClear(int count) throws IOException
    {
        byte[] data = new byte[count];
        fileInputStream.read(data);
        return data;
    }

    private String decryptLine() throws IOException
    {
        return cryptoReader.readLine();
    }

    private void close() throws IOException
    {
        cryptoReader.close();
        fileInputStream.close();
    }
}
