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
operationDescription="{""query"":""START n=node:neo_category(CategoryIdentifier={CATEGORYIDENTIFIER}) MATCH n<-[:SUB_CAT_OF]-sub_cats WHERE   (sub_cats.IsVisible? = {SUB_CATSISVISIBLE}) RETURN sub_cats ORDER BY sub_cats.Title"",""params"":{""CATEGORYIDENTIFIER"":""Bekleidung_Schuhe"",""SUB_CATSISVISIBLE"":true}}"
*/

public class GetSubCategoriesOfParentCategories1OperationFactory extends AbstractCypherOperationFactory
{
    @Override
    protected String getCypherQueryString()
    {
        /*
         * START category=node:neo_category(CategoryIdentifier={CATEGORYIDENTIFIER}) 
         * MATCH category<-[:SUB_CAT_OF]-sub_cats 
         * WHERE (sub_cats.IsVisible? = {SUB_CATSISVISIBLE}) 
         * RETURN sub_cats 
         * ORDER BY sub_cats.Title
         */
        return "START n=node:neo_category(CategoryIdentifier={CATEGORYIDENTIFIER}) MATCH n<-[:SUB_CAT_OF]-sub_cats WHERE   (sub_cats.IsVisible? = {SUB_CATSISVISIBLE}) RETURN sub_cats ORDER BY sub_cats.Title";
    }

    @Override
    protected Set<String> getCypherParamKeys()
    {
        Set<String> paramKeys = new HashSet<String>();
        paramKeys.add( "CATEGORYIDENTIFIER" );
        paramKeys.add( "SUB_CATSISVISIBLE" );
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
            return new GetSubCategoriesOfParentCategories1Operation( entry.getTime(), cypherQueryString, cypherParams );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    @Override
    public Class<? extends Operation<?>> operationType()
    {
        return GetSubCategoriesOfParentCategories1Operation.class;
    }

    public static class GetSubCategoriesOfParentCategories1Operation extends Operation<Object>
    {
        private final String queryString;
        private final Map<String, Object> params;

        private GetSubCategoriesOfParentCategories1Operation( Time time, String queryString, Map<String, Object> params )
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
            GetSubCategoriesOfParentCategories1Operation other = (GetSubCategoriesOfParentCategories1Operation) obj;
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
