package com.ldbc.driver.dshini.workloads;

import com.ldbc.driver.Db;
import com.ldbc.driver.DbException;
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

public abstract class DshiniCoreCommands implements OperationHandlersRegistrar
{
    @Override
    public void registerHandlersWithDb( Db db ) throws DbException
    {
        db.registerOperationHandler( CreateRelationshipSoldOperation.class, getCreateRelationshipSoldOperationHandler() );
        db.registerOperationHandler( CreateRelationshipSitesOwnedByOperation.class,
                getCreateRelationshipSitesOwnedByOperationHandler() );
        db.registerOperationHandler( CreateRelationshipShipsToOperation.class,
                getCreateRelationshipShipsToOperationHandler() );
        db.registerOperationHandler( CreateRelationshipRootOperation.class, getCreateRelationshipRootOperationHandler() );
        db.registerOperationHandler( CreateRelationshipRepinsOperation.class,
                getCreateRelationshipRepinsOperationHandler() );
        db.registerOperationHandler( CreateRelationshipPinsOperation.class, getCreateRelationshipPinsOperationHandler() );
        db.registerOperationHandler( CreateRelationshipPinsAssetOperation.class,
                getCreateRelationshipPinsAssetOperationHandler() );
        db.registerOperationHandler( CreateRelationshipPinReferencesUrlOperation.class,
                getCreateRelationshipPinReferencesUrlOperationHandler() );
        db.registerOperationHandler( CreateRelationshipPinnedViaOperation.class,
                getCreateRelationshipPinnedViaOperationHandler() );
        db.registerOperationHandler( CreateRelationshipOffersOperation.class,
                getCreateRelationshipOffersOperationHandler() );
        db.registerOperationHandler( CreateRelationshipOfferedByOperation.class,
                getCreateRelationshipOfferedByOperationHandler() );
        db.registerOperationHandler( CreateRelationshipInCategoryOperation.class,
                getCreateRelationshipInCategoryOperationHandler() );
        db.registerOperationHandler( CreateRelationshipCommentsOnOperation.class,
                getCreateRelationshipCommentsOnOperationHandler() );
        db.registerOperationHandler( CreateRelationshipCommentedByOperation.class,
                getCreateRelationshipCommentedByOperationHandler() );
        db.registerOperationHandler( CreateRelationshipClaimsOperation.class,
                getCreateRelationshipClaimsOperationHandler() );
        db.registerOperationHandler( CreateRelationshipClaimedOperation.class,
                getCreateRelationshipClaimedOperationHandler() );
        db.registerOperationHandler( CreateRelationshipBoughtOperation.class,
                getCreateRelationshipBoughtOperationHandler() );
        db.registerOperationHandler( CreateRelationshipBoardShownOnOperation.class,
                getCreateRelationshipBoardShownOnOperationHandler() );
        db.registerOperationHandler( CreateRelationshipAuthoredByOperation.class,
                getCreateRelationshipAuthoredByOperationHandler() );
        db.registerOperationHandler( CreateRelationshipSubBoardOfOperation.class,
                getCreateRelationshipSubBoardOfOperationHandler() );
        db.registerOperationHandler( CreateRelationshipSubscribesOperation.class,
                getCreateRelationshipSubscribesOperationHandler() );
        db.registerOperationHandler( CreateRelationshipTrollsOperation.class,
                getCreateRelationshipTrollsOperationHandler() );
        db.registerOperationHandler( CreateRelationshipUrlHostOfOperation.class,
                getCreateRelationshipUrlHostOfOperationHandler() );
        db.registerOperationHandler( CreateNodeNeoPinBoardOperation.class, getCreateNodeNeoPinBoardOperationHandler() );
        db.registerOperationHandler( CreateNodeNeoPinUrlHostOperation.class,
                getCreateNodeNeoPinUrlHostOperationHandler() );
        db.registerOperationHandler( CreateNodeNeoPinUrlOperation.class, getCreateNodeNeoPinUrlOperationHandler() );
        db.registerOperationHandler( CreateNodeNeoProductOperation.class, getCreateNodeNeoProductOperationHandler() );
        db.registerOperationHandler( CreateNodeNeoSiteOperation.class, getCreateNodeNeoSiteOperationHandler() );
        db.registerOperationHandler( GetNodeOperation.class, getGetNodeOperationHandler() );
        db.registerOperationHandler( GetRelationshipOperation.class, getGetRelationshipOperationHandler() );
        db.registerOperationHandler( GetNodesRelationshipsOperation.class, getGetNodesRelationshipsOperationHandler() );
        db.registerOperationHandler( GetNodesOutRelationshipsOperation.class,
                getGetNodesOutRelationshipsOperationHandler() );
        db.registerOperationHandler( GetNodesOutgoingWantedRelationshipsOperation.class,
                getGetNodesOutgoingWantedRelationshipsOperationHandler() );
        db.registerOperationHandler( GetNodesOutgoingTrollsRelationshipsOperation.class,
                getGetNodesOutgoingTrollsRelationshipsOperationHandler() );
        db.registerOperationHandler( GetNodesOutgoingClaimedAndClaimsRelationshipsOperation.class,
                getGetNodesOutgoingClaimedAndClaimsRelationshipsOperationHandler() );
        db.registerOperationHandler( GetNodesIncomingClaimsRelationshipsOperation.class,
                getGetNodesIncomingClaimsRelationshipsOperationHandler() );
        db.registerOperationHandler( GetNodesOutgoingIsSpotlightRelationshipsOperation.class,
                getGetNodesOutgoingIsSpotlightRelationshipsOperationHandler() );
        db.registerOperationHandler( DeleteNodeOperation.class, getDeleteNodeOperationHandler() );
        db.registerOperationHandler( DeleteRelationshipOperation.class, getDeleteRelationshipOperationHandler() );
        db.registerOperationHandler( UpdateNodeNeoPinBoardOperation.class, getUpdateNodeNeoPinBoardOperationHandler() );
        db.registerOperationHandler( UpdateNodeNeoPinOperation.class, getUpdateNodeNeoPinOperationHandler() );
        db.registerOperationHandler( UpdateNodeNeoSiteOperation.class, getUpdateNodeNeoSiteOperationHandler() );
    }

