package com.ldbc.driver.dshini;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.Matchable;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.operations.Dshini;
import com.ldbc.driver.dshini.operations.Dshini.ReadWrite;
import com.ldbc.driver.dshini.operations.core.CreateNodeNeoPinBoardOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipInCategoryOperationFactory;
import com.ldbc.driver.dshini.operations.core.GetNodesOutgoingTrollsRelationshipsOperationFactory;
import com.ldbc.driver.dshini.operations.core.GetRelationshipOperationFactory;
import com.ldbc.driver.dshini.operations.core.UpdateNodeNeoPinOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CheckUsersSubscribedToSitesOperationFactory;
import com.ldbc.driver.dshini.operations.index.AddNodeToNeoPinBoardIndexOperationFactory;
import com.ldbc.driver.util.Bucket;
import com.ldbc.driver.util.Histogram;
import com.ldbc.driver.util.Bucket.DiscreteBucket;

public class DshiniTest
{

    @Test
    public void shouldThrowExceptionIfOperationFactoryAddedMultipleTimes()
    {
        List<Matchable<RequestLogEntry>> factories = new ArrayList<Matchable<RequestLogEntry>>();

        /*
         * Add same factories for a first time
         * Should not throw exceptions
         */

        // AbstractNodeWriteOperationFactory
        assertThat( exceptionWasThrown( factories, new UpdateNodeNeoPinOperationFactory() ), is( false ) );
        assertThat( factories.size(), is( 1 ) );

        // AbstractRelationshipReadOperationFactory
        assertThat( exceptionWasThrown( factories, new GetNodesOutgoingTrollsRelationshipsOperationFactory() ),
                is( false ) );
        assertThat( factories.size(), is( 2 ) );

        // AbstractRelationshipCreateOperationFactory
        assertThat( exceptionWasThrown( factories, new CreateRelationshipInCategoryOperationFactory() ), is( false ) );
        assertThat( factories.size(), is( 3 ) );

        // AbstractNodeWriteOperationFactory
        assertThat( exceptionWasThrown( factories, new CreateNodeNeoPinBoardOperationFactory() ), is( false ) );
        assertThat( factories.size(), is( 4 ) );

        // AbstractOperationFactory
        assertThat( exceptionWasThrown( factories, new GetRelationshipOperationFactory() ), is( false ) );
        assertThat( factories.size(), is( 5 ) );

        // AbstractCypherOperationFactory
        assertThat( exceptionWasThrown( factories, new CheckUsersSubscribedToSitesOperationFactory() ), is( false ) );
        assertThat( factories.size(), is( 6 ) );

        // AbstractIndexOperationFactory
        assertThat( exceptionWasThrown( factories, new AddNodeToNeoPinBoardIndexOperationFactory() ), is( false ) );
        assertThat( factories.size(), is( 7 ) );

        /*
         * Add same factories for a second time
         * Should throw exceptions
         */

        // AbstractNodeWriteOperationFactory
        assertThat( exceptionWasThrown( factories, new UpdateNodeNeoPinOperationFactory() ), is( true ) );
        assertThat( factories.size(), is( 7 ) );

        // AbstractRelationshipReadOperationFactory
        assertThat( exceptionWasThrown( factories, new GetNodesOutgoingTrollsRelationshipsOperationFactory() ),
                is( true ) );
        assertThat( factories.size(), is( 7 ) );

        // AbstractRelationshipCreateOperationFactory
        assertThat( exceptionWasThrown( factories, new CreateRelationshipInCategoryOperationFactory() ), is( true ) );
        assertThat( factories.size(), is( 7 ) );

        // AbstractNodeWriteOperationFactory
        assertThat( exceptionWasThrown( factories, new CreateNodeNeoPinBoardOperationFactory() ), is( true ) );
        assertThat( factories.size(), is( 7 ) );

        // AbstractOperationFactory
        assertThat( exceptionWasThrown( factories, new GetRelationshipOperationFactory() ), is( true ) );
        assertThat( factories.size(), is( 7 ) );

        // AbstractCypherOperationFactory
        assertThat( exceptionWasThrown( factories, new CheckUsersSubscribedToSitesOperationFactory() ), is( true ) );
        assertThat( factories.size(), is( 7 ) );

        // AbstractIndexOperationFactory
        assertThat( exceptionWasThrown( factories, new AddNodeToNeoPinBoardIndexOperationFactory() ), is( true ) );
        assertThat( factories.size(), is( 7 ) );
    }

