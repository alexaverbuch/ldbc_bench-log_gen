package com.ldbc.driver.dshini.data.statistics;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.tooling.GlobalGraphOperations;

import com.ldbc.driver.util.Bucket.DiscreteBucket;
import com.ldbc.driver.util.Histogram;

/*
Dshini DB Statistics

    Size 18.9 GB
    11,145,122 Nodes
    36,839,822 Relationships
    21 Node Indexes: 
            neo_pin_application, 
            neo_pin_product_image, 
            neo_pin_youtube_video, 
            neo_node, 
            neo_pin_url, 
            neo_pin_board, 
            neo_site, 
            neo_pin_url_host, 
            neo_pin_game_image, 
            neo_pin_dailymotion_video, 
            neo_pin_comment, 
            neo_shipping_country, 
            neo_pin_entertainment_video, 
            user_profile, 
            neo_category, 
            neo_pin, 
            neo_root, 
            offer, 
            neo_pin_vimeo_video, 
            neo_product, 
            neo_pin_image
    0 Relationship Indexes: 

    Relationship Types [36]
        LIKED_BY
            DiscreteBucket [thing=CreatedAt] = 1841658
        SITE_OWNED_BY
        NEOCATEGORIES
        FRIEND_OF
        REPINS
            DiscreteBucket [thing=CreatedAt] = 2875196
        OFFERS
        NEOSITES
        IS_SPOTLIGHT
        COMMENTS_PIN
        TROLLS
            DiscreteBucket [thing=CreatedAt] = 882
        AUTHORED_BY
        SUB_CAT_OF
        SOLD
            DiscreteBucket [thing=Transaction] = 3698
        SPOTLIGHTS
        COMMENTED_BY
        URL_OF_HOST
        ROOT
        WANTED
            DiscreteBucket [thing=Transaction] = 303
        BOUGHT
            DiscreteBucket [thing=Transaction] = 4838
        NEOPRODUCTS
        SUB_BOARD_OF
        SITUATED_IN
        CLAIMS
            DiscreteBucket [thing=Code] = 1321
        APP_OWNED_BY
        PINS
        CLAIMED
            DiscreteBucket [thing=Expires] = 660
            DiscreteBucket [thing=Code] = 660
        PINNED_VIA
        OFFERED_BY
        SHIPS_TO
        PINS_ASSET
        NEOPINBOARDS
        SUBSCRIBES
            DiscreteBucket [thing=CreatedAt] = 1676185
            DiscreteBucket [thing=Type] = 163967
        CONTACT_OF
        PIN_REFERENCES_URL
        BOARD_SHOWN_ON    
        IN_CATEGORY

    Node ObjecTypes [18]
    
    NeoPinVimeoVideo
        DiscreteBucket [thing=VideoIdentifier] = 355
        DiscreteBucket [thing=Service] = 355
        DiscreteBucket [thing=ObjectType] = 355
        DiscreteBucket [thing=VideoId] = 355
        DiscreteBucket [thing=PreviewImage] = 355
        DiscreteBucket [thing=Tags] = 355
        DiscreteBucket [thing=AspectRatio] = 355
        DiscreteBucket [thing=CreatedAt] = 355

    NeoShippingCountry
        DiscreteBucket [thing=ObjectType] = 252
        DiscreteBucket [thing=CountryCode] = 252
        DiscreteBucket [thing=CreatedAt] = 1

    NeoPin
        DiscreteBucket [thing=ObjectType] = 5170080
        DiscreteBucket [thing=Message] = 5170080
        DiscreteBucket [thing=PinIdentifier] = 5170080
        DiscreteBucket [thing=CreatedAt] = 5170080
        DiscreteBucket [thing=CommentsClosed] = 2085831
        DiscreteBucket [thing=RepinCount] = 1752283
        DiscreteBucket [thing=LikeCount] = 1715552
        DiscreteBucket [thing=World] = 525630
        DiscreteBucket [thing=Price] = 120895
        DiscreteBucket [thing=Currency] = 120895

    NeoPinYoutubeVideo
        DiscreteBucket [thing=VideoIdentifier] = 126964
        DiscreteBucket [thing=Service] = 126964
        DiscreteBucket [thing=ObjectType] = 126964
        DiscreteBucket [thing=VideoId] = 126964
        DiscreteBucket [thing=CreatedAt] = 126964

    NeoProduct
        DiscreteBucket [thing=Title] = 9006
        DiscreteBucket [thing=ObjectType] = 9006
        DiscreteBucket [thing=LargeImage] = 9006
        DiscreteBucket [thing=Description] = 9006
        DiscreteBucket [thing=ASIN] = 9006
        DiscreteBucket [thing=ProductGroup] = 9006
        DiscreteBucket [thing=IsCustom] = 9006
        DiscreteBucket [thing=CreatedAt] = 9006
        DiscreteBucket [thing=DshDetailImage] = 7804
        DiscreteBucket [thing=DshOverlayImage] = 7804
        DiscreteBucket [thing=DshGridImage] = 7802
        DiscreteBucket [thing=DshListImage] = 7800
        DiscreteBucket [thing=ManagerStatus] = 6023
        DiscreteBucket [thing=AuxImages] = 1820
        DiscreteBucket [thing=Brand] = 185
        DiscreteBucket [thing=Color] = 145
        DiscreteBucket [thing=Author] = 137
        DiscreteBucket [thing=Language] = 106
        DiscreteBucket [thing=ISBN] = 101
        DiscreteBucket [thing=Size] = 101
        DiscreteBucket [thing=Actor] = 87
        DiscreteBucket [thing=NumberOfItems] = 84
        DiscreteBucket [thing=MaterialType] = 72
        DiscreteBucket [thing=Format] = 60
        DiscreteBucket [thing=Publisher] = 50
        DiscreteBucket [thing=AudienceRating] = 49
        DiscreteBucket [thing=NumberOfPages] = 45
        DiscreteBucket [thing=Artist] = 45
        DiscreteBucket [thing=EAN] = 33
        DiscreteBucket [thing=Model] = 27
        DiscreteBucket [thing=PublicationDate] = 26
        DiscreteBucket [thing=RunningTime] = 24
        DiscreteBucket [thing=ReleaseDate] = 22
        DiscreteBucket [thing=NumberOfDiscs] = 22
        DiscreteBucket [thing=Edition] = 20
        DiscreteBucket [thing=Platform] = 18
        DiscreteBucket [thing=Length] = 18
        DiscreteBucket [thing=Director] = 18
        DiscreteBucket [thing=Feature] = 13
        DiscreteBucket [thing=Weight] = 13
        DiscreteBucket [thing=RegionCode] = 12
        DiscreteBucket [thing=Height] = 8
        DiscreteBucket [thing=AudioFormat] = 7
        DiscreteBucket [thing=Width] = 6
        DiscreteBucket [thing=AspectRatio] = 5
        DiscreteBucket [thing=NumberOfIssues] = 5
        DiscreteBucket [thing=NumberOfTracks] = 4
        DiscreteBucket [thing=Source] = 1
        DiscreteBucket [thing=Warranty] = 1

    NeoPinDailymotionVideo
        DiscreteBucket [thing=VideoIdentifier] = 294
        DiscreteBucket [thing=Service] = 294
        DiscreteBucket [thing=ObjectType] = 294
        DiscreteBucket [thing=VideoId] = 294
        DiscreteBucket [thing=PreviewImage] = 294
        DiscreteBucket [thing=Tags] = 294
        DiscreteBucket [thing=AspectRatio] = 294
        DiscreteBucket [thing=CreatedAt] = 294

    NeoPinImage
        DiscreteBucket [thing=ObjectType] = 2040702
        DiscreteBucket [thing=ImageIdentifier] = 2040702
        DiscreteBucket [thing=InScaleQueue] = 2040702
        DiscreteBucket [thing=CreatedAt] = 2040702
        DiscreteBucket [thing=OriginalUrl] = 2040592
        DiscreteBucket [thing=OriginalWidth] = 2040592
        DiscreteBucket [thing=AspectRatio] = 2040592
        DiscreteBucket [thing=OriginalHeight] = 2040592
        DiscreteBucket [thing=OverlayUrl] = 1780794
        DiscreteBucket [thing=OverlayWidth] = 1780794
        DiscreteBucket [thing=ImageProcessed] = 1780794
        DiscreteBucket [thing=OverlayHeight] = 1780794
        DiscreteBucket [thing=DetailHeight] = 1780788
        DiscreteBucket [thing=DetailWidth] = 1780788
        DiscreteBucket [thing=DetailUrl] = 1780788
        DiscreteBucket [thing=GridWidth] = 1780779
        DiscreteBucket [thing=GridUrl] = 1780779
        DiscreteBucket [thing=GridHeight] = 1780779
        DiscreteBucket [thing=PinDetailUrl] = 1780772
        DiscreteBucket [thing=PinDetailHeight] = 1780772
        DiscreteBucket [thing=PinDetailWidth] = 1780772
        DiscreteBucket [thing=PinFlowHeight] = 1780765
        DiscreteBucket [thing=PinFlowWidth] = 1780765
        DiscreteBucket [thing=PinFlowUrl] = 1780765
        DiscreteBucket [thing=PinHighlightWidth] = 1780754
        DiscreteBucket [thing=PinHighlightUrl] = 1780754
        DiscreteBucket [thing=PinHighlightHeight] = 1780754
        DiscreteBucket [thing=SplashWidth] = 1780744
        DiscreteBucket [thing=SplashUrl] = 1780744
        DiscreteBucket [thing=SplashHeight] = 1780744
        DiscreteBucket [thing=ThumbUrl] = 1780727
        DiscreteBucket [thing=ThumbWidth] = 1780727
        DiscreteBucket [thing=ThumbHeight] = 1780727
        DiscreteBucket [thing=ActivityUrl] = 1780676
        DiscreteBucket [thing=ActivityWidth] = 1780676
        DiscreteBucket [thing=ActivityHeight] = 1780676
        DiscreteBucket [thing=ListUrl] = 1780542
        DiscreteBucket [thing=ListHeight] = 1780542
        DiscreteBucket [thing=ListWidth] = 1780542

    NeoPinApplication
        DiscreteBucket [thing=ObjectType] = 3
        DiscreteBucket [thing=ApplicationIdentifier] = 3
        DiscreteBucket [thing=Title] = 3
        DiscreteBucket [thing=Url] = 3
        DiscreteBucket [thing=CreatedAt] = 3

    NeoPinProductImage
        DiscreteBucket [thing=ObjectType] = 115240
        DiscreteBucket [thing=ImageIdentifier] = 115240
        DiscreteBucket [thing=InScaleQueue] = 115240
        DiscreteBucket [thing=CreatedAt] = 115240
        DiscreteBucket [thing=OriginalUrl] = 115233
        DiscreteBucket [thing=OriginalWidth] = 115233
        DiscreteBucket [thing=AspectRatio] = 115233
        DiscreteBucket [thing=OriginalHeight] = 115233
        DiscreteBucket [thing=OverlayUrl] = 110691
        DiscreteBucket [thing=GridWidth] = 110691
        DiscreteBucket [thing=OverlayWidth] = 110691
        DiscreteBucket [thing=ImageProcessed] = 110691
        DiscreteBucket [thing=DetailHeight] = 110691
        DiscreteBucket [thing=OverlayHeight] = 110691
        DiscreteBucket [thing=DetailWidth] = 110691
        DiscreteBucket [thing=DetailUrl] = 110691
        DiscreteBucket [thing=GridUrl] = 110691
        DiscreteBucket [thing=GridHeight] = 110691
        DiscreteBucket [thing=PinFlowHeight] = 110690
        DiscreteBucket [thing=PinDetailUrl] = 110690
        DiscreteBucket [thing=PinDetailHeight] = 110690
        DiscreteBucket [thing=PinFlowWidth] = 110690
        DiscreteBucket [thing=PinDetailWidth] = 110690
        DiscreteBucket [thing=PinHighlightWidth] = 110690
        DiscreteBucket [thing=PinFlowUrl] = 110690
        DiscreteBucket [thing=PinHighlightUrl] = 110690
        DiscreteBucket [thing=PinHighlightHeight] = 110690
        DiscreteBucket [thing=SplashWidth] = 110689
        DiscreteBucket [thing=SplashUrl] = 110689
        DiscreteBucket [thing=SplashHeight] = 110689
        DiscreteBucket [thing=ActivityUrl] = 110688
        DiscreteBucket [thing=ThumbUrl] = 110688
        DiscreteBucket [thing=ActivityWidth] = 110688
        DiscreteBucket [thing=ThumbWidth] = 110688
        DiscreteBucket [thing=ActivityHeight] = 110688
        DiscreteBucket [thing=ThumbHeight] = 110688
        DiscreteBucket [thing=ListUrl] = 110681
        DiscreteBucket [thing=ListHeight] = 110681
        DiscreteBucket [thing=ListWidth] = 110681

    NeoPinBoard
        DiscreteBucket [thing=BoardIdentifier] = 83395
        DiscreteBucket [thing=ObjectType] = 83395
        DiscreteBucket [thing=Title] = 83395
        DiscreteBucket [thing=CreatedAt] = 83395
        DiscreteBucket [thing=Profit] = 74162
        DiscreteBucket [thing=SortOrder] = 21324
        DiscreteBucket [thing=IsVisible] = 3

    NeoPinUrlHost
        DiscreteBucket [thing=HostHash] = 78529
        DiscreteBucket [thing=ObjectType] = 78529
        DiscreteBucket [thing=Hostname] = 78529
        DiscreteBucket [thing=CreatedAt] = 78529

    NeoSite
        DiscreteBucket [thing=ObjectType] = 164207
        DiscreteBucket [thing=Type] = 164207
        DiscreteBucket [thing=SiteIdentifier] = 164207
        DiscreteBucket [thing=CreatedAt] = 164207
        DiscreteBucket [thing=Wallpaper] = 8272
        DiscreteBucket [thing=StatusMessage] = 7545
        DiscreteBucket [thing=StatusMessageUpdatedAt] = 7545
        DiscreteBucket [thing=DisplaySettingWishes] = 5913
        DiscreteBucket [thing=DisplaySettingDob] = 5913
        DiscreteBucket [thing=DisplaySettingResidence] = 5913
        DiscreteBucket [thing=DisplaySettingGender] = 5913
        DiscreteBucket [thing=DisplaySettingOffers] = 5913

    NeoPinComment
        DiscreteBucket [thing=ObjectType] = 1065946
        DiscreteBucket [thing=Message] = 1065946
        DiscreteBucket [thing=CommentIdentifier] = 1065946
        DiscreteBucket [thing=CreatedAt] = 1065946

    NeoCategory
        DiscreteBucket [thing=ObjectType] = 549
        DiscreteBucket [thing=Title] = 549
        DiscreteBucket [thing=CategoryIdentifier] = 549
        DiscreteBucket [thing=IsVisible] = 548
        DiscreteBucket [thing=IconClass] = 18
        DiscreteBucket [thing=SortableRank] = 18
        DiscreteBucket [thing=LegacyId] = 18
        DiscreteBucket [thing=CreatedAt] = 6

    NeoPinEntertainmentVideo
        DiscreteBucket [thing=VideoIdentifier] = 140337
        DiscreteBucket [thing=Service] = 140337
        DiscreteBucket [thing=ObjectType] = 140337
        DiscreteBucket [thing=VideoId] = 140337
        DiscreteBucket [thing=CreatedAt] = 140337

    NeoPinUrl
        DiscreteBucket [thing=UrlHash] = 899199
        DiscreteBucket [thing=ObjectType] = 899199
        DiscreteBucket [thing=Url] = 899199
        DiscreteBucket [thing=CreatedAt] = 899199

    NeoPinGameImage
        DiscreteBucket [thing=ImageIdentifier] = 102429
        DiscreteBucket [thing=ObjectType] = 102429
        DiscreteBucket [thing=CreatedAt] = 102429
        DiscreteBucket [thing=OriginalUrl] = 86274
        DiscreteBucket [thing=ImageProcessed] = 86274
        DiscreteBucket [thing=AspectRatio] = 86274
        DiscreteBucket [thing=InScaleQueue] = 16155

    NeoRoot
        DiscreteBucket [thing=ObjectType] = 4
        DiscreteBucket [thing=Title] = 4
        DiscreteBucket [thing=RootOf] = 4
        DiscreteBucket [thing=CreatedAt] = 3


    Node Properties [226]:
        DiscreteBucket [thing=CreatedAt] = 10218214
        DiscreteBucket [thing=ObjectType] = 9997491
        DiscreteBucket [thing=Message] = 6236026
        DiscreteBucket [thing=PinIdentifier] = 5170080
        DiscreteBucket [thing=AspectRatio] = 2263202
        DiscreteBucket [thing=ImageIdentifier] = 2258371
        DiscreteBucket [thing=OriginalUrl] = 2242099
        DiscreteBucket [thing=InScaleQueue] = 2172097
        DiscreteBucket [thing=OriginalWidth] = 2155825
        DiscreteBucket [thing=CommentsClosed] = 2085831
        DiscreteBucket [thing=ImageProcessed] = 1977759
        DiscreteBucket [thing=OverlayWidth] = 1891485
        DiscreteBucket [thing=DetailHeight] = 1891479
        DiscreteBucket [thing=GridWidth] = 1891470
        DiscreteBucket [thing=PinDetailWidth] = 1891462
        DiscreteBucket [thing=PinFlowHeight] = 1891455
        DiscreteBucket [thing=PinHighlightHeight] = 1891444
        DiscreteBucket [thing=SplashWidth] = 1891433
        DiscreteBucket [thing=ThumbUrl] = 1891415
        DiscreteBucket [thing=ActivityUrl] = 1891364
        DiscreteBucket [thing=ListHeight] = 1891223
        DiscreteBucket [thing=RepinCount] = 1752283
        DiscreteBucket [thing=LikeCount] = 1715552
        DiscreteBucket [thing=CommentIdentifier] = 1065946
        DiscreteBucket [thing=Title] = 926259
        DiscreteBucket [thing=Url] = 899202
        DiscreteBucket [thing=UrlHash] = 899199
        DiscreteBucket [thing=ASIN] = 842308
        DiscreteBucket [thing=ProductGroup] = 842300
        DiscreteBucket [thing=DetailPageURL] = 819174
        DiscreteBucket [thing=ProductTypeName] = 819169
        DiscreteBucket [thing=LargeImage] = 787597
        DiscreteBucket [thing=DshOverlayImage] = 781529
        DiscreteBucket [thing=DshGridImage] = 781528
        DiscreteBucket [thing=DshDetailImage] = 781506
        DiscreteBucket [thing=DshListImage] = 781488
        DiscreteBucket [thing=Studio] = 769960
        DiscreteBucket [thing=Publisher] = 769959
        DiscreteBucket [thing=Label] = 769876
        DiscreteBucket [thing=MediumImage] = 764432
        DiscreteBucket [thing=DshSplashImage] = 759718
        DiscreteBucket [thing=DshActivityImage] = 759691
        DiscreteBucket [thing=Binding] = 734130
        DiscreteBucket [thing=EAN] = 691852
        DiscreteBucket [thing=World] = 525630
        DiscreteBucket [thing=SalesRank] = 505297
        DiscreteBucket [thing=SKU] = 503586
        DiscreteBucket [thing=Brand] = 461800
        DiscreteBucket [thing=Feature] = 308727
        DiscreteBucket [thing=PartNumber] = 295509
        DiscreteBucket [thing=VideoId] = 267950
        DiscreteBucket [thing=PublicationDate] = 235684
        DiscreteBucket [thing=Flag] = 221518
        DiscreteBucket [thing=Model] = 216659
        DiscreteBucket [thing=Type] = 213724
        DiscreteBucket [thing=PackageQuantity] = 211400
        DiscreteBucket [thing=Author] = 202035
        DiscreteBucket [thing=ReleaseDate] = 183049
        DiscreteBucket [thing=ISBN] = 179531
        DiscreteBucket [thing=NumberOfPages] = 178284
        DiscreteBucket [thing=HasImage] = 172002
        DiscreteBucket [thing=Alias] = 171393
        DiscreteBucket [thing=SiteIdentifier] = 164207
        DiscreteBucket [thing=IsPublic] = 163956
        DiscreteBucket [thing=Occupation] = 163955
        DiscreteBucket [thing=FlirtCount] = 159269
        DiscreteBucket [thing=ShowDob] = 151190
        DiscreteBucket [thing=Price] = 120895
        DiscreteBucket [thing=Color] = 117754
        DiscreteBucket [thing=Country] = 116284
        DiscreteBucket [thing=PreviewImage] = 104265
        DiscreteBucket [thing=Size] = 103912
        DiscreteBucket [thing=UPC] = 96059
        DiscreteBucket [thing=Format] = 85411
        DiscreteBucket [thing=BoardIdentifier] = 83395
        DiscreteBucket [thing=Edition] = 80961
        DiscreteBucket [thing=ManagerStatus] = 80193
        DiscreteBucket [thing=Hostname] = 78529
        DiscreteBucket [thing=Profit] = 74162
        DiscreteBucket [thing=Department] = 73616
        DiscreteBucket [thing=NumberOfDiscs] = 72390
        DiscreteBucket [thing=NumberOfItems] = 68946
        DiscreteBucket [thing=ClothingSize] = 66377
        DiscreteBucket [thing=Website] = 56650
        DiscreteBucket [thing=Artist] = 55469
        DiscreteBucket [thing=Zip] = 55253
        DiscreteBucket [thing=IsSwaped] = 49517
        DiscreteBucket [thing=Id] = 48674
        DiscreteBucket [thing=OfferPattern] = 48672
        DiscreteBucket [thing=Genre] = 43713
        DiscreteBucket [thing=AudienceRating] = 36309
        DiscreteBucket [thing=IsEligibleForTradeIn] = 35224
        DiscreteBucket [thing=LegalDisclaimer] = 33735
        DiscreteBucket [thing=IsAdultProduct] = 30554
        DiscreteBucket [thing=TrackSequence] = 30154
        DiscreteBucket [thing=IsSoundActive] = 28670
        DiscreteBucket [thing=OfferIdentifier] = 28169
        DiscreteBucket [thing=Description] = 23418
        DiscreteBucket [thing=IsCustom] = 23135
        DiscreteBucket [thing=IsMemorabilia] = 23066
        DiscreteBucket [thing=IsAutographed] = 22096
        DiscreteBucket [thing=Platform] = 21903
        DiscreteBucket [thing=OperatingSystem] = 21603
        DiscreteBucket [thing=SortOrder] = 21324
        DiscreteBucket [thing=HardwarePlatform] = 20881
        DiscreteBucket [thing=EISBN] = 19886
        DiscreteBucket [thing=Actor] = 19623
        DiscreteBucket [thing=Director] = 19031
        DiscreteBucket [thing=RegionCode] = 16917
        DiscreteBucket [thing=ItemPartNumber] = 14689
        DiscreteBucket [thing=YoutubeUrl] = 12050
        DiscreteBucket [thing=Wallpaper] = 8272
        DiscreteBucket [thing=GooglePlusUrl] = 7734
        DiscreteBucket [thing=StatusMessage] = 7545
        DiscreteBucket [thing=DisplaySettingDob] = 5913
        DiscreteBucket [thing=AuxImages] = 5404
        DiscreteBucket [thing=FreezedByUserAt] = 5087
        DiscreteBucket [thing=Warranty] = 4970
        DiscreteBucket [thing=EmailFailures] = 4338
        DiscreteBucket [thing=ManufacturerPartsWarrantyDescription] = 2324
        DiscreteBucket [thing=HazardousMaterialType] = 2092
        DiscreteBucket [thing=ESRBAgeRating] = 1750
        DiscreteBucket [thing=SeikodoProductCode] = 1105
        DiscreteBucket [thing=ProductTypeSubcategory] = 831
        DiscreteBucket [thing=Tags] = 649
        DiscreteBucket [thing=IsVisible] = 551
        DiscreteBucket [thing=CategoryIdentifier] = 549
        DiscreteBucket [thing=MaterialType] = 324
        DiscreteBucket [thing=NumberOfIssues] = 266
        DiscreteBucket [thing=MagazineType] = 259
        DiscreteBucket [thing=IssuesPerYear] = 254
        DiscreteBucket [thing=CountryCode] = 252
        DiscreteBucket [thing=Language] = 242
        DiscreteBucket [thing=Length] = 94
        DiscreteBucket [thing=Weight] = 66
        DiscreteBucket [thing=Height] = 60
        DiscreteBucket [thing=RunningTime] = 59
        DiscreteBucket [thing=Width] = 51
        DiscreteBucket [thing=MediaType] = 22
        DiscreteBucket [thing=IconClass] = 18
        DiscreteBucket [thing=PictureFormat] = 17
        DiscreteBucket [thing=NumberOfTracks] = 16
        DiscreteBucket [thing=Source] = 7
        DiscreteBucket [thing=RootOf] = 4
        DiscreteBucket [thing=ApplicationIdentifier] = 3

    Relationship Properties [5]:
        DiscreteBucket [thing=CreatedAt] = 6393921
        DiscreteBucket [thing=Type] = 163967
        DiscreteBucket [thing=Transaction] = 8839
        DiscreteBucket [thing=Code] = 1981
        DiscreteBucket [thing=Expires] = 660
 */

