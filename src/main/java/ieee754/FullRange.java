package ieee754;

import java.util.Arrays;

public class FullRange {
    public static void main(String[] args) {
        System.out.println(toString(123456789123456789.0, true));
        System.out.println(toString(Double.longBitsToDouble(0x0000000000000001L)));
        System.out.println(toString(Double.longBitsToDouble(0x000fffffffffffffL)));
        System.out.println(toString(Double.longBitsToDouble(0x0010000000000000L)));
        System.out.println(toString(Double.longBitsToDouble(0x7fefffffffffffffL)));
        System.out.println(toString(0.1));
        System.out.println(toString(0.2));
        System.out.println(toString(0.1 + 0.2));
        System.out.println(toString(0.3));
    }

    private static final long THRESHOLD = 1_000_000_000_000_000_000L;
    private static final int DIGITS = 18;
    private static final long HEADROOM = Long.MIN_VALUE - THRESHOLD;

    private static final long[][] POSITIVE_POWERS_OF_TWO;
    private static final long[][] NEGATIVE_POWERS_OF_TWO;

    static {
        POSITIVE_POWERS_OF_TWO = new long[1024][];
        long[] positive = {1};
        for (int i = 0; i < POSITIVE_POWERS_OF_TWO.length; ++i) {
            // System.out.println(Arrays.toString(positive));
            POSITIVE_POWERS_OF_TWO[i] = positive.clone();
            positive = addInteger(positive, positive);
        }

        NEGATIVE_POWERS_OF_TWO = new long[1074][];
        long[] negative = {THRESHOLD};
        for (int i = 0; i < NEGATIVE_POWERS_OF_TWO.length; ++i) {
            negative = halve(negative);
            // System.out.println(Arrays.toString(negative));
            NEGATIVE_POWERS_OF_TWO[i] = negative.clone();
        }
    }

    private static long[] addInteger(long[] x, long[] y) {
        if (x.length < y.length) {
            x = Arrays.copyOf(x, y.length);
        }
        long negativeCarry = 0; // or -1
        int i;
        for (i = 0; i < y.length; ++i) {
            long sum = x[i] + y[i] - negativeCarry;
            negativeCarry = (sum + HEADROOM) >> 63;
            x[i] = sum - (negativeCarry & THRESHOLD);
        }
        if (negativeCarry != 0) {
            for (; i < x.length; ++i) {
                if (++x[i] < THRESHOLD) return x;
                x[i] = 0;
            }
            x = Arrays.copyOf(x, i + 1);
            x[i] = 1;
        }
        return x;
    }

    private static long[] halve(long[] x) {
        long borrow = 0;
        int i;
        for (i = 0; i < x.length; ++i) {
            long segment = x[i];
            x[i] = (segment + borrow) >>> 1;
            borrow = (segment << 63 >> 63) & THRESHOLD;
        }
        if (borrow != 0) {
            x = Arrays.copyOf(x, i + 1);
            x[i] = borrow >>> 1;
        }
        return x;
    }

    public static String toString(double d) {
        return toString(d, false);
    }

    public static String toString(double d, boolean visiblePlus) {
        long bits = Double.doubleToRawLongBits(d);
        boolean negative = bits < 0;
        int exponent = ((int) (bits >> 52) & (0x7ff));
        long mantissa = bits << 12;

        if (exponent == 2047) {
            return mantissa != 0 ? "NaN" : negative ? "-Infinity" : visiblePlus ? "+Infinity" : "Infinity";
        }
        long normal = (Long.MAX_VALUE + exponent) >>> 63;
        exponent = exponent - 1022 - (int) normal;
        mantissa = normal << 63 | mantissa >>> 1;

        long[] integer = {0};
        while (mantissa != 0 && exponent >= 0) {
            if (mantissa < 0) {
                integer = addInteger(integer, POSITIVE_POWERS_OF_TWO[exponent]);
            }
            --exponent;
            mantissa <<= 1;
        }

        long[] fractional = {0};
        while (mantissa != 0) {
            if (mantissa < 0) {
                fractional = addFractional(fractional, NEGATIVE_POWERS_OF_TWO[~exponent]);
            }
            --exponent;
            mantissa <<= 1;
        }

        char[] a = new char[(integer.length + fractional.length) * DIGITS + 2];
        int left = generateInteger(integer, a);
        int right = generateFractional(fractional, a);

        if (negative) {
            a[--left] = '-';
        } else if (visiblePlus) {
            a[--left] = '+';
        }

        return new String(a, left, right - left + 1);
    }

    private static long[] addFractional(long[] x, long[] y) {
        int i = Math.min(x.length, y.length);
        if (x.length < y.length) {
            long[] temp = new long[y.length];
            System.arraycopy(x, 0, temp, 0, x.length);
            System.arraycopy(y, x.length, temp, x.length, y.length - x.length);
            x = temp;
        }
        long negativeCarry = 0; // or -1
        for (--i; i >= 0; --i) {
            long sum = x[i] + y[i] - negativeCarry;
            negativeCarry = (sum + HEADROOM) >> 63;
            x[i] = sum - (negativeCarry & THRESHOLD);
        }
        assert negativeCarry == 0;
        return x;
    }

    private static int generateInteger(long[] x, char[] a) {
        int left = x.length * DIGITS + 1;

        int i;
        for (i = 0; i < x.length - 1; ++i) {
            long segment = x[i];
            for (int j = 0; j < DIGITS; ++j) {
                a[--left] = (char) ('0' + segment % 10);
                segment /= 10;
            }
        }

        long segment = x[i];
        do {
            a[--left] = (char) ('0' + segment % 10);
            segment /= 10;
        } while (segment != 0);

        return left;
    }

    private static int generateFractional(long[] x, char[] a) {
        int right = a.length - x.length * DIGITS - 1;
        a[right] = ',';

        for (long segment : x) {
            right += DIGITS;
            for (int i = 0; i < DIGITS; ++i) {
                a[right - i] = (char) ('0' + segment % 10);
                segment /= 10;
            }
        }

        while (a[right] == '0') --right;
        if (a[right] == ',') ++right;

        return right;
    }
}
