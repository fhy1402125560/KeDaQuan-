package com.heima.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.article.dtos.ArticleDto;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.mess.ArticleVisitStreamMess;
import org.springframework.web.bind.annotation.RequestBody;

public interface ApArticleService extends IService<ApArticle> {

    /**
     *
     * @param dto
     * @param type 1：加载更多 2：加载最新
     * @return
     */
    public ResponseResult load(ArticleHomeDto dto, Short type);

    /**
     *
     * @param dto
     * @param type 1：加载更多 2：加载最新
     * @param firstPage 是否为首页
     * @return
     */
    public ResponseResult load2(ArticleHomeDto dto, Short type, boolean firstPage);


    /**
     * 保存app端相关文章
     * @param dto
     * @return
     */
    public ResponseResult saveArticle(ArticleDto dto);

    /**
     * 更新文章分值
     * 更新缓存中热点文章数据
     * @param mess
     */
    public void updateScore(ArticleVisitStreamMess mess);
}
