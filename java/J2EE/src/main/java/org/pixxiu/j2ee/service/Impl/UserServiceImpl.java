package org.pixxiu.j2ee.service.Impl;

import org.pixxiu.j2ee.mapper.UserMapper;
import org.pixxiu.j2ee.pojo.User;
import org.pixxiu.j2ee.service.UserService;
import org.pixxiu.j2ee.utils.Md5Util;
import org.pixxiu.j2ee.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    /*用户注册*/
    @Override
    public void register(String username, String password) {
//          加密
        String md5String = Md5Util.getMD5String(password);
//        添加入数据库
        userMapper.add(username, md5String);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl, id);
    }

    /*更新密码*/
    @Override
    public void updatePwd(String newPwd) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd), id);
    }
}
