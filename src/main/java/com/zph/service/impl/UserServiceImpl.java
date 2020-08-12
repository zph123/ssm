package com.zph.service.impl;

import com.zph.dao.UserDao;
import com.zph.domain.User;
import com.zph.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User selectUserByNameAndPassword(User user) {
        return userDao.selectUserByNameAndPassword(user);
    }

    @Override
    public User selectArticleForUser(User user) {
        return userDao.selectArticleForUser(user);
    }
}
