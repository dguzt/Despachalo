package org.guzman.despachalo.adapter.persistence.modules.storage.area;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@ToString
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
