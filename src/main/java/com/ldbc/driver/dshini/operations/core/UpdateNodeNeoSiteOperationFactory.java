package com.ldbc.driver.dshini.operations.core;

import java.util.Map;
import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.operations.AbstractNodeWriteOperationFactory;
import com.ldbc.driver.dshini.utils.UrlParsingUtils;
import com.ldbc.driver.dshini.utils.UrlPatterns;
import com.ldbc.driver.util.temporal.Time;

/*
url=http://graph.internal.dshini.net:7474/db/data/node/11445682/properties,
*/

//NOTE number of properties may vary
public class UpdateNodeNeoSiteOperationFactory extends AbstractNodeWriteOperationFactory
{
    @Override
    protected String getExpectedType()
    {
        return "NeoSite";
    }

    @Override
    protected final String getExpectedHttpMethod()
    {
        return "PUT";
    }

    @Override
    protected Pattern getExpectedUrlPattern()
    {
        return UrlPatterns.UPDATE_NODE;
    }

    @Override
    public Operation<?> createOperationFrom( RequestLogEntry entry ) throws MatchableException
    {
        try
        {
            long nodeId = UrlParsingUtils.parseNodeIdFromNodeUrl( entry.getUrl() );
            Map<String, Object> properties = entry.getDescriptionAsMap();
            return new UpdateNodeNeoSiteOperation( entry.getTime(), nodeId, properties );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    @Override
    public Class<? extends Operation<?>> operationType()
    {
        return UpdateNodeNeoSiteOperation.class;
    }

    public static class UpdateNodeNeoSiteOperation extends Operation<Integer>
    {
        private final long nodeId;
        private final Map<String, Object> properties;

        private UpdateNodeNeoSiteOperation( Time time, long nodeId, Map<String, Object> properties )
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
            return getClass().getSimpleName() + " [time=" + getScheduledStartTime() + ", nodeId=" + nodeId
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
            UpdateNodeNeoSiteOperation other = (UpdateNodeNeoSiteOperation) obj;
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
