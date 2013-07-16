package com.ldbc.driver.dshini.operations;

import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.Matchable;
import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.utils.UrlParsingUtils;
import com.ldbc.driver.util.temporal.Time;

/*
httpMethod=DELETE, 
url=http://graph.internal.dshini.net:7474/db/data/index/node/neo_pin/8027060, 
operationDescription=,
*/

public class DeleteNodeFromIndexOperationFactory implements Matchable<RequestLogEntry>
{
    private final Pattern DELETE_NODE_FROM_INDEX_PATTERN = Pattern.compile( ".*db/data/index/node/[[^/]\\w]*/\\d*$" );

    @Override
    public boolean matches( RequestLogEntry entry )
    {
        return entry.getHttpMethod().equals( "DELETE" )
               && DELETE_NODE_FROM_INDEX_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createOperationFrom( RequestLogEntry entry ) throws MatchableException
    {
        try
        {
            long nodeId = UrlParsingUtils.parseNodeIdFromNodeIndexUrl( entry.getUrl() );
            String indexName = UrlParsingUtils.parseIndexNameFromNodeIndexUrl( entry.getUrl() );
            return new DeleteNodeFromIndexOperation( entry.getTime(), nodeId, indexName );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    @Override
    public Class<? extends Operation<?>> operationType()
    {
        return DeleteNodeFromIndexOperation.class;
    }

    public static class DeleteNodeFromIndexOperation extends Operation<Integer>
    {
        private final long nodeId;
        private final String indexName;

        private DeleteNodeFromIndexOperation( Time time, long nodeId, String indexName )
        {
            super();
            setScheduledStartTime( time );
            this.nodeId = nodeId;
            this.indexName = indexName;
        }

        public long getNodeId()
        {
            return nodeId;
        }

        public String getIndexName()
        {
            return indexName;
        }

        @Override
        public String toString()
        {
            return "DeleteNodeFromIndexOperation [time=" + getScheduledStartTime() + ",nodeId=" + nodeId
                   + ", indexName=" + indexName + "]";
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + ( ( indexName == null ) ? 0 : indexName.hashCode() );
            result = prime * result + (int) ( nodeId ^ ( nodeId >>> 32 ) );
            return result;
        }

        @Override
        public boolean equals( Object obj )
        {
            if ( this == obj ) return true;
            if ( obj == null ) return false;
            if ( getClass() != obj.getClass() ) return false;
            DeleteNodeFromIndexOperation other = (DeleteNodeFromIndexOperation) obj;
            if ( indexName == null )
            {
                if ( other.indexName != null ) return false;
            }
            else if ( !indexName.equals( other.indexName ) ) return false;
            if ( nodeId != other.nodeId ) return false;
            return true;
        }
    }
}
