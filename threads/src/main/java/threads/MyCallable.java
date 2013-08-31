package threads;

import java.util.concurrent.Callable;

class MyCallable implements Callable<Integer> {
	private final int threadnumber;

	MyCallable(int threadnumber) {
		this.threadnumber = threadnumber;
	}

	public Integer call() {
		 DelayedImpl<Integer> impl = new DelayedImpl<Integer>(0);
	      if (impl.inTime()) {
	    	  ThreadOrdering.print(threadnumber);
	      }
	    	 
		return threadnumber;
	}
}