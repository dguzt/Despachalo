package org.guzman.despachalo.adapter.persistence.modules.storage.area;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.guzman.despachalo.adapter.persistence.modules.company.center.DistributionCenterEntity;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "ZONA_ALMACENAMIENTO")
public class AreaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descripcion", length = 100)
    private String description;

    @Column(name = "capacidad_total")
    private Integer totalCapacity = 0;

    @Column(name = "capacidad_disponible")
    private Integer availableCapacity = 0;

    @Column(name = "centro_distribucion_id", nullable = false)
    private Long centerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="centro_distribucion_id", insertable = false, updatable = false)
    private DistributionCenterEntity center;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AreaEntity that = (AreaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
