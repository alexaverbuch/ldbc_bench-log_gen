package com.ldbc.driver.dshini.generator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import com.ldbc.driver.dshini.generator.RequestLogEntryException;
import com.ldbc.driver.dshini.generator.UrlParsingUtils;

import static org.junit.Assert.assertEquals;

// TODO replace this class with test classes per OperationFactory
// TODO replace this class with test class for RequestLogEntry
public class RequestLogParsingUtilsTest
{
    // @Test
    // public void parseTimeTest() throws RequestLogEntryException
    // {
    // // Given
    // String dshiniLogTimeString = "\"2013-04-29 15:28:19.128978\"";
    // SimpleDateFormat strippedDateFormat = new SimpleDateFormat(
    // "yyyy-MM-dd HH:mm:ss.SSS" );
    // String expectedStrippedDshiniLogTimeString = "2013-04-29 15:28:19.128";
    //
    // // When
    // Date entryDate = new Date( UrlParsingUtils.parseTime( dshiniLogTimeString
    // ) );
    //
    // // Then
    // assertEquals( expectedStrippedDshiniLogTimeString,
    // strippedDateFormat.format( entryDate ) );
    // }

    // @Test
    // public void parseIndexNodeQueryGetTest() throws RequestLogEntryException
    // {
    // // Given
    // String urlString1 =
    // "http://graph.internal.dshini.net:7474/db/data/index/node/neo_pin?query=PinIdentifier:01e768b03de397e65a6d5aea30709bd338263d63";
    // String urlString2 =
    // "http://localhost:7474/db/data/index/node/bobTheIndex?query=Name:Build~0.1%20AND%20Gender:Male";
    //
    // // When
    // IndexQueryDescriptor result1 =
    // RequestLogParsingUtils.parseIndexQueryGetNodeUrl( urlString1 );
    // IndexQueryDescriptor result2 =
    // RequestLogParsingUtils.parseIndexQueryGetNodeUrl( urlString2 );
    //
    // // Then
    // assertEquals( "neo_pin", result1.getIndexName() );
    // assertEquals( "PinIdentifier:01e768b03de397e65a6d5aea30709bd338263d63",
    // result1.getIndexQuery() );
    //
    // assertEquals( "bobTheIndex", result2.getIndexName() );
    // assertEquals( "Name:Build~0.1 AND Gender:Male", result2.getIndexQuery()
    // );
    // }

    @Test
    public void parseIndexNameForIndexNodeUrlTest() throws RequestLogEntryException
    {
        // Given
        String urlString = "http://graph-master.dshini.net:7474/db/data/index/node/neo_pin_url";

        // When
        String indexName = UrlParsingUtils.parseIndexNameFromNodeIndexUrl( urlString );

        // Then
        assertEquals( "neo_pin_url", indexName );
    }

    @Test
    public void parseNodeFromIndexDeleteNodeTest() throws RequestLogEntryException
    {
        String urlString = "http://graph.internal.dshini.net:7474/db/data/index/node/neo_pin/7655702";
        long nodeId = UrlParsingUtils.parseNodeIdFromNodeIndexUrl( urlString );
        assertEquals( 7655702l, nodeId );
    }

    // @Test
    // public void parsePropertiesMapTest() throws RequestLogEntryException
    // {
    // String propertiesMapString =
    // "\"{\"\"UrlHash\"\":\"\"13db037cd6ed7ca73582cc06ee41cb4c7e18b937\"\",\"\"Url\"\":\"\"http:\\/\\/www.facebook.com\\/photo.php?fbid=620155461334520&set=a.161214197228651.43772.161211400562264&type=1&permPage=1\"\",\"\"ObjectType\"\":\"\"NeoPinUrl\"\",\"\"CreatedAt\"\":1367242260}\"";
    // Map<String, Object> propertiesMap =
    // UrlParsingUtils.parseJsonStringObjectMap( propertiesMapString, true );
    // assertEquals( "13db037cd6ed7ca73582cc06ee41cb4c7e18b937",
    // propertiesMap.get( "UrlHash" ) );
    // assertEquals(
    // "http://www.facebook.com/photo.php?fbid=620155461334520&set=a.161214197228651.43772.161211400562264&type=1&permPage=1",
    // propertiesMap.get( "Url" ) );
    // assertEquals( "NeoPinUrl", propertiesMap.get( "ObjectType" ) );
    // assertEquals( 1367242260, propertiesMap.get( "CreatedAt" ) );
    // }

    @Test
    public void parseStartNodeFromCreateRelationshipTest() throws RequestLogEntryException
    {
        String urlString = "http://graph-master.dshini.net:7474/db/data/node/11440883/relationships";
        long nodeId = UrlParsingUtils.parseNodeIdFromNodeRelationshipsUrl( urlString );
        assertEquals( 11440883, nodeId );
    }

