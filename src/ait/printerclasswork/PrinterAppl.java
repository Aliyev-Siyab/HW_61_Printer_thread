package ait.printerclasswork;
import ait.printerclasswork.taskeduard.Printer;

public class PrinterAppl {

    private static final int QUANTITY = 95; // Общее количество символов для печати
    private static final int PORTION = 10; // Размер порции для печати
    private static final int N_TASKS = 4; // Количество задач (принтеров)

    public static void main(String[] args) {
        Printer.setQuantity(QUANTITY); // Устанавливаем общее количество символов
        Printer.setPortion(PORTION); // Устанавливаем размер порции
        Printer[] tasks = new Printer[N_TASKS]; // Массив задач (принтеров)
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new Printer(i + 1); // Создаем и инициализируем задачи (принтеры)
        }
        Thread[] threads = new Thread[N_TASKS]; // Массив потоков
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(tasks[i]); // Создаем потоки на основе задач (принтеров)
        }
        for (int i = 0; i < tasks.length - 1; i++) {
            tasks[i].setNextThread(threads[i + 1]); // Устанавливаем ссылку на следующий поток для каждой задачи (принтера)
        }
        tasks[tasks.length - 1].setNextThread(threads[0]); // Устанавливаем ссылку на первый поток для последней задачи
        for (int i = 0; i < threads.length; i++) {
            threads[i].start(); // Запускаем потоки
        }
        threads[0].interrupt(); // Прерываем первый поток, начиная процесс печати
    }
}