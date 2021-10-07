package org.guzman.despachalo.adapter.persistence.modules.sync.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.guzman.despachalo.adapter.persistence.modules.sync.product.ProductDetailEntity;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "LINEA_PEDIDO")
public class OrderLineEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pedido_id", nullable = false)
    private Long orderId;

    @Column(name = "detalle_producto_id", nullable = false)
    private Long productDetailId;

    @Column(name = "cantidad_solicitada", nullable = false)
    private Integer requestedAmount;

    @Column(name = "cantidad_enviada")
    private Integer sentAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", insertable = false, updatable = false)
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "detalle_producto_id", insertable = false, updatable = false)
    private ProductDetailEntity productDetail;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderLineEntity that = (OrderLineEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 285330853;
    }
}
