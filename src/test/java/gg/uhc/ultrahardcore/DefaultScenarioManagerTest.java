package gg.uhc.ultrahardcore;

import gg.uhc.ultrahardcore.api.ScenarioManager;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class DefaultScenarioManagerTest
{
    private ScenarioManager manager;

    @Before
    public void onStartup()
    {
        manager = new DefaultScenarioManager();
    }

    //TODO map out specifications of the scenario manager
}
