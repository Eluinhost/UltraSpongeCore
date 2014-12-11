package gg.uhc.ultrahardcore;

import com.google.inject.Inject;
import com.google.inject.Injector;
import gg.uhc.ultrahardcore.api.ScenarioManager;
import gg.uhc.ultrahardcore.api.exception.ScenarioEnableFailedException;
import org.slf4j.Logger;
import org.spongepowered.api.event.state.InitializationEvent;
import org.spongepowered.api.event.state.LoadCompleteEvent;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.config.DefaultConfig;
import org.spongepowered.api.util.config.ConfigFile;
import org.spongepowered.api.util.event.Subscribe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

//version is empty, we use version.properties
@Plugin(id = "gg.uhc.ultrahardcore", name = "UltraHardcore", version = "")
public class UltraHardcore
{

    private ScenarioManager scenarioManager;
    private ConfigFile config;
    private Logger logger;

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
        // get a list of all the default enabled scenarios and enable all the valid ones we can
        List<String> enabled = config.getStringList("enabled at start");

        for (String current : enabled) {
            if (current == null || scenarioManager.hasScenario(current)) {
                logger.warn("Scenario {} was flagged to be enabled on start, but was not found, skipping enable.", current);
                continue;
            }

            try {
                scenarioManager.enableScenario(current);
            } catch (ScenarioEnableFailedException e) {
                logger.error("Exception thrown while enabling scenario: " + current, e);
            }
        }
    }

    @Inject
    protected void setLogger(Logger logger)
    {
        this.logger = logger;
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
