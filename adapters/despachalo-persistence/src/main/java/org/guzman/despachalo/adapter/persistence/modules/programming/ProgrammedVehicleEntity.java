package org.guzman.despachalo.adapter.persistence.modules.programming;

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
