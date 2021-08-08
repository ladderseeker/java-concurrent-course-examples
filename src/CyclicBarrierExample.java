import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Latch --> multiple threads can wait for each other
 * <p>
 * A CyclicBarrier is used in situations where you want to create a group of
 * tasks to perform work in parallel + wait until they are all finished before
 * moving on to the next step -> something like join() -> something like
 * CountDownLatch
 * <p>
 * CountDownLatch: one-shot event CyclicBarrier: it can be reused over and over
 * again
 * <p>
 * + cyclicBarrier has a barrier action: a runnable, that will run automatically
 * when the count reaches 0 !!
 * <p>
 * new CyclicBarrier(N) -> N threads will wait for each other
 * <p>
 * WE CAN NOT REUSE LATCHES BUT WE CAN REUSE CyclicBarriers --> reset() !!!
 */

class CyclicBarrierWorker implements Runnable {

    private int id;
    private Random random;
    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierWorker(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
        this.random = new Random();
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with ID " + id + " starts the task...");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread with ID " + id + " finished...");

        try {
            cyclicBarrier.await();
            System.out.println("After await...");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "" + this.id;
    }

}

public class CyclicBarrierExample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        CyclicBarrier barrier = new CyclicBarrier(4,
                () -> System.out.println("We are able to use the trained neural network..." + Thread.currentThread().getName()));

        for (int i = 0; i < 8; ++i)
            executorService.execute(new CyclicBarrierWorker(i + 1, barrier));

        executorService.shutdown();
    }
}

