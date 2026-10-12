package src.lab5.hash;

/** Простые числа для размера таблицы. */
public final class Primes {

    private Primes() {}

    public static int nextPrime(int n) {
        if (n < 2) return 2;
        if (n % 2 == 0) n++;
        while (!isPrime(n)) n += 2;
        return n;
    }

    public static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n % 2 == 0) return n == 2;
        for (int d = 3; d * d <= n; d += 2)
            if (n % d == 0) return false;
        return true;
    }
}