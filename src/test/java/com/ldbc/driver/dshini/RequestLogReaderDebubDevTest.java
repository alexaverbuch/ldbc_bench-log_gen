package com.ldbc.driver.dshini;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.log.RequestLogEntryReader;
import com.ldbc.driver.dshini.utils.UrlParsingUtils;
import com.ldbc.driver.dshini.utils.UrlPatterns;
import com.ldbc.driver.util.Pair;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.*;

@Ignore
public class RequestLogReaderDebubDevTest
{
    @Ignore
    @Test
    public void performanceTest() throws MatchableException, RequestLogEntryException
    {
        // Given
        RequestLogEntryReader[] readers = TestUtils.allLogReaders();

        // When
        int entries = 0;
        long startTime = System.nanoTime();

        for ( RequestLogEntryReader reader : readers )
        {
            while ( reader.hasNext() )
            {
                RequestLogEntry entry = reader.next();
                entries++;
            }
        }

        long endTime = System.nanoTime();

        long runtime = ( endTime - startTime ) / 1000000000;
        System.out.println( String.format( "Runtime: %s seconds", runtime ) );
        System.out.println( String.format( "Entries: %s ", entries ) );
        System.out.println( String.format( "Throughput: %s (entries/second)", entries / runtime ) );

        // Then
        assertEquals( 13049991l, entries );
    }

    @Ignore
    @Test
    public void countUniqueCypherQueries() throws MatchableException, RequestLogEntryException
    {
        // Given
        RequestLogEntryReader[] readers = TestUtils.allLogReaders();

        Set<String> cypherQueries = new HashSet<String>();

        // When
        for ( RequestLogEntryReader reader : readers )
        {
            while ( reader.hasNext() )
            {
                try
                {
                    RequestLogEntry entry = reader.next();
                    if ( UrlPatterns.CYPHER.matcher( entry.getUrl() ).matches() )
                    {
                        String cypherQueryString = (String) entry.getDescriptionAsMap().get( "query" );
                        cypherQueries.add( cypherQueryString );
                    }
                }
                catch ( RequestLogEntryException e )
                {
                }
            }
        }

        // Then
        System.out.println( cypherQueries.size() );
        System.out.println( cypherQueries.toString() );
    }

    @Ignore
    @Test
    public void countUniqueIndexNamesForAddNodeToIndexQueries() throws MatchableException, RequestLogEntryException
    {
        // Given
        RequestLogEntryReader[] readers = TestUtils.allLogReaders();

        Set<String> indexNames = new HashSet<String>();

        // When
        for ( RequestLogEntryReader reader : readers )
        {
            while ( reader.hasNext() )
            {
                try
                {
                    RequestLogEntry entry = reader.next();

                    if ( entry.getHttpMethod().equals( "POST" )
                         && UrlPatterns.ADD_NODE_TO_INDEX.matcher( entry.getUrl() ).matches() )
                    {
                        String indexName = UrlParsingUtils.parseIndexNameFromNodeIndexUrl( entry.getUrl() );
                        indexNames.add( indexName );
                    }
                }
                catch ( RequestLogEntryException e )
                {
                }
            }
        }

        // Then
        System.out.println( indexNames.size() );
        System.out.println( indexNames.toString() );
    }

    @Ignore
    @Test
    public void countUniqueIndexNamesForDeleteNodeFromIndexQueries() throws MatchableException,
            RequestLogEntryException
    {
        // Given
        RequestLogEntryReader[] readers = TestUtils.allLogReaders();

        Set<String> indexNames = new HashSet<String>();

        // When
        for ( RequestLogEntryReader reader : readers )
        {
            while ( reader.hasNext() )
            {
                try
                {
                    RequestLogEntry entry = reader.next();

                    if ( entry.getHttpMethod().equals( "DELETE" )
                         && UrlPatterns.DELETE_NODE_FROM_INDEX.matcher( entry.getUrl() ).matches() )
                    {
                        String indexName = UrlParsingUtils.parseIndexNameFromNodeIndexUrl( entry.getUrl() );
                        indexNames.add( indexName );
                    }
                }
                catch ( RequestLogEntryException e )
                {
                }
            }
        }

        // Then
        System.out.println( indexNames.size() );
        System.out.println( indexNames.toString() );
    }

    @Ignore
    @Test
    public void countUniqueIndexNamesForIndexQueryNodeQueries() throws MatchableException, RequestLogEntryException
    {
        // Given
        RequestLogEntryReader[] readers = TestUtils.allLogReaders();

        Set<String> indexNames = new HashSet<String>();
        Set<String> indexQueries = new HashSet<String>();

        // When
        for ( RequestLogEntryReader reader : readers )
        {
            while ( reader.hasNext() )
            {
                try
                {
                    RequestLogEntry entry = reader.next();

                    if ( entry.getHttpMethod().equals( "GET" )
                         && UrlPatterns.INDEX_QUERY_NODE.matcher( entry.getUrl() ).matches() )
                    {
                        String indexName = UrlParsingUtils.parseIndexNameFromNodeIndexUrl( entry.getUrl() );
                        indexNames.add( indexName );

                        String indexQuery = UrlParsingUtils.parseIndexQueryFromNodeIndexQueryUrl( entry.getUrl() );
                        // indexQueries.add( indexQuery );
                        // System.out.println( indexQuery );
                    }
                }
                catch ( RequestLogEntryException e )
                {
                }
            }
        }

        // Then
        System.out.println( "names = " + indexNames.size() );
        System.out.println( indexNames.toString() );
        System.out.println( "queries = " + indexQueries.size() );
    }

