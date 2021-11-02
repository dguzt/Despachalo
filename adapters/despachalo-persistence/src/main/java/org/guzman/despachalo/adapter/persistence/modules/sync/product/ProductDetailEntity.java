package org.guzman.despachalo.adapter.persistence.modules.sync.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter
@RequiredArgsConstructor
@Entity @Table(name = "DETALLE_PRODUCTO")
public class ProductDetailEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto_id", nullable = false)
    private Long productId;

    @Column(name = "peso", nullable = false)
    private Double weight;

    @Column(name = "volumen", nullable = false)
    private Double volume;

    @Column(name = "fecha_creado")
    private LocalDateTime createdAt;

    @Column(name = "fecha_actualizado")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="producto_id", insertable = false, updatable = false)
    private ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductDetailEntity that = (ProductDetailEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 285330853;
    }
}
