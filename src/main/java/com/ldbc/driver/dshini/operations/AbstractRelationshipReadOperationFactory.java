package com.ldbc.driver.dshini.operations;

import java.util.regex.Pattern;

import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.utils.UrlParsingUtils;

public abstract class AbstractRelationshipReadOperationFactory extends AbstractOperationFactory
{
    private final String expectedHttpMethod;
    private final Pattern expectedUrlPattern;
    private final String expectedType;

    public AbstractRelationshipReadOperationFactory()
    {
        expectedHttpMethod = getExpectedHttpMethod();
        expectedUrlPattern = getExpectedUrlPattern();
        expectedType = getExpectedType();
    }

    protected abstract String getExpectedType();

    protected final String getActualType( RequestLogEntry entry ) throws MatchableException
    {
        try
        {
            return UrlParsingUtils.parseRelationshipTypeFromNodeRelationshipsUrl( entry.getUrl() );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( e.getCause() );
        }
    }

    @Override
    protected boolean performCustomLogic( RequestLogEntry entry ) throws MatchableException
    {
        return getExpectedType().equals( getActualType( entry ) );
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( expectedHttpMethod == null ) ? 0 : expectedHttpMethod.hashCode() );
        result = prime * result + ( ( expectedType == null ) ? 0 : expectedType.hashCode() );
        result = prime * result + ( ( expectedUrlPattern == null ) ? 0 : expectedUrlPattern.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj ) return true;
        if ( !super.equals( obj ) ) return false;
        if ( getClass() != obj.getClass() ) return false;
        AbstractRelationshipReadOperationFactory other = (AbstractRelationshipReadOperationFactory) obj;
        if ( expectedHttpMethod == null )
        {
            if ( other.expectedHttpMethod != null ) return false;
        }
        else if ( !expectedHttpMethod.equals( other.expectedHttpMethod ) ) return false;
        if ( expectedType == null )
        {
            if ( other.expectedType != null ) return false;
        }
        else if ( !expectedType.equals( other.expectedType ) ) return false;
        if ( expectedUrlPattern == null )
        {
            if ( other.expectedUrlPattern != null ) return false;
        }
        else if ( !expectedUrlPattern.equals( other.expectedUrlPattern ) ) return false;
        return true;
    }
}
