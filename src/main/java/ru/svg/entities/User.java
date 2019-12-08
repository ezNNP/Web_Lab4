package ru.svg.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(schema = "s265077", name = "users")
public class User {
    @Id
    @NonNull private String login;
    @NonNull private String password;

    @Transient
    private String passwordConfirm;
}
