package com.ldbc.driver.dshini.workloads;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.dshini.operations.core.CreateNodeNeoPinBoardOperationFactory.CreateNodeNeoPinBoardOperation;
import com.ldbc.driver.dshini.operations.core.CreateNodeNeoPinUrlHostOperationFactory.CreateNodeNeoPinUrlHostOperation;
import com.ldbc.driver.dshini.operations.core.CreateNodeNeoPinUrlOperationFactory.CreateNodeNeoPinUrlOperation;
import com.ldbc.driver.dshini.operations.core.CreateNodeNeoProductOperationFactory.CreateNodeNeoProductOperation;
import com.ldbc.driver.dshini.operations.core.CreateNodeNeoSiteOperationFactory.CreateNodeNeoSiteOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipAuthoredByOperationFactory.CreateRelationshipAuthoredByOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipBoardShownOnOperationFactory.CreateRelationshipBoardShownOnOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipBoughtOperationFactory.CreateRelationshipBoughtOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipClaimedOperationFactory.CreateRelationshipClaimedOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipClaimsOperationFactory.CreateRelationshipClaimsOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipCommentedByOperationFactory.CreateRelationshipCommentedByOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipCommentsOnOperationFactory.CreateRelationshipCommentsOnOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipInCategoryOperationFactory.CreateRelationshipInCategoryOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipOfferedByOperationFactory.CreateRelationshipOfferedByOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipOffersOperationFactory.CreateRelationshipOffersOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipPinReferencesUrlOperationFactory.CreateRelationshipPinReferencesUrlOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipPinnedViaOperationFactory.CreateRelationshipPinnedViaOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipPinsAssetOperationFactory.CreateRelationshipPinsAssetOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipPinsOperationFactory.CreateRelationshipPinsOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipRepinsOperationFactory.CreateRelationshipRepinsOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipRootOperationFactory.CreateRelationshipRootOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipShipsToOperationFactory.CreateRelationshipShipsToOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipSitesOwnedByOperationFactory.CreateRelationshipSitesOwnedByOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipSoldOperationFactory.CreateRelationshipSoldOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipSubBoardOfOperationFactory.CreateRelationshipSubBoardOfOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipSubscribesOperationFactory.CreateRelationshipSubscribesOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipTrollsOperationFactory.CreateRelationshipTrollsOperation;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipUrlHostOfOperationFactory.CreateRelationshipUrlHostOfOperation;
import com.ldbc.driver.dshini.operations.core.DeleteNodeOperationFactory.DeleteNodeOperation;
import com.ldbc.driver.dshini.operations.core.DeleteRelationshipOperationFactory.DeleteRelationshipOperation;
import com.ldbc.driver.dshini.operations.core.GetNodeOperationFactory.GetNodeOperation;
import com.ldbc.driver.dshini.operations.core.GetNodesIncomingClaimsRelationshipsOperationFactory.GetNodesIncomingClaimsRelationshipsOperation;
import com.ldbc.driver.dshini.operations.core.GetNodesOutRelationshipsOperationFactory.GetNodesOutRelationshipsOperation;
import com.ldbc.driver.dshini.operations.core.GetNodesOutgoingClaimedAndClaimsRelationshipsOperationFactory.GetNodesOutgoingClaimedAndClaimsRelationshipsOperation;
import com.ldbc.driver.dshini.operations.core.GetNodesOutgoingIsSpotlightRelationshipsOperationFactory.GetNodesOutgoingIsSpotlightRelationshipsOperation;
import com.ldbc.driver.dshini.operations.core.GetNodesOutgoingTrollsRelationshipsOperationFactory.GetNodesOutgoingTrollsRelationshipsOperation;
import com.ldbc.driver.dshini.operations.core.GetNodesOutgoingWantedRelationshipsOperationFactory.GetNodesOutgoingWantedRelationshipsOperation;
import com.ldbc.driver.dshini.operations.core.GetNodesRelationshipsOperationFactory.GetNodesRelationshipsOperation;
import com.ldbc.driver.dshini.operations.core.GetRelationshipOperationFactory.GetRelationshipOperation;
import com.ldbc.driver.dshini.operations.core.UpdateNodeNeoPinBoardOperationFactory.UpdateNodeNeoPinBoardOperation;
import com.ldbc.driver.dshini.operations.core.UpdateNodeNeoPinOperationFactory.UpdateNodeNeoPinOperation;
import com.ldbc.driver.dshini.operations.core.UpdateNodeNeoSiteOperationFactory.UpdateNodeNeoSiteOperation;

public interface DshiniCoreCommands
{
    /*
     * Create Relationship
     */

