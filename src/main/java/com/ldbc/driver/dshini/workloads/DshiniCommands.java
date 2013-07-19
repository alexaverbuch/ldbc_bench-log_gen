package com.ldbc.driver.dshini.workloads;

import java.util.ArrayList;
import java.util.List;

import com.ldbc.driver.DbConnectionState;
import com.ldbc.driver.Operation;
import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.util.Pair;

public abstract class DshiniCommands implements AllOperationHandlersProvider
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
    public final List<Pair<Class<? extends Operation<?>>, Class<? extends OperationHandler<?>>>> allOperationHandlers()
    {
        List<Pair<Class<? extends Operation<?>>, Class<? extends OperationHandler<?>>>> handlers = new ArrayList<Pair<Class<? extends Operation<?>>, Class<? extends OperationHandler<?>>>>();
        handlers.addAll( batchCommands.allOperationHandlers() );
        handlers.addAll( coreCommands.allOperationHandlers() );
        handlers.addAll( cypherCommands.allOperationHandlers() );
        handlers.addAll( indexCommands.allOperationHandlers() );
        return handlers;
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

    public abstract void clearDb();

    public abstract DbConnectionState getDbConnectionState();
}
