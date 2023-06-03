package kirill.extended.multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
    private static void produce() throws InterruptedException {
        Random random = new Random();

        while (true) {
            queue.put(random.nextInt(100));
        }
    }

    private static void consumer() throws InterruptedException {
        Random random = new Random();

        while (true) {
            Thread.sleep(100);
            if (random.nextInt(10) == 5) {
                System.out.println(queue.take());
                System.out.println("Queue size is: " + queue.size());
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {

        //region high-level
        Thread thread1 = new Thread(() -> {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //thread1.start();
        //thread2.start();

        //thread1.join();
        //thread2.join();
        //endregion

        //region low-level
        ProducerConsumer2 producerConsumer2 = new ProducerConsumer2();
        Thread producer = new Thread(() -> {
            try {
                producerConsumer2.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                producerConsumer2.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
        //endregion
    }
}

class ProducerConsumer2 {
    private Queue<Integer> queue = new LinkedList<>();
    private final int ELEMENTS_LIMIT = 10;
    private final Object lock = new Object();
    private int value;
    public void produce() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (queue.size() == ELEMENTS_LIMIT) {
                    lock.wait();
                }

                queue.add(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (queue.size() == 0) {
                    lock.wait();
                }
                System.out.println("Queue size is: " + queue.size());
                System.out.println("Queue element: " + queue.poll());
                lock.notify();
            }
        }
    }
}
