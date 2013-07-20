package com.ldbc.driver.dshini.workloads;

import com.ldbc.driver.Db;
import com.ldbc.driver.DbException;

public interface OperationHandlersRegistrar
{
    public void registerHandlersWithDb( Db db ) throws DbException;

}
