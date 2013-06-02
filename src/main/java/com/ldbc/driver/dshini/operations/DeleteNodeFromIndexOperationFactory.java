package com.ldbc.driver.dshini.operations;

import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogEntry;
import com.ldbc.driver.dshini.generator.RequestLogEntryException;
import com.ldbc.driver.dshini.generator.UrlParsingUtils;

/*
httpMethod=DELETE, 
url=http://graph.internal.dshini.net:7474/db/data/index/node/neo_pin/8027060, 
operationDescription=,
*/

public class DeleteNodeFromIndexOperationFactory implements MatchableOperationCreator
{
    private final Pattern DELETE_NODE_FROM_INDEX_PATTERN = Pattern.compile( ".*db/data/index/node/[[^/]\\w]*/\\d*$" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws MatchableException
    {
        return entry.getHttpMethod().equals( "DELETE" )
               && DELETE_NODE_FROM_INDEX_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws RequestLogEntryException
    {
        long nodeId = UrlParsingUtils.parseNodeIdFromNodeIndexUrl( entry.getUrl() );
        return new DeleteNodeFromIndexOperation( entry.getTime(), nodeId );
    }

    public static class DeleteNodeFromIndexOperation extends Operation<Object>
    {
        private final long time;
        private final long nodeId;

        private DeleteNodeFromIndexOperation( long time, long nodeId )
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
            return "IndexDeleteNodeOperation [time=" + time + ", nodeId=" + nodeId + "]";
        }
    }
}