    /*
     * Create Relationship
     */

    public abstract Class<? extends OperationHandler<CreateRelationshipSoldOperation>> getCreateRelationshipSoldOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipSitesOwnedByOperation>> getCreateRelationshipSitesOwnedByOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipShipsToOperation>> getCreateRelationshipShipsToOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipRootOperation>> getCreateRelationshipRootOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipRepinsOperation>> getCreateRelationshipRepinsOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipPinsOperation>> getCreateRelationshipPinsOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipPinsAssetOperation>> getCreateRelationshipPinsAssetOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipPinReferencesUrlOperation>> getCreateRelationshipPinReferencesUrlOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipPinnedViaOperation>> getCreateRelationshipPinnedViaOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipOffersOperation>> getCreateRelationshipOffersOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipOfferedByOperation>> getCreateRelationshipOfferedByOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipInCategoryOperation>> getCreateRelationshipInCategoryOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipCommentsOnOperation>> getCreateRelationshipCommentsOnOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipCommentedByOperation>> getCreateRelationshipCommentedByOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipClaimsOperation>> getCreateRelationshipClaimsOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipClaimedOperation>> getCreateRelationshipClaimedOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipBoughtOperation>> getCreateRelationshipBoughtOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipBoardShownOnOperation>> getCreateRelationshipBoardShownOnOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipAuthoredByOperation>> getCreateRelationshipAuthoredByOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipSubBoardOfOperation>> getCreateRelationshipSubBoardOfOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipSubscribesOperation>> getCreateRelationshipSubscribesOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipTrollsOperation>> getCreateRelationshipTrollsOperationHandler();

    public abstract Class<? extends OperationHandler<CreateRelationshipUrlHostOfOperation>> getCreateRelationshipUrlHostOfOperationHandler();

    /*
     * Create Node
     */

    public abstract Class<? extends OperationHandler<CreateNodeNeoPinBoardOperation>> getCreateNodeNeoPinBoardOperationHandler();

    public abstract Class<? extends OperationHandler<CreateNodeNeoPinUrlHostOperation>> getCreateNodeNeoPinUrlHostOperationHandler();

    public abstract Class<? extends OperationHandler<CreateNodeNeoPinUrlOperation>> getCreateNodeNeoPinUrlOperationHandler();

    public abstract Class<? extends OperationHandler<CreateNodeNeoProductOperation>> getCreateNodeNeoProductOperationHandler();

    public abstract Class<? extends OperationHandler<CreateNodeNeoSiteOperation>> getCreateNodeNeoSiteOperationHandler();

    /*
     * Get Node
     */

    public abstract Class<? extends OperationHandler<GetNodeOperation>> getGetNodeOperationHandler();

    /*
     * Get Relationships
     */

    public abstract Class<? extends OperationHandler<GetRelationshipOperation>> getGetRelationshipOperationHandler();

    /*
     * Get Node Relationships
     */

    public abstract Class<? extends OperationHandler<GetNodesRelationshipsOperation>> getGetNodesRelationshipsOperationHandler();

    public abstract Class<? extends OperationHandler<GetNodesOutRelationshipsOperation>> getGetNodesOutRelationshipsOperationHandler();

    public abstract Class<? extends OperationHandler<GetNodesOutgoingWantedRelationshipsOperation>> getGetNodesOutgoingWantedRelationshipsOperationHandler();

    public abstract Class<? extends OperationHandler<GetNodesOutgoingTrollsRelationshipsOperation>> getGetNodesOutgoingTrollsRelationshipsOperationHandler();

    public abstract Class<? extends OperationHandler<GetNodesOutgoingClaimedAndClaimsRelationshipsOperation>> getGetNodesOutgoingClaimedAndClaimsRelationshipsOperationHandler();

    public abstract Class<? extends OperationHandler<GetNodesIncomingClaimsRelationshipsOperation>> getGetNodesIncomingClaimsRelationshipsOperationHandler();

    public abstract Class<? extends OperationHandler<GetNodesOutgoingIsSpotlightRelationshipsOperation>> getGetNodesOutgoingIsSpotlightRelationshipsOperationHandler();

    /*
     * Delete Nodes
     */

    public abstract Class<? extends OperationHandler<DeleteNodeOperation>> getDeleteNodeOperationHandler();

    /*
     * Delete Relationships
     */

    public abstract Class<? extends OperationHandler<DeleteRelationshipOperation>> getDeleteRelationshipOperationHandler();

    /*
     * Update Nodes
     */

    public abstract Class<? extends OperationHandler<UpdateNodeNeoPinBoardOperation>> getUpdateNodeNeoPinBoardOperationHandler();

    public abstract Class<? extends OperationHandler<UpdateNodeNeoPinOperation>> getUpdateNodeNeoPinOperationHandler();

    public abstract Class<? extends OperationHandler<UpdateNodeNeoSiteOperation>> getUpdateNodeNeoSiteOperationHandler();
}
