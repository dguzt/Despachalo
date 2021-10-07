package org.guzman.despachalo.adapter.persistence.modules.programming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.guzman.despachalo.adapter.persistence.modules.dispatch.DispatchEntity;
import org.guzman.despachalo.adapter.persistence.modules.dispatch.driver.DriverEntity;
import org.guzman.despachalo.adapter.persistence.modules.dispatch.truck.TruckEntity;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "VEHICULO_PROGRAMADO")
public class ProgrammedVehicleEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vehiculo_id", nullable = false)
    private Long vehicleId;

    @Column(name = "conductor_id", nullable = false)
    private Long driverId;

    @Column(name = "despacho_id", nullable = false)
    private Long dispatchId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculo_id", insertable = false, updatable = false)
    private TruckEntity truck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conductor_id", insertable = false, updatable = false)
    private DriverEntity driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "despacho_id", insertable = false, updatable = false)
    private DispatchEntity dispatch;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProgrammedVehicleEntity that = (ProgrammedVehicleEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
