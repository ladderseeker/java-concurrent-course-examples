import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class StockMarketUpdate implements Runnable {

    @Override
    public void run() {
        System.out.println("Updating...");
    }
}

public class ScheduledThreadPoolExample {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new StockMarketUpdate(), 1000, 3000, TimeUnit.MILLISECONDS);

    }




}
