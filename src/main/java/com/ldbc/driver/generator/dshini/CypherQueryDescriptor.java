package com.ldbc.driver.generator.dshini;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

public class CypherQueryDescriptor
{
    private String query;

    private Map<String, Object> params;

    public static CypherQueryDescriptor empty()
    {
        CypherQueryDescriptor cqd = new CypherQueryDescriptor();
        cqd.setQuery( "" );
        cqd.setParams( new HashMap<String, Object>() );
        return cqd;
    }

    @JsonProperty( "query" )
    public String getQuery()
    {
        return query;
    }

    @JsonProperty( "query" )
    public void setQuery( String query )
    {
        this.query = query;
    }

    @JsonProperty( "params" )
    public Map<String, Object> getParams()
    {
        return params;
    }

    @JsonProperty( "params" )
    public void setParams( Map<String, Object> params )
    {
        this.params = params;
    }

}
