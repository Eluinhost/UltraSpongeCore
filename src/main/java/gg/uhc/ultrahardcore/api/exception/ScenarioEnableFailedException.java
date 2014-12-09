package gg.uhc.ultrahardcore.api.exception;

public class ScenarioEnableFailedException extends Exception
{
    public ScenarioEnableFailedException(String message)
    {
        super(message);
    }

    public ScenarioEnableFailedException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
