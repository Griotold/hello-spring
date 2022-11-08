package hello.hellospring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity // JPA가 관리하는 엔티티
public class Member {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // @Column(name = "username")
    private String name;
}
