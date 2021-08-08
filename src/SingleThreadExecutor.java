import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Work implements Runnable {

    private final int id;

    public Work(int id) {
        this.id = id;
    }

    @Override
    public void run() {

        System.out.println("Task " + id + " is working on - thread " + Thread.currentThread().getName());

        long duration = (long) Math.random();

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SingleThreadExecutor {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            executor.execute(new Work(i));
        }

    }
}
