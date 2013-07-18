package com.ldbc.driver.dshini.operations;

import java.util.ArrayList;
import java.util.List;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.Matchable;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.operations.batch.BatchOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateNodeNeoPinBoardOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateNodeNeoPinUrlHostOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateNodeNeoPinUrlOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateNodeNeoProductOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateNodeNeoSiteOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipAuthoredByOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipBoardShownOnOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipBoughtOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipClaimedOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipClaimsOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipCommentedByOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipCommentsOnOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipInCategoryOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipOfferedByOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipOffersOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipPinReferencesUrlOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipPinnedViaOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipPinsAssetOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipPinsOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipRepinsOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipRootOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipShipsToOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipSitesOwnedByOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipSoldOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipSubBoardOfOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipSubscribesOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipTrollsOperationFactory;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipUrlHostOfOperationFactory;
import com.ldbc.driver.dshini.operations.core.DeleteNodeOperationFactory;
import com.ldbc.driver.dshini.operations.core.DeleteRelationshipOperationFactory;
import com.ldbc.driver.dshini.operations.core.GetNodeOperationFactory;
import com.ldbc.driver.dshini.operations.core.GetNodesIncomingClaimsRelationshipsOperationFactory;
import com.ldbc.driver.dshini.operations.core.GetNodesOutRelationshipsOperationFactory;
import com.ldbc.driver.dshini.operations.core.GetNodesOutgoingClaimedAndClaimsRelationshipsOperationFactory;
import com.ldbc.driver.dshini.operations.core.GetNodesOutgoingIsSpotlightRelationshipsOperationFactory;
import com.ldbc.driver.dshini.operations.core.GetNodesOutgoingTrollsRelationshipsOperationFactory;
import com.ldbc.driver.dshini.operations.core.GetNodesOutgoingWantedRelationshipsOperationFactory;
import com.ldbc.driver.dshini.operations.core.GetNodesRelationshipsOperationFactory;
import com.ldbc.driver.dshini.operations.core.GetRelationshipOperationFactory;
import com.ldbc.driver.dshini.operations.core.UpdateNodeNeoPinBoardOperationFactory;
import com.ldbc.driver.dshini.operations.core.UpdateNodeNeoPinOperationFactory;
import com.ldbc.driver.dshini.operations.core.UpdateNodeNeoSiteOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CheckEntitiesLikedByUsersOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CheckUserSubscribedToBoardOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CheckUserSubscribedToEntityOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountAuthorsOfCommentsOnPinsThatHaveNotBeenTrolledUserOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountCommentsOnPinsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountOwnersOfSitesShowingBoardsSubscribedToByUserOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountPinsOnBoards2OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountThingsLikedByUsersOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountUsersWhoLikedThingsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountProductsInCategoriesUnderOffer2OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountOwnersOfSitesShowingBoardsSubscribedToByUsersOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountPinsByAuthorsOnBoardsShownOnSitesOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountPinsByAuthorsOnBoardsOnSitesOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountPinsOnBoards1OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountPinsReferencingUrlsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountProductsInCategoriesUnderOfferInCountryOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountProductsInCategoriesUnderOffer1OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountProductsUnderOfferInCountryOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountProductsUnderOfferOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountRecentPinsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountRepinsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountUsersSubscribedToBoardsOnSitesOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetCommentsAndAuthorsOnPins1OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetAuthorsOfPinsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetBoardsShownOnSitesOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetBoardsShownOnSitesOwnedByUsersOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetBoardsOnSitesOwnedByUsersOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetCategoriesOfProductsOfferedByUserOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetCategoriesOfProductsUnderOfferInCountryOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetCategoriesOfProducts1OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetCategoriesOfProductsUnderOfferOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetClaimedByUsersOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetClaimsByUsersOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetCommentedOnPinsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetCommentsAndAuthorsOnPins2OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetAuthorsOfCommentsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetCommentsOnPin1OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetCommentsOnPin2OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetOwnersOfSitesShowingBoardsSubscribedToByUsers1OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetUrlsOfHostsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CountUsersSubscribedToSitesShowingBoardsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetOffersInCategoryOfferedByUser1OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetOffersInCategoryOfferedByUser2OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetOffersInCategoryOfferedByUser3OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetOffersByUserOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetOriginalPinOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetOwnersOfSitesShowingBoardsSubscribedToByUsers2OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetParentBoardsOfBoardsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetParentCategoriesOfSubCategoriesOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetPinsAndTheirAuthorsAndAssetsOnBoardsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetPinAssetsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetBoardsPinnedByPinsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetPinnedViaOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetPinsAndTheirAssetsOnBoardsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetPinsAndTheirAuthorsAndAssetsAndBoardsOnSitesSubscribedToByUsersOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites2OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards1OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards2OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetAuthorsOfCommentsOnPinsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetPinsInBoardsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetPinsLikedByOwnersOfSitesOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetPinsOnAssetsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetProductsInCategoriesUnderOffer1OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetProductsInCategoriesUnderOffer2OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetProductsInCategoriesUnderOfferInCountryOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetProductsUnderOfferInCountryOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetProductsUnderOfferOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetPinsRepinnedByUserOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetUsersThatRepinnedPinOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetRootOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetSiteModeratorsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetSiteOwnersOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetSitesShowingBoardsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetSitesOwnedByUsers2OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetSitesOwnedByUsers1OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetSpotlightPinsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetSubBoardsOfBoardWithNeoPins1OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetSubBoardsOfBoardWithNeoPins2OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetSubCategoriesOfParentCategories1OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetSubCategoriesOfParentCategories2OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetSubCategoriesOfParentCategories3OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetCategoriesOfProducts2OperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetUserSubscriptionsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetUrlsReferencedByPinsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetUsersLikesOnEntityOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetUsersSubscribedToBoardsShownOnSitesOwnedByUserOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CheckUsersSubscribedToSitesOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetUsersSubscribedToSitesShowingBoardsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetSubscriptionsOfUsersToEntitiesOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetUsersThatLikedEntitiesOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetUsersWhoClaimedOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetUsersWhoClaimsOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.GetUsersWhoLikedEntityOperationFactory;
import com.ldbc.driver.dshini.operations.index.AddNodeToNeoPinBoardIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.AddNodeToNeoPinIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.AddNodeToNeoPinUrlHostIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.AddNodeToNeoPinUrlIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.AddNodeToNeoProductIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.AddNodeToNeoSiteIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.DeleteNodeFromNeoPinBoardIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.DeleteNodeFromNeoPinCommentIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.DeleteNodeFromNeoPinIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.DeleteNodeFromNeoSiteIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoCategoryIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinApplicationIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinBoardIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinCommentIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinEntertainmentVideoIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinGameImageIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinImageIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinProductImageIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinUrlHostIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinUrlIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinYoutubeVideoIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoProductIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoRootIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoShippingCountryIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoSiteIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnOfferIndexOperationFactory;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnUserProfileIndexOperationFactory;
import com.ldbc.driver.util.Pair;

