package org.guzman.despachalo.adapter.persistence.modules.sync.load;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.guzman.despachalo.adapter.persistence.modules.company.user.UserEntity;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "SINCRONIZACION")
public class SyncEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_sincro")
    private LocalDateTime syncAt;

    @Column(name = "responsable_id")
    private Long responsibleId;

    @Column(name = "tipo_dato")
    private String dataType;

    @Column(name = "estado", nullable = false)
    private String state;

    @Column(name = "archivo_url", nullable = false)
    private String urlFile;

    @Column(name = "metadata")
    private String metadata;

    @Column(name = "archivo_original")
    private String originalName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "responsable_id", insertable = false, updatable = false)
    private UserEntity responsible;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SyncEntity that = (SyncEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 285330853;
    }
}
