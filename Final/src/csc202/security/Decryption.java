package csc202.security;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

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
    public static String decrypt(File file) throws IOException {
        byte[] bytes = Decryption.getByte(file);
        return new String(decrypt(bytes));
    }

    /**
     * <a href="http://www.mathtran.org/cgi-bin/mathtran?tex=index%20%3D%20%5Csum_%7Bi%3D0%7D%5E%7Bb.length%7D%20(-1)%5E(%5Ei%5E%2B%5E(%5Eb%5Em%5Eo%5Ed%5E2%5E%3D%5E%3D%5E0%5E%3F%5E1%5E%3A%5E-%5E1%5E)%5E)((i%20%2B%201)%20%2F%202)%20%2B%20((b%20-%201)%20%2F%202)">See Formula</a>
     * Takes the first value of the sequence and adds it to the middle.
     * However, this sequence is built up by an alternating series. Therefor, the next value goes next to the
     * sequence; and the value after that goes before the sequence.
     *
     * @param bytes the given sequence
     * @return a String of decrypted message
     *
     * @author Souleiman Ayoub
     */
    public static byte[] decrypt(byte [] bytes){
        byte[] sequence = new byte[bytes.length];
        int middle = (bytes.length - 1) / 2;
        for(int i = 0; i < bytes.length; i++){
            int pow = i + (bytes.length % 2 == 0 ? 1 : -1);
            int offset = ((i + 1) / 2);
            int result = (int)(Math.pow(-1, pow)) * offset + middle;
            sequence[result] = (byte) (bytes[i] ^ 0x7F);
        }
        return sequence;
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
        byte[] bytes = {115, 114, 116, 113, 117, 111};
        System.out.println(Arrays.toString(decrypt(bytes)));
    }
}
