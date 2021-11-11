package org.guzman.despachalo.adapter.persistence.modules.storage.commodity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.guzman.despachalo.adapter.persistence.modules.company.center.DistributionCenterEntity;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "MERCADERIA")
public class CommodityEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "almacen_id", nullable = false)
    private Long warehouseId;

    @Column(name = "centro_id")
    private Long assignedCenterId;

    @Column(name = "codigo", length = 50, nullable = false)
    private String code;

    @Column(name = "ubigeo")
    private String geocode;

    @Column(name = "placa_vehiculo", nullable = false)
    private String vehiclePlate;

    @Column(name = "estado", length = 15, nullable = false)
    private String state;

    @Column(name = "hora_llegada", nullable = false)
    private LocalDateTime arrivalTime;


    @Column(name = "fecha_creado")
    private LocalDateTime createdAt;

    @Column(name = "fecha_actualizado")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="almacen_id", insertable = false, updatable = false)
    private OriginPointEntity originPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="centro_id", insertable = false, updatable = false)
    private DistributionCenterEntity assignedCenter;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CommodityEntity that = (CommodityEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 285330853;
    }
}
