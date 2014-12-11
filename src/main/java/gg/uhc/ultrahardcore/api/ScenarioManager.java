package gg.uhc.ultrahardcore.api;

import com.google.common.base.Optional;
import gg.uhc.ultrahardcore.api.exception.ScenarioConflictException;
import gg.uhc.ultrahardcore.api.exception.ScenarioDisableFailedException;
import gg.uhc.ultrahardcore.api.exception.ScenarioEnableFailedException;

import javax.annotation.Nonnull;
import java.util.List;

public interface ScenarioManager
{
    /**
     * Register a new scenario. All scenarios should be registered before the game enters the stage
     * {@link org.spongepowered.api.GameState#SERVER_STARTED}
     *
     * @param scenario the scenario to register
     * @throws ScenarioConflictException when scenario id conflicts with existing scenario
     */
    void registerScenario(@Nonnull Scenario scenario) throws ScenarioConflictException;

    /**
     * Fetches a scenario by it's ID if it exists
     *
     * @param id the unique id of the scenario
     * @return the given scenario if it exists
     */
    @Nonnull
    Optional<Scenario> getScenario(@Nonnull String id);

    /**
     * Enables the scenario (and any sub-scenarios it requires)
     *
     * @param id the name of the scenario to enable
     * @return a list of scenarios that were enabled startng the scenario (including the chosen one)
     * @throws ScenarioEnableFailedException when the scenario is unable to be started
     */
    @Nonnull
    List<Scenario> enableScenario(@Nonnull String id) throws ScenarioEnableFailedException;

    /**
     * Disables the scenario (and any scenarios that started up as a side effect of this one starting)
     *
     * @param id the name of the scenario to disable
     * @return a list of scenarios that were enabled startng the scenario (including the chosen one)
     * @throws ScenarioDisableFailedException when the scenario is unable to be stopped
     */
    @Nonnull
    List<Scenario> disableScenario(@Nonnull String id) throws ScenarioDisableFailedException;

    @Nonnull
    List<Scenario> getScenarios();

    boolean hasScenario(String id);
}
