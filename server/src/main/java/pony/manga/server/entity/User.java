package pony.manga.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Данный класс отвечает за хранение информации о пользователе ресурса.
 * В базе данных сущности данного класса хранятся в таблице users.
 */
@Setter
@Getter
@Entity
@Table(name = "users")
@AllArgsConstructor
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = Long.valueOf(0);
    String mail;
    String email;
    String nickname;
    String username;
    String password;
    String avatarPath;
    @Transient
    private String passwordConfirm;
//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<Role> roles;
    @Column()
    boolean isActive = false;
    @OneToMany(mappedBy = "reviewer")
    List<TitleReview> reviews;
    @OneToMany(mappedBy = "reviewer")
    @JsonBackReference
    List<TitleRating> ratings;
    @ManyToMany()
    List<Title> favourite;
    @OneToMany(mappedBy = "uploader")
    List<TitleChapter> chapterUploads;
    @OneToMany(mappedBy = "commentator")
    @JsonBackReference
    List<PageCommentary> pageCommentaries;
    @OneToMany(mappedBy = "uploader")
    List<Title> uploadedTitles;
    @ManyToMany()
    @JsonBackReference
    List<TitleChapter> unreadChapters;
    @OneToMany(mappedBy = "author")
    List<ForumThread> forumThreads;
    @OneToMany(mappedBy = "author")
    @JsonBackReference
    List<ForumThreadMessage> forumThreadMessages;

    @OneToOne
    Role role;

    public User(){}

    public String getUsername(){
        return mail;
    }


    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }


    public boolean isEnabled() {
        return isActive;
    }



    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
