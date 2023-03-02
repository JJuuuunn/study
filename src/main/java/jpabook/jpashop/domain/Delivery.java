package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order; // 주문 정보

    @Embedded
    private Address address; // 배송 주소

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; // 배송 상태(READY, COMP)
}
