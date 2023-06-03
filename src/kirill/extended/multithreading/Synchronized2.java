package kirill.extended.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Synchronized2 {
    Random random = new Random();
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void addToList1() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt());
        }
    }
    public void addToList2() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt());
        }
    }
    public void work() {
        for (int i = 0; i < 1000; i++) {
            addToList1();
            addToList2();
        }
    }

    public void main2() throws InterruptedException {

        Thread thread1 = new Thread(this::work);
        Thread thread2 = new Thread(this::work);

        long before = System.currentTimeMillis();

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        long after = System.currentTimeMillis();

        // if not sync - 4_000+ ms. if sync in method - 8_000+ ms. if sync on different objects - 4_000 ms
        System.out.println("Program took " + (after - before) + "ms to run");
        System.out.println(list1.size() + "\n" + list2.size());

    }

    public static void main(String[] args) throws InterruptedException {
        Synchronized2 obj = new Synchronized2();
        obj.main2();
    }
}
