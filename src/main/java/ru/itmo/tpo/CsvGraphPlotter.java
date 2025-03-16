package ru.itmo.tpo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvGraphPlotter {

    public static void plotGraph(String csvFile) {
        // Чтение данных из CSV
        XYSeries series = new XYSeries("Function");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            br.readLine(); // Пропуск первой строки с заголовками
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);
                series.add(x, y);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Создание серии данных для графика
        XYSeriesCollection dataset = new XYSeriesCollection(series);

        // Создание графика
        JFreeChart chart = ChartFactory.createXYLineChart(
            "Graph for " + csvFile,   // Название графика
            "X",                      // Ось X
            "Y",                      // Ось Y
            dataset,                  // Данные
            PlotOrientation.VERTICAL, // Ориентация графика
            true,                     // Легенда
            true,                     // Подсказки
            false                     // URL-обработчики
        );

        // Отображение графика в окне
        JFrame frame = new JFrame("Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        plotGraph("C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\sin.csv");
        plotGraph("C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\cos.csv");
        plotGraph("C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\tan.csv");
        plotGraph("C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\sec.csv");
        plotGraph("C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\csc.csv");
        plotGraph("C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\ln.csv");
        plotGraph("C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\log2.csv");
        plotGraph("C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\log5.csv");
        plotGraph("C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\log10.csv");
        plotGraph("C:\\Users\\horob\\Desktop\\ITMO\\ТПО\\lab2\\src\\main\\resources\\system.csv");
    }
}

