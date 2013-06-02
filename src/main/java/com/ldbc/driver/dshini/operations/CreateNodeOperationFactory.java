package com.ldbc.driver.dshini.operations;

import java.util.Map;
import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogEntry;
import com.ldbc.driver.dshini.generator.RequestLogEntryException;
import com.ldbc.driver.dshini.generator.UrlParsingUtils;

/*
httpMethod=POST, 
url=http://graph-master.dshini.net:7474/db/data/node, 
operationDescription="{""UrlHash"":""13db037cd6ed7ca73582cc06ee41cb4c7e18b937"",""Url"":""http:\/\/www.facebook.com\/photo.php?fbid=620155461334520&set=a.161214197228651.43772.161211400562264&type=1&permPage=1"",""ObjectType"":""NeoPinUrl"",""CreatedAt"":1367242260}"
 */

public class CreateNodeOperationFactory implements MatchableOperationCreator
{
    private final Pattern CREATE_NODE_PATTERN = Pattern.compile( ".*db/data/node$" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws MatchableException
    {
        return entry.getHttpMethod().equals( "POST" ) && CREATE_NODE_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws RequestLogEntryException
    {
        return new CreateNodeOperation( entry.getTime(), entry.getDescriptionAsMap() );
    }

    public class CreateNodeOperation extends Operation<Object>
    {
        private final long time;
        private final Map<String, Object> properties;

        private CreateNodeOperation( long time, Map<String, Object> properties )
        {
            super();
            this.time = time;
            this.properties = properties;
        }

        public long getTime()
        {
            return time;
        }

        public Map<String, Object> getProperties()
        {
            return properties;
        }

        @Override
        public String toString()
        {
            return "CreateNodeOperation [time=" + time + ", properties=" + properties + "]";
        }
    }
}
