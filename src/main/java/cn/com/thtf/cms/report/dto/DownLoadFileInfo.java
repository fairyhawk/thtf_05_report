/**
 * ClassName  DownLoadFileInfo
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-11-11
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author liuqg
 */
@Data
public class DownLoadFileInfo {

/*    public List<String> fileList001;// 销售合同汇总表
    public List<String> fileList002;// 销售合同产品明细表
    public List<String> fileList003;// 退货合同明细表
    public List<String> fileList004;// 发货明细表
    public List<String> fileList005;// 退货明细表
    public List<String> fileList006;// 回款表
    public List<String> fileList007;// 退款明细表
    public List<String> fileList008;// 开票汇总
    public List<String> fileList009;// 退票明细表
    public List<String> fileList010;// 发货单汇总表
    public List<String> fileList011;// 库存数量明细
    public List<String> fileList012;// 运费明细表
    public List<String> fileList013;// 运费分摊
    public List<String> fileList014;// 动态盘点表
    public List<String> fileList015;// 采购合同汇总
    public List<String> fileList016;// 采购合同明细表
    public List<String> fileList017;// 入库单明细
    public List<String> fileList018;// 采购付款汇总
    public List<String> fileList019;// 采购合同收票
    public List<String> fileList020;// 客户信用明细
    public List<String> fileList021;// 客户销售经理对应
    public List<String> fileList022;// 采购付款明细
*/ 
    @Getter
    @Setter
    private  Map<String, List<String>> filemap;

}
