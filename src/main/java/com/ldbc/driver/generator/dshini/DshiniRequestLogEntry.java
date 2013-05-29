package com.ldbc.driver.generator.dshini;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class DshiniRequestLogEntry
{
    private static final Logger logger = Logger.getLogger( DshiniRequestLogEntry.class );

    private final Pattern EXPECTED_DSHINI_TIME_STAMP_PATTERN = Pattern.compile( "^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{6}" );
    private final SimpleDateFormat DESIRED_DATE_FORMAT = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );

    private final long time;
    private final String httpMethod;
    private final String url;
    private final String cypher;
    private final String httpHeaders;

    public DshiniRequestLogEntry( String timeStampString, String httpMethod, String url, String cypher,
            String httpHeaders ) throws DshiniRequestLogEntryException
    {
        super();
        this.time = convertTimeStampStringToMs( stripMicroSecondsFromTimeStampString( timeStampString ) );
        this.httpMethod = httpMethod;
        this.url = url;
        this.cypher = cypher;
        this.httpHeaders = httpHeaders;
    }

    private long convertTimeStampStringToMs( String timeStampString ) throws DshiniRequestLogEntryException
    {
        try
        {
            return DESIRED_DATE_FORMAT.parse( timeStampString ).getTime();
        }
        catch ( ParseException e )
        {
            String errMsg = String.format( "Error converting time stamp string [%s] to ms", timeStampString );
            logger.error( errMsg );
            throw new DshiniRequestLogEntryException( errMsg );
        }
    }

    private String stripMicroSecondsFromTimeStampString( String timeStampStringWithMicroSeconds )
            throws DshiniRequestLogEntryException
    {
        assertTimeStampFormat( EXPECTED_DSHINI_TIME_STAMP_PATTERN, timeStampStringWithMicroSeconds );
        return timeStampStringWithMicroSeconds.substring( 0, timeStampStringWithMicroSeconds.length() - 3 );
    }

    private void assertTimeStampFormat( Pattern pattern, String timeStampString ) throws DshiniRequestLogEntryException
    {
        if ( false == pattern.matcher( timeStampString ).matches() )
        {
            String errMsg = String.format( "Time stamp [%s] does not match pattern [%s]", timeStampString,
                    pattern.toString() );
            logger.error( errMsg );
            throw new DshiniRequestLogEntryException( errMsg );
        }
    }

    public long getTime()
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
