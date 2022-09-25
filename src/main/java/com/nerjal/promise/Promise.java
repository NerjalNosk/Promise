package com.nerjal.promise;

import java.util.concurrent.RunnableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

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
    protected Consumer<Exception> catcher = (e) -> {
        throw new PromiseException("Uncaught exception while running promise",e);
    };
    protected Consumer<T> then = t -> {};

    /**
     * Base constructor for the promise class
     * @param future the runnable to be executed
     *               to obtain the final value
     */
    protected Promise(RunnableFuture<T> future) {
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
     * Sets a consumer to catch any error while the
     * promise runs. Allows for error asynchronous
     * error handling.<br>
     * Any exception thrown by the catcher is
     * passed to the caller.
     * @param catcher the exception consumer to
     *                catch any promise runtime
     *                error.
     * @return the promise itself.
     */
    public final Promise<T> catchError(Consumer<Exception> catcher) {
        this.catcher = catcher;
        return this;
    }

    /**
     * Sets a consumer to receive the promise's
     * result when it finishes running.<br>
     * Is executed out of the catcher's reach, thus
     * any exception thrown by the {@code then}
     * consumer are directly passed to the caller.
     * @param then the result consumer to be
     *             executed at the end of the
     *             promise's execution.
     * @return the promise itself.
     */
    public final Promise<T> then(Consumer<T> then) {
        this.then = then;
        return this;
    }

    /**
     * Base execution method. Shall be called by {@link #start}
     */
    protected final void run() throws PromiseException {
        try {
            try {
                this.run.run();
            } catch (Exception e) {
                this.catcher.accept(e);
            }
            this.result = this.run.get();
        } catch (Exception e) {
            throw new PromiseException(e);
        }
        this.then.accept(this.result);
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
