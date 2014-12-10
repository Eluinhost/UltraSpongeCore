package gg.uhc.ultrahardcore;

import com.google.inject.AbstractModule;
import gg.uhc.ultrahardcore.api.ScenarioManager;

class DefaultModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(ScenarioManager.class).to(DefaultScenarioManager.class).asEagerSingleton();
    }
}
