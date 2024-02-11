package org.example.model;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player1", referencedColumnName = "id")
    private Player player1;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player2", referencedColumnName = "id")
    private Player player2;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "winner", referencedColumnName = "id")
    private Player winner;
}
