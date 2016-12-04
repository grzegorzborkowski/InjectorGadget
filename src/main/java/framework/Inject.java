package framework;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * http://stackoverflow.com/questions/4296910/is-it-possible-to-read-the-value-of-a-annotation-in-java
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
    String bindingName() default "";
}
