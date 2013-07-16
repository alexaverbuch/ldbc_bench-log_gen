package com.ldbc.driver.dshini.utils;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.Matchable;
import com.ldbc.driver.dshini.generator.RequestLogOperationGenerator;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryReader;
import com.ldbc.driver.dshini.operations.AddNodeToIndexOperationFactory;
import com.ldbc.driver.dshini.operations.BatchOperationFactory;
import com.ldbc.driver.dshini.operations.CreateNodeOperationFactory;
import com.ldbc.driver.dshini.operations.CreateRelationshipOperationFactory;
import com.ldbc.driver.dshini.operations.DeleteNodeFromIndexOperationFactory;
import com.ldbc.driver.dshini.operations.DeleteNodeOperationFactory;
import com.ldbc.driver.dshini.operations.DeleteRelationshipOperationFactory;
import com.ldbc.driver.dshini.operations.GetNodeOperationFactory;
import com.ldbc.driver.dshini.operations.GetNodesOutRelationshipsOperationFactory;
import com.ldbc.driver.dshini.operations.GetNodesRelationshipsOperationFactory;
import com.ldbc.driver.dshini.operations.GetNodesTypedInRelationshipsOperationFactory;
import com.ldbc.driver.dshini.operations.GetNodesTypedOutRelationshipsOperationFactory;
import com.ldbc.driver.dshini.operations.GetRelationshipOperationFactory;
import com.ldbc.driver.dshini.operations.IndexQueryGetNodeOperationFactory;
import com.ldbc.driver.dshini.operations.UpdateNodePropertiesOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CheckEntitiesLikedByUsersOperationFactory;
import com.ldbc.driver.dshini.operations.cypher.CheckUserLikedEntityOperationFactory;
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

public class DshiniLogs
{
    public static RequestLogOperationGenerator[] allLogGenerators()
    {
        File[] logFiles = allLogFiles();
        RequestLogOperationGenerator[] logGenerators = new RequestLogOperationGenerator[logFiles.length];
        for ( int i = 0; i < logFiles.length; i++ )
        {
            logGenerators[i] = new RequestLogOperationGenerator( logFiles[i] );
        }
        return logGenerators;
    }

    public static RequestLogEntryReader[] allLogReaders()
    {
        File[] logFiles = allLogFiles();
        RequestLogEntryReader[] logReaders = new RequestLogEntryReader[logFiles.length];
        for ( int i = 0; i < logFiles.length; i++ )
        {
            logReaders[i] = new RequestLogEntryReader( logFiles[i] );
        }
        return logReaders;
    }

    public static File[] allLogFiles()
    {
        File f1 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log" );
        File f2 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-196-162-95.log" );
        File f2repairedEntries = new File(
                "logs/dshini-request-logs-2013-04-29/request-ip-10-196-162-95-repaired entries.log" );
        File f3 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-76-97-169.log" );
        File f4 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-84-146-61.log" );
        File f4repairedEntries = new File(
                "logs/dshini-request-logs-2013-04-29/request-ip-10-84-146-61-repaired entries.log" );
        File f5 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-90-59-251.log" );
        File f6 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-98-203-214.log" );

        // request-domU-12-31-39-0A-B5-D1 (binary?)
        // request-domU-12-31-39-0B-D0-81
        // request-domU-12-31-39-0C-90-81

        return new File[] { f1, f2, f2repairedEntries, f3, f4, f4repairedEntries, f5, f6 };
    }

    public static Set<Class<? extends Operation<?>>> allDshiniOperationTypes()
    {
        Set<Class<? extends Operation<?>>> operationTypes = new HashSet<Class<? extends Operation<?>>>();
        for ( Matchable<?> matchable : allDshiniOperationFactories() )
        {
            operationTypes.add( matchable.operationType() );
        }
        return operationTypes;
    }

