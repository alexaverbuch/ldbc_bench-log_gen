package com.ldbc.driver.dshini.generator;

import java.util.ArrayList;
import java.util.List;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;

public class OperationMatcher
{
    private DshiniLogEntryMatchable[] operations;

    public OperationMatcher()
    {
    }

    public void setOperations( DshiniLogEntryMatchable[] operations )
    {
        this.operations = operations;
    }

    public DshiniLogEntryMatchable[] getOperations()
    {
        return operations;
    }

    public Operation<?> getSingleMatchingOperation( RequestLogEntry entry ) throws DshiniLogEntryMatchableException,
            RequestLogEntryException
    {
        for ( DshiniLogEntryMatchable operation : operations )
        {
            if ( operation.matches( entry ) )
            {
                return operation.createFromEntry( entry );
            }
        }
        String errMsg = String.format( "No matching operation for entry\nEntry: %s", entry );
        throw new DshiniLogEntryMatchableException( errMsg );
    }

    public List<Operation<?>> getAllMatchingOperations( RequestLogEntry entry ) throws DshiniLogEntryMatchableException,
            RequestLogEntryException
    {
        List<Operation<?>> matchingOperations = new ArrayList<Operation<?>>();
        for ( DshiniLogEntryMatchable operation : operations )
        {
            if ( operation.matches( entry ) )
            {
                matchingOperations.add( operation.createFromEntry( entry ) );
            }
        }
        return matchingOperations;
    }
}
