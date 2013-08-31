package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadOrdering {
	
	public static synchronized void print (Integer id){
		System.out.println("Running thread #" + id);
	}

	static int NUM_THREADS = 10;

	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(NUM_THREADS - 5);

		List<Callable<Integer>> callables = new ArrayList<Callable<Integer>>();
		for (int i = 1; i <= NUM_THREADS; i++) {
			callables.add(new MyCallable(i));
		}
		try {
			List<Future<Integer>> results = exec.invokeAll(callables);
			for (Future<Integer> result : results) {
				System.out.println("Got result of thread #" + result.get());
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} catch (ExecutionException ex) {
			ex.printStackTrace();
		} finally {
			exec.shutdownNow();
		}
		

	}

}