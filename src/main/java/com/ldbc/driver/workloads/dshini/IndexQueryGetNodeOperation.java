package com.ldbc.driver.workloads.dshini;

import com.ldbc.driver.Operation;
import com.ldbc.driver.generator.dshini.RequestLogEntryException;
import com.ldbc.driver.generator.dshini.RequestLogParsingUtils;
import com.ldbc.driver.util.Pair;

public class IndexQueryGetNodeOperation extends Operation<Object>
{
    private final long time;
    private final String indexName;
    private final String indexQuery;

    public static IndexQueryGetNodeOperation create( long time, String urlString ) throws RequestLogEntryException
    {
        Pair<String, String> indexNameAndIndexQuery = RequestLogParsingUtils.parseIndexNodeQueryGet( urlString );
        return new IndexQueryGetNodeOperation( time, indexNameAndIndexQuery._1(), indexNameAndIndexQuery._2() );
    }

    public IndexQueryGetNodeOperation( long time, String indexName, String indexQuery )
    {
        super();
        this.time = time;
        this.indexName = indexName;
        this.indexQuery = indexQuery;
    }

    public long getTime()
    {
        return time;
    }

    public String getIndexName()
    {
        return indexName;
    }

    public String getIndexQuery()
    {
        return indexQuery;
    }

    @Override
    public String toString()
    {
        return "IndexQueryGetNodeOperation [time=" + time + ", indexName=" + indexName + ", indexQuery=" + indexQuery
               + "]";
    }
}
