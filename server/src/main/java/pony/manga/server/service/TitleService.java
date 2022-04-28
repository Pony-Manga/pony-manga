package pony.manga.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pony.manga.server.dto.FilterDTO;
import pony.manga.server.entity.*;
import pony.manga.server.repository.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Несмотря на название TitleService, данный сервис работает со всем, что наполняет произведение.
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
    @Autowired
    EntityManagerFactory entityManagerFactory;


    /**
     * Метод добавляет нового худодника в базу, если в базе его нет.
     * @param artist - данные о художнике
     * @return Возвращает true если добавление прошло успешно, если такой художник уже есть, то false
     */
    public boolean addArtist(Artist artist){
        if (artistRepository.existsByName(artist.getName())){
            return false;
        } else {
            artistRepository.save(artist);
            return true;
        }
    }

    /**
     * Метод для получения художника из базы данных по его id.
     * @param id - id художника
     * @return Возвращает объект класса {@link pony.manga.server.entity.Artist} если поиск прошел успешно, иначе null
     */
    public Artist getArtist(Long id){
        return artistRepository.getById(id);
    }

    /**
     * Метод для получения списка всех художников из базы.
     * @return List объектов класса {@link pony.manga.server.entity.Artist} если есть хоть одна подходящая строка,
     * иначе пустой List.
     */
    public List<Artist> getArtists(){
        return artistRepository.findAll();
    }

    /**
     * Метод добавляет нового автора в базу, если в базе его нет.
     * @param author - данные об авторе
     * @return Возвращает true если добавление прошло успешно. Если такой автор уже есть, то false.
     */
    public boolean addAuthor(Author author){
        if (authorRepository.existsByName(author.getName())){
            return false;
        } else {
            authorRepository.save(author);
            return true;
        }
    }

    /**
     * Метод для получения автора из базы данных по его id.
     * @param id - id автора
     * @return Возвращает объект класса {@link pony.manga.server.entity.Author} если поиск прошел успешно, иначе null
     */
    public Author getAuthor(Long id){
        return authorRepository.getById(id);
    }

    /**
     * Метод для получения списка всех авторов из базы.
     * @return List объектов класса {@link pony.manga.server.entity.Author} если есть хоть одна подходящая строка,
     * иначе пустой List.
     */
    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }

    /**
     * Метод добавляет нового тег в базу, если в базе его нет.
     * @param titleTag - данные о теге
     * @return Возвращает true если добавление прошло успешно. Если такой тег уже есть, то false.
     */
    public boolean saveTitleTag(TitleTag titleTag){
        if (titleTagRepository.existsByName(titleTag.getName())){
            return false;
        } else {
            titleTagRepository.save(titleTag);
            return true;
        }
    }

    /**
     * Метод для получения тега из базы данных по его id.
     * @param id - id тега
     * @return Возвращает объект класса {@link pony.manga.server.entity.TitleTag} если поиск прошел успешно, иначе null
     */
    public TitleTag getTitleTag(Long id){
        return titleTagRepository.getById(id);
    }

    /**
     * Метод для получения списка всех тегов из базы.
     * @return List объектов класса {@link pony.manga.server.entity.TitleTag} если есть хоть одна подходящая строка,
     * иначе пустой List.
     */
    public List<TitleTag> getTitleTags(){
        return titleTagRepository.findAll();
    }

    /**
     * Метод добавляет нового тег в базу, если в базе его нет.
     * @param titleStatus - данные о статусе
     * @return Возвращает true если добавление прошло успешно. Если такой статус уже есть, то false.
     */
    public boolean saveTitleStatus(TitleStatus titleStatus){
        if (titleStatusRepository.existsByName(titleStatus.getName())){
            return false;
        } else {
            titleStatusRepository.save(titleStatus);
            return true;
        }
    }

    /**
     * Метод для получения тега из базы данных по его id.
     * @param id  - id статуса
     * @return Возвращает объект класса {@link pony.manga.server.entity.TitleStatus} если поиск прошел успешно, иначе null
     */
    public TitleStatus getTitleStatus(Long id){
        return titleStatusRepository.getById(id);
    }

    /**
     * Метод для получения списка всех статусов из базы.
     * @return List объектов класса {@link pony.manga.server.entity.TitleStatus} если есть хоть одна подходящая строка,
     * иначе пустой List.
     */
    public List<TitleStatus> getTitleStatuses(){
        return titleStatusRepository.findAll();
    }

    /**
     * Метод для добавления оценки произведения. Если пользователь уже ставил оценку данному произведению, то она
     * просто изменится.
     * @param titleRating - данные об оценке
     */
    public void saveTitleRating(TitleRating titleRating){
        TitleRating titleRatingFromDB = titleRatingRepository.getTitleRatingByUserId(titleRating.getReviewer().getId(),
                titleRating.getRatingTo().getId());
        if (titleRatingFromDB == null) {
            titleRatingRepository.save(titleRating);
        } else {
            titleRatingFromDB.setUserRating(titleRating.getUserRating());
            titleRatingRepository.save(titleRatingFromDB);
        }
    }

    /**
     * Метод для получения оценок произведения по его id
     * @param titleId - id произведения
     * @return List объектов класса {@link pony.manga.server.entity.TitleRating} если есть хоть одна подходящая строка,
     * иначе пустой List.
     */
    public List<TitleRating> getTitleRatings(Long titleId){
        return titleRatingRepository.getTitleRatingByTitleId(titleId);
    }

    /**
     * Метод для добавления отзыва о произведении.  Если пользователь уже оставил отзыв данному произведению, то он
     * просто изменится.
     * @param titleReview - данные об отзыве
     */
    public void saveTitleReview(TitleReview titleReview){
        TitleReview titleReviewFromDB = titleReviewRepository.getTitleReviewByUserId(titleReview.getReviewer().getId(),
                titleReview.getReviewTo().getId());
        if (titleReviewFromDB == null){
            titleReviewRepository.save(titleReview);
        } else {
            titleReviewFromDB.setReviewText(titleReview.getReviewText());
            titleReviewRepository.save(titleReviewFromDB);
        }

    }

    /**
     * Метод для получения отзывов о произведении по его id
     * @param titleId - id произведения
     * @return List объектов класса {@link pony.manga.server.entity.TitleReview} если есть хоть одна подходящая строка,
     * иначе пустой List.
     */
    public List<TitleReview> getTitleReviews(Long titleId){
        return titleReviewRepository.getTitleReviewByTitleId(titleId);
    }

    /**
     * Метод добавляет новый тип произведения в базу, если в базе его нет.
     * @param mangaType - информация о типе
     * @return Возвращает true если добавление прошло успешно. Если такой тип уже есть, то false.
     */
    public boolean saveMangaType(MangaType mangaType){
        if (mangaTypeRepository.existsByName(mangaType.getName())){
            return false;
        } else {
            mangaTypeRepository.save(mangaType);
            return true;
        }
    }

    /**
     * Метод позволяет найти тип произведения по id.
     * @param id - id типа
     * @return Возвращает объект класса {@link pony.manga.server.entity.MangaType} если поиск прошел успешно, иначе null
     */
    public MangaType getMangaType(Long id){
        return mangaTypeRepository.getById(id);
    }

    /**
     * Метод позволяет получить список всех типов произведений.
     * @return List объектов класса {@link pony.manga.server.entity.MangaType} если есть хоть одна подходящая строка,
     * иначе пустой List.
     */
    public List<MangaType> getMangaTypes(){
        return mangaTypeRepository.findAll();
    }

    /**
     * Метод добавляет новое произведение в базу, если в базе его нет.
     * @param title - информация о произведении
     * @return Возвращает true если добавление прошло успешно. Если произведение с таким именем уже есть, то false.
     */
    public boolean saveTitle(Title title){
        if (titleRepository.existsByName(title.getName())){
            return false;
        } else {
            titleRepository.save(title);
            return true;
        }
    }

    /**
     * Метод позволяет найти произведение по id.
     * @param id - id произведения
     * @return Возвращает объект класса {@link pony.manga.server.entity.Title} если поиск прошел успешно, иначе null
     */
    public Title getTitle(Long id){
        return titleRepository.getById(id);
    }

    /**
     * Метод позволяет получить список всех типов произведений.
     * @param offset - номер страницы
     * @return List объектов класса {@link pony.manga.server.entity.Title} если есть хоть одна подходящая строка,
     * иначе пустой List.
     */
    public List<Title> getTitles(Long offset){
        return titleRepository.getTitles((offset-1)*20);
    }

    /**
     * Метод позволяет найти список произведений по подстроке в названии
     * @param name - название (строка)
     * @return List объектов класса {@link pony.manga.server.entity.Title} если есть хоть одна подходящая строка,
     * иначе пустой List.
     */
    public List<Title> getTitlesByName(String name){
        return titleRepository.getTitlesByName(name);
    }

    /**
     * Метод позволяет найти список произведений с одинаковым тегом
     * @param id - id тега
     * @return List объектов класса {@link pony.manga.server.entity.Title} если есть хоть одна подходящая строка,
     * иначе пустой List.
     */
    public List<Title> getTitlesByTag(Long id){
        return titleRepository.getTitlesByTag(id);
    }

    /**
     * Метод позволяет найти список произведений с одинаковым автором
     * @param id - id автора
     * @return List объектов класса {@link pony.manga.server.entity.Title} если есть хоть одна подходящая строка,
     * иначе пустой List.
     */
    public List<Title> getTitlesByAuthor(Long id){
        return titleRepository.getTitlesByAuthor(id);
    }

    /**
     * Метод позволяет найти список  произведений с одинаковым художником
     * @param id - id художника
     * @return List объектов класса {@link pony.manga.server.entity.Title} если есть хоть одна подходящая строка,
     * иначе пустой List.
     */
    public List<Title> getTitlesByArtist(Long id){
        return titleRepository.getTitlesByArtist(id);
    }

    /**
     * Метод позволяет найти список произведений с одинааковым статусом
     * @param id - id состояния
     * @return List объектов класса {@link pony.manga.server.entity.Title} если есть хоть одна подходящая строка,
     * иначе пустой List.
     */
    public List<Title> getTitlesByStatus(Long id){
        return titleRepository.getTitlesByTitleStatus(id);
    }

    /**
     * Метод позволяет найти список произведений с одинаквым типом
     * @param id - id типа
     * @return List объектов класса {@link pony.manga.server.entity.Title} если есть хоть одна подходящая строка,
     * иначе пустой List.
     */
    public List<Title> getTitleByType(Long id){
        return titleRepository.getTitlesByMangaType(id);
    }

    /**
     * Метод позволяющий выбрать произведения по фильтрам. Используется CriteriaAPI.
     * !!Когда будет теститься приложение, проверить утечку подключений!!
     * @param filterDTO - DTO с фидьтрами
     * @return List объектов класса {@link pony.manga.server.entity.Title} если есть хоть одна подходящая строка,
     * иначе пустой List.
     */
    @Transactional
    public List<Title> getTitlesByFilters(FilterDTO filterDTO){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Title> bookCriteria = criteriaBuilder.createQuery(Title.class);

        Root<Title> titleRoot = bookCriteria.from(Title.class);

        bookCriteria.select(titleRoot);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (filterDTO.getAuthor() != null){
            predicates.add(criteriaBuilder.equal(titleRoot.get("author"), filterDTO.getAuthor()));
        }
        if (filterDTO.getArtist() != null){
            predicates.add(criteriaBuilder.equal(titleRoot.get("artist"), filterDTO.getArtist()));
        }
        if (filterDTO.getMangaType() != null){
            predicates.add(criteriaBuilder.equal(titleRoot.get("mangaType"), filterDTO.getMangaType()));
        }
        if (filterDTO.getTags() != null) {
            Join<TitleTag, Title> join = titleRoot.join("tags");
            List<Long> ids = new ArrayList<>();
            for (int i = 0; i < filterDTO.getTags().size(); i++) {
                ids.add(filterDTO.getTags().get(i).getId());
            }
            predicates.add(join.in(ids));
        }
        Expression<Long> count = criteriaBuilder.count(titleRoot.get(("id")));
        bookCriteria.where(predicates.toArray(new Predicate[]{}));

        if (filterDTO.getTags() != null) {
            bookCriteria.groupBy(titleRoot.get("id"));
            bookCriteria.having(criteriaBuilder.equal(count, filterDTO.getTags().size()));
        }

        List<Order> orderList = new ArrayList<>();
        orderList.add(criteriaBuilder.desc(titleRoot.get("id")));

        List<Title> titles = new ArrayList<>();

        bookCriteria.orderBy(orderList);
        try {
            titles = entityManager.createQuery(bookCriteria).getResultList();
        } catch (NoResultException nre) {}
        entityManager.close();
        return titles;
    }

    /**
     * Метод сохраняет новую главу произведения.
     * @param titleChapter - данные о главе
     */
    public void saveTitleChapter(TitleChapter titleChapter){
        titleChapterRepository.save(titleChapter);
    }

    /**
     * Метод позволяет найти главу произведения по id.
     * @param id - id главы
     * @return Возвращает объект класса {@link pony.manga.server.entity.TitleChapter} если поиск прошел успешно, иначе null
     */
    public TitleChapter getTitleChapter(Long id){
        return titleChapterRepository.getById(id);
    }

    /**
     * Метод позволяет получить список всех глав произведения.
     * @param titleId - id произвдения
     * @return List объектов класса {@link pony.manga.server.entity.TitleChapter} если есть хоть одна подходящая строка,
     * иначе пустой List.
     */
    public List<TitleChapter> getTitleChapters(Long titleId){
        return titleChapterRepository.getTitleChaptersByTitleId(titleId);
    }

    /**
     * Метод позволяет сохранить страницу главы в базу
     * @param chapterPage - информация о странице
     */
    public void saveChapterPage(ChapterPage chapterPage){
        chapterPageRepository.save(chapterPage);
    }

    /**
     * Получить страницу по номеру и id главы
     * @param pageNumber - номер страницы
     * @param chapterId - id главы
     * @return Возвращает объект класса {@link pony.manga.server.entity.ChapterPage} если поиск прошел успешно, иначе null
     */
    public ChapterPage getChapterPage(int pageNumber, Long chapterId){
        return chapterPageRepository.getChapterPageByPageNumber(pageNumber, chapterId);
    }

    /**
     * Получить кол-во страниц в конкретной главе
     * @param chapterId - id главы
     * @return Возвращает кол-во страниц
     */
    public Long getChapterPageCount(Long chapterId){
        return chapterPageRepository.getChapterCount(chapterId);
    }

    /**
     * Метод добавляет комментарий к странице
     * @param pageCommentary - информация о комменатрии под страницей
     */
    public void savePageCommentary(PageCommentary pageCommentary){
        pageCommentaryRepository.save(pageCommentary);
    }

    /**
     * Метод выбирает все комментарии под конкретной страницей
     * @param pageId - id страницы
     * @return List объектов класса {@link pony.manga.server.entity.PageCommentary} если есть хоть одна подходящая строка,
     * иначе пустой List.
     */
    public List<PageCommentary> getPageCommentaries(Long pageId){
        return pageCommentaryRepository.getPageCommentaryByPageId(pageId);
    }
}
