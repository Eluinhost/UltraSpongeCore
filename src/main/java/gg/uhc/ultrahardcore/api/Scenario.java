package gg.uhc.ultrahardcore.api;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.util.regex.Pattern;

public abstract class Scenario
{
    private boolean isRunning = false;
    private final String id;
    private final String description;

    private static final Pattern validIds = Pattern.compile("^[\\w]+$");

    /**
     * @param id a unique identifier for the scenario, must contain only alphanumeric characters (no whitespace)
     * @param description a short description explaining the scenario effects
     */
    public Scenario(String id, String description)
    {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(id), "Parameter 'id' must not be null or empty");
        Preconditions.checkArgument(validIds.matcher(id).matches(), "Parameter 'id' can only contain alphanumeric characters");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(description), "Parameter 'description' must not be null or empty");

        this.id = id;
        this.description = description;
    }

    /**
     * @return Unique ID of the scenario
     */
    public final String getId()
    {
        return id;
    }

    /**
     * @return A short description explaining the scenario
     */
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

    public final void setRunning(boolean running)
    {
        //don't do anything if the status doesnt change
        if(running == isRunning) {
            return;
        }

        isRunning = running;

        if(isRunning) {
            onEnable();
        } else {
            onDisable();
        }
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
