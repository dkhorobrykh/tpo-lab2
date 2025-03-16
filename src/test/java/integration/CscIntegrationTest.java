package integration;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import ru.itmo.tpo.Csc;
import ru.itmo.tpo.Sin;

public class CscIntegrationTest {

    private static final Integer DEFAULT_TERMS = 10;

    private static MockedStatic<Sin> mockedSin;

    @BeforeEach
    void setUp() {
        mockedSin = mockStatic(Sin.class);
        mockedSin.when(() -> Sin.sin(anyDouble(), eq(DEFAULT_TERMS))).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);
            if (Math.abs(x - PI / 2) < 1e-6) return 1.0;
            if (Math.abs(x - PI / 6) < 1e-6) return 0.5;
            if (Math.abs(x - PI / 4) < 1e-6) return sqrt(2) / 2;
            if (Math.abs(x - 3 * PI / 4) < 1e-6) return sqrt(2) / 2;
            if (Math.abs(x) < 1e-6 || Math.abs(x - PI) < 1e-6 || Math.abs(x + PI) < 1e-6) return 0.0;
            if (Math.abs(x + PI / 2) < 1e-6) return -1.0;
            if (Math.abs(x + PI / 6) < 1e-6) return -0.5;
            if (Math.abs(x + PI / 4) < 1e-6) return -sqrt(2) / 2;
            if (Math.abs(x + 3 * PI / 4) < 1e-6) return -sqrt(2) / 2;
            return null;
        });
    }

    @AfterEach
    void tearDown() {
        mockedSin.close();
    }

    @Test
    void shouldCallSinWhileCalculating() {
        var _ = Csc.csc(PI / 2, DEFAULT_TERMS);
        mockedSin.verify(() -> Sin.sin(PI / 2, DEFAULT_TERMS));
    }

    @Test
    void shouldThrowExceptionForUndefinedValues() {
        assertThrows(IllegalArgumentException.class, () -> Csc.csc(0, DEFAULT_TERMS));
        assertThrows(IllegalArgumentException.class, () -> Csc.csc(PI, DEFAULT_TERMS));
        assertThrows(IllegalArgumentException.class, () -> Csc.csc(-PI, DEFAULT_TERMS));
    }

    @ParameterizedTest
    @MethodSource("getBasicData")
    void shouldCalculateTableValues(Pair<Pair<Double, Integer>, Double> data) {
        test(data);
    }

    @ParameterizedTest
    @MethodSource("getCycleData")
    void shouldCalculateCycleValues(Pair<Pair<Double, Integer>, Double> data) {
        test(data);
    }

    private void test(Pair<Pair<Double, Integer>, Double> data) {
        Pair<Double, Integer> input = data.getLeft();
        double expected = data.getRight();
        double actual = Csc.csc(input.getLeft(), input.getRight());
        assertEquals(expected, actual, 1e-5);
    }

    private static Stream<Pair<Pair<Double, Integer>, Double>> getBasicData() {
        return Stream.of(
            Pair.of(Pair.of(PI / 2, DEFAULT_TERMS), 1.0),
            Pair.of(Pair.of(PI / 6, DEFAULT_TERMS), 1 / 0.5),
            Pair.of(Pair.of(PI / 4, DEFAULT_TERMS), 1 / (sqrt(2) / 2)),
            Pair.of(Pair.of(3 * PI / 4, DEFAULT_TERMS), 1 / (sqrt(2) / 2)),
            Pair.of(Pair.of(-PI / 2, DEFAULT_TERMS), -1.0),
            Pair.of(Pair.of(-PI / 6, DEFAULT_TERMS), 1 / -0.5),
            Pair.of(Pair.of(-PI / 4, DEFAULT_TERMS), 1 / (-sqrt(2) / 2)),
            Pair.of(Pair.of(-3 * PI / 4, DEFAULT_TERMS), 1 / (-sqrt(2) / 2))
        );
    }

    private static Stream<Pair<Pair<Double, Integer>, Double>> getCycleData() {
        return Stream.of(
            Pair.of(Pair.of(PI / 2 + 2 * PI, DEFAULT_TERMS), 1.0),
            Pair.of(Pair.of(-PI / 2 + 2 * PI, DEFAULT_TERMS), -1.0),
            Pair.of(Pair.of(PI / 6 + 2 * PI, DEFAULT_TERMS), 1 / 0.5),
            Pair.of(Pair.of(-7 * PI / 4 - 2 * PI, DEFAULT_TERMS), 1 / Math.cos(-7 * PI / 4 - 2 * PI))
        );
    }
}
