import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Task2_12 {
    private final int n;
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public Task2_12(int n) {
        this.n = n;
    }

    public void fizz() throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 5 != 0) {
                queue.put("fizz");
                break;
            }
        }
    }

    public void buzz() throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 != 0) {
                queue.put("buzz");
                break;
            }
        }
    }

    public void fizzbuzz() throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            queue.put("fizzbuzz");
            break;
        }
    }

    public void number() throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                queue.put(String.valueOf(i));
            } else {
                if (i % 3 == 0 && i % 5 == 0) {
                    queue.put("fizzbuzz");
                } else if (i % 3 == 0) {
                    queue.put("fizz");
                } else {
                    queue.put("buzz");
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 30;
        Task2_12 fizzBuzz = new Task2_12(n);

        Thread threadD = new Thread(() -> {
            try {
                fizzBuzz.number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadD.start();

        try {
            threadD.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!fizzBuzz.queue.isEmpty()) {
            try {
                System.out.println(fizzBuzz.queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}