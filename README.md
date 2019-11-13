- 会议室管理系统功能文档  
-- 用户登录/注册  
	--- 通过邮箱进行注册，并且进行登录（最好是企业内部邮箱）  
	--- paramater: email , name(真实姓名), 身份证号，手机号，密码，确认密码，所在部门  
	--- 密码利用MD5进行加密处理  
	--- 单点登录，session，cookie  
	--- 对应api接口：  
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
        ```
		- 检查邮箱是否已经使用api
		-- req
			url: /checkEmail
			method: get
			param:
				email
		-- res
			存在的情况：
				{
				    "msg": "邮箱已存在",
				    "code": 210
				}
			不存在的情况:
				{
				    "msg": "邮箱可用",
				    "code": 200
				}


		- 检查员工是否已经注册过api
		-- req 
			url: /checkEmployee
			method: get
			param:
				employee_id
		-- res
			存在的情况:
				{
				    "msg": "员工号已注册",
				    "code": 210
				}
			不存在的情况:
				{
				    "msg": "员工号可用",
				    "code": 200
				}





-- 管理员登录
	--- 固定username:admin 
	
	
- 管理员添加部门信息  
* req:
    * url: /admin/addDepartment  
    * method: get  
    * param: departmentName  
* res:
    * ```
      success:
      {
         "msg": "添加成功",
         "code": 200
      }
      fail:
      {
         "msg": "添加失败",
         "code": 210
      }
      ```  
* 更新部门操作
* req:
    * url: /admin/modifyDepartment
    * method: get
    * param department_id, departName
* res:
    * ```
      {
          "msg": "修改成功",
          "code": 200
      }
      
      {
          "msg": "修改失败",
          "code": 210
      }
      ```
* all部门信息
* req:
    * url: /department
    * method: get
    * ```
      {
          "msg": [
              {
                  "id": 1,
                  "name": "测试部"
              },
              {
                  "id": 2,
                  "name": "开发部"
              },
              {
                  "id": 3,
                  "name": "研发部"
              },
              {
                  "id": 4,
                  "name": "行政部"
              },
              {
                  "id": 5,
                  "name": "财务部"
              }
          ],
          "code": 200
      }
      ```    
* res: 
    * ```
      {
          "msg": "没有部门数据",
          "code": 210
      }
      ```
* 删除部门
* req:
    * url: /admin/deleteDepartment
    * method: delete
    * param: department_id
* res: 
    * ```
        {
            "msg": "删除成功",
            "code": 200
        }
      
        {
            "msg": "删除失败",
            "code": 210
        }
      ```
      

    
## 会议室列表显示

## 查看能预约的会议室

## 查看会议室设备状况

## 会议室预约

## 会议室签到功能

# 管理员相关功能和api
## 管理员登录
	--- 固定username:admin

        
   
