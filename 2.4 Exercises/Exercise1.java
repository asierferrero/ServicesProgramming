class MyRunnable implements Runnable {
    private int num;

    public MyRunnable(int n) {
        this.num = n;
    }

    public void run() {
        int n1 = 1, n2 = 1, count;
        System.out.print(n1 + " ");
        for (int i = 2; i < num; i++) {
            count = n1 + n2;
            System.out.print(count + " ");
            n1 = n2;
            n2 = count;
        }
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        int num = 10; // Count of Fibonacci numbers
        MyRunnable myRunnable = new MyRunnable(num);
        Thread thread1 = new Thread(myRunnable);

        // Start the thread
        thread1.start();
    }
}
