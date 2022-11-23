package me.xurround.mlock.logic.crypto;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileCryptoReader
{
    private FileInputStream fileInputStream;
    private BufferedReader cryptoReader;

    private final Cipher cipher;

    private final String path;

    public FileCryptoReader(String path, String password) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, InvalidKeyException, InvalidAlgorithmParameterException
    {
        this.path = path;
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(
            Cipher.DECRYPT_MODE,
            new SecretKeySpec(Cipherer.passwordToKey(password), "AES"),
            new IvParameterSpec(Cipherer.passwordToIV(password))
        );
    }

    public void open() throws FileNotFoundException
    {
        this.fileInputStream = new FileInputStream(path);
        this.cryptoReader = new BufferedReader(new InputStreamReader(new CipherInputStream(fileInputStream, cipher)));
    }

    public byte[] readBytesClear(int count) throws IOException
    {
        byte[] data = new byte[count];
        fileInputStream.read(data);
        return data;
    }

    public String decryptLine() throws IOException
    {
        return cryptoReader.readLine();
    }

    public void close() throws IOException
    {
        if (cryptoReader != null)
            cryptoReader.close();
        if (fileInputStream != null)
            fileInputStream.close();
    }
}
