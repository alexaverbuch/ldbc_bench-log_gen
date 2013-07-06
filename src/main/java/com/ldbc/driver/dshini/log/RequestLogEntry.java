package com.ldbc.driver.dshini.log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.ldbc.driver.util.temporal.Time;

public class RequestLogEntry
{
    private static final Pattern EXPECTED_DSHINI_TIME_STAMP_PATTERN = Pattern.compile( "^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{6}" );
    private static final SimpleDateFormat DESIRED_DATE_FORMAT = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );
    private static final String SINGLE_QUOTE_STRING = "\"";
    private static final Pattern DOUBLE_QUOTE_PATTERN = Pattern.compile( "\"\"" );

    private final ObjectMapper mapper;

    private final Time time;
    private final String httpMethod;
    private final String url;

    private final String descriptionString;
    private Map<String, Object> descriptionMap;
    private List<Map<String, Object>> descriptionMapList;

    private final String httpHeaders;

    public RequestLogEntry( ObjectMapper mapper, String time, String httpMethod, String url, String description,
            String httpHeaders ) throws RequestLogEntryException
    {
        this( mapper, Time.fromMilli( parseTimeToMilliSeconds( time ) ), httpMethod, url, description, null, null,
                httpHeaders );
    }

    public RequestLogEntry( ObjectMapper mapper, String time, String httpMethod, String url,
            Map<String, Object> description, String httpHeaders ) throws RequestLogEntryException
    {
        this( mapper, Time.fromMilli( parseTimeToMilliSeconds( time ) ), httpMethod, url, null, description, null,
                httpHeaders );
    }

    public RequestLogEntry( ObjectMapper mapper, String time, String httpMethod, String url,
            List<Map<String, Object>> description, String httpHeaders ) throws RequestLogEntryException
    {
        this( mapper, Time.fromMilli( parseTimeToMilliSeconds( time ) ), httpMethod, url, null, null, description,
                httpHeaders );
    }

    public RequestLogEntry( ObjectMapper mapper, Time time, String httpMethod, String url, String description,
            String httpHeaders ) throws RequestLogEntryException
    {
        this( mapper, time, httpMethod, url, description, null, null, httpHeaders );
    }

    public RequestLogEntry( ObjectMapper mapper, Time time, String httpMethod, String url,
            Map<String, Object> description, String httpHeaders ) throws RequestLogEntryException
    {
        this( mapper, time, httpMethod, url, null, description, null, httpHeaders );
    }

    public RequestLogEntry( ObjectMapper mapper, Time time, String httpMethod, String url,
            List<Map<String, Object>> description, String httpHeaders ) throws RequestLogEntryException
    {
        this( mapper, time, httpMethod, url, null, null, description, httpHeaders );
    }

    private RequestLogEntry( ObjectMapper mapper, Time time, String httpMethod, String url, String descriptionString,
            Map<String, Object> descriptionMap, List<Map<String, Object>> descriptionMapList, String httpHeaders )
                                                                                                                  throws RequestLogEntryException
    {
        super();
        this.mapper = mapper;
        this.time = time;
        this.httpMethod = httpMethod;
        this.url = url;
        this.descriptionString = descriptionString;
        this.descriptionMap = descriptionMap;
        this.descriptionMapList = descriptionMapList;
        this.httpHeaders = httpHeaders;
    }

    public Time getTime()
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

    public String getDescriptionAsString() throws RequestLogEntryException
    {
        if ( null == descriptionString )
        {
            String errMsg = "Description string is null";
            throw new RequestLogEntryException( errMsg );
        }
        return descriptionString;
    }

    public Map<String, Object> getDescriptionAsMap() throws RequestLogEntryException
    {
        descriptionMap = ( null == descriptionMap ) ? parseJsonStringObjectMap( descriptionString ) : descriptionMap;
        return descriptionMap;
    }

    public List<Map<String, Object>> getDescriptionAsMapList() throws RequestLogEntryException
    {
        descriptionMapList = ( null == descriptionMapList ) ? parseJsonListOfStringObjectMaps( descriptionString )
                : descriptionMapList;
        return descriptionMapList;
    }

    public String getHttpHeaders()
    {
        return httpHeaders;
    }

    @Override
    public String toString()
    {
        return "RequestLogEntry [mapper=" + mapper + ", time=" + time.asMilli() + ", httpMethod=" + httpMethod
               + ", url=" + url + ", descriptionString=" + descriptionString + ", descriptionMap=" + descriptionMap
               + ", descriptionMapList=" + descriptionMapList + ", httpHeaders=" + httpHeaders + "]";
    }

    private Map<String, Object> parseJsonStringObjectMap( String jsonString ) throws RequestLogEntryException
    {
        try
        {
            return mapper.readValue( cleanString( jsonString ), new TypeReference<Map<String, Object>>()
            {
            } );
        }
        catch ( Exception e )
        {
            String errMsg = String.format( "Error parsing map json string\n%s", jsonString );
            throw new RequestLogEntryException( errMsg );
        }
    }

    private List<Map<String, Object>> parseJsonListOfStringObjectMaps( String jsonString )
            throws RequestLogEntryException
    {
        try
        {
            return mapper.readValue( cleanString( jsonString ), new TypeReference<List<Map<String, Object>>>()
            {
            } );
        }
        catch ( Exception e )
        {
            String errMsg = String.format( "Error parsing list of maps json string\n%s", jsonString );
            throw new RequestLogEntryException( errMsg );
        }
    }

    // "2013-04-29 15:32:53.661274"
    private static long parseTimeToMilliSeconds( String timeString ) throws RequestLogEntryException
    {
        String timeStringWithoutQuote = stripSurroundingCharacters( timeString );
        assertTimeStampFormat( EXPECTED_DSHINI_TIME_STAMP_PATTERN, timeStringWithoutQuote );
        String timeStringMilli = timeStringWithoutQuote.substring( 0, timeStringWithoutQuote.length() - 3 );
        return convertTimeStringToMilliSeconds( timeStringMilli );
    }

    private static long convertTimeStringToMilliSeconds( String timeStampString ) throws RequestLogEntryException
    {
        try
        {
            return DESIRED_DATE_FORMAT.parse( timeStampString ).getTime();
        }
        catch ( ParseException e )
        {
            String errMsg = String.format( "Error converting time stamp string [%s] to ns", timeStampString );
            throw new RequestLogEntryException( errMsg );
        }
    }

    private static void assertTimeStampFormat( Pattern pattern, String timeStampString )
            throws RequestLogEntryException
    {
        if ( false == pattern.matcher( timeStampString ).matches() )
        {
            String errMsg = String.format( "Time stamp [%s] does not match pattern [%s]", timeStampString,
                    pattern.toString() );
            throw new RequestLogEntryException( errMsg );
        }
    }

    private static String cleanString( String dirtyString )
    {
        String stringWithoutSurroundQuotes = stripSurroundingCharacters( dirtyString );
        String singleQuotedString = convertDoubleQuotesToSingleQuote( stringWithoutSurroundQuotes );
        return singleQuotedString;
    }

    private static String stripSurroundingCharacters( String input )
    {
        return input.substring( 1, input.length() - 1 );
    }

    private static String convertDoubleQuotesToSingleQuote( String stringWithDoubleQuotes )
    {
        return DOUBLE_QUOTE_PATTERN.matcher( stringWithDoubleQuotes ).replaceAll( SINGLE_QUOTE_STRING );
    }
}
