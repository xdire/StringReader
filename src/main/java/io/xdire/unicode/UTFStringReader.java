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
    public static final byte ISO1 = 5;

    public UTFStringReader(byte[] initialString) {
        this.initialString = initialString;
        this.length = initialString.length;

        this.read();
    }

    public int getEncoding() {
        return detectedEncoding;
    }

    /**
     * @param encodingId int value from 1 to 4
     */
    public void setEncoding(byte encodingId) {
        if(encodingId > 0 && encodingId < 5) {
            detectedEncoding = encodingId;
        }
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
                    detectedEncoding = UTF16BE;
                }
                // -----------------------------------------------------------------------------
                //                              UTF 16 LE CASE
                // -----------------------------------------------------------------------------
                else if((first & 0b11111111) == 0b11111111) {
                    detectedEncoding = UTF16LE;
                }
                // -----------------------------------------------------------------------------
                //                              UTF 8 OR ISO CASE
                // -----------------------------------------------------------------------------
                else {

                    if((first >> 7) == 0) {

                        detectedEncoding = UTF8;

                    } else {

                        if((second & 0b10000000) == 0b10000000) {
                            // 1+ Byte
                            if ((first & 0b11100000) == 0b11000000) {
                                detectedEncoding = UTF8;
                            }
                            // 2+ Bytes
                            else if ((first & 0b11110000) == 0b11100000 && length > 2) {
                                if((initialString[2] >> 7) == -1) {
                                    detectedEncoding = UTF8;
                                }
                            }
                            // 3+ Bytes
                            else if ((first & 0b11111000) == 0b11110000 && length > 3) {
                                if((initialString[2] >> 7) == -1 &&
                                        (initialString[3] >> 7) == -1)
                                    detectedEncoding = UTF8;
                            }
                            // 4+ Bytes
                            else if ((first & 0b11111100) == 0b11111000) {
                                if((initialString[2] >> 7) == -1 &&
                                        (initialString[3] >> 7) == -1 &&
                                            (initialString[3] >> 7) == -1)
                                    detectedEncoding = UTF8;
                            }

                        }

                    }

                }

            }

        }

    }

    private void readUTF8(){

    }

    private void readUTF16(){

    }



}
