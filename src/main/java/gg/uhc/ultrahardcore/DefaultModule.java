package gg.uhc.ultrahardcore;

import com.google.common.base.Preconditions;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import gg.uhc.ultrahardcore.api.ScenarioManager;
import org.spongepowered.api.Game;

class DefaultModule extends AbstractModule
{
    private final Game game;

    @Provides
    @Singleton
    Game getGame()
    {
        return game;
    }

    public DefaultModule(Game game)
    {
        Preconditions.checkNotNull(game);

        this.game = game;
    }

    @Override
    protected void configure()
    {
        bind(ScenarioManager.class).to(DefaultScenarioManager.class).asEagerSingleton();
    }
}
