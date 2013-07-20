package com.ldbc.driver.dshini.workloads;

import com.ldbc.driver.Db;
import com.ldbc.driver.DbException;
import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.dshini.operations.index.AddNodeToNeoPinBoardIndexOperationFactory.AddNodeToNeoPinBoardIndexOperation;
import com.ldbc.driver.dshini.operations.index.AddNodeToNeoPinIndexOperationFactory.AddNodeToNeoPinIndexOperation;
import com.ldbc.driver.dshini.operations.index.AddNodeToNeoPinUrlHostIndexOperationFactory.AddNodeToNeoPinUrlHostIndexOperation;
import com.ldbc.driver.dshini.operations.index.AddNodeToNeoPinUrlIndexOperationFactory.AddNodeToNeoPinUrlIndexOperation;
import com.ldbc.driver.dshini.operations.index.AddNodeToNeoProductIndexOperationFactory.AddNodeToNeoProductIndexOperation;
import com.ldbc.driver.dshini.operations.index.AddNodeToNeoSiteIndexOperationFactory.AddNodeToNeoSiteIndexOperation;
import com.ldbc.driver.dshini.operations.index.DeleteNodeFromNeoPinBoardIndexOperationFactory.DeleteNodeFromNeoPinBoardIndexOperation;
import com.ldbc.driver.dshini.operations.index.DeleteNodeFromNeoPinCommentIndexOperationFactory.DeleteNodeFromNeoPinCommentIndexOperation;
import com.ldbc.driver.dshini.operations.index.DeleteNodeFromNeoPinIndexOperationFactory.DeleteNodeFromNeoPinIndexOperation;
import com.ldbc.driver.dshini.operations.index.DeleteNodeFromNeoSiteIndexOperationFactory.DeleteNodeFromNeoSiteIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoCategoryIndexOperationFactory.IndexQueryNodeOnNeoCategoryIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinApplicationIndexOperationFactory.IndexQueryNodeOnNeoPinApplicationIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinBoardIndexOperationFactory.IndexQueryNodeOnNeoPinBoardIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinCommentIndexOperationFactory.IndexQueryNodeOnNeoPinCommentIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinEntertainmentVideoIndexOperationFactory.IndexQueryNodeOnNeoPinEntertainmentVideoIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinGameImageIndexOperationFactory.IndexQueryNodeOnNeoPinGameImageIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinImageIndexOperationFactory.IndexQueryNodeOnNeoPinImageIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinIndexOperationFactory.IndexQueryNodeOnNeoPinIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinProductImageIndexOperationFactory.IndexQueryNodeOnNeoPinProductImageIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinUrlHostIndexOperationFactory.IndexQueryNodeOnNeoPinUrlHostIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinUrlIndexOperationFactory.IndexQueryNodeOnNeoPinUrlIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinYoutubeVideoIndexOperationFactory.IndexQueryNodeOnNeoPinYoutubeVideoIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoProductIndexOperationFactory.IndexQueryNodeOnNeoProductIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoRootIndexOperationFactory.IndexQueryNodeOnNeoRootIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoShippingCountryIndexOperationFactory.IndexQueryNodeOnNeoShippingCountryIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoSiteIndexOperationFactory.IndexQueryNodeOnNeoSiteIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnOfferIndexOperationFactory.IndexQueryNodeOnOfferIndexOperation;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnUserProfileIndexOperationFactory.IndexQueryNodeOnUserProfileIndexOperation;

