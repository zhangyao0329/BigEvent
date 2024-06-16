package org.pixxiu.j2ee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.pixxiu.j2ee.pojo.Result;
import org.pixxiu.j2ee.pojo.User;
import org.pixxiu.j2ee.service.UserService;
import org.pixxiu.j2ee.utils.JwtUtil;
import org.pixxiu.j2ee.utils.Md5Util;
import org.pixxiu.j2ee.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@Validated
@Tag(name = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /*用户注册*/
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User u = userService.findByUserName(username);
        if (u == null) {
            userService.register(username, password);
            return Result.success(u);
        } else {
            return Result.error("用户名已被占用");
        }
    }

    /*登录*/
    @PostMapping("/login")
    @Operation(summary = "登录")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User loginUser = userService.findByUserName(username);
        if (loginUser == null) {
            return Result.error("用户名不存在，请注册");
        }
        if (loginUser.getPassword().equals(Md5Util.getMD5String(password))) {
            //            用户名存在，且密码匹配，即登录成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);

            //登录成功把token存放进redis
            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            ops.set(token, token, 1, TimeUnit.HOURS);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    /*获取用户详细信息*/
    @Operation(summary = "当前登录用户信息", description = "解析token,从而获取已登录的用户的用户名,再从数据库中查到当前用户信息")
    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token) {
        //解析token,从而获取已登录的用户的用户名
/*        Map<String, Object> map = JwtUtil.parseToken(token);
        System.out.println("解析token（map）" + map);*/
        Map<String, Object> map = ThreadLocalUtil.get();
        System.out.println("线程池中解析的token:" + map);
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    /*更新用户基本信息*/
    @Operation(summary = "更新用户基本信息")
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        user.setUpdateTime(LocalDateTime.now());
        userService.update(user);
        return Result.success();
    }

    /*更新头像*/
    @Operation(summary = "更新头像")
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @Operation(summary = "更新用户密码")
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
        //1.校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }

        //原密码是否正确
        //调用userService根据用户名拿到原密码,再和old_pwd比对
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码填写不正确");
        }

        //newPwd和rePwd是否一样
        if (!rePwd.equals(newPwd)) {
            return Result.error("两次填写的新密码不一样");
        }

        //2.调用service完成密码更新
        userService.updatePwd(newPwd);
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.getOperations().delete(token);
        return Result.success();
    }

}
