package thread.creation.example01;

public class _01_Basic {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("We are now in thread: " + Thread.currentThread().getName());
            System.out.println("Current thread priority is: " + Thread.currentThread().getPriority());
            System.out.println("Current thread's group is: " + Thread.currentThread().getThreadGroup());
        });

        thread.setName("New worker thread");
        thread.start();

        System.out.println("We are now in thread: " + Thread.currentThread().getName());
    }

}
