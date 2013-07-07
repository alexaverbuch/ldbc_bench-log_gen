package com.ldbc.driver.dshini;

import java.util.HashMap;
import java.util.Map;

import com.ldbc.driver.BenchmarkPhase;
import com.ldbc.driver.Client;
import com.ldbc.driver.Db;
import com.ldbc.driver.DbException;
import com.ldbc.driver.Workload;
import com.ldbc.driver.WorkloadException;
import com.ldbc.driver.WorkloadRunner;
import com.ldbc.driver.db.basic.BasicDb;
import com.ldbc.driver.dshini.db.neo4j.Neo4jDb;
import com.ldbc.driver.dshini.workloads.DshiniWorkload;
import com.ldbc.driver.generator.GeneratorBuilder;
import com.ldbc.driver.measurements.MetricsExporterException;
import com.ldbc.driver.measurements.WorkloadMetricsManager;
import com.ldbc.driver.measurements.exporters.MetricsExporter;
import com.ldbc.driver.measurements.exporters.OutputStreamMetricsExporter;
import com.ldbc.driver.measurements.formatters.MetricsFormatter;
import com.ldbc.driver.measurements.formatters.SimpleMetricsFormatter;
import com.ldbc.driver.util.RandomDataGeneratorFactory;
import com.ldbc.driver.util.temporal.TimeUnit;
import com.ldbc.driver.workloads.simple.SimpleWorkload;

public class Example
{
    public static void main( String[] args ) throws DbException, WorkloadException, MetricsExporterException
    {
        doSimpleWorkload();
        doDshiniNeo4jWorkload();
    }

    /**
     * Simple CRUD workload. BasicDb connector just sleeps 100ms per operation.
     */
    private static void doSimpleWorkload() throws DbException, WorkloadException, MetricsExporterException
    {
        Db db = new BasicDb();
        db.init( new HashMap<String, String>() );

        // (TODO update as necessary) Number of operations to generate
        long operationCount = 100;
        // First operation (ignore unless using multiple loadgen machines)
        long operationStart = 0;
        // Number of records (sometimes useful for knowing max key)
        long recordCount = 100;

        Workload workload = new SimpleWorkload();
        workload.init( operationCount, operationStart, recordCount, new HashMap<String, String>() );

        BenchmarkPhase phase = BenchmarkPhase.LOAD_PHASE;
        GeneratorBuilder generatorBuilder = new GeneratorBuilder( new RandomDataGeneratorFactory( 42l ) );
        boolean showStatus = true;
        int threadPoolSize = Client.defaultThreadCount();
        TimeUnit durationUnit = TimeUnit.MILLI;
        WorkloadMetricsManager metricsManager = new WorkloadMetricsManager( durationUnit );

        WorkloadRunner workloadRunner = new WorkloadRunner( db, phase, workload, generatorBuilder, showStatus,
                threadPoolSize, metricsManager );

        workloadRunner.run();

        MetricsFormatter formatter = new SimpleMetricsFormatter();
        MetricsExporter exporter = new OutputStreamMetricsExporter( System.out );
        exporter.export( formatter, metricsManager.getAllMeasurements() );
    }

    /**
     * Dshini request logs workload, currently filtered to only read operations.
     * Neo4jDb implements most operations, excluding Cypher & Batch (#todo).
     */
    private static void doDshiniNeo4jWorkload() throws DbException, WorkloadException, MetricsExporterException
    {
        /*
         * neo4j.url=http://localhost:7474/db/data
         * neo4j.clear=false
         * neo4j.path=/tmp/db
         * neo4j.dbtype=embedded / server
         */
        Map<String, String> dbProperties = new HashMap<String, String>();
        dbProperties.put( "neo4j.clear", "false" );
        dbProperties.put( "neo4j.path", "/home/alex/Desktop/Dshini/dshini-graphdb-backup-2013-04-29" );
        dbProperties.put( "neo4j.dbtype", "embedded" );
        Db db = new Neo4jDb();
        db.init( dbProperties );

        // (TODO update as necessary, -1 means read all)
        // Number of operations to generate
        long operationCount = 10;
        // First operation (ignore unless using multiple loadgen machines)
        long operationStart = 0;
        // Number of records (sometimes useful for knowing max key) - not used
        long recordCount = 0;

        Map<String, String> workloadProperties = new HashMap<String, String>();
        // TODO remove some log files if you wish to run fewer logs/operations
        String logFiles = "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log,logs/dshini-request-logs-2013-04-29/request-ip-10-196-162-95.log,logs/dshini-request-logs-2013-04-29/request-ip-10-76-97-169.log,logs/dshini-request-logs-2013-04-29/request-ip-10-84-146-61.log,logs/dshini-request-logs-2013-04-29/request-ip-10-90-59-251.log,logs/dshini-request-logs-2013-04-29/request-ip-10-98-203-214.log";
        workloadProperties.put( "logfiles", logFiles );
        Workload workload = new DshiniWorkload();
        workload.init( operationCount, operationStart, recordCount, workloadProperties );

        BenchmarkPhase phase = BenchmarkPhase.TRANSACTION_PHASE;
        GeneratorBuilder generatorBuilder = new GeneratorBuilder( new RandomDataGeneratorFactory( 42l ) );
        boolean showStatus = true;
        int threadPoolSize = Client.defaultThreadCount();
        TimeUnit durationUnit = TimeUnit.MILLI;
        WorkloadMetricsManager metricsManager = new WorkloadMetricsManager( durationUnit );

        WorkloadRunner workloadRunner = new WorkloadRunner( db, phase, workload, generatorBuilder, showStatus,
                threadPoolSize, metricsManager );

        workloadRunner.run();

        MetricsFormatter formatter = new SimpleMetricsFormatter();
        MetricsExporter exporter = new OutputStreamMetricsExporter( System.out );
        exporter.export( formatter, metricsManager.getAllMeasurements() );
    }
}
