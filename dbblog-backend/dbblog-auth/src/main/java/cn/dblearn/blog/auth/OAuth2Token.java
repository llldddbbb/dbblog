package cn.dblearn.blog.auth;


import org.apache.shiro.authc.AuthenticationToken;

/**
 * OAuth2Token
 *
 * @author bobbi
 * @date 2018/10/07 16:39
 * @email 571002217@qq.com
 * @description Shiro 认证类
 */
public class OAuth2Token implements AuthenticationToken {
    private String token;

    public OAuth2Token(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
