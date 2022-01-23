package fibonacci;

import java.math.BigInteger;

import static java.math.BigInteger.*;

public class D {
    public static void main(String[] args) {
        System.out.println(fib(20));
    }

    public static BigInteger fib(int n) {
        BigInteger a = ZERO;
        BigInteger b = ONE;
        while (n != 0) {
            System.out.println("n = " + n + ", a = " + a + ", b = " + b);
            BigInteger c = a.add(b);
            a = b;
            b = c;
            --n;
        }
        return a;
    }
}
