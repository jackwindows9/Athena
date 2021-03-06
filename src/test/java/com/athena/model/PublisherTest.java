package com.athena.model;

import com.athena.repository.BookRepository;
import com.athena.repository.PublisherRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.transaction.Transactional;

/**
 * Created by tommy on 2017/3/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
@DatabaseSetup({"classpath:books.xml", "classpath:publishers.xml"})
public class PublisherTest {
    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void setup() {

    }

    @Test
    public void getBooks() {
        Publisher publisher = publisherRepository.findOne("999");
        Object[] books = bookRepository.getBooksByPublisher(publisher).toArray();
        Assert.assertArrayEquals(books, publisher.getBooks().toArray());
    }
}
