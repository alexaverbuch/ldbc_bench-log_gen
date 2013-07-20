package com.ldbc.driver.dshini.workloads;

import com.ldbc.driver.Db;
import com.ldbc.driver.DbException;
import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.dshini.operations.cypher.CheckEntitiesLikedByUsersOperationFactory.CheckEntitiesLikedByUsersOperation;
import com.ldbc.driver.dshini.operations.cypher.CheckUserSubscribedToBoardOperationFactory.CheckUserSubscribedToBoardOperation;
import com.ldbc.driver.dshini.operations.cypher.CheckUserSubscribedToEntityOperationFactory.CheckUserSubscribedToEntityOperation;
import com.ldbc.driver.dshini.operations.cypher.CheckUsersSubscribedToSitesOperationFactory.CheckUsersSubscribedToSitesOperation;
import com.ldbc.driver.dshini.operations.cypher.CountAuthorsOfCommentsOnPinsThatHaveNotBeenTrolledUserOperationFactory.CountAuthorsOfCommentsOnPinsThatHaveNotBeenTrolledUserOperation;
import com.ldbc.driver.dshini.operations.cypher.CountCommentsOnPinsOperationFactory.CountCommentsOnPinsOperation;
import com.ldbc.driver.dshini.operations.cypher.CountOwnersOfSitesShowingBoardsSubscribedToByUserOperationFactory.CountOwnersOfSitesShowingBoardsSubscribedToByUserOperation;
import com.ldbc.driver.dshini.operations.cypher.CountOwnersOfSitesShowingBoardsSubscribedToByUsersOperationFactory.CountOwnersOfSitesShowingBoardsSubscribedToByUsersOperation;
import com.ldbc.driver.dshini.operations.cypher.CountPinsByAuthorsOnBoardsOnSitesOperationFactory.CountPinsByAuthorsOnBoardsOnSitesOperation;
import com.ldbc.driver.dshini.operations.cypher.CountPinsByAuthorsOnBoardsShownOnSitesOperationFactory.CountPinsByAuthorsOnBoardsShownOnSitesOperation;
import com.ldbc.driver.dshini.operations.cypher.CountPinsOnBoards1OperationFactory.CountPinsOnBoards1Operation;
import com.ldbc.driver.dshini.operations.cypher.CountPinsOnBoards2OperationFactory.CountPinsOnBoards2Operation;
import com.ldbc.driver.dshini.operations.cypher.CountPinsReferencingUrlsOperationFactory.CountPinsReferencingUrlsOperation;
import com.ldbc.driver.dshini.operations.cypher.CountProductsInCategoriesUnderOffer1OperationFactory.CountProductsInCategoriesUnderOffer1Operation;
import com.ldbc.driver.dshini.operations.cypher.CountProductsInCategoriesUnderOffer2OperationFactory.CountProductsInCategoriesUnderOffer2Operation;
import com.ldbc.driver.dshini.operations.cypher.CountProductsInCategoriesUnderOfferInCountryOperationFactory.CountProductsInCategoriesUnderOfferInCountryOperation;
import com.ldbc.driver.dshini.operations.cypher.CountProductsUnderOfferInCountryOperationFactory.CountProductsUnderOfferInCountryOperation;
import com.ldbc.driver.dshini.operations.cypher.CountProductsUnderOfferOperationFactory.CountProductsUnderOfferOperation;
import com.ldbc.driver.dshini.operations.cypher.CountRecentPinsOperationFactory.CountRecentPinsOperation;
import com.ldbc.driver.dshini.operations.cypher.CountRepinsOperationFactory.CountRepinsOperation;
import com.ldbc.driver.dshini.operations.cypher.CountThingsLikedByUsersOperationFactory.CountThingsLikedByUsersOperation;
import com.ldbc.driver.dshini.operations.cypher.CountUsersSubscribedToBoardsOnSitesOperationFactory.CountUsersSubscribedToBoardsOnSitesOperation;
import com.ldbc.driver.dshini.operations.cypher.CountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperationFactory.CountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperation;
import com.ldbc.driver.dshini.operations.cypher.CountUsersSubscribedToSitesShowingBoardsOperationFactory.CountUsersSubscribedToSitesShowingBoardsOperation;
import com.ldbc.driver.dshini.operations.cypher.CountUsersWhoLikedThingsOperationFactory.CountUsersWhoLikedThingsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperationFactory.GetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperation;
import com.ldbc.driver.dshini.operations.cypher.GetAuthorsOfCommentsOnPinsOperationFactory.GetAuthorsOfCommentsOnPinsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetAuthorsOfCommentsOperationFactory.GetAuthorsOfCommentsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetAuthorsOfPinsOperationFactory.GetAuthorsOfPinsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetBoardsOnSitesOwnedByUsersOperationFactory.GetBoardsOnSitesOwnedByUsersOperation;
import com.ldbc.driver.dshini.operations.cypher.GetBoardsPinnedByPinsOperationFactory.GetBoardsPinnedByPinsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetBoardsShownOnSitesOperationFactory.GetBoardsShownOnSitesOperation;
import com.ldbc.driver.dshini.operations.cypher.GetBoardsShownOnSitesOwnedByUsersOperationFactory.GetBoardsShownOnSitesOwnedByUsersOperation;
import com.ldbc.driver.dshini.operations.cypher.GetCategoriesOfProducts1OperationFactory.GetCategoriesOfProducts1Operation;
import com.ldbc.driver.dshini.operations.cypher.GetCategoriesOfProducts2OperationFactory.GetCategoriesOfProducts2Operation;
import com.ldbc.driver.dshini.operations.cypher.GetCategoriesOfProductsOfferedByUserOperationFactory.GetCategoriesOfProductsOfferedByUserOperation;
import com.ldbc.driver.dshini.operations.cypher.GetCategoriesOfProductsUnderOfferInCountryOperationFactory.GetCategoriesOfProductsUnderOfferInCountryOperation;
import com.ldbc.driver.dshini.operations.cypher.GetCategoriesOfProductsUnderOfferOperationFactory.GetCategoriesOfProductsUnderOfferOperation;
import com.ldbc.driver.dshini.operations.cypher.GetClaimedByUsersOperationFactory.GetClaimedByUsersOperation;
import com.ldbc.driver.dshini.operations.cypher.GetClaimsByUsersOperationFactory.GetClaimsByUsersOperation;
import com.ldbc.driver.dshini.operations.cypher.GetCommentedOnPinsOperationFactory.GetCommentedOnPinsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetCommentsAndAuthorsOnPins1OperationFactory.GetCommentsAndAuthorsOnPins1Operation;
import com.ldbc.driver.dshini.operations.cypher.GetCommentsAndAuthorsOnPins2OperationFactory.GetCommentsAndAuthorsOnPins2Operation;
import com.ldbc.driver.dshini.operations.cypher.GetCommentsOnPin1OperationFactory.GetCommentsOnPin1Operation;
import com.ldbc.driver.dshini.operations.cypher.GetCommentsOnPin2OperationFactory.GetCommentsOnPin2Operation;
import com.ldbc.driver.dshini.operations.cypher.GetOffersByUserOperationFactory.GetOffersByUserOperation;
import com.ldbc.driver.dshini.operations.cypher.GetOffersInCategoryOfferedByUser1OperationFactory.GetOffersInCategoryOfferedByUser1Operation;
import com.ldbc.driver.dshini.operations.cypher.GetOffersInCategoryOfferedByUser2OperationFactory.GetOffersInCategoryOfferedByUser2Operation;
import com.ldbc.driver.dshini.operations.cypher.GetOffersInCategoryOfferedByUser3OperationFactory.GetOffersInCategoryOfferedByUser3Operation;
import com.ldbc.driver.dshini.operations.cypher.GetOriginalPinOperationFactory.GetOriginalPinOperation;
import com.ldbc.driver.dshini.operations.cypher.GetOwnersOfSitesShowingBoardsSubscribedToByUsers1OperationFactory.GetOwnersOfSitesShowingBoardsSubscribedToByUsers1Operation;
import com.ldbc.driver.dshini.operations.cypher.GetOwnersOfSitesShowingBoardsSubscribedToByUsers2OperationFactory.GetOwnersOfSitesShowingBoardsSubscribedToByUsers2Operation;
import com.ldbc.driver.dshini.operations.cypher.GetParentBoardsOfBoardsOperationFactory.GetParentBoardsOfBoardsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetParentCategoriesOfSubCategoriesOperationFactory.GetParentCategoriesOfSubCategoriesOperation;
import com.ldbc.driver.dshini.operations.cypher.GetPinAssetsOperationFactory.GetPinAssetsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetPinnedViaOperationFactory.GetPinnedViaOperation;
import com.ldbc.driver.dshini.operations.cypher.GetPinsAndTheirAssetsOnBoardsOperationFactory.GetPinsAndTheirAssetsOnBoardsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetPinsAndTheirAuthorsAndAssetsAndBoardsOnSitesSubscribedToByUsersOperationFactory.GetPinsAndTheirAuthorsAndAssetsAndBoardsOnSitesSubscribedToByUsersOperation;
import com.ldbc.driver.dshini.operations.cypher.GetPinsAndTheirAuthorsAndAssetsOnBoardsOperationFactory.GetPinsAndTheirAuthorsAndAssetsOnBoardsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1OperationFactory.GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1Operation;
import com.ldbc.driver.dshini.operations.cypher.GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites2OperationFactory.GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites2Operation;
import com.ldbc.driver.dshini.operations.cypher.GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards1OperationFactory.GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards1Operation;
import com.ldbc.driver.dshini.operations.cypher.GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards2OperationFactory.GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards2Operation;
import com.ldbc.driver.dshini.operations.cypher.GetPinsInBoardsOperationFactory.GetPinsInBoardsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetPinsLikedByOwnersOfSitesOperationFactory.GetPinsLikedByOwnersOfSitesOperation;
import com.ldbc.driver.dshini.operations.cypher.GetPinsOnAssetsOperationFactory.GetPinsOnAssetsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetPinsRepinnedByUserOperationFactory.GetPinsRepinnedByUserOperation;
import com.ldbc.driver.dshini.operations.cypher.GetProductsInCategoriesUnderOffer1OperationFactory.GetProductsInCategoriesUnderOffer1Operation;
import com.ldbc.driver.dshini.operations.cypher.GetProductsInCategoriesUnderOffer2OperationFactory.GetProductsInCategoriesUnderOffer2Operation;
import com.ldbc.driver.dshini.operations.cypher.GetProductsInCategoriesUnderOfferInCountryOperationFactory.GetProductsInCategoriesUnderOfferInCountryOperation;
import com.ldbc.driver.dshini.operations.cypher.GetProductsUnderOfferInCountryOperationFactory.GetProductsUnderOfferInCountryOperation;
import com.ldbc.driver.dshini.operations.cypher.GetProductsUnderOfferOperationFactory.GetProductsUnderOfferOperation;
import com.ldbc.driver.dshini.operations.cypher.GetRootOperationFactory.GetRootOperation;
import com.ldbc.driver.dshini.operations.cypher.GetSiteModeratorsOperationFactory.GetSiteModeratorsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetSiteOwnersOperationFactory.GetSiteOwnersOperation;
import com.ldbc.driver.dshini.operations.cypher.GetSitesOwnedByUsers1OperationFactory.GetSitesOwnedByUsers1Operation;
import com.ldbc.driver.dshini.operations.cypher.GetSitesOwnedByUsers2OperationFactory.GetSitesOwnedByUsers2Operation;
import com.ldbc.driver.dshini.operations.cypher.GetSitesShowingBoardsOperationFactory.GetSitesShowingBoardsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperationFactory.GetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetSpotlightPinsOperationFactory.GetSpotlightPinsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetSubBoardsOfBoardWithNeoPins1OperationFactory.GetSubBoardsOfBoardWithNeoPins1Operation;
import com.ldbc.driver.dshini.operations.cypher.GetSubBoardsOfBoardWithNeoPins2OperationFactory.GetSubBoardsOfBoardWithNeoPins2Operation;
import com.ldbc.driver.dshini.operations.cypher.GetSubCategoriesOfParentCategories1OperationFactory.GetSubCategoriesOfParentCategories1Operation;
import com.ldbc.driver.dshini.operations.cypher.GetSubCategoriesOfParentCategories2OperationFactory.GetSubCategoriesOfParentCategories2Operation;
import com.ldbc.driver.dshini.operations.cypher.GetSubCategoriesOfParentCategories3OperationFactory.GetSubCategoriesOfParentCategories3Operation;
import com.ldbc.driver.dshini.operations.cypher.GetSubscriptionsOfUsersToEntitiesOperationFactory.GetSubscriptionsOfUsersToEntitiesOperation;
import com.ldbc.driver.dshini.operations.cypher.GetUrlsOfHostsOperationFactory.GetUrlsOfHostsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetUrlsReferencedByPinsOperationFactory.GetUrlsReferencedByPinsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetUserSubscriptionsOperationFactory.GetUserSubscriptionsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetUsersLikesOnEntityOperationFactory.GetUsersLikesOnEntityOperation;
import com.ldbc.driver.dshini.operations.cypher.GetUsersSubscribedToBoardsShownOnSitesOwnedByUserOperationFactory.GetUsersSubscribedToBoardsShownOnSitesOwnedByUserOperation;
import com.ldbc.driver.dshini.operations.cypher.GetUsersSubscribedToSitesShowingBoardsOperationFactory.GetUsersSubscribedToSitesShowingBoardsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetUsersThatLikedEntitiesOperationFactory.GetUsersThatLikedEntitiesOperation;
import com.ldbc.driver.dshini.operations.cypher.GetUsersThatRepinnedPinOperationFactory.GetUsersThatRepinnedPinOperation;
import com.ldbc.driver.dshini.operations.cypher.GetUsersWhoClaimedOperationFactory.GetUsersWhoClaimedOperation;
import com.ldbc.driver.dshini.operations.cypher.GetUsersWhoClaimsOperationFactory.GetUsersWhoClaimsOperation;
import com.ldbc.driver.dshini.operations.cypher.GetUsersWhoLikedEntityOperationFactory.GetUsersWhoLikedEntityOperation;

