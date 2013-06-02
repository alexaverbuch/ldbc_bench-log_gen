package com.ldbc.driver.dshini.operations;

import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogEntry;
import com.ldbc.driver.dshini.generator.RequestLogEntryException;
import com.ldbc.driver.dshini.generator.UrlParsingUtils;

/*
httpMethod=GET, 
url=http://graph.internal.dshini.net:7474/db/data/node/11251602/relationships/in/CLAIMS, 
operationDescription=null,
*/

public class GetNodesTypedInRelationshipsOperationFactory implements MatchableOperationCreator
{
    private final Pattern GET_NODE_TYPED_IN_RELATIONSHIPS_PATTERN = Pattern.compile( ".*db/data/node/\\d*/relationships/in/[&\\w]*$" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws MatchableException
    {
        return entry.getHttpMethod().equals( "GET" )
               && GET_NODE_TYPED_IN_RELATIONSHIPS_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws RequestLogEntryException
    {
        long nodeId = UrlParsingUtils.parseNodeIdFromNodeRelationshipsUrl( entry.getUrl() );
        String relationshipType = UrlParsingUtils.parseRelationshipTypeFromNodeRelationshipsUrl( entry.getUrl() );
        return new GetNodeTypedInRelationshipsOperation( entry.getTime(), nodeId, relationshipType );
    }

    public class GetNodeTypedInRelationshipsOperation extends Operation<Object>
    {
        private final long time;
        private final long nodeId;
        private final String relationshipType;

        private GetNodeTypedInRelationshipsOperation( long time, long nodeId, String relationshipType )
        {
            super();
            this.time = time;
            this.nodeId = nodeId;
            this.relationshipType = relationshipType;
        }

        public long getTime()
        {
            return time;
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
            return "GetNodeTypedInRelationshipsOperation [time=" + time + ", nodeId=" + nodeId + ", relationshipType="
                   + relationshipType + "]";
        }
    }
}
