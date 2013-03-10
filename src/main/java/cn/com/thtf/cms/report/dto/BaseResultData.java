/**
 * ClassName  ResultDateBase
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-12-21
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.dto;

import java.util.ArrayList;
import java.util.List;

import cn.com.thtf.cms.report.util.Util;

import lombok.Data;

/**
 * BaseResultData
 * 
 * @author Lubo
 */
@Data
public class BaseResultData {

    /** 当前页 */
    private int page;
    /** 总共条 */
    private int records;
    /** 总共多少页 */
    private int total;
    /** 数据 */
    private List<PageCell> rows;
    /** num */
    private int num;

    /** url */
    private String url;
    /** query */
    private StringBuffer query = new StringBuffer("&");

    /**
     * @param query
     *            the query to set
     */
    public void setQuery(String name, String value) {
        if (!"".equals(value) && value != null) {
            query.append(name).append("=").append(Util.encodeUrl(value)).append("&");
        }
    }

    /**
     * @return the pageFirst
     */
    public String getPageFirst() {
        StringBuffer sbUrl = new StringBuffer();

        sbUrl.append(url);
        sbUrl.append(query);
        sbUrl.append("page");
        sbUrl.append("=1");

        return sbUrl.toString();
    }

    /**
     * @return the pageLast
     */
    public String getPageLast() {
        StringBuffer sbUrl = new StringBuffer();

        sbUrl.append(url);
        sbUrl.append(query);
        sbUrl.append("page");
        sbUrl.append("=");
        sbUrl.append(total);

        return sbUrl.toString();
    }

    /**
     * @return the pageNext
     */
    public String getPageNext() {
        StringBuffer sbUrl = new StringBuffer();

        sbUrl.append(url);
        sbUrl.append(query);
        sbUrl.append("page");
        sbUrl.append("=");
        if (page == total) {
            sbUrl.append(total);
        } else {
            sbUrl.append(page + 1);
        }

        return sbUrl.toString();
    }

    /**
     * @return the pageBefore
     */
    public String getPageBefore() {
        StringBuffer sbUrl = new StringBuffer();

        sbUrl.append(url);
        sbUrl.append(query);
        sbUrl.append("page");
        sbUrl.append("=");
        if (page < 2) {
            sbUrl.append(1);
        } else {
            sbUrl.append(page - 1);
        }

        return sbUrl.toString();
    }

    /**
     * ResultDateBase
     * 
     * @param page
     * @param records
     * @param total
     */
    public BaseResultData(int page, int records, int total) {
        this.page = page;
        this.records = records;
        this.total = total;
        rows = new ArrayList<PageCell>();
    }

    /**
     * addCells
     * 
     * @param cells
     */
    public void addCells(String... cells) {
        /** rows数据 */
        PageCell pageCell = new PageCell();
        List<Object> cellList = new ArrayList<Object>();
        for (int i = 0; i < cells.length; i++) {
            cellList.add(cells[i]);
        }
        pageCell.setId(String.valueOf((++num)));
        pageCell.setCell(cellList);
        rows.add(pageCell);
    }
}
