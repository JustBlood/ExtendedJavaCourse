package kirill.extended.multithreading;

public class Synchronized {
    private static int counter;
    public static void main(String[] args) throws InterruptedException {
        Synchronized object = new Synchronized();
        Thread thread1 = new Thread(object::Method);
        Thread thread2 = new Thread(object::Method);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(counter);
    }

    public synchronized void Method() {
        for (int i = 0; i < 100000; i++) {
            counter++;
            System.out.println("Hello from " + Thread.currentThread().getName() + "!");
        }
    }
}
