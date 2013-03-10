/**
 * ClassName  DownloadResult
 *
 * History
 * Create User: Lubo
 * Create Date: 2011-1-18
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.util;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DownloadResult
 * 
 * @author Lubo
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DownloadResult {

    /** 返回结果 */
    private boolean result;
    /** 返回结果类型 1:转换中 2:系统繁忙*/
    private int resultType;
    /** 返回信息 */
    private String resultMsg;

}
