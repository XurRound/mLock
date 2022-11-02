package me.xurround.mlock.logic.crypto;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileCryptoWriter
{
    private final FileOutputStream fileOutputStream;
    private final CipherOutputStream cryptoWriter;

    public FileCryptoWriter(String path, String password) throws NoSuchPaddingException, NoSuchAlgorithmException, FileNotFoundException, InvalidKeyException
    {
        this.fileOutputStream = new FileOutputStream(path);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(password.getBytes(StandardCharsets.UTF_8), "AES/CBC/PKCS5Padding"));
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
}
