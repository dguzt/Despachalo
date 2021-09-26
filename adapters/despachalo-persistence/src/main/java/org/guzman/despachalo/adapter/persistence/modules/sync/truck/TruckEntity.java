package org.guzman.despachalo.adapter.persistence.modules.sync.truck;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@ToString
@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "VEHICULO")
public class TruckEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TruckEntity that = (TruckEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 285330853;
    }
}
