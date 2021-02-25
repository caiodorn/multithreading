package thread.creation.example03;

import java.math.BigInteger;

public class _07_DaemonThread {

    public static void main(String[] args) {
        Thread t = new Thread(new LongComputationTask(BigInteger.valueOf(200_000_000), BigInteger.valueOf(500_000_000)));

        //make it a daemon thread (i.e. non-blocking thread)
        t.setDaemon(true);
        t.start();

        //daemon threads are terminated when the main thread terminates
        //t.interrupt();
    }

    private static class LongComputationTask implements Runnable {

        private final BigInteger base;
        private final BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(this.pow(this.base, this.power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                //do check if thread has been interrupted, else won't end task
//                if (Thread.currentThread().isInterrupted()) {
//                    System.out.println("Prematurely interrupted computation!");
//                    return BigInteger.ZERO;
//                }
                result = result.multiply(base);
            }

            return result;
        }

    }

}
