package csc202.security;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * User: Souleiman Ayoub
 * Date: 11/28/11
 * Time: 5:10 PM
 */
public class Decryption {
    /**
     * Decrypts the file and returns a String
     *
     * @param file that we want to decrypt
     * @return String of what is within the file, decrypted
     * @throws IOException in case something goes wrong
     *
     * @author Souleiman Ayoub
     */
    protected static String decrypt(File file) throws IOException {
        byte[] bytes = Decryption.getByte(file);
        ArrayList<Byte> sequence = File.loadList(bytes);
        return decrypt(sequence);
    }

    /**
     * Decrypt the array and return a String
     *
     * @param bytes array of byte we would like to decrypt
     * @return String of what is within the array of bytes, decrypted
     *
     * @author Souleiman Ayoub
     */
    protected static String decrypt(byte[] bytes){
        ArrayList<Byte> sequence = File.loadList(bytes);
        return decrypt(sequence);
    }

    /**
     * Takes the first value of the sequence and adds it to the middle.
     * However, this sequence is built up by an alternating series. Therefor, the next value goes next to the
     * sequence; and the value after that goes before the sequence.
     *
     * @param sequence the given sequence
     * @return a String of decrypted message
     *
     * @author Souleiman Ayoub
     */
    private static String decrypt(ArrayList<Byte> sequence){
        byte[] bytes = new byte[sequence.size()];
        int index = (bytes.length - 1) / 2;
        int sigma = -1;
        if(sequence.size() % 2 == 0)
            sigma = 1;

        for(int i = 0, c = 0; i < bytes.length; i++){
            int offset;
            if(i % 2 == 0){
                offset = c * -1 * sigma;
                c++;
            } else
                offset = c * sigma;

            bytes[index + offset] = (byte) (sequence.remove(0) + 0x7); //max is 9 bit
        }
        return new String(bytes);
    }

    /**
     * Reads the file.
     * @param file File that we would like to read.
     * @return array of bytes
     * @throws IOException in case something goes wrong.
     *
     * @author Souleiman Ayoub
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

    public static void main(String[] args) {
        byte[] bytes = "ROL7D".getBytes();
        System.out.println(decrypt(bytes));
    }
}