    @Ignore
    @Test
    public void countUniqueRelationshipTypesForGetNodesRelationshipsQueries() throws MatchableException,
            RequestLogEntryException
    {
        // Given
        RequestLogEntryReader[] readers = TestUtils.allLogReaders();

        Set<String> relationshipTypes = new HashSet<String>();

        // When
        for ( RequestLogEntryReader reader : readers )
        {
            while ( reader.hasNext() )
            {
                try
                {
                    RequestLogEntry entry = reader.next();

                    if ( entry.getHttpMethod().equals( "GET" )
                         && ( UrlPatterns.GET_NODE_TYPED_IN_RELATIONSHIPS.matcher( entry.getUrl() ).matches() || UrlPatterns.GET_NODE_TYPED_OUT_RELATIONSHIPS.matcher(
                                 entry.getUrl() ).matches() ) )
                    {
                        String relationshipType = UrlParsingUtils.parseRelationshipTypeFromNodeRelationshipsUrl( entry.getUrl() );
                        relationshipTypes.add( relationshipType );
                    }
                }
                catch ( RequestLogEntryException e )
                {
                }
            }
        }

        // Then
        System.out.println( "names = " + relationshipTypes.size() );
        System.out.println( relationshipTypes.toString() );
    }

    @Ignore
    @Test
    public void countUniqueCreateNodeQueries() throws MatchableException, RequestLogEntryException
    {
        // Given
        RequestLogEntryReader[] readers = TestUtils.allLogReaders();

        Set<Pair<String, Set<String>>> propertyKeySets = new HashSet<Pair<String, Set<String>>>();

        // When
        for ( RequestLogEntryReader reader : readers )
        {
            while ( reader.hasNext() )
            {
                try
                {
                    RequestLogEntry entry = reader.next();

                    if ( entry.getHttpMethod().equals( "POST" )
                         && UrlPatterns.CREATE_NODE.matcher( entry.getUrl() ).matches() )
                    {
                        Set<String> propertyKeys = entry.getDescriptionAsMap().keySet();
                        String objectType = (String) entry.getDescriptionAsMap().get( "ObjectType" );
                        propertyKeySets.add( Pair.create( objectType, propertyKeys ) );
                    }
                }
                catch ( RequestLogEntryException e )
                {
                }
            }
        }

        // Then
        System.out.println( "unique property key sets = " + propertyKeySets.size() );
        System.out.println( propertyKeySets.toString() );
    }

    @Ignore
    @Test
    public void countUniqueUpdateNodeQueries() throws MatchableException, RequestLogEntryException
    {
        // Given
        RequestLogEntryReader[] readers = TestUtils.allLogReaders();

        Set<Pair<String, Set<String>>> propertyKeySets = new HashSet<Pair<String, Set<String>>>();

        // When
        for ( RequestLogEntryReader reader : readers )
        {
            while ( reader.hasNext() )
            {
                try
                {
                    RequestLogEntry entry = reader.next();

                    if ( entry.getHttpMethod().equals( "PUT" )
                         && UrlPatterns.UPDATE_NODE.matcher( entry.getUrl() ).matches() )
                    {
                        Set<String> propertyKeys = entry.getDescriptionAsMap().keySet();

                        String objectType = null;
                        if ( entry.getDescriptionAsMap().containsKey( "ObjectType" ) )
                        {
                            objectType = (String) entry.getDescriptionAsMap().get( "ObjectType" );
                        }

                        propertyKeySets.add( Pair.create( objectType, propertyKeys ) );
                    }
                }
                catch ( RequestLogEntryException e )
                {
                }
            }
        }

        // Then
        System.out.println( "unique property key sets = " + propertyKeySets.size() );
        System.out.println( propertyKeySets.toString() );
    }

    @Ignore
    @Test
    public void countUniqueCreateRelationshipQueries() throws MatchableException, RequestLogEntryException
    {
        // Given
        RequestLogEntryReader[] readers = TestUtils.allLogReaders();

        Set<Pair<String, Set<String>>> createRelationshipTypes = new HashSet<Pair<String, Set<String>>>();

        // When
        for ( RequestLogEntryReader reader : readers )
        {
            while ( reader.hasNext() )
            {
                try
                {
                    RequestLogEntry entry = reader.next();

                    if ( entry.getHttpMethod().equals( "POST" )
                         && UrlPatterns.CREATE_RELATIONSHIP.matcher( entry.getUrl() ).matches() )
                    {
                        String relationshipType = (String) entry.getDescriptionAsMap().get( "type" );
                        Set<String> propertyKeys = null;
                        Object data = entry.getDescriptionAsMap().get( "data" );
                        if ( null != data )
                        {
                            propertyKeys = ( (Map<String, Object>) data ).keySet();
                        }

                        createRelationshipTypes.add( Pair.create( relationshipType, propertyKeys ) );
                    }
                }
                catch ( RequestLogEntryException e )
                {
                }
            }
        }

        // Then
        System.out.println( "create relationship types = " + createRelationshipTypes.size() );
        System.out.println( createRelationshipTypes.toString() );
    }
}
