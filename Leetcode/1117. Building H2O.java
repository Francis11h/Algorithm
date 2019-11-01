1117. Building H2O



class H2O {
    private Semaphore h;
    private Semaphore o;
    private int number;
    public H2O() {
        h = new Semaphore(2);
        o = new Semaphore(0);
        number = 0;
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
		h.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        number++;
        if (number % 2 == 0) {
            o.release();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
		releaseOxygen.run();
        h.release();
        h.release();
    }
}