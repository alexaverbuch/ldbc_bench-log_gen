package com.ldbc.driver.dshini.operations;

import java.util.Map;
import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.Matchable;
import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.utils.UrlParsingUtils;
import com.ldbc.driver.util.temporal.Time;

/*
httpMethod=PUT, 
url=http://graph.internal.dshini.net:7474/db/data/node/11445682/properties, 
operationDescription="{""ObjectType"":""NeoPin"",""LikeCount"":0,""Message"":""\"Viele Menschen sind gut erzogen, um nicht mit vollem Mund zu sprechen, aber sie haben keine Bedenken, es mit leerem Kopf zu tun.\"\n\nOrson Welles"",""RepinCount"":0,""CreatedAt"":1367250908,""PinIdentifier"":""edc5f911dae036fc36e4eb00b7467347379e2c38"",""CommentsClosed"":false}",  
*/

public class UpdateNodePropertiesOperationFactory implements Matchable<RequestLogEntry>
{
    private final Pattern UPDATE_NODE_PATTERN = Pattern.compile( ".*db/data/node/\\d*/properties$" );

    @Override
    public boolean matches( RequestLogEntry entry )
    {
        return entry.getHttpMethod().equals( "PUT" ) && UPDATE_NODE_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createOperationFrom( RequestLogEntry entry ) throws MatchableException
    {
        try
        {
            long nodeId = UrlParsingUtils.parseNodeIdFromNodeUrl( entry.getUrl() );
            Map<String, Object> properties = entry.getDescriptionAsMap();
            return new UpdateNodePropertiesOperation( entry.getTime(), nodeId, properties );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    @Override
    public Class<? extends Operation<?>> operationType()
    {
        return UpdateNodePropertiesOperation.class;
    }

    public static class UpdateNodePropertiesOperation extends Operation<Integer>
    {
        private final long nodeId;
        private final Map<String, Object> properties;

        private UpdateNodePropertiesOperation( Time time, long nodeId, Map<String, Object> properties )
        {
            super();
            setScheduledStartTime( time );
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
            return "UpdateNodePropertiesOperation [time=" + getScheduledStartTime() + ", nodeId=" + nodeId
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
