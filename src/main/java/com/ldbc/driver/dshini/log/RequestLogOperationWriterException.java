package com.ldbc.driver.dshini.log;

public class RequestLogOperationWriterException extends Exception
{
    private static final long serialVersionUID = 6646883591588721475L;

    public RequestLogOperationWriterException( String message )
    {
        super( message );
    }

    public RequestLogOperationWriterException()
    {
        super();
    }

    public RequestLogOperationWriterException( String message, Throwable cause )
    {
        super( message, cause );
    }

    public RequestLogOperationWriterException( Throwable cause )
    {
        super( cause );
    }

}
