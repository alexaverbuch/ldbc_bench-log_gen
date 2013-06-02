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
        return new DeleteRelationshipOperation( entry.getTime(), relationshipId );
    }

    public class DeleteRelationshipOperation extends Operation<Object>
    {
        private final long time;
        private final long relationshipId;

        private DeleteRelationshipOperation( long time, long relationshipId )
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
            return "DeleteRelationshipOperation [time=" + time + ", relationshipId=" + relationshipId + "]";
        }
    }
}
