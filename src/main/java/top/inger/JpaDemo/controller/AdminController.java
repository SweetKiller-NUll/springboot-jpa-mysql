package top.inger.JpaDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.inger.JpaDemo.domain.Admin;
import top.inger.JpaDemo.repository.AdminRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
//@Api(tags="管理员API")
public class AdminController {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /**
     * 创建一个管理员  —>  POST:  /api/admin/create
     */
//	@ApiOperation(value="创建管理员", notes="根据User对象创建管理员")
    @PostMapping("/create")
    public Admin createAdmin(@RequestBody @Valid Admin admin) {
        return adminRepository.saveAndFlush(admin);
    }

    /**
     * 查询所有的管理员  —>  GET:  /api/admin/findAll
     */
//	@ApiOperation(value="获取管理员列表", notes="")
    @GetMapping("/findAll")
    public List<Admin> findAllAdmin() {
        return adminRepository.findAll();
    }

    /**
     * 查询某个id的管理员  —>  GET:  /api/admin/findById/{adminId}
     */
//	@ApiOperation(value="获取管理员详细信息", notes="根据url的id来获取管理员详细信息")
    @GetMapping("/findById/{adminId}")
    public Optional<Admin> findAdminById(@PathVariable(value = "adminId") int id) {
        return adminRepository.findById(id);
    }

    /**
     * 更新某个id的管理员  —>  GET:  /api/admin/updateById/{adminId}
     */
//	@ApiOperation(value="更新管理员详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新管理员详细信息")
    @PutMapping("/updateById/{adminId}")
    public @Valid Admin updateAdminById(
            @PathVariable(value = "adminId") int id, @RequestBody @Valid Admin uptAdmin) {
        Optional<Admin> admin = adminRepository.findById(id);
        uptAdmin.setAdminId(admin.get().getAdminId());
        return adminRepository.saveAndFlush(uptAdmin);
    }

    /**
     * 删除某个id的管理员  —>  DELETE:  /api/admin/deleteById/{adminId}
     */
//	@ApiOperation(value="删除管理员", notes="根据url的id来指定删除对象")
    @DeleteMapping("/deleteById/{adminId}")
    public ResponseEntity<?> deleteAdminById(@PathVariable(value = "adminId") int id) {
        adminRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 查询管理员username是否存在  —>  POST:  /api/admin/checkAdminUsName/{adminUsName}
     */
    @PostMapping("/checkAdminUsName/{adminUsName}")
    public String checkAdminUsName(@PathVariable(value = "adminUsName") String adminUsName) {
        Boolean flag = adminRepository.existsAdminByAdminUsName(adminUsName);
        return flag ? "exist" : "doesn't exist";
    }

    /**
     * 查询管理员是否存在  —>  POST:  /api/admin/checkAdmin/{adminUsName}
     */
    @PostMapping("/checkAdmin/{adminUsName}")
    public Optional<Admin> checkAdmin(@PathVariable(value = "adminUsName") String adminUsName) {
        Optional<Admin> admin = adminRepository.findAdminByAdminUsName(adminUsName);
        return admin;
    }

}