public abstract class DshiniCypherCommands implements OperationHandlersRegistrar
{
    @Override
    public void registerHandlersWithDb( Db db ) throws DbException
    {
        db.registerOperationHandler( CheckEntitiesLikedByUsersOperation.class,
                getCheckEntitiesLikedByUsersOperationHandler() );
        db.registerOperationHandler( CheckUsersSubscribedToSitesOperation.class,
                getCheckUsersSubscribedToSitesOperationHandler() );
        db.registerOperationHandler( CheckUserSubscribedToBoardOperation.class,
                getCheckUserSubscribedToBoardOperationHandler() );
        db.registerOperationHandler( CheckUserSubscribedToEntityOperation.class,
                getCheckUserSubscribedToEntityOperationHandler() );
        db.registerOperationHandler( CountAuthorsOfCommentsOnPinsThatHaveNotBeenTrolledUserOperation.class,
                getCountAuthorsOfCommentsOnPinsThatHaveNotBeenTrolledUserOperationHandler() );
        db.registerOperationHandler( CountCommentsOnPinsOperation.class, getCountCommentsOnPinsOperationHandler() );
        db.registerOperationHandler( CountOwnersOfSitesShowingBoardsSubscribedToByUserOperation.class,
                getCountOwnersOfSitesShowingBoardsSubscribedToByUserOperationHandler() );
        db.registerOperationHandler( CountOwnersOfSitesShowingBoardsSubscribedToByUsersOperation.class,
                getCountOwnersOfSitesShowingBoardsSubscribedToByUsersOperationHandler() );
        db.registerOperationHandler( CountPinsByAuthorsOnBoardsOnSitesOperation.class,
                getCountPinsByAuthorsOnBoardsOnSitesOperationHandler() );
        db.registerOperationHandler( CountPinsByAuthorsOnBoardsShownOnSitesOperation.class,
                getCountPinsByAuthorsOnBoardsShownOnSitesOperationHandler() );
        db.registerOperationHandler( CountPinsOnBoards1Operation.class, getCountPinsOnBoards1OperationHandler() );
        db.registerOperationHandler( CountPinsOnBoards2Operation.class, getCountPinsOnBoards2OperationHandler() );
        db.registerOperationHandler( CountPinsReferencingUrlsOperation.class,
                getCountPinsReferencingUrlsOperationHandler() );
        db.registerOperationHandler( CountProductsInCategoriesUnderOffer1Operation.class,
                getCountProductsInCategoriesUnderOffer1OperationHandler() );
        db.registerOperationHandler( CountProductsInCategoriesUnderOffer2Operation.class,
                getCountProductsInCategoriesUnderOffer2OperationHandler() );
        db.registerOperationHandler( CountProductsInCategoriesUnderOfferInCountryOperation.class,
                getCountProductsInCategoriesUnderOfferInCountryOperationHandler() );
        db.registerOperationHandler( CountProductsUnderOfferInCountryOperation.class,
                getCountProductsUnderOfferInCountryOperationHandler() );
        db.registerOperationHandler( CountProductsUnderOfferOperation.class,
                getCountProductsUnderOfferOperationHandler() );
        db.registerOperationHandler( CountRecentPinsOperation.class, getCountRecentPinsOperationHandler() );
        db.registerOperationHandler( CountRepinsOperation.class, getCountRepinsOperationHandler() );
        db.registerOperationHandler( CountThingsLikedByUsersOperation.class,
                getCountThingsLikedByUsersOperationHandler() );
        db.registerOperationHandler( CountUsersSubscribedToBoardsOnSitesOperation.class,
                getCountUsersSubscribedToBoardsOnSitesOperationHandler() );
        db.registerOperationHandler( CountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperation.class,
                getCountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperationHandler() );
        db.registerOperationHandler( CountUsersSubscribedToSitesShowingBoardsOperation.class,
                getCountUsersSubscribedToSitesShowingBoardsOperationHandler() );
        db.registerOperationHandler( CountUsersWhoLikedThingsOperation.class,
                getCountUsersWhoLikedThingsOperationHandler() );
        db.registerOperationHandler( GetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperation.class,
                getGetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperationHandler() );
        db.registerOperationHandler( GetAuthorsOfCommentsOnPinsOperation.class,
                getGetAuthorsOfCommentsOnPinsOperationHandler() );
        db.registerOperationHandler( GetAuthorsOfCommentsOperation.class, getGetAuthorsOfCommentsOperationHandler() );
        db.registerOperationHandler( GetAuthorsOfPinsOperation.class, getGetAuthorsOfPinsOperationHandler() );
        db.registerOperationHandler( GetBoardsOnSitesOwnedByUsersOperation.class,
                getGetBoardsOnSitesOwnedByUsersOperationHandler() );
        db.registerOperationHandler( GetBoardsPinnedByPinsOperation.class, getGetBoardsPinnedByPinsOperationHandler() );
        db.registerOperationHandler( GetBoardsShownOnSitesOperation.class, getGetBoardsShownOnSitesOperationHandler() );
        db.registerOperationHandler( GetBoardsShownOnSitesOwnedByUsersOperation.class,
                getGetBoardsShownOnSitesOwnedByUsersOperationHandler() );
        db.registerOperationHandler( GetCategoriesOfProducts1Operation.class,
                getGetCategoriesOfProducts1OperationHandler() );
        db.registerOperationHandler( GetCategoriesOfProducts2Operation.class,
                getGetCategoriesOfProducts2OperationHandler() );
        db.registerOperationHandler( GetCategoriesOfProductsOfferedByUserOperation.class,
                getGetCategoriesOfProductsOfferedByUserOperationHandler() );
        db.registerOperationHandler( GetCategoriesOfProductsUnderOfferInCountryOperation.class,
                getGetCategoriesOfProductsUnderOfferInCountryOperationHandler() );
        db.registerOperationHandler( GetCategoriesOfProductsUnderOfferOperation.class,
                getGetCategoriesOfProductsUnderOfferOperationHandler() );
        db.registerOperationHandler( GetClaimedByUsersOperation.class, getGetClaimedByUsersOperationHandler() );
        db.registerOperationHandler( GetClaimsByUsersOperation.class, getGetClaimsByUsersOperationHandler() );
        db.registerOperationHandler( GetCommentedOnPinsOperation.class, getGetCommentedOnPinsOperationHandler() );
        db.registerOperationHandler( GetCommentsAndAuthorsOnPins1Operation.class,
                getGetCommentsAndAuthorsOnPins1OperationHandler() );
        db.registerOperationHandler( GetCommentsAndAuthorsOnPins2Operation.class,
                getGetCommentsAndAuthorsOnPins2OperationHandler() );
        db.registerOperationHandler( GetCommentsOnPin1Operation.class, getGetCommentsOnPin1OperationHandler() );
        db.registerOperationHandler( GetCommentsOnPin2Operation.class, getGetCommentsOnPin2OperationHandler() );
        db.registerOperationHandler( GetOffersByUserOperation.class, getGetOffersByUserOperationHandler() );
        db.registerOperationHandler( GetOffersInCategoryOfferedByUser1Operation.class,
                getGetOffersInCategoryOfferedByUser1OperationHandler() );
        db.registerOperationHandler( GetOffersInCategoryOfferedByUser2Operation.class,
                getGetOffersInCategoryOfferedByUser2OperationHandler() );
        db.registerOperationHandler( GetOffersInCategoryOfferedByUser3Operation.class,
                getGetOffersInCategoryOfferedByUser3OperationHandler() );
        db.registerOperationHandler( GetOriginalPinOperation.class, getGetOriginalPinOperationHandler() );
        db.registerOperationHandler( GetOwnersOfSitesShowingBoardsSubscribedToByUsers1Operation.class,
                getGetOwnersOfSitesShowingBoardsSubscribedToByUsers1OperationHandler() );
        db.registerOperationHandler( GetOwnersOfSitesShowingBoardsSubscribedToByUsers2Operation.class,
                getGetOwnersOfSitesShowingBoardsSubscribedToByUsers2OperationHandler() );
        db.registerOperationHandler( GetParentBoardsOfBoardsOperation.class,
                getGetParentBoardsOfBoardsOperationHandler() );
        db.registerOperationHandler( GetParentCategoriesOfSubCategoriesOperation.class,
                getGetParentCategoriesOfSubCategoriesOperationHandler() );
        db.registerOperationHandler( GetPinAssetsOperation.class, getGetPinAssetsOperationHandler() );
        db.registerOperationHandler( GetPinnedViaOperation.class, getGetPinnedViaOperationHandler() );
        db.registerOperationHandler( GetPinsAndTheirAssetsOnBoardsOperation.class,
                getGetPinsAndTheirAssetsOnBoardsOperationHandler() );
        db.registerOperationHandler( GetPinsAndTheirAuthorsAndAssetsAndBoardsOnSitesSubscribedToByUsersOperation.class,
                getGetPinsAndTheirAuthorsAndAssetsAndBoardsOnSitesSubscribedToByUsersOperationHandler() );
        db.registerOperationHandler( GetPinsAndTheirAuthorsAndAssetsOnBoardsOperation.class,
                getGetPinsAndTheirAuthorsAndAssetsOnBoardsOperationHandler() );
        db.registerOperationHandler( GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1Operation.class,
                getGetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1OperationHandler() );
        db.registerOperationHandler( GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites2Operation.class,
                getGetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites2OperationHandler() );
        db.registerOperationHandler( GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards1Operation.class,
                getGetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards1OperationHandler() );
        db.registerOperationHandler( GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards2Operation.class,
                getGetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards2OperationHandler() );
        db.registerOperationHandler( GetPinsInBoardsOperation.class, getGetPinsInBoardsOperationHandler() );
        db.registerOperationHandler( GetPinsLikedByOwnersOfSitesOperation.class,
                getGetPinsLikedByOwnersOfSitesOperationHandler() );
        db.registerOperationHandler( GetPinsOnAssetsOperation.class, getGetPinsOnAssetsOperationHandler() );
        db.registerOperationHandler( GetPinsRepinnedByUserOperation.class, getGetPinsRepinnedByUserOperationHandler() );
        db.registerOperationHandler( GetProductsInCategoriesUnderOffer1Operation.class,
                getGetProductsInCategoriesUnderOffer1OperationHandler() );
        db.registerOperationHandler( GetProductsInCategoriesUnderOffer2Operation.class,
                getGetProductsInCategoriesUnderOffer2OperationHandler() );
        db.registerOperationHandler( GetProductsInCategoriesUnderOfferInCountryOperation.class,
                getGetProductsInCategoriesUnderOfferInCountryOperationHandler() );
        db.registerOperationHandler( GetProductsUnderOfferInCountryOperation.class,
                getGetProductsUnderOfferInCountryOperationHandler() );
        db.registerOperationHandler( GetProductsUnderOfferOperation.class, getGetProductsUnderOfferOperationHandler() );
        db.registerOperationHandler( GetRootOperation.class, getGetRootOperationHandler() );
        db.registerOperationHandler( GetSiteModeratorsOperation.class, getGetSiteModeratorsOperationHandler() );
        db.registerOperationHandler( GetSiteOwnersOperation.class, getGetSiteOwnersOperationHandler() );
        db.registerOperationHandler( GetSitesOwnedByUsers1Operation.class, getGetSitesOwnedByUsers1OperationHandler() );
        db.registerOperationHandler( GetSitesOwnedByUsers2Operation.class, getGetSitesOwnedByUsers2OperationHandler() );
        db.registerOperationHandler( GetSitesShowingBoardsOperation.class, getGetSitesShowingBoardsOperationHandler() );
        db.registerOperationHandler( GetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperation.class,
                getGetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperationHandler() );
        db.registerOperationHandler( GetSpotlightPinsOperation.class, getGetSpotlightPinsOperationHandler() );
        db.registerOperationHandler( GetSubBoardsOfBoardWithNeoPins1Operation.class,
                getGetSubBoardsOfBoardWithNeoPins1OperationHandler() );
        db.registerOperationHandler( GetSubBoardsOfBoardWithNeoPins2Operation.class,
                getGetSubBoardsOfBoardWithNeoPins2OperationHandler() );
        db.registerOperationHandler( GetSubCategoriesOfParentCategories1Operation.class,
                getGetSubCategoriesOfParentCategories1OperationHandler() );
        db.registerOperationHandler( GetSubCategoriesOfParentCategories2Operation.class,
                getGetSubCategoriesOfParentCategories2OperationHandler() );
        db.registerOperationHandler( GetSubCategoriesOfParentCategories3Operation.class,
                getGetSubCategoriesOfParentCategories3OperationHandler() );
        db.registerOperationHandler( GetSubscriptionsOfUsersToEntitiesOperation.class,
                getGetSubscriptionsOfUsersToEntitiesOperationHandler() );
        db.registerOperationHandler( GetUrlsOfHostsOperation.class, getGetUrlsOfHostsOperationHandler() );
        db.registerOperationHandler( GetUrlsReferencedByPinsOperation.class,
                getGetUrlsReferencedByPinsOperationHandler() );
        db.registerOperationHandler( GetUsersLikesOnEntityOperation.class, getGetUsersLikesOnEntityOperationHandler() );
        db.registerOperationHandler( GetUsersSubscribedToBoardsShownOnSitesOwnedByUserOperation.class,
                getGetUsersSubscribedToBoardsShownOnSitesOwnedByUserOperationHandler() );
        db.registerOperationHandler( GetUsersSubscribedToSitesShowingBoardsOperation.class,
                getGetUsersSubscribedToSitesShowingBoardsOperationHandler() );
        db.registerOperationHandler( GetUsersThatLikedEntitiesOperation.class,
                getGetUsersThatLikedEntitiesOperationHandler() );
        db.registerOperationHandler( GetUsersThatRepinnedPinOperation.class,
                getGetUsersThatRepinnedPinOperationHandler() );
        db.registerOperationHandler( GetUserSubscriptionsOperation.class, getGetUserSubscriptionsOperationHandler() );
        db.registerOperationHandler( GetUsersWhoClaimedOperation.class, getGetUsersWhoClaimedOperationHandler() );
        db.registerOperationHandler( GetUsersWhoClaimsOperation.class, getGetUsersWhoClaimsOperationHandler() );
        db.registerOperationHandler( GetUsersWhoLikedEntityOperation.class, getGetUsersWhoLikedEntityOperationHandler() );
    }

