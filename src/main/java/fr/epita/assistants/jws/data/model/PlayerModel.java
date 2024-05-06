package fr.epita.assistants.jws.data.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="player")
@With
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
public class PlayerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public int lives;
    public String name;
    public int posx;
    public int posy;
    public Instant lastbomb;
    public Instant lastmovement;
    @ManyToOne
    public GameModel game;
}