    @Test
    public void temp() throws RequestLogEntryException
    {
        String nodeUrl = "http://graph.internal.dshini.net:7474/db/data/node/11154894";
        String nodeRelOutUrl = "http://graph-master.dshini.net:7474/db/data/node/11440798/relationships/out";
        String nodeRelOutTypeUrl = "http://graph.internal.dshini.net:7474/db/data/node/11440798/relationships/out/IS_SPOTLIGHT";

        String nodePatternString = ".*db/data/node/\\d*$";
        Pattern nodePattern = Pattern.compile( nodePatternString );
        assertEquals( true, nodePattern.matcher( nodeUrl ).matches() );
        assertEquals( false, nodePattern.matcher( nodeRelOutUrl ).matches() );
        assertEquals( false, nodePattern.matcher( nodeRelOutTypeUrl ).matches() );

        String nodeRelOutPatternString = ".*db/data/node/\\d*/relationships/out$";
        Pattern nodeRelOutPattern = Pattern.compile( nodeRelOutPatternString );
        assertEquals( false, nodeRelOutPattern.matcher( nodeUrl ).matches() );
        assertEquals( true, nodeRelOutPattern.matcher( nodeRelOutUrl ).matches() );
        assertEquals( false, nodeRelOutPattern.matcher( nodeRelOutTypeUrl ).matches() );

        String nodeRelOutTypePatternString = ".*db/data/node/\\d*/relationships/out/\\w*$";
        Pattern nodeRelOutTypePattern = Pattern.compile( nodeRelOutTypePatternString );
        assertEquals( false, nodeRelOutTypePattern.matcher( nodeUrl ).matches() );
        assertEquals( false, nodeRelOutTypePattern.matcher( nodeRelOutUrl ).matches() );
        assertEquals( true, nodeRelOutTypePattern.matcher( nodeRelOutTypeUrl ).matches() );

        String s = "http://graph-master.dshini.net:7474/db/data/index/node/neo_pin_url";
        assertEquals( true, Pattern.compile( ".*db/data/index/node/[[^/]\\w]*$" ).matcher( s ).matches() );

        String s2 = "httpMethod=DELETE, url=http://graph.internal.dshini.net:7474/db/data/index/node/neo_site/1144137";
        assertEquals( true, Pattern.compile( ".*db/data/index/node/[[^/]\\w]*/\\d*$" ).matcher( s2 ).matches() );

        // "{""to"":""http:\/\/graph.internal.dshini.net:7474\/db\/data\/node\/41982"",""type"":""TROLLS"",""data"":{""CreatedAt"":1367242667}}"
        // String mapString =
        // "\"{\"\"to\"\":\"\"http://graph.internal.dshini.net:7474/db/data/node/41982\"\",\"\"type\"\":\"\"TROLLS\"\",\"\"data\"\":{\"\"CreatedAt\"\":1367242667}}\"";
        // Map<String, Object> map = UrlParsingUtils.parseJsonStringObjectMap(
        // mapString, true );
        // System.out.println( map );
        // System.out.println( map.get( "to" ) );
        // System.out.println( map.get( "type" ) );
        // System.out.println( map.get( "data" ) );
        // System.out.println( ( (Map<String, Object>) map.get( "data" ) ).get(
        // "CreatedAt" ) );
    }

    /*
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
    */
    @Test
    public void batch() throws RequestLogEntryException, JsonGenerationException, JsonMappingException, IOException
    {
        String jsonString = "\"[{\"\"method\"\":\"\"POST\"\",\"\"to\"\":\"\"/node\"\",\"\"body\"\": {\"\"ObjectType\"\":\"\"NeoPin\"\",\"\"Message\"\":\"\"\"\",\"\"CommentsClosed\"\":false,\"\"CreatedAt\"\":1367242102,\"\"PinIdentifier\"\":\"\"279a7016759215a60cfa8d2417b6580696a7a474\"\",\"\"LikeCount\"\":0,\"\"RepinCount\"\":0},\"\"id\"\":0},{\"\"method\"\":\"\"POST\"\",\"\"to\"\":\"\"/index/node/neo_pin\"\",\"\"body\"\": {\"\"key\"\":\"\"ObjectType\"\",\"\"value\"\":\"\"NeoPin\"\",\"\"uri\"\":\"\"{0}\"\"},\"\"id\"\":1}]\"";

        ObjectMapper mapper = new ObjectMapper();
        RequestLogEntry entry = new RequestLogEntry( mapper, "\"2013-04-29 15:32:53.661274\"", "POST",
                "http://graph-master.dshini.net:7474/db/data/batch", jsonString, "" );
        /*
        [
            {   
                method=POST, 
                to=/node, 
                body=
                    {
                        ObjectType=NeoPin, 
                        Message=, 
                        CommentsClosed=false, 
                        CreatedAt=1367242102, 
                        PinIdentifier=279a7016759215a60cfa8d2417b6580696a7a474, 
                        LikeCount=0, 
                        RepinCount=0
                    }, 
                id=0
            }, 
            {
                method=POST, 
                to=/index/node/neo_pin, 
                body=
                    {
                        key=ObjectType, 
                        value=NeoPin, 
                        uri={0}
                    }, 
                id=1
            }
        ]
        */

        List<Map<String, Object>> parsedDescription = entry.getDescriptionAsMapList();

        System.out.println( parsedDescription );

        List<RequestLogEntry> entries = new ArrayList<RequestLogEntry>();

        for ( Map<String, Object> map : parsedDescription )
        {
            String httpMethod = (String) map.get( "method" );
            String url = "db/data" + (String) map.get( "to" );

            Map<String, Object> description = (Map<String, Object>) map.get( "body" );
            System.out.println( description );
            String httpHeaders = "";
            RequestLogEntry innerEntry = new RequestLogEntry( mapper, entry.getTime(), httpMethod, url, description,
                    httpHeaders );
            entries.add( innerEntry );
            System.out.println( innerEntry );
        }

        System.out.println( entries );
    }
}
