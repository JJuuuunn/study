package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMB보ER_ID")
    private Long id;

    @NotEmpty
    private String name; // 회원 이름

    @Embedded
    private Address address; // 회원 주소

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>(); // 회원이 주문한 것들

}
