package org.guzman.despachalo.adapter.persistence.modules.sync.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.guzman.despachalo.adapter.persistence.modules.company.center.DistributionCenterEntity;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "PUNTO_DESTINO")
public class EndPointEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", length = 50, nullable = false)
    private String code;

    @Column(name = "cliente_id", nullable = false)
    private Long clientId;

    @Column(name = "centro_dist_id")
    private Long centerId;

    @Column(name = "direccion", length = 100, nullable = false)
    private String address;

    @Column(name = "ubigeo", length = 10)
    private String geoCode;

    @Column(name = "ubi_longitud", nullable = false)
    private Double longitude;

    @Column(name = "ubi_latitud", nullable = false)
    private Double latitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", insertable = false, updatable = false)
    private ClientEntity client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "centro_dist_id", insertable = false, updatable = false)
    private DistributionCenterEntity center;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EndPointEntity that = (EndPointEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 285330853;
    }
}
