package com.ldbc.driver.dshini.generator;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class UrlParsingUtils
{
    private static final Logger logger = Logger.getLogger( UrlParsingUtils.class );

    private static final Pattern INDEX_DELETE_NODE_PATTERN = Pattern.compile( "db/data/index/node/\\w*/" );

    private static final Pattern NODE_FROM_RELATIONSHIP_URL_START_PATTERN = Pattern.compile( "db/data/node/" );
    private static final Pattern NODE_FROM_RELATIONSHIP_URL_END_PATTERN = Pattern.compile( "/relationships.*$" );

    private static final Pattern RELATIONSHIP_TYPE_FROM_RELATIONSHIP_URL_START_PATTERN = Pattern.compile( "/relationships/(in|out|all)/" );

    private static final Pattern NODE_PATTERN = Pattern.compile( "db/data/node/" );

    private static final Pattern PROPERTIES_PATTERN = Pattern.compile( "/properties$" );

    private static final Pattern RELATIONSHIP_PATTERN = Pattern.compile( "db/data/relationship/" );

    private static final Pattern INDEX_NODE_PATTERN = Pattern.compile( "db/data/index/node/" );

    private static final Pattern QUERY_PARAM_PATTERN = Pattern.compile( "\\?query=" );

    private static final String CHAR_SET = "UTF-8";

    // http://graph.internal.dshini.net:7474/db/data/index/node/neo_pin/8027060
    public static long parseNodeIdFromNodeIndexUrl( String urlString ) throws RequestLogEntryException
    {
        Matcher matcher = INDEX_DELETE_NODE_PATTERN.matcher( urlString );
        if ( matcher.find() )
        {
            return Long.parseLong( urlString.substring( matcher.end() ) );
        }
        String errMsg = String.format( "Pattern [%s] not found while parsing node ID from node index URL\nURL: %s",
                INDEX_DELETE_NODE_PATTERN.toString(), urlString );
        logger.error( errMsg );
        throw new RequestLogEntryException( errMsg );
    }

    // http://graph.internal.dshini.net:7474/db/data/node/11251602/relationships/in/CLAIMS
    public static String parseRelationshipTypeFromNodeRelationshipsUrl( String urlString )
            throws RequestLogEntryException
    {
        try
        {
            // "/relationships/(in|out|all)/"
            Matcher matcher = RELATIONSHIP_TYPE_FROM_RELATIONSHIP_URL_START_PATTERN.matcher( urlString );
            if ( matcher.find() )
            {
                return URLDecoder.decode( urlString.substring( matcher.end() ), CHAR_SET );
            }

            String errMsg = String.format(
                    "Pattern [%s] not found while parsing relationship type from relationship URL\nURL: %s",
                    RELATIONSHIP_TYPE_FROM_RELATIONSHIP_URL_START_PATTERN.toString(), urlString );
            logger.error( errMsg );
            throw new RequestLogEntryException( errMsg );

        }
        catch ( UnsupportedEncodingException e )
        {
            String errMsg = String.format( "Error parsing relationship type from URL\nURL: %s", urlString );
            logger.error( errMsg, e );
            throw new RequestLogEntryException( errMsg, e.getCause() );
        }
    }

    // http://graph-master.dshini.net:7474/db/data/node/11440883/relationships[/in|/out|/all][/TYPE]
    public static long parseNodeIdFromNodeRelationshipsUrl( String urlString ) throws RequestLogEntryException
    {
        Matcher startMatcher = NODE_FROM_RELATIONSHIP_URL_START_PATTERN.matcher( urlString );
        Matcher endMatcher = NODE_FROM_RELATIONSHIP_URL_END_PATTERN.matcher( urlString );
        boolean foundStart = startMatcher.find();
        boolean foundEnd = endMatcher.find();
        if ( foundStart && foundEnd )
        {
            return Long.parseLong( urlString.substring( startMatcher.end(), endMatcher.start() ) );
        }

        String errMsg = String.format(
                "Patterns [%s,%s] not found while parsing node ID from node relationships URL\nURL: %s",
                NODE_FROM_RELATIONSHIP_URL_START_PATTERN.toString(), NODE_FROM_RELATIONSHIP_URL_END_PATTERN.toString(),
                urlString );
        logger.error( errMsg );
        throw new RequestLogEntryException( errMsg );
    }

    // http://graph-master.dshini.net:7474/db/data/node/11455077[/properties]
    public static long parseNodeIdFromNodeUrl( String urlString ) throws RequestLogEntryException
    {
        // "db/data/node/"
        Matcher startMatcher = NODE_PATTERN.matcher( urlString );
        // "/properties$"
        Matcher endMatcher = PROPERTIES_PATTERN.matcher( urlString );
        boolean foundStart = startMatcher.find();
        boolean foundEnd = endMatcher.find();

        if ( foundStart && foundEnd )
        {
            return Long.parseLong( urlString.substring( startMatcher.end(), endMatcher.start() ) );
        }
        if ( foundStart )
        {
            return Long.parseLong( urlString.substring( startMatcher.end() ) );
        }

        String errMsg = String.format( "Patterns [%s,%s] not found while parsing node ID from node URL\nURL: %s",
                NODE_PATTERN.toString(), PROPERTIES_PATTERN.toString(), urlString );
        logger.error( errMsg );
        throw new RequestLogEntryException( errMsg );
    }

    // http://graph-master.dshini.net:7474/db/data/relationship/883198
    public static long parseRelationshipIdFromRelationshipUrl( String urlString ) throws RequestLogEntryException
    {
        Matcher startMatcher = RELATIONSHIP_PATTERN.matcher( urlString );
        if ( startMatcher.find() )
        {
            return Long.parseLong( urlString.substring( startMatcher.end() ) );
        }

        String errMsg = String.format(
                "Patterns [%s] not found while parsing relationship ID from relationship URL\nURL: %s",
                RELATIONSHIP_PATTERN.toString(), urlString );
        logger.error( errMsg );
        throw new RequestLogEntryException( errMsg );
    }

    // http://graph.internal.dshini.net:7474/db/data/index/node/neo_site
    public static String parseIndexNameFromNodeIndexUrl( String urlString ) throws RequestLogEntryException
    {
        try
        {
            // "db/data/index/node/"
            Matcher matcher = INDEX_NODE_PATTERN.matcher( urlString );
            if ( matcher.find() )
            {
                return URLDecoder.decode( urlString.substring( matcher.end() ), CHAR_SET );
            }

            String errMsg = String.format(
                    "Patterns [%s] not found while parsing index name from node index URL\nURL: %s",
                    INDEX_NODE_PATTERN.toString(), urlString );
            logger.error( errMsg );
            throw new RequestLogEntryException( errMsg );
        }
        catch ( UnsupportedEncodingException e )
        {
            String errMsg = String.format( "Error parsing index name from node index URL\nURL: %s", urlString );
            logger.error( errMsg, e );
            throw new RequestLogEntryException( errMsg, e.getCause() );
        }
    }

    // http://graph.internal.dshini.net:7474/db/data/index/node/user_profile?query=UserId:2149
    public static String parseIndexNameForNodeIndexQueryUrl( String urlString ) throws RequestLogEntryException
    {
        try
        {
            // "db/data/index/node/"
            Matcher startMatcher = INDEX_NODE_PATTERN.matcher( urlString );
            // "\\?query="
            Matcher endMatcher = QUERY_PARAM_PATTERN.matcher( urlString );
            if ( startMatcher.find() && endMatcher.find() )
            {
                return URLDecoder.decode( urlString.substring( startMatcher.end(), endMatcher.start() ), CHAR_SET );
            }

            String errMsg = String.format(
                    "Patterns [%s,%s] not found while parsing index name from node index query URL\nURL: %s",
                    INDEX_NODE_PATTERN.toString(), QUERY_PARAM_PATTERN.toString(), urlString );
            logger.error( errMsg );
            throw new RequestLogEntryException( errMsg );
        }
        catch ( UnsupportedEncodingException e )
        {
            String errMsg = String.format( "Error parsing index name node index query URL\nURL: %s", urlString );
            logger.error( errMsg, e );
            throw new RequestLogEntryException( errMsg, e.getCause() );
        }
    }

    // http://graph.internal.dshini.net:7474/db/data/index/node/user_profile?query=UserId:2149
    public static String parseIndexQueryFromNodeIndexQueryUrl( String urlString ) throws RequestLogEntryException
    {
        try
        {
            // "\\?query="
            Matcher matcher = QUERY_PARAM_PATTERN.matcher( urlString );
            if ( matcher.find() )
            {
                return URLDecoder.decode( urlString.substring( matcher.end() ), CHAR_SET );
            }

            String errMsg = String.format(
                    "Patterns [%s] not found while parsing index query from node index query URL\nURL: %s",
                    QUERY_PARAM_PATTERN.toString(), urlString );
            logger.error( errMsg );
            throw new RequestLogEntryException( errMsg );
        }
        catch ( UnsupportedEncodingException e )
        {
            String errMsg = String.format( "Error parsing index query from node index query url\nURL: %s", urlString );
            logger.error( errMsg, e );
            throw new RequestLogEntryException( errMsg, e.getCause() );
        }
    }
}
