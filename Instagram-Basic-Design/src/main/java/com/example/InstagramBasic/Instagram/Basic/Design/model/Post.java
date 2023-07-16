package com.example.InstagramBasic.Instagram.Basic.Design.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp postCreatedDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp postUpdatedDate;

    private String postData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JoinColumn(name = "fk_user_id")
    private User postOwner;
}
