package org.example.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Entity
@Table(name="players", uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    @GenericGenerator(name="player_id_gen" , strategy="increment")
    @GeneratedValue(generator="player_id_gen")
    @Column(name="id", nullable=false, unique=true)
    private Long id;

    @Column(name="name", length=20)
    private String name;

    private String restoreTest2;

}