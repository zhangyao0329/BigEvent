package org.pixxiu.j2ee.service;

import org.pixxiu.j2ee.pojo.User;

public interface UserService {
    //根据用户名查询用户
    User findByUserName(String username);
    //注册
    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);
}
