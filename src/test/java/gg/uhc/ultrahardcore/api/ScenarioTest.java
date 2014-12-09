package gg.uhc.ultrahardcore.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.spy;

@RunWith(PowerMockRunner.class)
public class ScenarioTest
{
    private Scenario scenario;

    @Before
    public void onStartup()
    {
        scenario = spy(new Scenario()
        {
            @Override
            public String getId()
            {
                return "TestScenario";
            }

            @Override
            public String getDescription()
            {
                return "This text doesn't matter, blah blah blah";
            }
        });
    }

    @Test
    public void test_scenario_starts_disabled()
    {
        assertThat(scenario.isRunning()).isFalse();
    }

    @Test
    public void test_scenario_enable_disable_and_callbacks()
    {
        assertThat(scenario.isRunning()).isFalse();
        verify(scenario, never()).onDisable();
        verify(scenario, never()).onEnable();

        scenario.setRunning(true);

        assertThat(scenario.isRunning()).isTrue();
        verify(scenario, never()).onDisable();
        verify(scenario, times(1)).onEnable();

        scenario.setRunning(false);

        assertThat(scenario.isRunning()).isFalse();
        verify(scenario, times(1)).onDisable();
        verify(scenario, times(1)).onEnable();
    }
}
