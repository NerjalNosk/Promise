package com.nerjal.promise;

import java.util.concurrent.RunnableFuture;

/**
 * A threaded implementation of
 * the Promise class
 */
public class ThreadedPromise<T> extends Promise<T> {
    /**
     * The process thread to be run
     */
    protected Thread t;

    /**
     * Base constructor for the threaded promise
     * @param future the runnable to be executed
     *               to obtain the final value
     */
    public ThreadedPromise(RunnableFuture<T> future) {
        super(future);
    }

    @Override
    public Promise<T> start() throws UnsupportedOperationException {
        if (this.closed()) return this;
        this.t = new Thread(() -> {
            lock.lock();
            this.run();
            lock.unlock();
        });
        this.t.start();
        return this;
    }
}
