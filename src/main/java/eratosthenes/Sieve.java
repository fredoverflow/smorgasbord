package eratosthenes;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

class Cross {
    private final long column, prime;

    Cross(long column, long prime) {
        this.column = column;
        this.prime = prime;
    }

    long column() {
        return column;
    }

    long prime() {
        return prime;
    }
}

public class Sieve implements Iterator<Long> {

    public static Iterable<Long> allPrimes() {
        return () -> new Sieve();
    }

    private final PriorityQueue<Cross> queue = new PriorityQueue<>(Comparator.comparingLong(Cross::column));
    private long number = 2;

    private boolean numberCrossedOut() {
        Cross leftmost = queue.peek();
        return leftmost != null && leftmost.column() == number;
    }

    private void moveCross() {
        Cross leftmost = queue.poll();
        long prime = leftmost.prime();
        Cross moved = new Cross(leftmost.column() + prime, prime);
        queue.add(moved);
    }

    private void addCross() {
        queue.add(new Cross(number * number, number));
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Long next() {
        while (numberCrossedOut()) {
            do {
                moveCross();
            } while (numberCrossedOut());
            number++;
        }
        addCross();
        return number++;
    }

    public static void main(String[] args) throws InterruptedException {
        for (long prime : allPrimes()) {
            System.out.print(prime + ", ");
            Thread.sleep(10);
        }
    }
}
