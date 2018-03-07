package com.example.shiro;

import com.example.entity.User;
import com.example.util.DecriptUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author jinx
 * @date 2018/3/7 16:36
 * Desc:
 */
public class MyShiroRealm extends AuthorizingRealm {
    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //角色
        Set<String> roles = new HashSet<>();
        roles.add("jinx");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        //权限
        Set<String> permissions = new HashSet<>();
        permissions.add("权限添加");
        permissions.add("权限删除");
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());

        Map<String, Object> map = new HashMap<>(15);
        String pawSalt = DecriptUtil.MD5(username + password);
        map.put("nickname", username);
        map.put("pswd", pawSalt);
        User user = new User("111", username, pawSalt);
        return new SimpleAuthenticationInfo(user, password, getName());
    }
}
