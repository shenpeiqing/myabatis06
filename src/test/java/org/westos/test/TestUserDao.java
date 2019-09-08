package org.westos.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.westos.dao.IUserDao;
import org.westos.domain.User;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: ShenMouMou
 * @CreateTime: 2019-07-08 11:17
 * @Description:西部开源教育科技有限公司
 */
public class TestUserDao {
    public static void main(String[] args) throws IOException {
        //加载主配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //构建SessionFactory
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
        //获取sqlSession
        SqlSession sqlSession = sessionFactory.openSession();
        //获取接口的代理对象
        IUserDao dao = sqlSession.getMapper(IUserDao.class);
        //根据id查询出一个用户
        User user = dao.selectUserById(59);

        System.out.println(user);
        user.setUsername("哈哈");
        user.setAddress("北京");
        //进行更新的操作
        dao.updateUser(user);

        //一级缓存是 SqlSession 范围的缓存，当调用 SqlSession 的修改，添加，删除，commit()，close() 等方法时，就会清空一级缓存。

        //清空一级缓存
       // sqlSession.clearCache();

        //进行第二次的查询
        User user2 = dao.selectUserById(59);
        System.out.println(user2);

        System.out.println(user==user2);

        sqlSession.close();
        //释放资源
        in.close();
    }
}
