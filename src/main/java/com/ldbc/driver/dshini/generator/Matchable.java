package com.ldbc.driver.dshini.generator;

import com.ldbc.driver.Operation;

public interface Matchable<I>
{
    public boolean matches( I entry ) throws MatchableException;

    public Operation<?> createOperationFrom( I entry ) throws MatchableException;

    public Class<? extends Operation<?>> operationType();
}
