package csc202.security;

import java.io.IOException;
import java.util.ArrayList;

/**
 * User: Souleiman Ayoub
 * Date: 11/28/11
 * Time: 4:36 PM
 */
public class Encryption {
    protected static byte[] encrypt(byte[] bytes) throws IOException {
        ArrayList<Byte> list = File.loadList(bytes);
        encrypt(list);
        return File.rewind(list);
    }

    private static void encrypt(ArrayList<Byte> list){}
}
