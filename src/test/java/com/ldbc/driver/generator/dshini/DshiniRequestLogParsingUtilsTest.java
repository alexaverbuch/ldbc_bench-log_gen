package com.ldbc.driver.generator.dshini;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.ldbc.driver.util.Pair;

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
        String urlString1 = "http://graph.internal.dshini.net:7474/db/data/index/node/neo_pin?query=PinIdentifier:01e768b03de397e65a6d5aea30709bd338263d63";
        Pair<String, String> result1 = RequestLogParsingUtils.parseIndexNodeQueryGet( urlString1 );
        assertEquals( "neo_pin", result1._1() );
        assertEquals( "PinIdentifier:01e768b03de397e65a6d5aea30709bd338263d63", result1._2() );

        String urlString2 = "http://localhost:7474/db/data/index/node/bobTheIndex?query=Name:Build~0.1%20AND%20Gender:Male";
        Pair<String, String> result2 = RequestLogParsingUtils.parseIndexNodeQueryGet( urlString2 );
        assertEquals( "bobTheIndex", result2._1() );
        assertEquals( "Name:Build~0.1 AND Gender:Male", result2._2() );
    }
}
