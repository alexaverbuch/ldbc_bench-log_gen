package com.ldbc.driver.dshini.db.neo4j.emdedded;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.helpers.collection.MapUtil;

import com.ldbc.driver.DbConnectionState;
import com.ldbc.driver.OperationHandler;

import com.ldbc.driver.dshini.db.neo4j.Neo4jDshiniCommands;
import com.ldbc.driver.dshini.db.neo4j.emdedded.unimplemented.EmbeddedBatchOperationHandler;

import com.ldbc.driver.dshini.operations.batch.BatchOperationFactory.BatchOperation;
import com.ldbc.driver.dshini.operations.core.CreateNodeNeoProductOperationFactory.CreateNodeNeoProductOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipAuthoredByOperationFactory.CreateRelationshipAuthoredByOperation;
import com.ldbc.driver.dshini.operations.core.DeleteNodeOperationFactory.DeleteNodeOperation;
import com.ldbc.driver.dshini.operations.core.DeleteRelationshipOperationFactory.DeleteRelationshipOperation;
import com.ldbc.driver.dshini.operations.core.GetNodeOperationFactory.GetNodeOperation;
import com.ldbc.driver.dshini.operations.core.GetNodesIncomingClaimsRelationshipsOperationFactory.GetNodesIncomingClaimsRelationshipsOperation;
import com.ldbc.driver.dshini.operations.core.GetNodesOutRelationshipsOperationFactory.GetNodeOutRelationshipsOperation;
import com.ldbc.driver.dshini.operations.core.GetNodesRelationshipsOperationFactory.GetNodeRelationshipsOperation;
import com.ldbc.driver.dshini.operations.core.GetRelationshipOperationFactory.GetRelationshipOperation;
import com.ldbc.driver.dshini.operations.core.UpdateNodeNeoPinOperationFactory.UpdateNodeNeoPinOperation;
import com.ldbc.driver.dshini.operations.index.AddNodeToNeoPinBoardIndexOperationFactory.AddNodeToNeoPinBoardIndexOperation;
import com.ldbc.driver.dshini.operations.index.DeleteNodeFromNeoPinBoardIndexOperationFactory.DeleteNodeFromNeoPinBoardIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinEntertainmentVideoIndexOperationFactory.IndexQueryNodeOnNeoPinEntertainmentVideoIndexOperation;

public class Neo4jDshiniCommandsEmbedded implements Neo4jDshiniCommands
{
    // TODO add all new cypher operations

    private final String path;

    private ExecutionEngine queryEngine;
    private GraphDatabaseService db;
    private DbConnectionState dbConnectionState;

    public Neo4jDshiniCommandsEmbedded( String path )
    {
        this.path = path;
    }

    public void init()
    {
        db = new GraphDatabaseFactory().newEmbeddedDatabaseBuilder( path ).newGraphDatabase();
        queryEngine = new ExecutionEngine( db );
        dbConnectionState = new Neo4jConnectionStateEmbedded( db, queryEngine );
        registerShutdownHook( db );
    }

    public void cleanUp()
    {
        db.shutdown();
    }

    public void clearDb()
    {
        queryEngine.execute( "START r=rel(*) DELETE r", MapUtil.map() );
        queryEngine.execute( "START n=node(*) DELETE n", MapUtil.map() );
    }

    @Override
    public DbConnectionState getDbConnectionState()
    {
        return dbConnectionState;
    }

    private static void registerShutdownHook( final GraphDatabaseService graphDb )
    {
        Runtime.getRuntime().addShutdownHook( new Thread()
        {
            @Override
            public void run()
            {
                graphDb.shutdown();
            }
        } );
    }

    @Override
    public Class<? extends OperationHandler<BatchOperation>> getBatchOperationHandler()
    {
        return EmbeddedBatchOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinEntertainmentVideoIndexOperation>> getIndexQueryGetNodeOperationHandler()
    {
        return EmbeddedIndexQueryGetNodeOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<AddNodeToNeoPinBoardIndexOperation>> getAddNodeToIndexOperationHandler()
    {
        return EmbeddedAddNodeToIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<DeleteNodeFromNeoPinBoardIndexOperation>> getDeleteNodeFromIndexOperationHandler()
    {
        return EmbeddedDeleteNodeFromIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetNodeOperation>> getGetNodeOperationHandler()
    {
        return EmbeddedGetNodeOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateNodeNeoProductOperation>> getCreateNodeOperationHandler()
    {
        return EmbeddedCreateNodeOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<UpdateNodeNeoPinOperation>> getUpdateNodePropertiesOperationHandler()
    {
        return EmbeddedUpdateNodePropertiesOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<DeleteNodeOperation>> getDeleteNodeOperationHandler()
    {
        return EmbeddedDeleteNodeOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<DeleteRelationshipOperation>> getDeleteRelationshipOperationHandler()
    {
        return EmbeddedDeleteRelationshipOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetRelationshipOperation>> getGetRelationshipOperationHandler()
    {
        return EmbeddedGetRelationshipOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetNodeOutRelationshipsOperation>> getGetNodeOutRelationshipsOperationHandler()
    {
        return EmbeddedGetNodeOutRelationshipsOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipAuthoredByOperation>> getCreateRelationshipOperationHandler()
    {
        return EmbeddedCreateRelationshipOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetNodesIncomingClaimsRelationshipsOperation>> getGetNodeTypedOutRelationshipsOperationHandler()
    {
        return EmbeddedGetNodesIncomingClaimsRelationshipsOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetNodeRelationshipsOperation>> getGetNodeRelationshipsOperationHandler()
    {
        return EmbeddedGetNodeRelationshipsOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetNodesIncomingClaimsRelationshipsOperation>> getGetNodeTypedInRelationshipsOperationHandler()
    {
        return EmbeddedGetNodesIncomingClaimsRelationshipsOperationHandler.class;
    }
}
