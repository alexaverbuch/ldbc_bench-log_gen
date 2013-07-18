package com.ldbc.driver.dshini.operations;

import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.utils.UrlPatterns;

/*
httpMethod=POST, 
url=http://graph.internal.dshini.net:7474/db/data/cypher,
operationDescription="{""query"":""..."",""params"":{...}}"
 */
public abstract class AbstractCypherOperationFactory extends AbstractOperationFactory
{
    private final String cypherQueryString;
    private final Set<String> cypherParamKeys;
    private final String expectedHttpMethod;
    private final Pattern expectedUrlPattern;

    public AbstractCypherOperationFactory()
    {
        cypherQueryString = getCypherQueryString();
        cypherParamKeys = getCypherParamKeys();
        expectedHttpMethod = getExpectedHttpMethod();
        expectedUrlPattern = getExpectedUrlPattern();
    }

    protected abstract String getCypherQueryString();

    protected abstract Set<String> getCypherParamKeys();

    @Override
    protected final String getExpectedHttpMethod()
    {
        return "POST";
    }

    @Override
    protected final Pattern getExpectedUrlPattern()
    {
        return UrlPatterns.CYPHER;
    }

    @Override
    protected final boolean performCustomLogic( RequestLogEntry entry ) throws MatchableException
    {
        try
        {
            String cypherString = (String) entry.getDescriptionAsMap().get( "query" );
            boolean isExpectedQuery = getCypherQueryString().equals( cypherString );
            if ( false == isExpectedQuery ) return false;

            Map<String, Object> params = (Map<String, Object>) entry.getDescriptionAsMap().get( "params" );
            boolean isExpectedParams = params.keySet().equals( getCypherParamKeys() );
            return isExpectedParams;
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( e.getCause() );
        }
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( cypherParamKeys == null ) ? 0 : cypherParamKeys.hashCode() );
        result = prime * result + ( ( cypherQueryString == null ) ? 0 : cypherQueryString.hashCode() );
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
        AbstractCypherOperationFactory other = (AbstractCypherOperationFactory) obj;
        if ( cypherParamKeys == null )
        {
            if ( other.cypherParamKeys != null ) return false;
        }
        else if ( !cypherParamKeys.equals( other.cypherParamKeys ) ) return false;
        if ( cypherQueryString == null )
        {
            if ( other.cypherQueryString != null ) return false;
        }
        else if ( !cypherQueryString.equals( other.cypherQueryString ) ) return false;
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
