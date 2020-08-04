package com.thoughtworks.rslist.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thoughtworks.rslist.domain.RsEvent;
import com.thoughtworks.rslist.domain.User;
import com.thoughtworks.rslist.exception.CommentError;
import com.thoughtworks.rslist.exception.IndexInvalidException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.rslist.api.UserController.users;

@RestController
@Validated
public class RsController {
    private List<RsEvent> rsList = initRsEvent();

    private List<RsEvent> initRsEvent() {
        User user = new User("alibaba", "male", 20, "a@b.com", "11234567890");
        List<RsEvent> initList = new ArrayList<>();
        initList.add(new RsEvent("第一条事件", "无分类", user));
        initList.add(new RsEvent("第二条事件", "无分类", user));
        initList.add(new RsEvent("第三条事件", "无分类", user));
        users.add(user);
        return initList;
    }

    @GetMapping("/rs/list")
    public ResponseEntity<List<RsEvent>> getRsEventBetween(@RequestParam(required = false) Integer start,
                                                           @RequestParam(required = false) Integer end) {
        if (start == null || end == null) {
            return ResponseEntity.ok(rsList);
        }
        return ResponseEntity.ok(rsList.subList(start - 1, end));
    }

    @GetMapping("/rs/{index}")
    public ResponseEntity<RsEvent> getOneRsEvent(@PathVariable int index) throws IndexInvalidException {
        if(index < 0 || index > rsList.size()){
            throw new IndexInvalidException("index is invalid");
        }
        return ResponseEntity.ok(rsList.get(index - 1));
    }

    @PostMapping("/rs/event")
    public ResponseEntity addOneRsEvent(@RequestBody @Valid RsEvent rsEvent) throws JsonProcessingException {
        rsList.add(rsEvent);
        return ResponseEntity.created(null).build();
    }

    @ExceptionHandler(IndexInvalidException.class)
    public ResponseEntity handleException(IndexInvalidException ex){
        CommentError commentError = new CommentError(ex.getMessage());
        return ResponseEntity.badRequest().body(commentError);
    }
}
