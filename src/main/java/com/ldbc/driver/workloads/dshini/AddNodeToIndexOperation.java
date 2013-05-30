package com.ldbc.driver.workloads.dshini;

import com.ldbc.driver.Operation;
import com.ldbc.driver.generator.dshini.RequestLogEntryException;
import com.ldbc.driver.generator.dshini.RequestLogParsingUtils;
import com.ldbc.driver.util.Triple;

public class AddNodeToIndexOperation extends Operation<Object>
{
    private final long time;
    private final String indexName;
    private final String key;
    private final Object value;
    private final long nodeId;

    public static AddNodeToIndexOperation create( long time, String urlString, String descriptionString )
            throws RequestLogEntryException
    {
        String indexName = RequestLogParsingUtils.parseIndexNameForAddNodeToIndex( urlString );
        Triple<String, Object, Long> keyValueNode = RequestLogParsingUtils.parseKeyValueNodeForAddNodeToIndex( descriptionString );
        return new AddNodeToIndexOperation( time, indexName, keyValueNode._1(), keyValueNode._2(), keyValueNode._3() );
    }

    public AddNodeToIndexOperation( long time, String indexName, String key, Object value, long nodeId )
    {
        super();
        this.time = time;
        this.indexName = indexName;
        this.key = key;
        this.value = value;
        this.nodeId = nodeId;
    }

    public long getTime()
    {
        return time;
    }

    public String getIndexName()
    {
        return indexName;
    }

    public String getKey()
    {
        return key;
    }

    public Object getValue()
    {
        return value;
    }

    public long getNodeId()
    {
        return nodeId;
    }

    @Override
    public String toString()
    {
        return "AddNodeToIndexOperation [time=" + time + ", indexName=" + indexName + ", key=" + key + ", value="
               + value + ", nodeId=" + nodeId + "]";
    }
}
