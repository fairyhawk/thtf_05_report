/**
 * ClassName  DownloadMsg
 *
 * History
 * Create User: Lubo
 * Create Date: 2011-1-18
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.util;

import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.util.DownloadController.FILE_STATUS;
import cn.com.thtf.cms.report.util.DownloadController.OPERATION_STATUS;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DownloadMsg
 * 
 * @author Lubo
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DownloadParam {

    /** IP */
    private String ip;
    /** nodeKey */
    private String nodeKey;
    /** nodeId */
    private String nodeId;
    /** user */
    private UserEntity user;
    /** status */
    private FILE_STATUS status;
    /** type */
    private OPERATION_STATUS type;
    /** requestType */
    private String requestType;

}
