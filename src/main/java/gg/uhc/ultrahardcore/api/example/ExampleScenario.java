package gg.uhc.ultrahardcore.api.example;

import gg.uhc.ultrahardcore.api.Scenario;
import gg.uhc.ultrahardcore.api.annotation.HardDependency;
import gg.uhc.ultrahardcore.api.annotation.ScenarioId;
import gg.uhc.ultrahardcore.api.annotation.SoftDependency;

@HardDependency({"scenario1", "scenario2"})
@SoftDependency("scenario3")
@ScenarioId("example")
public class ExampleScenario extends Scenario
{
    //TODO fill out an example scenario after Scenario refactor
}
