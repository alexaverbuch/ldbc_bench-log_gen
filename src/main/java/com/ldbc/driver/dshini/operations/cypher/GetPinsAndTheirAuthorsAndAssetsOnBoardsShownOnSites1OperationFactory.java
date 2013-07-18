package com.ldbc.driver.dshini.operations.cypher;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.operations.AbstractCypherOperationFactory;
import com.ldbc.driver.util.temporal.Time;

/*
operationDescription="{""query"":""START site=node({SITE_ID}), n=node:user_profile({USER_QUERY}) MATCH site<-[:BOARD_SHOWN_ON]-board WITH board, n MATCH board<-[:PINS]-pin-[:AUTHORED_BY]->author WHERE (pin.CreatedAt<{PINCREATEDAT}) WITH pin, author, board, n WHERE not(author<-[:TROLLS]-n) WITH pin, author, board, n ORDER BY pin.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT} MATCH pin-[:PINS_ASSET]->asset RETURN pin, author, board, asset, LENGTH(n<-[:LIKED_BY]-pin) > 0 as has_liked"",""params"":{""SITE_ID"":672776,""USER_QUERY"":""UserId:161088"",""PINCREATEDAT"":1364052396,""QSKIP"":0,""QLIMIT"":20}}"
*/

public class GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1OperationFactory extends
        AbstractCypherOperationFactory
{
    @Override
    protected String getCypherQueryString()
    {
        /*
         * START site=node({SITE_ID}), user=node:user_profile({USER_QUERY}) 
         * MATCH site<-[:BOARD_SHOWN_ON]-board 
         * WITH board, user
         * MATCH board<-[:PINS]-pin-[:AUTHORED_BY]->author
         * WHERE (pin.CreatedAt<{PINCREATEDAT}) 
         * WITH pin, author, board, user 
         * WHERE not(author<-[:TROLLS]-user)
         * WITH pin, author, board, user
         * ORDER BY pin.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT} 
         * MATCH pin-[:PINS_ASSET]->asset 
         * RETURN pin, author, board, asset, LENGTH(user<-[:LIKED_BY]-pin) > 0 as has_liked
         */
        return "START site=node({SITE_ID}), n=node:user_profile({USER_QUERY}) MATCH site<-[:BOARD_SHOWN_ON]-board WITH board, n MATCH board<-[:PINS]-pin-[:AUTHORED_BY]->author WHERE (pin.CreatedAt<{PINCREATEDAT}) WITH pin, author, board, n WHERE not(author<-[:TROLLS]-n) WITH pin, author, board, n ORDER BY pin.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT} MATCH pin-[:PINS_ASSET]->asset RETURN pin, author, board, asset, LENGTH(n<-[:LIKED_BY]-pin) > 0 as has_liked";
    }

    @Override
    protected Set<String> getCypherParamKeys()
    {
        Set<String> paramKeys = new HashSet<String>();
        paramKeys.add( "SITE_ID" );
        paramKeys.add( "USER_QUERY" );
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
            return new GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1Operation( entry.getTime(),
                    cypherQueryString, cypherParams );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    @Override
    public Class<? extends Operation<?>> operationType()
    {
        return GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1Operation.class;
    }

    public static class GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1Operation extends Operation<Object>
    {
        private final String queryString;
        private final Map<String, Object> params;

        private GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1Operation( Time time, String queryString,
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
            return getClass().getSimpleName() + " [time=" + getScheduledStartTime() + ", queryString=" + queryString
                   + ", params=" + params + "]";
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
            GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1Operation other = (GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1Operation) obj;
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
