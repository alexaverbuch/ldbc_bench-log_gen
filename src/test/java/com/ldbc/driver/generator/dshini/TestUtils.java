package com.ldbc.driver.generator.dshini;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class TestUtils
{
    public static File getResource( String path )
    {
        return FileUtils.toFile( TestUtils.class.getResource( path ) );
    }
}