    public static Set<Matchable<RequestLogEntry>> allDshiniOperationFactories()
    {
        Set<Matchable<RequestLogEntry>> matchables = new HashSet<Matchable<RequestLogEntry>>();

        /*
         * API
         */

        matchables.add( new AddNodeToIndexOperationFactory() );
        matchables.add( new CreateNodeOperationFactory() );
        matchables.add( new CreateRelationshipOperationFactory() );
        matchables.add( new DeleteNodeFromIndexOperationFactory() );
        matchables.add( new DeleteNodeOperationFactory() );
        matchables.add( new DeleteRelationshipOperationFactory() );
        matchables.add( new GetNodeOperationFactory() );
        matchables.add( new GetNodesRelationshipsOperationFactory() );
        matchables.add( new GetNodesOutRelationshipsOperationFactory() );
        matchables.add( new GetNodesTypedInRelationshipsOperationFactory() );
        matchables.add( new GetNodesTypedOutRelationshipsOperationFactory() );
        matchables.add( new GetRelationshipOperationFactory() );
        matchables.add( new IndexQueryGetNodeOperationFactory() );
        matchables.add( new UpdateNodePropertiesOperationFactory() );

        /*
         * BATCH
         */
        matchables.add( new BatchOperationFactory() );

        /*
         * Cypher
         */

        matchables.add( new GetCategoriesOfProducts1OperationFactory() );
        matchables.add( new GetBoardsPinnedByPinsOperationFactory() );
        matchables.add( new GetCommentedOnPinsOperationFactory() );
        matchables.add( new GetAuthorsOfPinsOperationFactory() );
        matchables.add( new GetAuthorsOfCommentsOperationFactory() );
        matchables.add( new GetAuthorsOfCommentsOnPinsOperationFactory() );
        matchables.add( new GetUserSubscriptionsOperationFactory() );
        matchables.add( new GetBoardsOnSitesOwnedByUsersOperationFactory() );
        matchables.add( new GetSitesShowingBoardsOperationFactory() );
        matchables.add( new GetSiteModeratorsOperationFactory() );
        matchables.add( new GetSiteOwnersOperationFactory() );
        matchables.add( new GetUrlsReferencedByPinsOperationFactory() );
        matchables.add( new GetPinAssetsOperationFactory() );
        matchables.add( new GetUsersWhoLikedEntityOperationFactory() );
        matchables.add( new GetUrlsOfHostsOperationFactory() );
        matchables.add( new CheckUserSubscribedToBoardOperationFactory() );
        matchables.add( new CheckUsersSubscribedToSitesOperationFactory() );
        matchables.add( new CountUsersSubscribedToSitesShowingBoardsOperationFactory() );
        matchables.add( new CountPinsOnBoards1OperationFactory() );
        matchables.add( new CountPinsOnBoards2OperationFactory() );
        matchables.add( new GetClaimedByUsersOperationFactory() );
        matchables.add( new GetClaimsByUsersOperationFactory() );
        matchables.add( new GetUsersWhoClaimedOperationFactory() );
        matchables.add( new GetUsersWhoClaimsOperationFactory() );
        matchables.add( new CheckUserLikedEntityOperationFactory() );
        matchables.add( new GetUsersThatLikedEntitiesOperationFactory() );
        matchables.add( new GetUsersThatRepinnedPinOperationFactory() );
        matchables.add( new GetPinsRepinnedByUserOperationFactory() );
        matchables.add( new GetOriginalPinOperationFactory() );
        matchables.add( new CountUsersWhoLikedThingsOperationFactory() );
        matchables.add( new CountRepinsOperationFactory() );
        matchables.add( new GetPinsAndTheirAuthorsAndAssetsOnBoardsOperationFactory() );
        matchables.add( new GetUsersSubscribedToBoardsShownOnSitesOwnedByUserOperationFactory() );
        matchables.add( new CountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperationFactory() );
        matchables.add( new GetSitesOwnedByUsers2OperationFactory() );
        matchables.add( new GetCommentsAndAuthorsOnPins2OperationFactory() );
        matchables.add( new GetPinsAndTheirAuthorsAndAssetsAndBoardsOnSitesSubscribedToByUsersOperationFactory() );
        matchables.add( new CountRecentPinsOperationFactory() );
        matchables.add( new CountCommentsOnPinsOperationFactory() );
        matchables.add( new GetBoardsShownOnSitesOperationFactory() );
        matchables.add( new CountUsersSubscribedToBoardsOnSitesOperationFactory() );
        matchables.add( new CountAuthorsOfCommentsOnPinsThatHaveNotBeenTrolledUserOperationFactory() );
        matchables.add( new GetCommentsAndAuthorsOnPins1OperationFactory() );
        matchables.add( new CountOwnersOfSitesShowingBoardsSubscribedToByUserOperationFactory() );
        matchables.add( new CountPinsByAuthorsOnBoardsShownOnSitesOperationFactory() );
        matchables.add( new CountThingsLikedByUsersOperationFactory() );
        matchables.add( new GetPinsAndTheirAssetsOnBoardsOperationFactory() );
        matchables.add( new GetOwnersOfSitesShowingBoardsSubscribedToByUsers2OperationFactory() );
        matchables.add( new CountOwnersOfSitesShowingBoardsSubscribedToByUsersOperationFactory() );
        matchables.add( new GetOwnersOfSitesShowingBoardsSubscribedToByUsers1OperationFactory() );
        matchables.add( new GetCategoriesOfProducts2OperationFactory() );
        matchables.add( new GetBoardsShownOnSitesOwnedByUsersOperationFactory() );
        matchables.add( new CountPinsReferencingUrlsOperationFactory() );
        matchables.add( new GetUsersSubscribedToSitesShowingBoardsOperationFactory() );
        matchables.add( new CheckUserSubscribedToEntityOperationFactory() );
        matchables.add( new GetOffersInCategoryOfferedByUser1OperationFactory() );
        matchables.add( new GetOffersInCategoryOfferedByUser2OperationFactory() );
        matchables.add( new GetOffersInCategoryOfferedByUser3OperationFactory() );
        matchables.add( new GetOffersByUserOperationFactory() );
        matchables.add( new GetCategoriesOfProductsOfferedByUserOperationFactory() );
        matchables.add( new GetParentCategoriesOfSubCategoriesOperationFactory() );
        matchables.add( new GetProductsInCategoriesUnderOffer2OperationFactory() );
        matchables.add( new CountProductsInCategoriesUnderOffer2OperationFactory() );
        matchables.add( new GetSubscriptionsOfUsersToEntitiesOperationFactory() );
        matchables.add( new GetCommentsOnPin1OperationFactory() );
        matchables.add( new GetCommentsOnPin2OperationFactory() );
        matchables.add( new GetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperationFactory() );
        matchables.add( new GetRootOperationFactory() );
        matchables.add( new GetUsersLikesOnEntityOperationFactory() );
        matchables.add( new GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards1OperationFactory() );
        matchables.add( new GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards2OperationFactory() );
        matchables.add( new GetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperationFactory() );
        matchables.add( new GetSpotlightPinsOperationFactory() );
        matchables.add( new CountProductsUnderOfferOperationFactory() );
        matchables.add( new GetProductsUnderOfferOperationFactory() );
        matchables.add( new GetCategoriesOfProductsUnderOfferOperationFactory() );
        matchables.add( new GetPinsOnAssetsOperationFactory() );
        matchables.add( new GetPinnedViaOperationFactory() );
        matchables.add( new GetParentBoardsOfBoardsOperationFactory() );
        matchables.add( new GetSitesOwnedByUsers1OperationFactory() );
        matchables.add( new GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1OperationFactory() );
        matchables.add( new GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites2OperationFactory() );
        matchables.add( new GetSubCategoriesOfParentCategories1OperationFactory() );
        matchables.add( new GetSubCategoriesOfParentCategories2OperationFactory() );
        matchables.add( new GetSubCategoriesOfParentCategories3OperationFactory() );
        matchables.add( new GetPinsLikedByOwnersOfSitesOperationFactory() );
        matchables.add( new GetProductsInCategoriesUnderOffer1OperationFactory() );
        matchables.add( new CountProductsInCategoriesUnderOffer1OperationFactory() );
        matchables.add( new GetSubBoardsOfBoardWithNeoPins1OperationFactory() );
        matchables.add( new GetSubBoardsOfBoardWithNeoPins2OperationFactory() );
        matchables.add( new CountPinsByAuthorsOnBoardsOnSitesOperationFactory() );
        matchables.add( new GetCategoriesOfProductsUnderOfferInCountryOperationFactory() );
        matchables.add( new GetProductsInCategoriesUnderOfferInCountryOperationFactory() );
        matchables.add( new CountProductsInCategoriesUnderOfferInCountryOperationFactory() );
        matchables.add( new GetPinsInBoardsOperationFactory() );
        matchables.add( new GetProductsUnderOfferInCountryOperationFactory() );
        matchables.add( new CountProductsUnderOfferInCountryOperationFactory() );
        matchables.add( new CheckEntitiesLikedByUsersOperationFactory() );

        return matchables;
    }

}
