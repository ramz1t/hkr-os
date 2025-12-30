package philosophers;

import java.util.concurrent.Semaphore;

public class Table {

    private final int nbrOfChopsticks;
    private final Semaphore[] chopstick;

    public Table(int nbrOfSticks) {
        this.nbrOfChopsticks = nbrOfSticks;
        this.chopstick = new Semaphore[nbrOfChopsticks];

        for (int i = 0; i < nbrOfChopsticks; i++) {
            chopstick[i] = new Semaphore(1);
        }
    }

    public boolean tryGetLeftChopstick(int n) throws InterruptedException {
        return chopstick[n].tryAcquire();
    }

    public boolean tryGetRightChopstick(int n) throws InterruptedException {
        int pos = (n + 1) % nbrOfChopsticks;
        return chopstick[pos].tryAcquire();
    }

    public void releaseLeftChopstick(int n) {
        chopstick[n].release();
    }

    public void releaseRightChopstick(int n) {
        int pos = (n + 1) % nbrOfChopsticks;
        chopstick[pos].release();
    }
}
