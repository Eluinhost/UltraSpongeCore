package gg.uhc.ultrahardcore.api;

public abstract class Scenario
{
    private boolean isRunning;

    /**
     * @return Unique ID of the scenario
     */
    public abstract String getId();

    /**
     * @return A short description explaining the scenario
     */
    public abstract String getDescription();

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

    protected void onEnable() {}

    protected void onDisable() {}
}
