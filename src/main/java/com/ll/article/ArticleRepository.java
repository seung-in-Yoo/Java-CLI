package com.ll.article;

import com.ll.setting.SettingsContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ArticleRepository {
    private final List<Article> articles;
    private int lastId;

    ArticleRepository() {
        articles = new ArrayList<Article>();
        lastId = 0;
    }

    void addArticleToList(String title, String content) {
        Article newArticle = new Article(++lastId, title, content);
        articles.add(newArticle);
    }

    Article findArticleFromList(int id) {
        for (Article article : articles) {
            if (article.getId() == id) return article;
        }

        return null;
    }

    void deleteArticleFromList(Article article) {
        articles.remove(article);
    }



    public void addViewCount(Article foundArticle) {
        foundArticle.addViewCount();
    }

    public List<Article> getArticleList() {
        List<Article> newArticle = new ArrayList<>(articles.stream().toList());
        return sortArticleList(newArticle);
    }

    public List<Article> getSearchArticleList(String keyword) {
        List<Article> newList = new ArrayList<>(articles.stream()
                .filter(article -> (article.getTitle().contains(keyword) || article.getContent().contains(keyword)))
                .toList());

        return sortArticleList(newList);
    }

    List<Article> sortArticleList(List<Article> articleList) {
        if (SettingsContext.orderName.equals("id")) {
            articleList.sort(new ArticleIdComparator());
        } else {
            articleList.sort(new ArticleRegComparator());
        }
        if (SettingsContext.isAsc) return articleList;
        else return articleList.reversed();
    }
}

class ArticleIdComparator implements Comparator<Article> {
    @Override
    public int compare(Article a1, Article a2) {
        return a1.getId() - a2.getId();
    }
}

class ArticleRegComparator implements Comparator<Article> {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public int compare(Article a1, Article a2) {
        try {
            Date date1 = formatter.parse(a1.getRegDate());
            Date date2 = formatter.parse(a2.getRegDate());
            return date1.compareTo(date2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}