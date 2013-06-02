package com.ldbc.driver.dshini.operations;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogEntry;
import com.ldbc.driver.dshini.generator.RequestLogEntryException;

public interface MatchableOperationCreator
{
    public boolean matches( RequestLogEntry entry ) throws MatchableException;

    public Operation<?> createFromEntry( RequestLogEntry entry ) throws RequestLogEntryException;
}
