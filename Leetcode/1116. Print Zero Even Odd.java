1116. Print Zero Even Odd



class ZeroEvenOdd {
    private int n;
    private Semaphore zeroLock = new Semaphore(1);
    private Semaphore evenLock = new Semaphore(0);
    private Semaphore oddLock = new Semaphore(0);
    
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i = 0; i < n; ++i){
            zeroLock.acquire();
            printNumber.accept(0);
            
            if(i % 2 == 0){
                oddLock.release();
            } else {
                evenLock.release();
            }
            
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i = 2; i <= n; i = i + 2){
            evenLock.acquire();
            printNumber.accept(i);
            zeroLock.release();
        }
        
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n; i = i + 2){
            oddLock.acquire();
            printNumber.accept(i);
            zeroLock.release();
        }
        
    }
}