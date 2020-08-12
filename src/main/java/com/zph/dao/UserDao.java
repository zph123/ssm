package com.zph.dao;

import com.zph.domain.User;

public interface UserDao {
    User selectUserByNameAndPassword(User user);
    User selectArticleForUser(User user);
}
