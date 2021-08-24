package org.guzman.despachalo.adapter.web.config.security.model;

import lombok.*;
import org.guzman.despachalo.adapter.persistence.company.EmployeeEntity;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@ToString
@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "MT_WEB_USER")
public class WebUserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "web_user_id")
    private Long id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "roles")
    private String roles;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private EmployeeEntity employee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WebUserEntity that = (WebUserEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1455551474;
    }
}
