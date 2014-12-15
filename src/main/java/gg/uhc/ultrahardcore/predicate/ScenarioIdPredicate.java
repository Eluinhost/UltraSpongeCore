package gg.uhc.ultrahardcore.predicate;

import com.google.common.base.Predicate;
import gg.uhc.ultrahardcore.api.Scenario;

public class ScenarioIdPredicate implements Predicate<Scenario>
{
    private String id;

    /**
     * A predicate that filters by the scenario name
     *
     * @param id the id to check for
     */
    public ScenarioIdPredicate(String id)
    {
        this.id = id;
    }

    @Override
    public boolean apply(Scenario input)
    {
        return input != null && input.getId().equals(id);
    }
}
