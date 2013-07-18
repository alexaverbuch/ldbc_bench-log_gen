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
operationDescription="{""query"":""START n=node({STARTIDS}) MATCH n-[subscription:SUBSCRIBES]->site<-[:BOARD_SHOWN_ON*0..1]-board WHERE   NOT(HAS(subscription.Type)) WITH board, n MATCH board<-[:PINS]-pin WHERE (pin.CreatedAt<{PINCREATEDAT}) WITH pin, board, n MATCH pin-[:AUTHORED_BY]->author WHERE NOT(author<-[:TROLLS]-n) WITH pin, author, board, n ORDER BY pin.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT} MATCH pin-[:PINS_ASSET]->asset RETURN pin, author, board, asset, LENGTH(n<-[:LIKED_BY]-pin) > 0 as has_liked"",""params"":{""STARTIDS"":2645696,""PINCREATEDAT"":1367242200,""QSKIP"":0,""QLIMIT"":20}}"
*/

public class GetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperationFactory extends
        AbstractCypherOperationFactory
{
    @Override
    protected String getCypherQueryString()
    {
        /*
         * START user=node({STARTIDS}) 
         * MATCH user-[subscription:SUBSCRIBES]->site<-[:BOARD_SHOWN_ON*0..1]-board 
         * WHERE NOT(HAS(subscription.Type)) 
         * WITH board, user 
         * MATCH board<-[:PINS]-pin
         * WHERE (pin.CreatedAt<{PINCREATEDAT}) 
         * WITH pin, board, user 
         * MATCH pin-[:AUTHORED_BY]->author
         * WHERE NOT(author<-[:TROLLS]-user) 
         * WITH pin, author, board, user
         * ORDER BY pin.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT} 
         * MATCH pin-[:PINS_ASSET]->asset 
         * RETURN pin, author, board, asset, LENGTH(user<-[:LIKED_BY]-pin) > 0 as has_liked 
         */
        return "START n=node({STARTIDS}) MATCH n-[subscription:SUBSCRIBES]->site<-[:BOARD_SHOWN_ON*0..1]-board WHERE   NOT(HAS(subscription.Type)) WITH board, n MATCH board<-[:PINS]-pin WHERE (pin.CreatedAt<{PINCREATEDAT}) WITH pin, board, n MATCH pin-[:AUTHORED_BY]->author WHERE NOT(author<-[:TROLLS]-n) WITH pin, author, board, n ORDER BY pin.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT} MATCH pin-[:PINS_ASSET]->asset RETURN pin, author, board, asset, LENGTH(n<-[:LIKED_BY]-pin) > 0 as has_liked";
    }

    @Override
    protected Set<String> getCypherParamKeys()
    {
        Set<String> paramKeys = new HashSet<String>();
        paramKeys.add( "STARTIDS" );
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
            return new GetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperation( entry.getTime(),
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
        return GetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperation.class;
    }

    public static class GetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperation extends
            Operation<Object>
    {
        private final String queryString;
        private final Map<String, Object> params;

        private GetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperation( Time time, String queryString,
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
            GetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperation other = (GetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperation) obj;
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