    public abstract Class<? extends OperationHandler<CheckEntitiesLikedByUsersOperation>> getCheckEntitiesLikedByUsersOperationHandler();

    public abstract Class<? extends OperationHandler<CheckUsersSubscribedToSitesOperation>> getCheckUsersSubscribedToSitesOperationHandler();

    public abstract Class<? extends OperationHandler<CheckUserSubscribedToBoardOperation>> getCheckUserSubscribedToBoardOperationHandler();

    public abstract Class<? extends OperationHandler<CheckUserSubscribedToEntityOperation>> getCheckUserSubscribedToEntityOperationHandler();

    public abstract Class<? extends OperationHandler<CountAuthorsOfCommentsOnPinsThatHaveNotBeenTrolledUserOperation>> getCountAuthorsOfCommentsOnPinsThatHaveNotBeenTrolledUserOperationHandler();

    public abstract Class<? extends OperationHandler<CountCommentsOnPinsOperation>> getCountCommentsOnPinsOperationHandler();

    public abstract Class<? extends OperationHandler<CountOwnersOfSitesShowingBoardsSubscribedToByUserOperation>> getCountOwnersOfSitesShowingBoardsSubscribedToByUserOperationHandler();

    public abstract Class<? extends OperationHandler<CountOwnersOfSitesShowingBoardsSubscribedToByUsersOperation>> getCountOwnersOfSitesShowingBoardsSubscribedToByUsersOperationHandler();

