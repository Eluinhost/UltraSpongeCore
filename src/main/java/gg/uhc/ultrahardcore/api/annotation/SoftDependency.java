package gg.uhc.ultrahardcore.api.annotation;

import java.lang.annotation.*;

/**
 * A soft dependency is a scenario that will be enabled if it is installed when the scenario this annotation is applied
 * to is enabled. This scenario will load regardless of whether the dependency is install or not
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Inherited
public @interface SoftDependency
{
    /**
     * @return array of IDs this scenario has a soft dependency on.
     */
    String[] value();
}
