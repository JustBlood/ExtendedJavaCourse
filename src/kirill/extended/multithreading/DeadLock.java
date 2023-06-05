package kirill.extended.multithreading;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        Thread thread1 = new Thread(() -> {
            try {
                runner.firstThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                runner.secondThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long before = System.currentTimeMillis();
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        long after = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (after - before) + " ms");
        runner.getFinishInfo();
    }
}

class Runner {
    private final Account account1 = new Account();
    private final Account account2 = new Account();
    private final Random random = new Random();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void takeLocks(Lock lock1, Lock lock2) {
        boolean isFirstLockTaken = false;
        boolean isSecondLockTaken = false;

        while (true) {
            try {
                isFirstLockTaken = lock1.tryLock();
                isSecondLockTaken = lock2.tryLock();
            } finally {
                if (isFirstLockTaken && isSecondLockTaken) {
                    return;
                }

                if (isFirstLockTaken) {
                    lock1.unlock();
                }

                if (isSecondLockTaken) {
                    lock2.unlock();
                }

                try {
                    Thread.sleep(0,1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void firstThread() throws InterruptedException {
        for (int i = 0; i < 10000000; i++) {
            takeLocks(lock1, lock2);
            try {
                Account.transfer(account1, account2, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() throws InterruptedException {
        for (int i = 0; i < 10000000; i++) {
            takeLocks(lock2, lock1);
            try {
                Account.transfer(account2, account1, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void getFinishInfo() {
        System.out.println("Account1: " + account1.getBalance());
        System.out.println("Account2: " + account2.getBalance());
        System.out.println("Total: " + (account1.getBalance() + account2.getBalance()));
    }
}

class Account {
    private int balance = 10000;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public static void transfer(Account acc1, Account acc2, int amount) {
        acc1.withdraw(amount);
        acc2.deposit(amount);
    }
}
