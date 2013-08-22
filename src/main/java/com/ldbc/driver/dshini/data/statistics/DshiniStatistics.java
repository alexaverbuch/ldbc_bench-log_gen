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
            CreatedAt = 1841658
        SITE_OWNED_BY
        NEOCATEGORIES
        FRIEND_OF
        REPINS
            CreatedAt = 2875196
        OFFERS
        NEOSITES
        IS_SPOTLIGHT
        COMMENTS_PIN
        TROLLS
            CreatedAt = 882
        AUTHORED_BY
        SUB_CAT_OF
        SOLD
            Transaction = 3698
        SPOTLIGHTS
        COMMENTED_BY
        URL_OF_HOST
        ROOT
        WANTED
            Transaction = 303
        BOUGHT
            Transaction = 4838
        NEOPRODUCTS
        SUB_BOARD_OF
        SITUATED_IN
        CLAIMS
            Code = 1321
        APP_OWNED_BY
        PINS
        CLAIMED
            Expires = 660
            Code = 660
        PINNED_VIA
        OFFERED_BY
        SHIPS_TO
        PINS_ASSET
        NEOPINBOARDS
        SUBSCRIBES
            CreatedAt = 1676185
            Type = 163967
        CONTACT_OF
        PIN_REFERENCES_URL
        BOARD_SHOWN_ON    
        IN_CATEGORY

    Node ObjecTypes [18]
    
    NeoPinVimeoVideo
        VideoIdentifier = 355
        Service = 355
        ObjectType = 355
        VideoId = 355
        PreviewImage = 355
        Tags = 355
        AspectRatio = 355
        CreatedAt = 355

    NeoShippingCountry
        ObjectType = 252
        CountryCode = 252
        CreatedAt = 1

    NeoPin
        ObjectType = 5170080
        Message = 5170080
        PinIdentifier = 5170080
        CreatedAt = 5170080
        CommentsClosed = 2085831
        RepinCount = 1752283
        LikeCount = 1715552
        World = 525630
        Price = 120895
        Currency = 120895

    NeoPinYoutubeVideo
        VideoIdentifier = 126964
        Service = 126964
        ObjectType = 126964
        VideoId = 126964
        CreatedAt = 126964

    NeoProduct
        Title = 9006
        ObjectType = 9006
        LargeImage = 9006
        Description = 9006
        ASIN = 9006
        ProductGroup = 9006
        IsCustom = 9006
        CreatedAt = 9006
        DshDetailImage = 7804
        DshOverlayImage = 7804
        DshGridImage = 7802
        DshListImage = 7800
        ManagerStatus = 6023
        AuxImages = 1820
        Brand = 185
        Color = 145
        Author = 137
        Language = 106
        ISBN = 101
        Size = 101
        Actor = 87
        NumberOfItems = 84
        MaterialType = 72
        Format = 60
        Publisher = 50
        AudienceRating = 49
        NumberOfPages = 45
        Artist = 45
        EAN = 33
        Model = 27
        PublicationDate = 26
        RunningTime = 24
        ReleaseDate = 22
        NumberOfDiscs = 22
        Edition = 20
        Platform = 18
        Length = 18
        Director = 18
        Feature = 13
        Weight = 13
        RegionCode = 12
        Height = 8
        AudioFormat = 7
        Width = 6
        AspectRatio = 5
        NumberOfIssues = 5
        NumberOfTracks = 4
        Source = 1
        Warranty = 1

    NeoPinDailymotionVideo
        VideoIdentifier = 294
        Service = 294
        ObjectType = 294
        VideoId = 294
        PreviewImage = 294
        Tags = 294
        AspectRatio = 294
        CreatedAt = 294

    NeoPinImage
        ObjectType = 2040702
        ImageIdentifier = 2040702
        InScaleQueue = 2040702
        CreatedAt = 2040702
        OriginalUrl = 2040592
        OriginalWidth = 2040592
        AspectRatio = 2040592
        OriginalHeight = 2040592
        OverlayUrl = 1780794
        OverlayWidth = 1780794
        ImageProcessed = 1780794
        OverlayHeight = 1780794
        DetailHeight = 1780788
        DetailWidth = 1780788
        DetailUrl = 1780788
        GridWidth = 1780779
        GridUrl = 1780779
        GridHeight = 1780779
        PinDetailUrl = 1780772
        PinDetailHeight = 1780772
        PinDetailWidth = 1780772
        PinFlowHeight = 1780765
        PinFlowWidth = 1780765
        PinFlowUrl = 1780765
        PinHighlightWidth = 1780754
        PinHighlightUrl = 1780754
        PinHighlightHeight = 1780754
        SplashWidth = 1780744
        SplashUrl = 1780744
        SplashHeight = 1780744
        ThumbUrl = 1780727
        ThumbWidth = 1780727
        ThumbHeight = 1780727
        ActivityUrl = 1780676
        ActivityWidth = 1780676
        ActivityHeight = 1780676
        ListUrl = 1780542
        ListHeight = 1780542
        ListWidth = 1780542

    NeoPinApplication
        ObjectType = 3
        ApplicationIdentifier = 3
        Title = 3
        Url = 3
        CreatedAt = 3

    NeoPinProductImage
        ObjectType = 115240
        ImageIdentifier = 115240
        InScaleQueue = 115240
        CreatedAt = 115240
        OriginalUrl = 115233
        OriginalWidth = 115233
        AspectRatio = 115233
        OriginalHeight = 115233
        OverlayUrl = 110691
        GridWidth = 110691
        OverlayWidth = 110691
        ImageProcessed = 110691
        DetailHeight = 110691
        OverlayHeight = 110691
        DetailWidth = 110691
        DetailUrl = 110691
        GridUrl = 110691
        GridHeight = 110691
        PinFlowHeight = 110690
        PinDetailUrl = 110690
        PinDetailHeight = 110690
        PinFlowWidth = 110690
        PinDetailWidth = 110690
        PinHighlightWidth = 110690
        PinFlowUrl = 110690
        PinHighlightUrl = 110690
        PinHighlightHeight = 110690
        SplashWidth = 110689
        SplashUrl = 110689
        SplashHeight = 110689
        ActivityUrl = 110688
        ThumbUrl = 110688
        ActivityWidth = 110688
        ThumbWidth = 110688
        ActivityHeight = 110688
        ThumbHeight = 110688
        ListUrl = 110681
        ListHeight = 110681
        ListWidth = 110681

    NeoPinBoard
        BoardIdentifier = 83395
        ObjectType = 83395
        Title = 83395
        CreatedAt = 83395
        Profit = 74162
        SortOrder = 21324
        IsVisible = 3

    NeoPinUrlHost
        HostHash = 78529
        ObjectType = 78529
        Hostname = 78529
        CreatedAt = 78529

    NeoSite
        ObjectType = 164207
        Type = 164207
        SiteIdentifier = 164207
        CreatedAt = 164207
        Wallpaper = 8272
        StatusMessage = 7545
        StatusMessageUpdatedAt = 7545
        DisplaySettingWishes = 5913
        DisplaySettingDob = 5913
        DisplaySettingResidence = 5913
        DisplaySettingGender = 5913
        DisplaySettingOffers = 5913

    NeoPinComment
        ObjectType = 1065946
        Message = 1065946
        CommentIdentifier = 1065946
        CreatedAt = 1065946

    NeoCategory
        ObjectType = 549
        Title = 549
        CategoryIdentifier = 549
        IsVisible = 548
        IconClass = 18
        SortableRank = 18
        LegacyId = 18
        CreatedAt = 6

    NeoPinEntertainmentVideo
        VideoIdentifier = 140337
        Service = 140337
        ObjectType = 140337
        VideoId = 140337
        CreatedAt = 140337

    NeoPinUrl
        UrlHash = 899199
        ObjectType = 899199
        Url = 899199
        CreatedAt = 899199

    NeoPinGameImage
        ImageIdentifier = 102429
        ObjectType = 102429
        CreatedAt = 102429
        OriginalUrl = 86274
        ImageProcessed = 86274
        AspectRatio = 86274
        InScaleQueue = 16155

    NeoRoot
        ObjectType = 4
        Title = 4
        RootOf = 4
        CreatedAt = 3


    Node Properties [226]:
        CreatedAt = 10218214
        ObjectType = 9997491
        Message = 6236026
        PinIdentifier = 5170080
        AspectRatio = 2263202
        ImageIdentifier = 2258371
        OriginalUrl = 2242099
        InScaleQueue = 2172097
        OriginalWidth = 2155825
        CommentsClosed = 2085831
        ImageProcessed = 1977759
        OverlayWidth = 1891485
        DetailHeight = 1891479
        GridWidth = 1891470
        PinDetailWidth = 1891462
        PinFlowHeight = 1891455
        PinHighlightHeight = 1891444
        SplashWidth = 1891433
        ThumbUrl = 1891415
        ActivityUrl = 1891364
        ListHeight = 1891223
        RepinCount = 1752283
        LikeCount = 1715552
        CommentIdentifier = 1065946
        Title = 926259
        Url = 899202
        UrlHash = 899199
        ASIN = 842308
        ProductGroup = 842300
        DetailPageURL = 819174
        ProductTypeName = 819169
        LargeImage = 787597
        DshOverlayImage = 781529
        DshGridImage = 781528
        DshDetailImage = 781506
        DshListImage = 781488
        Studio = 769960
        Publisher = 769959
        Label = 769876
        MediumImage = 764432
        DshSplashImage = 759718
        DshActivityImage = 759691
        Binding = 734130
        EAN = 691852
        World = 525630
        SalesRank = 505297
        SKU = 503586
        Brand = 461800
        Feature = 308727
        PartNumber = 295509
        VideoId = 267950
        PublicationDate = 235684
        Flag = 221518
        Model = 216659
        Type = 213724
        PackageQuantity = 211400
        Author = 202035
        ReleaseDate = 183049
        ISBN = 179531
        NumberOfPages = 178284
        HasImage = 172002
        Alias = 171393
        SiteIdentifier = 164207
        IsPublic = 163956
        Occupation = 163955
        FlirtCount = 159269
        ShowDob = 151190
        Price = 120895
        Color = 117754
        Country = 116284
        PreviewImage = 104265
        Size = 103912
        UPC = 96059
        Format = 85411
        BoardIdentifier = 83395
        Edition = 80961
        ManagerStatus = 80193
        Hostname = 78529
        Profit = 74162
        Department = 73616
        NumberOfDiscs = 72390
        NumberOfItems = 68946
        ClothingSize = 66377
        Website = 56650
        Artist = 55469
        Zip = 55253
        IsSwaped = 49517
        Id = 48674
        OfferPattern = 48672
        Genre = 43713
        AudienceRating = 36309
        IsEligibleForTradeIn = 35224
        LegalDisclaimer = 33735
        IsAdultProduct = 30554
        TrackSequence = 30154
        IsSoundActive = 28670
        OfferIdentifier = 28169
        Description = 23418
        IsCustom = 23135
        IsMemorabilia = 23066
        IsAutographed = 22096
        Platform = 21903
        OperatingSystem = 21603
        SortOrder = 21324
        HardwarePlatform = 20881
        EISBN = 19886
        Actor = 19623
        Director = 19031
        RegionCode = 16917
        ItemPartNumber = 14689
        YoutubeUrl = 12050
        Wallpaper = 8272
        GooglePlusUrl = 7734
        StatusMessage = 7545
        DisplaySettingDob = 5913
        AuxImages = 5404
        FreezedByUserAt = 5087
        Warranty = 4970
        EmailFailures = 4338
        ManufacturerPartsWarrantyDescription = 2324
        HazardousMaterialType = 2092
        ESRBAgeRating = 1750
        SeikodoProductCode = 1105
        ProductTypeSubcategory = 831
        Tags = 649
        IsVisible = 551
        CategoryIdentifier = 549
        MaterialType = 324
        NumberOfIssues = 266
        MagazineType = 259
        IssuesPerYear = 254
        CountryCode = 252
        Language = 242
        Length = 94
        Weight = 66
        Height = 60
        RunningTime = 59
        Width = 51
        MediaType = 22
        IconClass = 18
        PictureFormat = 17
        NumberOfTracks = 16
        Source = 7
        RootOf = 4
        ApplicationIdentifier = 3

    Relationship Properties [5]:
        CreatedAt = 6393921
        Type = 163967
        Transaction = 8839
        Code = 1981
        Expires = 660
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
