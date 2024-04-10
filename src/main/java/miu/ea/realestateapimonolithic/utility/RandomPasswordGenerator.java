package miu.ea.realestateapimonolithic.utility;

import java.util.Random;

public class RandomPasswordGenerator {
    public static String genPassword(int len) {
        String generatorString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(generatorString.charAt(rnd.nextInt(generatorString.length())));
        }
        return sb.toString();
    }
}
