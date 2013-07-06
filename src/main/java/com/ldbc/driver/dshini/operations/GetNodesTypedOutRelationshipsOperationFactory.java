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
url=http://graph.internal.dshini.net:7474/db/data/node/11440830/relationships/out/IS_SPOTLIGHT, 
operationDescription=null,  
*/

public class GetNodesTypedOutRelationshipsOperationFactory implements DshiniLogEntryMatchable
{
    private final Pattern GET_NODE_TYPED_OUT_RELATIONSHIPS_PATTERN = Pattern.compile( ".*db/data/node/\\d*/relationships/out/[&\\w]*$" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws DshiniLogEntryMatchableException
    {
        return entry.getHttpMethod().equals( "GET" )
               && GET_NODE_TYPED_OUT_RELATIONSHIPS_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws DshiniLogEntryMatchableException
    {
        try
        {
            long nodeId = UrlParsingUtils.parseNodeIdFromNodeRelationshipsUrl( entry.getUrl() );
            String relationshipType = UrlParsingUtils.parseRelationshipTypeFromNodeRelationshipsUrl( entry.getUrl() );
            return new GetNodeTypedOutRelationshipsOperation( entry.getTime(), nodeId, relationshipType );
        }
        catch ( RequestLogEntryException e )
        {
            throw new DshiniLogEntryMatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    public static class GetNodeTypedOutRelationshipsOperation extends Operation<Integer>
    {
        private final long nodeId;
        private final String relationshipType;

        private GetNodeTypedOutRelationshipsOperation( Time time, long nodeId, String relationshipType )
        {
            super();
            setScheduledStartTime( time );
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
            return "GetNodeTypedOutRelationshipsOperation [time=" + getScheduledStartTime() + ", nodeId=" + nodeId
                   + ", relationshipType=" + relationshipType + "]";
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
            GetNodeTypedOutRelationshipsOperation other = (GetNodeTypedOutRelationshipsOperation) obj;
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
