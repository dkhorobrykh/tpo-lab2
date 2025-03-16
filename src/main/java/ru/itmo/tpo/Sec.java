package ru.itmo.tpo;

import static java.lang.Math.PI;
import static ru.itmo.tpo.Cos.cos;

public class Sec {

    public static double sec(double x, int terms) {
        x %= (2 * PI);
        if (x > PI) {
            x -= 2 * PI;
        } else if (x < -PI) {
            x += 2 * PI;
        }

        var cosX = cos(x, terms);
        if (Math.abs(cosX) < 1e-10) {
            throw new IllegalArgumentException("sec(x) is undefined for x = " + x);
        }
        return 1 / cosX;
    }

}
