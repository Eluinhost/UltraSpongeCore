package gg.uhc.ultrahardcore;

import com.google.inject.Inject;
import com.google.inject.Injector;
import gg.uhc.ultrahardcore.api.ScenarioManager;
import org.spongepowered.api.event.state.InitializationEvent;
import org.spongepowered.api.event.state.LoadCompleteEvent;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.config.DefaultConfig;
import org.spongepowered.api.util.config.ConfigFile;
import org.spongepowered.api.util.event.Subscribe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

//version is empty, we use version.properties
@Plugin(id = "gg.uhc.ultrahardcore", name = "UltraHardcore", version = "")
public class UltraHardcore
{

    private ScenarioManager scenarioManager;
    private ConfigFile config;

    @Subscribe
    public void onPreInit(PreInitializationEvent event)
    {
        //TODO load and save defaults from JAR if config file doesn't exist
    }

    @Subscribe
    public void onInit(InitializationEvent event)
    {
        //TODO register our commands/events e.t.c.
    }

    @Subscribe
    public void onLoadComplete(LoadCompleteEvent event)
    {
        //TODO enable relevant scenarios where possible
    }

    @Inject
    protected void setDefaultConfig(@DefaultConfig(sharedRoot = false) ConfigFile file)
    {
        config = file;
    }

    @Inject
    protected void setScenarioManager(@Nonnull ScenarioManager manager)
    {
        scenarioManager = manager;
    }

    @Inject
    protected void setParentInjector(Injector injector)
    {
        Injector pluginInjector = injector.createChildInjector(new DefaultModule());

        scenarioManager = pluginInjector.getInstance(ScenarioManager.class);
    }

    /**
     * This method may return null if called before PreInitializationEvent is completed
     *
     * @return the scenario manager if initialized
     */
    @Nullable
    public ScenarioManager getScenarioManager()
    {
        return scenarioManager;
    }
}
