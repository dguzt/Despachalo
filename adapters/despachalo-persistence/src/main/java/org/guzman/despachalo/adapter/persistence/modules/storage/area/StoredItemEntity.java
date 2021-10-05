package org.guzman.despachalo.adapter.persistence.modules.storage.area;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@ToString
@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "ZONA_ALMACENAMIENTO")
public class StoredItemEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "zona_id", nullable = false)
    private Long zoneId;

    @Column(name = "fecha_almacenado", nullable = false)
    private LocalDateTime storedAt;

    @Column(name = "fecha_removido")
    private LocalDateTime removedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StoredItemEntity that = (StoredItemEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
