package com.ldbc.driver.dshini.generator;

import java.util.ArrayList;
import java.util.List;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;

public class OperationMatcher
{
    private Iterable<Matchable<RequestLogEntry>> matchables;

    public OperationMatcher()
    {
    }

    public void setMatchables( Iterable<Matchable<RequestLogEntry>> operations )
    {
        this.matchables = operations;
    }

    public Iterable<Matchable<RequestLogEntry>> getMatchables()
    {
        return matchables;
    }

    public Operation<?> getSingleMatchingOperation( RequestLogEntry entry ) throws RequestLogEntryException,
            MatchableException
    {
        for ( Matchable<RequestLogEntry> operation : matchables )
        {
            if ( operation.matches( entry ) )
            {
                return operation.createOperationFrom( entry );
            }
        }
        String errMsg = String.format( "No matching operation for entry\nEntry: %s", entry );
        throw new MatchableException( errMsg );
    }

    public List<Operation<?>> getAllMatchingOperations( RequestLogEntry entry ) throws RequestLogEntryException,
            MatchableException
    {
        List<Operation<?>> matchingOperations = new ArrayList<Operation<?>>();
        for ( Matchable<RequestLogEntry> operation : matchables )
        {
            if ( operation.matches( entry ) )
            {
                matchingOperations.add( operation.createOperationFrom( entry ) );
            }
        }
        return matchingOperations;
    }
}