public class Dshini
{
    private static final OperationFactories factories = new OperationFactories();
    private static final OperationTypes operationTypes = new OperationTypes();

    public enum ReadWrite
    {
        READ,
        WRITE,
        READWRITE
    }

    public static OperationTypes operations()
    {
        return operationTypes;
    }

    public static OperationFactories factories()
    {
        return factories;
    }

    private static List<Matchable<RequestLogEntry>> filterReadWrite(
            List<Pair<ReadWrite, ? extends Matchable<RequestLogEntry>>> readWriteFactories, ReadWrite readWrite )
    {
        List<Matchable<RequestLogEntry>> factories = new ArrayList<Matchable<RequestLogEntry>>();
        for ( Pair<ReadWrite, ? extends Matchable<RequestLogEntry>> factory : readWriteFactories )
        {
            if ( compareReadWrites( readWrite, factory._1() ) )
            {
                factories.add( factory._2() );
            }
        }
        return factories;
    }

    private static boolean compareReadWrites( ReadWrite base, ReadWrite compare )
    {
        switch ( base )
        {
        case READ:
            return compare.equals( base );
        case WRITE:
            return compare.equals( base );
        case READWRITE:
            return true;
        default:
            throw new RuntimeException( "Unrecognized ReadWrite value: " + base.toString() );
        }
    }

