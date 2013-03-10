/**
 * ClassName  LoginActionBean
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-9-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.action.view;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;
import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.security.CmsACL;
import cn.shiy.common.cws.action.BaseActionBean;
import cn.shiy.common.cws.action.JSONResolution;

/**
 * 登录/退出功能入口
 * 
 * @author Shiy
 */
public class LoginActionBean extends BaseActionBean {

    private static final Logger log = LoggerFactory.getLogger(LoginActionBean.class);

    // @Setter
    // @Getter
    // @Validate(required = true, on = { "login" })
    // private String username;
    //
    // @Setter
    // @Getter
    // @Validate(required = true, on = { "login" })
    // private String password;

    @Setter
    @Getter
    @Validate(required = false, on = { "hiddenLogin" })
    private String token;

    // public Resolution login() {
    // return new ForwardResolution("/");
    // }

    @DefaultHandler
    /**
     * 通过Token的登录入口
     */
    public Resolution hiddenLogin() {
        String ip = this.getContext().getRequest().getRemoteAddr();
        log.info("Token login request from {} with token {}", ip, token);
        if (token == null || token.trim().length() == 0) {
            return null;
        }
        Cache loginTokenCache = this.getBean("loginTokenCache", Cache.class);
        Element elmt = loginTokenCache.get(token);
        if (elmt == null) {
            log.debug("{} from {} is not exist or timeout", token, ip);
            return null;
        }
        UserEntity user = (UserEntity) elmt.getObjectValue();
        CmsACL acl = new CmsACL();
        acl.setUser(user);
        this.getContext().setAcl(acl);
        return new JSONResolution("done");
    }

    /**
     * 退出
     * 
     * @return
     */
    public Resolution logout() {
        HttpSession session = this.getContext().getRequest().getSession();
        if (session != null) {
            session.invalidate();
        }
        return null;
    }

    /**
     * 空操作
     * 
     * @return
     */
    public Resolution keep() {
        //log.debug("收到一个保持连接的请求");
        return null;
    }
}
