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
        return format(x, SignRule.MINUS_EMPTY, false);
    }

    public static String format2Decimals(double x, SignRule signRule) {
        return format(x, signRule, false);
    }

    public static String format012Decimals(double x) {
        return format(x, SignRule.MINUS_EMPTY, true);
    }

    public static String format012Decimals(double x, SignRule signRule) {
        return format(x, signRule, true);
    }

    public enum SignRule {
        MINUS_EMPTY {
            @Override
            char sign(double x) {
                return (x < 0) ? '-' : 0;
            }
        }, MINUS_PLUS {
            @Override
            char sign(double x) {
                return (x < 0) ? '-' : '+';
            }
        }, MINUS_PLUS_EMPTY {
            @Override
            char sign(double x) {
                return (x < 0) ? '-' : (x > 0) ? '+' : 0;
            }
        }, MINUS_PLUS_SPACE {
            @Override
            char sign(double x) {
                return (x < 0) ? '-' : (x > 0) ? '+' : ' ';
            }
        };

        abstract char sign(double x);
    }

    private static String format(double x, SignRule signRule, boolean hideTrailingZerosAndComma) {
        char sign = signRule.sign(x);
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
        if (sign != 0) {
            chars[--left] = sign;
        }

        // trailing
        int right = chars.length - 1;
        if (hideTrailingZerosAndComma) {
            while (chars[right] == '0') --right;
            if (chars[right] == ',') --right;
        }

        // done
        return new String(chars, left, right + 1 - left);
    }
}
