package thread.creation.example03;

public class _05_LongRunningThread {

    public static void main(String[] args) {
        Thread t = new Thread(new LongRunningTask());
        t.start();
        // if interrupt() is not called, thread will keep running long after main thread has stopped;
        t.interrupt();
    }

    private static class LongRunningTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(500_000);
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted!");
            }
        }

    }

}
