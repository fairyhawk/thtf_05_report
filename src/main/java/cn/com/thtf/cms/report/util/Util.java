/**
 * ClassName  Util
 *
 * History
 * Create User: Lubo
 * Create Date: 2011-2-22
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Util
 * 
 * @author Lubo
 */
public class Util {

    /** log */
    private static final Logger log = LoggerFactory.getLogger(Util.class);

    /**
     * encodeUrl
     * 
     * @param url
     * @return
     */
    public static String encodeUrl(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("encodeUrl错误:", e);
            return null;
        }
    }

}
