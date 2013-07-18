package com.ldbc.driver.dshini.utils;

import java.util.regex.Pattern;

public class UrlPatterns
{
    public final static Pattern CYPHER = Pattern.compile( ".*db/data/cypher$" );
    public final static Pattern ADD_NODE_TO_INDEX = Pattern.compile( ".*db/data/index/node/[[^/]\\w]*$" );
    public final static Pattern DELETE_NODE_FROM_INDEX = Pattern.compile( ".*db/data/index/node/[[^/]\\w]*/\\d*$" );
    public final static Pattern INDEX_QUERY_NODE = Pattern.compile( ".*db/data/index/node/.*\\?query=.*" );
    public final static Pattern GET_NODE = Pattern.compile( ".*db/data/node/\\d*$" );
    public final static Pattern GET_NODE_OUT_RELATIONSHIPS = Pattern.compile( ".*db/data/node/\\d*/relationships/out$" );
    public final static Pattern DELETE_NODE = Pattern.compile( ".*db/data/node/\\d*$" );
    public final static Pattern GET_NODE_RELATIONSHIPS = Pattern.compile( ".*db/data/node/\\d*/relationships/all$" );
    public final static Pattern GET_RELATIONSHIP = Pattern.compile( ".*db/data/relationship/\\d*$" );
    public final static Pattern DELETE_RELATIONSHIP = Pattern.compile( ".*db/data/relationship/\\d*$" );
    public final static Pattern GET_NODE_TYPED_IN_RELATIONSHIPS = Pattern.compile( ".*db/data/node/\\d*/relationships/in/[&\\w]*$" );
    public final static Pattern GET_NODE_TYPED_OUT_RELATIONSHIPS = Pattern.compile( ".*db/data/node/\\d*/relationships/out/[&\\w]*$" );
    public final static Pattern CREATE_NODE = Pattern.compile( ".*db/data/node$" );
    public final static Pattern UPDATE_NODE = Pattern.compile( ".*db/data/node/\\d*/properties$" );
    public final static Pattern CREATE_RELATIONSHIP = Pattern.compile( ".*db/data/node/\\d*/relationships" );

}
