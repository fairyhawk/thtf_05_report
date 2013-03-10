/**
 * ClassName  ThreadPoolTask
 *
 * History
 * Create User: Lubo
 * Create Date: 2011-1-19
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import cn.com.thtf.cms.report.action.data.InstockDetailActionBean;
import cn.com.thtf.cms.report.action.data.RBuyContractDetailActionBean;
import cn.com.thtf.cms.report.action.data.RDynamicStocktakeActionBean;
import cn.com.thtf.cms.report.action.data.RSendGoodsDetailActionBean;
import cn.com.thtf.cms.report.action.data.RTransportFareDetailActionBean;
import cn.com.thtf.cms.report.action.data.SellContractDetailActionBean;
import cn.com.thtf.cms.report.action.data.StockProductDetailActionBean;
import cn.com.thtf.cms.report.action.data.TransportFareSharingActionBean;
import cn.com.thtf.cms.report.constant.Constants;
import cn.com.thtf.cms.report.convert.CSVConvertUtil;
import cn.com.thtf.cms.report.convert.RBuyContractDetailConvertHelper;
import cn.com.thtf.cms.report.convert.RDynamicStockTakeConvertHelper;
import cn.com.thtf.cms.report.convert.RInstockDetailConvertHelper;
import cn.com.thtf.cms.report.convert.RInstockDetailRole13ConvertHelper;
import cn.com.thtf.cms.report.convert.RSellContractDetailConvertHelper;
import cn.com.thtf.cms.report.convert.RSendGoodsDetailConvertHelper;
import cn.com.thtf.cms.report.convert.RSendGoodsDetailRole13ConvertHelper;
import cn.com.thtf.cms.report.convert.RSendGoodsDetailRole3ConvertHelper;
import cn.com.thtf.cms.report.convert.RStockProductDetailConvertHelper;
import cn.com.thtf.cms.report.convert.RStockProductDetailOtherConvertHelper;
import cn.com.thtf.cms.report.convert.RTransportFareDetailConvertHelper;
import cn.com.thtf.cms.report.convert.RTransportFareSharingConvertHelper;
import cn.com.thtf.cms.report.dao.InstockDetailDao;
import cn.com.thtf.cms.report.dao.RBuyContractDetailDao;
import cn.com.thtf.cms.report.dao.RDynamicStocktakeDao;
import cn.com.thtf.cms.report.dao.RSellContractDetailDao;
import cn.com.thtf.cms.report.dao.RSendGoodsDetailDao;
import cn.com.thtf.cms.report.dao.RTransportFareDetailDao;
import cn.com.thtf.cms.report.dao.StockProductDetailDao;
import cn.com.thtf.cms.report.dao.TransportFareSharingDao;
import cn.com.thtf.cms.report.dto.BaseQueryData;
import cn.com.thtf.cms.report.dto.RSellContractDetailDto;
import cn.com.thtf.cms.report.dto.RSendGoodsDetailDto;
import cn.com.thtf.cms.report.dto.RTransportFareDetailDto;
import cn.com.thtf.cms.report.entity.InstockDetailEntity;
import cn.com.thtf.cms.report.entity.RBuyContractDetailEntity;
import cn.com.thtf.cms.report.entity.RDynamicStocktakeEntity;
import cn.com.thtf.cms.report.entity.StockProductDetailEntity;
import cn.com.thtf.cms.report.entity.TransportFareSharingEntity;
import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.service.CommonService;
import cn.com.thtf.cms.report.util.DownloadController.FILE_STATUS;
import cn.com.thtf.cms.report.util.DownloadController.OPERATION_STATUS;
import cn.shiy.common.cws.utils.Container;

/**
 * ThreadPoolTask
 * 
 * @author Lubo
 */
public class TransCSVTask implements Runnable {

    /** log */
    private static final Logger log = LoggerFactory.getLogger(TransCSVTask.class);
    /** CommonService */
    private CommonService commonService = null;
    /** nodeKey */
    @Setter
    @Getter
    private String nodeKey;
    /** nodeId */
    @Setter
    @Getter
    private String nodeId;
    /** user */
    @Setter
    @Getter
    private UserEntity user;

