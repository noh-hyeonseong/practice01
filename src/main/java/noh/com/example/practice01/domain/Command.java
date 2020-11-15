package noh.com.example.practice01.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.security.PrivateKey;

@Embeddable
@Entity
@Getter
@Setter
public class Command {

    @Id
    @GeneratedValue
    @Column(name = "command_id")
    private Long id;

    private String title;
    private String content;

    @ManyToOne @JoinColumn(name = "post_id")
    private Post post;

}
