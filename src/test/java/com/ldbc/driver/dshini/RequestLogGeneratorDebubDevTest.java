package com.ldbc.driver.dshini;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.*;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.operations.Dshini;
import com.ldbc.driver.generator.wrapper.OrderedMultiGeneratorWrapper;

public class RequestLogGeneratorDebubDevTest
{

    @Ignore
    @Test
    public void performanceTestNoLookahead()
    {
        doPerformanceTest( 1 );
    }

    @Ignore
    @Test
    public void performanceTestLookahead()
    {
        doPerformanceTest( 3 );
    }

    public void doPerformanceTest( int lookaheadDistance )
    {
        System.out.println( "Lookahead = " + lookaheadDistance );

        // Given
        OrderedMultiGeneratorWrapper<Operation<?>> requestLogGenerator = OrderedMultiGeneratorWrapper.operationsByScheduledStartTime(
                lookaheadDistance, TestUtils.allLogGenerators() );

        // When
        long operations = 0;
        long startTime = System.nanoTime();
        while ( requestLogGenerator.hasNext() )
        {
            requestLogGenerator.next();
            operations++;
        }
        long endTime = System.nanoTime();

        long runtime = ( endTime - startTime ) / 1000000000;
        System.out.println( String.format( "Runtime: %s seconds", runtime ) );
        System.out.println( String.format( "Operations: %s ", operations ) );
        System.out.println( String.format( "Throughput: %s (operations/second)", operations / runtime ) );

        // Then
        assertEquals( 13049989, operations );
    }
}
