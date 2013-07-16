package com.ldbc.driver.dshini.operations;

import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.Matchable;
import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.utils.UrlParsingUtils;
import com.ldbc.driver.util.temporal.Time;

public class GetRelationshipOperationFactory implements Matchable<RequestLogEntry>
{
    private final Pattern GET_RELATIONSHIP_PATTERN = Pattern.compile( ".*db/data/relationship/\\d*$" );

    @Override
    public boolean matches( RequestLogEntry entry )
    {
        return entry.getHttpMethod().equals( "GET" ) && GET_RELATIONSHIP_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createOperationFrom( RequestLogEntry entry ) throws MatchableException
    {
        try
        {
            long relationshipId = UrlParsingUtils.parseRelationshipIdFromRelationshipUrl( entry.getUrl() );
            return new GetRelationshipOperation( entry.getTime(), relationshipId );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    @Override
    public Class<? extends Operation<?>> operationType()
    {
        return GetRelationshipOperation.class;
    }

    public static class GetRelationshipOperation extends Operation<Long>
    {
        private final long relationshipId;

        private GetRelationshipOperation( Time time, long relationshipId )
        {
            super();
            setScheduledStartTime( time );
            this.relationshipId = relationshipId;
        }

        public long getRelationshipId()
        {
            return relationshipId;
        }

        @Override
        public String toString()
        {
            return "GetRelationshipOperation [time=" + getScheduledStartTime() + ", relationshipId=" + relationshipId
                   + "]";
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) ( relationshipId ^ ( relationshipId >>> 32 ) );
            return result;
        }

        @Override
        public boolean equals( Object obj )
        {
            if ( this == obj ) return true;
            if ( obj == null ) return false;
            if ( getClass() != obj.getClass() ) return false;
            GetRelationshipOperation other = (GetRelationshipOperation) obj;
            if ( relationshipId != other.relationshipId ) return false;
            return true;
        }
    }
}
