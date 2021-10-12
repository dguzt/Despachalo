package org.guzman.despachalo.adapter.persistence.modules.route;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.guzman.despachalo.adapter.persistence.modules.programming.ProgrammedVehicleEntity;
import org.guzman.despachalo.adapter.persistence.modules.sync.order.OrderEntity;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "RUTA")
public class RouteEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "orden_entrega", nullable = false)
    private Integer deliveryOrder;

    @Column(name = "vehiculo_id", nullable = false)
    private Long programmedVehicleId;

    @Column(name = "pedido_id", nullable = false)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculo_id", insertable = false, updatable = false)
    private ProgrammedVehicleEntity programmedVehicle;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id", insertable = false, updatable = false)
    private OrderEntity order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RouteEntity that = (RouteEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
