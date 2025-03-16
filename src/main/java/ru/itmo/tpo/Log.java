package ru.itmo.tpo;

import static ru.itmo.tpo.Ln.ln;

public class Log {

    public static double log(double x, double base, int terms) {
        if (x <= 0) {
            throw new ArithmeticException("log(x) is undefined for x = " + x);
        }
        if (base <= 0 || base == 1) {
            throw new ArithmeticException("log(x) is undefined for base = " + base);
        }

        return ln(x, terms) / ln(base, terms);
    }

}
