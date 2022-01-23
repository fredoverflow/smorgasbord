package fibonacci;

import java.math.BigInteger;

import static java.math.BigInteger.*;

public class A {
    public static void main(String[] args) {
        BigInteger result = fib(20);
        System.out.println();
        System.out.println(" calls: " + calls);
        System.out.println("result: " + result);
    }

    static long calls;

    public static BigInteger fib(int n) {
        System.out.print(n + " ");
        ++calls;
        switch (n) {
            case 0: return ZERO;

            case 1:
            case 2: return ONE;

            case 3: return TWO;

            default: return fib(n - 2).add(fib(n - 1));
        }
    }
}