public class DshiniStatistics
{
    final String DB_PATH = "/home/alex/Desktop/Dshini/dshini-graphdb-backup-2013-04-29";
    final String CONFIG_PATH = "/neo4j.properties";
    GraphDatabaseService graphDb;

    public static void main( final String[] args ) throws IOException
    {
        DshiniStatistics dshiniStatistics = new DshiniStatistics();
        dshiniStatistics.openDb();
        dshiniStatistics.printSimpleStats();
        dshiniStatistics.shutDown();
    }

    void openDb() throws IOException
    {
        Map config = new Properties();
        // config.put("neostore.nodestore.db.mapped_memory", "10M");
        // config.put("string_block_size", "60");
        // config.put("array_block_size", "300");
        // TODO use MapUtils from ldbc_driver
        // TODO uncomment when have reasonable .properties file from Dshini
        // ( (Properties) config ).load(
        // DshiniDbOpener.class.getResourceAsStream( CONFIG_PATH ) );
        graphDb = new GraphDatabaseFactory().newEmbeddedDatabaseBuilder( DB_PATH ).setConfig( config ).newGraphDatabase();
        registerShutdownHook( graphDb );
    }

    private void printSimpleStats()
    {
        System.out.println( "Dshini DB Statistics" );
        // System.out.println( String.format( "\tNodes: %s", getNodeCount() ) );

        // Map<String, Histogram<String, Integer>> nodeProperties =
        // getNodePropertiesByObjectType();
        // System.out.println( String.format( "\tNode Properties [%s]",
        // nodeProperties.size() ) );
        // for ( Entry<String, Histogram<String, Integer>> entry :
        // nodeProperties.entrySet() )
        // {
        // System.out.println( String.format( "\t%s\n%s", entry.getKey(),
        // entry.getValue().toPrettyString( "\t" ) ) );
        // }

        // System.out.println( String.format( "\tRelationships: %s",
        // getRelationshipCount() ) );
        // System.out.println( String.format( "\tRelationship Types [%s]: %s",
        // getRelationshipTypes().size(),
        // getRelationshipTypes().toString() ) );

        Map<String, Histogram<String, Integer>> relationshipProperties = getRelationshipPropertiesByType();
        System.out.println( String.format( "\tRelationship Properties [%s]", relationshipProperties.size() ) );
        for ( Entry<String, Histogram<String, Integer>> entry : relationshipProperties.entrySet() )
        {
            System.out.println( String.format( "\t%s\n%s", entry.getKey(), entry.getValue().toPrettyString( "\t" ) ) );
        }

        // System.out.println( String.format( "\tNode Indexes [%s]: %s",
        // getNodeIndexNames().length,
        // Arrays.toString( getNodeIndexNames() ) ) );
        // System.out.println( String.format(
        // "\tHas Relationship Indexes [%s]: %s",
        // getRelationshipIndexNames().length,
        // Arrays.toString( getRelationshipIndexNames() ) ) );
    }

