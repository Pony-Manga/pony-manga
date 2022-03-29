package pony.manga.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pony.manga.server.entity.*;
import pony.manga.server.repository.*;

import java.util.List;


/**
 * Хоть я и написал, что это TitleService, по сути тут работа со всем, что наполняет класс Title
 * */
@Service
public class TitleService {

    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    TitleTagRepository titleTagRepository;
    @Autowired
    TitleStatusRepository titleStatusRepository;
    @Autowired
    TitleRatingRepository titleRatingRepository;
    @Autowired
    TitleReviewRepository titleReviewRepository;
    @Autowired
    MangaTypeRepository mangaTypeRepository;
    @Autowired
    TitleRepository titleRepository;
    @Autowired
    TitleChapterRepository titleChapterRepository;
    @Autowired
    ChapterPageRepository chapterPageRepository;
    @Autowired
    PageCommentaryRepository pageCommentaryRepository;

    public boolean addArtist(Artist artist){
        if (artistRepository.existsByName(artist.getName())){
            return false;
        } else {
            artistRepository.save(artist);
            return true;
        }
    }

    public Artist getArtist(Long id){
        return artistRepository.getById(id);
    }

    public List<Artist> getArtists(){
        return artistRepository.findAll();
    }

    public boolean addAuthor(Author author){
        if (authorRepository.existsByName(author.getName())){
            return false;
        } else {
            authorRepository.save(author);
            return true;
        }
    }

    public Author getAuthor(Long id){
        return authorRepository.getById(id);
    }

    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }

    public boolean saveTitleTag(TitleTag titleTag){
        if (titleTagRepository.existsByName(titleTag.getName())){
            return false;
        } else {
            titleTagRepository.save(titleTag);
            return true;
        }
    }

    public TitleTag getTitleTag(Long id){
        return titleTagRepository.getById(id);
    }

    public List<TitleTag> getTitleTags(){
        return titleTagRepository.findAll();
    }

    public boolean saveTitleStatus(TitleStatus titleStatus){
        if (titleStatusRepository.existsByName(titleStatus.getName())){
            return false;
        } else {
            titleStatusRepository.save(titleStatus);
            return true;
        }
    }

    public TitleStatus getTitleStatus(Long id){
        return titleStatusRepository.getById(id);
    }

    public void saveTitleRating(TitleRating titleRating){
        titleRatingRepository.save(titleRating);
    }

    public List<TitleRating> getTitleRatings(){
        return titleRatingRepository.findAll();
    }

    public void setTitleReview(TitleReview titleReview){
        titleReviewRepository.save(titleReview);
    }

    public List<TitleReview> getTitleReviews(){
        return titleReviewRepository.findAll();
    }

    public boolean saveMangaType(MangaType mangaType){
        if (mangaTypeRepository.existsByName(mangaType.getName())){
            return false;
        } else {
            mangaTypeRepository.save(mangaType);
            return true;
        }
    }

    public MangaType getMangaType(Long id){
        return mangaTypeRepository.getById(id);
    }

    public List<MangaType> getMangaTypes(){
        return mangaTypeRepository.findAll();
    }

    public boolean saveTitle(Title title){
        if (titleRepository.existsByName(title.getName())){
            return false;
        } else {
            titleRepository.save(title);
            return true;
        }
    }

    public Title getTitle(Long id){
        return titleRepository.getById(id);
    }

    public List<Title> getTitles(){
        return titleRepository.findAll();
    }

    public void saveTitleChapter(TitleChapter titleChapter){
        titleChapterRepository.save(titleChapter);
    }

    public TitleChapter getTitleChapter(Long id){
        return titleChapterRepository.getById(id);
    }

    public void saveChapterPage(ChapterPage chapterPage){
        chapterPageRepository.save(chapterPage);
    }

    public ChapterPage getChapterPage(Long id){
        return chapterPageRepository.getById(id);
    }

    public void savePageCommentary(PageCommentary pageCommentary){
        pageCommentaryRepository.save(pageCommentary);
    }
}
