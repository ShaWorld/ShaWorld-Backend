package com.dsm.shaworld.domain.user.entity;

import lombok.*;
import javax.persistence.*;

@Getter
@Builder
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "user_nickname", nullable = false, length = 8)
    private String userNickname;

    @Column(name = "user_password", nullable = false, length = 20)
    private String userPassword;

    @Column(name = "user_profile", nullable = true)
    private String userProfile = null;

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }
}
