package com.inflearn.jpa.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_ITEM")
@Getter
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID", foreignKey = @ForeignKey(name = "FK_ITEM_ORDER_ITEM"))
    private Item item;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", foreignKey = @ForeignKey(name = "FK_ORDER_ORDER_ITEM"))
    private Order order;

    private Integer orderPrice;
    private Integer count;

    public void placeOrder(final Order order) {
        this.order = order;
    }
}
