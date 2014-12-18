package gg.uhc.ultrahardcore.api.annotation;

import java.lang.annotation.*;

/**
 * A hard dependency is a scenario that will be enabled when the scenario this annotation is applied
 * to is enabled. This scenario will fail to load on startup if the dependency is not installed
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Inherited
public @interface HardDependency
{
    /**
     * @return array of IDs this scenario has a hard dependency on
     */
    String[] value();
}
