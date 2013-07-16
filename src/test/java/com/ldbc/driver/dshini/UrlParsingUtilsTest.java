package com.ldbc.driver.dshini;

import org.junit.Test;

import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.utils.UrlParsingUtils;

import static org.junit.Assert.assertEquals;

public class UrlParsingUtilsTest
{

    @Test
    public void shouldParseNodeIdFromNodeIndexUrl() throws RequestLogEntryException
    {
        // Given
        String urlString = "http://graph.internal.dshini.net:7474/db/data/index/node/neo_pin/7655702";

        // When
        long nodeId = UrlParsingUtils.parseNodeIdFromNodeIndexUrl( urlString );

        // Then
        assertEquals( 7655702l, nodeId );
    }

    @Test
    public void shouldParseRelationshipTypeFromNodeRelationshipsUrl() throws RequestLogEntryException
    {
        // Given
        String urlString1 = "http://graph.internal.dshini.net:7474/db/data/node/11251602/relationships/in/REL_TYPE_IN";
        String urlString2 = "http://graph.internal.dshini.net:7474/db/data/node/11251602/relationships/out/REL_TYPE_OUT";
        String urlString3 = "http://graph.internal.dshini.net:7474/db/data/node/11251602/relationships/all/REL_TYPE_ALL";

        // When
        String relType1 = UrlParsingUtils.parseRelationshipTypeFromNodeRelationshipsUrl( urlString1 );
        String relType2 = UrlParsingUtils.parseRelationshipTypeFromNodeRelationshipsUrl( urlString2 );
        String relType3 = UrlParsingUtils.parseRelationshipTypeFromNodeRelationshipsUrl( urlString3 );

        // Then
        assertEquals( "REL_TYPE_IN", relType1 );
        assertEquals( "REL_TYPE_OUT", relType2 );
        assertEquals( "REL_TYPE_ALL", relType3 );
    }

    @Test
    public void shouldParseNodeIdFromNodeRelationshipsUrl() throws RequestLogEntryException
    {
        // Given
        String urlString1 = "http://graph-master.dshini.net:7474/db/data/node/11440883/relationships";
        String urlString2 = "http://graph-master.dshini.net:7474/db/data/node/12345/relationships/in/REL_TYPE_IN";
        String urlString3 = "http://graph-master.dshini.net:7474/db/data/node/234/relationships/out/REL_TYPE_OUT";
        String urlString4 = "http://graph-master.dshini.net:7474/db/data/node/999/relationships/all/REL_TYPE_OUT";
        String urlString5 = "http://graph-master.dshini.net:7474/db/data/node/111/relationships/all";
        //
        // When
        long nodeId1 = UrlParsingUtils.parseNodeIdFromNodeRelationshipsUrl( urlString1 );
        long nodeId2 = UrlParsingUtils.parseNodeIdFromNodeRelationshipsUrl( urlString2 );
        long nodeId3 = UrlParsingUtils.parseNodeIdFromNodeRelationshipsUrl( urlString3 );
        long nodeId4 = UrlParsingUtils.parseNodeIdFromNodeRelationshipsUrl( urlString4 );
        long nodeId5 = UrlParsingUtils.parseNodeIdFromNodeRelationshipsUrl( urlString5 );

        // Then
        assertEquals( 11440883, nodeId1 );
        assertEquals( 12345, nodeId2 );
        assertEquals( 234, nodeId3 );
        assertEquals( 999, nodeId4 );
        assertEquals( 111, nodeId5 );
    }

    @Test
    public void shouldParseNodeIdFromNodeUrl() throws RequestLogEntryException
    {
        // Given
        String urlString1 = "http://graph-master.dshini.net:7474/db/data/node/11455077/properties";
        String urlString2 = "http://graph-master.dshini.net:7474/db/data/node/12345";

        // When
        long nodeId1 = UrlParsingUtils.parseNodeIdFromNodeUrl( urlString1 );
        long nodeId2 = UrlParsingUtils.parseNodeIdFromNodeUrl( urlString2 );

        // Then
        assertEquals( 11455077, nodeId1 );
        assertEquals( 12345, nodeId2 );
    }

    @Test
    public void shouldParseRelationshipIdFromRelationshipUrl() throws RequestLogEntryException
    {
        // Given
        String urlString = "http://graph-master.dshini.net:7474/db/data/relationship/883198";

        // When
        long relationshipId = UrlParsingUtils.parseRelationshipIdFromRelationshipUrl( urlString );

        // Then
        assertEquals( 883198, relationshipId );
    }

    @Test
    public void shouldParseIndexNameFromNodeIndexUrl() throws RequestLogEntryException
    {
        // Given
        String urlString = "http://graph-master.dshini.net:7474/db/data/index/node/neo_pin_url";

        // When
        String indexName = UrlParsingUtils.parseIndexNameFromNodeIndexUrl( urlString );

        // Then
        assertEquals( "neo_pin_url", indexName );
    }

    @Test
    public void shouldParseIndexNameForNodeIndexQueryUrl() throws RequestLogEntryException
    {
        // Given
        String urlString1 = "http://graph.internal.dshini.net:7474/db/data/index/node/neo_pin?query=PinIdentifier:01e768b03de397e65a6d5aea30709bd338263d63";
        String urlString2 = "http://localhost:7474/db/data/index/node/bobTheIndex?query=Name:Build~0.1%20AND%20Gender:Male";

        // When
        String indexName1 = UrlParsingUtils.parseIndexNameForNodeIndexQueryUrl( urlString1 );
        String indexName2 = UrlParsingUtils.parseIndexNameForNodeIndexQueryUrl( urlString2 );

        // Then
        assertEquals( "neo_pin", indexName1 );
        assertEquals( "bobTheIndex", indexName2 );
    }

    @Test
    public void shouldParseIndexQueryFromNodeIndexQueryUrl() throws RequestLogEntryException
    {
        // Given
        String urlString1 = "http://graph.internal.dshini.net:7474/db/data/index/node/neo_pin?query=PinIdentifier:01e768b03de397e65a6d5aea30709bd338263d63";
        String urlString2 = "http://localhost:7474/db/data/index/node/bobTheIndex?query=Name:Build~0.1%20AND%20Gender:Male";

        // When
        String indexQuery1 = UrlParsingUtils.parseIndexQueryFromNodeIndexQueryUrl( urlString1 );
        String indexQuery2 = UrlParsingUtils.parseIndexQueryFromNodeIndexQueryUrl( urlString2 );

        // Then
        assertEquals( "PinIdentifier:01e768b03de397e65a6d5aea30709bd338263d63", indexQuery1 );
        assertEquals( "Name:Build~0.1 AND Gender:Male", indexQuery2 );
    }
}
