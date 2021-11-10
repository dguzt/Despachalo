package org.guzman.despachalo.adapter.persistence.modules.company.center;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "CENTRO_DISTRIBUCION")
public class DistributionCenterEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", length = 100)
    private String name;

    @Column(name = "ubi_longitud")
    private Double longitude;

    @Column(name = "ubi_latitud")
    private Double latitude;

    @Column(name = "direccion", length = 100)
    private String address;

    @Column(name = "ubigeo", length = 6)
    private String geocode;

    @Column(name = "eliminado")
    private Boolean deleted = false;

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
