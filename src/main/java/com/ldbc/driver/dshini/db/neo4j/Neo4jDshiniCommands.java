package com.ldbc.driver.dshini.db.neo4j;

import com.ldbc.driver.DbConnectionState;
import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.dshini.operations.batch.BatchOperationFactory.BatchOperation;
import com.ldbc.driver.dshini.operations.core.CreateNodeNeoProductOperationFactory.CreateNodeNeoProductOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipAuthoredByOperationFactory.CreateRelationshipAuthoredByOperation;
import com.ldbc.driver.dshini.operations.core.DeleteNodeOperationFactory.DeleteNodeOperation;
import com.ldbc.driver.dshini.operations.core.DeleteRelationshipOperationFactory.DeleteRelationshipOperation;
import com.ldbc.driver.dshini.operations.core.GetNodeOperationFactory.GetNodeOperation;
import com.ldbc.driver.dshini.operations.core.GetNodesIncomingClaimsRelationshipsOperationFactory.GetNodesIncomingClaimsRelationshipsOperation;
import com.ldbc.driver.dshini.operations.core.GetNodesOutRelationshipsOperationFactory.GetNodeOutRelationshipsOperation;
import com.ldbc.driver.dshini.operations.core.GetNodesOutgoingIsSpotlightRelationshipsOperationFactory.GetNodesOutgoingIsSpotlightRelationshipsOperation;
import com.ldbc.driver.dshini.operations.core.GetNodesRelationshipsOperationFactory.GetNodeRelationshipsOperation;
import com.ldbc.driver.dshini.operations.core.GetRelationshipOperationFactory.GetRelationshipOperation;
import com.ldbc.driver.dshini.operations.core.UpdateNodeNeoPinOperationFactory.UpdateNodeNeoPinOperation;
import com.ldbc.driver.dshini.operations.index.AddNodeToNeoPinBoardIndexOperationFactory.AddNodeToNeoPinBoardIndexOperation;
import com.ldbc.driver.dshini.operations.index.DeleteNodeFromNeoPinBoardIndexOperationFactory.DeleteNodeFromNeoPinBoardIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinEntertainmentVideoIndexOperationFactory.IndexQueryNodeOnNeoPinEntertainmentVideoIndexOperation;

public interface Neo4jDshiniCommands
{
    public void init();

    public void cleanUp();

    public void clearDb();

    public DbConnectionState getDbConnectionState();

    // TODO add ALL new cypher operations

    public Class<? extends OperationHandler<BatchOperation>> getBatchOperationHandler();

    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinEntertainmentVideoIndexOperation>> getIndexQueryGetNodeOperationHandler();

    public Class<? extends OperationHandler<AddNodeToNeoPinBoardIndexOperation>> getAddNodeToIndexOperationHandler();

    public Class<? extends OperationHandler<DeleteNodeFromNeoPinBoardIndexOperation>> getDeleteNodeFromIndexOperationHandler();

    public Class<? extends OperationHandler<GetNodeOperation>> getGetNodeOperationHandler();

    public Class<? extends OperationHandler<CreateNodeNeoProductOperation>> getCreateNodeOperationHandler();

    public Class<? extends OperationHandler<UpdateNodeNeoPinOperation>> getUpdateNodePropertiesOperationHandler();

    public Class<? extends OperationHandler<DeleteNodeOperation>> getDeleteNodeOperationHandler();

    public Class<? extends OperationHandler<DeleteRelationshipOperation>> getDeleteRelationshipOperationHandler();

    public Class<? extends OperationHandler<GetRelationshipOperation>> getGetRelationshipOperationHandler();

    public Class<? extends OperationHandler<GetNodeOutRelationshipsOperation>> getGetNodeOutRelationshipsOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipAuthoredByOperation>> getCreateRelationshipOperationHandler();

    public Class<? extends OperationHandler<GetNodesIncomingClaimsRelationshipsOperation>> getGetNodeTypedOutRelationshipsOperationHandler();

    public Class<? extends OperationHandler<GetNodeRelationshipsOperation>> getGetNodeRelationshipsOperationHandler();

    public Class<? extends OperationHandler<GetNodesIncomingClaimsRelationshipsOperation>> getGetNodeTypedInRelationshipsOperationHandler();
}
