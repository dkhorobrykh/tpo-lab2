package ru.itmo.tpo;

import static ru.itmo.tpo.Cos.cos;
import static ru.itmo.tpo.Csc.csc;
import static ru.itmo.tpo.Sec.sec;
import static ru.itmo.tpo.Sin.sin;
import static ru.itmo.tpo.Tan.tan;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.function.Function;

public class CsvWriter {

    public static void writeToCSV(
        double start,
        double end,
        double step,
        int terms,
        String filename,
        Function<Double, Double> func
    ) {
        Locale.setDefault(Locale.US);

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("X,Result");

            for (double x = start; x <= end; x += step) {
                try {
                    double result = func.apply(x);
                    writer.printf("%f,%f%n", x, result);  // Запись строки в CSV
                } catch (Exception ignored) {
                }
            }
            System.out.println("Данные успешно записаны в файл " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        double start = -10;      // Начальное значение X
        double end = 10;       // Конечное значение X
        double step = 0.001;     // Шаг наращивания X
        int terms = 20;        // Количество членов ряда Тейлора для вычислений

        writeToCSV(
            start,
            end,
            step,
            terms,
            "C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\sin.csv",
            x -> sin(x, terms)
        );

        writeToCSV(
            start,
            end,
            step,
            terms,
            "C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\cos.csv",
            x -> cos(x, terms)
        );

        writeToCSV(
            start,
            end,
            step,
            terms,
            "C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\tan.csv",
            x -> tan(x, terms)
        );

        writeToCSV(
            start,
            end,
            step,
            terms,
            "C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\sec.csv",
            x -> sec(x, terms)
        );

        writeToCSV(
            start,
            end,
            step,
            terms,
            "C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\csc.csv",
            x -> csc(x, terms)
        );

        writeToCSV(
            start,
            end,
            step,
            terms,
            "C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\ln.csv",
            x -> Ln.ln(x, terms)
        );

        writeToCSV(
            start,
            end,
            step,
            terms,
            "C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\log2.csv",
            x -> Log.log(x, 2, terms)
        );

        writeToCSV(
            start,
            end,
            step,
            terms,
            "C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\log5.csv",
            x -> Log.log(x, 5, terms)
        );

        writeToCSV(
            start,
            end,
            step,
            terms,
            "C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\log10.csv",
            x -> Log.log(x, 10, terms)
        );

        writeToCSV(
            start,
            end,
            step,
            terms,
            "C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\system.csv",
            x -> SystemFunction.system(x, terms)
        );
    }
}
