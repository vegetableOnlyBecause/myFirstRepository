package com.example.user.impl;

import com.alibaba.fastjson.JSON;
import com.example.aop.CacheAop;
import com.example.aop.CacheAopEnums;
import com.example.common.redis.RedisOperator;
import com.example.dao.CommonUserDao;
import com.example.model.CommonUserDO;
import com.example.user.UserService;
import com.example.user.dto.UserCreateDTO;
import com.example.user.dto.UserDTO;
import com.example.user.util.UserUtils;
import com.github.pagehelper.PageInfo;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @title: 用户信息查询实现类
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 15:11
 * @description:
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private CommonUserDao commonUserDao;
    @Resource
    private RedisOperator redisOperator;
    @Resource
    private RestHighLevelClient restHighLevelClient;

    private static final String symbol = "#";

    @Override
    public int saveUserInfo(UserCreateDTO create) throws IOException {
        CommonUserDO userDO = UserUtils.dto2do(create);
        if (null == userDO){
            // 抛错;
        }
        IndexRequest request = new IndexRequest("kuang_index");
        // 规则 put /kuang_index/_doc/1
        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));
        // 将我们的数据放入请求 json
        request.source(JSON.toJSONString(userDO), XContentType.JSON);
        // 客户端发送请求 , 获取响应的结果
        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(indexResponse.toString()); //
        System.out.println(indexResponse.status()); // 对应我们命令返回的状态CREATED
        return commonUserDao.saveUserInfo(userDO);
    }

    @Override
    public int deleteUserById(String userId) {
        int count = commonUserDao.deleteUserById(userId);
        if (count > 0){
            redisOperator.del(CacheAopEnums.GET_USER_BY_ID + symbol + userId);
        }
        return count;
    }

    @Override
    @CacheAop(operateEnums = CacheAopEnums.GET_USER_BY_ID)
    public UserDTO getUserById(String userId) {
        CommonUserDO userDO = commonUserDao.getUserById(userId);
        return UserUtils.do2Dto(userDO);
    }


    @Override
    public PageInfo<UserDTO> listUserByConditions(Map<String, Object> condition, int page, int pageSize){
        PageInfo<CommonUserDO> doPage = commonUserDao.listUserByConditions(condition, page, pageSize);
        List<CommonUserDO> dos = doPage.getList();
        List<UserDTO> dtos = UserUtils.dos2Dtos(dos);
        return new PageInfo<>(dtos);
    }
}
