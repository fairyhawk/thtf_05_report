/**
 * ClassName  PageCell
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-12-21
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.dto;

import java.util.List;
import lombok.Data;

/**
 * PageCell
 * 
 * @author Lubo
 */
@Data
public class PageCell {
    
    /** 编号 */
    private String id; 
    /** cell */
    private List<Object> cell;
  
}
