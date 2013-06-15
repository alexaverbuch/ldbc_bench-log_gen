package com.ldbc.driver.dshini.db.neo4j.emdedded;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.helpers.collection.MapUtil;

import com.ldbc.driver.DbConnectionState;
import com.ldbc.driver.OperationHandler;

import com.ldbc.driver.dshini.db.neo4j.Neo4jDshiniCommands;
import com.ldbc.driver.dshini.db.neo4j.emdedded.unimplemented.EmbeddedBatchOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.unimplemented.EmbeddedCypherOperationHandler;

import com.ldbc.driver.dshini.operations.AddNodeToIndexOperationFactory.AddNodeToIndexOperation;
import com.ldbc.driver.dshini.operations.BatchOperationFactory.BatchOperation;
import com.ldbc.driver.dshini.operations.CreateNodeOperationFactory.CreateNodeOperation;
import com.ldbc.driver.dshini.operations.CreateRelationshipOperationFactory.CreateRelationshipOperation;
import com.ldbc.driver.dshini.operations.CypherOperationFactory.CypherOperation;
import com.ldbc.driver.dshini.operations.DeleteNodeFromIndexOperationFactory.DeleteNodeFromIndexOperation;
import com.ldbc.driver.dshini.operations.DeleteNodeOperationFactory.DeleteNodeOperation;
import com.ldbc.driver.dshini.operations.DeleteRelationshipOperationFactory.DeleteRelationshipOperation;
import com.ldbc.driver.dshini.operations.GetNodeOperationFactory.GetNodeOperation;
import com.ldbc.driver.dshini.operations.GetNodesOutRelationshipsOperationFactory.GetNodeOutRelationshipsOperation;
import com.ldbc.driver.dshini.operations.GetNodesRelationshipsOperationFactory.GetNodeRelationshipsOperation;
import com.ldbc.driver.dshini.operations.GetNodesTypedInRelationshipsOperationFactory.GetNodeTypedInRelationshipsOperation;
import com.ldbc.driver.dshini.operations.GetNodesTypedOutRelationshipsOperationFactory.GetNodeTypedOutRelationshipsOperation;
import com.ldbc.driver.dshini.operations.GetRelationshipOperationFactory.GetRelationshipOperation;
import com.ldbc.driver.dshini.operations.IndexQueryGetNodeOperationFactory.IndexQueryGetNodeOperation;
import com.ldbc.driver.dshini.operations.UpdateNodePropertiesOperationFactory.UpdateNodePropertiesOperation;

public class Neo4jDshiniCommandsEmbedded implements Neo4jDshiniCommands
{
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
    public Class<? extends OperationHandler<CypherOperation>> getCypherOperationHandler()
    {
        return EmbeddedCypherOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<BatchOperation>> getBatchOperationHandler()
    {
        return EmbeddedBatchOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryGetNodeOperation>> getIndexQueryGetNodeOperationHandler()
    {
        return EmbeddedIndexQueryGetNodeOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<AddNodeToIndexOperation>> getAddNodeToIndexOperationHandler()
    {
        return EmbeddedAddNodeToIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<DeleteNodeFromIndexOperation>> getDeleteNodeFromIndexOperationHandler()
    {
        return EmbeddedDeleteNodeFromIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetNodeOperation>> getGetNodeOperationHandler()
    {
        return EmbeddedGetNodeOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateNodeOperation>> getCreateNodeOperationHandler()
    {
        return EmbeddedCreateNodeOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<UpdateNodePropertiesOperation>> getUpdateNodePropertiesOperationHandler()
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
    public Class<? extends OperationHandler<CreateRelationshipOperation>> getCreateRelationshipOperationHandler()
    {
        return EmbeddedCreateRelationshipOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetNodeTypedOutRelationshipsOperation>> getGetNodeTypedOutRelationshipsOperationHandler()
    {
        return EmbeddedGetNodeTypedOutRelationshipsOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetNodeRelationshipsOperation>> getGetNodeRelationshipsOperationHandler()
    {
        return EmbeddedGetNodeRelationshipsOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetNodeTypedInRelationshipsOperation>> getGetNodeTypedInRelationshipsOperationHandler()
    {
        return EmbeddedGetNodeTypedInRelationshipsOperationHandler.class;
    }
}