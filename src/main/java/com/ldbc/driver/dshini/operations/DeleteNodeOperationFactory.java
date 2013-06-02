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
        return new DeleteNodeOperation( entry.getTime(), nodeId );
    }

    public static class DeleteNodeOperation extends Operation<Object>
    {
        private final long time;
        private final long nodeId;

        private DeleteNodeOperation( long time, long nodeId )
        {
            super();
            this.time = time;
            this.nodeId = nodeId;
        }

        public long getTime()
        {
            return time;
        }

        public long getNodeId()
        {
            return nodeId;
        }

        @Override
        public String toString()
        {
            return "DeleteNodeOperation [time=" + time + ", nodeId=" + nodeId + "]";
        }
    }
}
