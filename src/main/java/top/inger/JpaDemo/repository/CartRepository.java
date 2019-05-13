package top.inger.JpaDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.inger.JpaDemo.domain.Cart;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>, JpaSpecificationExecutor<Cart> {
//    //根据用户ID查找购物车内容
//    List<Cart> findCartByUsId(Integer usId);

    //根据用户ID和购物车状态查找购物车内容
    List<Cart> findCartByUsIdAndCartStatus(Integer usId, Byte status);

    //下单后根据用户ID删除购物车内容
    @Transactional
    void deleteCartsByUsId(Integer usId);

}
