ldbc_bench-log_gen
==================

Generate benchmark workload from request logs

### Build

    git clone git@github.com:alexaverbuch/ldbc_bench-log_gen.git
    cd ldbc_bench-log_gen
    ./build.sh


### Run

Short:

	java -cp core/target/core-0.1-SNAPSHOT.jar:dshini/target/dshini-0.1-SNAPSHOT.jar com.ldbc.driver.Client -db com.ldbc.driver.dshini.db.neo4j.Neo4jDb -P dshini/workload/dshiniworkload -p neo4j.clear=false -p logfiles="dshini/logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log"

Full:

    java -cp core/target/core-0.1-SNAPSHOT.jar:dshini/target/dshini-0.1-SNAPSHOT.jar com.ldbc.driver.Client -db com.ldbc.driver.dshini.db.neo4j.Neo4jDb -P dshini/workload/dshiniworkload -p neo4j.clear=false -p logfiles="dshini/logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log,dshini/logs/dshini-request-logs-2013-04-29/request-ip-10-196-162-95.log,dshini/logs/dshini-request-logs-2013-04-29/request-ip-10-76-97-169.log,dshini/logs/dshini-request-logs-2013-04-29/request-ip-10-84-146-61.log,dshini/logs/dshini-request-logs-2013-04-29/request-ip-10-90-59-251.log,dshini/logs/dshini-request-logs-2013-04-29/request-ip-10-98-203-214.log"
