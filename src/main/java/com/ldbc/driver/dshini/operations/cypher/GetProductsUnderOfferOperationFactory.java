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
operationDescription="{""query"":""START n=node({STARTIDS}) MATCH n<-[:OFFERS]-o WHERE   (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) RETURN distinct n SKIP {QSKIP} LIMIT {QLIMIT}"",""params"":{""STARTIDS"":[532068,167351,167353,320717,167347,487812,168724,168725,528686,7784632,167348,462661],""OISINSTOCK"":true,""OISEXPIRED"":false,""OISREMOVED"":false,""OISFREEZEDBYUSER"":false,""OISSWAPED"":false,""QSKIP"":0,""QLIMIT"":5}}"
*/

public class GetProductsUnderOfferOperationFactory extends AbstractCypherOperationFactory
{
    @Override
    protected String getCypherQueryString()
    {
        /*
         * START product=node({STARTIDS}) 
         * MATCH product<-[:OFFERS]-o 
         * WHERE (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND 
         *       (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) 
         * RETURN distinct product SKIP {QSKIP} LIMIT {QLIMIT}
         */
        return "START n=node({STARTIDS}) MATCH n<-[:OFFERS]-o WHERE   (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) RETURN distinct n SKIP {QSKIP} LIMIT {QLIMIT}";
    }

    @Override
    protected Set<String> getCypherParamKeys()
    {
        Set<String> paramKeys = new HashSet<String>();
        paramKeys.add( "STARTIDS" );
        paramKeys.add( "OISINSTOCK" );
        paramKeys.add( "OISEXPIRED" );
        paramKeys.add( "OISREMOVED" );
        paramKeys.add( "OISFREEZEDBYUSER" );
        paramKeys.add( "OISSWAPED" );
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
            return new GetProductsUnderOfferOperation( entry.getTime(), cypherQueryString, cypherParams );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    @Override
    public Class<? extends Operation<?>> operationType()
    {
        return GetProductsUnderOfferOperation.class;
    }

    public static class GetProductsUnderOfferOperation extends Operation<Object>
    {
        private final String queryString;
        private final Map<String, Object> params;

        private GetProductsUnderOfferOperation( Time time, String queryString, Map<String, Object> params )
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
            GetProductsUnderOfferOperation other = (GetProductsUnderOfferOperation) obj;
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
