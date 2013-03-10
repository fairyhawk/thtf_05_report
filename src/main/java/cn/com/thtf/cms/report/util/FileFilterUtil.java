/**
 * ClassName  FileFilterEndType
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-11-18
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.util;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 
 * @author liuqg
 */

public class FileFilterUtil implements FilenameFilter {

    /**
     * 匹配的正则
     */
    private String regex = "";

    public FileFilterUtil(String regex) {
        this.regex = regex;
    }
 
    @Override
    public boolean accept(File dir, String name) {
        
        return name.matches(regex);
    }

}
