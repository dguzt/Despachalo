package org.guzman.despachalo.adapter.persistence.modules.dispatch.returning;

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
@Entity @Table(name = "ORDEN_DEVOLUCION")
public class ReturnOrderEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "analista_id", nullable = false)
    private Long analystId;

    @Column(name = "motivo", length = 100, nullable = false)
    private String reason;

    @Column(name = "fecha_reporte", nullable = false)
    private LocalDateTime reportedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "analista_id", insertable = false, updatable = false)
    private UserEntity analyst;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReturnOrderEntity that = (ReturnOrderEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