    public abstract Class<? extends OperationHandler<CountPinsByAuthorsOnBoardsOnSitesOperation>> getCountPinsByAuthorsOnBoardsOnSitesOperationHandler();

    public abstract Class<? extends OperationHandler<CountPinsByAuthorsOnBoardsShownOnSitesOperation>> getCountPinsByAuthorsOnBoardsShownOnSitesOperationHandler();

    public abstract Class<? extends OperationHandler<CountPinsOnBoards1Operation>> getCountPinsOnBoards1OperationHandler();

    public abstract Class<? extends OperationHandler<CountPinsOnBoards2Operation>> getCountPinsOnBoards2OperationHandler();

    public abstract Class<? extends OperationHandler<CountPinsReferencingUrlsOperation>> getCountPinsReferencingUrlsOperationHandler();

    public abstract Class<? extends OperationHandler<CountProductsInCategoriesUnderOffer1Operation>> getCountProductsInCategoriesUnderOffer1OperationHandler();

    public abstract Class<? extends OperationHandler<CountProductsInCategoriesUnderOffer2Operation>> getCountProductsInCategoriesUnderOffer2OperationHandler();

    public abstract Class<? extends OperationHandler<CountProductsInCategoriesUnderOfferInCountryOperation>> getCountProductsInCategoriesUnderOfferInCountryOperationHandler();

