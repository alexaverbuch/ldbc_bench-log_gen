package com.ldbc.driver.dshini.operations;

import java.util.ArrayList;
import java.util.List;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogEntry;
import com.ldbc.driver.dshini.generator.RequestLogEntryException;

public class OperationMatcher
{
    private MatchableOperationCreator[] operations;

    public OperationMatcher()
    {
    }

    public void setOperations( MatchableOperationCreator[] operations )
    {
        this.operations = operations;
    }

    public MatchableOperationCreator[] getOperations()
    {
        return operations;
    }

    public Operation<?> getSingleMatchingOperation( RequestLogEntry entry ) throws MatchableException,
            RequestLogEntryException
    {
        for ( MatchableOperationCreator operation : operations )
        {
            if ( operation.matches( entry ) )
            {
                return operation.createFromEntry( entry );
            }
        }
        String errMsg = String.format( "No matching operation for entry\nEntry: %s", entry );
        throw new MatchableException( errMsg );
    }

    public List<Operation<?>> getAllMatchingOperations( RequestLogEntry entry ) throws MatchableException,
            RequestLogEntryException
    {
        List<Operation<?>> matchingOperations = new ArrayList<Operation<?>>();
        for ( MatchableOperationCreator operation : operations )
        {
            if ( operation.matches( entry ) )
            {
                matchingOperations.add( operation.createFromEntry( entry ) );
            }
        }
        return matchingOperations;
    }
}
