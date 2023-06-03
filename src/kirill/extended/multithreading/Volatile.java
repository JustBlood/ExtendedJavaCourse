package kirill.extended.multithreading;

import java.util.Scanner;

public class Volatile {
    public static void main(String[] args) {
        MyThread thread = new MyThread();

        thread.start();

        new Scanner(System.in).nextLine();

        thread.shutdown();
    }

    public static class MyThread extends Thread {
        private volatile boolean isRunning = true;
        public void run() {
            while (isRunning)
                System.out.println("Hello from thread №" + this.threadId());
        }

        public void shutdown() {
            isRunning = false;
        }
    };
}
