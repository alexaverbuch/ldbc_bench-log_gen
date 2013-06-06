package com.ldbc.driver.dshini.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogEntry;
import com.ldbc.driver.dshini.generator.RequestLogEntryException;

/*
POST;
http://graph-master.dshini.net:7474/db/data/batch;
"[
    {
        ""method"":""POST"",
        ""to"":""\/node"",
        ""body"": {
                      ""ObjectType"":""NeoPin"",
                      ""Message"":"""",
                      ""CommentsClosed"":false,
                      ""CreatedAt"":1367242102,
                      ""PinIdentifier"":""279a7016759215a60cfa8d2417b6580696a7a474"",
                      ""LikeCount"":0,""RepinCount"":0
                  },
         ""id"":0
     },
     {
         ""method"":""POST"",
         ""to"":""\/index\/node\/neo_pin"",
         ""body"": {
                       ""key"":""ObjectType"",
                       ""value"":""NeoPin"",
                       ""uri"":""{0}""
                   },
         ""id"":1
     }
]";
"[""Accept: application\/json"",""X-Stream:true"",""Content-Length: 1371"",""Content-Type: application\/json""]"
*/

public class BatchOperationFactory implements MatchableOperationCreator
{
    private static final Logger logger = Logger.getLogger( BatchOperationFactory.class );

    private final Pattern BATCH_PATTERN = Pattern.compile( ".*db/data/batch$" );
    private final ObjectMapper mapper = new ObjectMapper();
    private final OperationMatcher operationMatcher;

    public BatchOperationFactory( OperationMatcher operationMatcher )
    {
        this.operationMatcher = operationMatcher;
    }

    @Override
    public boolean matches( RequestLogEntry entry ) throws MatchableException
    {
        return entry.getHttpMethod().equals( "POST" ) && BATCH_PATTERN.matcher( entry.getUrl() ).matches();
    }

    /*
    time=1367242102932, 
    httpMethod=POST, 
    url=db/data/node, 
    descriptionString=null, 
    descriptionMap={ObjectType=NeoPin, Message=, CommentsClosed=false, CreatedAt=1367242102, PinIdentifier=279a7016759215a60cfa8d2417b6580696a7a474, LikeCount=0, RepinCount=0}, 
    descriptionMapList=null, 
    httpHeaders=
    
    time=1367242102932, 
    httpMethod=POST, 
    url=db/data/index/node/neo_pin, 
    descriptionString=null, 
    descriptionMap={key=ObjectType, value=NeoPin, uri={0}}, 
    descriptionMapList=null, 
    httpHeaders=
     */

    @Override
    public Operation<?> createFromEntry( RequestLogEntry entry ) throws RequestLogEntryException
    {
        List<RequestLogEntry> entries = new ArrayList<RequestLogEntry>();
        for ( Map<String, Object> map : entry.getDescriptionAsMapList() )
        {
            String httpMethod = (String) map.get( "method" );
            String url = "db/data" + (String) map.get( "to" );
            Map<String, Object> description = (Map<String, Object>) map.get( "body" );
            String httpHeaders = "";
            RequestLogEntry innerEntry = new RequestLogEntry( mapper, entry.getTime(), httpMethod, url, description,
                    httpHeaders );
            entries.add( innerEntry );
            // try
            // {
            // // TODO put into list to give to BatchOperation
            // // TODO {0} means use result from previous
            // // http://docs.neo4j.org/chunked/stable/rest-api-batch-ops.html
            // // System.out.println( innerEntry );
            // Operation<?> operation =
            // operationMatcher.getSingleMatchingOperation( innerEntry );
            // }
            // catch ( MatchableException e )
            // {
            // String errMsg =
            // "Error matching one of the batch entries to an operation";
            // logger.error( errMsg, e );
            // throw new RequestLogEntryException( errMsg, e.getCause() );
            // }
        }
        return new BatchOperation( entry.getTime(), entries );
    }

    public static class BatchOperation extends Operation<Object>
    {
        private final long time;
        private final List<RequestLogEntry> operationBatch;

        private BatchOperation( long time, List<RequestLogEntry> operationBatch )
        {
            super();
            this.time = time;
            this.operationBatch = operationBatch;
        }

        public long getTime()
        {
            return time;
        }

        public List<RequestLogEntry> getOperationBatch()
        {
            return operationBatch;
        }

        @Override
        public String toString()
        {
            return "BatchOperation [time=" + time + ", operationBatch=" + operationBatch + "]";
        }
    }
}