package com.ldbc.driver.dshini.db.neo4j.emdedded;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import com.ldbc.driver.DbConnectionState;

public class Neo4jDshiniCommandsEmbedded
// TODO
// implements DshiniCommands
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
        // TODO never want to delete this database!
        // queryEngine.execute( "START r=rel(*) DELETE r", MapUtil.map() );
        // queryEngine.execute( "START n=node(*) DELETE n", MapUtil.map() );
    }

    // TODO
    // @Override
    // public DbConnectionState getDbConnectionState()
    // {
    // return dbConnectionState;
    // }

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

    // TODO
    // public Class<? extends OperationHandler<BatchOperation>>
    // getBatchOperationHandler()
    // {
    // return EmbeddedBatchOperationHandler.class;
    // }
}
