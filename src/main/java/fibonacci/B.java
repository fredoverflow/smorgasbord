package fibonacci;

import java.math.BigInteger;
import java.util.Arrays;

import static java.math.BigInteger.*;

public class B {
    public static void main(String[] args) {
        System.out.println(fib(20));
    }

    static BigInteger[] cache = { ZERO, ONE, ONE, TWO };

    public static BigInteger fib(int n) {
        if (n >= cache.length) {
            cache = Arrays.copyOf(cache, n + 1);
        }
        if (cache[n] == null) {
            System.out.println("cache[" + n + "] == null");
            cache[n] = fib(n - 2).add(fib(n - 1));
            System.out.println("cache[" + n + "] = " + cache[n]);
        }
        return cache[n];
    }
}
