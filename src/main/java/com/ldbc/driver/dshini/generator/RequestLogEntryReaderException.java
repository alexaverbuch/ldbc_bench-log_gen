/**                                                                                                                                                                                
 * Copyright (c) 2010 Yahoo! Inc. All rights reserved.                                                                                                                             
 *                                                                                                                                                                                 
 * Licensed under the Apache License, Version 2.0 (the "License"); you                                                                                                             
 * may not use this file except in compliance with the License. You                                                                                                                
 * may obtain a copy of the License at                                                                                                                                             
 *                                                                                                                                                                                 
 * http://www.apache.org/licenses/LICENSE-2.0                                                                                                                                      
 *                                                                                                                                                                                 
 * Unless required by applicable law or agreed to in writing, software                                                                                                             
 * distributed under the License is distributed on an "AS IS" BASIS,                                                                                                               
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or                                                                                                                 
 * implied. See the License for the specific language governing                                                                                                                    
 * permissions and limitations under the License. See accompanying                                                                                                                 
 * LICENSE file.                                                                                                                                                                   
 */

package com.ldbc.driver.dshini.generator;

/**
 * Something bad happened while interacting with the database.
 */
public class RequestLogEntryReaderException extends RuntimeException
{
    private static final long serialVersionUID = 6646883591588721475L;

    public RequestLogEntryReaderException( String message )
    {
        super( message );
    }

    public RequestLogEntryReaderException()
    {
        super();
    }

    public RequestLogEntryReaderException( String message, Throwable cause )
    {
        super( message, cause );
    }

    public RequestLogEntryReaderException( Throwable cause )
    {
        super( cause );
    }
}
