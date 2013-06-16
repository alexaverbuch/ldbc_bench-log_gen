package com.ldbc.driver.dshini.operations;

import java.util.Map;
import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.DshiniLogEntryMatchable;
import com.ldbc.driver.dshini.generator.DshiniLogEntryMatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.log.UrlParsingUtils;

/*
httpMethod=POST, 
url=http://graph-master.dshini.net:7474/db/data/node/11440883/relationships, 
operationDescription="{""to"":""http:\/\/graph.internal.dshini.net:7474\/db\/data\/node\/1526800"",""type"":""PINS"",""data"":null}"
*/

public class CreateRelationshipOperationFactory implements DshiniLogEntryMatchable
{
    private final Pattern CREATE_RELATIONSHIP_PATTERN = Pattern.compile( ".*db/data/node/\\d*/relationships" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws DshiniLogEntryMatchableException
    {
        return entry.getHttpMethod().equals( "POST" )
               && CREATE_RELATIONSHIP_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws DshiniLogEntryMatchableException
    {
        try
        {
            long startNodeId = UrlParsingUtils.parseNodeIdFromNodeRelationshipsUrl( entry.getUrl() );
            Map<String, Object> map = entry.getDescriptionAsMap();
            long endNodeId = UrlParsingUtils.parseNodeIdFromNodeUrl( (String) map.get( "to" ) );
            String relationshipType = (String) map.get( "type" );
            Map<String, Object> properties = (Map<String, Object>) map.get( "data" );
            return new CreateRelationshipOperation( entry.getTimeNanoSeconds(), startNodeId, endNodeId,
                    relationshipType, properties );
        }
        catch ( RequestLogEntryException e )
        {
            throw new DshiniLogEntryMatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    public static class CreateRelationshipOperation extends Operation<Integer>
    {
        private final long startNodeId;
        private final long endNodeId;
        private final String relationshipType;
        private final Map<String, Object> properties;

        private CreateRelationshipOperation( long timeNanoSeconds, long startNodeId, long endNodeId,
                String relationshipType, Map<String, Object> properties )
        {
            super();
            setScheduledStartTimeNanoSeconds( timeNanoSeconds );
            this.startNodeId = startNodeId;
            this.endNodeId = endNodeId;
            this.relationshipType = relationshipType;
            this.properties = properties;
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
            return "CreateRelationshipOperation [time=" + getScheduledStartTimeNanoSeconds() + ", startNodeId="
                   + startNodeId + ", endNodeId=" + endNodeId + ", relationshipType=" + relationshipType
                   + ", properties=" + properties + "]";
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) ( endNodeId ^ ( endNodeId >>> 32 ) );
            result = prime * result + ( ( properties == null ) ? 0 : properties.hashCode() );
            result = prime * result + ( ( relationshipType == null ) ? 0 : relationshipType.hashCode() );
            result = prime * result + (int) ( startNodeId ^ ( startNodeId >>> 32 ) );
            return result;
        }

        @Override
        public boolean equals( Object obj )
        {
            if ( this == obj ) return true;
            if ( obj == null ) return false;
            if ( getClass() != obj.getClass() ) return false;
            CreateRelationshipOperation other = (CreateRelationshipOperation) obj;
            if ( endNodeId != other.endNodeId ) return false;
            if ( properties == null )
            {
                if ( other.properties != null ) return false;
            }
            else if ( !properties.equals( other.properties ) ) return false;
            if ( relationshipType == null )
            {
                if ( other.relationshipType != null ) return false;
            }
            else if ( !relationshipType.equals( other.relationshipType ) ) return false;
            if ( startNodeId != other.startNodeId ) return false;
            return true;
        }
    }
}
