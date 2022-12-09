package me.xurround.mlock.logic.crypto;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileCryptoWriter
{
    private FileOutputStream fileOutputStream;
    private CipherOutputStream cryptoWriter;

    private final Cipher cipher;

    private final String path;

    public FileCryptoWriter(String path, String password) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException
    {
        this.path = path;
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(
            Cipher.ENCRYPT_MODE,
            new SecretKeySpec(Cipherer.passwordToKey(password), "AES"),
            new IvParameterSpec(Cipherer.passwordToIV(password))
        );
    }

    public void open() throws FileNotFoundException
    {
        this.fileOutputStream = new FileOutputStream(path);
        this.cryptoWriter = new CipherOutputStream(fileOutputStream, cipher);
    }

    public void writeBytesClear(byte[] hash) throws IOException
    {
        fileOutputStream.write(hash);
    }

    public void writeLineEncrypted(String data) throws IOException
    {
        cryptoWriter.write(data.getBytes(StandardCharsets.UTF_8));
    }

    public void close() throws IOException
    {
        if (cryptoWriter != null)
            cryptoWriter.close();
        if (fileOutputStream != null)
            fileOutputStream.close();
    }
}