public abstract class DshiniIndexCommands implements OperationHandlersRegistrar
{
    @Override
    public void registerHandlersWithDb( Db db ) throws DbException
    {
        db.registerOperationHandler( IndexQueryNodeOnNeoCategoryIndexOperation.class,
                getIndexQueryNodeOnNeoCategoryIndexOperationHandler() );
        db.registerOperationHandler( IndexQueryNodeOnNeoPinApplicationIndexOperation.class,
                getIndexQueryNodeOnNeoPinApplicationIndexOperationHandler() );
        db.registerOperationHandler( IndexQueryNodeOnNeoPinBoardIndexOperation.class,
                getIndexQueryNodeOnNeoPinBoardIndexOperationHandler() );
        db.registerOperationHandler( IndexQueryNodeOnNeoPinCommentIndexOperation.class,
                getIndexQueryNodeOnNeoPinCommentIndexOperationHandler() );
        db.registerOperationHandler( IndexQueryNodeOnNeoPinEntertainmentVideoIndexOperation.class,
                getIndexQueryNodeOnNeoPinEntertainmentVideoIndexOperationHandler() );
        db.registerOperationHandler( IndexQueryNodeOnNeoPinGameImageIndexOperation.class,
                getIndexQueryNodeOnNeoPinGameImageIndexOperationHandler() );
        db.registerOperationHandler( IndexQueryNodeOnNeoPinImageIndexOperation.class,
                getIndexQueryNodeOnNeoPinImageIndexOperationHandler() );
        db.registerOperationHandler( IndexQueryNodeOnNeoPinIndexOperation.class,
                getIndexQueryNodeOnNeoPinIndexOperationHandler() );
        db.registerOperationHandler( IndexQueryNodeOnNeoPinProductImageIndexOperation.class,
                getIndexQueryNodeOnNeoPinProductImageIndexOperationHandler() );
        db.registerOperationHandler( IndexQueryNodeOnNeoPinUrlIndexOperation.class,
                getIndexQueryNodeOnNeoPinUrlIndexOperationHandler() );
        db.registerOperationHandler( IndexQueryNodeOnNeoPinUrlHostIndexOperation.class,
                getIndexQueryNodeOnNeoPinUrlHostIndexOperationHandler() );
        db.registerOperationHandler( IndexQueryNodeOnNeoPinYoutubeVideoIndexOperation.class,
                getIndexQueryNodeOnNeoPinYoutubeVideoIndexOperationHandler() );
        db.registerOperationHandler( IndexQueryNodeOnNeoProductIndexOperation.class,
                getIndexQueryNodeOnNeoProductIndexOperationHandler() );
        db.registerOperationHandler( IndexQueryNodeOnNeoRootIndexOperation.class,
                getIndexQueryNodeOnNeoRootIndexOperationHandler() );
        db.registerOperationHandler( IndexQueryNodeOnNeoShippingCountryIndexOperation.class,
                getIndexQueryNodeOnNeoShippingCountryIndexOperationHandler() );
        db.registerOperationHandler( IndexQueryNodeOnNeoSiteIndexOperation.class,
                getIndexQueryNodeOnNeoSiteIndexOperationHandler() );
        db.registerOperationHandler( IndexQueryNodeOnOfferIndexOperation.class,
                getIndexQueryNodeOnOfferIndexOperationHandler() );
        db.registerOperationHandler( IndexQueryNodeOnUserProfileIndexOperation.class,
                getIndexQueryNodeOnUserProfileIndexOperationHandler() );
        db.registerOperationHandler( AddNodeToNeoPinBoardIndexOperation.class,
                getAddNodeToNeoPinBoardIndexOperationHandler() );
        db.registerOperationHandler( AddNodeToNeoPinIndexOperation.class, getAddNodeToNeoPinIndexOperationHandler() );
        db.registerOperationHandler( AddNodeToNeoPinUrlHostIndexOperation.class,
                getAddNodeToNeoPinUrlHostIndexOperationHandler() );
        db.registerOperationHandler( AddNodeToNeoPinUrlIndexOperation.class,
                getAddNodeToNeoPinUrlIndexOperationHandler() );
        db.registerOperationHandler( AddNodeToNeoProductIndexOperation.class,
                getAddNodeToNeoProductIndexOperationHandler() );
        db.registerOperationHandler( AddNodeToNeoSiteIndexOperation.class, getAddNodeToNeoSiteIndexOperationHandler() );
        db.registerOperationHandler( DeleteNodeFromNeoPinBoardIndexOperation.class,
                getDeleteNodeFromNeoPinBoardIndexOperationHandler() );
        db.registerOperationHandler( DeleteNodeFromNeoPinCommentIndexOperation.class,
                getDeleteNodeFromNeoPinCommentIndexOperationHandler() );
        db.registerOperationHandler( DeleteNodeFromNeoPinIndexOperation.class,
                getDeleteNodeFromNeoPinIndexOperationHandler() );
        db.registerOperationHandler( DeleteNodeFromNeoSiteIndexOperation.class,
                getDeleteNodeFromNeoSiteIndexOperationHandler() );
    }

    /*
     * Index Queries
     */

    public abstract Class<? extends OperationHandler<IndexQueryNodeOnNeoCategoryIndexOperation>> getIndexQueryNodeOnNeoCategoryIndexOperationHandler();

    public abstract Class<? extends OperationHandler<IndexQueryNodeOnNeoPinApplicationIndexOperation>> getIndexQueryNodeOnNeoPinApplicationIndexOperationHandler();

    public abstract Class<? extends OperationHandler<IndexQueryNodeOnNeoPinBoardIndexOperation>> getIndexQueryNodeOnNeoPinBoardIndexOperationHandler();

