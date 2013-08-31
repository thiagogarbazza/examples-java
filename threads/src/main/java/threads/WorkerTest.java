package threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class WorkerTest<T> {

    private final BlockingQueue<T> workQueue;
    private final ExecutorService service;

    public WorkerTest(int numWorkers, int workQueueSize) {
        workQueue = new LinkedBlockingQueue<T>(workQueueSize);
        service = Executors.newFixedThreadPool(numWorkers);

        for (int i=0; i < numWorkers; i++) {
            service.submit(new Worker<T>(workQueue,i));
        }
    }

    public void produce(T item) {
        try {
            workQueue.put(item);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }


    private static class Worker<T> implements Runnable {
        private final BlockingQueue<T> workQueue;
        private final Integer id;
        public Worker(BlockingQueue<T> workQueue, Integer id) {
            this.workQueue = workQueue;
            this.id = id;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                	T item = workQueue.take();
                    System.out.println("Thread "+id+" tentando realizar print");
                    print((Integer)item,id);
                    // Process item
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
    
    
    public static synchronized void print (Integer item, Integer id){
    	System.out.println("Thread "+id+" pegou o item "+item);
    	System.out.println("Thread "+id+" saiu");
    }
    public static void main(String[] args) {
		WorkerTest<Integer> teste = new WorkerTest<Integer>(20, 5);
		teste.produce(1);
		teste.produce(2);
		teste.produce(3);
		teste.produce(4);
		teste.produce(5);
		
	}
}