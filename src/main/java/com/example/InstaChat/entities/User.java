package com.example.InstaChat.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
public class User implements UserDetails {
    @Id
    @Column(name="user_id")
    private String userId;
    @Column(name="user_name")
    private String userName;
    private String name;
    private String email;
    private String password;
    @Column(name="profile_picture")
    private String profilePicture;
    private String bio;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;

//    @OneToMany(mappedBy = "following",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<User> followers;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<User> following;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_follows",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private List<User> following;

    @ManyToMany(mappedBy = "following", fetch = FetchType.EAGER)
    private List<User> followers;


//    @ManyToOne(mappedBy = "")
//    private ListUser user;

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
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
