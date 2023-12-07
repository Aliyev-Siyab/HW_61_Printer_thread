package ait.printerclasswork.taskeduard;

public class Printer implements Runnable {
    private static final long SLEEP_TIME = 1000 * 60 * 60 * 24; // Время сна в миллисекундах,24 часа.
    static int quantity; // Общее количество символов
    static int portion; // Размер порции
    int number; // Номер принтера
    Thread nextThread; // Ссылка на следующий поток

    public Printer(int number) {
        this.number = number; // Устанавливаем номер принтера
    }

    public static int getQuantity() {
        return quantity; // Получаем общее количество символов
    }

    public static void setQuantity(int quantity) {
        Printer.quantity = quantity; // Устанавливаем общее количество символов
    }

    public static int getPortion() {
        return portion; // Получаем размер порции
    }

    public static void setPortion(int portion) {
        Printer.portion = portion; // Устанавливаем размер порции
    }

    public int getNumber() {
        return number; // Получаем номер принтера
    }

    public void setNumber(int number) {
        this.number = number; // Устанавливаем номер принтера
    }

    public Thread getNextThread() {
        return nextThread; // Получаем ссылку на следующий поток
    }

    public void setNextThread(Thread nextThread) {
        this.nextThread = nextThread; // Устанавливаем ссылку на следующий поток
    }

    @Override
    public void run() {
        int nPortion = quantity / portion; // Вычисляем количество порций для печати
        for (int i = 0; i < nPortion; i++) { // Цикл печати порций
            try {
                Thread.sleep(SLEEP_TIME); // Засыпаем на заданное время(24 часа)
            } catch (InterruptedException e) {
                printPortion(portion); // Печатаем порцию при прерывании потока
                nextThread.interrupt(); // Прерываем следующий поток
            }
        }
        int remainder = quantity % portion; // Оставшаяся часть символов
        if (remainder != 0) {
            try {
                Thread.sleep(SLEEP_TIME); // Засыпаем на заданное время
            } catch (InterruptedException e) {
                printPortion(remainder); // Печатаем оставшуюся часть при прерывании потока
                nextThread.interrupt(); // Прерываем следующий поток
            }
        }

    }

    private void printPortion(int portion) {
        for (int i = 0; i < portion; i++) { // Печатаем порцию символов
            System.out.print(number);
        }
        System.out.println(); // Переход на новую строку
    }

}
