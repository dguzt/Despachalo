package org.guzman.despachalo.adapter.persistence.modules.storage.commodity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@ToString
@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "ITEM_MERCADERIA")
public class ItemEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mercaderia_id", nullable = false)
    private Long commodityId;

    @Column(name = "codigo", nullable = false)
    private String code;

    @Column(name = "detalle_producto_id", nullable = false)
    private Long productDetailId;

    @Column(name = "pedido_id", nullable = false)
    private Long orderId;

    @Column(name = "orden_devolucion_id")
    private Long returnOrderId;

    @ManyToOne
    @JoinColumn(name="mercaderia_id", nullable=false, insertable = false, updatable = false)
    private CommodityEntity commodity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ItemEntity that = (ItemEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 285330853;
    }
}
