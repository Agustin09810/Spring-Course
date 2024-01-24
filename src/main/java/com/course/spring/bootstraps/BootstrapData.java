package com.course.spring.bootstraps;

import com.course.spring.models.Publisher;
import com.course.spring.repositories.AuthorRepository;
import com.course.spring.repositories.BookRepository;
import com.course.spring.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import com.course.spring.models.Author;
import com.course.spring.models.Book;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author author1 = new Author("Agustín", "Lorenzo");
        Author author2 = new Author("Ana", "González");

        Publisher publisher1 = new Publisher(
                "Lorenzo's Books",
                "Eduardo Victor Haedo 2370",
                "Montevideo",
                "Montevideo",
                "11200"
        );

        Book book1 = new Book("Gusanos para colorear", "1");
        Book book2 = new Book("La Bella y la Bestia", "2");

        author1.getBooks().add(book1);
        book1.getAuthors().add(author1);

        author2.getBooks().add(book2);
        book2.getAuthors().add(author2);

        book1.setPublisher(publisher1);
        publisher1.getBooks().add(book1);

        authorRepository.save(author1);
        bookRepository.save(book1);

        authorRepository.save(author2);
        bookRepository.save(book2);

        publisherRepository.save(publisher1);

        System.out.println("Books quantity: " + bookRepository.count());

    }
}
