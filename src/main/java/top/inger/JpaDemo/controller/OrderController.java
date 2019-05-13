package top.inger.JpaDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.inger.JpaDemo.domain.Order;
import top.inger.JpaDemo.repository.OrderRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/order")
//@Api(tags="订单API")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * 创建一个订单  —>  POST:  /api/order/create
     */
//	@ApiOperation(value="创建订单", notes="根据Order对象创建订单")
    @PostMapping("/create")
    public Order createOrder(@RequestBody @Valid Order order) {
        return orderRepository.saveAndFlush(order);
    }

    /**
     * 查询所有的订单  —>  GET:  /api/order/findAll
     */
//	@ApiOperation(value="获取订单列表", notes="")
    @GetMapping("/findAll")
    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    /**
     * 查询某个id的订单  —>  GET:  /api/order/findById/{orderId}
     */
//	@ApiOperation(value="获取订单详细信息", notes="根据url的id来获取订单详细信息")
    @GetMapping("/findById/{orderId}")
    public Optional<Order> findOrderById(@PathVariable(value = "orderId") int id) {
        return orderRepository.findById(id);
    }

    /**
     * 更新某个id的订单  —>  GET:  /api/order/updateById/{orderId}
     */
//	@ApiOperation(value="更新订单详细信息", notes="根据url的id来指定更新对象，并根据传过来的order信息来更新订单详细信息")
    @PutMapping("/updateById/{orderId}")
    public @Valid Order updateOrderById(
            @PathVariable(value = "orderId") int id, @RequestBody @Valid Order uptOrder) {
        Optional<Order> order = orderRepository.findById(id);
        uptOrder.setOrderId(order.get().getOrderId());
        return orderRepository.saveAndFlush(uptOrder);
    }

    /**
     * 删除某个id的订单  —>  DELETE:  /api/order/deleteById/{orderId}
     */
//	@ApiOperation(value="删除订单", notes="根据url的id来指定删除对象")
    @DeleteMapping("/deleteById/{orderId}")
    public ResponseEntity<?> deleteOrderById(@PathVariable(value = "orderId") int id) {
        orderRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 通过usid和status查询该用户的订单  —>  POST:  /api/order/findOrderByUsId/{usId}
     */
    @PostMapping("/findOrderByUsIdAndStatus/{usId}/{status}")
    public List<Order> findOrderByUsIdAndStatus(
            @PathVariable(value = "usId") int usId,
            @PathVariable(value = "status") byte status) {
        List<Order> order = orderRepository.findOrderByUsIdAndStatus(usId, status);
        return order;
    }


}
