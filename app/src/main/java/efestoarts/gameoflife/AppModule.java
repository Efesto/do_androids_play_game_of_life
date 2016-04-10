package efestoarts.gameoflife;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import efestoarts.gameoflife.model.Generation;
import efestoarts.gameoflife.model.Life;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class AppModule {
    private static final int DEFAULT_GRID_SIZE = 20;

    @Provides @Singleton Life providesLife()
    {
        return new Life(new Generation(DEFAULT_GRID_SIZE));
    }

    @Provides @Singleton ThreadSchedulers providesThreadSchedulers()
    {
        return new ThreadSchedulers(Schedulers.computation(), AndroidSchedulers.mainThread());
    }
}
