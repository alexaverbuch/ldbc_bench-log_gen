package com.ldbc.driver.dshini.db.neo4j.emdedded;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.AddNodeToNeoPinBoardIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.AddNodeToNeoPinIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.AddNodeToNeoPinUrlHostIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.AddNodeToNeoPinUrlIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.AddNodeToNeoProductIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.AddNodeToNeoSiteIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.DeleteNodeFromNeoPinBoardIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.DeleteNodeFromNeoPinCommentIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.DeleteNodeFromNeoPinIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.DeleteNodeFromNeoSiteIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.IndexQueryNodeOnNeoCategoryIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.IndexQueryNodeOnNeoPinApplicationIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.IndexQueryNodeOnNeoPinBoardIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.IndexQueryNodeOnNeoPinCommentIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.IndexQueryNodeOnNeoPinEntertainmentVideoIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.IndexQueryNodeOnNeoPinGameImageIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.IndexQueryNodeOnNeoPinImageIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.IndexQueryNodeOnNeoPinIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.IndexQueryNodeOnNeoPinProductImageIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.IndexQueryNodeOnNeoPinUrlHostIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.IndexQueryNodeOnNeoPinUrlIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.IndexQueryNodeOnNeoPinYoutubeVideoIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.IndexQueryNodeOnNeoProductIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.IndexQueryNodeOnNeoRootIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.IndexQueryNodeOnNeoShippingCountryIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.IndexQueryNodeOnNeoSiteIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.IndexQueryNodeOnOfferIndexOperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index.IndexQueryNodeOnUserProfileIndexOperationHandler;
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
import com.ldbc.driver.dshini.workloads.DshiniIndexCommands;

public class Neo4jDshiniIndexCommandsEmbedded extends DshiniIndexCommands
{
    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnNeoCategoryIndexOperation>> getIndexQueryNodeOnNeoCategoryIndexOperationHandler()
    {
        return IndexQueryNodeOnNeoCategoryIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinApplicationIndexOperation>> getIndexQueryNodeOnNeoPinApplicationIndexOperationHandler()
    {
        return IndexQueryNodeOnNeoPinApplicationIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinBoardIndexOperation>> getIndexQueryNodeOnNeoPinBoardIndexOperationHandler()
    {
        return IndexQueryNodeOnNeoPinBoardIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinCommentIndexOperation>> getIndexQueryNodeOnNeoPinCommentIndexOperationHandler()
    {
        return IndexQueryNodeOnNeoPinCommentIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinEntertainmentVideoIndexOperation>> getIndexQueryNodeOnNeoPinEntertainmentVideoIndexOperationHandler()
    {
        return IndexQueryNodeOnNeoPinEntertainmentVideoIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinGameImageIndexOperation>> getIndexQueryNodeOnNeoPinGameImageIndexOperationHandler()
    {
        return IndexQueryNodeOnNeoPinGameImageIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinImageIndexOperation>> getIndexQueryNodeOnNeoPinImageIndexOperationHandler()
    {
        return IndexQueryNodeOnNeoPinImageIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinIndexOperation>> getIndexQueryNodeOnNeoPinIndexOperationHandler()
    {
        return IndexQueryNodeOnNeoPinIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinProductImageIndexOperation>> getIndexQueryNodeOnNeoPinProductImageIndexOperationHandler()
    {
        return IndexQueryNodeOnNeoPinProductImageIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinUrlIndexOperation>> getIndexQueryNodeOnNeoPinUrlIndexOperationHandler()
    {
        return IndexQueryNodeOnNeoPinUrlIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinUrlHostIndexOperation>> getIndexQueryNodeOnNeoPinUrlHostIndexOperationHandler()
    {
        return IndexQueryNodeOnNeoPinUrlHostIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnNeoPinYoutubeVideoIndexOperation>> getIndexQueryNodeOnNeoPinYoutubeVideoIndexOperationHandler()
    {
        return IndexQueryNodeOnNeoPinYoutubeVideoIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnNeoProductIndexOperation>> getIndexQueryNodeOnNeoProductIndexOperationHandler()
    {
        return IndexQueryNodeOnNeoProductIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnNeoRootIndexOperation>> getIndexQueryNodeOnNeoRootIndexOperationHandler()
    {
        return IndexQueryNodeOnNeoRootIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnNeoShippingCountryIndexOperation>> getIndexQueryNodeOnNeoShippingCountryIndexOperationHandler()
    {
        return IndexQueryNodeOnNeoShippingCountryIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnNeoSiteIndexOperation>> getIndexQueryNodeOnNeoSiteIndexOperationHandler()
    {
        return IndexQueryNodeOnNeoSiteIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnOfferIndexOperation>> getIndexQueryNodeOnOfferIndexOperationHandler()
    {
        return IndexQueryNodeOnOfferIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<IndexQueryNodeOnUserProfileIndexOperation>> getIndexQueryNodeOnUserProfileIndexOperationHandler()
    {
        return IndexQueryNodeOnUserProfileIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<AddNodeToNeoPinBoardIndexOperation>> getAddNodeToNeoPinBoardIndexOperationHandler()
    {
        return AddNodeToNeoPinBoardIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<AddNodeToNeoPinIndexOperation>> getAddNodeToNeoPinIndexOperationHandler()
    {
        return AddNodeToNeoPinIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<AddNodeToNeoPinUrlHostIndexOperation>> getAddNodeToNeoPinUrlHostIndexOperationHandler()
    {
        return AddNodeToNeoPinUrlHostIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<AddNodeToNeoPinUrlIndexOperation>> getAddNodeToNeoPinUrlIndexOperationHandler()
    {
        return AddNodeToNeoPinUrlIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<AddNodeToNeoProductIndexOperation>> getAddNodeToNeoProductIndexOperationHandler()
    {
        return AddNodeToNeoProductIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<AddNodeToNeoSiteIndexOperation>> getAddNodeToNeoSiteIndexOperationHandler()
    {
        return AddNodeToNeoSiteIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<DeleteNodeFromNeoPinBoardIndexOperation>> getDeleteNodeFromNeoPinBoardIndexOperationHandler()
    {
        return DeleteNodeFromNeoPinBoardIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<DeleteNodeFromNeoPinCommentIndexOperation>> getDeleteNodeFromNeoPinCommentIndexOperationHandler()
    {
        return DeleteNodeFromNeoPinCommentIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<DeleteNodeFromNeoPinIndexOperation>> getDeleteNodeFromNeoPinIndexOperationHandler()
    {
        return DeleteNodeFromNeoPinIndexOperationHandler.class;
    }

    @Override
    public Class<? extends OperationHandler<DeleteNodeFromNeoSiteIndexOperation>> getDeleteNodeFromNeoSiteIndexOperationHandler()
    {
        return DeleteNodeFromNeoSiteIndexOperationHandler.class;
    }
}
