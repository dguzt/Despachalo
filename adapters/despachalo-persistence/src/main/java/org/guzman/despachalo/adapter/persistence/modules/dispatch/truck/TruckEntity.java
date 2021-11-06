package org.guzman.despachalo.adapter.persistence.modules.dispatch.truck;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "VEHICULO")
public class TruckEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "capacidad_peso")
    private Double weightCapacity;

    @Column(name = "capacidad_volumen")
    private Double volumeCapacity;

    @Column(name = "placa_vehiculo")
    private String vehiclePlate;

    @Column(name = "fecha_creado")
    private LocalDateTime createdAt;

    @Column(name = "fecha_actualizado")
    private LocalDateTime updatedAt;

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
