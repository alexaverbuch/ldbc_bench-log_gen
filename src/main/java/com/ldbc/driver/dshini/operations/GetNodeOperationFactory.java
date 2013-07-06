package com.ldbc.driver.dshini.operations;

import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.DshiniLogEntryMatchable;
import com.ldbc.driver.dshini.generator.DshiniLogEntryMatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.log.UrlParsingUtils;
import com.ldbc.driver.util.temporal.Time;

/*
httpMethod=GET, 
url=http://graph.internal.dshini.net:7474/db/data/node/737898, 
operationDescription=null, 
*/

public class GetNodeOperationFactory implements DshiniLogEntryMatchable
{
    private final Pattern GET_NODE_PATTERN = Pattern.compile( ".*db/data/node/\\d*$" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws DshiniLogEntryMatchableException
    {
        return entry.getHttpMethod().equals( "GET" ) && GET_NODE_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws DshiniLogEntryMatchableException
    {
        try
        {
            long nodeId = UrlParsingUtils.parseNodeIdFromNodeUrl( entry.getUrl() );
            return new GetNodeOperation( entry.getTime(), nodeId );
        }
        catch ( RequestLogEntryException e )
        {
            throw new DshiniLogEntryMatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    public static class GetNodeOperation extends Operation<Long>
    {
        private final long nodeId;

        private GetNodeOperation( Time time, long nodeId )
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
            return "GetNodeOperation [time=" + getScheduledStartTime() + ", nodeId=" + nodeId + "]";
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
            GetNodeOperation other = (GetNodeOperation) obj;
            if ( nodeId != other.nodeId ) return false;
            return true;
        }
    }
}
