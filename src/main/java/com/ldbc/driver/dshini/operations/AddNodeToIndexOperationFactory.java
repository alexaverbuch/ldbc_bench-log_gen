package com.ldbc.driver.dshini.operations;

import java.util.Map;
import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.DshiniLogEntryMatchable;
import com.ldbc.driver.dshini.generator.DshiniLogEntryMatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.log.UrlParsingUtils;
import com.ldbc.driver.util.temporal.Time;

/*
httpMethod=POST, 
url=http://graph.internal.dshini.net:7474/db/data/index/node/neo_site, 
operationDescription="{""key"":""StatusMessage"",""value"":""<P>:D<\/P>"",""uri"":""http:\/\/graph.internal.dshini.net:7474\/db\/data\/node\/1144137""}"
*/

public class AddNodeToIndexOperationFactory implements DshiniLogEntryMatchable
{
    private final Pattern ADD_NODE_TO_INDEX_PATTERN = Pattern.compile( ".*db/data/index/node/[[^/]\\w]*$" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws DshiniLogEntryMatchableException
    {
        return entry.getHttpMethod().equals( "POST" ) && ADD_NODE_TO_INDEX_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws DshiniLogEntryMatchableException
    {
        try
        {
            String indexName = UrlParsingUtils.parseIndexNameFromNodeIndexUrl( entry.getUrl() );
            Map<String, Object> operationMap = entry.getDescriptionAsMap();
            String key = (String) operationMap.get( "key" );
            Object value = operationMap.get( "value" );
            long nodeId = UrlParsingUtils.parseNodeIdFromNodeUrl( (String) operationMap.get( "uri" ) );
            return new AddNodeToIndexOperation( entry.getTimeNanoSeconds(), indexName, key, value, nodeId );
        }
        catch ( RequestLogEntryException e )
        {
            throw new DshiniLogEntryMatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    public static class AddNodeToIndexOperation extends Operation<Integer>
    {
        private final String indexName;
        private final String key;
        private final Object value;
        private final long nodeId;

        private AddNodeToIndexOperation( long time, String indexName, String key, Object value, long nodeId )
        {
            super();
            setScheduledStartTime( Time.fromNano( time ) );
            this.indexName = indexName;
            this.key = key;
            this.value = value;
            this.nodeId = nodeId;
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
            return "AddNodeToIndexOperation [time=" + getScheduledStartTime() + ", indexName=" + indexName + ", key="
                   + key + ", value=" + value + ", nodeId=" + nodeId + "]";
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + ( ( indexName == null ) ? 0 : indexName.hashCode() );
            result = prime * result + ( ( key == null ) ? 0 : key.hashCode() );
            result = prime * result + (int) ( nodeId ^ ( nodeId >>> 32 ) );
            result = prime * result + ( ( value == null ) ? 0 : value.hashCode() );
            return result;
        }

        @Override
        public boolean equals( Object obj )
        {
            if ( this == obj ) return true;
            if ( obj == null ) return false;
            if ( getClass() != obj.getClass() ) return false;
            AddNodeToIndexOperation other = (AddNodeToIndexOperation) obj;
            if ( indexName == null )
            {
                if ( other.indexName != null ) return false;
            }
            else if ( !indexName.equals( other.indexName ) ) return false;
            if ( key == null )
            {
                if ( other.key != null ) return false;
            }
            else if ( !key.equals( other.key ) ) return false;
            if ( nodeId != other.nodeId ) return false;
            if ( value == null )
            {
                if ( other.value != null ) return false;
            }
            else if ( !value.equals( other.value ) ) return false;
            return true;
        }
    }

}
