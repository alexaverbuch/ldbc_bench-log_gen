package com.ldbc.driver.workloads.dshini;

import com.ldbc.driver.Operation;
import com.ldbc.driver.generator.dshini.RequestLogEntryException;

public class RelationshipGetOperation extends Operation<Object>
{
    private final long time;

    // TODO something other than String
    private final String description;

    public static RelationshipGetOperation create( long time, String descriptionString )
            throws RequestLogEntryException
    {
        String description = descriptionString;
        return new RelationshipGetOperation( time, description );
    }

    public RelationshipGetOperation( long time, String description )
    {
        super();
        this.time = time;
        this.description = description;
    }

    public long getTime()
    {
        return time;
    }

    public String getDescription()
    {
        return description;
    }

    // TODO toString
}
