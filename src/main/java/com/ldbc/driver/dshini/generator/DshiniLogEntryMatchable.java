package com.ldbc.driver.dshini.generator;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.log.RequestLogEntry;

public interface DshiniLogEntryMatchable
{
    public boolean matches( RequestLogEntry entry ) throws DshiniLogEntryMatchableException;

    public Operation<?> createFromEntry( RequestLogEntry entry ) throws DshiniLogEntryMatchableException;
}
