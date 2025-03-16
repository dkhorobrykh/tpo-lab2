package ru.itmo.tpo;

import static java.lang.Math.PI;
import static ru.itmo.tpo.Sin.sin;

public class Cos {

    public static double cos(double x, int terms) {
        x = PI / 2 - x;

        x %= (2 * PI);
        if (x > PI) {
            x -= 2 * PI;
        } else if (x < -PI) {
            x += 2 * PI;
        }

        return sin(x, terms);
    }

}
