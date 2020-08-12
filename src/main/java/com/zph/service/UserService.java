package com.zph.service;

import com.zph.domain.User;

public interface UserService {
    User selectUserByNameAndPassword(User user);
    User selectArticleForUser(User user);
}
