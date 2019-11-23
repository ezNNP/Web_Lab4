package ru.svg.entities;

import javax.persistence.*;

@Entity
@Table(schema = "s265077", name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;
}
