package com.ldbc.driver.dshini.operations.index;

import java.util.Map;
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
httpMethod=POST, 
url=http://graph.internal.dshini.net:7474/db/data/index/node/INDEX_NAME, 
operationDescription="{""key"":""StatusMessage"",""value"":""<P>:D<\/P>"",""uri"":""http:\/\/graph.internal.dshini.net:7474\/db\/data\/node\/1144137""}"
*/

public class AddNodeToNeoPinUrlIndexOperationFactory extends AbstractIndexOperationFactory
{
    @Override
    protected String getExpectedHttpMethod()
    {
        return "POST";
    }

    @Override
    protected Pattern getExpectedUrlPattern()
    {
        return UrlPatterns.ADD_NODE_TO_INDEX;
    }

    @Override
    protected String getExpectedIndexNameString()
    {
        return "neo_pin_url";
    }

    @Override
    public Operation<?> createOperationFrom( RequestLogEntry entry ) throws MatchableException
    {
        try
        {
            Map<String, Object> operationMap = entry.getDescriptionAsMap();
            String key = (String) operationMap.get( "key" );
            Object value = operationMap.get( "value" );
            long nodeId = UrlParsingUtils.parseNodeIdFromNodeUrl( (String) operationMap.get( "uri" ) );
            return new AddNodeToNeoPinUrlIndexOperation( entry.getTime(), getExpectedIndexNameString(), key, value,
                    nodeId );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    @Override
    public Class<? extends Operation<?>> operationType()
    {
        return AddNodeToNeoPinUrlIndexOperation.class;
    }

    public static class AddNodeToNeoPinUrlIndexOperation extends Operation<Integer>
    {
        private final String indexName;
        private final String key;
        private final Object value;
        private final long nodeId;

        private AddNodeToNeoPinUrlIndexOperation( Time time, String indexName, String key, Object value, long nodeId )
        {
            super();
            setScheduledStartTime( time );
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
            return getClass().getSimpleName() + " [time=" + getScheduledStartTime() + ", indexName=" + indexName
                   + ", key=" + key + ", value=" + value + ", nodeId=" + nodeId + "]";
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
            AddNodeToNeoPinUrlIndexOperation other = (AddNodeToNeoPinUrlIndexOperation) obj;
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
