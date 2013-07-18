package com.ldbc.driver.dshini.operations.core;

import java.util.Map;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.operations.AbstractRelationshipCreateOperationFactory;
import com.ldbc.driver.dshini.utils.UrlParsingUtils;
import com.ldbc.driver.util.temporal.Time;

/*
url=http://graph-master.dshini.net:7474/db/data/node/11440883/relationships, 
*/

//NOTE number of properties may vary
public class CreateRelationshipPinsAssetOperationFactory extends AbstractRelationshipCreateOperationFactory
{
    @Override
    protected String getExpectedType()
    {
        return "PINS_ASSET";
    }

    @Override
    public Operation<?> createOperationFrom( RequestLogEntry entry ) throws MatchableException
    {
        try
        {
            long startNodeId = UrlParsingUtils.parseNodeIdFromNodeRelationshipsUrl( entry.getUrl() );
            Map<String, Object> map = entry.getDescriptionAsMap();
            long endNodeId = UrlParsingUtils.parseNodeIdFromNodeUrl( (String) map.get( "to" ) );
            String relationshipType = (String) map.get( "type" );
            Map<String, Object> properties = (Map<String, Object>) map.get( "data" );
            return new CreateRelationshipPinsAssetOperation( entry.getTime(), startNodeId, endNodeId, relationshipType,
                    properties );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    @Override
    public Class<? extends Operation<?>> operationType()
    {
        return CreateRelationshipPinsAssetOperation.class;
    }

    public static class CreateRelationshipPinsAssetOperation extends Operation<Integer>
    {
        private final long startNodeId;
        private final long endNodeId;
        private final String relationshipType;
        private final Map<String, Object> properties;

        private CreateRelationshipPinsAssetOperation( Time time, long startNodeId, long endNodeId,
                String relationshipType, Map<String, Object> properties )
        {
            super();
            setScheduledStartTime( time );
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
            return getClass().getSimpleName() + " [time=" + getScheduledStartTime() + ", startNodeId=" + startNodeId
                   + ", endNodeId=" + endNodeId + ", relationshipType=" + relationshipType + ", properties="
                   + properties + "]";
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
            CreateRelationshipPinsAssetOperation other = (CreateRelationshipPinsAssetOperation) obj;
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
