package com.ldbc.driver.dshini.operations.core;

import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.operations.AbstractRelationshipReadOperationFactory;
import com.ldbc.driver.dshini.utils.UrlParsingUtils;
import com.ldbc.driver.dshini.utils.UrlPatterns;
import com.ldbc.driver.util.temporal.Time;

/*
httpMethod=GET, 
url=http://graph.internal.dshini.net:7474/db/data/node/11251602/relationships/in/CLAIMS, 
operationDescription=null,
*/

public class GetNodesIncomingClaimsRelationshipsOperationFactory extends AbstractRelationshipReadOperationFactory
{
    @Override
    protected String getExpectedType()
    {
        return "CLAIMS";
    }

    @Override
    protected String getExpectedHttpMethod()
    {
        return "GET";
    }

    @Override
    protected Pattern getExpectedUrlPattern()
    {
        return UrlPatterns.GET_NODE_TYPED_IN_RELATIONSHIPS;
    }

    @Override
    public Operation<?> createOperationFrom( RequestLogEntry entry ) throws MatchableException
    {
        try
        {
            long nodeId = UrlParsingUtils.parseNodeIdFromNodeRelationshipsUrl( entry.getUrl() );
            String relationshipType = UrlParsingUtils.parseRelationshipTypeFromNodeRelationshipsUrl( entry.getUrl() );
            return new GetNodesIncomingClaimsRelationshipsOperation( entry.getTime(), nodeId, relationshipType );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    @Override
    public Class<? extends Operation<?>> operationType()
    {
        return GetNodesIncomingClaimsRelationshipsOperation.class;
    }

    public static class GetNodesIncomingClaimsRelationshipsOperation extends Operation<Integer>
    {
        private final long nodeId;
        private final String relationshipType;

        private GetNodesIncomingClaimsRelationshipsOperation( Time time, long nodeId, String relationshipType )
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
            return getClass().getSimpleName() + " [time=" + getScheduledStartTime() + ", nodeId=" + nodeId
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
            GetNodesIncomingClaimsRelationshipsOperation other = (GetNodesIncomingClaimsRelationshipsOperation) obj;
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
