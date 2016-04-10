package efestoarts.gameoflife.presenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import efestoarts.gameoflife.ThreadSchedulers;
import efestoarts.gameoflife.model.Generation;
import efestoarts.gameoflife.model.Life;
import efestoarts.gameoflife.view.WorldActivity;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

public class GameOfLifePresenter {

    public static final int SIMULATION_REFRESH_TIME = 100;
    private WorldActivity activity;
    private Life life;
    private ThreadSchedulers schedulers;
    private Observable<Generation> lifeObservable;
    private Subscription subscription;
    private Action1<Generation> setGenerationOnActivity;

    @Inject
    public GameOfLifePresenter(Life life, ThreadSchedulers schedulers) {
        this.life = life;
        this.schedulers = schedulers;

        setGenerationOnActivity = new Action1<Generation>() {
            @Override
            public void call(Generation generation) {
                activity.setGeneration(generation);
            }
        };

        lifeObservable = Observable.interval(SIMULATION_REFRESH_TIME, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Generation>() {
                    @Override
                    public Generation call(Long aLong) {
                        return GameOfLifePresenter.this.life.nextGeneration();
                    }
                }).subscribeOn(schedulers.getComputationalScheduler())
                .observeOn(schedulers.getUiScheduler());
    }

    public void resume(WorldActivity activity) {
        this.activity = activity;
    }

    public void startStopSimulation() {
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
        else {
            subscription = lifeObservable.subscribe(setGenerationOnActivity);
        }
    }

    public void onGlobalLayout() {
        Observable.just(life.nextGeneration())
                .subscribeOn(schedulers.getComputationalScheduler())
                .observeOn(schedulers.getUiScheduler())
                .subscribe(setGenerationOnActivity);
    }
}
