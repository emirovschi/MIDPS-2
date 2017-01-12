package com.emirovschi.midps2.calc.matcher;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatcherTest
{
    @Test
    public void shouldMatchB()
    {
        final A object = new B();

        runMatcher(object);

        assertEquals(B.class, object.getObjectClass());
    }

    @Test
    public void shouldMatchC()
    {
        final A object = new C();

        runMatcher(object);

        assertEquals(C.class, object.getObjectClass());
    }

    @Test
    public void shouldMatchD()
    {
        final A object = new D();

        runMatcher(object);

        assertEquals(D.class, object.getObjectClass());
    }

    @Test
    public void shouldMatchNone()
    {
        final A object = new E();

        runMatcher(object);

        assertEquals(null, object.getObjectClass());
    }

    private void runMatcher(final A object)
    {
        Matcher.of(object)
                .when(B.class).then(o -> o.setObjectClass(B.class))
                .when(C.class).then(o -> o.setObjectClass(C.class))
                .when(D.class).then(o -> o.setObjectClass(D.class))
                .match();
    }

    private abstract class A
    {
        private Class<? extends A> objectClass;

        public void setObjectClass(final Class<? extends A> objectClass)
        {
            this.objectClass = objectClass;
        }

        public Class<? extends A> getObjectClass()
        {
            return objectClass;
        }
    }

    private class B extends A {}

    private class C extends A {}

    private class D extends B {}

    private class E extends A {}
}