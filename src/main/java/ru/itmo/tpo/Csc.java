package ru.itmo.tpo;

import static java.lang.Math.PI;
import static ru.itmo.tpo.Sin.sin;

public class Csc {

    public static double csc(double x, int terms) {
        x %= (2 * PI);
        if (x > PI) {
            x -= 2 * PI;
        } else if (x < -PI) {
            x += 2 * PI;
        }

        var sinX = sin(x, terms);
        if (Math.abs(sinX) < 1e-10) {
            throw new IllegalArgumentException("csc(x) is undefined for x = " + x);
        }
        return 1 / sinX;
    }

}
