package csc202.security;

import java.util.ArrayList;

/**
 * User: Souleiman Ayoub
 * Date: 11/28/11
 * Time: 4:36 PM
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
    protected static byte[] encrypt(byte[] bytes) {
        ArrayList<Byte> list = File.loadList(bytes);
        String sequence = encrypt(list);
        return sequence.getBytes();
    }

    /**
     * This recursive method will first check if the size of the sequence is less than or equal to 2.
     * if it is, it will just insert the remaining to the sequence.
     * Otherwise, it will preform the recursive method and concatenate the partial sequence.
     *
     * @param sequence sequence given that contains the sequence
     * @return a String of the Sequence
     *
     * @author Souleiman Ayoub
     */
    private static String encrypt(ArrayList<Byte> sequence){
        /**
         * Since we are pulling by 3 at a time and it falls below 3,
         * there will always be ONE leftover. Try this, suppose we had sequence with 9 elements,
         * pull 3 until it falls below 3, it will be 9. Unlike 10 element, there will be 1 leftover, try 823,
         * there will be 1 left over.
         *
         * Watch:
         * 10 - 3 = 7
         * 7 - 3 = 4
         * 4 - 3 = 1 <- Below 3, it will never be 2. Always 1.
         *
         * What we do now is return the last element in our sequence.
         * However, just in case our sequence is 2 *somehow*
         */
        if(sequence.size() <= 2){
            byte[] bytes = new byte[sequence.size()];
            for(int i = 0; i < bytes.length; i++)
                bytes[i] = sequence.remove(0);
            return new String(bytes);
        }

        byte[] partialSeq = Encryption.extract(sequence, (byte) 0x20);
        return new String(partialSeq) + encrypt(sequence);
    }

    /**
     * This static method will extract values from the sequence and add it to our partial sequence.
     *
     * @param sequence given sequence that will be needed to remove.
     * @param bit offset range for encryption
     * @return array of bytes
     *
     * @author Souleiman Ayoub
     */
    private static byte[] extract(ArrayList<Byte> sequence, byte bit){
        int len = sequence.size() - 1;
        int mid = len / 2;

        byte last = (byte) (sequence.remove(len) + bit); //Pull last first so we don't mess the sequence
        byte middle = (byte) (sequence.remove(mid) - bit); //Pul middle next so we don't mess the sequence again
        byte first = (byte) (sequence.remove(0) + bit); //Finally, pull first; first.
        return new byte[]{last, first, middle};
    }
}