    private boolean exceptionWasThrown( List<Matchable<RequestLogEntry>> matchables,
            Matchable<RequestLogEntry> matchable )
    {
        boolean exceptionThrown = false;
        try
        {
            matchables = Dshini.addIfNotExists( matchables, matchable );
        }
        catch ( Exception e )
        {
            exceptionThrown = true;
        }
        return exceptionThrown;
    }

    @Test
    public void shouldBeSameNumberOfUniqueOperationFactoriesAsUniqueOperations()
    {
        // Given
        Histogram<String, Integer> dshiniOperationTypeNames = new Histogram<String, Integer>( 0 );

        // When
        for ( Class<? extends Operation<?>> operationType : Dshini.operations().all( ReadWrite.READWRITE ) )
        {
            dshiniOperationTypeNames.incOrCreateBucket( DiscreteBucket.create( operationType.getSimpleName() ), 1 );
        }

        // Then
        for ( Entry<Bucket<String>, Integer> bucket : dshiniOperationTypeNames.getAllBuckets() )
        {
            assertThat( String.format( "Operation [%s] should appear only once", bucket.getKey() ), bucket.getValue(),
                    is( 1 ) );
        }

        assertThat( Dshini.factories().all( ReadWrite.READWRITE ).size(),
                is( Dshini.operations().all( ReadWrite.READWRITE ).size() ) );
        assertThat( Dshini.operations().all( ReadWrite.READWRITE ).size(),
                is( dshiniOperationTypeNames.getBucketCount() ) );
        assertThat( Dshini.factories().all( ReadWrite.READWRITE ).size(),
                is( dshiniOperationTypeNames.getBucketCount() ) );
    }

    @Test
    public void allOperationFactoriesShouldBeUnique()
    {
        // Given
        Histogram<String, Integer> operationFactoryClassNames = new Histogram<String, Integer>( 0 );

        // When
        for ( Matchable<?> operationFactoryType : Dshini.factories().all( ReadWrite.READWRITE ) )
        {
            operationFactoryClassNames.incOrCreateBucket(
                    DiscreteBucket.create( operationFactoryType.getClass().getSimpleName() ), 1 );
        }

        // Then
        for ( Entry<Bucket<String>, Integer> bucket : operationFactoryClassNames.getAllBuckets() )
        {
            assertThat( String.format( "Operation [%s] should appear only once", bucket.getKey() ), bucket.getValue(),
                    is( 1 ) );
        }
    }

    @Test
    public void allOperationsShouldBeUnique()
    {
        // Given
        Histogram<String, Integer> operationClassNames = new Histogram<String, Integer>( 0 );

        // When
        for ( Class<? extends Operation<?>> operationType : Dshini.operations().all( ReadWrite.READWRITE ) )
        {
            operationClassNames.incOrCreateBucket( DiscreteBucket.create( operationType.getSimpleName() ), 1 );
        }

        // Then
        for ( Entry<Bucket<String>, Integer> bucket : operationClassNames.getAllBuckets() )
        {
            assertThat( String.format( "Operation [%s] should appear only once", bucket.getKey() ), bucket.getValue(),
                    is( 1 ) );
        }
    }

    @Test
    public void shouldBeSameAmountOfUniqueOperationsAsOperationFactories()
    {
        // Given
        Histogram<String, Integer> operationFactoryClassNames = new Histogram<String, Integer>( 0 );
        Histogram<String, Integer> operationClassNames = new Histogram<String, Integer>( 0 );

        // When
        for ( Matchable<?> operationFactoryType : Dshini.factories().all( ReadWrite.READWRITE ) )
        {
            operationFactoryClassNames.incOrCreateBucket(
                    DiscreteBucket.create( operationFactoryType.getClass().getSimpleName() ), 1 );
        }
        for ( Class<? extends Operation<?>> operationType : Dshini.operations().all( ReadWrite.READWRITE ) )
        {
            operationClassNames.incOrCreateBucket( DiscreteBucket.create( operationType.getSimpleName() ), 1 );
        }

        // Then
        assertThat( operationFactoryClassNames.getBucketCount(), is( operationClassNames.getBucketCount() ) );
    }

    @Test
    public void shouldBeNoNullOperationTypes()
    {
        // Given
        // When

        for ( Matchable<?> matchable : Dshini.factories().all( ReadWrite.READWRITE ) )
        {
            Class<? extends Operation<?>> operationType = matchable.operationType();
            // Then
            assertThat( matchable.getClass().getSimpleName() + " should not return null operationType", operationType,
                    notNullValue() );
        }

    }

}
