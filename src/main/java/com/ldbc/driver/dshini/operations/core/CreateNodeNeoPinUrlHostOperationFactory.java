package com.ldbc.driver.dshini.operations.core;

import java.util.Map;
import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.operations.AbstractNodeWriteOperationFactory;
import com.ldbc.driver.dshini.utils.UrlPatterns;
import com.ldbc.driver.util.temporal.Time;

/*
url=http://graph-master.dshini.net:7474/db/data/node, 
 */

// NOTE number of properties may vary
public class CreateNodeNeoPinUrlHostOperationFactory extends AbstractNodeWriteOperationFactory
{
    @Override
    protected String getExpectedType()
    {
        return "NeoPinUrlHost";
    }

    @Override
    protected String getExpectedHttpMethod()
    {
        return "POST";
    }

    @Override
    protected Pattern getExpectedUrlPattern()
    {
        return UrlPatterns.CREATE_NODE;
    }

    @Override
    public Operation<?> createOperationFrom( RequestLogEntry entry ) throws MatchableException
    {
        try
        {
            return new CreateNodeNeoPinUrlHostOperation( entry.getTime(), entry.getDescriptionAsMap() );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    @Override
    public Class<? extends Operation<?>> operationType()
    {
        return CreateNodeNeoPinUrlHostOperation.class;
    }

    public static class CreateNodeNeoPinUrlHostOperation extends Operation<Integer>
    {
        private final Map<String, Object> properties;

        private CreateNodeNeoPinUrlHostOperation( Time time, Map<String, Object> properties )
        {
            super();
            setScheduledStartTime( time );
            this.properties = properties;
        }

        public Map<String, Object> getProperties()
        {
            return properties;
        }

        @Override
        public String toString()
        {
            return getClass().getSimpleName() + " [time=" + getScheduledStartTime() + ", properties=" + properties
                   + "]";
        }
    }
}
