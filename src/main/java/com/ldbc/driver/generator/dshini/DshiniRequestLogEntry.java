package com.ldbc.driver.generator.dshini;

public class DshiniRequestLogEntry
{
    private final String time;
    private final String httpMethod;
    private final String url;
    private final String cypher;
    private final String httpHeaders;

    public DshiniRequestLogEntry( String time, String httpMethod, String url, String cypher, String httpHeaders )
    {
        super();
        this.time = time;
        this.httpMethod = httpMethod;
        this.url = url;
        this.cypher = cypher;
        this.httpHeaders = httpHeaders;
    }

    public String getTime()
    {
        return time;
    }

    public String getHttpMethod()
    {
        return httpMethod;
    }

    public String getUrl()
    {
        return url;
    }

    public String getCypher()
    {
        return cypher;
    }

    public String getHttpHeaders()
    {
        return httpHeaders;
    }

    @Override
    public String toString()
    {
        return "DshiniRequestLogEntry [time=" + time + ", httpMethod=" + httpMethod + ", url=" + url + ", cypher="
               + cypher + ", httpHeaders=" + httpHeaders + "]";
    }
}
