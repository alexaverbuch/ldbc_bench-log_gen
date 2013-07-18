package com.ldbc.driver.dshini.workloads;

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

public interface DshiniIndexCommands
{
    /*
     * Index Queries
     */

    public Class<? extends OperationHandler<IndexQueryNodeOnNeoCategoryIndexOperation>> getIndexQueryNodeOnNeoCategoryIndexOperationHandler();

    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinApplicationIndexOperation>> getIndexQueryNodeOnNeoPinApplicationIndexOperationHandler();

    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinBoardIndexOperation>> getIndexQueryNodeOnNeoPinBoardIndexOperationHandler();

    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinCommentIndexOperation>> getIndexQueryNodeOnNeoPinCommentIndexOperationHandler();

    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinEntertainmentVideoIndexOperation>> getIndexQueryNodeOnNeoPinEntertainmentVideoIndexOperationHandler();

    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinGameImageIndexOperation>> getIndexQueryNodeOnNeoPinGameImageIndexOperationHandler();

    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinImageIndexOperation>> getIndexQueryNodeOnNeoPinImageIndexOperationHandler();

    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinIndexOperation>> getIndexQueryNodeOnNeoPinIndexOperationHandler();

    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinProductImageIndexOperation>> getIndexQueryNodeOnNeoPinProductImageIndexOperationHandler();

    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinUrlIndexOperation>> getIndexQueryNodeOnNeoPinUrlIndexOperationHandler();

    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinUrlHostIndexOperation>> getIndexQueryNodeOnNeoPinUrlHostIndexOperationHandler();

    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinYoutubeVideoIndexOperation>> getIndexQueryNodeOnNeoPinYoutubeVideoIndexOperationHandler();

    public Class<? extends OperationHandler<IndexQueryNodeOnNeoProductIndexOperation>> getIndexQueryNodeOnNeoProductIndexOperationHandler();

    public Class<? extends OperationHandler<IndexQueryNodeOnNeoRootIndexOperation>> getIndexQueryNodeOnNeoRootIndexOperationHandler();

    public Class<? extends OperationHandler<IndexQueryNodeOnNeoShippingCountryIndexOperation>> getIndexQueryNodeOnNeoShippingCountryIndexOperationHandler();

    public Class<? extends OperationHandler<IndexQueryNodeOnNeoSiteIndexOperation>> getIndexQueryNodeOnNeoSiteIndexOperationHandler();

    public Class<? extends OperationHandler<IndexQueryNodeOnOfferIndexOperation>> getIndexQueryNodeOnOfferIndexOperationHandler();

    public Class<? extends OperationHandler<IndexQueryNodeOnUserProfileIndexOperation>> getIndexQueryNodeOnUserProfileIndexOperationHandler();

    /*
     * Add Node to Index
     */

    public Class<? extends OperationHandler<AddNodeToNeoPinBoardIndexOperation>> getAddNodeToNeoPinBoardIndexOperationHandler();

    public Class<? extends OperationHandler<AddNodeToNeoPinIndexOperation>> getAddNodeToNeoPinIndexOperationHandler();

    public Class<? extends OperationHandler<AddNodeToNeoPinUrlHostIndexOperation>> getAddNodeToNeoPinUrlHostIndexOperationHandler();

    public Class<? extends OperationHandler<AddNodeToNeoPinUrlIndexOperation>> getAddNodeToNeoPinUrlIndexOperationHandler();

    public Class<? extends OperationHandler<AddNodeToNeoProductIndexOperation>> getAddNodeToNeoProductIndexOperationHandler();

    public Class<? extends OperationHandler<AddNodeToNeoSiteIndexOperation>> getAddNodeToNeoSiteIndexOperationHandler();

    /*
     * Delete Node from Index
     */

    public Class<? extends OperationHandler<DeleteNodeFromNeoPinBoardIndexOperation>> getDeleteNodeFromNeoPinBoardIndexOperationHandler();

    public Class<? extends OperationHandler<DeleteNodeFromNeoPinCommentIndexOperation>> getDeleteNodeFromNeoPinCommentIndexOperationHandler();

    public Class<? extends OperationHandler<DeleteNodeFromNeoPinIndexOperation>> getDeleteNodeFromNeoPinIndexOperationHandler();

    public Class<? extends OperationHandler<DeleteNodeFromNeoSiteIndexOperation>> getDeleteNodeFromNeoSiteIndexOperationHandler();
}
