package com.ldbc.driver.dshini;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.ldbc.driver.dshini.utils.DshiniLogs;

public class DshiniLogsTest
{
    @Test
    public void shouldAssignNewStartTimeToAllOperationsThatIsOffsetByTheCorrectDuration()
    {
        // Given
        int operationFactoryCount = DshiniLogs.allDshiniOperationFactories().size();
        int operationTypeCount = DshiniLogs.allDshiniOperationTypes().size();

        // When

        // Then
        assertThat( operationFactoryCount, is( operationTypeCount ) );
    }
}
