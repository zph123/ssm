package com.zph.service.impl;

import com.zph.dao.TagDao;
import com.zph.domain.Tag;
import com.zph.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagDao tagDao;

    @Override
    public List<Tag> selectTags() {
        List<Tag> tags= tagDao.selectTags();
        System.out.println(tags);
        return tags;
    }
}
