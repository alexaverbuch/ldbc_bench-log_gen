package com.ldbc.driver.dshini.workloads;

import java.util.ArrayList;
import java.util.List;

import com.ldbc.driver.Operation;
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
import com.ldbc.driver.util.Pair;

public abstract class DshiniCoreCommands implements AllOperationHandlersProvider
{
    @Override
    public final List<Pair<Class<? extends Operation<?>>, Class<? extends OperationHandler<?>>>> allOperationHandlers()
    {
        List<Pair<Class<? extends Operation<?>>, Class<? extends OperationHandler<?>>>> handlers = new ArrayList<Pair<Class<? extends Operation<?>>, Class<? extends OperationHandler<?>>>>();
        handlers.add( new Pair( CreateRelationshipSoldOperation.class, getCreateRelationshipSoldOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipSitesOwnedByOperation.class,
                getCreateRelationshipSitesOwnedByOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipShipsToOperation.class,
                getCreateRelationshipShipsToOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipRootOperation.class, getCreateRelationshipRootOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipRepinsOperation.class, getCreateRelationshipRepinsOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipPinsOperation.class, getCreateRelationshipPinsOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipPinsAssetOperation.class,
                getCreateRelationshipPinsAssetOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipPinReferencesUrlOperation.class,
                getCreateRelationshipPinReferencesUrlOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipPinnedViaOperation.class,
                getCreateRelationshipPinnedViaOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipOffersOperation.class, getCreateRelationshipOffersOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipOfferedByOperation.class,
                getCreateRelationshipOfferedByOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipInCategoryOperation.class,
                getCreateRelationshipInCategoryOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipCommentsOnOperation.class,
                getCreateRelationshipCommentsOnOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipCommentedByOperation.class,
                getCreateRelationshipCommentedByOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipClaimsOperation.class, getCreateRelationshipClaimsOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipClaimedOperation.class,
                getCreateRelationshipClaimedOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipBoughtOperation.class, getCreateRelationshipBoughtOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipBoardShownOnOperation.class,
                getCreateRelationshipBoardShownOnOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipAuthoredByOperation.class,
                getCreateRelationshipAuthoredByOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipSubBoardOfOperation.class,
                getCreateRelationshipSubBoardOfOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipSubscribesOperation.class,
                getCreateRelationshipSubscribesOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipTrollsOperation.class, getCreateRelationshipTrollsOperationHandler() ) );
        handlers.add( new Pair( CreateRelationshipUrlHostOfOperation.class,
                getCreateRelationshipUrlHostOfOperationHandler() ) );
        handlers.add( new Pair( CreateNodeNeoPinBoardOperation.class, getCreateNodeNeoPinBoardOperationHandler() ) );
        handlers.add( new Pair( CreateNodeNeoPinUrlHostOperation.class, getCreateNodeNeoPinUrlHostOperationHandler() ) );
        handlers.add( new Pair( CreateNodeNeoPinUrlOperation.class, getCreateNodeNeoPinUrlOperationHandler() ) );
        handlers.add( new Pair( CreateNodeNeoProductOperation.class, getCreateNodeNeoProductOperationHandler() ) );
        handlers.add( new Pair( CreateNodeNeoSiteOperation.class, getCreateNodeNeoSiteOperationHandler() ) );
        handlers.add( new Pair( GetNodeOperation.class, getGetNodeOperationHandler() ) );
        handlers.add( new Pair( GetRelationshipOperation.class, getGetRelationshipOperationHandler() ) );
        handlers.add( new Pair( GetNodesRelationshipsOperation.class, getGetNodesRelationshipsOperationHandler() ) );
        handlers.add( new Pair( GetNodesOutRelationshipsOperation.class, getGetNodesOutRelationshipsOperationHandler() ) );
        handlers.add( new Pair( GetNodesOutgoingWantedRelationshipsOperation.class,
                getGetNodesOutgoingWantedRelationshipsOperationHandler() ) );
        handlers.add( new Pair( GetNodesOutgoingTrollsRelationshipsOperation.class,
                getGetNodesOutgoingTrollsRelationshipsOperationHandler() ) );
        handlers.add( new Pair( GetNodesOutgoingClaimedAndClaimsRelationshipsOperation.class,
                getGetNodesOutgoingClaimedAndClaimsRelationshipsOperationHandler() ) );
        handlers.add( new Pair( GetNodesIncomingClaimsRelationshipsOperation.class,
                getGetNodesIncomingClaimsRelationshipsOperationHandler() ) );
        handlers.add( new Pair( GetNodesOutgoingIsSpotlightRelationshipsOperation.class,
                getGetNodesOutgoingIsSpotlightRelationshipsOperationHandler() ) );
        handlers.add( new Pair( DeleteNodeOperation.class, getDeleteNodeOperationHandler() ) );
        handlers.add( new Pair( DeleteRelationshipOperation.class, getDeleteRelationshipOperationHandler() ) );
        handlers.add( new Pair( UpdateNodeNeoPinBoardOperation.class, getUpdateNodeNeoPinBoardOperationHandler() ) );
        handlers.add( new Pair( UpdateNodeNeoPinOperation.class, getUpdateNodeNeoPinOperationHandler() ) );
        handlers.add( new Pair( UpdateNodeNeoSiteOperation.class, getUpdateNodeNeoSiteOperationHandler() ) );
        return handlers;
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
