/**
 * ClassName  BaseDto
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-9-13
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * BaseDto
 * @author ChenHuajiang
 */
@SuppressWarnings("serial")
public abstract class BaseDto implements Serializable {
     
	@Getter
	private List<String> errMsgList = new ArrayList<String>();

	public void addErrorMsg(String msg) {
		errMsgList.add(msg);
	}
}
