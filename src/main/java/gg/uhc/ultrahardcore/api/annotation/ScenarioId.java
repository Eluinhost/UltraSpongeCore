package gg.uhc.ultrahardcore.api.annotation;

public @interface ScenarioId
{
    /**
     * NOTE: All scenario IDs must be alphanumeric without whitespace
     *
     * @return the unique ID of the scenario used as an identifier
     */
    String value();
}
