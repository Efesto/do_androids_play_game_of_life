package efestoarts.gameoflife;

import javax.inject.Singleton;

import dagger.Component;
import efestoarts.gameoflife.view.WorldActivity;

@Singleton
@Component(modules = MockedPresenterModule.class)
public interface MockedAppComponent {
    void inject(WorldActivity test);
}
