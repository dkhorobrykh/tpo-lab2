package unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.itmo.tpo.Ln.ln;

import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class LnTest {

    private final static int DEFAULT_TERMS = 20;

    @ParameterizedTest
    @MethodSource("getBasicData")
    void basicTest(Pair<Pair<Double, Integer>, Double> data) {
        testValid(data);
    }

    @ParameterizedTest
    @MethodSource("getNonPositiveData")
    void testLnException(Pair<Double, Integer> input) {
        assertThrows(ArithmeticException.class, () -> ln(input.getLeft(), input.getRight()));
    }

    private void testValid(Pair<Pair<Double, Integer>, Double> data) {
        Pair<Double, Integer> input = data.getLeft();
        double expected = data.getRight();
        double actual = ln(input.getLeft(), input.getRight());
        assertEquals(expected, actual, 1e-4);
    }

    private static Stream<Pair<Pair<Double, Integer>, Double>> getBasicData() {
        return Stream.of(
            Pair.of(Pair.of(1.0, DEFAULT_TERMS), 0.0),
            Pair.of(Pair.of(Math.E, DEFAULT_TERMS), 1.0),
            Pair.of(Pair.of(2.0, DEFAULT_TERMS), Math.log(2.0)),
            Pair.of(Pair.of(10.0, DEFAULT_TERMS), Math.log(10.0)),
            Pair.of(Pair.of(0.5, DEFAULT_TERMS), Math.log(0.5)),
            Pair.of(Pair.of(0.1, DEFAULT_TERMS), Math.log(0.1))
        );
    }

    private static Stream<Pair<Double, Integer>> getNonPositiveData() {
        return Stream.of(
            Pair.of(0.0, DEFAULT_TERMS),
            Pair.of(-1.0, DEFAULT_TERMS),
            Pair.of(-10.0, DEFAULT_TERMS)
        );
    }
}