    // TODO doesn't work. Matchable impls don't implement equals or hashcode
    private static <T> List<T> addIfNotExists( List<T> list, T value )
    {
        if ( list.contains( value ) )
        {
            throw new RuntimeException(
                    String.format( "Value [%s] already present, cannot add twice", value.toString() ) );
        }
        list.add( value );
        return list;
    }

    public static class OperationTypes
    {
        public List<Class<? extends Operation<?>>> all( ReadWrite readWrite )
        {
            return operationTypes( factories().all( readWrite ) );
        }

        public List<Class<? extends Operation<?>>> batch( ReadWrite readWrite )
        {
            return operationTypes( factories().batch( readWrite ) );
        }

        public List<Class<? extends Operation<?>>> core( ReadWrite readWrite )
        {
            return operationTypes( factories().core( readWrite ) );
        }

        public List<Class<? extends Operation<?>>> cypher( ReadWrite readWrite )
        {
            return operationTypes( factories().cypher( readWrite ) );
        }

        public List<Class<? extends Operation<?>>> index( ReadWrite readWrite )
        {
            return operationTypes( factories().index( readWrite ) );
        }

        private List<Class<? extends Operation<?>>> operationTypes( List<Matchable<RequestLogEntry>> matchables )
        {
            List<Class<? extends Operation<?>>> operationTypes = new ArrayList<Class<? extends Operation<?>>>();
            for ( Matchable<?> matchable : matchables )
            {
                operationTypes.add( matchable.operationType() );
            }
            return operationTypes;
        }
    }

    public static class OperationFactories
    {
        public List<Matchable<RequestLogEntry>> all( ReadWrite readWrite )
        {
            List<Matchable<RequestLogEntry>> matchables = new ArrayList<Matchable<RequestLogEntry>>();
            matchables.addAll( core( readWrite ) );
            matchables.addAll( index( readWrite ) );
            matchables.addAll( cypher( readWrite ) );
            matchables.addAll( batch( readWrite ) );
            return matchables;
        }

        public List<Matchable<RequestLogEntry>> batch( ReadWrite readWrite )
        {
            return filterReadWrite( batch(), readWrite );
        }

        public List<Matchable<RequestLogEntry>> core( ReadWrite readWrite )
        {
            return filterReadWrite( core(), readWrite );
        }

        public List<Matchable<RequestLogEntry>> cypher( ReadWrite readWrite )
        {
            return filterReadWrite( cypher(), readWrite );
        }

        public List<Matchable<RequestLogEntry>> index( ReadWrite readWrite )
        {
            return filterReadWrite( index(), readWrite );
        }

        private List<Pair<ReadWrite, ? extends Matchable<RequestLogEntry>>> batch()
        {
            List<Pair<ReadWrite, ? extends Matchable<RequestLogEntry>>> matchables = new ArrayList<Pair<ReadWrite, ? extends Matchable<RequestLogEntry>>>();

            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.WRITE, new BatchOperationFactory() ) );

