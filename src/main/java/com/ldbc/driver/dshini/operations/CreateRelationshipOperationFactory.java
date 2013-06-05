package com.ldbc.driver.dshini.operations;

import java.util.Map;
import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogEntry;
import com.ldbc.driver.dshini.generator.RequestLogEntryException;
import com.ldbc.driver.dshini.generator.UrlParsingUtils;

/*
httpMethod=POST, 
url=http://graph-master.dshini.net:7474/db/data/node/11440883/relationships, 
operationDescription="{""to"":""http:\/\/graph.internal.dshini.net:7474\/db\/data\/node\/1526800"",""type"":""PINS"",""data"":null}"
*/

public class CreateRelationshipOperationFactory implements MatchableOperationCreator
{
    private final Pattern CREATE_RELATIONSHIP_PATTERN = Pattern.compile( ".*db/data/node/\\d*/relationships" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws MatchableException
    {
        return entry.getHttpMethod().equals( "POST" )
               && CREATE_RELATIONSHIP_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws RequestLogEntryException
    {
        long startNodeId = UrlParsingUtils.parseNodeIdFromNodeRelationshipsUrl( entry.getUrl() );
        Map<String, Object> map = entry.getDescriptionAsMap();
        long endNodeId = UrlParsingUtils.parseNodeIdFromNodeUrl( (String) map.get( "to" ) );
        String relationshipType = (String) map.get( "type" );
        Map<String, Object> properties = (Map<String, Object>) map.get( "data" );
        return new CreateRelationshipOperation( entry.getTime(), startNodeId, endNodeId, relationshipType, properties );
    }

    public class CreateRelationshipOperation extends Operation<Long>
    {
        private final long time;
        private final long startNodeId;
        private final long endNodeId;
        private final String relationshipType;
        private final Map<String, Object> properties;

        private CreateRelationshipOperation( long time, long startNodeId, long endNodeId, String relationshipType,
                Map<String, Object> properties )
        {
            super();
            this.time = time;
            this.startNodeId = startNodeId;
            this.endNodeId = endNodeId;
            this.relationshipType = relationshipType;
            this.properties = properties;
        }

        public long getTime()
        {
            return time;
        }

        public long getStartNodeId()
        {
            return startNodeId;
        }

        public long getEndNodeId()
        {
            return endNodeId;
        }

        public String getRelationshipType()
        {
            return relationshipType;
        }

        public Map<String, Object> getProperties()
        {
            return properties;
        }

        @Override
        public String toString()
        {
            return "CreateRelationshipOperation [time=" + time + ", startNodeId=" + startNodeId + ", endNodeId="
                   + endNodeId + ", relationshipType=" + relationshipType + ", properties=" + properties + "]";
        }
    }
}
