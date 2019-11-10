Read / Write Lock Java Implementation


Read Access   	If no threads are writing, and no threads have requested write access.
Write Access   	If no threads are reading or writing.

 A lock acquired for reading can be shared by other readers, but a lock acquired for writing must be exclusive.

Yet another problem that occurs due to the interactions between process synchronization and process scheduling is priority inversion
Priority inversion occurs when a higher priority thread is blocked waiting on a lock (or a semaphore) held by a lower priority thread. 
高优先级线程 被 持有锁的低优先级线程 block了:  用 priority inheritance 来解决


Inside the process table entry:
a)Theoriginal priority (pprio) with which a process is created. 
b) pinh – The current inherited priority of the process. 
	This value can be 0 when the process is running with its original priority. 
c) A bit mask or a linked list through which all the locks held by the process can be found. 
d) An integer value lockid indicating the lock descriptor in whose wait queue the process is blocked. 
	It can take the value -1 when the process is not blocked inside any wait queue.



lock: Suppose that the process P1 requests a lock. 
	If the lock is available, then nothing needs to be done. 

	Otherwise, if the priority of the process (P2) holding the lock is no less than the priority of P1, 
	then also nothing has to be done. 


	Otherwise the priority of P2 (pinh) has to be ramped up to被提升到 the priority of P1 . 
	After ramping up the priority of P1, we also have to take care of the transitivity of priority inheritance as discussed earlier. 

Release: On releasing a lock, the priority of the process has to be reset to the maximum priority of all the processes in the wait queues of all the locks still held by the process. 
		The bitmask/linked list field in the process table entry can be used for this purpose. 
		Note that multiple locks can be released by a process. 

		 If a process P1, in the wait queue of a lock L has its priority changed or is killed, we need to recalculate the maximum priority of all the processes in L’s wait queue, and update the priority of the processes holding L too, if necessary. T




public class ReadWriteLock{

  private int readers       = 0;
  private int writers       = 0;
  private int writeRequests = 0;

  public synchronized void lockRead() throws InterruptedException{
    while(writers > 0 || writeRequests > 0){
      wait();
    }
    readers++;
  }

  public synchronized void unlockRead(){
    readers--;
    notifyAll();
  }

  public synchronized void lockWrite() throws InterruptedException{
    writeRequests++;

    while(readers > 0 || writers > 0){
      wait();
    }
    writeRequests--;
    writers++;
  }

  public synchronized void unlockWrite() throws InterruptedException{
    writers--;
    notifyAll();
  }
}




