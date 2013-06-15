package com.ldbc.driver.dshini.operations;

import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogEntry;
import com.ldbc.driver.dshini.generator.RequestLogEntryException;
import com.ldbc.driver.dshini.generator.UrlParsingUtils;

/*
httpMethod=DELETE, 
url=http://graph-master.dshini.net:7474/db/data/node/11455077, 
operationDescription= 
*/

public class DeleteNodeOperationFactory implements MatchableOperationCreator
{
    private final Pattern DELETE_NODE_PATTERN = Pattern.compile( ".*db/data/node/\\d*$" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws MatchableException
    {
        return entry.getHttpMethod().equals( "DELETE" ) && DELETE_NODE_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws RequestLogEntryException
    {
        long nodeId = UrlParsingUtils.parseNodeIdFromNodeUrl( entry.getUrl() );
        return new DeleteNodeOperation( entry.getTimeNanoSeconds(), nodeId );
    }

    public static class DeleteNodeOperation extends Operation<Integer>
    {
        private final long nodeId;

        private DeleteNodeOperation( long time, long nodeId )
        {
            super();
            setScheduledStartTimeNanoSeconds( time );
            this.nodeId = nodeId;
        }

        public long getNodeId()
        {
            return nodeId;
        }

        @Override
        public String toString()
        {
            return "DeleteNodeOperation [time=" + getScheduledStartTimeNanoSeconds() + ", nodeId=" + nodeId + "]";
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) ( nodeId ^ ( nodeId >>> 32 ) );
            return result;
        }

        @Override
        public boolean equals( Object obj )
        {
            if ( this == obj ) return true;
            if ( obj == null ) return false;
            if ( getClass() != obj.getClass() ) return false;
            DeleteNodeOperation other = (DeleteNodeOperation) obj;
            if ( nodeId != other.nodeId ) return false;
            return true;
        }
    }
}
