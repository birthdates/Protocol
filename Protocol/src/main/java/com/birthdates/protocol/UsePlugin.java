package com.birthdates.protocol;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface UsePlugin {
    double weight() default 0D;

    String name() default "Unknown";
}
