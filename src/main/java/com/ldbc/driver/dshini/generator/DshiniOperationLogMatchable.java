package com.ldbc.driver.dshini.generator;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.log.OperationLogEntry;

public interface DshiniOperationLogMatchable
{
    public boolean matches( OperationLogEntry entry ) throws DshiniOperationLogMatchableException;

    public Operation<?> createFromEntry( OperationLogEntry entry ) throws DshiniOperationLogMatchableException;

    public String toOperationLogEntry( OperationLogEntry entry ) throws DshiniOperationLogMatchableException;
}
