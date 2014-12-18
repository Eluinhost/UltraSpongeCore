package gg.uhc.ultrahardcore.api;

import gg.uhc.ultrahardcore.api.exception.ScenarioConflictException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public interface ScenarioRegistry
{
    /**
     * Registers a scenario. The scenario MUST be configured via annotations. Scenarios MUST be registered before the
     * registry is locked (during the load complete event).
     *
     * @param scenario the class of the scenario to load
     * @return the created scenario
     */
    @Nonnull
    <T extends Scenario> T registerScenario(@Nonnull Class<T> scenario) throws ScenarioConflictException;

    /**
     * Registers a scenario by an already created instance. Any annotations will be ignored. Scenarios MUST be
     * registered before the registry is locked (during the load complete event).
     *
     * @param scenario the class of the scenario to load
     * @param id the unique ID of the scenario, must be alphanumeric
     * @param hardDependencies {@link gg.uhc.ultrahardcore.api.annotation.HardDependency}
     * @param softDependencies {@link gg.uhc.ultrahardcore.api.annotation.SoftDependency}
     */
    <T extends Scenario> void registerScenario(@Nonnull T scenario,
                                               @Nonnull String id,
                                               @Nullable Set<String> hardDependencies,
                                               @Nullable Set<String> softDependencies) throws ScenarioConflictException;

    /**
     * @return whether the registry is locked or not
     */
    boolean isLocked();

    /**
     * NOTE: when called before the registry is locked this method can return scenarios with invalid dependency and
     * can be missing scenarios. Always call this method after the registry is locked.
     *
     * @return list of all currently registed scenarios.
     */
    Set<Scenario> getScenarios();
}
