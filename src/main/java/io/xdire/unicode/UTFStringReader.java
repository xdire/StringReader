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

    private void prepare() {

        if(length > 1) {
            byte first = initialString[0];
            byte second = initialString[1];
        }
        if(length > 3){
            byte third = initialString[2];
            byte fourth = initialString[3];
        }



    }

}
