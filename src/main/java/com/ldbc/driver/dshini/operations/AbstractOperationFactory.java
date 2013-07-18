package com.ldbc.driver.dshini.operations;

import java.util.regex.Pattern;

import com.ldbc.driver.dshini.generator.Matchable;
import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;

public abstract class AbstractOperationFactory implements Matchable<RequestLogEntry>
{
    private final String expectedHttpMethod;
    private final Pattern expectedUrlPattern;

    public AbstractOperationFactory()
    {
        expectedHttpMethod = getExpectedHttpMethod();
        expectedUrlPattern = getExpectedUrlPattern();
    }

    protected abstract String getExpectedHttpMethod();

    protected abstract Pattern getExpectedUrlPattern();

    protected boolean performCustomLogic( RequestLogEntry entry ) throws MatchableException
    {
        return true;
    }

    @Override
    public final boolean matches( RequestLogEntry entry ) throws MatchableException
    {
        if ( false == getExpectedHttpMethod().equals( entry.getHttpMethod() ) ) return false;

        if ( false == getExpectedUrlPattern().matcher( entry.getUrl() ).matches() ) return false;

        return performCustomLogic( entry );
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( expectedHttpMethod == null ) ? 0 : expectedHttpMethod.hashCode() );
        result = prime * result + ( ( expectedUrlPattern == null ) ? 0 : expectedUrlPattern.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        AbstractOperationFactory other = (AbstractOperationFactory) obj;
        if ( expectedHttpMethod == null )
        {
            if ( other.expectedHttpMethod != null ) return false;
        }
        else if ( !expectedHttpMethod.equals( other.expectedHttpMethod ) ) return false;
        if ( expectedUrlPattern == null )
        {
            if ( other.expectedUrlPattern != null ) return false;
        }
        else if ( !expectedUrlPattern.equals( other.expectedUrlPattern ) ) return false;
        return true;
    }

}
