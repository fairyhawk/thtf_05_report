/**
 * ClassName  SiteInformationSubject
 *
 * History
 * Create User: hanrb
 * Create Date: 2011-2-21
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.dto;

import java.util.List;

/**
 * 
 * @author hanrb
 */

public class SiteInformationSubjectDto {
    private List<String> msgs;
    private List<Integer> id;

    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }

    public List<String> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<String> msgs) {
        this.msgs = msgs;
    }
}
