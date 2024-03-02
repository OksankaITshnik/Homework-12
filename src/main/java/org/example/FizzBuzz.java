import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;
    private int i = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        while(i <= n) {
            if(i%3 == 0 && i%5 !=0) {
                printFizz.run();
                i++;
                notifyAll();
            }
            else {
                wait();
            }
        }
    }

    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while(i <= n) {
            if(i%5 == 0 && i%3 !=0) {
                printBuzz.run();
                i++;
                notifyAll();
            }
            else {
                wait();
            }
        }
    }


    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while(i <= n) {
            if(i%15==0) {
                printFizzBuzz.run();
                i++;
                notifyAll();
            }
            else {
                wait();
            }
        }
    }

    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while(i <= n) {
            if(i%3!=0&&i%5!=0) {
                printNumber.accept(i);
                i++;
                notifyAll();
            }
            else {
                wait();
            }
        }
    }

    public static void main(String[] args) {
        int n = 31;
        FizzBuzz fizzBuzz = new FizzBuzz(n);

        Thread fizzThread = new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.print("fizz "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread buzzThread = new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.print("buzz "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread fizzBuzzThread = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.print("fizzbuzz "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread numberThread = new Thread(() -> {
            try {
                fizzBuzz.number(x -> System.out.print(x + " "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        fizzThread.start();
        buzzThread.start();
        fizzBuzzThread.start();
        numberThread.start();

        try {
            fizzThread.join();
            buzzThread.join();
            fizzBuzzThread.join();
            numberThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
