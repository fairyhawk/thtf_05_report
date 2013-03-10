/**
 * ClassName  DownloadController
 *
 * History
 * Create User: Lubo
 * Create Date: 2011-1-18
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.cms.report.constant.Constants;

/**
 * 下载控制
 * 
 * @author Lubo
 */
public class DownloadController {

    /** log */
    private static final Logger log = LoggerFactory.getLogger(DownloadController.class);
    /** 当前同时下载数量 */
    private int downloadSize = 0;
    /** 文档的状态 */
    private Map<String, FILE_STATUS> fileMap = new HashMap<String, FILE_STATUS>();
    /** DownloadController */
    private static DownloadController me = null;
    /** ThreadPoolExecutor */
    private static ThreadPoolExecutor threadPool = null;

    /** 文件类型 */
    public static enum FILE_STATUS {
        /** 转换中 */
        ONGOING,
        /** 可下载 */
        COMPLETE,
        /** 不存在 */
        NONE
    };

    /** 操作类型 */
    public static enum OPERATION_STATUS {
        /** 申请下载 */
        APP,
        /** 申请修改状态 */
        CHANGESTATUS,
        /** 生成完毕 */
        UNAPP
    };

    /**
     * 
     * @param param
     * @return
     */
    public synchronized DownloadResult app(DownloadParam param) {
        DownloadResult result = new DownloadResult();

        log.debug(
                "DownloadSize:{},NodeId:{},FileName:{},Type:{},Status:{},MapStatus:{},RequestType:{}",
                new Object[] { downloadSize, param.getNodeId(), param.getNodeKey(),
                        param.getType(), param.getStatus(),
                        getFileVal(param.getNodeKey()), param.getRequestType() });

        if (param.getType() == OPERATION_STATUS.APP) {
            if (getFileVal(param.getNodeKey()) != null
                    && checkFileExist(param.getNodeKey())
                    && getFileVal(param.getNodeKey()) != FILE_STATUS.NONE) {
                if (getFileVal(param.getNodeKey()) == FILE_STATUS.ONGOING) {
                    // 转换中
                    log.debug("特殊标识BBBBBVVVVV", param.getNodeId(), param.getNodeKey());// 如果正式上线后未出现过此LOG,即可删掉此if
                    log.debug("NodeId:{},FileName:{},转换中", param.getNodeId(),
                            param.getNodeKey());
                    result.setResult(false);
                    result.setResultType(1);
                } else {
                    // 可直接下载
                    log.debug("NodeId:{},FileName:{},可下载", param.getNodeId(),
                            param.getNodeKey());
                    result.setResult(true);
                }
            } else {
                if (getFileVal(param.getNodeKey()) == FILE_STATUS.ONGOING) {
                    // 转换中
                    log.debug("NodeId:{},FileName:{},转换中", param.getNodeId(),
                            param.getNodeKey());
                    result.setResult(false);
                    result.setResultType(1);
                } else {
                    /* 如果是重试操作,并且文件状态是NONE,那么清楚NONE状态 */
                    if ("1".equals(param.getRequestType())
                            && getFileVal(param.getNodeKey()) == FILE_STATUS.NONE) {
                        putFileVal(param.getNodeKey(), null);
                    }

                    if (getFileVal(param.getNodeKey()) != FILE_STATUS.NONE) {
                        // 不可下载,不存在
                        if (downloadSize < Constants.TRANSCSV_MAXCOUNT) {
                            log.debug("NodeId:{},FileName:{},不存在,转换中", param.getNodeId(),
                                    param.getNodeKey());

                            putFileVal(param.getNodeKey(), FILE_STATUS.ONGOING);
                            threadPool.execute(new TransCSVTask(param.getNodeId(), param
                                    .getNodeKey(), param.getUser()));
                            downloadSize++;
                            result.setResult(false);
                            result.setResultType(1);
                        } else {
                            log.debug("NodeId:{},FileName:{},不存在,系统繁忙",
                                    param.getNodeId(), param.getNodeKey());
                            result.setResult(false);
                            result.setResultType(2);
                        }
                    } else {
                        log.debug("NodeId:{},FileName:{},生成错误", param.getNodeId(),
                                param.getNodeKey());
                        result.setResult(false);
                        result.setResultType(8);
                    }

                }
            }
        } else if (param.getType() == OPERATION_STATUS.CHANGESTATUS) {
            putFileVal(param.getNodeKey(), param.getStatus());
            downloadSize--;
        } else if (param.getType() == OPERATION_STATUS.UNAPP) {
            putFileVal(param.getNodeKey(), FILE_STATUS.COMPLETE);
            downloadSize--;
        }
        return result;
    }

    /**
     * checkFileExist
     * 
     * @param fileName
     * @return
     */
    private boolean checkFileExist(String fileName) {
        File file = new File(Constants.CSVTMPFILEPATH + fileName);
        return file.exists();
    }

    /**
     * 
     * @param key
     * @param value
     */
    private void putFileVal(String key, FILE_STATUS value) {
        // Cache downloadCache = Container.getBean("downloadCache",
        // Cache.class);
        // downloadCache.put(new Element(key, value));
        fileMap.put(key, value);
    }

    /**
     * 
     * @param key
     * @return
     */
    private FILE_STATUS getFileVal(String key) {
        // Cache downloadCache = Container.getBean("downloadCache",
        // Cache.class);
        // Element elmt = downloadCache.get(key);
        // if (elmt == null) {
        // return null;
        // } else {
        // return (FILE_STATUS) elmt.getObjectValue();
        // }
        return fileMap.get(key);
    }

    /**
     * 单体实例
     * 
     * @return
     */
    public synchronized static DownloadController getInstance() {
        if (me == null) {
            me = new DownloadController();
            threadPool = new ThreadPoolExecutor(2, 10, 60, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(3),
                    new ThreadPoolExecutor.CallerRunsPolicy());
        }
        return me;
    }

    /**
     * 
     */
    private DownloadController() {
    }

}
