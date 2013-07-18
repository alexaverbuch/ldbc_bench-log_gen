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
operationDescription="{""query"":""START n=node({STARTIDS}) MATCH n<-[:OFFERS]-o, n-[:IN_CATEGORY]->subcat-[:SUB_CAT_OF*0..2]->cat WHERE   (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) AND (cat.CategoryIdentifier = {CATCATEGORYIDENTIFIER}) RETURN distinct n SKIP {QSKIP} LIMIT {QLIMIT}"",""params"":{""STARTIDS"":[779751,1024525,327179],""OISINSTOCK"":true,""OISEXPIRED"":false,""OISREMOVED"":false,""OISFREEZEDBYUSER"":false,""OISSWAPED"":false,""CATCATEGORYIDENTIFIER"":""Kosmetik_Pflege"",""QSKIP"":0,""QLIMIT"":30}}"
*/

public class GetProductsInCategoriesUnderOffer1OperationFactory extends AbstractCypherOperationFactory
{
    @Override
    protected String getCypherQueryString()
    {
        /*
         * START product=node({STARTIDS}) 
         * MATCH product<-[:OFFERS]-o, product-[:IN_CATEGORY]->subcat-[:SUB_CAT_OF*0..2]->cat 
         * WHERE (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND 
         *       (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) AND 
         *       (cat.CategoryIdentifier = {CATCATEGORYIDENTIFIER}) 
         * RETURN distinct product SKIP {QSKIP} LIMIT {QLIMIT}
         */
        return "START n=node({STARTIDS}) MATCH n<-[:OFFERS]-o, n-[:IN_CATEGORY]->subcat-[:SUB_CAT_OF*0..2]->cat WHERE   (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) AND (cat.CategoryIdentifier = {CATCATEGORYIDENTIFIER}) RETURN distinct n SKIP {QSKIP} LIMIT {QLIMIT}";
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
        paramKeys.add( "CATCATEGORYIDENTIFIER" );
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
            return new GetProductsInCategoriesUnderOffer1Operation( entry.getTime(), cypherQueryString, cypherParams );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    @Override
    public Class<? extends Operation<?>> operationType()
    {
        return GetProductsInCategoriesUnderOffer1Operation.class;
    }

    public static class GetProductsInCategoriesUnderOffer1Operation extends Operation<Object>
    {
        private final String queryString;
        private final Map<String, Object> params;

        private GetProductsInCategoriesUnderOffer1Operation( Time time, String queryString, Map<String, Object> params )
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
            GetProductsInCategoriesUnderOffer1Operation other = (GetProductsInCategoriesUnderOffer1Operation) obj;
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