    private long getNodeCount()
    {
        GlobalGraphOperations globalOperations = GlobalGraphOperations.at( graphDb );
        long nodeCount = -1;

        Transaction tx = graphDb.beginTx();
        try
        {
            nodeCount = IteratorUtil.count( globalOperations.getAllNodes() );
            tx.success();
        }
        catch ( Exception e )
        {
            throw new RuntimeException( e.getCause() );
        }
        finally
        {
            tx.finish();
        }
        return nodeCount;
    }

    private Histogram<String, Integer> getAllNodeProperties()
    {
        GlobalGraphOperations globalOperations = GlobalGraphOperations.at( graphDb );
        Histogram<String, Integer> nodePropertiesHistogram = new Histogram<String, Integer>( 0 );

        Transaction tx = graphDb.beginTx();
        try
        {
            for ( Node node : globalOperations.getAllNodes() )
            {
                for ( String property : node.getPropertyKeys() )
                {
                    nodePropertiesHistogram.incOrCreateBucket( DiscreteBucket.create( property ), 1 );
                }
            }
            tx.success();
        }
        catch ( Exception e )
        {
            throw new RuntimeException( e.getCause() );
        }
        finally
        {
            tx.finish();
        }
        return nodePropertiesHistogram;
    }

    private Map<String, Histogram<String, Integer>> getNodePropertiesByObjectType()
    {
        GlobalGraphOperations globalOperations = GlobalGraphOperations.at( graphDb );
        Map<String, Histogram<String, Integer>> nodeProperties = new HashMap<String, Histogram<String, Integer>>();

        Transaction tx = graphDb.beginTx();
        try
        {
            for ( Node node : globalOperations.getAllNodes() )
            {
                if ( false == node.hasProperty( "ObjectType" ) )
                {
                    continue;
                }
                String nodeType = (String) node.getProperty( "ObjectType" );
                if ( false == nodeProperties.containsKey( nodeType ) )
                {
                    nodeProperties.put( nodeType, new Histogram<String, Integer>( 0 ) );
                }

                Histogram<String, Integer> histogram = nodeProperties.get( nodeType );

                for ( String property : node.getPropertyKeys() )
                {
                    histogram.incOrCreateBucket( DiscreteBucket.create( property ), 1 );
                }
            }
            tx.success();
        }
        catch ( Exception e )
        {
            throw new RuntimeException( e.getCause() );
        }
        finally
        {
            tx.finish();
        }
        return nodeProperties;
    }

