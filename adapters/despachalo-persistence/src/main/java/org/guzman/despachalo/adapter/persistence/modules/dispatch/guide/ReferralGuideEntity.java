package org.guzman.despachalo.adapter.persistence.modules.dispatch.guide;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.guzman.despachalo.adapter.persistence.modules.programming.ProgrammedVehicleEntity;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "GUIA_REMISION")
public class ReferralGuideEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vehiculo_prog_id", nullable = false)
    private Long programmedVehicleId;

    @Column(name = "tipo_transporte", length = 20)
    private String transportType;

    @Column(name = "motivo_traslado", length = 20)
    private String transferReason;

    @Column(name = "documento_url", length = 200)
    private String documentUrl;

    @Column(name = "fecha_emision")
    private LocalDateTime issuedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculo_prog_id", insertable = false, updatable = false)
    private ProgrammedVehicleEntity programmedVehicle;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReferralGuideEntity that = (ReferralGuideEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
