package com.dsm.shaworld.domain.post.entity;

import com.dsm.shaworld.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;

    @Column(name = "post_thumbnail", nullable = true)
    private String postThumbnail;

    @Column(name = "post_title", nullable = false)
    private String postTitle;

    @ManyToOne
    @JoinColumn(name = "post_author", referencedColumnName = "id", nullable = false)
    private User postAuthor;

    @Column(name = "post_address", nullable = false)
    private String postAddress;

    @Column(name = "post_detail", nullable = true)
    private String postDetail;

    @Column(name = "post_price", nullable = false)
    private int postPrice;

    @Column(name = "post_date", nullable = false)
    private LocalDateTime postDate;

    public void setPostThumbnail(String postThumbnail) {
        this.postThumbnail = postThumbnail;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public void setPostDetail(String postDetail) {
        this.postDetail = postDetail;
    }

    public void setPostPrice(int postPrice) {
        this.postPrice = postPrice;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }
}
