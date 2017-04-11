package com.xzit.ar.portal.service.forum.impl;

import com.xzit.ar.common.exception.ServiceException;
import com.xzit.ar.common.mapper.info.CommentMapper;
import com.xzit.ar.common.mapper.info.InformationMapper;
import com.xzit.ar.common.page.Page;
import com.xzit.ar.common.po.info.Comment;
import com.xzit.ar.common.util.CommonUtil;
import com.xzit.ar.portal.service.forum.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * TODO ${TODO}
 * Created by 董亮亮 on 2017/4/9.
 */
@Service("postService")
public class PostServiceImpl implements PostService {

    @Resource
    private InformationMapper informationMapper;

    @Resource
    private CommentMapper commentMapper;

    /**
     * TODO 根据info_id查询帖子详情
     *
     * @param postId
     * @return post
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> getPostById(Integer postId) throws ServiceException {
        Map<String, Object> post = null;
        try {
            // 参数校验
            if (CommonUtil.isNotEmpty(postId)){
                // 累计浏览量
                informationMapper.increaseViews(postId);
                // 加载帖子详情
                post = informationMapper.getInfoDetailById(postId);
            }
        } catch (Exception e) {
            throw new ServiceException("加载信息详情时发生异常！");
        }
        return post;
    }

    /**
     * TODO 喜欢帖子
     *
     * @param postId 帖子id
     * @return 喜欢此帖子的人数
     * @throws ServiceException
     */
    @Override
    public Integer lovePost(Integer postId) throws ServiceException {
        int loves = 0;
        try {
            // 参数校验
            if (CommonUtil.isNotEmpty(postId)){
                // 点赞
                if (informationMapper.increaseLoves(postId) > 0 ) {
                    loves = 1;
                    // 查询点赞数
                    loves = informationMapper.getLoves(postId);
                }
            }
        } catch (Exception e) {
            throw new ServiceException("给文章点赞时发生异常!");
        }
        return loves;
    }

    /**
     * TODO 存储评论内容
     *
     * @param comment
     * @return
     * @throws ServiceException
     */
    @Override
    public Integer commentPost(Comment comment) throws ServiceException {
        try {
            // 参数校验
            if (comment != null && CommonUtil.isNotEmpty(comment.getUserId()) && CommonUtil.isNotEmpty(comment.getInfoId())){
                // 存储评论内容
                commentMapper.save(comment);
                // 累计评论
                informationMapper.increaseComments(comment.getInfoId());
            }
        } catch (Exception e) {
            throw new ServiceException("评论时发生异常！");
        }
        return null;
    }

    /**
     * TODO 动态加载评论列表
     *
     * @param page
     * @param postId
     * @return
     * @throws ServiceException
     */
    @Override
    public List<Map<String, Object>> dynamicLoadComment(Page<Map<String, Object>> page, Integer postId) throws ServiceException {
        List<Map<String, Object>> comments = null;
        try {
            // 参数校验
            if (CommonUtil.isNotEmpty(postId)){
                // 加载评论
                comments = commentMapper.dynamicLoadComment(page, postId);
            }
        } catch (Exception e) {
            throw new ServiceException("加载评论时发生异常！");
        }
        return comments;
    }

    /**
     * TODO 查询论坛帖子列表
     * @param page     分页类
     * @param queryStr 查询条件
     * @return 帖子list
     * @throws ServiceException
     */
    @Override
    public List<Map<String, Object>> queryPosts(Page<Map<String, Object>> page, String queryStr) throws ServiceException {
        List<Map<String, Object>> posts = null;
//        try {
            // 校验参数
              if (CommonUtil.isEmpty(queryStr)){
                  queryStr = "";
              }
            queryStr = "%" + queryStr + "%";
            // 根据条件查询帖子
            posts = informationMapper.queryInfos(page, queryStr,"BBS", "%%", "A");
//        } catch (Exception e) {
//            throw new ServiceException("加载论坛帖子时发生异常！");
//        }
        return posts;
    }
}
