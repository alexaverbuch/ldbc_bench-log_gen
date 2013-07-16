package com.ldbc.driver.dshini.operations.cypher;

import java.util.Map;
import java.util.Set;

import com.ldbc.driver.dshini.generator.Matchable;
import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.utils.UrlPatterns;

/*
httpMethod=POST, 
url=http://graph.internal.dshini.net:7474/db/data/cypher,
operationDescription="{""query"":""..."",""params"":{...}}"
 */
public abstract class AbstractCypherOperationFactory implements Matchable<RequestLogEntry>
{
    protected abstract String getCypherQueryString();

    protected abstract Set<String> getCypherParamKeys();

    @Override
    public final boolean matches( RequestLogEntry entry ) throws MatchableException
    {
        try
        {
            boolean isCypher = UrlPatterns.CYPHER.matcher( entry.getUrl() ).matches();
            if ( false == isCypher ) return false;

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
}
