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
httpMethod=GET, 
url=http://graph-master.dshini.net:7474/db/data/node/11440956/relationships/out, 
operationDescription=null,
*/
public class GetNodesOutRelationshipsOperationFactory implements Matchable<RequestLogEntry>
{
    private final Pattern GET_NODE_OUT_RELATIONSHIPS_PATTERN = Pattern.compile( ".*db/data/node/\\d*/relationships/out$" );

    @Override
    public boolean matches( RequestLogEntry entry )
    {
        return entry.getHttpMethod().equals( "GET" )
               && GET_NODE_OUT_RELATIONSHIPS_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createOperationFrom( RequestLogEntry entry ) throws MatchableException
    {
        try
        {
            long nodeId = UrlParsingUtils.parseNodeIdFromNodeRelationshipsUrl( entry.getUrl() );
            return new GetNodeOutRelationshipsOperation( entry.getTime(), nodeId );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    @Override
    public Class<? extends Operation<?>> operationType()
    {
        return GetNodeOutRelationshipsOperation.class;
    }

    public static class GetNodeOutRelationshipsOperation extends Operation<Integer>
    {
        private final long nodeId;

        private GetNodeOutRelationshipsOperation( Time time, long nodeId )
        {
            super();
            setScheduledStartTime( time );
            this.nodeId = nodeId;
        }

        public long getNodeId()
        {
            return nodeId;
        }

        @Override
        public String toString()
        {
            return "GetNodeOutRelationshipsOperation [time=" + getScheduledStartTime() + ", nodeId=" + nodeId + "]";
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
