package efestoarts.gameoflife.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import efestoarts.gameoflife.model.Generation;
import efestoarts.gameoflife.view.WorldActivity;

import static org.mockito.Mockito.verify;

public class GameOfLifePresenterTest {

    @Mock
    public WorldActivity mockedWorldActivity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void resume() {
        GameOfLifePresenter presenter = new GameOfLifePresenter(null);
        presenter.resume(mockedWorldActivity);

        verify(mockedWorldActivity).setGeneration(new Generation(20));
    }


}
