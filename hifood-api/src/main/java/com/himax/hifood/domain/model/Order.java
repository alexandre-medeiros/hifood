package com.himax.hifood.domain.model;

import com.himax.hifood.domain.enums.StatusOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal subtotal;
    private BigDecimal deliveryFees;
    private BigDecimal total;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private StatusOrder status;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    private OffsetDateTime confirmedAt;
    private OffsetDateTime canceledAt;
    private OffsetDateTime deliveredAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PaymentWay paymentWay;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Users client;

    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private List<OrderItem> itens = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return getId() != null && Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}