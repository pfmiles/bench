package functionality;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Execute any code snippet in limited time. Usage:
 * 
 * <pre>
 * new TimedRunner(1000) {
 * 
 *     protected void run() {
 *         // business logic goes here... should respond to interruptions correctly
 *         ...
 *     }
 * 
 * };
 * </pre>
 * 
 * this means the 'business code' should complete running in 1000ms, or it would
 * be canceled.
 * 
 * @author pf-miles
 * 
 */
public abstract class TimedRunner {

    public static final ExecutorService exeService = Executors.newCachedThreadPool(new ThreadFactory() {

        private AtomicInteger threadNum = new AtomicInteger();

        public Thread newThread(Runnable r) {
            Thread ret = new Thread(r, "timed-runner-execution-thread-" + threadNum.getAndIncrement());
            ret.setDaemon(true);
            return ret;
        }
    });

    private boolean timedOut;

    public TimedRunner(int timeLimit) {

        Future<?> f = exeService.submit(new Runner());

        try {
            f.get(timeLimit, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            timedOut = true;
            f.cancel(true);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private class Runner implements Runnable {

        public void run() {
            TimedRunner.this.run();
        }
    }

    protected abstract void run();

    public boolean isTimedOut() {
        return timedOut;
    }

}