    public abstract Class<? extends OperationHandler<CountProductsUnderOfferInCountryOperation>> getCountProductsUnderOfferInCountryOperationHandler();

    public abstract Class<? extends OperationHandler<CountProductsUnderOfferOperation>> getCountProductsUnderOfferOperationHandler();

    public abstract Class<? extends OperationHandler<CountRecentPinsOperation>> getCountRecentPinsOperationHandler();

    public abstract Class<? extends OperationHandler<CountRepinsOperation>> getCountRepinsOperationHandler();

    public abstract Class<? extends OperationHandler<CountThingsLikedByUsersOperation>> getCountThingsLikedByUsersOperationHandler();

    public abstract Class<? extends OperationHandler<CountUsersSubscribedToBoardsOnSitesOperation>> getCountUsersSubscribedToBoardsOnSitesOperationHandler();

    public abstract Class<? extends OperationHandler<CountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperation>> getCountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperationHandler();

    public abstract Class<? extends OperationHandler<CountUsersSubscribedToSitesShowingBoardsOperation>> getCountUsersSubscribedToSitesShowingBoardsOperationHandler();

    public abstract Class<? extends OperationHandler<CountUsersWhoLikedThingsOperation>> getCountUsersWhoLikedThingsOperationHandler();

    public abstract Class<? extends OperationHandler<GetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperation>> getGetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperationHandler();

