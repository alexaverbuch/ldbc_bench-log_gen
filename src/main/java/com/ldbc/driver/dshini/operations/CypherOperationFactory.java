package com.ldbc.driver.dshini.operations;

import java.util.Map;
import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogEntry;
import com.ldbc.driver.dshini.generator.RequestLogEntryException;

/*
httpMethod=POST, 
url=http://graph.internal.dshini.net:7474/db/data/cypher, 
operationDescription="{""query"":""START n=node({STARTIDS}) MATCH n-[:PIN_REFERENCES_URL]->other_nodes RETURN other_nodes"",""params"":{""STARTIDS"":11436417}}",
*/

public class CypherOperationFactory implements MatchableOperationCreator
{
    private final Pattern CYPHER_PATTERN = Pattern.compile( ".*db/data/cypher$" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws MatchableException
    {
        // TODO cypher is always POST?
        // return entry.getHttpMethod().equals( "POST" ) &&
        // CYPHER_PATTERN.matcher( entry.getUrl() ).matches();
        return CYPHER_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws RequestLogEntryException
    {
        Map<String, Object> cypherMap = entry.getDescriptionAsMap();
        String cypherQueryString = (String) cypherMap.get( "query" );
        Map<String, Object> cypherParams = (Map<String, Object>) cypherMap.get( "params" );
        return new CypherOperation( entry.getTimeNanoSeconds(), cypherQueryString, cypherParams );
    }

    public static class CypherOperation extends Operation<Object>
    {
        private final String queryString;
        private final Map<String, Object> params;

        private CypherOperation( long time, String queryString, Map<String, Object> params )
        {
            super();
            setScheduledStartTimeNanoSeconds( time );
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
            return "CypherOperation [time=" + getScheduledStartTimeNanoSeconds() + ", queryString=" + queryString
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
            CypherOperation other = (CypherOperation) obj;
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
