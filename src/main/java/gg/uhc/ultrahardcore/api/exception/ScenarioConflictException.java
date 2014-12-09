package gg.uhc.ultrahardcore.api.exception;

public class ScenarioConflictException extends Exception
{
    public ScenarioConflictException(String message)
    {
        super(message);
    }

    public ScenarioConflictException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
