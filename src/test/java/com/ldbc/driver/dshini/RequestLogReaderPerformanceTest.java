package com.ldbc.driver.dshini;

import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.log.RequestLogEntryReader;
import com.ldbc.driver.dshini.utils.DshiniLogs;
import com.ldbc.driver.dshini.utils.UrlPatterns;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.*;

@Ignore
public class RequestLogReaderPerformanceTest
{
    @Test
    public void performanceTest() throws MatchableException, RequestLogEntryException
    {
        // Given
        RequestLogEntryReader[] readers = DshiniLogs.allLogReaders();

        // When
        int entries = 0;
        long startTime = System.nanoTime();

        for ( RequestLogEntryReader reader : readers )
        {
            while ( reader.hasNext() )
            {
                RequestLogEntry entry = reader.next();
                entries++;
            }
        }

        long endTime = System.nanoTime();

        long runtime = ( endTime - startTime ) / 1000000000;
        System.out.println( String.format( "Runtime: %s seconds", runtime ) );
        System.out.println( String.format( "Entries: %s ", entries ) );
        System.out.println( String.format( "Throughput: %s (entries/second)", entries / runtime ) );

        // Then
        assertEquals( 13049991l, entries );
    }

    @Test
    public void countUniqueCypherQueries() throws MatchableException, RequestLogEntryException
    {
        // Given
        RequestLogEntryReader[] readers = DshiniLogs.allLogReaders();

        Set<String> cypherQueries = new HashSet<String>();

        // When
        for ( RequestLogEntryReader reader : readers )
        {
            while ( reader.hasNext() )
            {
                try
                {
                    RequestLogEntry entry = reader.next();
                    if ( UrlPatterns.CYPHER.matcher( entry.getUrl() ).matches() )
                    {
                        String cypherQueryString = (String) entry.getDescriptionAsMap().get( "query" );
                        cypherQueries.add( cypherQueryString );
                    }
                }
                catch ( RequestLogEntryException e )
                {
                }
            }
        }

        // Then
        System.out.println( cypherQueries.size() );
        System.out.println( cypherQueries.toString() );
        /*
        97 Unique Cypher Queries!
        
        START n=node({STARTIDS}) MATCH n<-[:OFFERS]-o-[:SHIPS_TO]->im<-[:SITUATED_IN*0..2]-sc WHERE   (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) AND (sc.CountryCode = {SCCOUNTRYCODE}) RETURN COUNT(distinct n), 
        START n=node({STARTIDS}) MATCH n<-[:PINS]-pin WHERE   (pin.CreatedAt<{PINCREATEDAT}) WITH pin, n ORDER BY pin.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT} MATCH pin-[:PINS_ASSET]->asset, pin-[:AUTHORED_BY]->author RETURN pin, asset, author, n as board, 
        START n=node({STARTIDS}) MATCH n<-[:COMMENTS_PIN]-comments-[:COMMENTED_BY]->author WHERE   (author.IsActive! = {AUTHORISACTIVE}) RETURN comments ORDER BY comments.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT}, 
        START user=node({USERID}), n=node({ENTITYID}) MATCH n<-[:SUBSCRIBES]-user RETURN user SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node({STARTIDS}) MATCH n-[:SITE_OWNED_BY]->other_nodes RETURN other_nodes, 
        START n=node:user_profile({USERQUERY}) MATCH n-[rel:SUBSCRIBES]->board-[:BOARD_SHOWN_ON*0..1]->sites WHERE   NOT(HAS(rel.Type)) WITH sites, rel WHERE (sites.IsDeleted? = {SITESISDELETED}) WITH sites, rel MATCH sites-[:SITE_OWNED_BY]->users WHERE (users.IsActive! = {USERSISACTIVE}) WITH users, MIN(rel.CreatedAt?) AS created_at RETURN COUNT(users), 
        START n=node({STARTIDS}) MATCH n<-[:SUB_CAT_OF*0..2]-c<-[:IN_CATEGORY]-p<-[:OFFERS]-o WHERE   (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) RETURN p ORDER BY o.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node({STARTIDS}) MATCH n-[:AUTHORED_BY]->other_nodes RETURN other_nodes, 
        START n=node({STARTIDS}) MATCH n<-[:SITE_OWNED_BY]-sites RETURN sites, 
        START pin=node({PINID}), user=node:user_profile(UserId={AUTHORID}), current=node({USERID}) MATCH pin<-[:COMMENTS_PIN]-comment-[:COMMENTED_BY]->author WHERE   (not(author<-[:TROLLS]-user) OR (author = current)) AND (author.IsActive! = {AUTHORISACTIVE}) RETURN distinct comment, author ORDER BY comment.CreatedAt DESC, 
        START n=node({STARTIDS}) MATCH n<-[:IS_SPOTLIGHT]-pins WHERE   (pins.CreatedAt<{PINSCREATEDAT}) RETURN pins ORDER BY pins.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node({STARTIDS}) MATCH n<-[:OFFERS]-o, n-[:IN_CATEGORY]->subcat-[:SUB_CAT_OF*0..2]->cat WHERE   (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) AND (cat.CategoryIdentifier = {CATCATEGORYIDENTIFIER}) RETURN COUNT(distinct n), 
        START n=node({STARTIDS}) MATCH n-[rel:SUBSCRIBES]->opt_board-[:BOARD_SHOWN_ON*0..1]->sites-[:SITE_OWNED_BY]->users WHERE   (rel.Type? is null) AND (users.IsActive! = {USERSISACTIVE}) AND NOT(n = {N}) RETURN DISTINCT users, 
        START user=node({USERID}), n=node({ENTITYID}) MATCH n-[rel:LIKED_BY]->user RETURN rel SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node({STARTIDS}) MATCH n<-[:SITE_OWNED_BY]-sites<-[:BOARD_SHOWN_ON*0..1]-board WHERE   (board.IsDeleted? = {BOARDISDELETED}) WITH board MATCH board<-[rel:SUBSCRIBES]-users WHERE NOT(HAS(rel.Type)) WITH board, users, rel WHERE (users.IsActive! = {USERSISACTIVE}) WITH users, MIN(rel.CreatedAt?) AS created_at RETURN users ORDER BY created_at DESC SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node({STARTIDS}) MATCH n<-[:SITE_OWNED_BY]-site WHERE   (site.Type = {SITETYPE}) RETURN site SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node({STARTIDS}) MATCH n-[:LIKED_BY]->other_nodes RETURN other_nodes ORDER BY other_nodes.CreatedAt! DESC, 
        START n=node({STARTIDS}) MATCH n-[:SITE_MODERATED_BY]->other_nodes RETURN other_nodes, 
        START n=node:neo_category(CategoryIdentifier={CATEGORYIDENTIFIER}) MATCH n<-[:SUB_CAT_OF]-sub_cats WHERE   (sub_cats.IsVisible? = {SUB_CATSISVISIBLE}) RETURN sub_cats ORDER BY sub_cats.Title, 
        START n=node({STARTIDS}) MATCH n-[:NEOPINBOARDS]->root_board<-[:SUB_BOARD_OF]-cat_boards RETURN cat_boards ORDER BY cat_boards.Title, 
        START n=node({STARTIDS}) MATCH n<-[:COMMENTS_PIN]-comments-[:COMMENTED_BY]->author WHERE   (author.IsActive! = {AUTHORISACTIVE}) RETURN comments ORDER BY comments.CreatedAt DESC, 
        START n=node({STARTIDS}) MATCH n-[:CLAIMED]->other_nodes RETURN other_nodes, 
        START n=node:neo_category(CategoryIdentifier={CATEGORYIDENTIFIER}) MATCH n<-[:SUB_CAT_OF]-sub WHERE   (sub.IsVisible? = {SUBISVISIBLE}) RETURN sub ORDER BY sub.Title, 
        START pin=node({PINID}), user=node({AUTHORID}), current=node({USERID}) MATCH pin<-[:COMMENTS_PIN]-comments-[:COMMENTED_BY]->author WHERE   (  not(author<-[:TROLLS]-user) OR (author = current)) AND (author.IsActive! = {AUTHORISACTIVE}) RETURN COUNT(*), 
        START n=node({STARTIDS}) MATCH n-[:PINS_ASSET]->other_nodes RETURN other_nodes, 
        START site=node({SITEID}), user=node:user_profile({QUERY}) WHERE   user-[:SUBSCRIBES]->site RETURN ID(user) SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node({STARTIDS}) MATCH n-[:IN_CATEGORY]->other_nodes RETURN other_nodes, 
        START n=node({STARTIDS}) MATCH n<-[:OFFERS]-o, n-[:IN_CATEGORY]->subcat-[:SUB_CAT_OF*0..2]->cat WHERE   (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) AND (cat.CategoryIdentifier = {CATCATEGORYIDENTIFIER}) RETURN distinct n SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node({STARTIDS}) MATCH n-[:CLAIMS]->other_nodes RETURN other_nodes, 
        START n=node({STARTIDS}) MATCH n<-[:OFFERED_BY]-o-[:OFFERS]->p WHERE   (o.IsRemoved = {OISREMOVED}) AND (o.IsSwaped = {OISSWAPED}) RETURN distinct o, 
        START n=node({STARTIDS}) MATCH n<-[:BOARD_SHOWN_ON]-boards RETURN boards ORDER BY boards.SortOrder?, 
        START n=node({STARTIDS}) MATCH n<-[:OFFERS]-o, n-[:IN_CATEGORY]->subcat-[:SUB_CAT_OF*0..2]->cat-[:SUB_CAT_OF]->root WHERE   (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) AND (root.CategoryIdentifier = {ROOTCATEGORYIDENTIFIER}) RETURN distinct cat, count(cat), 
        START n=node({STARTIDS}) MATCH n-[:IN_CATEGORY]->sc-[:SUB_CAT_OF*0..3]->cat-[:SUB_CAT_OF]->cat_root WHERE   (cat.IsVisible! = {CATISVISIBLE}) AND (cat_root.CategoryIdentifier = {CAT_ROOTCATEGORYIDENTIFIER}) RETURN cat SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node({STARTIDS}) MATCH n-[:REPINS*]->root-[r?:REPINS]->out WHERE   (r is null) RETURN root SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node:user_profile({USERQUERY}) MATCH n-[rel:SUBSCRIBES]->board-[:BOARD_SHOWN_ON*0..1]->sites WHERE   NOT(HAS(rel.Type)) WITH sites, rel WHERE (sites.IsDeleted? = {SITESISDELETED}) WITH sites, rel MATCH sites-[:SITE_OWNED_BY]->users WHERE (users.IsActive! = {USERSISACTIVE}) WITH users, MIN(rel.CreatedAt?) AS created_at RETURN users ORDER BY created_at DESC SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node({STARTIDS}) MATCH n<-[:COMMENTS_PIN]-comments-[:COMMENTED_BY]->authors RETURN distinct authors, 
        START n=node({STARTIDS}) MATCH n<-[:PINS]-pins RETURN COUNT(*), 
        START n=node({STARTIDS}) MATCH n-[:SITE_OWNED_BY]->owner, n<-[:BOARD_SHOWN_ON*0..1]-boards<-[rel:SUBSCRIBES]-users WHERE   (users.IsActive! = {USERSISACTIVE}) AND NOT(owner = users) RETURN COUNT(distinct users), 
        START n=node:neo_category(CategoryIdentifier={CATEGORYIDENTIFIER}) MATCH n<-[:SUB_CAT_OF*1..3]-subs WHERE   (subs.IsVisible? = {SUBSISVISIBLE}) RETURN distinct subs, 
        START n=node({STARTIDS}) MATCH n<-[:LIKED_BY]-likes RETURN COUNT(*), 
        START n=node({STARTIDS}) MATCH n-[:PINNED_VIA]->other_nodes RETURN other_nodes, 
        START n=node({STARTIDS}) MATCH n-[:NEOPINBOARDS]->board_root<-[:SUB_BOARD_OF]-cat_boards WHERE   (cat_boards.IsVisible? = {CAT_BOARDSISVISIBLE}) RETURN cat_boards ORDER BY cat_boards.SortOrder?, 
        START n=node({STARTIDS}) MATCH n<-[:OFFERED_BY]-o-[:OFFERS]->p-[:IN_CATEGORY]->sub_c-[:SUB_CAT_OF*0..3]->cat-[:SUB_CAT_OF]->cat_root WHERE   (o.IsRemoved = {OISREMOVED}) AND (o.IsSwaped = {OISSWAPED}) AND (cat_root.CategoryIdentifier = {CAT_ROOTCATEGORYIDENTIFIER}) AND (cat.CategoryIdentifier = {CATCATEGORYIDENTIFIER}) RETURN distinct o, 
        START n=node({STARTIDS}) MATCH n<-[:OFFERS]-o WHERE   (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) RETURN distinct n SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node({STARTIDS}) MATCH n<-[:CLAIMS]-other_nodes RETURN other_nodes, 
        START n=node({STARTIDS}) MATCH n<-[:SITE_OWNED_BY]-sites<-[:BOARD_SHOWN_ON]-boards RETURN boards ORDER BY boards.SortOrder?, 
        START cat_board=node({CAT}), n=node:user_profile({USER_QUERY}) MATCH cat_board<-[:SUB_BOARD_OF]-board WITH board, n MATCH board<-[:PINS]-pin-[:AUTHORED_BY]->author WHERE (pin.CreatedAt<{PINCREATEDAT}) WITH pin, author, board, n WHERE not(author<-[:TROLLS]-n) WITH pin, author, board, n ORDER BY pin.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT} MATCH pin-[:PINS_ASSET]->asset RETURN pin, author, board, asset, LENGTH(n<-[:LIKED_BY]-pin) > 0 as has_liked, 
        START n=node:neo_root(RootOf = {ROOT}), current=node:user_profile(UserId = {USERID}) MATCH n<-[:IS_SPOTLIGHT]-pin WHERE   (pin.CreatedAt<{PINCREATEDAT}) WITH pin, current ORDER BY pin.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT} MATCH pin-[:AUTHORED_BY]->author, pin-[:PINS]->board, pin-[:PINS_ASSET]->asset WHERE (not(author<-[:TROLLS]-current)) RETURN pin, author, board, asset, 
        START n=node({STARTIDS}) MATCH n<-[:OFFERS]-o WHERE   (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) RETURN COUNT(distinct n), 
        START n=node({STARTIDS}) MATCH n<-[:PINS_ASSET]-other_nodes RETURN other_nodes, 
        START n=node({STARTIDS}) MATCH n-[:SUB_CAT_OF]->other_nodes RETURN other_nodes, 
        START n=node({STARTIDS}) MATCH n<-[:SITE_OWNED_BY]-sites<-[:BOARD_SHOWN_ON]-boards RETURN boards, 
        START n=node({STARTIDS}) MATCH n<-[:OFFERS]-o-[:SHIPS_TO]->im<-[:SITUATED_IN*0..2]-sc, n-[:IN_CATEGORY]->subcat-[:SUB_CAT_OF*0..2]->cat WHERE   (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) AND (sc.CountryCode = {SCCOUNTRYCODE}) AND (cat.CategoryIdentifier = {CATCATEGORYIDENTIFIER}) RETURN COUNT(distinct n), 
        START n=node({STARTIDS}) MATCH n-[subscription:SUBSCRIBES]->site<-[:BOARD_SHOWN_ON*0..1]-board WHERE   NOT(HAS(subscription.Type)) WITH board, n MATCH board<-[:PINS]-pin WHERE (pin.CreatedAt<{PINCREATEDAT}) WITH pin, board, n MATCH pin-[:AUTHORED_BY]->author WHERE NOT(author<-[:TROLLS]-n) WITH pin, author, board, n ORDER BY pin.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT} MATCH pin-[:PINS_ASSET]->asset RETURN pin, author, board, asset, LENGTH(n<-[:LIKED_BY]-pin) > 0 as has_liked, 
        START n=node({STARTIDS}) MATCH n-[:COMMENTED_BY]->other_nodes RETURN other_nodes, 
        START n=node({STARTIDS}) MATCH n<-[:PINS]-pins-[:PINS_ASSET]->assets RETURN pins, assets ORDER BY pins.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node({STARTIDS}) MATCH n-[:LIKED_BY]->likes RETURN COUNT(*), 
        START user=node({USERID}), n=node({ENTITYID}) MATCH n<-[rel:SUBSCRIBES]-user RETURN rel SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node({STARTIDS}) MATCH n<-[:OFFERS]-o-[:SHIPS_TO]->im<-[:SITUATED_IN*0..2]-sc WHERE   (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) AND (sc.CountryCode = {SCCOUNTRYCODE}) RETURN distinct n SKIP {QSKIP} LIMIT {QLIMIT}, 
        START cat_board=node({CAT}), n=node(0) MATCH cat_board<-[:SUB_BOARD_OF]-board WITH board, n MATCH board<-[:PINS]-pin-[:AUTHORED_BY]->author WHERE (pin.CreatedAt<{PINCREATEDAT}) WITH pin, author, board, n WHERE not(author<-[:TROLLS]-n) WITH pin, author, board, n ORDER BY pin.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT} MATCH pin-[:PINS_ASSET]->asset RETURN pin, author, board, asset, false as has_liked, 
        START n=node({STARTIDS}) MATCH n<-[:PINS]-pins RETURN pins ORDER BY pins.CreatedAt DESC, 
        START n=node({STARTIDS}) MATCH n<-[:PIN_REFERENCES_URL]-pins RETURN COUNT(*), 
        START n=node({STARTIDS}) MATCH n<-[:OFFERED_BY]-o-[:OFFERS]->p-[:IN_CATEGORY]->sub_c-[:SUB_CAT_OF*0..3]->cat-[:SUB_CAT_OF]->cat_root WHERE   (cat.IsVisible? = {CATISVISIBLE}) AND (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) AND (cat_root.CategoryIdentifier = {CAT_ROOTCATEGORYIDENTIFIER}) RETURN distinct cat, count(o), 
        START site=node({SITE_ID}), n=node(0) MATCH site<-[:BOARD_SHOWN_ON]-board WITH board, n MATCH board<-[:PINS]-pin-[:AUTHORED_BY]->author WHERE (pin.CreatedAt<{PINCREATEDAT}) WITH pin, author, board, n WHERE not(author<-[:TROLLS]-n) WITH pin, author, board, n ORDER BY pin.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT} MATCH pin-[:PINS_ASSET]->asset RETURN pin, author, board, asset, false as has_liked, 
        START n=node({STARTIDS}) MATCH n<-[:SUB_CAT_OF*0..2]-c<-[:IN_CATEGORY]-p<-[:OFFERS]-o WHERE   (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) RETURN COUNT(p), 
        START n=node({STARTIDS}) MATCH n-[rel:SUBSCRIBES]->opt_board-[:BOARD_SHOWN_ON*0..1]->sites-[:SITE_OWNED_BY]->users WHERE   (rel.Type? is null) AND (users.IsActive! = {USERSISACTIVE}) AND NOT(n = {N}) RETURN COUNT(DISTINCT users), 
        START n=node({BOARD_ID}), user=node({USER_ID}) MATCH n-[:BOARD_SHOWN_ON*0..1]->site<-[rel:SUBSCRIBES]-users WHERE   (users.IsActive! = {USERSISACTIVE}) AND NOT(user = users) RETURN users ORDER BY rel.CreatedAt! DESC, 
        START site=node({SITE_ID}), n=node:user_profile({USER_QUERY}) MATCH site<-[:BOARD_SHOWN_ON]-board WITH board, n MATCH board<-[:PINS]-pin-[:AUTHORED_BY]->author WHERE (pin.CreatedAt<{PINCREATEDAT}) WITH pin, author, board, n WHERE not(author<-[:TROLLS]-n) WITH pin, author, board, n ORDER BY pin.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT} MATCH pin-[:PINS_ASSET]->asset RETURN pin, author, board, asset, LENGTH(n<-[:LIKED_BY]-pin) > 0 as has_liked, 
        START n=node({STARTIDS}) MATCH n-[:URL_OF_HOST]->other_nodes RETURN other_nodes, 
        START n=node({STARTIDS}) MATCH n<-[:OFFERS]-o-[:SHIPS_TO]->im<-[:SITUATED_IN*0..2]-sc, n-[:IN_CATEGORY]->subcat-[:SUB_CAT_OF*0..2]->cat WHERE   (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) AND (sc.CountryCode = {SCCOUNTRYCODE}) AND (cat.CategoryIdentifier = {CATCATEGORYIDENTIFIER}) RETURN distinct n SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node({STARTIDS}) MATCH n-[:REPINS]->other_nodes RETURN other_nodes, 
        START n=node({STARTIDS}) MATCH n-[rel:LIKED_BY]->user RETURN user ORDER BY id(rel) DESC SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node({STARTIDS}) MATCH n-[:PINS]->other_nodes RETURN other_nodes, 
        START n=node({STARTIDS}) MATCH n-[:SUBSCRIBES]->site<-[:BOARD_SHOWN_ON*0..1]-board WITH board, n MATCH board<-[:PINS]-pin-[:AUTHORED_BY]->author WHERE (pin.CreatedAt<{PINCREATEDAT}) WITH pin, author, board, n WHERE not(author<-[:TROLLS]-n) WITH pin, author, board, n ORDER BY pin.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT} MATCH pin-[:PINS_ASSET]->asset RETURN pin, author, board, asset, LENGTH(n<-[:LIKED_BY]-pin) > 0 as has_liked, 
        START n=node({ENTITYID}), user=node:user_profile(UserId={USERID}) WHERE   n-[:LIKED_BY]->user RETURN id(n) SKIP {QSKIP} LIMIT {QLIMIT}, 
        START board=node({BOARDID}), user=node:user_profile({QUERY}) WHERE   user-[:SUBSCRIBES]->board RETURN user SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node({STARTIDS}) MATCH n<-[:BOARD_SHOWN_ON]-boards<-[:PINS]-pins-[:AUTHORED_BY]->author WHERE   (author.UserId is null) RETURN COUNT(*), 
        START n=node({STARTIDS}) MATCH n<-[:CLAIMED]-other_nodes RETURN other_nodes, 
        START n=node({STARTIDS}) MATCH n<-[:OFFERED_BY]-o-[:OFFERS]->p-[:IN_CATEGORY]->sub_c-[:SUB_CAT_OF*0..3]->cat-[:SUB_CAT_OF]->cat_root WHERE   (cat.IsVisible? = {CATISVISIBLE}) AND (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) AND (cat_root.CategoryIdentifier = {CAT_ROOTCATEGORYIDENTIFIER}) AND (cat.CategoryIdentifier = {CATCATEGORYIDENTIFIER}) RETURN distinct o, 
        START n=node({STARTIDS}) MATCH n-[:BOARD_SHOWN_ON]->other_nodes RETURN other_nodes, 
        START n=node({STARTIDS}) MATCH n<-[:SITE_OWNED_BY]-sites<-[:BOARD_SHOWN_ON*0..1]-board WHERE   (board.IsDeleted? = {BOARDISDELETED}) WITH board MATCH board<-[rel:SUBSCRIBES]-users WHERE NOT(HAS(rel.Type)) WITH board, users, rel WHERE (users.IsActive! = {USERSISACTIVE}) WITH users, MIN(rel.CreatedAt?) AS created_at RETURN COUNT(users), 
        START n=node({STARTIDS}) MATCH n<-[:COMMENTS_PIN]-comments-[:COMMENTED_BY]->author WHERE   (author.IsActive! = {AUTHORISACTIVE}) RETURN COUNT(comments), 
        START n=node({STARTIDS}) MATCH n-[:ROOT]->other_nodes RETURN other_nodes, 
        START n=node({STARTIDS}) MATCH n-[:SITE_OWNED_BY]->owner<-[:LIKED_BY]-pins WHERE   (pins.CreatedAt<{PINSCREATEDAT}) RETURN pins ORDER BY pins.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node({STARTIDS}) MATCH n<-[:PINS]-pin WHERE   (pin.CreatedAt>{PINCREATEDAT}) RETURN COUNT(pin), 
        START n=node({STARTIDS}) MATCH n<-[:OFFERS]-o-[:SHIPS_TO]->im<-[:SITUATED_IN*0..2]-sc, n-[:IN_CATEGORY]->subcat-[:SUB_CAT_OF*0..2]->cat-[:SUB_CAT_OF]->root WHERE   (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) AND (root.CategoryIdentifier = {ROOTCATEGORYIDENTIFIER}) AND (sc.CountryCode = {SCCOUNTRYCODE}) RETURN distinct cat, count(cat), 
        START pin=node({PINID}), user=node({AUTHORID}), current=node({USERID}) MATCH pin<-[:COMMENTS_PIN]-comments-[:COMMENTED_BY]->author WHERE   (  not(author<-[:TROLLS]-user) OR (author = current)) AND (author.IsActive! = {AUTHORISACTIVE}) RETURN comments, author ORDER BY comments.CreatedAt DESC SKIP {QSKIP} LIMIT {QLIMIT}, 
        START n=node({STARTIDS}) MATCH n-[:COMMENTS_PIN]->other_nodes RETURN other_nodes, 
        START n=node({STARTIDS}) MATCH n<-[:OFFERED_BY]-o-[:OFFERS]->p-[:IN_CATEGORY]->sub_c-[:SUB_CAT_OF*0..3]->cat-[:SUB_CAT_OF]->cat_root WHERE   (cat.IsVisible? = {CATISVISIBLE}) AND (o.IsInStock = {OISINSTOCK}) AND (o.IsExpired = {OISEXPIRED}) AND (o.IsRemoved = {OISREMOVED}) AND (o.IsFreezedByUser = {OISFREEZEDBYUSER}) AND (o.IsSwaped = {OISSWAPED}) AND (cat_root.CategoryIdentifier = {CAT_ROOTCATEGORYIDENTIFIER}) RETURN distinct o, 
        START n=node({STARTIDS}) MATCH n-[:SUBSCRIBES]->other_nodes RETURN other_nodes, 
        START n=node({STARTIDS}) MATCH n-[:PIN_REFERENCES_URL]->other_nodes RETURN other_nodes, 
        START n=node({STARTIDS}) MATCH n<-[:REPINS]-other_nodes RETURN other_nodes ORDER BY other_nodes.CreatedAt DESC, 
        START n=node({BOARD_ID}), user=node({USER_ID}) MATCH n-[:BOARD_SHOWN_ON*0..1]->site<-[rel:SUBSCRIBES]-users WHERE   (users.IsActive! = {USERSISACTIVE}) AND NOT(user = users) RETURN COUNT(users), 
        START n=node({STARTIDS}) MATCH n<-[:BOARD_SHOWN_ON]-boards<-[:PINS]-pins-[:AUTHORED_BY]->author WHERE   (author.UserId = {AUTHORUSERID}) RETURN COUNT(*), 
        START n=node({STARTIDS}) MATCH n<-[:PINS]-pin RETURN COUNT(pin), 
        START n=node({STARTIDS}) MATCH n-[:SUB_BOARD_OF]->other_nodes RETURN other_nodes, 
        START n=node({STARTIDS}) MATCH n<-[:REPINS]-repin RETURN COUNT(*)]
         */
    }
}
