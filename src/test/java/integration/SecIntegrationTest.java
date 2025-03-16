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
import ru.itmo.tpo.Cos;
import ru.itmo.tpo.Sec;

public class SecIntegrationTest {

    private static final Integer DEFAULT_TERMS = 10;

    private static MockedStatic<Cos> mockedCos;

    @BeforeEach
    void setUp() {
        mockedCos = mockStatic(Cos.class);

        mockedCos.when(() -> Cos.cos(anyDouble(), eq(DEFAULT_TERMS))).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);
            if (Math.abs(x - PI / 2) < 1e-6) return 0.0;
            if (Math.abs(x - PI / 6) < 1e-6) return sqrt(3) / 2;
            if (Math.abs(x - PI / 4) < 1e-6) return sqrt(2) / 2;
            if (Math.abs(x - PI / 3) < 1e-6) return 0.5;
            if (Math.abs(x - 3 * PI / 4) < 1e-6) return -sqrt(2) / 2;
            if (Math.abs(x) < 1e-6 || Math.abs(x - PI) < 1e-6 || Math.abs(x + PI) < 1e-6) return 1.0;
            if (Math.abs(x + PI / 2) < 1e-6) return 0.0;
            if (Math.abs(x + PI / 6) < 1e-6) return sqrt(3) / 2;
            if (Math.abs(x + PI / 4) < 1e-6) return sqrt(2) / 2;
            if (Math.abs(x + PI / 3) < 1e-6) return 0.5;
            if (Math.abs(x + 3 * PI / 4) < 1e-6) return -sqrt(2) / 2;
            return null;
        });
    }

    @AfterEach
    void tearDown() {
        mockedCos.close();
    }

    @Test
    void shouldCallCosWhileCalculatingSec() {
        var _ = Sec.sec(PI / 4, DEFAULT_TERMS);
        mockedCos.verify(() -> Cos.cos(PI / 4, DEFAULT_TERMS));
    }

    @Test
    void shouldThrowExceptionForUndefinedSecValues() {
        assertThrows(IllegalArgumentException.class, () -> Sec.sec(PI / 2, DEFAULT_TERMS));
        assertThrows(IllegalArgumentException.class, () -> Sec.sec(-PI / 2, DEFAULT_TERMS));
    }

    @ParameterizedTest
    @MethodSource("getBasicData")
    void shouldCalculateSecValues(Pair<Pair<Double, Integer>, Double> data) {
        test(data);
    }

    @ParameterizedTest
    @MethodSource("getCycleData")
    void shouldCalculateSecCycleValues(Pair<Pair<Double, Integer>, Double> data) {
        test(data);
    }

    private void test(Pair<Pair<Double, Integer>, Double> data) {
        Pair<Double, Integer> input = data.getLeft();
        double expected = data.getRight();
        double actual = Sec.sec(input.getLeft(), input.getRight());
        assertEquals(expected, actual, 1e-5);
    }

    private static Stream<Pair<Pair<Double, Integer>, Double>> getBasicData() {
        return Stream.of(
            Pair.of(Pair.of(PI / 4, DEFAULT_TERMS), sqrt(2)),
            Pair.of(Pair.of(PI / 6, DEFAULT_TERMS), 2 / sqrt(3)),
            Pair.of(Pair.of(PI / 3, DEFAULT_TERMS), 2.),
            Pair.of(Pair.of(3 * PI / 4, DEFAULT_TERMS), -sqrt(2)),
            Pair.of(Pair.of(-PI / 4, DEFAULT_TERMS), sqrt(2)),
            Pair.of(Pair.of(-PI / 6, DEFAULT_TERMS), 2 / sqrt(3)),
            Pair.of(Pair.of(-PI / 3, DEFAULT_TERMS), 2.),
            Pair.of(Pair.of(-3 * PI / 4, DEFAULT_TERMS), -sqrt(2))
        );
    }

    private static Stream<Pair<Pair<Double, Integer>, Double>> getCycleData() {
        return Stream.of(
            Pair.of(Pair.of(PI / 4 + 2 * PI, DEFAULT_TERMS), sqrt(2)),
            Pair.of(Pair.of(-PI / 4 + 2 * PI, DEFAULT_TERMS), sqrt(2)),
            Pair.of(Pair.of(PI / 6 + 2 * PI, DEFAULT_TERMS), 2 / sqrt(3)),
            Pair.of(Pair.of(-PI / 6 - 2 * PI, DEFAULT_TERMS), 2 / sqrt(3)),
            Pair.of(Pair.of(-7 * PI / 4 - 2 * PI, DEFAULT_TERMS), 1 / Math.sin(-7 * PI / 4 - 2 * PI))
        );
    }
}

