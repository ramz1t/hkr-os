package readerwriter;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentantLockRWLockAdapter extends RWLock {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    @Override
    public void acquireRead() {
        lock.readLock().lock();
    }

    @Override
    public void releaseRead() {
        lock.readLock().unlock();
    }

    @Override
    public void acquireWrite() {
        lock.writeLock().lock();
    }

    @Override
    public void releaseWrite() {
        lock.writeLock().unlock();
    }
}
