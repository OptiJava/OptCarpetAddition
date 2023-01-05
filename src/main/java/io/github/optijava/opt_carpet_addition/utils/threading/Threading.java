package io.github.optijava.opt_carpet_addition.utils.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Threading {
    public static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(1, 4, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());

}
