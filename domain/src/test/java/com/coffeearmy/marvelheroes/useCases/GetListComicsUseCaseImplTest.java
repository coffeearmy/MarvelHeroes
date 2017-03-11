package com.coffeearmy.marvelheroes.useCases;

import com.coffeearmy.marvelheroes.model.Comic;
import com.coffeearmy.marvelheroes.repository.ComicsRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Dummy JUNIT test with mocks
 */
public class GetListComicsUseCaseImplTest {

    // Testee
    private GetListComicsUseCase getListComicsUseCase;

    // Mock
    private ComicsRepository comicsRepositoryMock;

    @Before
    public void setUp() throws Exception {
        comicsRepositoryMock = mock(ComicsRepository.class);
        getListComicsUseCase = new GetListComicsUseCaseImpl(comicsRepositoryMock);
    }

    @Test
    public void GetListComicsUseCase_Execute_GetComicList() throws Exception {

        // Arrange
        List<Comic> expectedList = new ArrayList<>();
        Comic expectedComic = new Comic();
        expectedComic.setTitle("BEST COMIC EVER");
        expectedComic.setDescription("BETTER THAN TWILIGHT!!");
        expectedList.add(expectedComic);

        // Act
        when(comicsRepositoryMock.getListComics("characterTest", 0)).thenReturn(Flowable.just(expectedList));
        Flowable<List<Comic>> realList = getListComicsUseCase.execute("characterTest", 0);
        TestSubscriber<List<Comic>> testSubscriber = new TestSubscriber<>();
        realList.subscribe(testSubscriber);

        // Assert
        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(expectedList);
    }

    @Test
    public void GetListComicsUseCase_Execute_GetEmptyList() throws Exception {

        // Arrange
        List<Comic> expectedList = new ArrayList<>();

        // Act
        when(comicsRepositoryMock.getListComics("characterTest", 0)).thenReturn(Flowable.just(expectedList));
        Flowable<List<Comic>> realList = getListComicsUseCase.execute("characterTest", 0);
        TestSubscriber<List<Comic>> testSubscriber = new TestSubscriber<>();
        realList.subscribe(testSubscriber);

        // Assert
        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(expectedList);
    }
}