
public class Stopwatch {

    public void start(int timeLimitS) {
        Thread thread = new Thread(() -> {
            int elapsedTimeMs = 0;
            int stepMs = 10;
            try {
                while (elapsedTimeMs <= timeLimitS * 1000) {
                    System.out.println("Stopwatch thread. Elapsed: " + Double.toString(elapsedTimeMs / 1000.0) + "s.");
                    Thread.sleep(stepMs);
                    elapsedTimeMs += stepMs;
                }
            } catch (InterruptedException ie) {
                System.err.println("err");
                System.err.println(ie);
            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            System.err.println("join interrupted");
        }
    }
}
