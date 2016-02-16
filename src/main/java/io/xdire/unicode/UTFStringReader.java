package io.xdire.unicode;

/**
 * Created by xdire on 2/14/16.
 */
public class UTFStringReader {

    private final byte[] initialString;
    private final int length;

    private byte detectedEncoding = 0;

    public static final byte UTF8 = 1;
    public static final byte UTF16 = 2;
    public static final byte UTF16BE = 3;
    public static final byte UTF16LE = 4;

    public UTFStringReader(byte[] initialString) {
        this.initialString = initialString;
        this.length = initialString.length;

        this.read();
    }

    private void read(){
        this.prepare();
    }

    /**
     *  PREPARE BY DETECTING ENCODING TYPE
     *
     *  Read first bytes, compare, set flag
     */
    private void prepare() {

        if (detectedEncoding == 0) {

            if (length > 1) {

                byte first = initialString[0];
                byte second = initialString[1];

                // -----------------------------------------------------------------------------
                //                              UTF 16 BE CASE
                // -----------------------------------------------------------------------------
                if((first & 0b11111110) == 0b11111110 && (second & 0b11111111) == 0b11111111) {
                    System.out.println("!!!!!!!! DETECTED UTF-16BE");
                    detectedEncoding = UTF16BE;
                }
                // -----------------------------------------------------------------------------
                //                              UTF 16 LE CASE
                // -----------------------------------------------------------------------------
                else if((first & 0b11111111) == 0b11111111) {
                    System.out.println("!!!!!!!! DETECTED UTF-16LE");
                    detectedEncoding = UTF16LE;
                }
                // -----------------------------------------------------------------------------
                //                              UTF 8 OR ISO CASE
                // -----------------------------------------------------------------------------
                else {
                    System.out.println("!!!!!!!! DETECTED SOMETHING OTHER MAYBE UTF-8");
                    if((first >> 7) == 0) {
                        System.out.println("!!!!!!!! Definitely UTF-8 or ISO");
                        detectedEncoding = UTF8;
                    } else {

                        if((first & 0b11100000) == 0b11000000) {
                            System.out.print("Expecting 1 bytes next \n");

                        } else if ((first & 0b11110000) == 0b11100000) {
                            System.out.print("Expecting 2 bytes next \n");
                        }

                    }
                }

            }

            if (length > 3) {
                byte third = initialString[2];
                byte fourth = initialString[3];
            }


        }

    }

    private void readUTF8(){

    }

    private void readUTF16(){

    }

}
