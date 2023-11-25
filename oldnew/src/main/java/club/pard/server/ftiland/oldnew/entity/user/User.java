package club.pard.server.ftiland.oldnew.entity.user;

import club.pard.server.ftiland.oldnew.entity.room.Room;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table
public class User {
    @Getter @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    
    @Getter @Column(nullable = false, unique = true, length = 20)
    private String userid;
    
    @Getter @Column(nullable = false, length = 20)
    private String username;

    @Getter @Column(nullable = false, length = 20)
    private String password;

    @Getter @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) Room room;

    public User(String userid, String username, String password)
    {
        this.userid = userid;
        this.username = username;
        this.password = password;
    }
}
