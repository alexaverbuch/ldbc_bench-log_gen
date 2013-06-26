package com.ldbc.driver.dshini.operations;

import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.DshiniLogEntryMatchable;
import com.ldbc.driver.dshini.generator.DshiniLogEntryMatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.log.UrlParsingUtils;
import com.ldbc.driver.util.Time;

/*
httpMethod=GET, 
url=http://graph.internal.dshini.net:7474/db/data/node/11442041/relationships/all, 
operationDescription=null, 
*/

public class GetNodesRelationshipsOperationFactory implements DshiniLogEntryMatchable
{
    private final Pattern GET_NODE_RELATIONSHIPS_PATTERN = Pattern.compile( ".*db/data/node/\\d*/relationships/all$" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws DshiniLogEntryMatchableException
    {
        return entry.getHttpMethod().equals( "GET" )
               && GET_NODE_RELATIONSHIPS_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws DshiniLogEntryMatchableException
    {
        try
        {
            long nodeId = UrlParsingUtils.parseNodeIdFromNodeRelationshipsUrl( entry.getUrl() );
            return new GetNodeRelationshipsOperation( entry.getTimeNanoSeconds(), nodeId );
        }
        catch ( RequestLogEntryException e )
        {
            throw new DshiniLogEntryMatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    public static class GetNodeRelationshipsOperation extends Operation<Integer>
    {
        private final long nodeId;

        private GetNodeRelationshipsOperation( long time, long nodeId )
        {
            super();
            setScheduledStartTime( Time.fromNano( time ) );
            this.nodeId = nodeId;
        }

        public long getNodeId()
        {
            return nodeId;
        }

        @Override
        public String toString()
        {
            return "GetNodeRelationshipsOperation [time=" + getScheduledStartTime() + ", nodeId=" + nodeId + "]";
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
            GetNodeRelationshipsOperation other = (GetNodeRelationshipsOperation) obj;
            if ( nodeId != other.nodeId ) return false;
            return true;
        }
    }
}
