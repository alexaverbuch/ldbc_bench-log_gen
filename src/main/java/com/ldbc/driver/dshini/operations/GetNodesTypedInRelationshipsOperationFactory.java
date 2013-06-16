package com.ldbc.driver.dshini.operations;

import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.DshiniLogEntryMatchable;
import com.ldbc.driver.dshini.generator.DshiniLogEntryMatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.log.UrlParsingUtils;

/*
httpMethod=GET, 
url=http://graph.internal.dshini.net:7474/db/data/node/11251602/relationships/in/CLAIMS, 
operationDescription=null,
*/

public class GetNodesTypedInRelationshipsOperationFactory implements DshiniLogEntryMatchable
{
    private final Pattern GET_NODE_TYPED_IN_RELATIONSHIPS_PATTERN = Pattern.compile( ".*db/data/node/\\d*/relationships/in/[&\\w]*$" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws DshiniLogEntryMatchableException
    {
        return entry.getHttpMethod().equals( "GET" )
               && GET_NODE_TYPED_IN_RELATIONSHIPS_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws DshiniLogEntryMatchableException
    {
        try
        {
            long nodeId = UrlParsingUtils.parseNodeIdFromNodeRelationshipsUrl( entry.getUrl() );
            String relationshipType = UrlParsingUtils.parseRelationshipTypeFromNodeRelationshipsUrl( entry.getUrl() );
            return new GetNodeTypedInRelationshipsOperation( entry.getTimeNanoSeconds(), nodeId, relationshipType );
        }
        catch ( RequestLogEntryException e )
        {
            throw new DshiniLogEntryMatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    public static class GetNodeTypedInRelationshipsOperation extends Operation<Integer>
    {
        private final long nodeId;
        private final String relationshipType;

        private GetNodeTypedInRelationshipsOperation( long time, long nodeId, String relationshipType )
        {
            super();
            setScheduledStartTimeNanoSeconds( time );
            this.nodeId = nodeId;
            this.relationshipType = relationshipType;
        }

        public long getNodeId()
        {
            return nodeId;
        }

        public String getRelationshipType()
        {
            return relationshipType;
        }

        @Override
        public String toString()
        {
            return "GetNodeTypedInRelationshipsOperation [time=" + getScheduledStartTimeNanoSeconds() + ", nodeId="
                   + nodeId + ", relationshipType=" + relationshipType + "]";
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) ( nodeId ^ ( nodeId >>> 32 ) );
            result = prime * result + ( ( relationshipType == null ) ? 0 : relationshipType.hashCode() );
            return result;
        }

        @Override
        public boolean equals( Object obj )
        {
            if ( this == obj ) return true;
            if ( obj == null ) return false;
            if ( getClass() != obj.getClass() ) return false;
            GetNodeTypedInRelationshipsOperation other = (GetNodeTypedInRelationshipsOperation) obj;
            if ( nodeId != other.nodeId ) return false;
            if ( relationshipType == null )
            {
                if ( other.relationshipType != null ) return false;
            }
            else if ( !relationshipType.equals( other.relationshipType ) ) return false;
            return true;
        }
    }
}
