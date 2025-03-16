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

public class SystemFunctionIntegrationTest {

    private static final int DEFAULT_TERMS = 10;
    private static MockedStatic<Sin> mockedSin;
    private static MockedStatic<Cos> mockedCos;
    private static MockedStatic<Tan> mockedTan;
    private static MockedStatic<Csc> mockedCsc;
    private static MockedStatic<Sec> mockedSec;
    private static MockedStatic<Ln> mockedLn;
    private static MockedStatic<Log> mockedLogBase;

    @BeforeEach
    void setUp() {
        mockedSin = mockStatic(Sin.class);
        mockedCos = mockStatic(Cos.class);
        mockedTan = mockStatic(Tan.class);
        mockedCsc = mockStatic(Csc.class);
        mockedSec = mockStatic(Sec.class);
        mockedLn = mockStatic(Ln.class);
        mockedLogBase = mockStatic(Log.class);

        mockedSin.when(() -> Sin.sin(anyDouble(), eq(DEFAULT_TERMS))).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);

            if (Math.abs(x - (-5.84595)) < 1e-6) return Math.sin(-5.84595);
            if (Math.abs(x - (-4.71239)) < 1e-6) return Math.sin(-4.71239);
            if (Math.abs(x - (-4.5513)) < 1e-6) return Math.sin(-4.5513);
            if (Math.abs(x - (-4.4135)) < 1e-6) return Math.sin(-4.4135);
            if (Math.abs(x - (-4.26925)) < 1e-6) return Math.sin(-4.26925);
            if (Math.abs(x - (-3.51278)) < 1e-6) return Math.sin(-3.51278);

            if (Math.abs(x - (-2.79553)) < 1e-6) return Math.sin(-2.79553);
            if (Math.abs(x - (-1.570796)) < 1e-6) return Math.sin(-1.570796);
            if (Math.abs(x - (-1.118)) < 1e-6) return Math.sin(-1.118);
            if (Math.abs(x - (-0.847864)) < 1e-6) return Math.sin(-0.847864);
            if (Math.abs(x - (-0.5423)) < 1e-6) return Math.sin(-0.5423);

            if (Math.abs(x - (0.57772)) < 1e-6) return Math.sin(0.57772);
            if (Math.abs(x - (0.749)) < 1e-6) return Math.sin(0.749);
            if (Math.abs(x - (0.953)) < 1e-6) return Math.sin(0.953);
            if (Math.abs(x - (1.0)) < 1e-6) return Math.sin(1);
            if (Math.abs(x - (1.453245)) < 1e-6) return Math.sin(1.453245);
            if (Math.abs(x - (1.73094)) < 1e-6) return Math.sin(1.73094);

