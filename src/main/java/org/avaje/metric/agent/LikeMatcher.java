package org.avaje.metric.agent;

import java.util.regex.Pattern;

/**
 * String matching using "*" as wildcards.
 */
class LikeMatcher {

  private final boolean allMatch;

  private final Pattern pattern;

  /**
   * Create with an expression that can contain "*" characters as wildcards.
   * <p>
   *   Note that matches are case insensitive.
   * </p>
   * <p>
   *   If the expression is null or whitespace this will match all non-null values.
   * </p>
   *
   * <pre>{@code
   *
   *   // starts with web.
   *   LikeMatcher match = new LikeMatcher("web.*");
   *
   *   // end with resource.
   *   LikeMatcher match = new LikeMatcher("*resource");
   *
   *   // starts with web. and contains customer
   *   LikeMatcher match = new LikeMatcher("web.*customer*");
   *
   *   // starts with web. and contains customer and ends with resource
   *   LikeMatcher match = new LikeMatcher("web.*customer*resource");
   *
   * }</pre>
   */
  LikeMatcher(String expr) {

    allMatch = (expr == null || expr.trim().length() == 0);

    if (allMatch) {
      // skip using pattern in this case
      this.pattern = Pattern.compile(".*");

    } else {
      expr = expr.toLowerCase().trim();
      expr = expr.replace(".", "\\.");
      expr = expr.replace("*", ".*");

      this.pattern = Pattern.compile(expr);
    }
  }

  /**
   * Return true if the values matches the pattern.
   */
  boolean matches(String value) {

    return value != null && (allMatch || pattern.matcher(value.toLowerCase()).matches());
  }
}
