package top.inger.JpaDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.inger.JpaDemo.domain.Terms;
import top.inger.JpaDemo.repository.TermsRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/terms")
//@Api(tags="条款API")
public class TermsController {

    private final TermsRepository termsRepository;

    @Autowired
    public TermsController(TermsRepository termsRepository) {
        this.termsRepository = termsRepository;
    }

    /**
     * 创建一个条款  —>  POST:  /terms/create
     */
//	@ApiOperation(value="创建条款", notes="根据Terms对象创建条款")
    @PostMapping("/create")
    public Terms createTerms(@RequestBody @Valid Terms terms) {
        return termsRepository.saveAndFlush(terms);
    }

    /**
     * 查询所有的条款  —>  GET:  /terms/findAll
     */
//	@ApiOperation(value="获取条款列表", notes="")
    @GetMapping("/findAll")
    public List<Terms> findAllTerms() {
        return termsRepository.findAll();
    }

    /**
     * 查询某个id的条款  —>  GET:  /terms/findById/{termsId}
     */
//	@ApiOperation(value="获取条款详细信息", notes="根据url的id来获取条款详细信息")
    @GetMapping("/findById/{termsId}")
    public Optional<Terms> findTermsById(@PathVariable(value = "termsId") int id) {
        return termsRepository.findById(id);
    }

    /**
     * 更新某个id的条款  —>  GET:  /terms/updateById/{termsId}
     */
//	@ApiOperation(value="更新条款详细信息", notes="根据url的id来指定更新对象，并根据传过来的terms信息来更新条款详细信息")
    @PutMapping("/updateById/{termsId}")
    public @Valid Terms updateTermsById(
            @PathVariable(value = "termsId") int id, @RequestBody @Valid Terms uptTerms) {
        Optional<Terms> terms = termsRepository.findById(id);
        uptTerms.setTermsId(terms.get().getTermsId());
        return termsRepository.saveAndFlush(uptTerms);
    }

    /**
     * 删除某个id的条款  —>  DELETE:  /terms/deleteById/{termsId}
     */
//	@ApiOperation(value="删除条款", notes="根据url的id来指定删除对象")
    @DeleteMapping("/deleteById/{termsId}")
    public ResponseEntity<?> deleteTermsById(@PathVariable(value = "termsId") int id) {
        termsRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
