package com.ldbc.driver.dshini.workloads;

import com.ldbc.driver.Db;
import com.ldbc.driver.DbConnectionState;
import com.ldbc.driver.DbException;

public abstract class DshiniCommands implements OperationHandlersRegistrar
{
    private final DshiniBatchCommands batchCommands;
    private final DshiniCoreCommands coreCommands;
    private final DshiniCypherCommands cypherCommands;
    private final DshiniIndexCommands indexCommands;

    public DshiniCommands( DshiniBatchCommands batchCommands, DshiniCoreCommands coreCommands,
            DshiniCypherCommands cypherCommands, DshiniIndexCommands indexCommands )
    {
        this.batchCommands = batchCommands;
        this.coreCommands = coreCommands;
        this.cypherCommands = cypherCommands;
        this.indexCommands = indexCommands;
    }

    @Override
    public final void registerHandlersWithDb( Db db ) throws DbException
    {
        batchCommands.registerHandlersWithDb( db );
        coreCommands.registerHandlersWithDb( db );
        cypherCommands.registerHandlersWithDb( db );
        indexCommands.registerHandlersWithDb( db );
    }

    public final DshiniBatchCommands batch()
    {
        return batchCommands;
    }

    public final DshiniCoreCommands core()
    {
        return coreCommands;
    }

    public final DshiniCypherCommands cypher()
    {
        return cypherCommands;
    }

    public final DshiniIndexCommands index()
    {
        return indexCommands;
    }

    public abstract void init();

    public abstract void cleanUp();

    public abstract DbConnectionState getDbConnectionState();
}
