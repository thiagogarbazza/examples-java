package threads;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DeadlineChecker implements Delayed {

    private final long maxExecTimeMinutes;
    private final long startInMillis = System.currentTimeMillis();

    public DeadlineChecker( long maxExecTimeMinutes) {
    	this.maxExecTimeMinutes = maxExecTimeMinutes;
    }

    public long getDelay(TimeUnit unit) {
        return unit.convert((startInMillis + maxExecTimeMinutes*60*1000) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public int compareTo(Delayed o) {
        Long thisDelay = getDelay(TimeUnit.MILLISECONDS);
        Long thatDelay = o.getDelay(TimeUnit.MILLISECONDS);
        return thisDelay.compareTo(thatDelay);
    }
    
    public boolean inTime(){
    	if(System.currentTimeMillis() - startInMillis > maxExecTimeMinutes*60*1000){
    		return false;
    	}
    	return true;
    }

}