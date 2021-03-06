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
httpMethod=DELETE, 
url=http://graph.internal.dshini.net:7474/db/data/index/node/INDEX_NAME/8027060, 
operationDescription=,
*/

public class DeleteNodeFromNeoPinBoardIndexOperationFactory extends AbstractIndexOperationFactory
{
    @Override
    protected String getExpectedHttpMethod()
    {
        return "DELETE";
    }

    @Override
    protected Pattern getExpectedUrlPattern()
    {
        return UrlPatterns.DELETE_NODE_FROM_INDEX;
    }

    @Override
    protected String getExpectedIndexNameString()
    {
        return "neo_pin_board";
    }

    @Override
    public Operation<?> createOperationFrom( RequestLogEntry entry ) throws MatchableException
    {
        try
        {
            long nodeId = UrlParsingUtils.parseNodeIdFromNodeIndexUrl( entry.getUrl() );
            String indexName = UrlParsingUtils.parseIndexNameFromNodeIndexUrl( entry.getUrl() );
            return new DeleteNodeFromNeoPinBoardIndexOperation( entry.getTime(), nodeId, indexName );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    @Override
    public Class<? extends Operation<?>> operationType()
    {
        return DeleteNodeFromNeoPinBoardIndexOperation.class;
    }

    public static class DeleteNodeFromNeoPinBoardIndexOperation extends Operation<Integer>
    {
        private final long nodeId;
        private final String indexName;

        private DeleteNodeFromNeoPinBoardIndexOperation( Time time, long nodeId, String indexName )
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
            return getClass().getSimpleName() + " [time=" + getScheduledStartTime() + ",nodeId=" + nodeId
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
            DeleteNodeFromNeoPinBoardIndexOperation other = (DeleteNodeFromNeoPinBoardIndexOperation) obj;
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
