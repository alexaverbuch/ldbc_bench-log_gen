package com.ldbc.driver.dshini;

import java.util.HashMap;
import java.util.Map;

import com.ldbc.driver.BenchmarkPhase;
import com.ldbc.driver.Client;
import com.ldbc.driver.Db;
import com.ldbc.driver.DbException;
import com.ldbc.driver.Operation;
import com.ldbc.driver.Workload;
import com.ldbc.driver.WorkloadException;
import com.ldbc.driver.db.basic.BasicDb;
import com.ldbc.driver.dshini.db.neo4j.Neo4jDb;
import com.ldbc.driver.dshini.workloads.DshiniWorkload;
import com.ldbc.driver.generator.Generator;
import com.ldbc.driver.generator.GeneratorBuilder;
import com.ldbc.driver.measurements.MetricsExporterException;
import com.ldbc.driver.measurements.WorkloadMetricsManager;
import com.ldbc.driver.measurements.exporters.OutputStreamMetricsExporter;
import com.ldbc.driver.measurements.formatters.DiscreteMetricSimpleFormatter;
import com.ldbc.driver.measurements.formatters.HdrHistogramMetricSimpleFormatter;
import com.ldbc.driver.runner.WorkloadRunner;
import com.ldbc.driver.util.RandomDataGeneratorFactory;
import com.ldbc.driver.util.temporal.TimeUnit;
import com.ldbc.driver.workloads.simple.SimpleWorkload;

public class Example
{
    public static void main( String[] args ) throws DbException, WorkloadException, MetricsExporterException
    {
        // doSimpleWorkload();
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

        GeneratorBuilder generatorBuilder = new GeneratorBuilder( new RandomDataGeneratorFactory( 42l ) );
        Generator<Operation<?>> operationGenerator = workload.getLoadOperations( generatorBuilder );
        boolean showStatus = true;
        int threadPoolSize = Client.defaultThreadCount();
        TimeUnit durationUnit = TimeUnit.MILLI;
        WorkloadMetricsManager metricsManager = new WorkloadMetricsManager( durationUnit );

        WorkloadRunner workloadRunner = new WorkloadRunner( db, operationGenerator, showStatus, threadPoolSize,
                metricsManager );

        workloadRunner.run();

        metricsManager.export( new OutputStreamMetricsExporter( System.out ), new HdrHistogramMetricSimpleFormatter(),
                new DiscreteMetricSimpleFormatter() );
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
        long operationCount = 100;
        // First operation (ignore unless using multiple loadgen machines)
        long operationStart = 0;
        // Number of records (sometimes useful for knowing max key) - not used
        long recordCount = 0;

        Map<String, String> workloadProperties = new HashMap<String, String>();
        // TODO remove log files if you wish to run fewer logs/operations
        StringBuilder logs = new StringBuilder();
        logs.append( "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log" ).append( "," );
        logs.append( "logs/dshini-request-logs-2013-04-29/request-ip-10-196-162-95.log" ).append( "," );
        logs.append( "logs/dshini-request-logs-2013-04-29/request-ip-10-196-162-95-repaired entries.log" ).append( "," );
        logs.append( "logs/dshini-request-logs-2013-04-29/request-ip-10-76-97-169.log" ).append( "," );
        logs.append( "logs/dshini-request-logs-2013-04-29/request-ip-10-84-146-61.log" ).append( "," );
        logs.append( "logs/dshini-request-logs-2013-04-29/request-ip-10-84-146-61-repaired entries.log" ).append( "," );
        logs.append( "logs/dshini-request-logs-2013-04-29/request-ip-10-90-59-251.log" ).append( "," );
        logs.append( "logs/dshini-request-logs-2013-04-29/request-ip-10-98-203-214.log" );
        workloadProperties.put( "logfiles", logs.toString() );

        Workload workload = new DshiniWorkload();
        workload.init( operationCount, operationStart, recordCount, workloadProperties );

        BenchmarkPhase phase = BenchmarkPhase.TRANSACTION_PHASE;
        GeneratorBuilder generatorBuilder = new GeneratorBuilder( new RandomDataGeneratorFactory( 42l ) );
        Generator<Operation<?>> operationGenerator = workload.getTransactionalOperations( generatorBuilder );
        boolean showStatus = true;
        int threadPoolSize = Client.defaultThreadCount();
        TimeUnit durationUnit = TimeUnit.MILLI;
        WorkloadMetricsManager metricsManager = new WorkloadMetricsManager( durationUnit );

        WorkloadRunner workloadRunner = new WorkloadRunner( db, operationGenerator, showStatus, threadPoolSize,
                metricsManager );

        workloadRunner.run();

        metricsManager.export( new OutputStreamMetricsExporter( System.out ), new HdrHistogramMetricSimpleFormatter(),
                new DiscreteMetricSimpleFormatter() );
    }
}
