package com.ldbc.driver.generator.dshini;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.ldbc.driver.util.Pair;
import com.ldbc.driver.util.Triple;

import static org.junit.Assert.assertEquals;

public class DshiniRequestLogParsingUtilsTest
{
    @Test
    public void parseTimeTest() throws RequestLogEntryException
    {
        // Given
        String dshiniLogTimeString = "\"2013-04-29 15:28:19.128978\"";
        SimpleDateFormat strippedDateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );
        String expectedStrippedDshiniLogTimeString = "2013-04-29 15:28:19.128";

        // When
        Date entryDate = new Date( RequestLogParsingUtils.parseTime( dshiniLogTimeString ) );

        // Then
        assertEquals( expectedStrippedDshiniLogTimeString, strippedDateFormat.format( entryDate ) );
    }

    @Test
    public void parseCypherTest() throws RequestLogEntryException
    {
        String cypherJsonString;
        CypherQueryDescriptor cypherJson;

        cypherJsonString = "\"{\"\"query\"\":\"\"START n=node({STARTIDS}) MATCH n-[:PINS_ASSET]->other_nodes RETURN other_nodes\"\",\"\"params\"\":{\"\"STARTIDS\"\":9237012}}\"";
        cypherJson = RequestLogParsingUtils.parseCypherDescription( cypherJsonString );
        assertEquals( "START n=node({STARTIDS}) MATCH n-[:PINS_ASSET]->other_nodes RETURN other_nodes",
                cypherJson.getQuery() );
        assertEquals( 9237012, cypherJson.getParams().get( "STARTIDS" ) );

        cypherJsonString = "\"{\"\"query\"\":\"\"START n=node({STARTIDS}) MATCH n-[:PINS_ASSET]->other_nodes RETURN other_nodes\"\",\"\"params\"\":{\"\"STARTIDS\"\":11168794}}\"";
        cypherJson = RequestLogParsingUtils.parseCypherDescription( cypherJsonString );
        assertEquals( "START n=node({STARTIDS}) MATCH n-[:PINS_ASSET]->other_nodes RETURN other_nodes",
                cypherJson.getQuery() );
        assertEquals( 11168794, cypherJson.getParams().get( "STARTIDS" ) );

        cypherJsonString = "\"{\"\"query\"\":\"\"START n=node({BOARD_ID}), user=node({USER_ID}) MATCH n-[:BOARD_SHOWN_ON*0..1]->site<-[rel:SUBSCRIBES]-users WHERE   (users.IsActive! = {USERSISACTIVE}) AND NOT(user = users) RETURN COUNT(users)\"\",\"\"params\"\":{\"\"BOARD_ID\"\":1459891,\"\"USER_ID\"\":13588,\"\"USERSISACTIVE\"\":true}}\"";
        cypherJson = RequestLogParsingUtils.parseCypherDescription( cypherJsonString );
        assertEquals(
                "START n=node({BOARD_ID}), user=node({USER_ID}) MATCH n-[:BOARD_SHOWN_ON*0..1]->site<-[rel:SUBSCRIBES]-users WHERE   (users.IsActive! = {USERSISACTIVE}) AND NOT(user = users) RETURN COUNT(users)",
                cypherJson.getQuery() );
        assertEquals( 1459891, cypherJson.getParams().get( "BOARD_ID" ) );
        assertEquals( 13588, cypherJson.getParams().get( "USER_ID" ) );
        assertEquals( true, cypherJson.getParams().get( "USERSISACTIVE" ) );
    }

    @Test
    public void parseIndexNodeQueryGetTest() throws RequestLogEntryException
    {
        // Given
        String urlString1 = "http://graph.internal.dshini.net:7474/db/data/index/node/neo_pin?query=PinIdentifier:01e768b03de397e65a6d5aea30709bd338263d63";
        String urlString2 = "http://localhost:7474/db/data/index/node/bobTheIndex?query=Name:Build~0.1%20AND%20Gender:Male";

        // When
        Pair<String, String> result1 = RequestLogParsingUtils.parseIndexNodeQueryGet( urlString1 );
        Pair<String, String> result2 = RequestLogParsingUtils.parseIndexNodeQueryGet( urlString2 );

        // Then
        assertEquals( "neo_pin", result1._1() );
        assertEquals( "PinIdentifier:01e768b03de397e65a6d5aea30709bd338263d63", result1._2() );

        assertEquals( "bobTheIndex", result2._1() );
        assertEquals( "Name:Build~0.1 AND Gender:Male", result2._2() );
    }

    @Test
    public void parseIndexNameForAddNodeToIndexTest() throws RequestLogEntryException
    {
        // Given
        String urlString = "http://graph-master.dshini.net:7474/db/data/index/node/neo_pin_url";

        // When
        String indexName = RequestLogParsingUtils.parseIndexNameForAddNodeToIndex( urlString );

        // Then
        assertEquals( "neo_pin_url", indexName );
    }

    @Test
    public void parseKeyValueForAddNodeToIndexTest() throws RequestLogEntryException
    {
        // Given
        String descriptionString1 = "\"{ \"\"key\"\":\"\"ObjectType\"\", \"\"value\"\":\"\"NeoPinUrl\"\", \"\"uri\"\":\"\"http:\\/\\/graph-master.dshini.net:7474\\/db\\/data\\/node\\/11440897\"\"}\"";
        String descriptionString2 = "\"{ \"\"key\"\":\"\"Url\"\",\"\"value\"\":\"\"http:\\/\\/www.facebook.com\\/photo.php?fbid=620155461334520&set=a.161214197228651.43772.161211400562264&type=1&permPage=1\"\",\"\"uri\"\":\"\"http:\\/\\/graph-master.dshini.net:7474\\/db\\/data\\/node\\/11440897\"\"}\"";

        // When
        Triple<String, Object, Long> keyValueNode1 = RequestLogParsingUtils.parseKeyValueNodeForAddNodeToIndex( descriptionString1 );
        Triple<String, Object, Long> keyValueNode2 = RequestLogParsingUtils.parseKeyValueNodeForAddNodeToIndex( descriptionString2 );

        // Then

        assertEquals( "ObjectType", keyValueNode1._1() );
        assertEquals( "NeoPinUrl", keyValueNode1._2() );
        assertEquals( new Long( 11440897 ), keyValueNode1._3() );

        assertEquals( "Url", keyValueNode2._1() );
        assertEquals(
                "http://www.facebook.com/photo.php?fbid=620155461334520&set=a.161214197228651.43772.161211400562264&type=1&permPage=1",
                keyValueNode2._2() );
        assertEquals( new Long( 11440897 ), keyValueNode2._3() );
    }
}
