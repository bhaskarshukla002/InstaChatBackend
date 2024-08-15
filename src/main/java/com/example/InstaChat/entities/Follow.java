package com.example.InstaChat.entities;

//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "follows")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Follow {
//
////    @Embeddable
////    public static class FollowId implements Serializable {
////        @Column(name = "following_id")
////        private Long followingId;
////        @Column(name = "follower_id")
////        private Long followerId;
////        // Getters, setters, equals, and hashCode...
////    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private String id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
////    @MapsId("followerId")
//    @JoinColumn(name = "user_id")
//    User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("user")
//    @JoinColumn(name = "user_following_id")
//    User following;

//    @Column(nullable = false, updatable = false)
//    private LocalDateTime createdAt = LocalDateTime.now();
//}
