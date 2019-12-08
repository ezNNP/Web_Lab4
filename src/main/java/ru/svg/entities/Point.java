package ru.svg.entities;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "s265077", name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private int id;

    @NonNull private double x;
    @NonNull private double y;
    @NonNull private double r;
    @NonNull private boolean in;
    @NonNull private boolean correct;
}
