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
    /*
    Histogram
    defaultBucketValue=0
    bucketCount=168
    sumOfAllBucketValues=13049993
    DiscreteBucket [thing=CheckEntitiesLikedByUsersOperation] = 986645
    DiscreteBucket [thing=GetAuthorsOfPinsOperation] = 856334
    DiscreteBucket [thing=GetSiteOwnersOperation] = 639811
    DiscreteBucket [thing=GetCommentsAndAuthorsOnPins2Operation] = 575661
    DiscreteBucket [thing=GetPinAssetsOperation] = 552031
    DiscreteBucket [thing=IndexQueryNodeOnUserProfileIndexOperation] = 547934
    DiscreteBucket [thing=CountPinsOnBoards1Operation] = 530796
    DiscreteBucket [thing=GetUrlsReferencedByPinsOperation] = 516540
    DiscreteBucket [thing=IndexQueryNodeOnNeoPinIndexOperation] = 501957
    DiscreteBucket [thing=CheckUsersSubscribedToSiteOperation] = 498684
    DiscreteBucket [thing=AddNodeToNeoPinBoardIndexOperation] = 467215
    DiscreteBucket [thing=GetSitesShowingBoardsOperation] = 456663
    DiscreteBucket [thing=GetBoardsPinnedByPinsOperation] = 442957
    DiscreteBucket [thing=GetPinsRepinnedByUserOperation] = 390024
    DiscreteBucket [thing=CountUsersSubscribedToSitesShowingBoardsOperation] = 353675
    DiscreteBucket [thing=CheckUserSubscribedToBoardOperation] = 327736
    DiscreteBucket [thing=CountUsersWhoLikedThingsOperation] = 298273
    DiscreteBucket [thing=GetSitesOwnedByUsers2Operation] = 297338
    DiscreteBucket [thing=CountRepinsOperation] = 290747
    DiscreteBucket [thing=GetUrlsOfHostsOperation] = 252037
    DiscreteBucket [thing=GetPinsAndTheirAssetsOnBoardsOperation] = 229362
    DiscreteBucket [thing=GetNodeOperation] = 198873
    DiscreteBucket [thing=GetOriginalPinOperation] = 183527
    DiscreteBucket [thing=GetCategoriesOfProducts1Operation] = 162068
    DiscreteBucket [thing=GetSiteModeratorsOperation] = 154668
    DiscreteBucket [thing=GetUsersThatRepinnedPinOperation] = 114905
    DiscreteBucket [thing=BatchOperation] = 107418
    DiscreteBucket [thing=GetBoardsOnSitesOwnedByUsersOperation] = 101426
    DiscreteBucket [thing=GetPinsAndTheirAuthorsAndAssetsAndBoardsOnSitesSubscribedToByUsersOperation] = 97172
    DiscreteBucket [thing=CountPinsByAuthorsOnBoardsShownOnSitesOperation] = 95157
    DiscreteBucket [thing=GetCategoriesOfProducts2Operation] = 89973
    DiscreteBucket [thing=UpdateNodeNeoPinBoardOperation] = 87978
    DiscreteBucket [thing=DeleteNodeFromNeoPinBoardIndexOperation] = 87978
    DiscreteBucket [thing=GetUsersThatLikedEntitiesOperation] = 85298
    DiscreteBucket [thing=CountOwnersOfSitesShowingBoardsSubscribedToByUserOperation] = 82367
    DiscreteBucket [thing=CountUsersSubscribedToBoardsOnSitesOperation] = 82315
    DiscreteBucket [thing=GetPinsAndTheirAuthorsAndAssetsOnBoardsOperation] = 79339
    DiscreteBucket [thing=GetNodeOutRelationshipsOperation] = 71586
    DiscreteBucket [thing=GetOwnersOfSitesShowingBoardsSubscribedToByUsers1Operation] = 71052
    DiscreteBucket [thing=GetBoardsShownOnSitesOperation] = 69887
    DiscreteBucket [thing=GetUsersWhoClaimedOperation] = 67701
    DiscreteBucket [thing=IndexQueryNodeOnNeoProductIndexOperation] = 66629
    DiscreteBucket [thing=CountThingsLikedByUsersOperation] = 52174
    DiscreteBucket [thing=GetNodesOutgoingIsSpotlightRelationshipsOperation] = 51011
    DiscreteBucket [thing=GetUsersWhoLikedEntityOperation] = 50999
    DiscreteBucket [thing=CountAuthorsOfCommentsOnPinThatHaveNotBeenTrolledByUserOperation] = 33654
    DiscreteBucket [thing=GetCommentsAndAuthorsOnPins1Operation] = 33654
    DiscreteBucket [thing=CountRecentPinsOperation] = 32198
    DiscreteBucket [thing=CountPinsOnBoards2Operation] = 32198
    DiscreteBucket [thing=IndexQueryNodeOnNeoPinCommentIndexOperation] = 31206
    DiscreteBucket [thing=CreateRelationshipPinsOperation] = 30338
    DiscreteBucket [thing=IndexQueryNodeOnNeoPinUrlIndexOperation] = 29945
    DiscreteBucket [thing=CreateRelationshipAuthoredByOperation] = 29311
    DiscreteBucket [thing=CreateRelationshipPinnedViaOperation] = 29311
    DiscreteBucket [thing=CreateRelationshipPinsAssetOperation] = 29311
    DiscreteBucket [thing=AddNodeToNeoPinUrlIndexOperation] = 28824
    DiscreteBucket [thing=GetAuthorsOfCommentsOperation] = 27215
    DiscreteBucket [thing=GetUsersSubscribedToBoardsOnSitesOwnedByGivenUserOperation] = 23845
    DiscreteBucket [thing=CountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperation] = 23845
    DiscreteBucket [thing=GetCommentedOnPinsOperation] = 21333
    DiscreteBucket [thing=GetAuthorsOfCommentsOnPinsOperation] = 21260
    DiscreteBucket [thing=CreateRelationshipPinReferencesUrlOperation] = 20927
    DiscreteBucket [thing=CountCommentsOnPinsOperation] = 19126
    DiscreteBucket [thing=GetBoardsShownOnSitesOwnedByUsersOperation] = 16722
    DiscreteBucket [thing=IndexQueryNodeOnNeoPinBoardIndexOperation] = 15130
    DiscreteBucket [thing=GetSubscriptionsOfUsersToEntitiesOperation] = 12732
    DiscreteBucket [thing=CountOwnersOfSitesShowingBoardsSubscribedToByUsersOperation] = 12190
    DiscreteBucket [thing=GetOwnersOfSitesShowingBoardsSubscribedToByUsers2Operation] = 12190
    DiscreteBucket [thing=GetCategoriesOfProductsOfferedByUserOperation] = 11517
    DiscreteBucket [thing=GetParentCategoriesOfSubCategoriesOperation] = 10594
    DiscreteBucket [thing=CreateRelationshipCommentsOnOperation] = 9899
    DiscreteBucket [thing=CreateRelationshipCommentedByOperation] = 9899
    DiscreteBucket [thing=AddNodeToNeoPinIndexOperation] = 8003
    DiscreteBucket [thing=GetOffersInCategoryOfferedByUser1Operation] = 7292
    DiscreteBucket [thing=CreateNodeNeoPinUrlOperation] = 7206
    DiscreteBucket [thing=CreateRelationshipUrlHostOfOperation] = 7206
    DiscreteBucket [thing=GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards1Operation] = 6557
    DiscreteBucket [thing=CountPinsReferencingUrlsOperation] = 6553
    DiscreteBucket [thing=IndexQueryNodeOnNeoSiteIndexOperation] = 4947
    DiscreteBucket [thing=CopyOfIndexQueryNodeOnNeoPinApplicationIndexOperation] = 4773
    DiscreteBucket [thing=GetProductsInCategoriesUnderOffer2Operation] = 3900
    DiscreteBucket [thing=GetCommentsOnPin1Operation] = 3733
    DiscreteBucket [thing=DeleteRelationshipOperation] = 3415
    DiscreteBucket [thing=IndexQueryNodeOnNeoPinUrlHostIndexOperation] = 3094
    DiscreteBucket [thing=GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1Operation] = 3049
    DiscreteBucket [thing=AddNodeToNeoSiteIndexOperation] = 3019
    DiscreteBucket [thing=CheckUserSubscribedToEntityOperation] = 2648
    DiscreteBucket [thing=CreateRelationshipSubscribesOperation] = 2586
    DiscreteBucket [thing=GetNodeRelationshipsOperation] = 2195
    DiscreteBucket [thing=IndexQueryNodeOnNeoCategoryIndexOperation] = 2036
    DiscreteBucket [thing=GetPinsOnAssetsOperation] = 1966
    DiscreteBucket [thing=AddNodeToNeoPinUrlHostIndexOperation] = 1932
    DiscreteBucket [thing=GetSubCategoriesOfParentCategories1Operation] = 1917
    DiscreteBucket [thing=CountProductsInCategoriesUnderOffer2Operation] = 1908
    DiscreteBucket [thing=GetPinnedViaOperation] = 1483
    DiscreteBucket [thing=IndexQueryNodeOnNeoPinImageIndexOperation] = 1479
    DiscreteBucket [thing=CountProductsUnderOfferOperation] = 1432
    DiscreteBucket [thing=GetClaimedByUsersOperation] = 1368
    DiscreteBucket [thing=GetOffersByUserOperation] = 1359
    DiscreteBucket [thing=GetClaimsByUsersOperation] = 1358
    DiscreteBucket [thing=GetProductsUnderOfferOperation] = 1322
    DiscreteBucket [thing=DeleteNodeFromNeoPinIndexOperation] = 1198
    DiscreteBucket [thing=UpdateNodeNeoPinOperation] = 1198
    DiscreteBucket [thing=GetOffersInCategoryOfferedByUser2Operation] = 1114
    DiscreteBucket [thing=GetSitesOwnedByUsers1Operation] = 1103
    DiscreteBucket [thing=GetParentBoardsOfBoardsOperation] = 1080
    DiscreteBucket [thing=GetRelationshipOperation] = 1063
    DiscreteBucket [thing=GetUsersSubscribedToSitesShowingBoardsOperation] = 906
    DiscreteBucket [thing=AddNodeToNeoProductIndexOperation] = 858
    DiscreteBucket [thing=GetUserSubscriptionsOperation] = 830
    DiscreteBucket [thing=IndexQueryNodeOnNeoRootIndexOperation] = 721
    DiscreteBucket [thing=IndexQueryNodeOnOfferIndexOperation] = 548
    DiscreteBucket [thing=GetRootOperation] = 487
    DiscreteBucket [thing=CreateNodeNeoPinUrlHostOperation] = 483
    DiscreteBucket [thing=GetUsersLikesOnEntityOperation] = 465
    DiscreteBucket [thing=GetCategoriesOfProductsUnderOfferOperation] = 437
    DiscreteBucket [thing=CreateRelationshipSubBoardOfOperation] = 370
    DiscreteBucket [thing=GetSubCategoriesOfParentCategories3Operation] = 358
    DiscreteBucket [thing=CreateRelationshipBoardShownOnOperation] = 334
    DiscreteBucket [thing=CreateNodeNeoPinBoardOperation] = 334
    DiscreteBucket [thing=GetPinsLikedByOwnersOfSitesOperation] = 332
    DiscreteBucket [thing=CreateRelationshipRepinsOperation] = 301
    DiscreteBucket [thing=UpdateNodeNeoSiteOperation] = 288
    DiscreteBucket [thing=DeleteNodeFromNeoSiteIndexOperation] = 288
    DiscreteBucket [thing=CreateRelationshipRootOperation] = 199
    DiscreteBucket [thing=GetNodesOutgoingClaimedAndClaimsRelationshipsOperation] = 198
    DiscreteBucket [thing=GetNodesOutgoingWantedRelationshipsOperation] = 184
    DiscreteBucket [thing=GetSpotlightPinsOperation] = 183
    DiscreteBucket [thing=CreateRelationshipShipsToOperation] = 151
    DiscreteBucket [thing=CreateRelationshipOfferedByOperation] = 151
    DiscreteBucket [thing=CreateRelationshipOffersOperation] = 151
    DiscreteBucket [thing=GetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperation] = 151
    DiscreteBucket [thing=GetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperation] = 114
    DiscreteBucket [thing=CreateNodeNeoProductOperation] = 105
    DiscreteBucket [thing=CreateRelationshipInCategoryOperation] = 105
    DiscreteBucket [thing=GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards2Operation] = 102
    DiscreteBucket [thing=GetCommentsOnPin2Operation] = 99
    DiscreteBucket [thing=CountProductsInCategoriesUnderOffer1Operation] = 95
    DiscreteBucket [thing=CreateRelationshipSitesOwnedByOperation] = 94
    DiscreteBucket [thing=CreateNodeNeoSiteOperation] = 94
    DiscreteBucket [thing=DeleteNodeFromNeoPinCommentIndexOperation] = 73
    DiscreteBucket [thing=DeleteNodeOperation] = 73
    DiscreteBucket [thing=IndexQueryNodeOnNeoShippingCountryIndexOperation] = 72
    DiscreteBucket [thing=GetOffersInCategoryOfferedByUser3Operation] = 60
    DiscreteBucket [thing=GetProductsInCategoriesUnderOffer1Operation] = 54
    DiscreteBucket [thing=GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites2Operation] = 53
    DiscreteBucket [thing=GetSubBoardsOfBoardWithNeoPins2Operation] = 53
    DiscreteBucket [thing=GetSubBoardsOfBoardWithNeoPins1Operation] = 50
    DiscreteBucket [thing=CreateRelationshipSoldOperation] = 39
    DiscreteBucket [thing=CreateRelationshipBoughtOperation] = 39
    DiscreteBucket [thing=GetSubCategoriesOfParentCategories2Operation] = 28
    DiscreteBucket [thing=GetNodesIncomingClaimsRelationshipsOperation] = 15
    DiscreteBucket [thing=GetUsersWhoClaimsOperation] = 11
    DiscreteBucket [thing=CreateRelationshipClaimsOperation] = 10
    DiscreteBucket [thing=IndexQueryNodeOnNeoPinYoutubeVideoIndexOperation] = 9
    DiscreteBucket [thing=CountProductsInCategoriesUnderOfferInCountryOperation] = 8
    DiscreteBucket [thing=CreateRelationshipTrollsOperation] = 7
    DiscreteBucket [thing=GetNodesOutgoingTrollsRelationshipsOperation] = 7
    DiscreteBucket [thing=GetCategoriesOfProductsUnderOfferInCountryOperation] = 5
    DiscreteBucket [thing=GetProductsInCategoriesUnderOfferInCountryOperation] = 4
    DiscreteBucket [thing=CreateRelationshipClaimedOperation] = 4
    DiscreteBucket [thing=CountProductsUnderOfferInCountryOperation] = 3
    DiscreteBucket [thing=IndexQueryNodeOnNeoPinEntertainmentVideoIndexOperation] = 3
    DiscreteBucket [thing=GetProductsUnderOfferInCountryOperation] = 3
    DiscreteBucket [thing=IndexQueryNodeOnNeoPinProductImageIndexOperation] = 2
    DiscreteBucket [thing=IndexQueryNodeOnNeoPinGameImageIndexOperation] = 2
    DiscreteBucket [thing=CountPinsByAuthorsOnBoardsOnSitesOperation] = 2
    DiscreteBucket [thing=GetPinsInBoardsOperation] = 1
     */

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

    // TODO implement as GeneratorWrapper is ReadWrite is moved to Operation
    public static List<Matchable<RequestLogEntry>> filterReadWrite(
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

    // TODO make ldbc_driver class
    public static boolean compareReadWrites( ReadWrite base, ReadWrite compare )
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

    // TODO use map and add this to MapUtils?
    public static <T> List<T> addIfNotExists( List<T> list, T value )
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
            for ( Matchable<RequestLogEntry> matchable : matchables )
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
