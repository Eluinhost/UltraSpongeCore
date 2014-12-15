package gg.uhc.ultrahardcore;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import gg.uhc.ultrahardcore.api.Scenario;
import gg.uhc.ultrahardcore.api.ScenarioManager;
import gg.uhc.ultrahardcore.api.exception.ScenarioConflictException;
import gg.uhc.ultrahardcore.api.exception.ScenarioDisableFailedException;
import gg.uhc.ultrahardcore.api.exception.ScenarioEnableFailedException;
import gg.uhc.ultrahardcore.predicate.ScenarioIdPredicate;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

class DefaultScenarioManager implements ScenarioManager
{
    private final List<Scenario> scenarios = Lists.newArrayList();

    @Override
    public void registerScenario(@Nonnull Scenario scenario) throws ScenarioConflictException
    {
        String scenarioId = scenario.getId();

        if(hasScenario(scenarioId)) {
            throw new ScenarioConflictException("Scenario ID '" + scenarioId + "' is already in use");
        }

        scenarios.add(scenario);
    }

    @Nonnull
    @Override
    public Optional<Scenario> getScenario(@Nonnull String id)
    {
        return Iterables.tryFind(scenarios, new ScenarioIdPredicate(id));
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
        return Collections.unmodifiableList(scenarios);
    }

    @Override
    public boolean hasScenario(String id)
    {
        return Iterables.indexOf(scenarios, new ScenarioIdPredicate(id)) != -1;
    }

    @Override
    public boolean isEnabled(String id)
    {
        Optional<Scenario> scenario = getScenario(id);
        Preconditions.checkArgument(scenario.isPresent());

        return scenario.get().isRunning();
    }
}
