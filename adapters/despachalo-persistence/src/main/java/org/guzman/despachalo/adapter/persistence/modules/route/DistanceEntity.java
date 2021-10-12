package org.guzman.despachalo.adapter.persistence.modules.route;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.guzman.despachalo.adapter.persistence.modules.sync.client.EndPointEntity;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "DISTANCIA")
public class DistanceEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nodo_salida_id", nullable = false)
    private Long outputNodeId;

    @Column(name = "nodo_llegada_id", nullable = false)
    private Long inputNodeId;

    @Column(name = "distancia")
    private Double distance = 0d;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nodo_salida_id", insertable = false, updatable = false)
    private EndPointEntity outputNode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nodo_llegada_id", insertable = false, updatable = false)
    private EndPointEntity inputNode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DistanceEntity that = (DistanceEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
