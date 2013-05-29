package com.ldbc.driver.generator.dshini;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DshiniRequestLogEntryTest
{
    @Test
    public void testTimeFormatTest() throws DshiniRequestLogEntryException
    {
        // Given
        String dshiniLogTimeString = "2013-04-29 15:28:19.128978";
        SimpleDateFormat strippedDateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );
        String expectedStrippedDshiniLogTimeString = "2013-04-29 15:28:19.128";

        // When
        DshiniRequestLogEntry entry = new DshiniRequestLogEntry( dshiniLogTimeString, null, null, null, null );
        Date entryDate = new Date( entry.getTime() );

        // Then
        assertEquals( expectedStrippedDshiniLogTimeString, strippedDateFormat.format( entryDate ) );
    }
}
