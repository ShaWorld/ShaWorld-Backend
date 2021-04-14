package com.dsm.shaworld.domain.apply.controller;

import com.dsm.shaworld.domain.apply.dto.GetReceiveAppliesResponse;
import com.dsm.shaworld.domain.apply.dto.GetSendAppliesResponse;
import com.dsm.shaworld.domain.apply.service.ApplyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/apply")
public class ApplyController {
    private final ApplyService applyService;

    public ApplyController(ApplyService applyService) { this.applyService = applyService; }

    @GetMapping("/send-applies")
    @ResponseStatus(value = HttpStatus.OK)
    public List<GetSendAppliesResponse> getSendApplies(@RequestHeader(value = "Authorization") String token) {
        return applyService.getSendApplies(token);
    }

    @GetMapping("/receive-applies")
    @ResponseStatus(value = HttpStatus.OK)
    public List<GetReceiveAppliesResponse> getReceiveApplies(@RequestHeader(value = "Authorization") String token) {
        return applyService.getReceiveApplies(token);
    }

    @GetMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void applyPost(@RequestHeader(value = "Authorization") String token, @PathVariable(value = "postId") int postId) {
        applyService.applyPost(token, postId);
    }
}
