package org.example.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "matches", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    @Id
    @GenericGenerator(name = "match_id_gen", strategy = "increment")
    @GeneratedValue(generator = "match_id_gen")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @OneToOne
    @JoinColumn(name = "player1", referencedColumnName = "id")
    private Player player1;

    @OneToOne
    @JoinColumn(name = "player2", referencedColumnName = "id")
    private Player player2;
    @OneToOne
    @JoinColumn(name = "winner", referencedColumnName = "id")
    private Player winner;

    private String restoreTest;
}
