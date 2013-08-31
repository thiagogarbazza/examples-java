package threads;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedImpl<T> implements Delayed {

    private final long maxExecTimeMillis;
    private final long startInMillis;

    public DelayedImpl( long maxExecTimeMinutes) {
    	this.maxExecTimeMillis = maxExecTimeMinutes;
    	this.startInMillis = System.currentTimeMillis();
    }

    public long getDelay(TimeUnit unit) {
        return unit.convert((startInMillis + maxExecTimeMillis*60*1000) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public int compareTo(Delayed o) {
        Long thisDelay = getDelay(TimeUnit.MILLISECONDS);
        Long thatDelay = o.getDelay(TimeUnit.MILLISECONDS);
        return thisDelay.compareTo(thatDelay);
    }
    
    public boolean inTime(){
    	if(System.currentTimeMillis() - startInMillis > maxExecTimeMillis){
    		return false;
    	}
    	return true;
    }

}