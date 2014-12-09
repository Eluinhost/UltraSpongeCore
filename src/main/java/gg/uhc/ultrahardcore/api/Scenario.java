package gg.uhc.ultrahardcore.api;

public abstract class Scenario
{
    private boolean isRunning = false;
    private String id;
    private String description;

    /**
     * @param id a unique identifier for the scenario
     * @param description a short description explaining the scenario effects
     */
    public Scenario(String id, String description)
    {
        this.id = id;
        this.description = description;
    }

    /**
     * @return Unique ID of the scenario
     */
    public String getId()
    {
        return id;
    }

    /**
     * @return A short description explaining the scenario
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return whether the scenario is active or not
     */
    public boolean isRunning()
    {
        return isRunning;
    }

    public void setRunning(boolean running)
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
