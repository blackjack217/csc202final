package csc202.security;

import java.util.Arrays;

/**
 * User: Souleiman Ayoub
 * Date: 11/30/11
 * Time: 5:26 PM
 */
public class SafeString {
    private byte[] encrypted;
    private byte mask;

    public SafeString(String arg){
        this(arg.getBytes());
    }
    public SafeString(char[] chars){
        this(new String(chars));
    }
    public SafeString(byte[] arg){
        mask = (byte) (1 + Math.random() * 127);
        encrypted = Encryption.encrypt(arg, mask);
    }

    public char[] getSequence(){
        String a = new String(Decryption.decrypt(encrypted, mask));
        return a.toCharArray();
    }

    @Override
    public String toString() {
        return "SafeString{" +
                "encrypted=" + Arrays.toString(encrypted) +
                '}';
    }
}
