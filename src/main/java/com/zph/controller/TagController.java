package com.zph.controller;

import com.zph.domain.Tag;
import com.zph.service.TagService;
import com.zph.utils.Msg;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Resource
    private TagService tagService;
    @GetMapping
    public Msg selectTags(){
        List<Tag> tags = tagService.selectTags();
        return Msg.success().add("list",tags);
    }
}
