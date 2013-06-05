package com.ldbc.driver.dshini.db.neo4j;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.dshini.operations.AddNodeToIndexOperationFactory.AddNodeToIndexOperation;
import com.ldbc.driver.dshini.operations.BatchOperationFactory.BatchOperation;
import com.ldbc.driver.dshini.operations.CreateNodeOperationFactory.CreateNodeOperation;
import com.ldbc.driver.dshini.operations.CreateRelationshipOperationFactory.CreateRelationshipOperation;
import com.ldbc.driver.dshini.operations.CypherOperationFactory.CypherOperation;
import com.ldbc.driver.dshini.operations.DeleteNodeFromIndexOperationFactory.DeleteNodeFromIndexOperation;
import com.ldbc.driver.dshini.operations.DeleteNodeOperationFactory.DeleteNodeOperation;
import com.ldbc.driver.dshini.operations.DeleteRelationshipOperationFactory.DeleteRelationshipOperation;
import com.ldbc.driver.dshini.operations.GetNodeOperationFactory.GetNodeOperation;
import com.ldbc.driver.dshini.operations.GetNodesOutRelationshipsOperationFactory.GetNodeOutRelationshipsOperation;
import com.ldbc.driver.dshini.operations.GetNodesRelationshipsOperationFactory.GetNodeRelationshipsOperation;
import com.ldbc.driver.dshini.operations.GetNodesTypedInRelationshipsOperationFactory.GetNodeTypedInRelationshipsOperation;
import com.ldbc.driver.dshini.operations.GetNodesTypedOutRelationshipsOperationFactory.GetNodeTypedOutRelationshipsOperation;
import com.ldbc.driver.dshini.operations.GetRelationshipOperationFactory.GetRelationshipOperation;
import com.ldbc.driver.dshini.operations.IndexQueryGetNodeOperationFactory.IndexQueryGetNodeOperation;
import com.ldbc.driver.dshini.operations.UpdateNodePropertiesOperationFactory.UpdateNodePropertiesOperation;

public interface Neo4jDshiniCommands
{
    public void init();

    public void cleanUp();

    public void clearDb();

    public Class<? extends OperationHandler<CypherOperation>> getCypherOperationHandler();

    public Class<? extends OperationHandler<BatchOperation>> getBatchOperationHandler();

    public Class<? extends OperationHandler<IndexQueryGetNodeOperation>> getIndexQueryGetNodeOperationHandler();

    public Class<? extends OperationHandler<AddNodeToIndexOperation>> getAddNodeToIndexOperationHandler();

    public Class<? extends OperationHandler<DeleteNodeFromIndexOperation>> getDeleteNodeFromIndexOperationHandler();

    public Class<? extends OperationHandler<GetNodeOperation>> getGetNodeOperationHandler();

    public Class<? extends OperationHandler<CreateNodeOperation>> getCreateNodeOperationHandler();

    public Class<? extends OperationHandler<UpdateNodePropertiesOperation>> getUpdateNodePropertiesOperationHandler();

    public Class<? extends OperationHandler<DeleteNodeOperation>> getDeleteNodeOperationHandler();

    public Class<? extends OperationHandler<DeleteRelationshipOperation>> getDeleteRelationshipOperationHandler();

    public Class<? extends OperationHandler<GetRelationshipOperation>> getGetRelationshipOperationHandler();

    public Class<? extends OperationHandler<GetNodeOutRelationshipsOperation>> getGetNodeOutRelationshipsOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipOperation>> getCreateRelationshipOperationHandler();

    public Class<? extends OperationHandler<GetNodeTypedOutRelationshipsOperation>> getGetNodeTypedOutRelationshipsOperationHandler();

    public Class<? extends OperationHandler<GetNodeRelationshipsOperation>> getGetNodeRelationshipsOperationHandler();

    public Class<? extends OperationHandler<GetNodeTypedInRelationshipsOperation>> getGetNodeTypedInRelationshipsOperationHandler();
}
