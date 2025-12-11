package readerwriter;

public class RWLock {

    private int readers = 0;
    private int writersWaiting = 0;
    private boolean isWriting = false;

    public RWLock() {
    }

    public synchronized void acquireRead() {
        while (isWriting || writersWaiting > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        readers++;
    }

    public synchronized void acquireWrite() {
        writersWaiting++;
        while (isWriting || readers > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        writersWaiting--;
        isWriting = true;
    }

    public synchronized void releaseRead() {
        readers--;
        if (readers == 0) {
            notifyAll();
        }
    }

    public synchronized void releaseWrite() {
        isWriting = false;
        notifyAll();
    }

}
