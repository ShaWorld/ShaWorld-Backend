package com.dsm.shaworld.domain.apply.entity;

import com.dsm.shaworld.domain.post.entity.Post;
import com.dsm.shaworld.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@Entity
@Table(name = "apply")
@NoArgsConstructor
@AllArgsConstructor
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_id")
    private int applyId;

    @ManyToOne
    @JoinColumn(name = "apply_post", nullable = false)
    private Post applyPost;

    @ManyToOne
    @JoinColumn(name = "apply_applicant", nullable = false)
    private User applyApplicant;

    @Column(name = "apply_state", nullable = false)
    private String applyState;
}
