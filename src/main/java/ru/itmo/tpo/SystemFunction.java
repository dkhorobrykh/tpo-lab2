package ru.itmo.tpo;

import static java.lang.Math.pow;
import static ru.itmo.tpo.Cos.cos;
import static ru.itmo.tpo.Csc.csc;
import static ru.itmo.tpo.Ln.ln;
import static ru.itmo.tpo.Log.log;
import static ru.itmo.tpo.Sec.sec;
import static ru.itmo.tpo.Sin.sin;
import static ru.itmo.tpo.Tan.tan;

public class SystemFunction {

    public static double system(double x, int terms) {
        if (x <= 0) {
            return (((((sec(x, terms) / tan(x, terms)) / csc(x, terms)) - csc(x, terms)) -
                (sin(x, terms) + (cos(x, terms) - (csc(x, terms) / tan(x, terms))))) / csc(x, terms));
        } else {
            return (pow(
                (((log(x, 5, terms) + log(x, 2, terms)) * ln(x, terms)) - (ln(x, terms) * log(x, 5, terms))) /
                    (log(x, 10, terms) / ln(x, terms)), 3
            ));
        }
    }

}
