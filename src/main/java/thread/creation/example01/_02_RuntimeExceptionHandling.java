package thread.creation.example01;

public class _02_RuntimeExceptionHandling {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            throw new RuntimeException("BOOOM!!!");
        });

        thread.setName("New worker thread");
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("Runtime exception caught!!! Msg: " + e.getMessage());
        });

        thread.start();

        System.out.println("We are now in thread: " + Thread.currentThread().getName());
    }

}
