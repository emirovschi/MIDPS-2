package com.emirovschi.midps2.calc.matcher;

import java.util.function.Consumer;

public class Matcher<T, R extends T>
{
    private final T object;
    private final Class<R> objectClass;
    private final Consumer<R> consumer;
    private final Matcher<T, ? extends T> parent;

    private Matcher(final T object, final Class<R> objectClass, final Consumer<R> consumer, final Matcher<T, ? extends T> parent)
    {
        this.object = object;
        this.objectClass = objectClass;
        this.consumer = consumer;
        this.parent = parent;
    }

    public <M extends T> MatcherPredicate<T, M> when(final Class<M> objectClass)
    {
        return new MatcherPredicate<>(object, objectClass, this);
    }

    public void match()
    {
        tryMatch();
    }

    private boolean tryMatch()
    {
        return (parent != null && parent.tryMatch()) || doMatch();
    }

    private boolean doMatch()
    {
        final boolean match = object.getClass().equals(objectClass);

        if (match)
        {
            consumer.accept((R) object);
        }

        return match;
    }

    public static <T> MatcherBuilder<T> of(final T object)
    {
        return new MatcherBuilder<>(object, null);
    }

    public static class MatcherBuilder<T>
    {
        private final T object;
        private final Matcher<T, ? extends T> parent;

        private MatcherBuilder(final T object, final Matcher<T, ? extends T> parent)
        {
            this.object = object;
            this.parent = parent;
        }

        public <R extends T> MatcherPredicate<T, R> when(final Class<R> objectClass)
        {
            return new MatcherPredicate<>(object, objectClass, parent);
        }
    }

    public static class MatcherPredicate<T, R extends T>
    {
        private final T object;
        private final Class<R> objectClass;
        private final Matcher<T, ? extends T> parent;

        private MatcherPredicate(final T object, final Class<R> objectClass, final Matcher<T, ? extends T> parent)
        {
            this.object = object;
            this.objectClass = objectClass;
            this.parent = parent;
        }

        public Matcher<T, R> then(final Consumer<R> consumer)
        {
            return new Matcher<>(object, objectClass, consumer, parent);
        }
    }
}
