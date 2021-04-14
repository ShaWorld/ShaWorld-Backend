package com.dsm.shaworld.domain.apply.service;

import com.dsm.shaworld.domain.apply.dto.GetReceiveAppliesResponse;
import com.dsm.shaworld.domain.apply.dto.GetSendAppliesResponse;
import com.dsm.shaworld.domain.apply.entity.Apply;
import com.dsm.shaworld.domain.apply.repository.ApplyRepository;
import com.dsm.shaworld.domain.post.entity.Post;
import com.dsm.shaworld.domain.post.service.PostService;
import com.dsm.shaworld.domain.user.entity.User;
import com.dsm.shaworld.domain.user.service.UserService;
import com.dsm.shaworld.global.exception.ApplyDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final UserService userService;
    private final PostService postService;
    private final ApplyRepository applyRepository;

    public List<GetSendAppliesResponse> getSendApplies(String token) {
        User user = userService.getInfoByTokenForServer(token);
        List<Apply> apply = applyRepository.findByApplyApplicant(user);

        return apply.stream().map((item) ->
            GetSendAppliesResponse.builder()
                .applyId(item.getApplyId())
                .applyPostId(item.getApplyPost().getPostId())
                .applyPostTitle(item.getApplyPost().getPostTitle())
                .applyState(item.getApplyState())
                .build()
        ).collect(Collectors.toList());
    }

    public List<GetReceiveAppliesResponse> getReceiveApplies(String token) {
        User user = userService.getInfoByTokenForServer(token);
        List<Apply> apply = applyRepository.findByApplyPostPostAuthor(user);

        return apply.stream().map((item) ->
            GetReceiveAppliesResponse.builder()
                .applyId(item.getApplyId())
                .applyPostId(item.getApplyPost().getPostId())
                .applyPostTitle(item.getApplyPost().getPostTitle())
                .applyApplicant(item.getApplyApplicant().getUserNickname())
                .build()
        ).collect(Collectors.toList());
    }

    public void applyPost(String token, int postId) {
        User user = userService.getInfoByTokenForServer(token);
        Post post = postService.getPost(postId);

        if(applyRepository.existsByApplyPostAndApplyApplicant(post, user)) {
            throw new ApplyDuplicateException();
        }

        applyRepository.save(
            Apply.builder()
                .applyPost(post)
                .applyApplicant(user)
                .applyState("대기중")
                .build()
        );

        return;
    }
}
