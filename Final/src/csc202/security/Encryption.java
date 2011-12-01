package csc202.security;

import java.util.ArrayList;

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
     * @param mask mask the values
     * @return encrypted array of bytes.
     *
     * @author Souleiman Ayoub
     */
    public static byte[] encrypt(byte[] bytes, byte mask) {
        ArrayList<Byte> list = File.loadList(bytes);
        String sequence = encrypt(list, mask);
        return sequence.getBytes();
    }

    /**
     * This recursive method will first check if the size of the sequence is empty.
     * if it is, it will just return an encrypted sequence.
     * Otherwise, it will preform the recursive method and concatenate the partial sequence.
     *
     * @param sequence sequence given that contains the sequence
     * @param mask mask the value
     * @return a String of the Sequence
     *
     * @author Souleiman Ayoub
     */
    private static String encrypt(ArrayList<Byte> sequence, byte mask){
        if(sequence.isEmpty()){
            return "";
        }

        byte[] partialSeq = {Encryption.extract(sequence, mask)}; //Max of 127 bit
        return new String(partialSeq) + encrypt(sequence, mask);
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
}
