package club.pard.server.ftiland.oldnew.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;

import club.pard.server.ftiland.oldnew.entity.post.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
    
}
