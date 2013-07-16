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
operationDescription="{""query"":""START n=node({STARTIDS}) MATCH n<-[:SITE_OWNED_BY]-sites<-[:BOARD_SHOWN_ON*0..1]-board WHERE   (board.IsDeleted? = {BOARDISDELETED}) WITH board MATCH board<-[rel:SUBSCRIBES]-users WHERE NOT(HAS(rel.Type)) WITH board, users, rel WHERE (users.IsActive! = {USERSISACTIVE}) WITH users, MIN(rel.CreatedAt?) AS created_at RETURN COUNT(users)"",""params"":{""STARTIDS"":260722,""BOARDISDELETED"":false,""USERSISACTIVE"":true}}"
*/

public class CountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperationFactory extends AbstractCypherOperationFactory
{
    @Override
    protected String getCypherQueryString()
    {
        /*
         * START user=node({STARTIDS}) 
         * MATCH user<-[:SITE_OWNED_BY]-sites<-[:BOARD_SHOWN_ON*0..1]-board 
         * WHERE (board.IsDeleted? = {BOARDISDELETED}) 
         * WITH board 
         * MATCH board<-[rel:SUBSCRIBES]-users
         * WHERE NOT(HAS(rel.Type)) 
         * WITH board, users, rel 
         * WHERE (users.IsActive! = {USERSISACTIVE}) 
         * WITH users, MIN(rel.CreatedAt?) AS created_at 
         * RETURN COUNT(users)
         */
        return "START n=node({STARTIDS}) MATCH n<-[:SITE_OWNED_BY]-sites<-[:BOARD_SHOWN_ON*0..1]-board WHERE   (board.IsDeleted? = {BOARDISDELETED}) WITH board MATCH board<-[rel:SUBSCRIBES]-users WHERE NOT(HAS(rel.Type)) WITH board, users, rel WHERE (users.IsActive! = {USERSISACTIVE}) WITH users, MIN(rel.CreatedAt?) AS created_at RETURN COUNT(users)";
    }

    @Override
    protected Set<String> getCypherParamKeys()
    {
        Set<String> paramKeys = new HashSet<String>();
        paramKeys.add( "STARTIDS" );
        paramKeys.add( "BOARDISDELETED" );
        paramKeys.add( "USERSISACTIVE" );
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
            return new CountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperation( entry.getTime(), cypherQueryString,
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
        return CountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperation.class;
    }

    public static class CountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperation extends Operation<Object>
    {
        private final String queryString;
        private final Map<String, Object> params;

        private CountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperation( Time time, String queryString,
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
            CountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperation other = (CountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperation) obj;
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
