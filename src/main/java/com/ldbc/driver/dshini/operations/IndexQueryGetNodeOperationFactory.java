package com.ldbc.driver.dshini.operations;

import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogEntry;
import com.ldbc.driver.dshini.generator.RequestLogEntryException;
import com.ldbc.driver.dshini.generator.UrlParsingUtils;

/*
httpMethod=GET, 
url=http://graph.internal.dshini.net:7474/db/data/index/node/user_profile?query=UserId:2149, 
operationDescription=null,  
*/

public class IndexQueryGetNodeOperationFactory implements MatchableOperationCreator
{
    private final Pattern INDEX_QUERY_NODE_PATTERN = Pattern.compile( ".*db/data/index/node/.*\\?query=.*" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws MatchableException
    {
        return entry.getHttpMethod().equals( "GET" ) && INDEX_QUERY_NODE_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws RequestLogEntryException
    {
        String indexName = UrlParsingUtils.parseIndexNameForNodeIndexQueryUrl( entry.getUrl() );
        String indexQuery = UrlParsingUtils.parseIndexQueryFromNodeIndexQueryUrl( entry.getUrl() );
        return new IndexQueryGetNodeOperation( entry.getTime(), indexName, indexQuery );
    }

    public class IndexQueryGetNodeOperation extends Operation<Object>
    {
        private final long time;
        private final String indexName;
        private final String indexQuery;

        private IndexQueryGetNodeOperation( long time, String indexName, String indexQuery )
        {
            super();
            this.time = time;
            this.indexName = indexName;
            this.indexQuery = indexQuery;
        }

        public long getTime()
        {
            return time;
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
            return "IndexQueryGetNodeOperation [time=" + time + ", indexName=" + indexName + ", indexQuery="
                   + indexQuery + "]";
        }
    }
}
