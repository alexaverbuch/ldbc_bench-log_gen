package com.ldbc.driver.dshini;

import java.io.File;

import org.junit.Test;

import com.ldbc.driver.dshini.log.RequestLogEntryReader;

import static com.ldbc.driver.util.TestUtils.getResource;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.*;

public class RequestLogReaderTest
{

    @Test
    public void shouldNotFailWhenHasNextCalledAfterReturningFalse()
    {
        // Given
        File requestLogFile = getResource( "/test_request_log.log" );

        // When
        RequestLogEntryReader reader = new RequestLogEntryReader( requestLogFile );
        while ( reader.hasNext() )
        {
            reader.next();
        }

        // Then
        assertThat( reader.hasNext(), is( false ) );
        assertThat( reader.hasNext(), is( false ) );
    }
}
