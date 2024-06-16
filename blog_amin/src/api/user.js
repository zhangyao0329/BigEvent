import request from "@/utils/request";
/* 用户表的操作 */

// 用户注册
export const userRegisterService = (registerData) => {
  const params = new URLSearchParams();
  for (const key in registerData) {
    params.append(key, registerData[key])
  }
  return request.post('/user/register', params)
}

// 用户登录
export const userLoginService = (loginData) => {
  const params = new URLSearchParams();
  for (const key in loginData) {
    params.append(key, loginData[key])
  }
  return request.post('/user/login', params)
}

// 获取当前登录用户信息
export const userInfoService = () => {
  return request.get('/user/userInfo')
}

// 用户别名和邮箱更新
export const userInfoUpdateService = (userInfoData) => {
  return request.put('/user/update', userInfoData)
}

// 用户头像更新
export const userAvatarUpdateService = (avatarUrl) => {
  const urlSearchParams = new URLSearchParams();
  urlSearchParams.append('avatarUrl', avatarUrl)
  return request.patch('/user/updateAvatar', urlSearchParams)
}

// 用户密码更新
export const userPwdUpdateService = (pwdData) => {
  return request.patch('/user/updatePwd', pwdData)
}

