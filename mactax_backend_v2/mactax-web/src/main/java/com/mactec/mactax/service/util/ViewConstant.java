package com.mactec.mactax.service.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 
 * @author akshaylap
 *
 */
public class ViewConstant {

    protected final Log logger = LogFactory.getLog(getClass());

    public static final String SAVE_LOCATION = System.getProperty("catalina.base") + "/MACTAX-IND";

    public static final String SAVE_ORDER_DOCS = SAVE_LOCATION + "/order-docs";

}
