package philosophers;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Philosopher implements Runnable {

    private int myId;
    private Table myTable;

    public Philosopher(int id, Table table) {
        myId = id;
        myTable = table;
    }

    private void getLeftChopstick() throws InterruptedException {
        while (!myTable.tryGetLeftChopstick(myId)) {
            Thread.sleep((int) (Math.random() * 10));
        }
    }

    private void getRightChopstick() throws InterruptedException {
        while (!myTable.tryGetRightChopstick(myId)) {
            myTable.releaseLeftChopstick(myId);
            Thread.sleep((int) (Math.random() * 10));
            getLeftChopstick();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {

                System.out.println("Philosopher " + myId + " thinks. Iteration " + i);
                Thread.sleep((int) (Math.random() * 100));

                getLeftChopstick();
                System.out.println("Philosopher " + myId + " pick up left");
                Thread.sleep((int) (Math.random() * 100));

                getRightChopstick();
                System.out.println("Philosopher " + myId + " pick up right");

                System.out.println("Philosopher " + myId + " eats. Iteration " + i);
                Thread.sleep((int) (Math.random() * 100));

                myTable.releaseLeftChopstick(myId);
                System.out.println("Philosopher " + myId + " drop left");
                Thread.sleep((int) (Math.random() * 100));

                myTable.releaseRightChopstick(myId);
                System.out.println("Philosopher " + myId + " drop right");
                Thread.sleep((int) (Math.random() * 100));

            } catch (InterruptedException ex) {
                Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
