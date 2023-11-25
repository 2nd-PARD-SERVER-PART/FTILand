package club.pard.server.ftiland.oldnew.entity.post;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import club.pard.server.ftiland.oldnew.entity.room.Room;
import club.pard.server.ftiland.oldnew.entity.user.User;
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
public class Post {
    @Getter @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Getter @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) Room room;
    @Getter @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) User author;

    @Getter @Column(nullable = false, length = 15)
    private String title;

    @Getter @Column(nullable = false, length = 10)
    private String sender;
    
    @Getter @Column(nullable = false, length = 400)
    private String content;

    @Getter @CreationTimestamp
    Timestamp timeUploaded;

    // @Getter @Column(nullable = true)
    // private String imagePath;

    // public Post(User user, String title, String sender, String content, String imagePath)
    public Post(User user, Room room, String title, String sender, String content)
    {
        this.author = user;
        this.room = room;
        this.title = title;
        this.sender = sender;
        this.content = content;
        // this.imagePath = imagePath;
    }
}
