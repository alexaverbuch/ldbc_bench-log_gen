package com.ldbc.driver.generator.dshini;

public class RequestLogEntry
{
    private final String time;
    private final String httpMethod;
    private final String url;
    private final String description;
    private final String httpHeaders;

    public RequestLogEntry( String time, String httpMethod, String url, String description, String httpHeaders )
    {
        super();
        this.time = time;
        this.httpMethod = httpMethod;
        this.url = url;
        this.description = description;
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

    public String getDescription()
    {
        return description;
    }

    public String getHttpHeaders()
    {
        return httpHeaders;
    }

    @Override
    public String toString()
    {
        return "RequestLogEntry [time=" + time + ", httpMethod=" + httpMethod + ", url=" + url
               + ", operationDescription=" + description + ", httpHeaders=" + httpHeaders + "]";
    }
}
