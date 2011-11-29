package csc202.security;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: Souleiman Ayoub
 * Date: 11/28/11
 * Time: 5:10 PM
 */
public class Decryption {
    protected static String decrypt(File file) throws IOException {
        byte[] bytes = Decryption.getByte(file);
        return null;
    }

    /**
     * Reads the file.
     * @param file File that we would like to read.
     * @return array of bytes
     * @throws IOException in case something goes wrong.
     */
    private static byte[] getByte(File file) throws IOException {
        byte[] bytes = new byte[(int) file.length()];
        int offset = 0;
        int readByte;

        //Read the bytes of the file
        InputStream is = new FileInputStream(file);
        while (offset < bytes.length && (readByte = is.read(bytes, offset, bytes.length-offset)) >= 0)
            offset += readByte;
        return bytes;
    }
}
