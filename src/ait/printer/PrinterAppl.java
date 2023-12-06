package ait.printer;

import ait.printer.task.Printer;

public class PrinterAppl {

    public static void main(String[] args) {
        Printer[] printers = new Printer[4]; // Массив потоков Printer
        for (int i = 0; i < printers.length; i++) {
            printers[i] = new Printer(i); // Создание и инициализация потоков Printer
        }

        Thread[] threads = new Thread[4]; // Массив потоков
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(printers[i]); // Создание потоков из объектов Printer
        }

        for (int i = 0; i < threads.length; i++) {
            int nextIndex = (i + 1) % threads.length; // Определение индекса следующего потока по кругу
            printers[i].setNextThread(threads[nextIndex]); // Установка ссылки на следующий поток
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start(); // Запуск всех потоков
        }

        threads[0].interrupt(); // Прерывание первого потока, чтобы началась цепочка прерывания
    }
}