    public abstract Class<? extends OperationHandler<GetAuthorsOfCommentsOnPinsOperation>> getGetAuthorsOfCommentsOnPinsOperationHandler();

    public abstract Class<? extends OperationHandler<GetAuthorsOfCommentsOperation>> getGetAuthorsOfCommentsOperationHandler();

    public abstract Class<? extends OperationHandler<GetAuthorsOfPinsOperation>> getGetAuthorsOfPinsOperationHandler();

    public abstract Class<? extends OperationHandler<GetBoardsOnSitesOwnedByUsersOperation>> getGetBoardsOnSitesOwnedByUsersOperationHandler();

    public abstract Class<? extends OperationHandler<GetBoardsPinnedByPinsOperation>> getGetBoardsPinnedByPinsOperationHandler();

    public abstract Class<? extends OperationHandler<GetBoardsShownOnSitesOperation>> getGetBoardsShownOnSitesOperationHandler();

    public abstract Class<? extends OperationHandler<GetBoardsShownOnSitesOwnedByUsersOperation>> getGetBoardsShownOnSitesOwnedByUsersOperationHandler();

    public abstract Class<? extends OperationHandler<GetCategoriesOfProducts1Operation>> getGetCategoriesOfProducts1OperationHandler();

    public abstract Class<? extends OperationHandler<GetCategoriesOfProducts2Operation>> getGetCategoriesOfProducts2OperationHandler();

