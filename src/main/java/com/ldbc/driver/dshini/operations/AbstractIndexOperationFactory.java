package com.ldbc.driver.dshini.operations;

import java.util.regex.Pattern;

import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.utils.UrlParsingUtils;

public abstract class AbstractIndexOperationFactory extends AbstractOperationFactory
{
    private final String expectedHttpMethod;
    private final Pattern expectedUrlPattern;
    private final String expectedIndexNameString;

    public AbstractIndexOperationFactory()
    {
        expectedHttpMethod = getExpectedHttpMethod();
        expectedUrlPattern = getExpectedUrlPattern();
        expectedIndexNameString = getExpectedIndexNameString();
    }

    protected abstract String getExpectedIndexNameString();

    protected final String getActualIndexNameString( RequestLogEntry entry ) throws MatchableException
    {
        try
        {
            return UrlParsingUtils.parseIndexNameFromNodeIndexUrl( entry.getUrl() );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( e.getCause() );
        }
    }

    protected final boolean performCustomLogic( RequestLogEntry entry ) throws MatchableException
    {
        return getExpectedIndexNameString().equals( getActualIndexNameString( entry ) );
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( expectedHttpMethod == null ) ? 0 : expectedHttpMethod.hashCode() );
        result = prime * result + ( ( expectedIndexNameString == null ) ? 0 : expectedIndexNameString.hashCode() );
        result = prime * result + ( ( expectedUrlPattern == null ) ? 0 : expectedUrlPattern.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        AbstractIndexOperationFactory other = (AbstractIndexOperationFactory) obj;
        if ( expectedHttpMethod == null )
        {
            if ( other.expectedHttpMethod != null ) return false;
        }
        else if ( !expectedHttpMethod.equals( other.expectedHttpMethod ) ) return false;
        if ( expectedIndexNameString == null )
        {
            if ( other.expectedIndexNameString != null ) return false;
        }
        else if ( !expectedIndexNameString.equals( other.expectedIndexNameString ) ) return false;
        if ( expectedUrlPattern == null )
        {
            if ( other.expectedUrlPattern != null ) return false;
        }
        else if ( !expectedUrlPattern.equals( other.expectedUrlPattern ) ) return false;
        return true;
    }
}
