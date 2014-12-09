package gg.uhc.ultrahardcore.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.spy;

@RunWith(PowerMockRunner.class)
public class ScenarioTest
{
    @Test
    public void test_scenario_initial_data()
    {
        Scenario scenario = spy(new Scenario("TestScenario", "This text doesn't matter, blah blah blah"){});

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

    @Test(expected = IllegalArgumentException.class)
    public void test_mixed_dependency_type()
    {
        Set<String> hard = new HashSet<String>();
        hard.add("TestScenario2");

        Set<String> soft = new HashSet<String>();
        soft.add("TestScenario2");

        new Scenario("TestScenario", "Test Description", hard, soft){};
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_self_dependency()
    {
        Set<String> deps = new HashSet<String>();
        deps.add("TestScenario");

        new Scenario("TestScenario", "Test Description", deps, new HashSet<String>()){};
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_self_dependency_soft()
    {
        Set<String> deps = new HashSet<String>();
        deps.add("TestScenario");

        new Scenario("TestScenario", "Test Description", new HashSet<String>(), deps){};
    }

    @Test
    public void test_empty_dependencies_when_null()
    {
        Scenario sc = new Scenario("id", "description"){};

        assertThat(sc.getDependencies()).isEmpty();
        assertThat(sc.getSoftDependencies()).isEmpty();

        sc = new Scenario("id", "description", null, null){};

        assertThat(sc.getDependencies()).isEmpty();
        assertThat(sc.getSoftDependencies()).isEmpty();
    }

    @Test
    public void test_scenario_enable_disable_and_callbacks()
    {
        Scenario scenario = spy(new Scenario("TestScenario", "This text doesn't matter, blah blah blah"){});

        assertThat(scenario.isRunning()).isFalse();
        verify(scenario, never()).onDisable();
        verify(scenario, never()).onEnable();

        boolean switched = scenario.setRunning(true);

        assertThat(switched).isTrue();
        assertThat(scenario.isRunning()).isTrue();
        verify(scenario, never()).onDisable();
        verify(scenario, times(1)).onEnable();

        switched = scenario.setRunning(false);

        assertThat(switched).isTrue();
        assertThat(scenario.isRunning()).isFalse();
        verify(scenario, times(1)).onDisable();
        verify(scenario, times(1)).onEnable();

        switched = scenario.setRunning(false);

        assertThat(switched).isFalse();
        assertThat(scenario.isRunning()).isFalse();
        verify(scenario, times(1)).onDisable();
        verify(scenario, times(1)).onEnable();
    }
}
