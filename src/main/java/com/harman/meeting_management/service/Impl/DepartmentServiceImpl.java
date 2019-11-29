package com.harman.meeting_management.service.Impl;

import com.harman.meeting_management.entity.Department;
import com.harman.meeting_management.mapper.DepartmentMapper;
import com.harman.meeting_management.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author L.Willian
 * @date 11/12/2019 9:19 AM
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    private final String key = "departmentAll";

    @Transactional //事件的完整性约束
    public int addDepartment(Department department) throws DataAccessException {
        int flag = departmentMapper.insertSelective(department);
        //得到刚刚插入的数据的id
        Long currentId = department.getId();
        //根据刚插入数据的id得到对应的数据，并存到缓存中去
        Department departmentById = departmentMapper.selectByPrimaryKey(currentId);
        if (flag == 1) {
            //说明插入成功
            //把新数据加入缓存缓存 ，刚才更新的id的数据
            redisTemplate.boundHashOps(key).put(currentId, departmentById);
            LOGGER.info("把刚插入的数据添加进缓存中" + departmentById.getId() + " -> " + departmentById.getName());
        } else {
            LOGGER.info("添加没有成功");
            flag = 0;
        }
        return flag;
    }
    @Transactional //事件的完整性约束
    public int modifyDepartment(Department department) {
        int flag = departmentMapper.updateByPrimaryKeySelective(department);
        //得到刚刚修改的数据的id
        Long currentId = department.getId();
        Department departmentById = departmentMapper.selectByPrimaryKey(currentId);
        if (flag == 1) {
            //说明修改成功
            //更新redis中对应的数据
            redisTemplate.boundHashOps(key).put(currentId, departmentById);
            LOGGER.info("把更新的数据更新进缓存中" + departmentById.getId() + " -> " + departmentById.getName());
        } else {
            LOGGER.info("更新没有成功");
            flag = 0;
        }
        return flag;
    }

    public int deleteById(Long id) {
        int flag = departmentMapper.deleteByPrimaryKey(id);
        if (flag == 1) {
            //说明删除成功
            //删除缓存中对应的数据
            redisTemplate.boundHashOps(key).delete(id);
            LOGGER.info("删除数据成功"+id);
        } else {
            flag = 0;
            LOGGER.info("删除失败");
        }
        return flag;
    }
    @Transactional //事件的完整性约束
    public List<Department> findAll() {
        List<Department> departmentDtoList = redisTemplate.boundHashOps(key).values();
        if (departmentDtoList == null || departmentDtoList.size() == 0) {
            //说明缓存中没有部门的数据
            //查询数据库中的数据并放在缓存中去
            departmentDtoList = departmentMapper.select();
            for (Department department : departmentDtoList) {
                //将部门数据依次放入缓存当中去，key：部门id；value：对应的部门信息
                redisTemplate.boundHashOps(key).put(department.getId(), department);
                LOGGER.info("部门信息数据：findAll -> 从数据库中读取放到缓存中" + department.getId() + " -> " + department.getName());
            }
        } else {
            LOGGER.info("部门信息数据：findAll -> 从缓存中读取");
        }
        return departmentDtoList;
    }
}
