/**
 * ClassName  LoginActionBean
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-10-26
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.action.view;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.validation.Validate;
import cn.com.thtf.cms.report.constant.Constants;
import cn.com.thtf.cms.report.entity.UserEntity;
import cn.com.thtf.cms.report.service.UserService;
import cn.shiy.common.cws.action.BaseActionBean;

/**
 * 
 * 与CMS对接登录入口
 * 
 * @author Shiy
 */

public class BackEndLoginActionBean extends BaseActionBean {

    private static final Logger log = LoggerFactory
            .getLogger(BackEndLoginActionBean.class);

    private static final String ENCODING = "UTF-8";

    private UserService userService;

    @Setter
    @Getter
    @Validate(required = true, on = { "saveLoginToken" })
    /**
     * userid 传入的登录名
     */
    private String userid;

    @DefaultHandler
    /**
     * 取指定userid的Token，存入Cache待查
     */
    public Resolution saveLoginToken() {
        String ip = this.getContext().getRequest().getRemoteAddr();

        if (!ip.equals(Constants.HIDDENLOGIN_IP)) {
            log.warn("illegal login request from {}", ip);
            return null;
        }

        StringBuilder tokenSeed = new StringBuilder(String.valueOf(System
                .currentTimeMillis())).append(userid);

        String cryptedKey = getCryptedKey(tokenSeed);

        userService = this.getBean("userService", UserService.class);

        Cache loginTokenCache = this.getBean("loginTokenCache", Cache.class);

        UserEntity user = userService.getUserById(userid);

        loginTokenCache.put(new Element(cryptedKey, user));
        StreamingResolution sr = new StreamingResolution("application/octet-stream",
                cryptedKey);
        sr.setCharacterEncoding(ENCODING);
        log.debug("{} has gotten loginToken:{}", userid, cryptedKey);
        return sr;
    }

    /**
     * <pre>
     * 转换Token
     * 尝试使用MD5>Base64>明文
     * </pre>
     * 
     * @param seed
     * @return
     */
    private String getCryptedKey(StringBuilder seed) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            return new String(Base64.encodeBase64(md.digest(seed.toString().getBytes(
                    ENCODING))));

        } catch (Throwable e) {
            log.warn("can't use md5,try Base64", e);
            try {
                return new String(Base64.encodeBase64(seed.toString().getBytes(ENCODING)));
            } catch (Throwable ex) {
                log.error("Base64 failed too,show original words", e);
                return seed.toString();
            }
        }
    }
}
