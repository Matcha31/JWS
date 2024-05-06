package fr.epita.assistants.jws.data.model;

import fr.epita.assistants.jws.utils.GameState;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name="game")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@With
public class GameModel {
    public @Column(name="starttime") Instant startTime;
    @Column(columnDefinition = "varchar")
    public GameState state;
    public int nbPlayers;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "map")
    public List<String> map;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault(value = "1")
    public Long id;
}
