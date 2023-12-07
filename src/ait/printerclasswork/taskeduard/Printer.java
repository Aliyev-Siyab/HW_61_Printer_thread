package ait.printerclasswork.taskeduard;

public class Printer implements Runnable {
    private static final long SLEEP_TIME = 1000 * 60 * 60 * 24;
    static int quantity;
    static int portion;
    int number;
    Thread nextThread;

    public Printer(int number) {
        this.number = number;
    }

    public static int getQuantity() {
        return quantity;
    }

    public static void setQuantity(int quantity) {
        Printer.quantity = quantity;
    }

    public static int getPortion() {
        return portion;
    }

    public static void setPortion(int portion) {
        Printer.portion = portion;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Thread getNextThread() {
        return nextThread;
    }

    public void setNextThread(Thread nextThread) {
        this.nextThread = nextThread;
    }

    @Override
    public void run() {
        int nPortion = quantity / portion;
        for (int i = 0; i < nPortion; i++) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                printPortion(portion);
                nextThread.interrupt();
            }
        }
        int remainder = quantity % portion;
        if(remainder != 0) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                printPortion(remainder);
                nextThread.interrupt();
            }
        }

    }

    private void printPortion(int portion) {
        for (int i = 0; i < portion; i++) {
            System.out.print(number);
        }
        System.out.println();
    }

}