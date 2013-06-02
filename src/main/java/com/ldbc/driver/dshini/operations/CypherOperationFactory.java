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
        return entry.getHttpMethod().equals( "POST" ) && CYPHER_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws RequestLogEntryException
    {
        Map<String, Object> cypherMap = entry.getDescriptionAsMap();
        String cypherQueryString = (String) cypherMap.get( "query" );
        Map<String, Object> cypherParams = (Map<String, Object>) cypherMap.get( "params" );
        return new CypherOperation( entry.getTime(), cypherQueryString, cypherParams );
    }

    public static class CypherOperation extends Operation<Object>
    {
        private final long time;
        private final String queryString;
        private final Map<String, Object> params;

        private CypherOperation( long time, String queryString, Map<String, Object> params )
        {
            super();
            this.time = time;
            this.queryString = queryString;
            this.params = params;
        }

        public long getTime()
        {
            return time;
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
            return "CypherOperation [time=" + time + ", queryString=" + queryString + ", params=" + params + "]";
        }
    }
}
