package com.ldbc.driver.dshini.generator;

import java.io.File;

import org.apache.log4j.Logger;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.log.RequestLogEntryReader;
import com.ldbc.driver.dshini.utils.DshiniLogs;
import com.ldbc.driver.generator.Generator;
import com.ldbc.driver.generator.GeneratorException;

public class RequestLogOperationGenerator extends Generator<Operation<?>>
{
    private static final Logger logger = Logger.getLogger( RequestLogOperationGenerator.class );

    private final OperationMatcher operationMatcher;
    private final RequestLogEntryReader requestLogReader;

    public static Iterable<Matchable<RequestLogEntry>> operations()
    {
        return DshiniLogs.allDshiniOperationFactories();
        /*
        Histogram
        bucketCount=111
        sumOfAllBucketValues=13049993
        DiscreteBucket [thing=IndexQueryGetNodeOperation] = 1210487
        DiscreteBucket [thing=CheckUserLikedEntityOperation] = 986645
        DiscreteBucket [thing=GetAuthorsOperation] = 856334
        DiscreteBucket [thing=GetSiteOwnersOperation] = 639811
        DiscreteBucket [thing=GetCommentsAndAuthorsOnPinOfGivenUserWhoIsNotTrollOperation] = 575661
        DiscreteBucket [thing=CountPinsOnBoardsOperation] = 562994
        DiscreteBucket [thing=GetPinAssetsOperation] = 552031
        DiscreteBucket [thing=GetUrlsReferencedByPinsOperation] = 516540
        DiscreteBucket [thing=AddNodeToIndexOperation] = 509851
        DiscreteBucket [thing=GetUsersSubscribedToSiteOperation] = 498684
        DiscreteBucket [thing=GetSitesBoardsShownOnOperation] = 456663
        DiscreteBucket [thing=GetPinnedByOperation] = 442957
        DiscreteBucket [thing=GetRepinnedOperation] = 390024
        DiscreteBucket [thing=GetNumberOfUsersSubscribedToSiteBoardIsShownOnOperation] = 353675
        DiscreteBucket [thing=CheckUserSubscribedToBoardOperation] = 327736
        DiscreteBucket [thing=CountLikesOperation] = 298273
        DiscreteBucket [thing=GetSitesOfGivenTypeOwnedByUserOperation] = 297338
        DiscreteBucket [thing=CountRepinsOperation] = 290747
        DiscreteBucket [thing=GetHostUrlsOperation] = 252037
        DiscreteBucket [thing=GetPinsAndAssetsOnBoardsOperation] = 229362
        DiscreteBucket [thing=GetNodeOperation] = 198873
        DiscreteBucket [thing=GetOriginalPinOperation] = 183527
        DiscreteBucket [thing=CreateRelationshipOperation] = 170743
        DiscreteBucket [thing=GetCategoriesOperation] = 162068
        DiscreteBucket [thing=GetSiteModeratorsOperation] = 154668
        DiscreteBucket [thing=GetRepinsOperation] = 114905
        DiscreteBucket [thing=BatchOperation] = 107418
        DiscreteBucket [thing=GetBoardsShownOnSitesOwnedByUsersSortedOperation] = 101426
        DiscreteBucket [thing=GetPinsAndAuthorsOnBoardsShownOnSitesSubscribedToByUserOperation] = 97172
        DiscreteBucket [thing=CountPinsOnBoardsOnGivenSitesByGivenUsersOperation] = 95157
        DiscreteBucket [thing=GetSubCategoriesThenCategoriesOperation] = 89973
        DiscreteBucket [thing=DeleteNodeFromIndexOperation] = 89537
        DiscreteBucket [thing=UpdateNodePropertiesOperation] = 89464
        DiscreteBucket [thing=GetUsersThatLikedEntitiesOperation] = 85298
        DiscreteBucket [thing=CountDistinctOwnersOfSitesShowingBoardsUserIsSubscribedToOperation] = 82367
        DiscreteBucket [thing=CountUsersSubscribedToBoardsShownOnSiteOperation] = 82315
        DiscreteBucket [thing=GetPinAssetAuthorAndBoardOperation] = 79339
        DiscreteBucket [thing=GetNodeOutRelationshipsOperation] = 71586
        DiscreteBucket [thing=GetDistinctOwnersOfSitesShowingBoardsUserIsSubscribedToOperation] = 71052
        DiscreteBucket [thing=GetBoardsShownOnSitesOperation] = 69887
        DiscreteBucket [thing=GetUsersWhoClaimedOperation] = 67701
        DiscreteBucket [thing=CountLikesByGivenUsersOperation] = 52174
        DiscreteBucket [thing=GetNodeTypedOutRelationshipsOperation] = 51400
        DiscreteBucket [thing=GetUsersWhoLikedEntityOperation] = 50999
        DiscreteBucket [thing=GetAuthorsOfCommentsOnPinThatHaveNotBeenTrolledByGivenUserOperation] = 33654
        DiscreteBucket [thing=CountAuthorsOfCommentsOnPinThatHaveNotBeenTrolledByGivenUserOperation] = 33654
        DiscreteBucket [thing=CountRecentPinsOperation] = 32198
        DiscreteBucket [thing=GetCommentsAuthorsByOperation] = 27215
        DiscreteBucket [thing=GetUsersSubscribedToBoardsOnSitesOwnedByGivenUserOperation] = 23845
        DiscreteBucket [thing=CountUsersSubscribedToBoardsOnSitesOwnedByGivenUserOperation] = 23845
        DiscreteBucket [thing=GetCommentedOnPinsOperation] = 21333
        DiscreteBucket [thing=GetPinsCommentsAuthorsOperation] = 21260
        DiscreteBucket [thing=CountCommentsOnPinsOperation] = 19126
        DiscreteBucket [thing=GetBoardsShownOnSitesOwnedByUsersOperation] = 16722
        DiscreteBucket [thing=GetUsersSubscriptionToEntityOperation] = 12732
        DiscreteBucket [thing=GetOwnersOfSitesShowingBoardsUserIsSubscribedToParameterizedOperation] = 12190
        DiscreteBucket [thing=CountOwnersOfSitesShowingBoardsUserIsSubscribedToParameterizedOperation] = 12190
        DiscreteBucket [thing=GetCategoriesOfProductsOfferedByUserOperation] = 11517
        DiscreteBucket [thing=GetParentCategoriesOfSubCategoriesOperation] = 10594
        DiscreteBucket [thing=CreateNodeOperation] = 8222
        DiscreteBucket [thing=GetOffersInCategoryOfferedByUser1Operation] = 7292
        DiscreteBucket [thing=GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards1Operation] = 6557
        DiscreteBucket [thing=CountPinsReferencingUrlsOperation] = 6553
        DiscreteBucket [thing=GetProductsInCategoriesUnderOffer2Operation] = 3900
        DiscreteBucket [thing=GetCommentsOnPin1Operation] = 3733
        DiscreteBucket [thing=DeleteRelationshipOperation] = 3415
        DiscreteBucket [thing=GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites1Operation] = 3049
        DiscreteBucket [thing=CheckUserSubscribedToEntityOperation] = 2648
        DiscreteBucket [thing=GetNodeRelationshipsOperation] = 2195
        DiscreteBucket [thing=GetPinsOnAssetsOperation] = 1966
        DiscreteBucket [thing=GetSubCategoriesOfParentCategories1Operation] = 1917
        DiscreteBucket [thing=CountOffersForProductsInSubCategoryOfCategoryOperation] = 1908
        DiscreteBucket [thing=GetPinnedViaOperation] = 1483
        DiscreteBucket [thing=CountProductsUnderOfferOperation] = 1432
        DiscreteBucket [thing=GetClaimedByUsersOperation] = 1368
        DiscreteBucket [thing=GetOffersOfferedByUserOperation] = 1359
        DiscreteBucket [thing=GetClaimsByUsersOperation] = 1358
        DiscreteBucket [thing=GetProductsUnderOfferOperation] = 1322
        DiscreteBucket [thing=GetOffersInCategoryOfferedByUser2Operation] = 1114
        DiscreteBucket [thing=GetSitesOwnedByUserOperation] = 1103
        DiscreteBucket [thing=GetParentBoardsOfBoardsOperation] = 1080
        DiscreteBucket [thing=GetRelationshipOperation] = 1063
        DiscreteBucket [thing=GetUsersSubscribedToSitesShowingBoardsOperation] = 906
        DiscreteBucket [thing=GetSubscribedToOperation] = 830
        DiscreteBucket [thing=GetRootOperation] = 487
        DiscreteBucket [thing=GetUsersLikesOnEntityOperation] = 465
        DiscreteBucket [thing=GetCategoriesProductsAreInOperation] = 437
        DiscreteBucket [thing=GetSubCategoriesOfParentCategories3Operation] = 358
        DiscreteBucket [thing=GetPinsLikedByOwnersOfSitesOperation] = 332
        DiscreteBucket [thing=GetSpotlightPinsOperation] = 183
        DiscreteBucket [thing=GetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperation] = 151
        DiscreteBucket [thing=GetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperation] = 114
        DiscreteBucket [thing=GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards2Operation] = 102
        DiscreteBucket [thing=GetCommentsOnPin2Operation] = 99
        DiscreteBucket [thing=CountProductsInCategoriesUnderOfferOperation] = 95
        DiscreteBucket [thing=DeleteNodeOperation] = 73
        DiscreteBucket [thing=GetOffersInCategoryOfferedByUser3Operation] = 60
        DiscreteBucket [thing=GetProductsInCategoriesUnderOffer1Operation] = 54
        DiscreteBucket [thing=GetPinsAndTheirAuthorsAndAssetsOnBoardsShownOnSites2Operation] = 53
        DiscreteBucket [thing=GetSubBoardsOfBoardWithNeoPins2Operation] = 53
        DiscreteBucket [thing=GetSubBoardsOfBoardWithNeoPins1Operation] = 50
        DiscreteBucket [thing=GetSubCategoriesOfParentCategories2Operation] = 28
        DiscreteBucket [thing=GetNodeTypedInRelationshipsOperation] = 15
        DiscreteBucket [thing=GetUsersWhoClaimsOperation] = 11
        DiscreteBucket [thing=CountProductsInCategoriesUnderOfferInCountryOperation] = 8
        DiscreteBucket [thing=GetCategoriesOfProductsUnderOfferInCountryOperation] = 5
        DiscreteBucket [thing=GetProductsInCategoriesUnderOfferInCountryOperation] = 4
        DiscreteBucket [thing=CountProductsUnderOfferInCountryOperation] = 3
        DiscreteBucket [thing=GetProductsUnderOfferInCountryOperation] = 3
        DiscreteBucket [thing=CountPinsOnBoardsOnSitesByAuthorWithNoUserIdOperation] = 2
        DiscreteBucket [thing=GetPinsInBoardsOperation] = 1
         */
    }

    public RequestLogOperationGenerator( File requestLogFile )
    {
        super( null );
        requestLogReader = new RequestLogEntryReader( requestLogFile );
        operationMatcher = new OperationMatcher();
        operationMatcher.setMatchables( operations() );
    }

    @Override
    protected Operation<?> doNext() throws GeneratorException
    {
        while ( requestLogReader.hasNext() )
        {
            RequestLogEntry entry = requestLogReader.next();
            try
            {
                return operationMatcher.getSingleMatchingOperation( entry );
            }
            catch ( RequestLogEntryException e )
            {
                // TODO hide notifications of entries that were not parsed
                String errMsg = String.format( "Error parsing log entry\n%s", entry.toString() );
                logger.error( errMsg );
            }
            catch ( MatchableException e )
            {
                String errMsg = String.format( "Error matching operation to log entry\n%s", entry.toString() );
                logger.error( errMsg );
                // TODO remove
                throw new GeneratorException( errMsg, e.getCause() );
            }
        }
        return null;
    }
}
