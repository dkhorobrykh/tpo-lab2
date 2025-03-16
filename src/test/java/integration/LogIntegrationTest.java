package integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import ru.itmo.tpo.Ln;
import ru.itmo.tpo.Log;

public class LogIntegrationTest {

    private static final Integer DEFAULT_TERMS = 10;
    private static MockedStatic<Ln> mockedLn;

    @BeforeEach
    void setUp() {
        mockedLn = mockStatic(Ln.class);

        mockedLn.when(() -> Ln.ln(anyDouble(), eq(DEFAULT_TERMS))).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);
            if (Math.abs(x - 1) < 1e-6) return 0.0;
            if (Math.abs(x - 2) < 1e-6) return Math.log(2);
            if (Math.abs(x - Math.E) < 1e-6) return 1.0;
            if (Math.abs(x - 4) < 1e-6) return Math.log(4);
            if (Math.abs(x - 5) < 1e-6) return Math.log(5);
            if (Math.abs(x - 10) < 1e-6) return Math.log(10);
            if (Math.abs(x - 25) < 1e-6) return Math.log(25);
            if (Math.abs(x - 100) < 1e-6) return Math.log(100);
            return null;
        });
    }

    @AfterEach
    void tearDown() {
        mockedLn.close();
    }

    @Test
    void shouldCallLnWhileCalculating() {
        var _ = Log.log(2, 10, DEFAULT_TERMS);
        mockedLn.verify(() -> Ln.ln(2D, DEFAULT_TERMS));
    }

    @Test
    void shouldThrowExceptionForInvalidX() {
        assertThrows(ArithmeticException.class, () -> Log.log(0, 10, DEFAULT_TERMS));
        assertThrows(ArithmeticException.class, () -> Log.log(-1, 10, DEFAULT_TERMS));
    }

    @Test
    void shouldThrowExceptionForInvalidBase() {
        assertThrows(ArithmeticException.class, () -> Log.log(2, -2, DEFAULT_TERMS));
        assertThrows(ArithmeticException.class, () -> Log.log(2, 1, DEFAULT_TERMS));
    }

    @ParameterizedTest
    @MethodSource("getBasicData")
    void shouldCalculateLogValues(Pair<Triple<Double, Integer, Integer>, Double> data) {
        test(data);
    }

    private void test(Pair<Triple<Double, Integer, Integer>, Double> data) {
        Triple<Double, Integer, Integer> input = data.getLeft();
        double expected = data.getRight();
        double actual = Log.log(input.getLeft(), input.getMiddle(), input.getRight());
        assertEquals(expected, actual, 1e-5);
    }

    private static Stream<Pair<Triple<Double, Integer, Integer>, Double>> getBasicData() {
        return Stream.of(
            Pair.of(Triple.of(1.0, 10, DEFAULT_TERMS), 0.0),
            Pair.of(Triple.of(4.0, 2, DEFAULT_TERMS), 2.0),
            Pair.of(Triple.of(100., 10, DEFAULT_TERMS), 2.0),
            Pair.of(Triple.of(25., 5, DEFAULT_TERMS), 2.0)
        );
    }
}
