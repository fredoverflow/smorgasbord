package bank;

import java.math.BigInteger;
import java.util.regex.Pattern;

public class Iban {
    public static void main(String[] args) {
        System.out.println(de("1234567890", "12345678"));
    }

    private static final Pattern KNR = Pattern.compile("\\d{10}");
    private static final Pattern BLZ = Pattern.compile("\\d{8}");

    public static String de(String knr, String blz) {
        if (!KNR.matcher(knr).matches()) throw new IllegalArgumentException(knr);
        if (!BLZ.matcher(blz).matches()) throw new IllegalArgumentException(blz);
        // https://de.wikipedia.org/wiki/Internationale_Bankkontonummer#Pr%C3%BCfsumme
        String rotated = blz + knr + "1314" + "00";
        int check = 98 - new BigInteger(rotated).mod(BigInteger.valueOf(97)).intValue();
        return String.format("DE%02d%s%s", check, blz, knr);
    }
}
