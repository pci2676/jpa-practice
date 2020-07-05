package com.inflearn.jpa.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    // many to one 관계에서 join column 명시
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", foreignKey = @ForeignKey(name = "FK_MEMBER_ORDER"))
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderDate;

    // one to one 관계에서 join Column 명시
    @OneToOne
    @JoinColumn(name = "DELEVERY_ID", foreignKey = @ForeignKey(name = "FK_ORDER_DELIVERY"))
    private Delivery delivery;

    public void placeMember(Member member) {
        if (hasMember()) {
            this.member.getOrders().remove(this);
        }
        this.member = member;
        member.getOrders().add(this);
    }

    private boolean hasMember() {
        return this.member != null;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.placeOrder(this);
    }

    public void placeDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.placeOrder(this);
    }
}
