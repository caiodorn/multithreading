package thread.creation.example01;

public class _03_ExtendsThread {

    public static void main(String[] args) {
        Thread thread = new NewThread();

        thread.start();
    }

    private static class NewThread extends Thread {

        @Override
        public void run() {
            System.out.println("We are now in thread: " + this.getName());
        }

    }

}
