package gg.uhc.ultrahardcore.api;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public abstract class Scenario
{
    private boolean isRunning = false;
    private final String id;
    private final String description;
    private final Set<String> dependencies;
    private final Set<String> softDependencies;

    private static final Pattern validIds = Pattern.compile("^[\\w]+$");

    /**
     * Utility constructor for scenarios without any dependencies
     *
     * @param id a unique identifier for the scenario, must contain only alphanumeric characters (no whitespace)
     * @param description a short description explaining the scenario effects
     */
    public Scenario(@Nonnull String id, @Nonnull String description)
    {
        this(id, description, null, null);
    }

    /**
     * Creates a new scenario.
     *
     * <p>
     *     Dependencies are in two separate arrays: dependencies and softDependencies. The dependencies array is a list
     *     of all scenarios that MUST be enabled when this scenario is enabled. If the other scenario is not installed
     *     or fails to start then this scenario will not start up. Soft dependencies are similar except will not stop
     *     this scenario from loading up if they are not install/failed to start
     * </p>
     *
     * @param id a unique identifier for the scenario, must contain only alphanumeric characters (no whitespace)
     * @param description a short description explaining the scenario effects
     * @param dependencies a list of scenario names that need to be enabled when this one starts, null or empty set means no dependencies
     * @param softDependencies a list of scenario names that need to be enabled when this one starts, null or empty set means no dependencies
     */
    public Scenario(@Nonnull String id, @Nonnull String description, @Nullable Set<String> dependencies, @Nullable Set<String> softDependencies)
    {
        Set<String> safeDeps = dependencies == null ? new HashSet<String>() : dependencies;
        Set<String> safeSoftDeps = softDependencies == null ? new HashSet<String>() : softDependencies;

        Preconditions.checkArgument(!Strings.isNullOrEmpty(id), "Parameter 'id' must not be null or empty");
        Preconditions.checkArgument(validIds.matcher(id).matches(), "Parameter 'id' can only contain alphanumeric characters");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(description), "Parameter 'description' must not be null or empty");

        Set<String> mutualDependencies = Sets.intersection(safeDeps, safeSoftDeps);
        Preconditions.checkArgument(mutualDependencies.size() == 0, "A scenario cannot be both a dependency and a soft dependency: " + Joiner.on(", ").join(mutualDependencies));

        Preconditions.checkArgument(!safeDeps.contains(id) && !safeSoftDeps.contains(id), "A scenario cannot contain itself in it's dependency list");

        this.id = id;
        this.description = description;
        this.dependencies = safeDeps;
        this.softDependencies = safeSoftDeps;
    }

    /**
     * Dependencies that are required to be enabled for this scenario to be active
     *
     * @return a set of required scenario names
     */
    @Nonnull
    public final Set<String> getDependencies()
    {
        return dependencies;
    }

    /**
     * Dependencies that should attempt to be enabled when this scenario is active
     *
     * @return a set of scenario names
     */
    @Nonnull
    public final Set<String> getSoftDependencies()
    {
        return softDependencies;
    }

    /**
     * @return Unique ID of the scenario
     */
    @Nonnull
    public final String getId()
    {
        return id;
    }

    /**
     * @return A short description explaining the scenario
     */
    @Nonnull
    public final String getDescription()
    {
        return description;
    }

    /**
     * @return whether the scenario is active or not
     */
    public final boolean isRunning()
    {
        return isRunning;
    }

    /**
     * Change the running status of this scenario.
     *
     * THIS METHOD SHOULD NOT BE CALLED MANUALLY. USE THE SCENARIO MANAGER INSTEAD.
     *
     * @see gg.uhc.ultrahardcore.api.ScenarioManager
     * @param running true to enable, false to disable.
     * @return true if state changed, false otherwise
     */
    public final boolean setRunning(boolean running)
    {
        //don't do anything if the status doesnt change
        if(running == isRunning) {
            return false;
        }

        isRunning = running;

        if(isRunning) {
            onEnable();
        } else {
            onDisable();
        }

        return true;
    }

    /**
     * Triggered when the scenario is enabled, override to add functionality
     */
    protected void onEnable() {}

    /**
     * Triggered when the scenario is disabled, override to add functionality
     */
    protected void onDisable() {}
}
