package com.itmuch.cloud.study.controller;

import com.itmuch.cloud.study.entity.PublicNum;
import com.itmuch.cloud.study.entity.User;
import com.itmuch.cloud.study.service.PublicNumService;
import com.itmuch.cloud.study.service.RedisUtil;
import com.itmuch.cloud.study.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;


@RestController
public class UserController {
  private final Logger logger=Logger.getLogger(getClass());
  @Autowired
  private PublicNumService publicNumService;
  @Autowired
  private UserService userService;
  @Autowired
  private RedisUtil redisUtil;
  @Autowired
  private MongoTemplate mongoTemplate;

  @GetMapping("/{id}")
  public User findById(@PathVariable Long id) {
    if (redisUtil.exists(String.valueOf(id))){
        logger.info("从redis获取数据");
        Object result=redisUtil.get(String.valueOf(id));
        if(result instanceof User){
          return (User)result;
        }
    }else{
      logger.info("从数据库获取数据");
      User findOne = this.userService.findOne(id);
      //放入redis
      redisUtil.set(String.valueOf(id),findOne) ;
      return findOne;
    }
    return  null;

  }

  @GetMapping("/publicnum/{id}")
  public List<PublicNum> findPublicInfoById(@PathVariable String id) {

      List<PublicNum> numInfo = this.publicNumService.findAll();

    return  numInfo;

  }
  @GetMapping("/user/all/{id}")
  public List<User> findAll(@PathVariable String id) {

    List<User> infos = this.userService.findAll();

    return  infos;

  }

  /**
   * save use before findName
   * @return
   */
  @GetMapping("/save")
  public User save() {
    User user = new User();
    user.setId(1l);
    user.setAge(23);
    user.setBalance(new BigDecimal(5000));
    user.setName("JAVA");
    user.setUsername("zhaokui");
    userService.save(user);
    return user;
  }

  @GetMapping("/find")
  public List<User> find() {
    List<User> userList = mongoTemplate.findAll(User.class);
    return userList;
  }
}
