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
        return new GetNodeOutRelationshipsOperation( entry.getTimeNanoSeconds(), nodeId );
    }

    public static class GetNodeOutRelationshipsOperation extends Operation<Integer>
    {
        private final long nodeId;

        private GetNodeOutRelationshipsOperation( long time, long nodeId )
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
            return "GetNodeOutRelationshipsOperation [time=" + getScheduledStartTimeNanoSeconds() + ", nodeId="
                   + nodeId + "]";
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
            GetNodeOutRelationshipsOperation other = (GetNodeOutRelationshipsOperation) obj;
            if ( nodeId != other.nodeId ) return false;
            return true;
        }
    }
}
