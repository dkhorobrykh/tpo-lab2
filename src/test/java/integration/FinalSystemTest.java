package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import ru.itmo.tpo.Cos;
import ru.itmo.tpo.Csc;
import ru.itmo.tpo.Ln;
import ru.itmo.tpo.Log;
import ru.itmo.tpo.Sec;
import ru.itmo.tpo.Sin;
import ru.itmo.tpo.SystemFunction;
import ru.itmo.tpo.Tan;

public class FinalSystemTest {

    private static final int DEFAULT_TERMS = 10;

    @ParameterizedTest
    @MethodSource("getNegativeData")
    void negativeTest(Pair<Pair<Double, Integer>, Double> data) {
        test(data);
    }

    @ParameterizedTest
    @MethodSource("getPositiveData")
    void positiveTest(Pair<Pair<Double, Integer>, Double> data) {
        test(data);
    }

    private void test(Pair<Pair<Double, Integer>, Double> data) {
        var input = data.getLeft();
        double expected = data.getRight();
        double actual = SystemFunction.system(input.getLeft(), input.getRight());
        assertEquals(expected, actual, 1e-2);
    }

    private static Stream<Pair<Pair<Double, Integer>, Double>> getNegativeData() {
        return Stream.of(
            Pair.of(Pair.of(-5.84595, DEFAULT_TERMS), 1.),
            Pair.of(Pair.of(-4.71239, DEFAULT_TERMS), -1.),
            Pair.of(Pair.of(-4.5513, DEFAULT_TERMS), -0.991401),
            Pair.of(Pair.of(-4.4135, DEFAULT_TERMS), -0.984346),
            Pair.of(Pair.of(-4.26925, DEFAULT_TERMS), -1.),
            Pair.of(Pair.of(-3.51278, DEFAULT_TERMS), -3.),

            Pair.of(Pair.of(-2.79553, DEFAULT_TERMS), 1.),
            Pair.of(Pair.of(-1.570796, DEFAULT_TERMS), -3.),
            Pair.of(Pair.of(-1.118, DEFAULT_TERMS), -2.80095),
            Pair.of(Pair.of(-0.847864, DEFAULT_TERMS), -2.698339),
            Pair.of(Pair.of(-0.5423, DEFAULT_TERMS), -3.)
        );
    }

    private static Stream<Pair<Pair<Double, Integer>, Double>> getPositiveData() {
        return Stream.of(
            Pair.of(Pair.of(0.57772, DEFAULT_TERMS), 1.),
            Pair.of(Pair.of(0.749, DEFAULT_TERMS), 0.021365),
            Pair.of(Pair.of(0.953, DEFAULT_TERMS), .0),
            Pair.of(Pair.of(1., DEFAULT_TERMS), Double.NaN),
            Pair.of(Pair.of(1.453245, DEFAULT_TERMS), 0.1),
            Pair.of(Pair.of(1.73094, DEFAULT_TERMS), 1.)
        );
    }

}
