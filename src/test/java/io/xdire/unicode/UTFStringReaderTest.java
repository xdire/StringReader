package io.xdire.unicode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

/**
 * Created by xdire on 2/14/16.
 */
public class UTFStringReaderTest {

    String utf8String = "hello€";
    String utf16String = "\u0068" + "\u0065" + "\u006c" + "\u006c" + "\u006f" + "\u0080";

    UTFStringReader stringReader;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void maketestUTF8(){

        try {
            System.out.println("\n ---------------- UTF8 String test -------------\n\n");
            byte[] bytes = utf8String.getBytes("UTF-8");
            printBytesAsHex(bytes);
            printBytesAsBin(bytes);
            stringReader = new UTFStringReader(bytes);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void maketestUTF16(){

        try {
            System.out.println("\n ---------------- UTF16 String test -------------\n\n");
            byte[] bytes = utf16String.getBytes("UTF-16");
            printBytesAsHex(bytes);
            printBytesAsBin(bytes);
            stringReader = new UTFStringReader(bytes);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
    }

    @Test
    public void maketestUTF16BE(){

    }

    @Test
    public void maketestUTF16LE(){

    }

    private void printBytesAsHex(byte[] bytes){
        for(byte b : bytes){
            System.out.print(" H[ " + Integer.toHexString(b) + "]");
        }
        System.out.println("\n");
    }

    private void printBytesAsBin(byte[] bytes){
        for(byte b : bytes){
            System.out.print(" B[ " + Integer.toBinaryString(b) + "]");
        }
        System.out.println("\n");
    }

}