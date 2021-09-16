package org.guzman.despachalo.adapter.persistence.company;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@ToString
@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "CENTRO_DISTRIBUCION")
public class DistributionCenterEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "ubi_longitud")
    private Double longitude;

    @Column(name = "ubi_latitud")
    private Double latitude;

    @Column(name = "direccion")
    private String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DistributionCenterEntity that = (DistributionCenterEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
