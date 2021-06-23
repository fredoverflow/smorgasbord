package ieee754;

public class FastPath {
    public static void main(String[] args) {
        System.out.println(format(1.0, Signs.MINUS, Fractionals.ZERO_ONE_TWO));
        System.out.println(format(1.01, Signs.MINUS, Fractionals.ZERO_ONE_TWO));
        System.out.println(format(1.10, Signs.MINUS, Fractionals.ZERO_ONE_TWO));
        System.out.println(format(1.23, Signs.MINUS, Fractionals.ZERO_ONE_TWO));
        System.out.println(format(1.001, Signs.MINUS, Fractionals.ZERO_ONE_TWO));
        System.out.println(format(1.009, Signs.MINUS, Fractionals.ZERO_ONE_TWO));
        System.out.println(format(9223372036854774784.0, Signs.MINUS, Fractionals.TWO));
        System.out.println(format((double) Long.MAX_VALUE, Signs.MINUS, Fractionals.TWO));
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
