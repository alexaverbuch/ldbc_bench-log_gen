package com.ldbc.driver.dshini.workloads;

import java.util.ArrayList;
import java.util.List;

import com.ldbc.driver.Operation;
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
import com.ldbc.driver.util.Pair;

public abstract class DshiniCypherCommands implements AllOperationHandlersProvider
{
    @Override
    public final List<Pair<Class<? extends Operation<?>>, Class<? extends OperationHandler<?>>>> allOperationHandlers()
    {
        List<Pair<Class<? extends Operation<?>>, Class<? extends OperationHandler<?>>>> handlers = new ArrayList<Pair<Class<? extends Operation<?>>, Class<? extends OperationHandler<?>>>>();
        handlers.add( new Pair( CheckEntitiesLikedByUsersOperation.class,
                getCheckEntitiesLikedByUsersOperationHandler() ) );
        handlers.add( new Pair( CheckUsersSubscribedToSitesOperation.class,
                getCheckUsersSubscribedToSitesOperationHandler() ) );
        handlers.add( new Pair( CheckUserSubscribedToBoardOperation.class,
                getCheckUserSubscribedToBoardOperationHandler() ) );
        handlers.add( new Pair( CheckUserSubscribedToEntityOperation.class,
                getCheckUserSubscribedToEntityOperationHandler() ) );
        handlers.add( new Pair( CountAuthorsOfCommentsOnPinsThatHaveNotBeenTrolledUserOperation.class,
                getCountAuthorsOfCommentsOnPinsThatHaveNotBeenTrolledUserOperationHandler() ) );
        handlers.add( new Pair( CountCommentsOnPinsOperation.class, getCountCommentsOnPinsOperationHandler() ) );
        handlers.add( new Pair( CountOwnersOfSitesShowingBoardsSubscribedToByUserOperation.class,
                getCountOwnersOfSitesShowingBoardsSubscribedToByUserOperationHandler() ) );
        handlers.add( new Pair( CountOwnersOfSitesShowingBoardsSubscribedToByUsersOperation.class,
                getCountOwnersOfSitesShowingBoardsSubscribedToByUsersOperationHandler() ) );
        handlers.add( new Pair( CountPinsByAuthorsOnBoardsOnSitesOperation.class,
                getCountPinsByAuthorsOnBoardsOnSitesOperationHandler() ) );
        handlers.add( new Pair( CountPinsByAuthorsOnBoardsShownOnSitesOperation.class,
                getCountPinsByAuthorsOnBoardsShownOnSitesOperationHandler() ) );
        handlers.add( new Pair( CountPinsOnBoards1Operation.class, getCountPinsOnBoards1OperationHandler() ) );
        handlers.add( new Pair( CountPinsOnBoards2Operation.class, getCountPinsOnBoards2OperationHandler() ) );
        handlers.add( new Pair( CountPinsReferencingUrlsOperation.class, getCountPinsReferencingUrlsOperationHandler() ) );
        handlers.add( new Pair( CountProductsInCategoriesUnderOffer1Operation.class,
                getCountProductsInCategoriesUnderOffer1OperationHandler() ) );
        handlers.add( new Pair( CountProductsInCategoriesUnderOffer2Operation.class,
                getCountProductsInCategoriesUnderOffer2OperationHandler() ) );
        handlers.add( new Pair( CountProductsInCategoriesUnderOfferInCountryOperation.class,
                getCountProductsInCategoriesUnderOfferInCountryOperationHandler() ) );
        handlers.add( new Pair( CountProductsUnderOfferInCountryOperation.class,
                getCountProductsUnderOfferInCountryOperationHandler() ) );
        handlers.add( new Pair( CountProductsUnderOfferOperation.class, getCountProductsUnderOfferOperationHandler() ) );
        handlers.add( new Pair( CountRecentPinsOperation.class, getCountRecentPinsOperationHandler() ) );
        handlers.add( new Pair( CountRepinsOperation.class, getCountRepinsOperationHandler() ) );
        handlers.add( new Pair( CountThingsLikedByUsersOperation.class, getCountThingsLikedByUsersOperationHandler() ) );
        handlers.add( new Pair( CountUsersSubscribedToBoardsOnSitesOperation.class,
                getCountUsersSubscribedToBoardsOnSitesOperationHandler() ) );
        handlers.add( new Pair( CountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperation.class,
                getCountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperationHandler() ) );
        handlers.add( new Pair( CountUsersSubscribedToSitesShowingBoardsOperation.class,
                getCountUsersSubscribedToSitesShowingBoardsOperationHandler() ) );
        handlers.add( new Pair( CountUsersWhoLikedThingsOperation.class, getCountUsersWhoLikedThingsOperationHandler() ) );
        handlers.add( new Pair( GetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperation.class,
                getGetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperationHandler() ) );
        handlers.add( new Pair( GetAuthorsOfCommentsOnPinsOperation.class,
                getGetAuthorsOfCommentsOnPinsOperationHandler() ) );
        handlers.add( new Pair( GetAuthorsOfCommentsOperation.class, getGetAuthorsOfCommentsOperationHandler() ) );
        handlers.add( new Pair( GetAuthorsOfPinsOperation.class, getGetAuthorsOfPinsOperationHandler() ) );
        handlers.add( new Pair( GetBoardsOnSitesOwnedByUsersOperation.class,
                getGetBoardsOnSitesOwnedByUsersOperationHandler() ) );
        handlers.add( new Pair( GetBoardsPinnedByPinsOperation.class, getGetBoardsPinnedByPinsOperationHandler() ) );
        handlers.add( new Pair( GetBoardsShownOnSitesOperation.class, getGetBoardsShownOnSitesOperationHandler() ) );
        handlers.add( new Pair( GetBoardsShownOnSitesOwnedByUsersOperation.class,
                getGetBoardsShownOnSitesOwnedByUsersOperationHandler() ) );
        handlers.add( new Pair( GetCategoriesOfProducts1Operation.class, getGetCategoriesOfProducts1OperationHandler() ) );
        handlers.add( new Pair( GetCategoriesOfProducts2Operation.class, getGetCategoriesOfProducts2OperationHandler() ) );
        handlers.add( new Pair( GetCategoriesOfProductsOfferedByUserOperation.class,
                getGetCategoriesOfProductsOfferedByUserOperationHandler() ) );
        handlers.add( new Pair( GetCategoriesOfProductsUnderOfferInCountryOperation.class,
                getGetCategoriesOfProductsUnderOfferInCountryOperationHandler() ) );
        handlers.add( new Pair( GetCategoriesOfProductsUnderOfferOperation.class,
                getGetCategoriesOfProductsUnderOfferOperationHandler() ) );
        handlers.add( new Pair( GetClaimedByUsersOperation.class, getGetClaimedByUsersOperationHandler() ) );
        handlers.add( new Pair( GetClaimsByUsersOperation.class, getGetClaimsByUsersOperationHandler() ) );
        handlers.add( new Pair( GetCommentedOnPinsOperation.class, getGetCommentedOnPinsOperationHandler() ) );
        handlers.add( new Pair( GetCommentsAndAuthorsOnPins1Operation.class,
                getGetCommentsAndAuthorsOnPins1OperationHandler() ) );
        handlers.add( new Pair( GetCommentsAndAuthorsOnPins2Operation.class,
                getGetCommentsAndAuthorsOnPins2OperationHandler() ) );
        handlers.add( new Pair( GetCommentsOnPin1Operation.class, getGetCommentsOnPin1OperationHandler() ) );
        handlers.add( new Pair( GetCommentsOnPin2Operation.class, getGetCommentsOnPin2OperationHandler() ) );
        handlers.add( new Pair( GetOffersByUserOperation.class, getGetOffersByUserOperationHandler() ) );
        handlers.add( new Pair( GetOffersInCategoryOfferedByUser1Operation.class,
                getGetOffersInCategoryOfferedByUser1OperationHandler() ) );
        handlers.add( new Pair( GetOffersInCategoryOfferedByUser2Operation.class,
                getGetOffersInCategoryOfferedByUser2OperationHandler() ) );
        handlers.add( new Pair( GetOffersInCategoryOfferedByUser3Operation.class,
                getGetOffersInCategoryOfferedByUser3OperationHandler() ) );
        handlers.add( new Pair( GetOriginalPinOperation.class, getGetOriginalPinOperationHandler() ) );
        handlers.add( new Pair( GetOwnersOfSitesShowingBoardsSubscribedToByUsers1Operation.class,
                getGetOwnersOfSitesShowingBoardsSubscribedToByUsers1OperationHandler() ) );
        handlers.add( new Pair( GetOwnersOfSitesShowingBoardsSubscribedToByUsers2Operation.class,
                getGetOwnersOfSitesShowingBoardsSubscribedToByUsers2OperationHandler() ) );
        handlers.add( new Pair( GetParentBoardsOfBoardsOperation.class, getGetParentBoardsOfBoardsOperationHandler() ) );
        handlers.add( new Pair( GetParentCategoriesOfSubCategoriesOperation.class,
                getGetParentCategoriesOfSubCategoriesOperationHandler() ) );
        handlers.add( new Pair( GetPinAssetsOperation.class, getGetPinAssetsOperationHandler() ) );
        handlers.add( new Pair( GetPinnedViaOperation.class, getGetPinnedViaOperationHandler() ) );
        handlers.add( new Pair( GetPinsAndTheirAssetsOnBoardsOperation.class,
                getGetPinsAndTheirAssetsOnBoardsOperationHandler() ) );
        handlers.add( new Pair( GetPinsAndTheirAuthorsAndAssetsAndBoardsOnSitesSubscribedToByUsersOperation.class,
                getGetPinsAndTheirAuthorsAndAssetsAndBoardsOnSitesSubscribedToByUsersOperationHandler() ) );
        handlers.add( new Pair( GetPinsAndTheirAuthorsAndAssetsOnBoardsOperation.class,
                getGetPinsAndTheirAuthorsAndAssetsOnBoardsOperationHandler() ) );
        handlers.add( new Pair( GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1Operation.class,
                getGetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1OperationHandler() ) );
        handlers.add( new Pair( GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites2Operation.class,
                getGetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites2OperationHandler() ) );
        handlers.add( new Pair( GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards1Operation.class,
                getGetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards1OperationHandler() ) );
        handlers.add( new Pair( GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards2Operation.class,
                getGetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards2OperationHandler() ) );
        handlers.add( new Pair( GetPinsInBoardsOperation.class, getGetPinsInBoardsOperationHandler() ) );
        handlers.add( new Pair( GetPinsLikedByOwnersOfSitesOperation.class,
                getGetPinsLikedByOwnersOfSitesOperationHandler() ) );
        handlers.add( new Pair( GetPinsOnAssetsOperation.class, getGetPinsOnAssetsOperationHandler() ) );
        handlers.add( new Pair( GetPinsRepinnedByUserOperation.class, getGetPinsRepinnedByUserOperationHandler() ) );
        handlers.add( new Pair( GetProductsInCategoriesUnderOffer1Operation.class,
                getGetProductsInCategoriesUnderOffer1OperationHandler() ) );
        handlers.add( new Pair( GetProductsInCategoriesUnderOffer2Operation.class,
                getGetProductsInCategoriesUnderOffer2OperationHandler() ) );
        handlers.add( new Pair( GetProductsInCategoriesUnderOfferInCountryOperation.class,
                getGetProductsInCategoriesUnderOfferInCountryOperationHandler() ) );
        handlers.add( new Pair( GetProductsUnderOfferInCountryOperation.class,
                getGetProductsUnderOfferInCountryOperationHandler() ) );
        handlers.add( new Pair( GetProductsUnderOfferOperation.class, getGetProductsUnderOfferOperationHandler() ) );
        handlers.add( new Pair( GetRootOperation.class, getGetRootOperationHandler() ) );
        handlers.add( new Pair( GetSiteModeratorsOperation.class, getGetSiteModeratorsOperationHandler() ) );
        handlers.add( new Pair( GetSiteOwnersOperation.class, getGetSiteOwnersOperationHandler() ) );
        handlers.add( new Pair( GetSitesOwnedByUsers1Operation.class, getGetSitesOwnedByUsers1OperationHandler() ) );
        handlers.add( new Pair( GetSitesOwnedByUsers2Operation.class, getGetSitesOwnedByUsers2OperationHandler() ) );
        handlers.add( new Pair( GetSitesShowingBoardsOperation.class, getGetSitesShowingBoardsOperationHandler() ) );
        handlers.add( new Pair( GetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperation.class,
                getGetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperationHandler() ) );
        handlers.add( new Pair( GetSpotlightPinsOperation.class, getGetSpotlightPinsOperationHandler() ) );
        handlers.add( new Pair( GetSubBoardsOfBoardWithNeoPins1Operation.class,
                getGetSubBoardsOfBoardWithNeoPins1OperationHandler() ) );
        handlers.add( new Pair( GetSubBoardsOfBoardWithNeoPins2Operation.class,
                getGetSubBoardsOfBoardWithNeoPins2OperationHandler() ) );
        handlers.add( new Pair( GetSubCategoriesOfParentCategories1Operation.class,
                getGetSubCategoriesOfParentCategories1OperationHandler() ) );
        handlers.add( new Pair( GetSubCategoriesOfParentCategories2Operation.class,
                getGetSubCategoriesOfParentCategories2OperationHandler() ) );
        handlers.add( new Pair( GetSubCategoriesOfParentCategories3Operation.class,
                getGetSubCategoriesOfParentCategories3OperationHandler() ) );
        handlers.add( new Pair( GetSubscriptionsOfUsersToEntitiesOperation.class,
                getGetSubscriptionsOfUsersToEntitiesOperationHandler() ) );
        handlers.add( new Pair( GetUrlsOfHostsOperation.class, getGetUrlsOfHostsOperationHandler() ) );
        handlers.add( new Pair( GetUrlsReferencedByPinsOperation.class, getGetUrlsReferencedByPinsOperationHandler() ) );
        handlers.add( new Pair( GetUsersLikesOnEntityOperation.class, getGetUsersLikesOnEntityOperationHandler() ) );
        handlers.add( new Pair( GetUsersSubscribedToBoardsShownOnSitesOwnedByUserOperation.class,
                getGetUsersSubscribedToBoardsShownOnSitesOwnedByUserOperationHandler() ) );
        handlers.add( new Pair( GetUsersSubscribedToSitesShowingBoardsOperation.class,
                getGetUsersSubscribedToSitesShowingBoardsOperationHandler() ) );
        handlers.add( new Pair( GetUsersThatLikedEntitiesOperation.class,
                getGetUsersThatLikedEntitiesOperationHandler() ) );
        handlers.add( new Pair( GetUsersThatRepinnedPinOperation.class, getGetUsersThatRepinnedPinOperationHandler() ) );
        handlers.add( new Pair( GetUserSubscriptionsOperation.class, getGetUserSubscriptionsOperationHandler() ) );
        handlers.add( new Pair( GetUsersWhoClaimedOperation.class, getGetUsersWhoClaimedOperationHandler() ) );
        handlers.add( new Pair( GetUsersWhoClaimsOperation.class, getGetUsersWhoClaimsOperationHandler() ) );
        handlers.add( new Pair( GetUsersWhoLikedEntityOperation.class, getGetUsersWhoLikedEntityOperationHandler() ) );
        return handlers;
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
