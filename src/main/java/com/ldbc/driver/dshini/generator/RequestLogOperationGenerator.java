package com.ldbc.driver.dshini.generator;

import java.io.File;

import org.apache.log4j.Logger;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.log.RequestLogEntryReader;
import com.ldbc.driver.dshini.operations.Dshini;
import com.ldbc.driver.dshini.operations.Dshini.ReadWrite;
import com.ldbc.driver.generator.Generator;
import com.ldbc.driver.generator.GeneratorException;

public class RequestLogOperationGenerator extends Generator<Operation<?>>
{
    private static final Logger logger = Logger.getLogger( RequestLogOperationGenerator.class );

    private final OperationMatcher operationMatcher;
    private final RequestLogEntryReader requestLogReader;

    public static Iterable<Matchable<RequestLogEntry>> operations()
    {
        return Dshini.factories().all( ReadWrite.READWRITE );
        /*
        Histogram
        defaultBucketValue=0
        bucketCount=113
        sumOfAllBucketValues=13049993
        DiscreteBucket [thing=IndexQueryGetNodeOperation] = 1210487
        DiscreteBucket [thing=CheckEntitiesLikedByUsersOperation] = 986645
        DiscreteBucket [thing=GetAuthorsOfPinsOperation] = 856334
        DiscreteBucket [thing=GetSiteOwnersOperation] = 639811
        DiscreteBucket [thing=GetCommentsAndAuthorsOnPins2Operation] = 575661
        DiscreteBucket [thing=GetPinAssetsOperation] = 552031
        DiscreteBucket [thing=CountPinsOnBoards1Operation] = 530796
        DiscreteBucket [thing=GetUrlsReferencedByPinsOperation] = 516540
        DiscreteBucket [thing=AddNodeToIndexOperation] = 509851
        DiscreteBucket [thing=CheckUsersSubscribedToSiteOperation] = 498684
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
        DiscreteBucket [thing=CreateRelationshipOperation] = 170743
        DiscreteBucket [thing=GetCategoriesOfProducts1Operation] = 162068
        DiscreteBucket [thing=GetSiteModeratorsOperation] = 154668
        DiscreteBucket [thing=GetUsersThatRepinnedPinOperation] = 114905
        DiscreteBucket [thing=BatchOperation] = 107418
        DiscreteBucket [thing=GetBoardsOnSitesOwnedByUsersOperation] = 101426
        DiscreteBucket [thing=GetPinsAndTheirAuthorsAndAssetsAndBoardsOnSitesSubscribedToByUsersOperation] = 97172
        DiscreteBucket [thing=CountPinsByAuthorsOnBoardsShownOnSitesOperation] = 95157
        DiscreteBucket [thing=GetCategoriesOfProducts2Operation] = 89973
        DiscreteBucket [thing=DeleteNodeFromIndexOperation] = 89537
        DiscreteBucket [thing=UpdateNodePropertiesOperation] = 89464
        DiscreteBucket [thing=GetUsersThatLikedEntitiesOperation] = 85298
        DiscreteBucket [thing=CountOwnersOfSitesShowingBoardsSubscribedToByUserOperation] = 82367
        DiscreteBucket [thing=CountUsersSubscribedToBoardsOnSitesOperation] = 82315
        DiscreteBucket [thing=GetPinsAndTheirAuthorsAndAssetsOnBoardsOperation] = 79339
        DiscreteBucket [thing=GetNodeOutRelationshipsOperation] = 71586
        DiscreteBucket [thing=GetOwnersOfSitesShowingBoardsSubscribedToByUsers1Operation] = 71052
        DiscreteBucket [thing=GetBoardsShownOnSitesOperation] = 69887
        DiscreteBucket [thing=GetUsersWhoClaimedOperation] = 67701
        DiscreteBucket [thing=CountThingsLikedByUsersOperation] = 52174
        DiscreteBucket [thing=GetNodeTypedOutRelationshipsOperation] = 51400
        DiscreteBucket [thing=GetUsersWhoLikedEntityOperation] = 50999
        DiscreteBucket [thing=CountAuthorsOfCommentsOnPinThatHaveNotBeenTrolledByUserOperation] = 33654
        DiscreteBucket [thing=GetCommentsAndAuthorsOnPins1Operation] = 33654
        DiscreteBucket [thing=CountRecentPinsOperation] = 32198
        DiscreteBucket [thing=CountPinsOnBoards2Operation] = 32198
        DiscreteBucket [thing=GetAuthorsOfCommentsOperation] = 27215
        DiscreteBucket [thing=GetUsersSubscribedToBoardsOnSitesOwnedByGivenUserOperation] = 23845
        DiscreteBucket [thing=CountUsersSubscribedToBoardsOnSitesOwnedByAuthorsOperation] = 23845
        DiscreteBucket [thing=GetCommentedOnPinsOperation] = 21333
        DiscreteBucket [thing=GetAuthorsOfCommentsOnPinsOperation] = 21260
        DiscreteBucket [thing=CountCommentsOnPinsOperation] = 19126
        DiscreteBucket [thing=GetBoardsShownOnSitesOwnedByUsersOperation] = 16722
        DiscreteBucket [thing=GetSubscriptionsOfUsersToEntitiesOperation] = 12732
        DiscreteBucket [thing=CountOwnersOfSitesShowingBoardsSubscribedToByUsersOperation] = 12190
        DiscreteBucket [thing=GetOwnersOfSitesShowingBoardsSubscribedToByUsers2Operation] = 12190
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
        DiscreteBucket [thing=CountProductsInCategoriesUnderOffer2Operation] = 1908
        DiscreteBucket [thing=GetPinnedViaOperation] = 1483
        DiscreteBucket [thing=CountProductsUnderOfferOperation] = 1432
        DiscreteBucket [thing=GetClaimedByUsersOperation] = 1368
        DiscreteBucket [thing=GetOffersByUserOperation] = 1359
        DiscreteBucket [thing=GetClaimsByUsersOperation] = 1358
        DiscreteBucket [thing=GetProductsUnderOfferOperation] = 1322
        DiscreteBucket [thing=GetOffersInCategoryOfferedByUser2Operation] = 1114
        DiscreteBucket [thing=GetSitesOwnedByUsers1Operation] = 1103
        DiscreteBucket [thing=GetParentBoardsOfBoardsOperation] = 1080
        DiscreteBucket [thing=GetRelationshipOperation] = 1063
        DiscreteBucket [thing=GetUsersSubscribedToSitesShowingBoardsOperation] = 906
        DiscreteBucket [thing=GetUserSubscriptionsOperation] = 830
        DiscreteBucket [thing=GetRootOperation] = 487
        DiscreteBucket [thing=GetUsersLikesOnEntityOperation] = 465
        DiscreteBucket [thing=GetCategoriesOfProductsUnderOfferOperation] = 437
        DiscreteBucket [thing=GetSubCategoriesOfParentCategories3Operation] = 358
        DiscreteBucket [thing=GetPinsLikedByOwnersOfSitesOperation] = 332
        DiscreteBucket [thing=GetSpotlightPinsOperation] = 183
        DiscreteBucket [thing=GetSpotlightPinsAndTheirAuthorsAndBoardsAndAssetsOperation] = 151
        DiscreteBucket [thing=GetAuthorsAndAssetsOfPinsOnBoardsShownOnSitesUserSubscribesToOperation] = 114
        DiscreteBucket [thing=GetPinsAndTheirAuthorsAndAssetsOnSubBoardsOfBoards2Operation] = 102
        DiscreteBucket [thing=GetCommentsOnPin2Operation] = 99
        DiscreteBucket [thing=CountProductsInCategoriesUnderOffer1Operation] = 95
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
        DiscreteBucket [thing=CountPinsByAuthorsOnBoardsOnSitesOperation] = 2
        DiscreteBucket [thing=GetPinsInBoardsOperation] = 1
        DiscreteBucket [thing=CheckUserLikedEntityOperation] = 0
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
