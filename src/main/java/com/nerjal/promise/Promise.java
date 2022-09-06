package com.nerjal.promise;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Base interface for promises.
 * Allows both checks for state and final value.
 * @param <T> The result type class
 */
public abstract class Promise<T> {
    /**
     * The promise's lock, used to check
     * if it is still running.
     */
    protected final Lock lock = new ReentrantLock();
    /**
     * The promise's result, null until completion.
     */
    protected T result = null;
    /**
     * The runnable to be executed to obtain
     * the final value
     */
    private final RunnableFuture<T> run;

    /**
     * Base constructor for the promise class
     * @param future the runnable to be executed
     *               to obtain the final value
     */
    public Promise(RunnableFuture<T> future) {
        this.run = future;
    }

    /**
     * Starts the promise. Shouldn't be able to run if
     * it has already run once.
     * @return the promise itself.
     * @throws UnsupportedOperationException if the promise cannot be started
     *         (e.g. If it was already run)
     */
    public abstract Promise<T> start() throws UnsupportedOperationException;

    /**
     * Base execution method. Shall be called by {@link #start}
     */
    protected final void run() {
        this.run.run();
        try {
            this.result = this.run.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Whether the promise is closed or not.
     * A promise should be closed after running.
     * @return whether the promise is closed.
     */
    public boolean closed() {
        return result != null;
    }

    /**
     * Whether the promise is opened or not.
     * A promise should be opened while it runs.
     * Returns the opposite of {@link #closed}
     * @return whether the promise is opened.
     */
    public final boolean opened() {
        return !closed();
    }

    /**
     * Returns the promise's value.
     * Force-waits for the promises' completion
     * if it is still running.
     * @return the promise's processed value
     */
    public T get() {
        lock.lock();
        lock.unlock();
        return result;
    }
}
