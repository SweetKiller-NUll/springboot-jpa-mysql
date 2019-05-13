package top.inger.JpaDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.inger.JpaDemo.domain.User;
import top.inger.JpaDemo.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
//@Api(tags="会员API")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 创建一个会员  —>  POST:  /api/user/create
     */
//	@ApiOperation(value="创建会员", notes="根据User对象创建会员")
    @PostMapping("/create")
    public User createUser(@RequestBody @Valid User user) {
        return userRepository.saveAndFlush(user);
    }

    /**
     * 查询所有的会员  —>  GET:  /api/user/findAll
     */
//	@ApiOperation(value="获取会员列表", notes="")
    @GetMapping("/findAll")
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    /**
     * 查询某个id的会员  —>  GET:  /api/user/findById/{userId}
     */
//	@ApiOperation(value="获取会员详细信息", notes="根据url的id来获取会员详细信息")
    @GetMapping("/findById/{userId}")
    public Optional<User> findUserById(@PathVariable(value = "userId") int id) {
        return userRepository.findById(id);
    }

    /**
     * 更新某个id的会员  —>  GET:  /api/user/updateById/{userId}
     */
//	@ApiOperation(value="更新会员详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新会员详细信息")
    @PutMapping("/updateById/{userId}")
    public @Valid User updateUserById(
            @PathVariable(value = "userId") int id, @RequestBody @Valid User uptUser) {
        Optional<User> user = userRepository.findById(id);
        uptUser.setUserId(user.get().getUserId());
        return userRepository.saveAndFlush(uptUser);
    }

    /**
     * 删除某个id的会员  —>  DELETE:  /api/user/deleteById/{userId}
     */
//	@ApiOperation(value="删除会员", notes="根据url的id来指定删除对象")
    @DeleteMapping("/deleteById/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable(value = "userId") int id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 查询用户名是否存在  —>  POST:  /api/user/checkUserUsName/{userUsName}
     */
    @PostMapping("/checkUserUsName/{userUsName}")
    public String checkUserUsName(@PathVariable(value = "userUsName") String userUsName){
        Boolean flag = userRepository.existsUserByUserUsName(userUsName);
        return flag? "exist" : "doesn't exist";
    }

    /**
     * 通过用户名查询用户是否存在  —>  POST:  /api/user/checkUser/{userUsName}
     */
    @PostMapping("/checkUser/{userUsName}")
    public Optional<User> checkUserByUserUsName(@PathVariable(value = "userUsName") String userUsName){
        Optional<User> user=userRepository.findUserByUserUsName(userUsName);
        return user;
    }

    /**
     * 通过用户名和邮箱查询用户是否存在  —>  POST:  /api/user/checkUser/{userUsName}/{userEmail}
     */
    @PostMapping("/checkUser/{userUsName}/{userEmail}")
    public Optional<User> checkUserByUserUsNameAndUserEmail(
            @PathVariable(value = "userUsName") String userUsName,
            @PathVariable(value = "userEmail") String userEmail){
        Optional<User> user=userRepository.findUserByUserUsNameAndUserEmail(userUsName,userEmail);
        return user;
    }

//    /**
//     * 查询用户名是否存在  —>  POST:  /api/user/check/{userUsName}
//     */
//    @PostMapping("/check/{userUsName}")
//    public String checkName(@PathVariable(value = "userUsName") String userUsName){
//        Optional<User> user = userRepository.findByUserUsName(userUsName);
//        return user.isPresent() ? "exist" : "doesn't exist";
//    }

}
