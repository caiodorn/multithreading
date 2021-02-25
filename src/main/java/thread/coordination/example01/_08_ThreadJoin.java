package thread.coordination.example01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class _08_ThreadJoin {

    public static void main(String[] args) {
        List<Long> inputNumbers = List.of(0L, 3435999999999999L, 35435L, 4656L, 23L, 5556L);
        List<FactorialThread> threads = new ArrayList<>();

        //create threads
        inputNumbers.forEach(i -> threads.add(new FactorialThread(i)));

        //start threads
        threads.forEach(t -> {
            t.setDaemon(true); //else thread won't be terminated regardless of join invocation
            t.start();
        });

        //join -> will only proceed with main thread exec AFTER other threads have finished; without it, results
        // will be missed due to racing condition.
        threads.forEach(factorialThread -> {
            try {
                factorialThread.join(2000); // wait for no more than 2 seconds for each thread to do its job
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //check execution status
        threads.forEach(t -> {
            if (t.isFinished) {
                System.out.println("Factorial of " + t.getInputNumber() + " is " + t.getResult());
            } else {
                System.out.println("The calculation of the factorial of " + t.getInputNumber() + " is still in progress... ");
            }
        });
    }

    private static class FactorialThread extends Thread {

        private final long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        public BigInteger factorial(long n) {
            BigInteger tempResult = BigInteger.ONE;

            for (long i = n; i > 0; i--) {
                tempResult = tempResult.multiply(BigInteger.valueOf(i));
            }

            return tempResult;
        }

        public BigInteger getResult() {
            return result;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public long getInputNumber() {
            return this.inputNumber;
        }

    }

}
