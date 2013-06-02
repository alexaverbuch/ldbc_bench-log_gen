package com.ldbc.driver.dshini.operations;

import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogEntry;
import com.ldbc.driver.dshini.generator.RequestLogEntryException;
import com.ldbc.driver.dshini.generator.UrlParsingUtils;

/*
httpMethod=GET, 
url=http://graph-master.dshini.net:7474/db/data/node/11440956/relationships/out, 
operationDescription=null,
*/
public class GetNodesOutRelationshipsOperationFactory implements MatchableOperationCreator
{
    private final Pattern GET_NODE_OUT_RELATIONSHIPS_PATTERN = Pattern.compile( ".*db/data/node/\\d*/relationships/out$" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws MatchableException
    {
        return entry.getHttpMethod().equals( "GET" )
               && GET_NODE_OUT_RELATIONSHIPS_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws RequestLogEntryException
    {
        long nodeId = UrlParsingUtils.parseNodeIdFromNodeRelationshipsUrl( entry.getUrl() );
        return new GetNodeOutRelationshipsOperation( entry.getTime(), nodeId );
    }

    public class GetNodeOutRelationshipsOperation extends Operation<Object>
    {
        private final long time;
        private final long nodeId;

        private GetNodeOutRelationshipsOperation( long time, long nodeId )
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
            return "GetNodeOutRelationshipsOperation [time=" + time + ", nodeId=" + nodeId + "]";
        }
    }
}
