/**
 * ClassName  SiteInformationDto
 *
 * History
 * Create User: hanrb
 * Create Date: 2011-2-21
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.dto;

/**
 * 
 * @author hanrb
 */

public class SiteInformationOfShowDto {
    /*编号*/
    private Integer id;
    /*标题*/
    private String subject;
    /*内容*/
    private String text;
    /*是否滚动*/
    private Integer rollFlag;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getText() {
        if(text==null)return text;
        text = text.replaceAll("\r\n", "<br>");
        text = text.replaceAll("\r", "<br>");
        text = text.replaceAll("\n", "<br>");
        text = text.replaceAll(" ", "&nbsp;");
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Integer getRollFlag() {
        return rollFlag;
    }
    public void setRollFlag(Integer rollFlag) {
        this.rollFlag = rollFlag;
    }
    public String getDatetime() {
        return datetime;
    }
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
    public String getUpdTime() {
        return updTime;
    }
    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getFilepath() {
        return filepath;
    }
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
    /*发送者编号*/
    private String userId;
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /*新建时间*/
    private String datetime;
    /*修改时间*/
    private String updTime;
    /*附件名称*/
    private String filename;
    /*附件路径*/
    private String filepath;
}
