package noh.com.example.practice01.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue         // @Id : 기본키 설정, @GeneratedValue : 기본키 자동 생성
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String content;

    @Embedded
    @OneToMany(mappedBy = "post")
    private List<Command> commands = new ArrayList<>();



}
