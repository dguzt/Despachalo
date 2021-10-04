package org.guzman.despachalo.adapter.persistence.modules.storage.commodity;

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
@Entity @Table(name = "PUNTO_ORIGEN")
public class OriginPointEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", length = 50, nullable = false)
    private String warehouseId;

    @Column(name = "direccion", length = 50, nullable = false)
    private String address;

    @Column(name = "ubi_longitud", nullable = false)
    private Double longitude;

    @Column(name = "ubi_latitud", nullable = false)
    private Double latitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OriginPointEntity that = (OriginPointEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 285330853;
    }
}
