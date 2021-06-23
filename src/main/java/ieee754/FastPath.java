package ieee754;

public class FastPath {
    public static void main(String[] args) {
        System.out.println(format012Decimals(1.0));
        System.out.println(format012Decimals(1.01));
        System.out.println(format012Decimals(1.10));
        System.out.println(format012Decimals(1.23));
        System.out.println(format012Decimals(1.001));
        System.out.println(format012Decimals(1.009));
        System.out.println(format2Decimals(9223372036854774784.0));
        System.out.println(format2Decimals(Long.MAX_VALUE));
    }

    public static String format2Decimals(double x) {
        return format(x, Signs.MINUS, Fractionals.TWO);
    }

    public static String format2Decimals(double x, Signs signs) {
        return format(x, signs, Fractionals.TWO);
    }

    public static String format012Decimals(double x) {
        return format(x, Signs.MINUS, Fractionals.ZERO_ONE_TWO);
    }

    public static String format012Decimals(double x, Signs signs) {
        return format(x, signs, Fractionals.ZERO_ONE_TWO);
    }

    private static String format(double x, Signs signs, Fractionals fractionals) {
        char sign = signs.sign(x);
        x = Math.abs(x);
        if (!(x < 0.0 - Long.MIN_VALUE)) return Double.toString(x); // "not less" handles NaN correctly

        long integer = (long) x;
        int fractional = (int) ((x - integer) * 100 + 0.5);
        // worst case: "-9223372036854774784,00"
        char[] chars = new char[1 + 19 + 1 + 2];
        int left = chars.length;

        // fractional
        chars[--left] = (char) ('0' + fractional % 10);
        chars[--left] = (char) ('0' + fractional / 10);
        chars[--left] = ',';

        // integer
        do {
            chars[--left] = (char) ('0' + integer % 10);
            integer /= 10;
        } while (integer != 0);

        // sign
        if (sign != Signs.NONE) {
            chars[--left] = sign;
        }

        // trailing
        int end = fractionals.cut(chars);

        // done
        return new String(chars, left, end - left);
    }
}
