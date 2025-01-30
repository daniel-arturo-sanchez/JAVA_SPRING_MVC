package com.dsw.Project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "carrito")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private int id;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "order_product",
            joinColumns = { @JoinColumn(name = "id_order") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    private List<Product> products;

    private Date date;
}
