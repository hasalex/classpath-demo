package fr.sw.cp.misc;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class Base64 {

    private static final String SUCCESS = "\u2713 : ";
    private static final String WARNING = "\u26A0 : ";

    public static void main(String[] args) {
        try {
            System.out.println(SUCCESS + "Successful access to " + BASE64Encoder.class);
            System.out.println(SUCCESS + "Successful access to " + BASE64Decoder.class);
            BASE64Encoder encoder = new BASE64Encoder();
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] decoded = ("Hello").getBytes();
            String encoded = encoder.encodeBuffer(decoded);
            decoded = decoder.decodeBuffer(encoded);
            System.out.println("... decoded : " + new String(decoded));
        } catch (IOException e) {
            System.out.println(WARNING + "Problem with BASE64Decoder " + e);
        } catch (NoClassDefFoundError err) {
            System.out.println(WARNING + "Cannot access to class " + err.getMessage());
        }
    }

}
