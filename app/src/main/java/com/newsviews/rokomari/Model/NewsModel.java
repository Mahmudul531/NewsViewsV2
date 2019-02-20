package com.newsviews.rokomari.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NewsModel {


    @SerializedName("articles")
    private ArrayList<Articles> articles;
    @SerializedName("totalResults")
    private int totalresults;
    @SerializedName("status")
    private String status;

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }

    public int getTotalresults() {
        return totalresults;
    }

    public void setTotalresults(int totalresults) {
        this.totalresults = totalresults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class Articles {
        @SerializedName("content")
        private String content;
        @SerializedName("publishedAt")
        private String publishedat;
        @SerializedName("urlToImage")
        private String urltoimage;
        @SerializedName("url")
        private String url;
        @SerializedName("description")
        private String description;
        @SerializedName("title")
        private String title;
        @SerializedName("author")
        private String author;
        @SerializedName("source")
        private Source source;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPublishedat() {
            return publishedat;
        }

        public void setPublishedat(String publishedat) {
            this.publishedat = publishedat;
        }

        public String getUrltoimage() {
            return urltoimage;
        }

        public void setUrltoimage(String urltoimage) {
            this.urltoimage = urltoimage;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
        }
    }

    public static class Source {
        @SerializedName("name")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
