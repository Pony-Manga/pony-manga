package pony.manga.server.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = Long.valueOf(0);
    String mail;
    String nickname;
    String password;
    String avatarPath;
    @Transient
    private String passwordConfirm;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @Column()
    boolean isActive = false;
    @OneToMany(mappedBy = "reviewer")
    List<TitleReview> reviews;
    @OneToMany(mappedBy = "reviewer")
    List<TitleRating> ratings;
    @ManyToMany()
    List<Title> favourite;
    @OneToMany(mappedBy = "uploader")
    List<TitleChapter> chapterUploads;
    @OneToMany(mappedBy = "commentator")
    List<PageCommentary> pageCommentaries;
    @OneToMany(mappedBy = "uploader")
    List<Title> uploadedTitles;
    @ManyToMany()
    List<TitleChapter> unreadChapters;
    @OneToMany(mappedBy = "author")
    List<ForumThread> forumThreads;
    @OneToMany(mappedBy = "author")
    List<ForumThreadMessage> forumThreadMessages;

    public User(){}

    public String getUsername(){
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
