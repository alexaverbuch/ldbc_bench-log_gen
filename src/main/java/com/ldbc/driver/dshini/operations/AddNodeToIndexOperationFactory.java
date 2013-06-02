package com.ldbc.driver.dshini.operations;

import java.util.Map;
import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogEntry;
import com.ldbc.driver.dshini.generator.RequestLogEntryException;
import com.ldbc.driver.dshini.generator.UrlParsingUtils;

/*
httpMethod=POST, 
url=http://graph.internal.dshini.net:7474/db/data/index/node/neo_site, 
operationDescription="{""key"":""StatusMessage"",""value"":""<P>:D<\/P>"",""uri"":""http:\/\/graph.internal.dshini.net:7474\/db\/data\/node\/1144137""}"
*/

public class AddNodeToIndexOperationFactory implements MatchableOperationCreator
{
    private final Pattern ADD_NODE_TO_INDEX_PATTERN = Pattern.compile( ".*db/data/index/node/[[^/]\\w]*$" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws MatchableException
    {
        return entry.getHttpMethod().equals( "POST" ) && ADD_NODE_TO_INDEX_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws RequestLogEntryException
    {
        String indexName = UrlParsingUtils.parseIndexNameFromNodeIndexUrl( entry.getUrl() );
        Map<String, Object> operationMap = entry.getDescriptionAsMap();
        String key = (String) operationMap.get( "key" );
        Object value = operationMap.get( "value" );
        long nodeId = UrlParsingUtils.parseNodeIdFromNodeUrl( (String) operationMap.get( "uri" ) );
        return new AddNodeToIndexOperation( entry.getTime(), indexName, key, value, nodeId );
    }

    public static class AddNodeToIndexOperation extends Operation<Object>
    {
        private final long time;
        private final String indexName;
        private final String key;
        private final Object value;
        private final long nodeId;

        private AddNodeToIndexOperation( long time, String indexName, String key, Object value, long nodeId )
        {
            super();
            this.time = time;
            this.indexName = indexName;
            this.key = key;
            this.value = value;
            this.nodeId = nodeId;
        }

        public long getTime()
        {
            return time;
        }

        public String getIndexName()
        {
            return indexName;
        }

        public String getKey()
        {
            return key;
        }

        public Object getValue()
        {
            return value;
        }

        public long getNodeId()
        {
            return nodeId;
        }

        @Override
        public String toString()
        {
            return "AddNodeToIndexOperation [time=" + time + ", indexName=" + indexName + ", key=" + key + ", value="
                   + value + ", nodeId=" + nodeId + "]";
        }
    }

}
