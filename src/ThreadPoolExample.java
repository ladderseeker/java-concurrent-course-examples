import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {

    private final int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {

        System.out.println("Task " + id + " is working on - thread " + Thread.currentThread().getId());

        long duration = (long) Math.random();

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ThreadPoolExample {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(6);

        for (int i = 0; i < 100; i++) {
            executor.execute(new Task(i));
        }

        // prevent the executor to execute any further tasks
        executor.shutdown();

        // terminate running tasks after some time waiting
        try {
            if (!executor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
//                executor.shutdownNow();
            }
        } catch (InterruptedException e) {

        }

    }
}
