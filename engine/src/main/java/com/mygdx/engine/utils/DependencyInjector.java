package com.mygdx.engine.utils;

import java.util.Map;
import java.util.HashMap;

/**
 * Low-complexity dependency injector
 * TODO: port this to GameContainer
 */
public class DependencyInjector {
    // Maps abstract classes to their concrete dependencies
    private static final Map<Class<?>, Object> dependencies = new HashMap<>();

    /**
     * @param c        The abstract representation of the class
     * @param instance Concrete implementation of the class
     */
    public static <T> void register(Class<T> c, T instance) {
        dependencies.put(c, instance);
    }

    /**
     * @param c The class to resolve for concrete dependency
     * @return The concrete implementation to return
     */
    public static <T> T resolve(Class<T> c) {
        return c.cast(dependencies.get(c));
    }
}
