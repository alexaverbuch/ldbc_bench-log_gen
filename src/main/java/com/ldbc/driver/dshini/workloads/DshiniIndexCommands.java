package com.ldbc.driver.dshini.workloads;

import java.util.ArrayList;
import java.util.List;

import com.ldbc.driver.Operation;
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
import com.ldbc.driver.util.Pair;

public abstract class DshiniIndexCommands implements AllOperationHandlersProvider
{
    @Override
    public final List<Pair<Class<? extends Operation<?>>, Class<? extends OperationHandler<?>>>> allOperationHandlers()
    {
        List<Pair<Class<? extends Operation<?>>, Class<? extends OperationHandler<?>>>> handlers = new ArrayList<Pair<Class<? extends Operation<?>>, Class<? extends OperationHandler<?>>>>();
        handlers.add( new Pair( IndexQueryNodeOnNeoCategoryIndexOperation.class,
                getIndexQueryNodeOnNeoCategoryIndexOperationHandler() ) );
        handlers.add( new Pair( IndexQueryNodeOnNeoPinApplicationIndexOperation.class,
                getIndexQueryNodeOnNeoPinApplicationIndexOperationHandler() ) );
        handlers.add( new Pair( IndexQueryNodeOnNeoPinBoardIndexOperation.class,
                getIndexQueryNodeOnNeoPinBoardIndexOperationHandler() ) );
        handlers.add( new Pair( IndexQueryNodeOnNeoPinCommentIndexOperation.class,
                getIndexQueryNodeOnNeoPinCommentIndexOperationHandler() ) );
        handlers.add( new Pair( IndexQueryNodeOnNeoPinEntertainmentVideoIndexOperation.class,
                getIndexQueryNodeOnNeoPinEntertainmentVideoIndexOperationHandler() ) );
        handlers.add( new Pair( IndexQueryNodeOnNeoPinGameImageIndexOperation.class,
                getIndexQueryNodeOnNeoPinGameImageIndexOperationHandler() ) );
        handlers.add( new Pair( IndexQueryNodeOnNeoPinImageIndexOperation.class,
                getIndexQueryNodeOnNeoPinImageIndexOperationHandler() ) );
        handlers.add( new Pair( IndexQueryNodeOnNeoPinIndexOperation.class,
                getIndexQueryNodeOnNeoPinIndexOperationHandler() ) );
        handlers.add( new Pair( IndexQueryNodeOnNeoPinProductImageIndexOperation.class,
                getIndexQueryNodeOnNeoPinProductImageIndexOperationHandler() ) );
        handlers.add( new Pair( IndexQueryNodeOnNeoPinUrlIndexOperation.class,
                getIndexQueryNodeOnNeoPinUrlIndexOperationHandler() ) );
        handlers.add( new Pair( IndexQueryNodeOnNeoPinUrlHostIndexOperation.class,
                getIndexQueryNodeOnNeoPinUrlHostIndexOperationHandler() ) );
        handlers.add( new Pair( IndexQueryNodeOnNeoPinYoutubeVideoIndexOperation.class,
                getIndexQueryNodeOnNeoPinYoutubeVideoIndexOperationHandler() ) );
        handlers.add( new Pair( IndexQueryNodeOnNeoProductIndexOperation.class,
                getIndexQueryNodeOnNeoProductIndexOperationHandler() ) );
        handlers.add( new Pair( IndexQueryNodeOnNeoRootIndexOperation.class,
                getIndexQueryNodeOnNeoRootIndexOperationHandler() ) );
        handlers.add( new Pair( IndexQueryNodeOnNeoShippingCountryIndexOperation.class,
                getIndexQueryNodeOnNeoShippingCountryIndexOperationHandler() ) );
        handlers.add( new Pair( IndexQueryNodeOnNeoSiteIndexOperation.class,
                getIndexQueryNodeOnNeoSiteIndexOperationHandler() ) );
        handlers.add( new Pair( IndexQueryNodeOnOfferIndexOperation.class,
                getIndexQueryNodeOnOfferIndexOperationHandler() ) );
        handlers.add( new Pair( IndexQueryNodeOnUserProfileIndexOperation.class,
                getIndexQueryNodeOnUserProfileIndexOperationHandler() ) );
        handlers.add( new Pair( AddNodeToNeoPinBoardIndexOperation.class,
                getAddNodeToNeoPinBoardIndexOperationHandler() ) );
        handlers.add( new Pair( AddNodeToNeoPinIndexOperation.class, getAddNodeToNeoPinIndexOperationHandler() ) );
        handlers.add( new Pair( AddNodeToNeoPinUrlHostIndexOperation.class,
                getAddNodeToNeoPinUrlHostIndexOperationHandler() ) );
        handlers.add( new Pair( AddNodeToNeoPinUrlIndexOperation.class, getAddNodeToNeoPinUrlIndexOperationHandler() ) );
        handlers.add( new Pair( AddNodeToNeoProductIndexOperation.class, getAddNodeToNeoProductIndexOperationHandler() ) );
        handlers.add( new Pair( AddNodeToNeoSiteIndexOperation.class, getAddNodeToNeoSiteIndexOperationHandler() ) );
        handlers.add( new Pair( DeleteNodeFromNeoPinBoardIndexOperation.class,
                getDeleteNodeFromNeoPinBoardIndexOperationHandler() ) );
        handlers.add( new Pair( DeleteNodeFromNeoPinCommentIndexOperation.class,
                getDeleteNodeFromNeoPinCommentIndexOperationHandler() ) );
        handlers.add( new Pair( DeleteNodeFromNeoPinIndexOperation.class,
                getDeleteNodeFromNeoPinIndexOperationHandler() ) );
        handlers.add( new Pair( DeleteNodeFromNeoSiteIndexOperation.class,
                getDeleteNodeFromNeoSiteIndexOperationHandler() ) );
        return handlers;
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
