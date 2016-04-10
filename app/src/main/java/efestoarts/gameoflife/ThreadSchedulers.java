package efestoarts.gameoflife;

import rx.Scheduler;

public class ThreadSchedulers {
    private final Scheduler computationalScheduler;
    private final Scheduler uiScheduler;

    public ThreadSchedulers(Scheduler computationalScheduler, Scheduler uiScheduler)
    {
        this.computationalScheduler = computationalScheduler;
        this.uiScheduler = uiScheduler;
    }

    public Scheduler getUiScheduler() {
        return uiScheduler;
    }

    public Scheduler getComputationalScheduler() {
        return computationalScheduler;
    }
}