            return null;
        });

        mockedCos.when(() -> Cos.cos(anyDouble(), eq(DEFAULT_TERMS))).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);

            if (Math.abs(x - (-5.84595)) < 1e-6) return Math.cos(-5.84595);
            if (Math.abs(x - (-4.71239)) < 1e-6) return Math.cos(-4.71239);
            if (Math.abs(x - (-4.5513)) < 1e-6) return Math.cos(-4.5513);
            if (Math.abs(x - (-4.4135)) < 1e-6) return Math.cos(-4.4135);
            if (Math.abs(x - (-4.26925)) < 1e-6) return Math.cos(-4.26925);
            if (Math.abs(x - (-3.51278)) < 1e-6) return Math.cos(-3.51278);

            if (Math.abs(x - (-2.79553)) < 1e-6) return Math.cos(-2.79553);
            if (Math.abs(x - (-1.570796)) < 1e-6) return Math.cos(-1.570796);
            if (Math.abs(x - (-1.118)) < 1e-6) return Math.cos(-1.118);
            if (Math.abs(x - (-0.847864)) < 1e-6) return Math.cos(-0.847864);
            if (Math.abs(x - (-0.5423)) < 1e-6) return Math.cos(-0.5423);

            if (Math.abs(x - (0.57772)) < 1e-6) return Math.cos(0.57772);
            if (Math.abs(x - (0.749)) < 1e-6) return Math.cos(0.749);
            if (Math.abs(x - (0.953)) < 1e-6) return Math.cos(0.953);
            if (Math.abs(x - (1.0)) < 1e-6) return Math.cos(1.0);
            if (Math.abs(x - (1.453245)) < 1e-6) return Math.cos(1.453245);
            if (Math.abs(x - (1.73094)) < 1e-6) return Math.cos(1.73094);

            return null;
        });

        mockedTan.when(() -> Tan.tan(anyDouble(), eq(DEFAULT_TERMS))).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);

            if (Math.abs(x - (-5.84595)) < 1e-6) return Math.tan(-5.84595);
            if (Math.abs(x - (-4.71239)) < 1e-6) return Math.tan(-4.71239);
            if (Math.abs(x - (-4.5513)) < 1e-6) return Math.tan(-4.5513);
            if (Math.abs(x - (-4.4135)) < 1e-6) return Math.tan(-4.4135);
            if (Math.abs(x - (-4.26925)) < 1e-6) return Math.tan(-4.26925);
            if (Math.abs(x - (-3.51278)) < 1e-6) return Math.tan(-3.51278);

            if (Math.abs(x - (-2.79553)) < 1e-6) return Math.tan(-2.79553);
            if (Math.abs(x - (-1.570796)) < 1e-6) return Math.tan(-1.570796);
            if (Math.abs(x - (-1.118)) < 1e-6) return Math.tan(-1.118);
            if (Math.abs(x - (-0.847864)) < 1e-6) return Math.tan(-0.847864);
            if (Math.abs(x - (-0.5423)) < 1e-6) return Math.tan(-0.5423);

            if (Math.abs(x - (0.57772)) < 1e-6) return Math.tan(0.57772);
            if (Math.abs(x - (0.749)) < 1e-6) return Math.tan(0.749);
            if (Math.abs(x - (0.953)) < 1e-6) return Math.tan(0.953);
            if (Math.abs(x - (1.0)) < 1e-6) return Math.tan(1.0);
            if (Math.abs(x - (1.453245)) < 1e-6) return Math.tan(1.453245);
            if (Math.abs(x - (1.73094)) < 1e-6) return Math.tan(1.73094);

            return null;
        });

        mockedCsc.when(() -> Csc.csc(anyDouble(), eq(DEFAULT_TERMS))).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);

            if (Math.abs(x - (-5.84595)) < 1e-6) return 1.0 / Math.sin(-5.84595);
            if (Math.abs(x - (-4.71239)) < 1e-6) return 1.0 / Math.sin(-4.71239);
            if (Math.abs(x - (-4.5513)) < 1e-6) return 1.0 / Math.sin(-4.5513);
            if (Math.abs(x - (-4.4135)) < 1e-6) return 1.0 / Math.sin(-4.4135);
            if (Math.abs(x - (-4.26925)) < 1e-6) return 1.0 / Math.sin(-4.26925);
            if (Math.abs(x - (-3.51278)) < 1e-6) return 1.0 / Math.sin(-3.51278);

            if (Math.abs(x - (-2.79553)) < 1e-6) return 1.0 / Math.sin(-2.79553);
            if (Math.abs(x - (-1.570796)) < 1e-6) return 1.0 / Math.sin(-1.570796);
            if (Math.abs(x - (-1.118)) < 1e-6) return 1.0 / Math.sin(-1.118);
            if (Math.abs(x - (-0.847864)) < 1e-6) return 1.0 / Math.sin(-0.847864);
            if (Math.abs(x - (-0.5423)) < 1e-6) return 1.0 / Math.sin(-0.5423);

            if (Math.abs(x - (0.57772)) < 1e-6) return 1.0 / Math.sin(0.57772);
            if (Math.abs(x - (0.749)) < 1e-6) return 1.0 / Math.sin(0.749);
            if (Math.abs(x - (0.953)) < 1e-6) return 1.0 / Math.sin(0.953);
            if (Math.abs(x - (1.0)) < 1e-6) return 1.0 / Math.sin(1.0);
            if (Math.abs(x - (1.453245)) < 1e-6) return 1.0 / Math.sin(1.453245);
            if (Math.abs(x - (1.73094)) < 1e-6) return 1.0 / Math.sin(1.73094);

            return null;
        });

        mockedSec.when(() -> Sec.sec(anyDouble(), eq(DEFAULT_TERMS))).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);

            if (Math.abs(x - (-5.84595)) < 1e-6) return 1.0 / Math.cos(-5.84595);
            if (Math.abs(x - (-4.71239)) < 1e-6) return 1.0 / Math.cos(-4.71239);
            if (Math.abs(x - (-4.5513)) < 1e-6) return 1.0 / Math.cos(-4.5513);
            if (Math.abs(x - (-4.4135)) < 1e-6) return 1.0 / Math.cos(-4.4135);
            if (Math.abs(x - (-4.26925)) < 1e-6) return 1.0 / Math.cos(-4.26925);
            if (Math.abs(x - (-3.51278)) < 1e-6) return 1.0 / Math.cos(-3.51278);

            if (Math.abs(x - (-2.79553)) < 1e-6) return 1.0 / Math.cos(-2.79553);
            if (Math.abs(x - (-1.570796)) < 1e-6) return 1.0 / Math.cos(-1.570796);
            if (Math.abs(x - (-1.118)) < 1e-6) return 1.0 / Math.cos(-1.118);
            if (Math.abs(x - (-0.847864)) < 1e-6) return 1.0 / Math.cos(-0.847864);
            if (Math.abs(x - (-0.5423)) < 1e-6) return 1.0 / Math.cos(-0.5423);

            if (Math.abs(x - (0.57772)) < 1e-6) return 1.0 / Math.cos(0.57772);
            if (Math.abs(x - (0.749)) < 1e-6) return 1.0 / Math.cos(0.749);
            if (Math.abs(x - (0.953)) < 1e-6) return 1.0 / Math.cos(0.953);
            if (Math.abs(x - (1.0)) < 1e-6) return 1.0 / Math.cos(1.0);
            if (Math.abs(x - (1.453245)) < 1e-6) return 1.0 / Math.cos(1.453245);
            if (Math.abs(x - (1.73094)) < 1e-6) return 1.0 / Math.cos(1.73094);

            return null;
        });

        mockedLn.when(() -> Ln.ln(anyDouble(), eq(DEFAULT_TERMS))).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);

            if (x <= 0) return Double.NaN;

            if (Math.abs(x - (0.57772)) < 1e-6) return Math.log(0.57772);
            if (Math.abs(x - (0.749)) < 1e-6) return Math.log(0.749);
            if (Math.abs(x - (0.953)) < 1e-6) return Math.log(0.953);
            if (Math.abs(x - (1.0)) < 1e-6) return Math.log(1.0);
            if (Math.abs(x - (1.453245)) < 1e-6) return Math.log(1.453245);
            if (Math.abs(x - (1.73094)) < 1e-6) return Math.log(1.73094);

            return null;
        });

        mockedLogBase.when(() -> Log.log(anyDouble(), eq(2.0), eq(DEFAULT_TERMS))).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);

            if (x <= 0) return Double.NaN;

            if (Math.abs(x - (0.57772)) < 1e-6) return Math.log(0.57772) / Math.log(2);
            if (Math.abs(x - (0.749)) < 1e-6) return Math.log(0.749) / Math.log(2);
            if (Math.abs(x - (0.953)) < 1e-6) return Math.log(0.953) / Math.log(2);
            if (Math.abs(x - (1.0)) < 1e-6) return Double.NaN;
            if (Math.abs(x - (1.453245)) < 1e-6) return Math.log(1.453245) / Math.log(2);
            if (Math.abs(x - (1.73094)) < 1e-6) return Math.log(1.73094) / Math.log(2);

            return null;
        });

        mockedLogBase.when(() -> Log.log(anyDouble(), eq(5.0), eq(DEFAULT_TERMS))).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);

            if (x <= 0) return Double.NaN;

            if (Math.abs(x - (0.57772)) < 1e-6) return Math.log(0.57772) / Math.log(5);
            if (Math.abs(x - (0.749)) < 1e-6) return Math.log(0.749) / Math.log(5);
            if (Math.abs(x - (0.953)) < 1e-6) return Math.log(0.953) / Math.log(5);
            if (Math.abs(x - (1.0)) < 1e-6) return Double.NaN;
            if (Math.abs(x - (1.453245)) < 1e-6) return Math.log(1.453245) / Math.log(5);
            if (Math.abs(x - (1.73094)) < 1e-6) return Math.log(1.73094) / Math.log(5);

            return null;
        });

        mockedLogBase.when(() -> Log.log(anyDouble(), eq(10.0), eq(DEFAULT_TERMS))).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);

            if (x <= 0) return Double.NaN;

            if (Math.abs(x - (0.57772)) < 1e-6) return Math.log(0.57772) / Math.log(10);
            if (Math.abs(x - (0.749)) < 1e-6) return Math.log(0.749) / Math.log(10);
            if (Math.abs(x - (0.953)) < 1e-6) return Math.log(0.953) / Math.log(10);
            if (Math.abs(x - (1.0)) < 1e-6) return Double.NaN;
            if (Math.abs(x - (1.453245)) < 1e-6) return Math.log(1.453245) / Math.log(10);
            if (Math.abs(x - (1.73094)) < 1e-6) return Math.log(1.73094) / Math.log(10);

            return null;
        });
    }

    @AfterEach
    void tearDown() {
        mockedSin.close();
        mockedCos.close();
        mockedTan.close();
        mockedCsc.close();
        mockedSec.close();
        mockedLn.close();
        mockedLogBase.close();
    }

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
