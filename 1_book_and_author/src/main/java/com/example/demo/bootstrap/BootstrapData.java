package com.example.demo.bootstrap;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.domain.Publisher;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // add author
        Author ahmad = new Author();
        ahmad.setFirstName("Ahmad");
        ahmad.setLastName("Ayden");

        // add book
        Book book1 = new Book();
        book1.setIsbn("12345");
        book1.setTitle("Book One");

        Author ahmadSaved = authorRepository.save(ahmad);
        Book book1Saved = bookRepository.save(book1);

        // add author
        Author byzantine = new Author();
        byzantine.setFirstName("Byzantine");
        byzantine.setLastName("Beast");

        // add book
        Book book2 = new Book();
        book2.setIsbn("54321");
        book2.setTitle("Book Two");

        Author byzantineSaved = authorRepository.save(byzantine);
        Book book2Saved = bookRepository.save(book2);

        // add publisher
        Publisher publisher = new Publisher();
        publisher.setPublisherName("Blue Well");
        publisher.setAddress("3 Angkasapuri Kuala Lumpur");
        Publisher publisherSaved = publisherRepository.save(publisher);

        // add books to the publisher
        book1Saved.setPublisher(publisherSaved);
        book2Saved.setPublisher(publisherSaved);

        // Must be a bidirectional way for many-to-many relationship
        ahmadSaved.getBooks().add(book1Saved);
        book1Saved.getAuthors().add(ahmadSaved);
        byzantineSaved.getBooks().add(book2Saved);
        book2Saved.getAuthors().add(byzantineSaved);
        ahmadSaved.getBooks().add(book2Saved);
        book2Saved.getAuthors().add(ahmadSaved);

        authorRepository.save(ahmadSaved);
        authorRepository.save(byzantineSaved);
        bookRepository.save(book1Saved);
        bookRepository.save(book2Saved);

        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
    }
}
