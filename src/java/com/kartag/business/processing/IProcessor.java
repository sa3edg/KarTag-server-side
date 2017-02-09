package com.kartag.business.processing;

import com.kartag.business.common.Request;
import com.kartag.business.common.Response;

/**
 * Interface of Processors, i.e. the classes that handle an incoming XML
 * request.
 *
 */
public interface IProcessor
{
   /**
    * Performs the actual (synchronous) processing of the request.
    * @return the result, in most cases a {@link org.jdom.Document}.
    */
   Response process();

   /**
    * Performs preprocessing steps before the actual processing.
    *
    */
   void preprocess();
  
   
   /**
    * Supplies the processor with necessary data.
    * @param request the incoming request
    */
   Request getRequest();
   
   /**
    * Supplies the processor with necessary data.
    * @param request the incoming request
    */
   void setRequest(Request request);

   /**
    * The return value indicate if at the present time (asynchronous) responses
    * are expected.
    * 
    * @return a boolean indicating if more responses are expected.
    */
   boolean isResponseExpected();
   
   /**
    * Shuts down the processor, i.e. close any connections etc.
    */
   void terminate();
  
}
