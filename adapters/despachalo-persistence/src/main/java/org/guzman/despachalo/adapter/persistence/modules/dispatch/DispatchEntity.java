package org.guzman.despachalo.adapter.persistence.modules.dispatch;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.guzman.despachalo.adapter.persistence.modules.company.user.UserEntity;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "DESPACHO")
public class DispatchEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "analista_id", nullable = false)
    private Long analystId;

    @Column(name = "estado", length = 10, nullable = false)
    private String state;

    @Column(name = "estado_sol_ruta", length = 10)
    private String routeRequestState;

    @Column(name = "pedidos_programados", nullable = false)
    private Integer programmedOrders = 0;

    @Column(name = "fecha_despacho", nullable = false)
    private LocalDateTime dispatchAt;

    @Column(name = "fecha_creado")
    private LocalDateTime createdAt;

    @Column(name = "fecha_actualizado")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "analista_id", insertable = false, updatable = false)
    private UserEntity analyst;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DispatchEntity that = (DispatchEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
