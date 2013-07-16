package com.ldbc.driver.dshini;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.util.temporal.Time;

public class RequestLogEntryTest
{

    final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldReturnSameTimeAsGiven() throws RequestLogEntryException
    {
        // Given
        String dshiniLogTimeString = "\"2013-04-29 15:28:19.128978\"";
        String httpMethod = null;
        String url = null;
        String description = null;
        String httpHeaders = null;

        // When
        RequestLogEntry entry = new RequestLogEntry( mapper, dshiniLogTimeString, httpMethod, url, description,
                httpHeaders );

        Time time = entry.getTime();
        Date entryDate = new Date( time.asMilli() );

        SimpleDateFormat strippedDateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );
        String expectedStrippedDshiniLogTimeString = "2013-04-29 15:28:19.128";

        // Then
        assertEquals( expectedStrippedDshiniLogTimeString, strippedDateFormat.format( entryDate ) );
    }

    @Test
    public void shouldParseJsonPropertiesMap() throws RequestLogEntryException
    {
        // Given
        String dshiniLogTimeString = "\"2013-04-29 15:28:19.128978\"";
        String httpMethod = null;
        String url = null;
        String description = "\"{\"\"UrlHash\"\":\"\"13db037cd6ed7ca73582cc06ee41cb4c7e18b937\"\",\"\"Url\"\":\"\"http:\\/\\/www.facebook.com\\/photo.php?fbid=620155461334520&set=a.161214197228651.43772.161211400562264&type=1&permPage=1\"\",\"\"ObjectType\"\":\"\"NeoPinUrl\"\",\"\"CreatedAt\"\":1367242260}\"";
        String httpHeaders = null;

        // When
        RequestLogEntry entry = new RequestLogEntry( mapper, dshiniLogTimeString, httpMethod, url, description,
                httpHeaders );

        Map<String, Object> propertiesMap = entry.getDescriptionAsMap();

        // Then
        assertEquals( "13db037cd6ed7ca73582cc06ee41cb4c7e18b937", propertiesMap.get( "UrlHash" ) );
        assertEquals(
                "http://www.facebook.com/photo.php?fbid=620155461334520&set=a.161214197228651.43772.161211400562264&type=1&permPage=1",
                propertiesMap.get( "Url" ) );
        assertEquals( "NeoPinUrl", propertiesMap.get( "ObjectType" ) );
        assertEquals( 1367242260, propertiesMap.get( "CreatedAt" ) );
    }

    @Test
    public void shouldParseJsonPropertiesMapList() throws RequestLogEntryException
    {
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

        // Given
        String dshiniLogTimeString = "\"2013-04-29 15:28:19.128978\"";
        String httpMethod = null;
        String url = null;
        String description = "\"[{\"\"method\"\":\"\"POST\"\",\"\"to\"\":\"\"/node\"\",\"\"body\"\": {\"\"ObjectType\"\":\"\"NeoPin\"\",\"\"Message\"\":\"\"\"\",\"\"CommentsClosed\"\":false,\"\"CreatedAt\"\":1367242102,\"\"PinIdentifier\"\":\"\"279a7016759215a60cfa8d2417b6580696a7a474\"\",\"\"LikeCount\"\":0,\"\"RepinCount\"\":0},\"\"id\"\":0},{\"\"method\"\":\"\"POST\"\",\"\"to\"\":\"\"/index/node/neo_pin\"\",\"\"body\"\": {\"\"key\"\":\"\"ObjectType\"\",\"\"value\"\":\"\"NeoPin\"\",\"\"uri\"\":\"\"{0}\"\"},\"\"id\"\":1}]\"";
        String httpHeaders = null;

        // When
        RequestLogEntry entry = new RequestLogEntry( mapper, dshiniLogTimeString, httpMethod, url, description,
                httpHeaders );

        // Then
        assertEquals( 2, entry.getDescriptionAsMapList().size() );

        assertEquals( "POST", entry.getDescriptionAsMapList().get( 0 ).get( "method" ) );
        assertEquals( "/node", entry.getDescriptionAsMapList().get( 0 ).get( "to" ) );
        assertEquals( "279a7016759215a60cfa8d2417b6580696a7a474",
                ( (Map<String, Object>) entry.getDescriptionAsMapList().get( 0 ).get( "body" ) ).get( "PinIdentifier" ) );

        assertEquals( "POST", entry.getDescriptionAsMapList().get( 1 ).get( "method" ) );
        assertEquals( "/index/node/neo_pin", entry.getDescriptionAsMapList().get( 1 ).get( "to" ) );
        assertEquals( "NeoPin",
                ( (Map<String, Object>) entry.getDescriptionAsMapList().get( 1 ).get( "body" ) ).get( "value" ) );
    }

}