    private Histogram<String, Integer> getAllRelationshipProperties()
    {
        GlobalGraphOperations globalOperations = GlobalGraphOperations.at( graphDb );
        Histogram<String, Integer> relationshipPropertiesHistogram = new Histogram<String, Integer>( 0 );

        Transaction tx = graphDb.beginTx();
        try
        {
            for ( Relationship relationship : globalOperations.getAllRelationships() )
            {
                for ( String property : relationship.getPropertyKeys() )
                {
                    relationshipPropertiesHistogram.incOrCreateBucket( DiscreteBucket.create( property ), 1 );
                }
            }
            tx.success();
        }
        catch ( Exception e )
        {
            throw new RuntimeException( e.getCause() );
        }
        finally
        {
            tx.finish();
        }
        return relationshipPropertiesHistogram;
    }

    private Map<String, Histogram<String, Integer>> getRelationshipPropertiesByType()
    {
        GlobalGraphOperations globalOperations = GlobalGraphOperations.at( graphDb );
        Map<String, Histogram<String, Integer>> relationshipProperties = new HashMap<String, Histogram<String, Integer>>();

        Transaction tx = graphDb.beginTx();
        try
        {
            for ( Relationship relationship : globalOperations.getAllRelationships() )
            {
                String relationshipType = relationship.getType().toString();
                if ( false == relationshipProperties.containsKey( relationshipType ) )
                {
                    relationshipProperties.put( relationshipType, new Histogram<String, Integer>( 0 ) );
                }

                Histogram<String, Integer> histogram = relationshipProperties.get( relationshipType );

                for ( String property : relationship.getPropertyKeys() )
                {
                    histogram.incOrCreateBucket( DiscreteBucket.create( property ), 1 );
                }
            }
            tx.success();
        }
        catch ( Exception e )
        {
            throw new RuntimeException( e.getCause() );
        }
        finally
        {
            tx.finish();
        }
        return relationshipProperties;
    }