    /**
     * ThreadPoolTask
     * 
     * @param node
     */
    public TransCSVTask(String nodeId, String nodeKey, UserEntity user) {
        this.nodeId = nodeId;
        this.nodeKey = nodeKey;
        this.user = user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        log.debug("开始转换文件:{}", nodeKey);
        DownloadParam param = new DownloadParam();
        param.setNodeKey(nodeKey);
        try {
            if (user.getRoleId() == 2 || user.getRoleId() == 7 || user.getRoleId() == 13
                    || user.getRoleId() == 16 || user.getRoleId() == 17
                    || user.getRoleId() == 18) {
                if (transAllToCSV(nodeKey, user)) {
                    param.setType(OPERATION_STATUS.UNAPP);
                    log.debug("转换文件完毕:{}", nodeKey);
                } else {
                    log.error("转换csv文件错误:{}", nodeKey);
                    param.setType(OPERATION_STATUS.CHANGESTATUS);
                    param.setStatus(FILE_STATUS.NONE);
                }
            } else {

                if ("1141".equals(nodeId)) {
                    /* 销售合同产品明细 */
                    transSellContractDetailToCSV(nodeKey, user);
                } else if ("1142".equals(nodeId)) {
                    sendGoodsDetailToCSV(nodeKey, user);
                } else if ("1152".equals(nodeId)) {
                    transBuyContractDetailToCSV(nodeKey, user);
                } else if ("1153".equals(nodeId)) {
                    transInstockDetailToCSV(nodeKey, user);
                } else if ("1187".equals(nodeId)) {
                    transStockProductDetailToCSV(nodeKey, user);
                } else if ("1171".equals(nodeId)) {
                    transTransportFareSharingToCSV(nodeKey, user);
                } else if ("1172".equals(nodeId)) {
                    transTransportFareDetailToCSV(nodeKey, user);
                } else if ("1191".equals(nodeId)) {
                    transDynamicStocktakeToCSV(nodeKey, user);
                } else {

                }
                param.setType(OPERATION_STATUS.UNAPP);
                log.debug("转换文件完毕:{}", nodeKey);
            }

        } catch (Exception e) {
            log.error("转换csv文件错误:{}", nodeKey);
            param.setType(OPERATION_STATUS.CHANGESTATUS);
            param.setStatus(FILE_STATUS.NONE);
        }

        DownloadController.getInstance().app(param);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("Sleep发生错误!", e);
        }
    }

    /**
     * 拷贝
     * 
     * @param fileName
     * @param user
     * @throws IOException
     */
    private boolean transAllToCSV(String fileName, UserEntity user) throws IOException {
        if ("1141".equals(nodeId)) {
            return copyFile(Constants.CSVFILEPATH + Constants.CSVFILE_SELLCONTRACTDETAIL,
                    Constants.CSVTMPFILEPATH + fileName);
        } else if ("1142".equals(nodeId)) {
            return copyFile(Constants.CSVFILEPATH + Constants.CSVFILE_SENDGOODS,
                    Constants.CSVTMPFILEPATH + fileName);
        } else if ("1152".equals(nodeId)) {
            return copyFile(Constants.CSVFILEPATH + Constants.CSVFILE_BUYCONTRACTDETAIL,
                    Constants.CSVTMPFILEPATH + fileName);
        } else if ("1153".equals(nodeId)) {
            return copyFile(Constants.CSVFILEPATH + Constants.CSVFILE_INVOICEDETAIL,
                    Constants.CSVTMPFILEPATH + fileName);
        } else if ("1187".equals(nodeId)) {
            return copyFile(Constants.CSVFILEPATH + Constants.CSVFILE_STOCKPRODUCTDETAIL,
                    Constants.CSVTMPFILEPATH + fileName);
        } else if ("1171".equals(nodeId)) {
            return copyFile(Constants.CSVFILEPATH + Constants.CSVFILE_SHARING,
                    Constants.CSVTMPFILEPATH + fileName);
        } else if ("1172".equals(nodeId)) {
            return copyFile(
                    Constants.CSVFILEPATH + Constants.CSVFILE_TRANSPORTFAREDETAIL,
                    Constants.CSVTMPFILEPATH + fileName);
        } else if ("1191".equals(nodeId)) {
            return copyFile(Constants.CSVFILEPATH + Constants.CSVFILE_DYNAMIC_STOCKTAKE,
                    Constants.CSVTMPFILEPATH + fileName);
        } else {
            return false;
        }
    }

    /**
     * 复制文件csv-->csvtmp目录
     * 
     * @param oldFile
     * @param newFile
     * @return
     */
    public boolean copyFile(String oldFile, String newFile) {

        StringBuffer oldFileTmp = new StringBuffer(oldFile);
        oldFileTmp.append(".");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (week != 0) {
            oldFileTmp.append(week);
        } else {
            oldFileTmp.append(7);
        }
        String oldFileEnd = oldFileTmp.toString() + ".end";
        oldFileTmp.append(".zip");
        try {
            File endfile = new File(oldFileEnd);
            /* .end文件存在才进行复制 */
            if (endfile.exists()) {
                FileUtils.copyFile(new File(oldFileTmp.toString()), new File(newFile));
            } else {
                log.error("复制文件错误：.end文件不存在!");
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("复制文件错误：", e);
            return false;
        }
    }

    /**
     * 库存产品明细
     * 
     * @param fileName
     * @throws IOException
     */
    @SuppressWarnings("serial")
    private void transStockProductDetailToCSV(String fileName, UserEntity user)
            throws IOException {
        StockProductDetailDao dao = Container.getBean("stockProductDetailDao",
                StockProductDetailDao.class);

        /* 封装查询参数 */
        BaseQueryData<StockProductDetailEntity> queryParam = new BaseQueryData<StockProductDetailEntity>();
        if (user.getRoleId() == 12 || user.getRoleId() == 13 || user.getRoleId() == 19
                || user.getRoleId() == 20) {
            queryParam.setFieldsParam("1111111100",
                    StockProductDetailActionBean.fieldRows);// 显示的列
        } else {
            queryParam.setFieldsParam("1111111111",
                    StockProductDetailActionBean.fieldRows);// 显示的列
        }
        queryParam
                .setSidx("stockroom_name,product_type_name,product_serie_name,product_name");

        commonService = Container.getBean("commonService", CommonService.class);
        commonService.getUserFileParam(user, queryParam);

        try {
            if (user.getRoleId() == 12 || user.getRoleId() == 13
                    || user.getRoleId() == 19 || user.getRoleId() == 20) {
                CSVConvertUtil.transToCSV(fileName,
                        new RStockProductDetailOtherConvertHelper(), dao,
                        "getStockProductDetail", queryParam);
            } else {
                CSVConvertUtil.transToCSV(fileName,
                        new RStockProductDetailConvertHelper(), dao,
                        "getStockProductDetail", queryParam);
            }
        } catch (Exception e) {
            log.error("转换csv文件错误！", e);
            throw new DataAccessException("CSV trans error") {
            };
        }
    }

    /**
     * 采购合同明细
     * 
     * @param fileName
     * @param usesr
     */
    @SuppressWarnings("serial")
    private void transBuyContractDetailToCSV(String fileName, UserEntity usesr) {
        RBuyContractDetailDao dao = Container.getBean("rBuyContractDetailDao",
                RBuyContractDetailDao.class);
        StringBuffer fieldKey = new StringBuffer();
        for (int i = 0; i < RBuyContractDetailActionBean.fields.length; i++) {
            fieldKey.append("1");
        }
        /* 封装查询参数 */
        BaseQueryData<RBuyContractDetailEntity> queryParam = new BaseQueryData<RBuyContractDetailEntity>();
        queryParam.setFieldsParam(fieldKey.toString(),
                RBuyContractDetailActionBean.fields);// 显示的列
        queryParam.setSidx("buy_contract_id");

        commonService = Container.getBean("commonService", CommonService.class);
        commonService.getUserFileParam(user, queryParam);
        try {
            CSVConvertUtil.transToCSV(fileName, new RBuyContractDetailConvertHelper(),
                    dao, "getBuyContractDetailList", queryParam);
        } catch (Exception e) {
            log.error("转换csv文件错误！", e);
            throw new DataAccessException("CSV trans error") {
            };
        }
    }

    /**
     * 采购合同明细
     * 
     * @param fileName
     * @param usesr
     */
    @SuppressWarnings("serial")
    private void transDynamicStocktakeToCSV(String fileName, UserEntity usesr) {
        RDynamicStocktakeDao dao = Container.getBean("rDynamicStocktakeDao",
                RDynamicStocktakeDao.class);
        StringBuffer fieldKey = new StringBuffer();
        for (int i = 0; i < RDynamicStocktakeActionBean.fields.length; i++) {
            fieldKey.append("1");
        }
        /* 封装查询参数 */
        BaseQueryData<RDynamicStocktakeEntity> queryParam = new BaseQueryData<RDynamicStocktakeEntity>();
        queryParam
                .setFieldsParam(fieldKey.toString(), RDynamicStocktakeActionBean.fields);// 显示的列
        queryParam.setSidx("stock_name");

        commonService = Container.getBean("commonService", CommonService.class);
        commonService.getUserFileParam(user, queryParam);
        try {
            CSVConvertUtil.transToCSV(fileName, new RDynamicStockTakeConvertHelper(),
                    dao, "getStocktakeList", queryParam);
        } catch (Exception e) {
            log.error("转换csv文件错误！", e);
            throw new DataAccessException("CSV trans error") {
            };
        }
    }

    /**
     * 销售合同产品明细
     * 
     * @param fileName
     * @throws IOException
     */
    @SuppressWarnings("serial")
    private void transSellContractDetailToCSV(String fileName, UserEntity user)
            throws IOException {
        RSellContractDetailDao dao = Container.getBean("sellContractDetailDao",
                RSellContractDetailDao.class);
        /* 封装查询参数 */
        BaseQueryData<RSellContractDetailDto> queryParam = new BaseQueryData<RSellContractDetailDto>();
        queryParam.setFieldsParam("11111111111111111111111111111111",
                SellContractDetailActionBean.fieldRows);// 显示的列
        queryParam.setSidx("sell_contract_id");
        commonService = Container.getBean("commonService", CommonService.class);
        commonService.getUserFileParam(user, queryParam);
        try {
            CSVConvertUtil.transToCSV(fileName, new RSellContractDetailConvertHelper(),
                    dao, "queryRecords", queryParam);
        } catch (Exception e) {
            log.error("转换csv文件错误！", e);
            throw new DataAccessException("CSV trans error") {
            };
        }
    }

    /**
     * 运费分摊
     * 
     * @param fileName
     * @throws IOException
     */
    @SuppressWarnings("serial")
    private void transTransportFareSharingToCSV(String fileName, UserEntity user)
            throws IOException {
        TransportFareSharingDao dao = Container.getBean("transportFareSharingDao",
                TransportFareSharingDao.class);

        /* 封装查询参数 */
        BaseQueryData<TransportFareSharingEntity> queryParam = new BaseQueryData<TransportFareSharingEntity>();
        queryParam.setFieldsParam("1111111111111",
                TransportFareSharingActionBean.fieldRows);// 显示的列
        queryParam.setSidx("box_id");

        commonService = Container.getBean("commonService", CommonService.class);
        commonService.getUserFileParam(user, queryParam);

        try {
            CSVConvertUtil.transToCSV(fileName, new RTransportFareSharingConvertHelper(),
                    dao, "getTransportFareSharing", queryParam);
        } catch (Exception e) {
            log.error("转换csv文件错误！", e);
            throw new DataAccessException("CSV trans error") {
            };
        }
    }

    /**
     * 入库明细
     * 
     * @param fileName
     * @throws IOException
     */
    @SuppressWarnings("serial")
    private void transInstockDetailToCSV(String fileName, UserEntity user)
            throws IOException {
        InstockDetailDao dao = Container.getBean("instockDetailDao",
                InstockDetailDao.class);

        /* 封装查询参数 */
        BaseQueryData<InstockDetailEntity> queryParam = new BaseQueryData<InstockDetailEntity>();
        queryParam.setFieldsParam("1111111111111111111111111111",
                InstockDetailActionBean.fieldRows);// 显示的列
        queryParam.setSidx("buy_contract_id");

        commonService = Container.getBean("commonService", CommonService.class);
        commonService.getUserFileParam(user, queryParam);
        if (user.getRoleId() != 13) {
            try {
                CSVConvertUtil.transToCSV(fileName, new RInstockDetailConvertHelper(),
                        dao, "getInstockDetail", queryParam);
            } catch (Exception e) {
                log.error("转换csv文件错误！", e);
                throw new DataAccessException("CSV trans error") {
                };
            }
        } else {
            try {
                CSVConvertUtil.transToCSV(fileName,
                        new RInstockDetailRole13ConvertHelper(), dao, "getInstockDetail",
                        queryParam);
            } catch (Exception e) {
                log.error("转换csv文件错误！", e);
                throw new DataAccessException("CSV trans error") {
                };
            }
        }
    }

    /**
     * 运费明细
     * 
     * @param fileName
     * @throws IOException
     */
    @SuppressWarnings("serial")
    private void transTransportFareDetailToCSV(String fileName, UserEntity user)
            throws IOException {
        RTransportFareDetailDao dao = Container.getBean("transportFareDetailDao",
                RTransportFareDetailDao.class);

        /* 封装查询参数 */
        BaseQueryData<RTransportFareDetailDto> queryParam = new BaseQueryData<RTransportFareDetailDto>();
        queryParam.setFieldsParam("111111111111111111111",
                RTransportFareDetailActionBean.fieldRows);// 显示的列
        queryParam.setSidx("box_id");

        commonService = Container.getBean("commonService", CommonService.class);
        commonService.getUserFileParam(user, queryParam);

        try {
            CSVConvertUtil.transToCSV(fileName, new RTransportFareDetailConvertHelper(),
                    dao, "queryRecords", queryParam);
        } catch (Exception e) {
            log.error("转换csv文件错误！", e);
            throw new DataAccessException("CSV trans error") {
            };
        }
    }

    /**
     * 发货明细
     * 
     * @param fileName
     * @throws IOException
     */
    @SuppressWarnings("serial")
    private void sendGoodsDetailToCSV(String fileName, UserEntity user)
            throws IOException {
        RSendGoodsDetailDao dao = Container.getBean("sendGoodsDetailDao",
                RSendGoodsDetailDao.class);

        /* 封装查询参数 */
        BaseQueryData<RSendGoodsDetailDto> queryParam = new BaseQueryData<RSendGoodsDetailDto>();
        if (user.getRoleId() == 3 || user.getRoleId() == 4 || user.getRoleId() == 6
                || user.getRoleId() == 7 || user.getRoleId() == 9
                || user.getRoleId() == 19) {
            queryParam.setFieldsParam("111111111111111111111110000111111110011111",
                    RSendGoodsDetailActionBean.fieldRows);// 显示的列
        } else if (user.getRoleId() == 13) {
            queryParam.setFieldsParam("111111111111111101000100000000101110011111",
                    RSendGoodsDetailActionBean.fieldRows);// 显示的列
        } else {
            queryParam.setFieldsParam("111111111111111111111111111111111111111111",
                    RSendGoodsDetailActionBean.fieldRows);// 显示的列
        }

        queryParam.setSidx("send_goods_id");

        commonService = Container.getBean("commonService", CommonService.class);
        commonService.getUserFileParam(user, queryParam);

        try {
            if (user.getRoleId() == 3 || user.getRoleId() == 4 || user.getRoleId() == 6
                    || user.getRoleId() == 7 || user.getRoleId() == 9
                    || user.getRoleId() == 19) {
                CSVConvertUtil.transToCSV(fileName,
                        new RSendGoodsDetailRole3ConvertHelper(), dao, "queryRecords",
                        queryParam);
            } else if (user.getRoleId() == 13) {
                CSVConvertUtil.transToCSV(fileName,
                        new RSendGoodsDetailRole13ConvertHelper(), dao, "queryRecords",
                        queryParam);
            } else {
                CSVConvertUtil.transToCSV(fileName, new RSendGoodsDetailConvertHelper(),
                        dao, "queryRecords", queryParam);
            }

        } catch (Exception e) {
            log.error("转换csv文件错误！", e);
            throw new DataAccessException("CSV trans error") {
            };
        }
    }
}
