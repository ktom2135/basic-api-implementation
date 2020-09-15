package com.thoughtworks.rslist.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.dto.RsEventDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RsController {

    private List<RsEventDto> rsList = initRsEvents();
    private List<RsEventDto> initRsEvents(){
        List<RsEventDto> rsEventDtos = new ArrayList<>();
        rsEventDtos.add(new RsEventDto("第一条事件", "无分类"));
        rsEventDtos.add(new RsEventDto("第二条事件", "无分类"));
        rsEventDtos.add(new RsEventDto("第三条事件", "无分类"));
        return rsEventDtos;
    }

    @GetMapping("/rs/list")
    public List<RsEventDto> getRsList(@RequestParam(required = false) Integer start, @RequestParam(required = false) Integer end) {
        if (start == null || end == null) {
            return rsList;
        }

        return rsList.subList(start - 1, end);

    }

    @GetMapping("/rs/{index}")
    public RsEventDto getRsEvent(@PathVariable int index) {
        return rsList.get(index - 1);
    }

    @PostMapping("/rs/event")
    public void addRsEvent(@RequestBody String rsEventStr) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        RsEventDto rsEventDto = objectMapper.readValue(rsEventStr, RsEventDto.class);
        rsList.add(rsEventDto);
    }
}
