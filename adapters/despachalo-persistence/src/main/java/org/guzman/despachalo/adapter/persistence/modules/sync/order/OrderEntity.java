package org.guzman.despachalo.adapter.persistence.modules.sync.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.guzman.despachalo.adapter.persistence.modules.dispatch.DispatchEntity;
import org.guzman.despachalo.adapter.persistence.modules.sync.client.EndPointEntity;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "PEDIDO")
public class OrderEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tienda_id", nullable = false)
    private Long storeId;

    @Column(name = "despacho_id")
    private Long dispatchId;

    @Column(name = "estado")
    private String state;

    @Column(name = "fecha_creado")
    private LocalDateTime createdAt;

    @Column(name = "fecha_actualizado")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "despacho_id", insertable = false, updatable = false)
    private DispatchEntity dispatch;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tienda_id", insertable = false, updatable = false)
    private EndPointEntity endPoint;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderEntity that = (OrderEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 285330853;
    }
}
