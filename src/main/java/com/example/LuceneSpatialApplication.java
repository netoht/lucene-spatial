package com.example;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LuceneSpatialApplication {

    @Value("${lucene.index.location}")
    private String indexLocation;

    @Bean
    public IndexSearcher indexSearcher() throws IOException {
        Directory directory = new SimpleFSDirectory(new File(indexLocation));
        IndexReader indexReader = DirectoryReader.open(directory);
        return new IndexSearcher(indexReader);
    }

    public static void main(String[] args) {
        SpringApplication.run(LuceneSpatialApplication.class, args);
    }
}
