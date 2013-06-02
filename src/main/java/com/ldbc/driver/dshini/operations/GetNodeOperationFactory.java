package com.ldbc.driver.dshini.operations;

import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogEntry;
import com.ldbc.driver.dshini.generator.RequestLogEntryException;
import com.ldbc.driver.dshini.generator.UrlParsingUtils;

/*
httpMethod=GET, 
url=http://graph.internal.dshini.net:7474/db/data/node/737898, 
operationDescription=null, 
*/

public class GetNodeOperationFactory implements MatchableOperationCreator
{
    private final Pattern GET_NODE_PATTERN = Pattern.compile( ".*db/data/node/\\d*$" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws MatchableException
    {
        return entry.getHttpMethod().equals( "GET" ) && GET_NODE_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws RequestLogEntryException
    {
        long nodeId = UrlParsingUtils.parseNodeIdFromNodeUrl( entry.getUrl() );
        return new GetNodeOperation( entry.getTime(), nodeId );
    }

    public class GetNodeOperation extends Operation<Object>
    {
        private final long time;
        private final long nodeId;

        private GetNodeOperation( long time, long nodeId )
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
            return "GetNodeOperation [time=" + time + ", nodeId=" + nodeId + "]";
        }
    }
}
