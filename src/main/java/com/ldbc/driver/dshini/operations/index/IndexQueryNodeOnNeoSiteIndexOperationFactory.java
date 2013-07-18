package com.ldbc.driver.dshini.operations.index;

import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.operations.AbstractIndexOperationFactory;
import com.ldbc.driver.dshini.utils.UrlParsingUtils;
import com.ldbc.driver.dshini.utils.UrlPatterns;
import com.ldbc.driver.util.temporal.Time;

/*
httpMethod=GET, 
url=http://graph.internal.dshini.net:7474/db/data/index/node/INDEX_NAME?query=QUERY, 
operationDescription=null,  
*/

public class IndexQueryNodeOnNeoSiteIndexOperationFactory extends AbstractIndexOperationFactory
{
    @Override
    protected String getExpectedHttpMethod()
    {
        return "GET";
    }

    @Override
    protected Pattern getExpectedUrlPattern()
    {
        return UrlPatterns.INDEX_QUERY_NODE;
    }

    @Override
    protected String getExpectedIndexNameString()
    {
        return "neo_site";
    }

    @Override
    public Operation<?> createOperationFrom( RequestLogEntry entry ) throws MatchableException
    {
        try
        {
            String indexName = UrlParsingUtils.parseIndexNameFromNodeIndexUrl( entry.getUrl() );
            String indexQuery = UrlParsingUtils.parseIndexQueryFromNodeIndexQueryUrl( entry.getUrl() );
            return new IndexQueryNodeOnNeoSiteIndexOperation( entry.getTime(), indexName, indexQuery );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    @Override
    public Class<? extends Operation<?>> operationType()
    {
        return IndexQueryNodeOnNeoSiteIndexOperation.class;
    }

    public static class IndexQueryNodeOnNeoSiteIndexOperation extends Operation<Integer>
    {
        private final String indexName;
        private final String indexQuery;

        private IndexQueryNodeOnNeoSiteIndexOperation( Time time, String indexName, String indexQuery )
        {
            super();
            setScheduledStartTime( time );
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
            return getClass().getSimpleName() + " [time=" + getScheduledStartTime() + ", indexName=" + indexName
                   + ", indexQuery=" + indexQuery + "]";
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
            IndexQueryNodeOnNeoSiteIndexOperation other = (IndexQueryNodeOnNeoSiteIndexOperation) obj;
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
