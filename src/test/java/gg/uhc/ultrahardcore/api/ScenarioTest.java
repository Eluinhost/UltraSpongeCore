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
        scenario = spy(new Scenario("TestScenario", "This text doesn't matter, blah blah blah"){});
    }

    @Test
    public void test_scenario_initial_data()
    {
        assertThat(scenario.isRunning()).isFalse();
        assertThat(scenario.getId()).isEqualTo("TestScenario");
        assertThat(scenario.getDescription()).isEqualTo("This text doesn't matter, blah blah blah");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_empty_id()
    {
        new Scenario("", "Test Description"){};
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_null_id()
    {
        new Scenario(null, "Test Description"){};
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_invalid_format_id()
    {
        new Scenario("This ID has spaces in it", "Test Description"){};
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_empty_description()
    {
        new Scenario("TestScenario", ""){};
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_null_description()
    {
        new Scenario("TestScenario", null){};
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