    public abstract Class<? extends OperationHandler<GetCategoriesOfProductsOfferedByUserOperation>> getGetCategoriesOfProductsOfferedByUserOperationHandler();

    public abstract Class<? extends OperationHandler<GetCategoriesOfProductsUnderOfferInCountryOperation>> getGetCategoriesOfProductsUnderOfferInCountryOperationHandler();

    public abstract Class<? extends OperationHandler<GetCategoriesOfProductsUnderOfferOperation>> getGetCategoriesOfProductsUnderOfferOperationHandler();

    public abstract Class<? extends OperationHandler<GetClaimedByUsersOperation>> getGetClaimedByUsersOperationHandler();

    public abstract Class<? extends OperationHandler<GetClaimsByUsersOperation>> getGetClaimsByUsersOperationHandler();

    public abstract Class<? extends OperationHandler<GetCommentedOnPinsOperation>> getGetCommentedOnPinsOperationHandler();

    public abstract Class<? extends OperationHandler<GetCommentsAndAuthorsOnPins1Operation>> getGetCommentsAndAuthorsOnPins1OperationHandler();

    public abstract Class<? extends OperationHandler<GetCommentsAndAuthorsOnPins2Operation>> getGetCommentsAndAuthorsOnPins2OperationHandler();

    public abstract Class<? extends OperationHandler<GetCommentsOnPin1Operation>> getGetCommentsOnPin1OperationHandler();

    public abstract Class<? extends OperationHandler<GetCommentsOnPin2Operation>> getGetCommentsOnPin2OperationHandler();

    public abstract Class<? extends OperationHandler<GetOffersByUserOperation>> getGetOffersByUserOperationHandler();

    public abstract Class<? extends OperationHandler<GetOffersInCategoryOfferedByUser1Operation>> getGetOffersInCategoryOfferedByUser1OperationHandler();

    public abstract Class<? extends OperationHandler<GetOffersInCategoryOfferedByUser2Operation>> getGetOffersInCategoryOfferedByUser2OperationHandler();

    public abstract Class<? extends OperationHandler<GetOffersInCategoryOfferedByUser3Operation>> getGetOffersInCategoryOfferedByUser3OperationHandler();

    public abstract Class<? extends OperationHandler<GetOriginalPinOperation>> getGetOriginalPinOperationHandler();

    public abstract Class<? extends OperationHandler<GetOwnersOfSitesShowingBoardsSubscribedToByUsers1Operation>> getGetOwnersOfSitesShowingBoardsSubscribedToByUsers1OperationHandler();

    public abstract Class<? extends OperationHandler<GetOwnersOfSitesShowingBoardsSubscribedToByUsers2Operation>> getGetOwnersOfSitesShowingBoardsSubscribedToByUsers2OperationHandler();

    public abstract Class<? extends OperationHandler<GetParentBoardsOfBoardsOperation>> getGetParentBoardsOfBoardsOperationHandler();

    public abstract Class<? extends OperationHandler<GetParentCategoriesOfSubCategoriesOperation>> getGetParentCategoriesOfSubCategoriesOperationHandler();

    public abstract Class<? extends OperationHandler<GetPinAssetsOperation>> getGetPinAssetsOperationHandler();

    public abstract Class<? extends OperationHandler<GetPinnedViaOperation>> getGetPinnedViaOperationHandler();

    public abstract Class<? extends OperationHandler<GetPinsAndTheirAssetsOnBoardsOperation>> getGetPinsAndTheirAssetsOnBoardsOperationHandler();

    public abstract Class<? extends OperationHandler<GetPinsAndTheirAuthorsAndAssetsAndBoardsOnSitesSubscribedToByUsersOperation>> getGetPinsAndTheirAuthorsAndAssetsAndBoardsOnSitesSubscribedToByUsersOperationHandler();

    public abstract Class<? extends OperationHandler<GetPinsAndTheirAuthorsAndAssetsOnBoardsOperation>> getGetPinsAndTheirAuthorsAndAssetsOnBoardsOperationHandler();

    public abstract Class<? extends OperationHandler<GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1Operation>> getGetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1OperationHandler();

