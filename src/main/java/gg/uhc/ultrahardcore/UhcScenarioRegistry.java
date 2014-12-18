package gg.uhc.ultrahardcore;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import gg.uhc.ultrahardcore.api.Scenario;
import gg.uhc.ultrahardcore.api.ScenarioRegistry;
import gg.uhc.ultrahardcore.api.exception.ScenarioConflictException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class UhcScenarioRegistry implements ScenarioRegistry
{
    boolean locked = false;

    /**
     * Builds the final list of scenarios and locks the registry.
     *
     * @return scenarios that failed to load
     */
    protected Set<Scenario> finalizeScenarios()
    {
        // lock the registry to avoid adding of any more scenarios
        locked = true;

        //TODO validate dependencies and finalize scenario list
        return Sets.newHashSet();
    }

    @Nonnull
    @Override
    public <T extends Scenario> T registerScenario(@Nonnull Class<T> scenario) throws ScenarioConflictException
    {
        // make sure we're not locked
        Preconditions.checkState(!locked);

        //TODO setup scenario requirements and add to registry ready for finalization
        return null;
    }

    @Override
    public <T extends Scenario> void registerScenario(@Nonnull T scenario, @Nonnull String id, @Nullable Set<String>
            hardDependencies, @Nullable Set<String> softDependencies) throws ScenarioConflictException
    {
        // make sure we're not locked
        Preconditions.checkState(!locked);

        //TODO setup scenario requirements and add to registry ready for finalization
    }

    @Override
    public boolean isLocked()
    {
        return locked;
    }

    @Override
    public Set<Scenario> getScenarios()
    {
        //TODO
        return null;
    }
}