    public Class<? extends OperationHandler<CreateRelationshipSoldOperation>> getCreateRelationshipSoldOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipSitesOwnedByOperation>> getCreateRelationshipSitesOwnedByOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipShipsToOperation>> getCreateRelationshipShipsToOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipRootOperation>> getCreateRelationshipRootOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipRepinsOperation>> getCreateRelationshipRepinsOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipPinsOperation>> getCreateRelationshipPinsOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipPinsAssetOperation>> getCreateRelationshipPinsAssetOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipPinReferencesUrlOperation>> getCreateRelationshipPinReferencesUrlOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipPinnedViaOperation>> getCreateRelationshipPinnedViaOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipOffersOperation>> getCreateRelationshipOffersOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipOfferedByOperation>> getCreateRelationshipOfferedByOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipInCategoryOperation>> getCreateRelationshipInCategoryOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipCommentsOnOperation>> getCreateRelationshipCommentsOnOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipCommentedByOperation>> getCreateRelationshipCommentedByOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipClaimsOperation>> getCreateRelationshipClaimsOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipClaimedOperation>> getCreateRelationshipClaimedOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipBoughtOperation>> getCreateRelationshipBoughtOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipBoardShownOnOperation>> getCreateRelationshipBoardShownOnOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipAuthoredByOperation>> getCreateRelationshipAuthoredByOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipSubBoardOfOperation>> getCreateRelationshipSubBoardOfOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipSubscribesOperation>> getCreateRelationshipSubscribesOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipTrollsOperation>> getCreateRelationshipTrollsOperationHandler();

    public Class<? extends OperationHandler<CreateRelationshipUrlHostOfOperation>> getCreateRelationshipUrlHostOfOperationHandler();

    /*
     * Create Node
     */

    public Class<? extends OperationHandler<CreateNodeNeoPinBoardOperation>> getCreateNodeNeoPinBoardOperationHandler();

    public Class<? extends OperationHandler<CreateNodeNeoPinUrlHostOperation>> getCreateNodeNeoPinUrlHostOperationHandler();

    public Class<? extends OperationHandler<CreateNodeNeoPinUrlOperation>> getCreateNodeNeoPinUrlOperationHandler();

    public Class<? extends OperationHandler<CreateNodeNeoProductOperation>> getCreateNodeNeoProductOperationHandler();

    public Class<? extends OperationHandler<CreateNodeNeoSiteOperation>> getCreateNodeNeoSiteOperationHandler();

    /*
     * Get Node
     */

    public Class<? extends OperationHandler<GetNodeOperation>> getGetNodeOperationHandler();

    /*
     * Get Relationships
     */

    public Class<? extends OperationHandler<GetRelationshipOperation>> getGetRelationshipOperationHandler();

    /*
     * Get Node Relationships
     */

    public Class<? extends OperationHandler<GetNodesRelationshipsOperation>> getGetNodesRelationshipsOperationHandler();

    public Class<? extends OperationHandler<GetNodesOutRelationshipsOperation>> getGetNodesOutRelationshipsOperationHandler();

    public Class<? extends OperationHandler<GetNodesOutgoingWantedRelationshipsOperation>> getGetNodesOutgoingWantedRelationshipsOperationHandler();

    public Class<? extends OperationHandler<GetNodesOutgoingTrollsRelationshipsOperation>> getGetNodesOutgoingTrollsRelationshipsOperationHandler();

    public Class<? extends OperationHandler<GetNodesOutgoingClaimedAndClaimsRelationshipsOperation>> getGetNodesOutgoingClaimedAndClaimsRelationshipsOperationHandler();

    public Class<? extends OperationHandler<GetNodesIncomingClaimsRelationshipsOperation>> getGetNodesIncomingClaimsRelationshipsOperationHandler();

    public Class<? extends OperationHandler<GetNodesOutgoingIsSpotlightRelationshipsOperation>> getGetNodesOutgoingIsSpotlightRelationshipsOperationHandler();

    /*
     * Delete Nodes
     */

    public Class<? extends OperationHandler<DeleteNodeOperation>> getDeleteNodeOperationHandler();

    /*
     * Delete Relationships
     */

    public Class<? extends OperationHandler<DeleteRelationshipOperation>> getDeleteRelationshipOperationHandler();

    /*
     * Update Nodes
     */

    public Class<? extends OperationHandler<UpdateNodeNeoPinBoardOperation>> getUpdateNodeNeoPinBoardOperationHandler();

    public Class<? extends OperationHandler<UpdateNodeNeoPinOperation>> getUpdateNodeNeoPinOperationHandler();

    public Class<? extends OperationHandler<UpdateNodeNeoSiteOperation>> getUpdateNodeNeoSiteOperationHandler();
}
