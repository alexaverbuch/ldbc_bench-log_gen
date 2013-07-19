package com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.cypher;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.db.neo4j.emdedded.Neo4jConnectionStateEmbedded;
import com.ldbc.driver.dshini.operations.cypher.GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites2OperationFactory.GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites2Operation;

public class GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites2OperationHandler extends
        OperationHandler<GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites2Operation>
{
    @Override
    protected OperationResult executeOperation( GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites2Operation operation )
    {
        Neo4jConnectionStateEmbedded connection = (Neo4jConnectionStateEmbedded) getDbConnectionState();
        connection.getExecutionEngine().execute( operation.getQueryString(), operation.getParams() );
        // TODO find way to do this
        int resultCode = 0;
        // TODO return what query actually returns
        int result = 0;
        return operation.buildResult( resultCode, result );
    }
}
