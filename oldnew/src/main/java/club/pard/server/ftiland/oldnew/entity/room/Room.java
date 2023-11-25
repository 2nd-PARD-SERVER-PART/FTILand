package club.pard.server.ftiland.oldnew.entity.room;

import java.util.ArrayList;
import java.util.List;

import club.pard.server.ftiland.oldnew.entity.post.Post;
import club.pard.server.ftiland.oldnew.entity.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table
public class Room {
    @Getter @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Getter @Column(nullable = false, unique = true, length = 8)
    private String code;

    @Getter @OneToMany List<User> users = new ArrayList<>();
    @Getter @OneToMany List<Post> posts = new ArrayList<>();

    public Room(String roomCode){ this.code = roomCode; }
}