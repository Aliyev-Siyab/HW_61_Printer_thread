package ait.printerclasswork;
import ait.printerclasswork.taskeduard.Printer;

public class PrinterAppl {

    private static final int QUANTITY = 95;
    private static final int PORTION = 10;
    private static final int N_TASKS = 4;

    public static void main(String[] args) {
        Printer.setQuantity(QUANTITY);
        Printer.setPortion(PORTION);
        Printer[] tasks = new Printer[N_TASKS];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new Printer(i + 1);
        }
        Thread[] threads = new Thread[N_TASKS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(tasks[i]);
        }
        for (int i = 0; i < tasks.length - 1; i++) {
            tasks[i].setNextThread(threads[i + 1]);
        }
        tasks[tasks.length - 1].setNextThread(threads[0]);
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        threads[0].interrupt();

    }

}