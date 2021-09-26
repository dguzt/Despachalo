package org.guzman.despachalo.adapter.persistence.modules.dispatch;

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