    public abstract Class<? extends OperationHandler<GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites2Operation>> getGetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites2OperationHandler();

    public abstract Class<? extends OperationHandler<GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards1Operation>> getGetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards1OperationHandler();

    public abstract Class<? extends OperationHandler<GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards2Operation>> getGetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards2OperationHandler();

    public abstract Class<? extends OperationHandler<GetPinsInBoardsOperation>> getGetPinsInBoardsOperationHandler();

    public abstract Class<? extends OperationHandler<GetPinsLikedByOwnersOfSitesOperation>> getGetPinsLikedByOwnersOfSitesOperationHandler();

    public abstract Class<? extends OperationHandler<GetPinsOnAssetsOperation>> getGetPinsOnAssetsOperationHandler();

    public abstract Class<? extends OperationHandler<GetPinsRepinnedByUserOperation>> getGetPinsRepinnedByUserOperationHandler();

    public abstract Class<? extends OperationHandler<GetProductsInCategoriesUnderOffer1Operation>> getGetProductsInCategoriesUnderOffer1OperationHandler();

    public abstract Class<? extends OperationHandler<GetProductsInCategoriesUnderOffer2Operation>> getGetProductsInCategoriesUnderOffer2OperationHandler();

    public abstract Class<? extends OperationHandler<GetProductsInCategoriesUnderOfferInCountryOperation>> getGetProductsInCategoriesUnderOfferInCountryOperationHandler();

    public abstract Class<? extends OperationHandler<GetProductsUnderOfferInCountryOperation>> getGetProductsUnderOfferInCountryOperationHandler();

    public abstract Class<? extends OperationHandler<GetProductsUnderOfferOperation>> getGetProductsUnderOfferOperationHandler();

    public abstract Class<? extends OperationHandler<GetRootOperation>> getGetRootOperationHandler();

    public abstract Class<? extends OperationHandler<GetSiteModeratorsOperation>> getGetSiteModeratorsOperationHandler();

    public abstract Class<? extends OperationHandler<GetSiteOwnersOperation>> getGetSiteOwnersOperationHandler();

    public abstract Class<? extends OperationHandler<GetSitesOwnedByUsers1Operation>> getGetSitesOwnedByUsers1OperationHandler();

    public abstract Class<? extends OperationHandler<GetSitesOwnedByUsers2Operation>> getGetSitesOwnedByUsers2OperationHandler();

    public abstract Class<? extends OperationHandler<GetSitesShowingBoardsOperation>> getGetSitesShowingBoardsOperationHandler();

    public abstract Class<? extends OperationHandler<GetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperation>> getGetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperationHandler();

    public abstract Class<? extends OperationHandler<GetSpotlightPinsOperation>> getGetSpotlightPinsOperationHandler();

    public abstract Class<? extends OperationHandler<GetSubBoardsOfBoardWithNeoPins1Operation>> getGetSubBoardsOfBoardWithNeoPins1OperationHandler();

    public abstract Class<? extends OperationHandler<GetSubBoardsOfBoardWithNeoPins2Operation>> getGetSubBoardsOfBoardWithNeoPins2OperationHandler();

    public abstract Class<? extends OperationHandler<GetSubCategoriesOfParentCategories1Operation>> getGetSubCategoriesOfParentCategories1OperationHandler();

    public abstract Class<? extends OperationHandler<GetSubCategoriesOfParentCategories2Operation>> getGetSubCategoriesOfParentCategories2OperationHandler();

    public abstract Class<? extends OperationHandler<GetSubCategoriesOfParentCategories3Operation>> getGetSubCategoriesOfParentCategories3OperationHandler();

    public abstract Class<? extends OperationHandler<GetSubscriptionsOfUsersToEntitiesOperation>> getGetSubscriptionsOfUsersToEntitiesOperationHandler();

    public abstract Class<? extends OperationHandler<GetUrlsOfHostsOperation>> getGetUrlsOfHostsOperationHandler();

    public abstract Class<? extends OperationHandler<GetUrlsReferencedByPinsOperation>> getGetUrlsReferencedByPinsOperationHandler();

    public abstract Class<? extends OperationHandler<GetUsersLikesOnEntityOperation>> getGetUsersLikesOnEntityOperationHandler();

    public abstract Class<? extends OperationHandler<GetUsersSubscribedToBoardsShownOnSitesOwnedByUserOperation>> getGetUsersSubscribedToBoardsShownOnSitesOwnedByUserOperationHandler();

    public abstract Class<? extends OperationHandler<GetUsersSubscribedToSitesShowingBoardsOperation>> getGetUsersSubscribedToSitesShowingBoardsOperationHandler();

    public abstract Class<? extends OperationHandler<GetUsersThatLikedEntitiesOperation>> getGetUsersThatLikedEntitiesOperationHandler();

    public abstract Class<? extends OperationHandler<GetUsersThatRepinnedPinOperation>> getGetUsersThatRepinnedPinOperationHandler();

    public abstract Class<? extends OperationHandler<GetUserSubscriptionsOperation>> getGetUserSubscriptionsOperationHandler();

    public abstract Class<? extends OperationHandler<GetUsersWhoClaimedOperation>> getGetUsersWhoClaimedOperationHandler();

    public abstract Class<? extends OperationHandler<GetUsersWhoClaimsOperation>> getGetUsersWhoClaimsOperationHandler();

    public abstract Class<? extends OperationHandler<GetUsersWhoLikedEntityOperation>> getGetUsersWhoLikedEntityOperationHandler();
}
