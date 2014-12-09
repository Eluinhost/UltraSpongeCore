package gg.uhc.ultrahardcore.api.exception;

public class ScenarioDisableFailedException extends Exception
{
    public ScenarioDisableFailedException(String message)
    {
        super(message);
    }

    public ScenarioDisableFailedException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
