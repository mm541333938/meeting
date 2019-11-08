# 会议室管理系统功能文档
## 用户注册
- 通过邮箱进行注册，并且进行登录（最好是企业内部邮箱）
- paramater: email , name(真实姓名), 身份证号，手机号，密码，确认密码，所在部门
- 密码利用MD5进行加密处理
- 单点登录，session，cookie
- 对应api接口：
- 注册的api 
		-- request
			url: /register
			method: post
			param: 
				email(邮箱作为唯一username，需要判断邮箱正确性),
				name(真是姓名) , 
				employee_id(唯一员工号),
				phone(手机号，需要判断11位), 
				password(密码> 6 位), 
				rePassword(确认密码), 
				depart(部门)
		-- response
			{
			    "msg": "注册失败，账号已存在或员工号已存在，具体请联系管理员",
			    "code": 210,
			    "error": "Duplicate entry 'xxxxxx@qq.com' for key 'email'"
			}
			
			{
			    "msg": "注册成功",
			    "code": 200
			}

		- 检查邮箱是否已经使用api
		-- req
			url: /checkEmail
			method: get
			param:
				email
		-- res


		- 检查员工是否已经注册过api
		-- req 
			url: /checkEmployee
			method: get
			param:
				employee_id
		-- res
			
## 用户登录
### 通过输入用户名登录(用户名是邮箱or员工号)
- url: /login
- param: user_name, password,需要用md5对密码进行加密

## 会议室列表显示

## 查看能预约的会议室

## 查看会议室设备状况

## 会议室预约

## 会议室签到功能

# 管理员相关功能和api
## 管理员登录
	--- 固定username:admin
