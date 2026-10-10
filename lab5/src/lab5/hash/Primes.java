package src.lab5.hash;

/** Работа с простыми числами для размера таблицы. */
public final class Primes {

    private Primes() {}

    /** Следующее число >= n. */
    public static int nextPrime(int n) {
        if (n < 2) return 2;
        int candidate = (n % 2 == 0) ? n + 1 : n;
        while (!isPrime(candidate)) candidate += 2;
        return candidate;
    }

    public static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int d = 3; d * d <= n; d += 2) {
            if (n % d == 0) return false;
        }
        return true;
    }
}