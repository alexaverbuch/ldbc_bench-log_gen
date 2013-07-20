package com.ldbc.driver.dshini.db.neo4j.emdedded;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import com.ldbc.driver.DbConnectionState;
import com.ldbc.driver.dshini.workloads.DshiniCommands;

public class Neo4jDshiniCommandsEmbedded extends DshiniCommands
{
    private final String path;
    private ExecutionEngine queryEngine;
    private GraphDatabaseService db;
    private DbConnectionState dbConnectionState;

    public Neo4jDshiniCommandsEmbedded( String path )
    {
        super( new Neo4jDshiniBatchCommandsEmbedded(), new Neo4jDshiniCoreCommandsEmbedded(),
                new Neo4jDshiniCypherCommandsEmbedded(), new Neo4jDshiniIndexCommandsEmbedded() );
        this.path = path;
    }

    @Override
    public void init()
    {
        db = new GraphDatabaseFactory().newEmbeddedDatabaseBuilder( path ).newGraphDatabase();
        queryEngine = new ExecutionEngine( db );
        dbConnectionState = new Neo4jConnectionStateEmbedded( db, queryEngine );
        registerShutdownHook( db );
    }

    @Override
    public void cleanUp()
    {
        db.shutdown();
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
}
