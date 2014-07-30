package org.avaje.metric.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker that public methods should have timed execution statistics collected.
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Timed {

  /**
   * Replaces the method name part of full metric name. Used when the method name is not appropriate or clear perhaps 
   * due to method overloading (with different parameters).
   * <p>
   * The package and class names are still used and prepended to this name value.
   */
  String name() default "";

  /**
   * Set the full name of the metric.
   * <p>
   * Provides a complete replacement of the metric name.
   */
  String fullName() default "";

}