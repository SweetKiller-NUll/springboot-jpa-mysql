package top.inger.JpaDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.inger.JpaDemo.domain.Admin;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>, JpaSpecificationExecutor<Admin> {
    //检验用户名是否存在
    boolean existsAdminByAdminUsName(String adminusname);
    //检验用户是否存在
    Optional<Admin> findAdminByAdminUsName(String adminusname);
}
