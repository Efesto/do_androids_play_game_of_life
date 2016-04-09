package efestoarts.gameoflife;

import dagger.Module;
import dagger.Provides;
import efestoarts.gameoflife.presenter.GameOfLifePresenter;

import static org.mockito.Mockito.mock;

@Module
public class MockedPresenterModule {

    @Provides
    GameOfLifePresenter providesGameOfLifePresenter() {
        return mock(GameOfLifePresenter.class);
    }
}
