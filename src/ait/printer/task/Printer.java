package ait.printer.task;

public class Printer implements Runnable {
    private int number;
    private Thread nextThread; // Ссылка на следующий поток

    public Printer(int number) {
        this.number = number; // Номер принтера
    }

    public void setNextThread(Thread nextThread) {
        this.nextThread = nextThread; // Устанавливаем ссылку на следующий поток
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // Печатаем 10 порций
            try {
                System.out.println(String.valueOf(number).repeat(10)); // Печать порции чисел
                Thread.sleep(1000); // Пауза в 1 секунду
            } catch (InterruptedException e) {
//                System.out.println("Thread interrupted: " + e); // Обработка прерывания потока
                {
                    nextThread.interrupt(); // Передача прерывания следующему потоку
                }
            }
        }
    }
}