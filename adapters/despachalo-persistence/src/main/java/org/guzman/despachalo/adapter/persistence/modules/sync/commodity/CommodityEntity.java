package org.guzman.despachalo.adapter.persistence.modules.sync.commodity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@ToString
@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "MERCADERIA")
public class CommodityEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "almacen_id", nullable = false)
    private Long warehouseId;

    @Column(name = "codigo", length = 50, nullable = false)
    private String code;

    @Column(name = "placa_vehiculo", nullable = false)
    private String vehiclePlate;

    @Column(name = "estado", length = 15, nullable = false)
    private String state;

    @Column(name = "hora_llegada", nullable = false)
    private LocalDateTime arrivalTime;

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
