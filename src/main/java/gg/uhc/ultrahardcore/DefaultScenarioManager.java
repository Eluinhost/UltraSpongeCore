package gg.uhc.ultrahardcore;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import gg.uhc.ultrahardcore.api.Scenario;
import gg.uhc.ultrahardcore.api.ScenarioManager;
import gg.uhc.ultrahardcore.api.exception.ScenarioConflictException;
import gg.uhc.ultrahardcore.api.exception.ScenarioDisableFailedException;
import gg.uhc.ultrahardcore.api.exception.ScenarioEnableFailedException;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;

class DefaultScenarioManager implements ScenarioManager
{
    /**
     * Map of scenario name -> Scenario
     */
    private final HashMap<String, Scenario> scenarios = Maps.newHashMap();

    @Override
    public void registerScenario(@Nonnull Scenario scenario) throws ScenarioConflictException
    {
        String scenarioId = scenario.getId();

        if(scenarios.containsKey(scenarioId)) {
            throw new ScenarioConflictException("Scenario ID '" + scenarioId + "' is already in use");
        }

        scenarios.put(scenarioId, scenario);
    }

    @Nonnull
    @Override
    public Optional<Scenario> getScenario(@Nonnull String id)
    {
        return Optional.fromNullable(scenarios.get(id));
    }

    @Nonnull
    @Override
    public List<Scenario> enableScenario(@Nonnull String id) throws ScenarioEnableFailedException
    {
        //TODO dependency management/checking
        return Lists.newArrayList();
    }

    @Nonnull
    @Override
    public List<Scenario> disableScenario(@Nonnull String id) throws ScenarioDisableFailedException
    {
        //TODO dependency management/checking
        return Lists.newArrayList();
    }

    @Nonnull
    @Override
    public List<Scenario> getScenarios()
    {
        return Lists.newArrayList(scenarios.values());
    }

    @Override
    public boolean hasScenario(String id)
    {
        return scenarios.containsKey(id);
    }

    @Override
    public boolean isEnabled(String id)
    {
        Preconditions.checkArgument(hasScenario(id));

        return scenarios.get(id).isRunning();
    }
}
