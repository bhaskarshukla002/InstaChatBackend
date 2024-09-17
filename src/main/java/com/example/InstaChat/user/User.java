package com.example.InstaChat.user;

import com.example.InstaChat.comment.Comment;
import com.example.InstaChat.like.model.Like;
import com.example.InstaChat.posts.model.Post;
import jakarta.persistence.*;
import lombok.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="users")
public class User implements UserDetails, Principal {
    @Id
    @Column(name="user_id")
    private String userId;
    @Column(name="user_name",unique = true)
    private String userName;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean accountLocked;
    private boolean enabled;
    @Column(name="profile_picture")
    private String profilePicture;
    private String bio;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_follows",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private List<User> following;

    @ManyToMany(mappedBy = "following", fetch = FetchType.EAGER)
    private List<User> followers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Like> likes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        if(this.userName==null)
        return this.email;
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
