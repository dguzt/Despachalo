package org.guzman.despachalo.adapter.persistence.modules.company.user;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@ToString
@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "USUARIO")
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombres", length = 100)
    private String names;

    @Column(name = "apellidos", length = 100)
    private String lastnames;

    @Column(name = "tipo_documento", length = 10)
    private String documentType;

    @Column(name = "num_documento", length = 15)
    private String documentNumber;

    @Column(name = "correo", length = 50)
    private String email;

    @Column(name = "activo")
    private Boolean active;

    @Column(name = "es_admin")
    private Boolean isAdmin;

    @Column(name = "contrasena_hash")
    private String hashedPassword;

    @Column(name = "rol_id")
    private Long roleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_id", insertable = false, updatable = false)
    private RoleEntity role;

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