            return matchables;
        }

        private List<Pair<ReadWrite, ? extends Matchable<RequestLogEntry>>> core()
        {
            List<Pair<ReadWrite, ? extends Matchable<RequestLogEntry>>> matchables = new ArrayList<Pair<ReadWrite, ? extends Matchable<RequestLogEntry>>>();

            // Create Relationship
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipSoldOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipSitesOwnedByOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipShipsToOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipRootOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipRepinsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipPinsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipPinsAssetOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipPinReferencesUrlOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipPinnedViaOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipOffersOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipOfferedByOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipInCategoryOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipCommentsOnOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipCommentedByOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipClaimsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipClaimedOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipBoughtOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipBoardShownOnOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipAuthoredByOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipSubBoardOfOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipSubscribesOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipTrollsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateRelationshipUrlHostOfOperationFactory() ) );

            // Create Node
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateNodeNeoPinBoardOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateNodeNeoPinUrlHostOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateNodeNeoPinUrlOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateNodeNeoProductOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new CreateNodeNeoSiteOperationFactory() ) );

            // Get Nodes
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ, new GetNodeOperationFactory() ) );

            // Get Relationships
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetRelationshipOperationFactory() ) );

            // Get Node's Relationships
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetNodesRelationshipsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetNodesOutRelationshipsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetNodesOutgoingWantedRelationshipsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetNodesOutgoingTrollsRelationshipsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetNodesOutgoingClaimedAndClaimsRelationshipsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetNodesIncomingClaimsRelationshipsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetNodesOutgoingIsSpotlightRelationshipsOperationFactory() ) );

            // Delete Nodes
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.WRITE, new DeleteNodeOperationFactory() ) );

            // Delete Relationships
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new DeleteRelationshipOperationFactory() ) );

            // Update Nodes
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new UpdateNodeNeoPinBoardOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new UpdateNodeNeoPinOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new UpdateNodeNeoSiteOperationFactory() ) );

            return matchables;
        }

        private List<Pair<ReadWrite, ? extends Matchable<RequestLogEntry>>> cypher()
        {
            List<Pair<ReadWrite, ? extends Matchable<RequestLogEntry>>> matchables = new ArrayList<Pair<ReadWrite, ? extends Matchable<RequestLogEntry>>>();

            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CheckEntitiesLikedByUsersOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CheckUsersSubscribedToSitesOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CheckUserSubscribedToBoardOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CheckUserSubscribedToEntityOperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ,
                    new CountAuthorsOfCommentsOnPinsThatHaveNotBeenTrolledUserOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CountCommentsOnPinsOperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ,
                    new CountOwnersOfSitesShowingBoardsSubscribedToByUserOperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ,
                    new CountOwnersOfSitesShowingBoardsSubscribedToByUsersOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CountPinsByAuthorsOnBoardsOnSitesOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CountPinsByAuthorsOnBoardsShownOnSitesOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CountPinsOnBoards1OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CountPinsOnBoards2OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CountPinsReferencingUrlsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CountProductsInCategoriesUnderOffer1OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CountProductsInCategoriesUnderOffer2OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CountProductsInCategoriesUnderOfferInCountryOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CountProductsUnderOfferInCountryOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CountProductsUnderOfferOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CountRecentPinsOperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ, new CountRepinsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CountThingsLikedByUsersOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CountUsersSubscribedToBoardsOnSitesOperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ,
                    new CountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CountUsersSubscribedToSitesShowingBoardsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new CountUsersWhoLikedThingsOperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ,
                    new GetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetAuthorsOfCommentsOnPinsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetAuthorsOfCommentsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetAuthorsOfPinsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetBoardsOnSitesOwnedByUsersOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetBoardsPinnedByPinsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetBoardsShownOnSitesOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetBoardsShownOnSitesOwnedByUsersOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetCategoriesOfProducts1OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetCategoriesOfProducts2OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetCategoriesOfProductsOfferedByUserOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetCategoriesOfProductsUnderOfferInCountryOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetCategoriesOfProductsUnderOfferOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetClaimedByUsersOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetClaimsByUsersOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetCommentedOnPinsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetCommentsAndAuthorsOnPins1OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetCommentsAndAuthorsOnPins2OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetCommentsOnPin1OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetCommentsOnPin2OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetOffersByUserOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetOffersInCategoryOfferedByUser1OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetOffersInCategoryOfferedByUser2OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetOffersInCategoryOfferedByUser3OperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ, new GetOriginalPinOperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ,
                    new GetOwnersOfSitesShowingBoardsSubscribedToByUsers1OperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ,
                    new GetOwnersOfSitesShowingBoardsSubscribedToByUsers2OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetParentBoardsOfBoardsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetParentCategoriesOfSubCategoriesOperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ, new GetPinAssetsOperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ, new GetPinnedViaOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetPinsAndTheirAssetsOnBoardsOperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ,
                    new GetPinsAndTheirAuthorsAndAssetsAndBoardsOnSitesSubscribedToByUsersOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetPinsAndTheirAuthorsAndAssetsOnBoardsOperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ,
                    new GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1OperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ,
                    new GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites2OperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ,
                    new GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards1OperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ,
                    new GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards2OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetPinsInBoardsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetPinsLikedByOwnersOfSitesOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetPinsOnAssetsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetPinsRepinnedByUserOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetProductsInCategoriesUnderOffer1OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetProductsInCategoriesUnderOffer2OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetProductsInCategoriesUnderOfferInCountryOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetProductsUnderOfferInCountryOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetProductsUnderOfferOperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ, new GetRootOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetSiteModeratorsOperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ, new GetSiteOwnersOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetSitesOwnedByUsers1OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetSitesOwnedByUsers2OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetSitesShowingBoardsOperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ,
                    new GetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetSpotlightPinsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetSubBoardsOfBoardWithNeoPins1OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetSubBoardsOfBoardWithNeoPins2OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetSubCategoriesOfParentCategories1OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetSubCategoriesOfParentCategories2OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetSubCategoriesOfParentCategories3OperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetSubscriptionsOfUsersToEntitiesOperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ, new GetUrlsOfHostsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetUrlsReferencedByPinsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetUsersLikesOnEntityOperationFactory() ) );
            matchables = addIfNotExists( matchables, Pair.create( ReadWrite.READ,
                    new GetUsersSubscribedToBoardsShownOnSitesOwnedByUserOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetUsersSubscribedToSitesShowingBoardsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetUsersThatLikedEntitiesOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetUsersThatRepinnedPinOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetUserSubscriptionsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetUsersWhoClaimedOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetUsersWhoClaimsOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new GetUsersWhoLikedEntityOperationFactory() ) );

            return matchables;
        }

        private List<Pair<ReadWrite, ? extends Matchable<RequestLogEntry>>> index()
        {
            List<Pair<ReadWrite, ? extends Matchable<RequestLogEntry>>> matchables = new ArrayList<Pair<ReadWrite, ? extends Matchable<RequestLogEntry>>>();

            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new IndexQueryNodeOnNeoCategoryIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new IndexQueryNodeOnNeoPinApplicationIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new IndexQueryNodeOnNeoPinBoardIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new IndexQueryNodeOnNeoPinCommentIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new IndexQueryNodeOnNeoPinEntertainmentVideoIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new IndexQueryNodeOnNeoPinGameImageIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new IndexQueryNodeOnNeoPinImageIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new IndexQueryNodeOnNeoPinIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new IndexQueryNodeOnNeoPinProductImageIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new IndexQueryNodeOnNeoPinUrlIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new IndexQueryNodeOnNeoPinUrlHostIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new IndexQueryNodeOnNeoPinYoutubeVideoIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new IndexQueryNodeOnNeoProductIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new IndexQueryNodeOnNeoRootIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new IndexQueryNodeOnNeoShippingCountryIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new IndexQueryNodeOnNeoSiteIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new IndexQueryNodeOnOfferIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.READ, new IndexQueryNodeOnUserProfileIndexOperationFactory() ) );

            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new AddNodeToNeoPinBoardIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new AddNodeToNeoPinIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new AddNodeToNeoPinUrlHostIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new AddNodeToNeoPinUrlIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new AddNodeToNeoProductIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new AddNodeToNeoSiteIndexOperationFactory() ) );

            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new DeleteNodeFromNeoPinBoardIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new DeleteNodeFromNeoPinCommentIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new DeleteNodeFromNeoPinIndexOperationFactory() ) );
            matchables = addIfNotExists( matchables,
                    Pair.create( ReadWrite.WRITE, new DeleteNodeFromNeoSiteIndexOperationFactory() ) );

            return matchables;
        }
    }
}
