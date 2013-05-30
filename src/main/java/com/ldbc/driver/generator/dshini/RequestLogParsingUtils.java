package com.ldbc.driver.generator.dshini;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.ldbc.driver.util.Pair;
import com.ldbc.driver.util.Triple;

public class RequestLogParsingUtils
{
    private static final Logger logger = Logger.getLogger( RequestLogParsingUtils.class );

    private static final String SINGLE_QUOTE = "\"";
    private static final String DOUBLE_QUOTE = "\"\"";
    private static final Pattern DOUBLE_QUOTE_PATTERN = Pattern.compile( DOUBLE_QUOTE );
    private static final Pattern EXPECTED_DSHINI_TIME_STAMP_PATTERN = Pattern.compile( "^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{6}" );
    private static final SimpleDateFormat DESIRED_DATE_FORMAT = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );
    private static final String CHAR_SET = "UTF-8";
    private static final String NODE_STRING = "db/data/node/";
    private static final int NODE_STRING_LENGTH = NODE_STRING.length();
    private static final String INDEX_NODE_STRING = "db/data/index/node/";
    private static final int INDEX_NODE_STRING_LENGTH = INDEX_NODE_STRING.length();
    private static final String QUERY_PARAM = "?query=";
    private static final int QUERY_PARAM_LENGTH = QUERY_PARAM.length();

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static long parseTime( String timeString ) throws RequestLogEntryException
    {
        String timeStringWithoutQuote = stripSurroundingCharacters( timeString );
        assertTimeStampFormat( EXPECTED_DSHINI_TIME_STAMP_PATTERN, timeStringWithoutQuote );
        String timeStringMilli = timeStringWithoutQuote.substring( 0, timeStringWithoutQuote.length() - 3 );
        return convertTimeStringToMs( timeStringMilli );
    }

    private static long convertTimeStringToMs( String timeStampString ) throws RequestLogEntryException
    {
        try
        {
            return DESIRED_DATE_FORMAT.parse( timeStampString ).getTime();
        }
        catch ( ParseException e )
        {
            String errMsg = String.format( "Error converting time stamp string [%s] to ms", timeStampString );
            logger.error( errMsg );
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
            logger.error( errMsg );
            throw new RequestLogEntryException( errMsg );
        }
    }

    public static String parseHttpMethod( String httpMethodString )
    {
        return httpMethodString;
    }

    public static String parseUrl( String urlString )
    {
        return urlString;
    }

    public static CypherQueryDescriptor parseCypherDescription( String cypherJsonString )
            throws RequestLogEntryException
    {
        try
        {
            String cypherJsonStringWithoutSurroundQuotes = stripSurroundingCharacters( cypherJsonString );
            String singleQuotedCypherJsonString = convertDoubleQuotesToSingleQuote( cypherJsonStringWithoutSurroundQuotes );
            return OBJECT_MAPPER.readValue( singleQuotedCypherJsonString, CypherQueryDescriptor.class );
        }
        catch ( Exception e )
        {
            String errMsg = String.format( "Error parsing Cypher string\n%s", cypherJsonString );
            logger.error( errMsg, e );
            throw new RequestLogEntryException( errMsg );
        }
    }

    public static Pair<String, String> parseIndexNodeQueryGet( String urlString ) throws RequestLogEntryException
    {
        try
        {
            int startIndex = urlString.indexOf( INDEX_NODE_STRING ) + INDEX_NODE_STRING_LENGTH;
            int endIndex = urlString.indexOf( QUERY_PARAM );
            String indexName = URLDecoder.decode( urlString.substring( startIndex, endIndex ), CHAR_SET );
            String indexQuery = URLDecoder.decode( urlString.substring( endIndex + QUERY_PARAM_LENGTH ), CHAR_SET );
            return Pair.create( indexName, indexQuery );
        }
        catch ( UnsupportedEncodingException e )
        {
            String errMsg = String.format( "Error parsing URL for IndexNodeQueryGet operation\nURL: %s", urlString );
            logger.error( errMsg, e );
            throw new RequestLogEntryException( errMsg, e.getCause() );
        }
    }

    public static String parseHttpHeaders( String httpHeadersString )
    {
        return httpHeadersString;
    }

    /*
     * BATCH
     */
    /*
    "2013-04-29 15:28:22.932807";
    POST;
    http://graph-master.dshini.net:7474/db/data/batch;
    "[
        {
            ""method"":""POST"",
            ""to"":""\/node"",
            ""body"": {
                          ""ObjectType"":""NeoPin"",
                          ""Message"":"""",
                          ""CommentsClosed"":false,
                          ""CreatedAt"":1367242102,
                          ""PinIdentifier"":""279a7016759215a60cfa8d2417b6580696a7a474"",
                          ""LikeCount"":0,""RepinCount"":0
                      },
             ""id"":0
         },
         {
             ""method"":""POST"",
             ""to"":""\/index\/node\/neo_pin"",
             ""body"": {
                           ""key"":""ObjectType"",
                           ""value"":""NeoPin"",
                           ""uri"":""{0}""
                       },
             ""id"":1
         },
         {
             ""method"":""POST"",
             ""to"":""\/index\/node\/neo_pin"",
             ""body"": {
                           ""key"":""Message"",
                           ""value"":"""",
                           ""uri"":""{0}""
                       },
             ""id"":2
         },
         {
             ""method"":""POST"",
             ""to"":""\/index\/node\/neo_pin"",
             ""body"": {
                           ""key"":""CommentsClosed"",
                           ""value"":false,
                           ""uri"":""{0}""
                       },
             ""id"":3
         },
         {
             ""method"":""POST"",
             ""to"":""\/index\/node\/neo_pin"",
             ""body"": {
                           ""key"":""CreatedAt"",
                           ""value"":1367242102,
                           ""uri"":""{0}""
                       },
             ""id"":4
         },
         {
             ""method"":""POST"",
             ""to"":""\/index\/node\/neo_pin"",
             ""body"": {
                           ""key"":""PinIdentifier"",
                           ""value"":""279a7016759215a60cfa8d2417b6580696a7a474"",
                           ""uri"":""{0}""
                       },
             ""id"":5
         },
         {
             ""method"":""POST"",
             ""to"":""\/index\/node\/neo_pin"",
             ""body"": {
                           ""key"":""LikeCount"",
                           ""value"":0,
                           ""uri"":""{0}""
                       },
             ""id"":6
         },
         {
             ""method"":""POST"",
             ""to"":""\/index\/node\/neo_pin"",
             ""body"": {
                           ""key"":""RepinCount"",
                           ""value"":0,
                           ""uri"":""{0}""
                       },
             ""id"":7
         },
         {
             ""method"":""POST"",
             ""to"":""{0}\/relationships"",
             ""body"": {
                           ""to"":""\/node\/11434545"",
                           ""data"": {
                                         ""CreatedAt"":1367242102
                                     },
                                     ""type"":""REPINS""
                       },
             ""id"":8
         },
         {
             ""method"":""POST"",
             ""to"":""\/cypher"",
             ""body"": {
                           ""query"":""START n=node({STARTIDS}) MATCH n<-[repins?:REPINS]-() WITH n, COUNT(repins) as repin_count SET n.RepinCount = repin_count RETURN n"",
                           ""params"":{""STARTIDS"":11434545}
                       },
             ""id"":9
         }
    ]";
    "[""Accept: application\/json"",""X-Stream:true"",""Content-Length: 1371"",""Content-Type: application\/json""]"
    */
    public static void parseBatchDescription( String batchDescription )
    {
        // TODO
    }

    public static String parseIndexNameForAddNodeToIndex( String urlString ) throws RequestLogEntryException
    {
        try
        {
            int startIndex = urlString.indexOf( INDEX_NODE_STRING ) + INDEX_NODE_STRING_LENGTH;
            return URLDecoder.decode( urlString.substring( startIndex ), CHAR_SET );
        }
        catch ( UnsupportedEncodingException e )
        {
            String errMsg = String.format( "Error parsing URL for AddNodeToIndex operation\nURL: %s", urlString );
            logger.error( errMsg, e );
            throw new RequestLogEntryException( errMsg, e.getCause() );
        }
    }

    public static Triple<String, Object, Long> parseKeyValueNodeForAddNodeToIndex( String descriptionString )
            throws RequestLogEntryException
    {
        try
        {
            String operationStringWithoutSurroundQuotes = stripSurroundingCharacters( descriptionString );
            String singleQuotedOperationString = convertDoubleQuotesToSingleQuote( operationStringWithoutSurroundQuotes );
            Map<String, Object> operationMap = OBJECT_MAPPER.readValue( singleQuotedOperationString, Map.class );
            String key = (String) operationMap.get( "key" );
            Object value = operationMap.get( "value" );
            String uriString = (String) operationMap.get( "uri" );
            int startIndex = uriString.indexOf( NODE_STRING ) + NODE_STRING_LENGTH;
            String nodeIdString = uriString.substring( startIndex );
            long nodeId = Long.parseLong( nodeIdString );
            return Triple.create( key, value, nodeId );
        }
        catch ( Exception e )
        {
            String errMsg = String.format( "Error parsing JSON for AddNodeToIndex operation\nURL: %s",
                    descriptionString );
            logger.error( errMsg, e );
            throw new RequestLogEntryException( errMsg, e.getCause() );
        }
    }

    /*
     * Utils
     */
    private static String stripSurroundingCharacters( String input )
    {
        return input.substring( 1, input.length() - 1 );
    }

    private static String convertDoubleQuotesToSingleQuote( String stringWithDoubleQuotes )
    {
        return DOUBLE_QUOTE_PATTERN.matcher( stringWithDoubleQuotes ).replaceAll( SINGLE_QUOTE );
    }
}
