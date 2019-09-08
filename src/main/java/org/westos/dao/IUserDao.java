package org.westos.dao;

import org.apache.ibatis.annotations.*;
import org.westos.domain.User;

/**
 * @Author: ShenMouMou
 * @CreateTime: 2019-07-05 15:10
 * @Description:西部开源教育科技有限公司
 */
public interface IUserDao {
    //根据id查询出一个用户
    @Select("select * from user where id=#{uid}")
    User selectUserById(Integer id);


    //更新用户信息
    @Update("update user set username=#{username},address=#{address} where id=#{id}")
    void updateUser(User user);
}
