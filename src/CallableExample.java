import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Processor implements Callable<String> {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(500);
        return "Id : " + id;
    }
}

public class CallableExample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        List<Future<String>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> futureItem = executorService.submit(new Processor(i + 1));
            futureList.add(futureItem);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Future<String> future : futureList) {

            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }

}
