package com.ldbc.driver.dshini.operations;

import java.util.Map;
import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.Matchable;
import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.util.temporal.Time;

/*
httpMethod=POST, 
url=http://graph-master.dshini.net:7474/db/data/node, 
operationDescription="{""UrlHash"":""13db037cd6ed7ca73582cc06ee41cb4c7e18b937"",""Url"":""http:\/\/www.facebook.com\/photo.php?fbid=620155461334520&set=a.161214197228651.43772.161211400562264&type=1&permPage=1"",""ObjectType"":""NeoPinUrl"",""CreatedAt"":1367242260}"
 */

public class CreateNodeOperationFactory implements Matchable<RequestLogEntry>
{
    private final Pattern CREATE_NODE_PATTERN = Pattern.compile( ".*db/data/node$" );

    @Override
    public boolean matches( RequestLogEntry entry )
    {
        return entry.getHttpMethod().equals( "POST" ) && CREATE_NODE_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createOperationFrom( RequestLogEntry entry ) throws MatchableException
    {
        try
        {
            return new CreateNodeOperation( entry.getTime(), entry.getDescriptionAsMap() );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    @Override
    public Class<? extends Operation<?>> operationType()
    {
        return CreateNodeOperation.class;
    }

    public static class CreateNodeOperation extends Operation<Integer>
    {
        private final Map<String, Object> properties;

        private CreateNodeOperation( Time time, Map<String, Object> properties )
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
            return "CreateNodeOperation [time=" + getScheduledStartTime() + ", properties=" + properties + "]";
        }
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( CREATE_NODE_PATTERN == null ) ? 0 : CREATE_NODE_PATTERN.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        CreateNodeOperationFactory other = (CreateNodeOperationFactory) obj;
        if ( CREATE_NODE_PATTERN == null )
        {
            if ( other.CREATE_NODE_PATTERN != null ) return false;
        }
        else if ( !CREATE_NODE_PATTERN.equals( other.CREATE_NODE_PATTERN ) ) return false;
        return true;
    }
}
