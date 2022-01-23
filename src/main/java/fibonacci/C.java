package fibonacci;

import java.math.BigInteger;

import static java.math.BigInteger.*;

public class C {
    public static void main(String[] args) {
        System.out.println(fib(20));
    }

    public static BigInteger fib(int n) {
        return fib(n, ZERO, ONE);
    }

    static BigInteger fib(int n, BigInteger a, BigInteger b) {
        System.out.println("fib(" + n + ", " + a + ", " + b + ")");
        if (n == 0) {
            // new Exception().printStackTrace();
            return a;
        } else {
            return fib(n - 1, b, a.add(b));
        }
    }
}
