package greenatom.projects.multithreading;

public class MyThread extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("Асинхронный привет!");
            try {
                MyThread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Асинхронный пока!");
            try {
                MyThread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
