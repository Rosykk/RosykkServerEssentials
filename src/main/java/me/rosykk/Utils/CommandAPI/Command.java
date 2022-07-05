package me.rosykk.Utils.CommandAPI;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    public String name();

    public String permission() default "";

    public String[] aliases() default {};

    public String description() default "";

    public String usage() default "";

    public boolean inGameOnly() default true;

    public boolean isAdminOnly() default false;

    public boolean isConsoleOnly() default false;
}