package top.inger.JpaDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.inger.JpaDemo.domain.Alcohol;
import top.inger.JpaDemo.repository.AlcoholRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/alcohol")
//@Api(tags="酒类API")
public class AlcoholController {

    private final AlcoholRepository alcoholRepository;

    @Autowired
    public AlcoholController(AlcoholRepository alcoholRepository) {
        this.alcoholRepository = alcoholRepository;
    }

    /**
     * 创建一个酒类  —>  POST:  /alcohol/create
     */
//	@ApiOperation(value="创建酒类", notes="根据Alcohol对象创建酒类")
    @PostMapping("/create")
    public Alcohol createAlcohol(@RequestBody @Valid Alcohol alcohol) {
        return alcoholRepository.saveAndFlush(alcohol);
    }

    /**
     * 查询所有的酒类  —>  GET:  /alcohol/findAll
     */
//	@ApiOperation(value="获取酒类列表", notes="")
    @GetMapping("/findAll")
    public List<Alcohol> findAllAlcohol() {
        return alcoholRepository.findAll();
    }

    /**
     * 查询某个id的酒类  —>  GET:  /alcohol/findById/{alcoholId}
     */
//	@ApiOperation(value="获取酒类详细信息", notes="根据url的id来获取酒类详细信息")
    @GetMapping("/findById/{alcoholId}")
    public Optional<Alcohol> findAlcoholById(@PathVariable(value = "alcoholId") int id) {
        return alcoholRepository.findById(id);
    }

    /**
     * 更新某个id的酒类  —>  GET:  /alcohol/updateById/{alcoholId}
     */
//	@ApiOperation(value="更新酒类详细信息", notes="根据url的id来指定更新对象，并根据传过来的alcohol信息来更新酒类详细信息")
    @PutMapping("/updateById/{alcoholId}")
    public @Valid Alcohol updateAlcoholById(
            @PathVariable(value = "alcoholId") int id, @RequestBody @Valid Alcohol uptAlcohol) {
        Optional<Alcohol> alcohol = alcoholRepository.findById(id);
        uptAlcohol.setAlcoholId(alcohol.get().getAlcoholId());
        return alcoholRepository.saveAndFlush(uptAlcohol);
    }

    /**
     * 删除某个id的酒类  —>  DELETE:  /alcohol/deleteById/{alcoholId}
     */
//	@ApiOperation(value="删除酒类", notes="根据url的id来指定删除对象")
    @DeleteMapping("/deleteById/{alcoholId}")
    public ResponseEntity<?> deleteAlcoholById(@PathVariable(value = "alcoholId") int id) {
        alcoholRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
}
