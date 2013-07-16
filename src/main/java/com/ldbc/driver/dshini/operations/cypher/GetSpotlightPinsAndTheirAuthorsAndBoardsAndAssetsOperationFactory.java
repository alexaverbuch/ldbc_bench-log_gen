package com.ldbc.driver.dshini.operations.cypher;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.util.temporal.Time;

/*
operationDescription="{""query"":""START n=node:neo_root(RootOf = {ROOT}), current=node:user_profile(UserId = {USERID}) MATCH n<-[:IS_SPOTLIGHT]-pin WHERE   (pin.CreatedAt<{PINCREATEDAT}) WITH pin, current ORDER BY pin.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT} MATCH pin-[:AUTHORED_BY]->author, pin-[:PINS]->board, pin-[:PINS_ASSET]->asset WHERE (not(author<-[:TROLLS]-current)) RETURN pin, author, board, asset"",""params"":{""ROOT"":""Spotlight"",""USERID"":1,""PINCREATEDAT"":1366223316,""QSKIP"":0,""QLIMIT"":20}}"
*/

public class GetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperationFactory extends AbstractCypherOperationFactory
{
    @Override
    protected String getCypherQueryString()
    {
        /*
         * START spotlight=node:neo_root(RootOf = {ROOT}), user=node:user_profile(UserId = {USERID}) 
         * MATCH spotlight<-[:IS_SPOTLIGHT]-pin 
         * WHERE (pin.CreatedAt<{PINCREATEDAT})
         * WITH pin, user 
         * ORDER BY pin.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT} 
         * MATCH pin-[:AUTHORED_BY]->author, pin-[:PINS]->board, pin-[:PINS_ASSET]->asset
         * WHERE (not(author<-[:TROLLS]-user)) 
         * RETURN pin, author, board, asset
         */
        return "START n=node:neo_root(RootOf = {ROOT}), current=node:user_profile(UserId = {USERID}) MATCH n<-[:IS_SPOTLIGHT]-pin WHERE   (pin.CreatedAt<{PINCREATEDAT}) WITH pin, current ORDER BY pin.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT} MATCH pin-[:AUTHORED_BY]->author, pin-[:PINS]->board, pin-[:PINS_ASSET]->asset WHERE (not(author<-[:TROLLS]-current)) RETURN pin, author, board, asset";
    }

    @Override
    protected Set<String> getCypherParamKeys()
    {
        Set<String> paramKeys = new HashSet<String>();
        paramKeys.add( "ROOT" );
        paramKeys.add( "USERID" );
        paramKeys.add( "PINCREATEDAT" );
        paramKeys.add( "QSKIP" );
        paramKeys.add( "QLIMIT" );
        return paramKeys;
    }

    @Override
    public Operation<?> createOperationFrom( RequestLogEntry entry ) throws MatchableException
    {
        // TODO extract fields for Operation
        try
        {
            Map<String, Object> cypherMap = entry.getDescriptionAsMap();
            String cypherQueryString = (String) cypherMap.get( "query" );
            Map<String, Object> cypherParams = (Map<String, Object>) cypherMap.get( "params" );
            return new GetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperation( entry.getTime(), cypherQueryString,
                    cypherParams );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    @Override
    public Class<? extends Operation<?>> operationType()
    {
        return GetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperation.class;
    }

    public static class GetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperation extends Operation<Object>
    {
        private final String queryString;
        private final Map<String, Object> params;

        private GetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperation( Time time, String queryString,
                Map<String, Object> params )
        {
            super();
            setScheduledStartTime( time );
            this.queryString = queryString;
            this.params = params;
        }

        public String getQueryString()
        {
            return queryString;
        }

        public Map<String, Object> getParams()
        {
            return params;
        }

        @Override
        public String toString()
        {
            return "CypherOperation [time=" + getScheduledStartTime() + ", queryString=" + queryString + ", params="
                   + params + "]";
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + ( ( params == null ) ? 0 : params.hashCode() );
            result = prime * result + ( ( queryString == null ) ? 0 : queryString.hashCode() );
            return result;
        }

        @Override
        public boolean equals( Object obj )
        {
            if ( this == obj ) return true;
            if ( obj == null ) return false;
            if ( getClass() != obj.getClass() ) return false;
            GetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperation other = (GetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperation) obj;
            if ( params == null )
            {
                if ( other.params != null ) return false;
            }
            else if ( !params.equals( other.params ) ) return false;
            if ( queryString == null )
            {
                if ( other.queryString != null ) return false;
            }
            else if ( !queryString.equals( other.queryString ) ) return false;
            return true;
        }
    }
}
