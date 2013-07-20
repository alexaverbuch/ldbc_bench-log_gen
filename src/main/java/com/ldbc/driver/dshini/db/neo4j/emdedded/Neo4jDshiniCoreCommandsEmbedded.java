package com.ldbc.driver.dshini.db.neo4j.emdedded;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateNodeNeoPinBoardOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateNodeNeoPinUrlHostOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateNodeNeoPinUrlOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateNodeNeoProductOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateNodeNeoSiteOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipAuthoredByOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipBoardShownOnOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipBoughtOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipClaimedOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipClaimsOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipCommentedByOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipCommentsOnOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipInCategoryOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipOfferedByOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipOffersOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipPinReferencesUrlOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipPinnedViaOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipPinsAssetOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipPinsOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipRepinsOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipRootOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipShipsToOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipSitesOwnedByOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipSoldOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipSubBoardOfOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipSubscribesOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipTrollsOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.CreateRelationshipUrlHostOfOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.DeleteNodeOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.DeleteRelationshipOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.GetNodeOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.GetNodesIncomingClaimsRelationshipsOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.GetNodesOutRelationshipsOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.GetNodesOutgoingClaimedAndClaimsRelationshipsOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.GetNodesOutgoingIsSpotlightRelationshipsOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.GetNodesOutgoingTrollsRelationshipsOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.GetNodesOutgoingWantedRelationshipsOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.GetNodesRelationshipsOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.GetRelationshipOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.UpdateNodeNeoPinBoardOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.UpdateNodeNeoPinOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core.UpdateNodeNeoSiteOperationHandler;
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
import com.ldbc.driver.dshini.workloads.DshiniCoreCommands;

