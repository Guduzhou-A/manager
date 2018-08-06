package com.baicells.manager.service.impl;

import com.baicells.manager.exception.UserException;
import com.baicells.manager.mapper.UserDao;
import com.baicells.manager.model.entity.User;
import com.baicells.manager.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getByUsernameAndPass(String username, String password) {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password))
            return  null;
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName",username);
        criteria.andEqualTo("passWord",password);

        return userDao.selectOneByExample(example);
    }

    @Override
    public User getById(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public void updateUser(int id, String nickName, String oldPass, String newPass) throws UserException {
        User user = userDao.selectByPrimaryKey(id);
        if (user == null){
            throw  new UserException("用户信息获取失败，请稍后再试");
        }

        if (!user.getPassWord().equals(oldPass)){
            throw  new UserException("原密码错误");
        }
        if (StringUtils.isBlank(nickName)){
            throw  new UserException("昵称不能为空");
        }
        if (StringUtils.isBlank(newPass)){
            throw  new UserException("新密码不能为空");
        }
        user.setNickName(nickName);
        user.setPassWord(newPass);
        userDao.updateByPrimaryKey(user);

    }
}
