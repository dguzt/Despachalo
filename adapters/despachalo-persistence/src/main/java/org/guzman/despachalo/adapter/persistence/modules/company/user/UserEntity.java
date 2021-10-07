package org.guzman.despachalo.adapter.persistence.modules.company.user;

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
@Entity @Table(name = "USUARIO")
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "centro_id", nullable = false)
    private Long centerId;

    @Column(name = "nombres", length = 100, nullable = false)
    private String names;

    @Column(name = "apellidos", length = 100, nullable = false)
    private String lastnames;

    @Column(name = "tipo_documento", length = 10, nullable = false)
    private String documentType;

    @Column(name = "num_documento", length = 15, nullable = false)
    private String documentNumber;

    @Column(name = "correo", length = 50, nullable = false)
    private String email;

    @Column(name = "activo")
    private Boolean active = true;

    @Column(name = "es_admin")
    private Boolean isAdmin = false;

    @Column(name = "contrasena_hash")
    private String hashedPassword;

    @Column(name = "rol_id", nullable = false)
    private Long roleId;

    @Column(name = "fecha_creado")
    private LocalDateTime createdAt;

    @Column(name = "fecha_actualizado")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_id", insertable = false, updatable = false)
    private RoleEntity role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centro_id", insertable = false, updatable = false)
    private DistributionCenterEntity center;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