    private long getRelationshipCount()
    {
        GlobalGraphOperations globalOperations = GlobalGraphOperations.at( graphDb );
        long relationshipCount = -1;

        Transaction tx = graphDb.beginTx();
        try
        {
            relationshipCount = IteratorUtil.count( globalOperations.getAllRelationships() );
            tx.success();
        }
        catch ( Exception e )
        {
            throw new RuntimeException( e.getCause() );
        }
        finally
        {
            tx.finish();
        }
        return relationshipCount;
    }

    private Collection<RelationshipType> getRelationshipTypes()
    {
        GlobalGraphOperations globalOperations = GlobalGraphOperations.at( graphDb );
        Collection<RelationshipType> relationshipTypes = null;

        Transaction tx = graphDb.beginTx();
        try
        {
            relationshipTypes = IteratorUtil.asCollection( globalOperations.getAllRelationshipTypes() );
            tx.success();
        }
        catch ( Exception e )
        {
            throw new RuntimeException( e.getCause() );
        }
        finally
        {
            tx.finish();
        }
        return relationshipTypes;
    }

    private String[] getNodeIndexNames()
    {
        String[] nodeIndexNames = null;

        Transaction tx = graphDb.beginTx();
        try
        {
            nodeIndexNames = graphDb.index().nodeIndexNames();

            tx.success();
        }
        catch ( Exception e )
        {
            throw new RuntimeException( e.getCause() );
        }
        finally
        {
            tx.finish();
        }
        return nodeIndexNames;
    }

    private String[] getRelationshipIndexNames()
    {
        String[] relationshipIndexNames = null;

        Transaction tx = graphDb.beginTx();
        try
        {
            relationshipIndexNames = graphDb.index().relationshipIndexNames();

            tx.success();
        }
        catch ( Exception e )
        {
            throw new RuntimeException( e.getCause() );
        }
        finally
        {
            tx.finish();
        }
        return relationshipIndexNames;
    }

    void shutDown()
    {
        System.out.println( "Shutting down database ..." );
        graphDb.shutdown();
    }

    private static void registerShutdownHook( final GraphDatabaseService graphDb )
    {
        Runtime.getRuntime().addShutdownHook( new Thread()
        {
            @Override
            public void run()
            {
                graphDb.shutdown();
            }
        } );
    }
}
