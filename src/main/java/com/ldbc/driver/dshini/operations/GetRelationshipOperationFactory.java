package com.ldbc.driver.dshini.operations;

import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogEntry;
import com.ldbc.driver.dshini.generator.RequestLogEntryException;
import com.ldbc.driver.dshini.generator.UrlParsingUtils;

public class GetRelationshipOperationFactory implements MatchableOperationCreator
{
    private final Pattern GET_RELATIONSHIP_PATTERN = Pattern.compile( ".*db/data/relationship/\\d*$" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws MatchableException
    {
        return entry.getHttpMethod().equals( "GET" ) && GET_RELATIONSHIP_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws RequestLogEntryException
    {
        long relationshipId = UrlParsingUtils.parseRelationshipIdFromRelationshipUrl( entry.getUrl() );
        return new GetRelationshipOperation( entry.getTime(), relationshipId );
    }

    public class GetRelationshipOperation extends Operation<Object>
    {
        private final long time;
        private final long relationshipId;

        private GetRelationshipOperation( long time, long relationshipId )
        {
            super();
            this.time = time;
            this.relationshipId = relationshipId;
        }

        public long getTime()
        {
            return time;
        }

        public long getRelationshipId()
        {
            return relationshipId;
        }

        @Override
        public String toString()
        {
            return "GetRelationshipOperation [time=" + time + ", relationshipId=" + relationshipId + "]";
        }
    }
}