    public abstract Class<? extends OperationHandler<IndexQueryNodeOnNeoPinCommentIndexOperation>> getIndexQueryNodeOnNeoPinCommentIndexOperationHandler();

    public abstract Class<? extends OperationHandler<IndexQueryNodeOnNeoPinEntertainmentVideoIndexOperation>> getIndexQueryNodeOnNeoPinEntertainmentVideoIndexOperationHandler();

    public abstract Class<? extends OperationHandler<IndexQueryNodeOnNeoPinGameImageIndexOperation>> getIndexQueryNodeOnNeoPinGameImageIndexOperationHandler();

    public abstract Class<? extends OperationHandler<IndexQueryNodeOnNeoPinImageIndexOperation>> getIndexQueryNodeOnNeoPinImageIndexOperationHandler();

    public abstract Class<? extends OperationHandler<IndexQueryNodeOnNeoPinIndexOperation>> getIndexQueryNodeOnNeoPinIndexOperationHandler();

    public abstract Class<? extends OperationHandler<IndexQueryNodeOnNeoPinProductImageIndexOperation>> getIndexQueryNodeOnNeoPinProductImageIndexOperationHandler();

    public abstract Class<? extends OperationHandler<IndexQueryNodeOnNeoPinUrlIndexOperation>> getIndexQueryNodeOnNeoPinUrlIndexOperationHandler();

    public abstract Class<? extends OperationHandler<IndexQueryNodeOnNeoPinUrlHostIndexOperation>> getIndexQueryNodeOnNeoPinUrlHostIndexOperationHandler();

    public abstract Class<? extends OperationHandler<IndexQueryNodeOnNeoPinYoutubeVideoIndexOperation>> getIndexQueryNodeOnNeoPinYoutubeVideoIndexOperationHandler();

    public abstract Class<? extends OperationHandler<IndexQueryNodeOnNeoProductIndexOperation>> getIndexQueryNodeOnNeoProductIndexOperationHandler();

    public abstract Class<? extends OperationHandler<IndexQueryNodeOnNeoRootIndexOperation>> getIndexQueryNodeOnNeoRootIndexOperationHandler();

    public abstract Class<? extends OperationHandler<IndexQueryNodeOnNeoShippingCountryIndexOperation>> getIndexQueryNodeOnNeoShippingCountryIndexOperationHandler();

    public abstract Class<? extends OperationHandler<IndexQueryNodeOnNeoSiteIndexOperation>> getIndexQueryNodeOnNeoSiteIndexOperationHandler();

    public abstract Class<? extends OperationHandler<IndexQueryNodeOnOfferIndexOperation>> getIndexQueryNodeOnOfferIndexOperationHandler();

    public abstract Class<? extends OperationHandler<IndexQueryNodeOnUserProfileIndexOperation>> getIndexQueryNodeOnUserProfileIndexOperationHandler();

    /*
     * Add Node to Index
     */

    public abstract Class<? extends OperationHandler<AddNodeToNeoPinBoardIndexOperation>> getAddNodeToNeoPinBoardIndexOperationHandler();

    public abstract Class<? extends OperationHandler<AddNodeToNeoPinIndexOperation>> getAddNodeToNeoPinIndexOperationHandler();

    public abstract Class<? extends OperationHandler<AddNodeToNeoPinUrlHostIndexOperation>> getAddNodeToNeoPinUrlHostIndexOperationHandler();

    public abstract Class<? extends OperationHandler<AddNodeToNeoPinUrlIndexOperation>> getAddNodeToNeoPinUrlIndexOperationHandler();

    public abstract Class<? extends OperationHandler<AddNodeToNeoProductIndexOperation>> getAddNodeToNeoProductIndexOperationHandler();

    public abstract Class<? extends OperationHandler<AddNodeToNeoSiteIndexOperation>> getAddNodeToNeoSiteIndexOperationHandler();

    /*
     * Delete Node from Index
     */

    public abstract Class<? extends OperationHandler<DeleteNodeFromNeoPinBoardIndexOperation>> getDeleteNodeFromNeoPinBoardIndexOperationHandler();

    public abstract Class<? extends OperationHandler<DeleteNodeFromNeoPinCommentIndexOperation>> getDeleteNodeFromNeoPinCommentIndexOperationHandler();

    public abstract Class<? extends OperationHandler<DeleteNodeFromNeoPinIndexOperation>> getDeleteNodeFromNeoPinIndexOperationHandler();

    public abstract Class<? extends OperationHandler<DeleteNodeFromNeoSiteIndexOperation>> getDeleteNodeFromNeoSiteIndexOperationHandler();
}
