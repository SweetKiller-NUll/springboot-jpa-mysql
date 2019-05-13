package top.inger.JpaDemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.inger.JpaDemo.domain.Cart;
import top.inger.JpaDemo.repository.CartRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/cart")
//@Api(tags="购物车API")
public class CartController {

    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    /**
     * 创建一个购物车  —>  POST:  /api/cart/create
     */
//	@ApiOperation(value="创建购物车", notes="根据User对象创建购物车")
    @PostMapping("/create")
    public Cart createCart(@RequestBody @Valid Cart cart) {
        return cartRepository.saveAndFlush(cart);
    }

    /**
     * 查询所有的购物车  —>  GET:  /api/cart/findAll
     */
//	@ApiOperation(value="获取购物车列表", notes="")
    @GetMapping("/findAll")
    public List<Cart> findAllCart() {
        return cartRepository.findAll();
    }

    /**
     * 查询某个id的购物车  —>  GET:  /api/cart/findById/{cartId}
     */
//	@ApiOperation(value="获取购物车详细信息", notes="根据url的id来获取购物车详细信息")
    @GetMapping("/findById/{cartId}")
    public Optional<Cart> findCartById(@PathVariable(value = "cartId") int id) {
        return cartRepository.findById(id);
    }

    /**
     * 更新某个id的购物车  —>  GET:  /api/cart/updateById/{cartId}
     */
//	@ApiOperation(value="更新购物车详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新购物车详细信息")
    @PutMapping("/updateById/{cartId}")
    public @Valid Cart updateCartById(
            @PathVariable(value = "cartId") int id, @RequestBody @Valid Cart uptCart) {
        Optional<Cart> cart = cartRepository.findById(id);
        uptCart.setCartId(cart.get().getCartId());
        return cartRepository.saveAndFlush(uptCart);
    }

    /**
     * 删除某个id的购物车  —>  DELETE:  /api/cart/deleteById/{cartId}
     */
//	@ApiOperation(value="删除购物车", notes="根据url的id来指定删除对象")
    @DeleteMapping("/deleteById/{cartId}")
    public ResponseEntity<?> deleteCartById(@PathVariable(value = "cartId") int id) {
        cartRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 通过usid和status查询该用户的购物车内容  —>  POST:  /api/cart/findCartByUsIdAndStatus/{usId}/{cartStatus}
     */
    @PostMapping("/findCartByUsIdAndStatus/{usId}/{cartStatus}")
    public List<Cart> findCartByUsIdAndStatus(
            @PathVariable(value = "usId") int usId,
            @PathVariable(value = "cartStatus") byte status) {
        List<Cart> cart = cartRepository.findCartByUsIdAndCartStatus(usId, status);
        return cart;
    }

    /**
     * 下单后通过usid删除该用户的购物车内容  —>  POST:  /api/cart/deleteCartsByUsId/{usId}
     */
    @DeleteMapping("/deleteCartsByUsId/{usId}")
    public ResponseEntity<?> deleteCartsByUsId(@PathVariable(value = "usId") int usId) {
        cartRepository.deleteCartsByUsId(usId);
        return ResponseEntity.ok().build();
    }

}
