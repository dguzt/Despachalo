package org.guzman.despachalo.adapter.persistence.modules.sync.driver;

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
@Entity @Table(name = "CONDUCTOR")
public class DriverEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_documento", length = 10, nullable = false)
    private String documentType;

    @Column(name = "num_documento", length = 15, nullable = false)
    private String documentNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DriverEntity that = (DriverEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 285330853;
    }
}
