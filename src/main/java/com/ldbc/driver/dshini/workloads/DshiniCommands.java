package com.ldbc.driver.dshini.workloads;

import com.ldbc.driver.DbConnectionState;

public interface DshiniCommands extends DshiniBatchCommands, DshiniCoreCommands, DshiniCypherCommands,
        DshiniIndexCommands
{
    public void init();

    public void cleanUp();

    public void clearDb();

    public DbConnectionState getDbConnectionState();
}
