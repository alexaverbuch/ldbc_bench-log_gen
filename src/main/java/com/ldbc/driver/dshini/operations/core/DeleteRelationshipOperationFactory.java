package com.ldbc.driver.dshini.operations.core;

import java.util.regex.Pattern;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.operations.AbstractOperationFactory;
import com.ldbc.driver.dshini.utils.UrlParsingUtils;
import com.ldbc.driver.dshini.utils.UrlPatterns;
import com.ldbc.driver.util.temporal.Time;

/*
httpMethod=DELETE, 
url=http://graph-master.dshini.net:7474/db/data/relationship/883198, 
operationDescription=, 
*/

public class DeleteRelationshipOperationFactory extends AbstractOperationFactory
{
    @Override
    protected String getExpectedHttpMethod()
    {
        return "DELETE";
    }

    @Override
    protected Pattern getExpectedUrlPattern()
    {
        return UrlPatterns.DELETE_RELATIONSHIP;
    }

    @Override
    public DeleteRelationshipOperation createOperationFrom( RequestLogEntry entry ) throws MatchableException
    {
        try
        {
            long relationshipId = UrlParsingUtils.parseRelationshipIdFromRelationshipUrl( entry.getUrl() );
            return new DeleteRelationshipOperation( entry.getTime(), relationshipId );
        }
        catch ( RequestLogEntryException e )
        {
            throw new MatchableException( "Error creating operation from log entry", e.getCause() );
        }
    }

    @Override
    public Class<? extends Operation<?>> operationType()
    {
        return DeleteRelationshipOperation.class;
    }

    public static class DeleteRelationshipOperation extends Operation<Integer>
    {
        private final long relationshipId;

        private DeleteRelationshipOperation( Time time, long relationshipId )
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
            return getClass().getSimpleName() + " [time=" + getScheduledStartTime() + ", relationshipId="
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
