package top.inger.JpaDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.inger.JpaDemo.domain.FeedBack;
import top.inger.JpaDemo.repository.FeedBackRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/feedBack")
//@Api(tags="反馈API")
public class FeedBackController {

    private final FeedBackRepository feedBackRepository;

    @Autowired
    public FeedBackController(FeedBackRepository feedBackRepository) {
        this.feedBackRepository = feedBackRepository;
    }

    /**
     * 创建一个反馈  —>  POST:  /feedBack/create
     */
//	@ApiOperation(value="创建反馈", notes="根据FeedBack对象创建反馈")
    @PostMapping("/create")
    public FeedBack createFeedBack(@RequestBody @Valid FeedBack feedBack) {
        return feedBackRepository.saveAndFlush(feedBack);
    }

    /**
     * 查询所有的反馈  —>  GET:  /feedBack/findAll
     */
//	@ApiOperation(value="获取反馈列表", notes="")
    @GetMapping("/findAll")
    public List<FeedBack> findAllFeedBack() {
        return feedBackRepository.findAll();
    }

    /**
     * 查询某个id的反馈  —>  GET:  /feedBack/findById/{feedBackId}
     */
//	@ApiOperation(value="获取反馈详细信息", notes="根据url的id来获取反馈详细信息")
    @GetMapping("/findById/{feedBackId}")
    public Optional<FeedBack> findFeedBackById(@PathVariable(value = "feedBackId") int id) {
        return feedBackRepository.findById(id);
    }

    /**
     * 更新某个id的反馈  —>  GET:  /feedBack/updateById/{feedBackId}
     */
//	@ApiOperation(value="更新反馈详细信息", notes="根据url的id来指定更新对象，并根据传过来的feedBack信息来更新反馈详细信息")
    @PutMapping("/updateById/{feedBackId}")
    public @Valid FeedBack updateFeedBackById(
            @PathVariable(value = "feedBackId") int id, @RequestBody @Valid FeedBack uptFeedBack) {
        Optional<FeedBack> feedBack = feedBackRepository.findById(id);
        uptFeedBack.setFeedBackId(feedBack.get().getFeedBackId());
        return feedBackRepository.saveAndFlush(uptFeedBack);
    }

    /**
     * 删除某个id的反馈  —>  DELETE:  /feedBack/deleteById/{feedBackId}
     */
//	@ApiOperation(value="删除反馈", notes="根据url的id来指定删除对象")
    @DeleteMapping("/deleteById/{feedBackId}")
    public ResponseEntity<?> deleteFeedBackById(@PathVariable(value = "feedBackId") int id) {
        feedBackRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
