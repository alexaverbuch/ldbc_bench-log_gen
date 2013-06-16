package com.ldbc.driver.dshini.generator;

import java.io.File;

import org.apache.log4j.Logger;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.log.RequestLogEntryReader;
import com.ldbc.driver.dshini.operations.AddNodeToIndexOperationFactory;
import com.ldbc.driver.dshini.operations.BatchOperationFactory;
import com.ldbc.driver.dshini.operations.CreateNodeOperationFactory;
import com.ldbc.driver.dshini.operations.CreateRelationshipOperationFactory;
import com.ldbc.driver.dshini.operations.CypherOperationFactory;
import com.ldbc.driver.dshini.operations.DeleteNodeFromIndexOperationFactory;
import com.ldbc.driver.dshini.operations.DeleteNodeOperationFactory;
import com.ldbc.driver.dshini.operations.DeleteRelationshipOperationFactory;
import com.ldbc.driver.dshini.operations.GetNodeOperationFactory;
import com.ldbc.driver.dshini.operations.GetNodesOutRelationshipsOperationFactory;
import com.ldbc.driver.dshini.operations.GetNodesRelationshipsOperationFactory;
import com.ldbc.driver.dshini.operations.GetNodesTypedInRelationshipsOperationFactory;
import com.ldbc.driver.dshini.operations.GetNodesTypedOutRelationshipsOperationFactory;
import com.ldbc.driver.dshini.operations.GetRelationshipOperationFactory;
import com.ldbc.driver.dshini.operations.IndexQueryGetNodeOperationFactory;
import com.ldbc.driver.dshini.operations.UpdateNodePropertiesOperationFactory;
import com.ldbc.driver.generator.Generator;
import com.ldbc.driver.generator.GeneratorException;

public class RequestLogOperationGenerator extends Generator<Operation<?>>
{
    private static final Logger logger = Logger.getLogger( RequestLogOperationGenerator.class );

    private final OperationMatcher operationMatcher;
    private final RequestLogEntryReader requestLogReader;

    public static DshiniLogEntryMatchable[] operations( OperationMatcher matcher )
    {
        return new DshiniLogEntryMatchable[] {
                // add a node to the index
                new AddNodeToIndexOperationFactory(),
                // batch operations
                new BatchOperationFactory( matcher ),
                // create a node
                new CreateNodeOperationFactory(),
                // cypher query
                new CypherOperationFactory(),
                // create a relationship
                new CreateRelationshipOperationFactory(),
                // delete a node from an index
                new DeleteNodeFromIndexOperationFactory(),
                // delete a node
                new DeleteNodeOperationFactory(),
                // delete a relationship
                new DeleteRelationshipOperationFactory(),
                // get a node
                new GetNodeOperationFactory(),
                // get a node's relationships
                new GetNodesRelationshipsOperationFactory(),
                // get a node's outgoing relationships
                new GetNodesOutRelationshipsOperationFactory(),
                // get a node's incoming relationships of given type
                new GetNodesTypedInRelationshipsOperationFactory(),
                // get a node's outgoing relationships of given type
                new GetNodesTypedOutRelationshipsOperationFactory(),
                // get a relationship
                new GetRelationshipOperationFactory(),
                // query an index to get a node(s)
                new IndexQueryGetNodeOperationFactory(),
                // update a node
                new UpdateNodePropertiesOperationFactory() };
    }

    public RequestLogOperationGenerator( File requestLogFile )
    {
        super( null );
        this.requestLogReader = new RequestLogEntryReader( requestLogFile );
        operationMatcher = new OperationMatcher();
        DshiniLogEntryMatchable[] operations = operations( operationMatcher );
        operationMatcher.setOperations( operations );
    }

    @Override
    protected Operation<?> doNext() throws GeneratorException
    {
        while ( requestLogReader.hasNext() )
        {
            RequestLogEntry entry = requestLogReader.next();
            try
            {
                return operationMatcher.getSingleMatchingOperation( entry );
            }
            catch ( RequestLogEntryException e )
            {
                // TODO hide notifications of entries that were not parsed
                String errMsg = String.format( "Error parsing log entry\n%s", entry.toString() );
                logger.error( errMsg );
            }
            catch ( DshiniLogEntryMatchableException e )
            {
                String errMsg = String.format( "Error matching operation to log entry\n%s", entry.toString() );
                logger.error( errMsg );
                throw new GeneratorException( errMsg, e.getCause() );
            }
        }
        return null;
    }
}
