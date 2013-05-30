package com.ldbc.driver.workloads.dshini;

import com.ldbc.driver.Operation;
import com.ldbc.driver.generator.dshini.CypherQueryDescriptor;
import com.ldbc.driver.generator.dshini.RequestLogEntryException;
import com.ldbc.driver.generator.dshini.RequestLogParsingUtils;

public class CypherOperation extends Operation<Object>
{
    private final long time;
    private final CypherQueryDescriptor cypherDescriptor;

    public static CypherOperation create( long time, String cypherString ) throws RequestLogEntryException
    {
        CypherQueryDescriptor cypherDescriptor = RequestLogParsingUtils.parseCypherDescription( cypherString );
        return new CypherOperation( time, cypherDescriptor );
    }

    public CypherOperation( long time, CypherQueryDescriptor cypherDescriptor )
    {
        super();
        this.time = time;
        this.cypherDescriptor = cypherDescriptor;
    }

    public long getTime()
    {
        return time;
    }

    public CypherQueryDescriptor getCypherDescriptor()
    {
        return cypherDescriptor;
    }

    @Override
    public String toString()
    {
        return "CypherOperation [time=" + time + ", cypherDescriptor=" + cypherDescriptor + "]";
    }
}
