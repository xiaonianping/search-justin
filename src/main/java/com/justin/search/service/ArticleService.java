package com.justin.search.service;

import com.justin.search.mapper.ArticleMapper;
import com.justin.search.model.Article;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleService {

    @Resource
    private ArticleMapper articleMapper;


    public int deleteByPrimaryKey(Long id) {
        return articleMapper.deleteByPrimaryKey(id);
    }


    public int insert(Article record) {
        return articleMapper.insert(record);
    }


    public int insertSelective(Article record) {
        return articleMapper.insertSelective(record);
    }


    public List<Article> selectById(Long id, int size) {
        return articleMapper.selectById(id, size);
    }

    public Article selectByPrimaryKey(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(Article record) {
        return articleMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Article record) {
        return articleMapper.updateByPrimaryKey(record);
    }

}
