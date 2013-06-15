package com.ldbc.driver.dshini.operations;

import java.util.Map;
import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogEntry;
import com.ldbc.driver.dshini.generator.RequestLogEntryException;
import com.ldbc.driver.dshini.generator.UrlParsingUtils;

/*
httpMethod=PUT, 
url=http://graph.internal.dshini.net:7474/db/data/node/11445682/properties, 
operationDescription="{""ObjectType"":""NeoPin"",""LikeCount"":0,""Message"":""\"Viele Menschen sind gut erzogen, um nicht mit vollem Mund zu sprechen, aber sie haben keine Bedenken, es mit leerem Kopf zu tun.\"\n\nOrson Welles"",""RepinCount"":0,""CreatedAt"":1367250908,""PinIdentifier"":""edc5f911dae036fc36e4eb00b7467347379e2c38"",""CommentsClosed"":false}",  
*/

public class UpdateNodePropertiesOperationFactory implements MatchableOperationCreator
{
    private final Pattern UPDATE_NODE_PATTERN = Pattern.compile( ".*db/data/node/\\d*/properties$" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws MatchableException
    {
        return entry.getHttpMethod().equals( "PUT" ) && UPDATE_NODE_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws RequestLogEntryException
    {
        long nodeId = UrlParsingUtils.parseNodeIdFromNodeUrl( entry.getUrl() );
        Map<String, Object> properties = entry.getDescriptionAsMap();
        return new UpdateNodePropertiesOperation( entry.getTimeNanoSeconds(), nodeId, properties );
    }

    public static class UpdateNodePropertiesOperation extends Operation<Integer>
    {
        private final long nodeId;
        private final Map<String, Object> properties;

        private UpdateNodePropertiesOperation( long time, long nodeId, Map<String, Object> properties )
        {
            super();
            setScheduledStartTimeNanoSeconds( time );
            this.nodeId = nodeId;
            this.properties = properties;
        }

        public long getNodeId()
        {
            return nodeId;
        }

        public Map<String, Object> getProperties()
        {
            return properties;
        }

        @Override
        public String toString()
        {
            return "UpdateNodePropertiesOperation [time=" + getScheduledStartTimeNanoSeconds() + ", nodeId=" + nodeId
                   + ", properties=" + properties + "]";
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) ( nodeId ^ ( nodeId >>> 32 ) );
            result = prime * result + ( ( properties == null ) ? 0 : properties.hashCode() );
            return result;
        }

        @Override
        public boolean equals( Object obj )
        {
            if ( this == obj ) return true;
            if ( obj == null ) return false;
            if ( getClass() != obj.getClass() ) return false;
            UpdateNodePropertiesOperation other = (UpdateNodePropertiesOperation) obj;
            if ( nodeId != other.nodeId ) return false;
            if ( properties == null )
            {
                if ( other.properties != null ) return false;
            }
            else if ( !properties.equals( other.properties ) ) return false;
            return true;
        }
    }
}
