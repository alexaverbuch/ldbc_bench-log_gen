package com.ldbc.driver.dshini.log;

public class RequestLogEntryException extends Exception
{
    private static final long serialVersionUID = 6646883591588721475L;

    public RequestLogEntryException( String message )
    {
        super( message );
    }

    public RequestLogEntryException()
    {
        super();
    }

    public RequestLogEntryException( String message, Throwable cause )
    {
        super( message, cause );
    }

    public RequestLogEntryException( Throwable cause )
    {
        super( cause );
    }
}
