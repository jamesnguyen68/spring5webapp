package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * CommandLineRunner will look for instances of this type and run them
 * Make this class as a component so that Spring will detect this
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //creating new publisher
        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        System.out.println("Publisher Count: " + publisherRepository.count());



        //creating new author
        Author eric  = new Author("Eric","Evans");
        //creating new book
        Book ddd = new Book("Domain Driven Design","123123");



        //adding the book to the author
        eric.getBooks().add(ddd);

        //adding author to the book
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        //saving the author to database using repository
        authorRepository.save(eric);
        //saving the book to database using repositoty
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        //another example

        Author james = new Author("James","Nguyen");
        Book jamesBook = new Book("Some Book","12345");

        james.getBooks().add(jamesBook);
        jamesBook.getAuthors().add(james);
        jamesBook.setPublisher(publisher);
        publisher.getBooks().add(jamesBook);

        //saving
        authorRepository.save(james);
        bookRepository.save(jamesBook);
        publisherRepository.save(publisher);

        //printing out
        System.out.println("Started in Bootstrap");
        System.out.println("Number of Publishers: " +publisher.getBooks().size());
    }
}
