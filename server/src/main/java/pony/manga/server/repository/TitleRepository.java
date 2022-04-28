package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pony.manga.server.entity.Title;

import java.util.List;

public interface TitleRepository extends JpaRepository<Title, Long> {
    boolean existsByName(String name);
    @Query(value = "selsect * from titles order by id desc limit 20 offset ?1", nativeQuery = true)
    List<Title> getTitles(Long offset);
    @Query(value = "select * from titles where lower(name) like lower(concat('%',?1,'%')) order by id desc", nativeQuery = true)
    List<Title> getTitlesByName(String name);
    @Query(value = "select * from titles where author_id = ?1 order by id desc", nativeQuery = true)
    List<Title> getTitlesByAuthor(Long id);
    @Query(value = "select * from titles where artist_id = ?1 order by id desc", nativeQuery = true)
    List<Title> getTitlesByArtist(Long id);
    @Query(value = "select * from titles where manga_type_id = ?1 order by id desc", nativeQuery = true)
    List<Title> getTitlesByMangaType(Long id);
    @Query(value = "select * from titles where title_status_id = ?1 order by id desc", nativeQuery = true)
    List<Title> getTitlesByTitleStatus(Long id);
    @Query(value = "select id, description, logo, name, rating, release_date, artist_id, author_id, manga_type_id, " +
            "title_status_id, uploader_id from titles join title_tag_titles on titles_id = id where tags_id = ?1", nativeQuery = true)
    List<Title> getTitlesByTag(Long id);
}