public class Neo4jDshiniCoreCommandsEmbedded extends DshiniCoreCommands
{
    @Override
    public Class<? extends OperationHandler<CreateRelationshipSoldOperation>> getCreateRelationshipSoldOperationHandler()
    {
        return CreateRelationshipSoldOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipSitesOwnedByOperation>> getCreateRelationshipSitesOwnedByOperationHandler()
    {
        return CreateRelationshipSitesOwnedByOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipShipsToOperation>> getCreateRelationshipShipsToOperationHandler()
    {
        return CreateRelationshipShipsToOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipRootOperation>> getCreateRelationshipRootOperationHandler()
    {
        return CreateRelationshipRootOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipRepinsOperation>> getCreateRelationshipRepinsOperationHandler()
    {
        return CreateRelationshipRepinsOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipPinsOperation>> getCreateRelationshipPinsOperationHandler()
    {
        return CreateRelationshipPinsOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipPinsAssetOperation>> getCreateRelationshipPinsAssetOperationHandler()
    {
        return CreateRelationshipPinsAssetOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipPinReferencesUrlOperation>> getCreateRelationshipPinReferencesUrlOperationHandler()
    {
        return CreateRelationshipPinReferencesUrlOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipPinnedViaOperation>> getCreateRelationshipPinnedViaOperationHandler()
    {
        return CreateRelationshipPinnedViaOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipOffersOperation>> getCreateRelationshipOffersOperationHandler()
    {
        return CreateRelationshipOffersOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipOfferedByOperation>> getCreateRelationshipOfferedByOperationHandler()
    {
        return CreateRelationshipOfferedByOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipInCategoryOperation>> getCreateRelationshipInCategoryOperationHandler()
    {
        return CreateRelationshipInCategoryOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipCommentsOnOperation>> getCreateRelationshipCommentsOnOperationHandler()
    {
        return CreateRelationshipCommentsOnOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipCommentedByOperation>> getCreateRelationshipCommentedByOperationHandler()
    {
        return CreateRelationshipCommentedByOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipClaimsOperation>> getCreateRelationshipClaimsOperationHandler()
    {
        return CreateRelationshipClaimsOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipClaimedOperation>> getCreateRelationshipClaimedOperationHandler()
    {
        return CreateRelationshipClaimedOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipBoughtOperation>> getCreateRelationshipBoughtOperationHandler()
    {
        return CreateRelationshipBoughtOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipBoardShownOnOperation>> getCreateRelationshipBoardShownOnOperationHandler()
    {
        return CreateRelationshipBoardShownOnOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipAuthoredByOperation>> getCreateRelationshipAuthoredByOperationHandler()
    {
        return CreateRelationshipAuthoredByOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipSubBoardOfOperation>> getCreateRelationshipSubBoardOfOperationHandler()
    {
        return CreateRelationshipSubBoardOfOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipSubscribesOperation>> getCreateRelationshipSubscribesOperationHandler()
    {
        return CreateRelationshipSubscribesOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipTrollsOperation>> getCreateRelationshipTrollsOperationHandler()
    {
        return CreateRelationshipTrollsOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateRelationshipUrlHostOfOperation>> getCreateRelationshipUrlHostOfOperationHandler()
    {
        return CreateRelationshipUrlHostOfOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateNodeNeoPinBoardOperation>> getCreateNodeNeoPinBoardOperationHandler()
    {
        return CreateNodeNeoPinBoardOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateNodeNeoPinUrlHostOperation>> getCreateNodeNeoPinUrlHostOperationHandler()
    {
        return CreateNodeNeoPinUrlHostOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateNodeNeoPinUrlOperation>> getCreateNodeNeoPinUrlOperationHandler()
    {
        return CreateNodeNeoPinUrlOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateNodeNeoProductOperation>> getCreateNodeNeoProductOperationHandler()
    {
        return CreateNodeNeoProductOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<CreateNodeNeoSiteOperation>> getCreateNodeNeoSiteOperationHandler()
    {
        return CreateNodeNeoSiteOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetNodeOperation>> getGetNodeOperationHandler()
    {
        return GetNodeOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetRelationshipOperation>> getGetRelationshipOperationHandler()
    {
        return GetRelationshipOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetNodesRelationshipsOperation>> getGetNodesRelationshipsOperationHandler()
    {
        return GetNodesRelationshipsOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetNodesOutRelationshipsOperation>> getGetNodesOutRelationshipsOperationHandler()
    {
        return GetNodesOutRelationshipsOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetNodesOutgoingWantedRelationshipsOperation>> getGetNodesOutgoingWantedRelationshipsOperationHandler()
    {
        return GetNodesOutgoingWantedRelationshipsOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetNodesOutgoingTrollsRelationshipsOperation>> getGetNodesOutgoingTrollsRelationshipsOperationHandler()
    {
        return GetNodesOutgoingTrollsRelationshipsOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetNodesOutgoingClaimedAndClaimsRelationshipsOperation>> getGetNodesOutgoingClaimedAndClaimsRelationshipsOperationHandler()
    {
        return GetNodesOutgoingClaimedAndClaimsRelationshipsOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetNodesIncomingClaimsRelationshipsOperation>> getGetNodesIncomingClaimsRelationshipsOperationHandler()
    {
        return GetNodesIncomingClaimsRelationshipsOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<GetNodesOutgoingIsSpotlightRelationshipsOperation>> getGetNodesOutgoingIsSpotlightRelationshipsOperationHandler()
    {
        return GetNodesOutgoingIsSpotlightRelationshipsOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<DeleteNodeOperation>> getDeleteNodeOperationHandler()
    {
        return DeleteNodeOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<DeleteRelationshipOperation>> getDeleteRelationshipOperationHandler()
    {
        return DeleteRelationshipOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<UpdateNodeNeoPinBoardOperation>> getUpdateNodeNeoPinBoardOperationHandler()
    {
        return UpdateNodeNeoPinBoardOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<UpdateNodeNeoPinOperation>> getUpdateNodeNeoPinOperationHandler()
    {
        return UpdateNodeNeoPinOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<UpdateNodeNeoSiteOperation>> getUpdateNodeNeoSiteOperationHandler()
    {
        return UpdateNodeNeoSiteOperationHandler.class;
    }
}
