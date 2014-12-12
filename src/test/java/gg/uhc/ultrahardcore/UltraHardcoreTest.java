package gg.uhc.ultrahardcore;

import com.google.common.collect.Lists;
import gg.uhc.ultrahardcore.api.ScenarioManager;
import gg.uhc.ultrahardcore.api.exception.ScenarioEnableFailedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.spongepowered.api.event.state.LoadCompleteEvent;
import org.spongepowered.api.util.config.ConfigFile;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.verifyNoMoreInteractions;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConfigFile.class)
public class UltraHardcoreTest
{

    @Test
    public void test_skips_invalid_scenarios() throws ScenarioEnableFailedException
    {
        ScenarioManager manager = mock(ScenarioManager.class);

        when(manager.hasScenario(null)).thenReturn(false);
        when(manager.hasScenario("missing")).thenReturn(false);
        when(manager.hasScenario("existing")).thenReturn(true);
        when(manager.hasScenario("duplicated")).thenReturn(true);

        List<String> toEnable = Lists.newArrayList();

        toEnable.add(null);
        toEnable.add("missing");
        toEnable.add("existing");
        toEnable.add("duplicated");
        toEnable.add("duplicated");

        ConfigFile config = mock(ConfigFile.class);
        when(config.getStringList("enabled at start")).thenReturn(toEnable);

        Logger logger = mock(Logger.class);

        UltraHardcore uhc = new UltraHardcore();
        uhc.setDefaultConfig(config);
        uhc.setScenarioManager(manager);
        uhc.setLogger(logger);

        // actual test method
        uhc.onLoadComplete(mock(LoadCompleteEvent.class));

        verify(manager, never()).hasScenario(null);
        verify(manager, times(1)).hasScenario("missing");
        verify(manager, times(1)).hasScenario("existing");
        verify(manager, times(2)).hasScenario("duplicated");

        verify(manager, times(1)).enableScenario("existing");
        verify(manager, times(2)).enableScenario("duplicated");

        verifyNoMoreInteractions(manager);

        verify(logger, times(1)).warn(contains("null scenario"));
        verify(logger, times(1)).info(contains("Scenario {} was flagged"), eq("missing"));
        verifyNoMoreInteractions(logger);
    }
}
