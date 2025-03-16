package ru.itmo.tpo;

public class Ln {

    public static double ln(double x, int terms) {
        if (x <= 0) {
            throw new ArithmeticException("ln(x) is undefined for x = " + x);
        }

        double y = (x - 1) / (x + 1);
        double result = 0;
        for (int i = 0; i < terms; i++) {
            result += 2 * Math.pow(y, 2 * i + 1) / (2 * i + 1);
        }
        return result;
    }

}
