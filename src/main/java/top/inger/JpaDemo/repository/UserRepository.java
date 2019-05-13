package top.inger.JpaDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.inger.JpaDemo.domain.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    //检验用户名是否存在
    boolean existsUserByUserUsName(String userusname);
//    通过用户名检验用户是否存在
//    Optional<User> findByUserUsName(String userusname);

    //通过用户名查找用户
    Optional<User> findUserByUserUsName(String userusname);

    //通过用户名和邮箱验证用户是否存在
    Optional<User> findUserByUserUsNameAndUserEmail(String userusname, String useremail);
}
