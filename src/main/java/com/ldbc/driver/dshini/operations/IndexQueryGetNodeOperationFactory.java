package com.ldbc.driver.dshini.operations;

import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.DshiniLogEntryMatchable;
import com.ldbc.driver.dshini.generator.DshiniLogEntryMatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.log.UrlParsingUtils;

/*
httpMethod=GET, 
url=http://graph.internal.dshini.net:7474/db/data/index/node/user_profile?query=UserId:2149, 
operationDescription=null,  
*/

public class IndexQueryGetNodeOperationFactory implements DshiniLogEntryMatchable
{
    private final Pattern INDEX_QUERY_NODE_PATTERN = Pattern.compile( ".*db/data/index/node/.*\\?query=.*" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws DshiniLogEntryMatchableException
    {
        return entry.getHttpMethod().equals( "GET" ) && INDEX_QUERY_NODE_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws DshiniLogEntryMatchableException
    {
        try
        {
            String indexName = UrlParsingUtils.parseIndexNameForNodeIndexQueryUrl( entry.getUrl() );
            String indexQuery = UrlParsingUtils.parseIndexQueryFromNodeIndexQueryUrl( entry.getUrl() );
            return new IndexQueryGetNodeOperation( entry.getTimeNanoSeconds(), indexName, indexQuery );
        }
        catch ( RequestLogEntryException e )
        {
            throw new DshiniLogEntryMatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    public static class IndexQueryGetNodeOperation extends Operation<Integer>
    {
        private final String indexName;
        private final String indexQuery;

        private IndexQueryGetNodeOperation( long time, String indexName, String indexQuery )
        {
            super();
            setScheduledStartTimeNanoSeconds( time );
            this.indexName = indexName;
            this.indexQuery = indexQuery;
        }

        public String getIndexName()
        {
            return indexName;
        }

        public String getIndexQuery()
        {
            return indexQuery;
        }

        @Override
        public String toString()
        {
            return "IndexQueryGetNodeOperation [time=" + getScheduledStartTimeNanoSeconds() + ", indexName="
                   + indexName + ", indexQuery=" + indexQuery + "]";
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + ( ( indexName == null ) ? 0 : indexName.hashCode() );
            result = prime * result + ( ( indexQuery == null ) ? 0 : indexQuery.hashCode() );
            return result;
        }

        @Override
        public boolean equals( Object obj )
        {
            if ( this == obj ) return true;
            if ( obj == null ) return false;
            if ( getClass() != obj.getClass() ) return false;
            IndexQueryGetNodeOperation other = (IndexQueryGetNodeOperation) obj;
            if ( indexName == null )
            {
                if ( other.indexName != null ) return false;
            }
            else if ( !indexName.equals( other.indexName ) ) return false;
            if ( indexQuery == null )
            {
                if ( other.indexQuery != null ) return false;
            }
            else if ( !indexQuery.equals( other.indexQuery ) ) return false;
            return true;
        }
    }
}
