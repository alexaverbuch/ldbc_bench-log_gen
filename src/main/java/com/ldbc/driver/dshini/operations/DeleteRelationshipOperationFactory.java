package com.ldbc.driver.dshini.operations;

import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogEntry;
import com.ldbc.driver.dshini.generator.RequestLogEntryException;
import com.ldbc.driver.dshini.generator.UrlParsingUtils;

/*
httpMethod=DELETE, 
url=http://graph-master.dshini.net:7474/db/data/relationship/883198, 
operationDescription=, 
*/

public class DeleteRelationshipOperationFactory implements MatchableOperationCreator
{
    private final Pattern DELETE_RELATIONSHIP_PATTERN = Pattern.compile( ".*db/data/relationship/\\d*$" );

    @Override
    public boolean matches( RequestLogEntry entry ) throws MatchableException
    {
        return entry.getHttpMethod().equals( "DELETE" )
               && DELETE_RELATIONSHIP_PATTERN.matcher( entry.getUrl() ).matches();
    }

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws RequestLogEntryException
    {
        long relationshipId = UrlParsingUtils.parseRelationshipIdFromRelationshipUrl( entry.getUrl() );
        return new DeleteRelationshipOperation( entry.getTimeNanoSeconds(), relationshipId );
    }

    public static class DeleteRelationshipOperation extends Operation<Integer>
    {
        private final long relationshipId;

        private DeleteRelationshipOperation( long time, long relationshipId )
        {
            super();
            setScheduledStartTimeNanoSeconds( time );
            this.relationshipId = relationshipId;
        }

        public long getRelationshipId()
        {
            return relationshipId;
        }

        @Override
        public String toString()
        {
            return "DeleteRelationshipOperation [time=" + getScheduledStartTimeNanoSeconds() + ", relationshipId="
                   + relationshipId + "]";
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
            DeleteRelationshipOperation other = (DeleteRelationshipOperation) obj;
            if ( relationshipId != other.relationshipId ) return false;
            return true;
        }
    }
}
