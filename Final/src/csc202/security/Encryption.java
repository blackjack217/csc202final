package csc202.security;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * User: Souleiman Ayoub
 * Date: 11/28/11
 * Time: 4:36 PM
 *
 * Since this assignment is for educational purpose, it's ok to explain how the encryption is working.
 */
public class Encryption {
    /**
     * This static method basically manipulates the array of bytes and reallocate them.
     *
     * @param bytes array of bytes given to us to reallocate
     * @return encrypted array of bytes.
     *
     * @author Souleiman Ayoub
     */
    public static byte[] encrypt(byte[] bytes) {
        ArrayList<Byte> list = File.loadList(bytes);
        String sequence = encrypt(list);
        return sequence.getBytes();
    }

    /**
     * This recursive method will first check if the size of the sequence is empty.
     * if it is, it will just return an encrypted sequence.
     * Otherwise, it will preform the recursive method and concatenate the partial sequence.
     *
     * @param sequence sequence given that contains the sequence
     * @return a String of the Sequence
     *
     * @author Souleiman Ayoub
     */
    private static String encrypt(ArrayList<Byte> sequence){
        if(sequence.isEmpty()){
            return "";
        }

        byte[] partialSeq = {Encryption.extract(sequence, (byte) 0x7F)}; //Max of 127 bit
        return new String(partialSeq) + encrypt(sequence);
    }

    /**
     * This static method will extract values from the middle of the sequence and add it to our partial sequence.
     * The encryption will then be returned in this order of the sequence.
     *
     * @param sequence given sequence that will be needed to remove.
     * @param bit offset range for encryption
     * @return array of bytes
     *
     * @author Souleiman Ayoub
     */
    private static byte extract(ArrayList<Byte> sequence, byte bit){
        int mid = (sequence.size() - 1) / 2;
        return (byte) (sequence.remove(mid) ^ bit);
    }

    public static void main(String[] args) {
        byte[] bytes = {10, 11, 12, 13, 14, 16};
        System.out.println(Arrays.toString(encrypt(bytes)));
    }
}
