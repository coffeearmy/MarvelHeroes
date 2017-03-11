package com.coffeearmy.marvelheroes.ui.listComics;

import com.coffeearmy.marvelheroes.data.exception.ApiException;
import com.coffeearmy.marvelheroes.model.Comic;
import com.coffeearmy.marvelheroes.model.ComicView;
import com.coffeearmy.marvelheroes.model.mapper.ComicDomainMapper;
import com.coffeearmy.marvelheroes.useCases.GetListComicsUseCase;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.subscribers.BlockingBaseSubscriber;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * :3
 */
public class ListComicsPresenterTest {

    //Testee
    private ListComicsPresenter listComicsPresenter;

    //Mock
    private GetListComicsUseCase getListComicsUseCaseMock;
    private ComicDomainMapper comicDomainMapperMock;
    private String idCharacter;
    private ListComicsViewModel lisComicsViewModelMock;

    @Before
    public void setUp() throws Exception {
        getListComicsUseCaseMock = mock(GetListComicsUseCase.class);
        comicDomainMapperMock = mock(ComicDomainMapper.class);
        idCharacter = "1009415";
        listComicsPresenter = new ListComicsPresenter(getListComicsUseCaseMock
                , comicDomainMapperMock, idCharacter);

        lisComicsViewModelMock= mock(ListComicsViewModel.class);
        listComicsPresenter.attachView(lisComicsViewModelMock);

    }


    @BeforeClass
    public static void setupClass() {
        //When testing RX we need to change the schedulers of test won't work
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    @AfterClass
    public static void tearDownClass() {
       //Cleaning the mainthreadSchedulerHandelr
        RxAndroidPlugins.reset();
    }

    @Test
    public void ListComicsPresenter_OnViewReady_GetComicList() {

        // Arrange
        List<Comic> expectedList = new ArrayList<>();
        Comic expectedComic = new Comic();
        expectedComic.setTitle("BEST COMIC EVER");
        expectedComic.setDescription("BETTER THAN TWILIGHT!!");
        expectedList.add(expectedComic);

        // Act
        when(getListComicsUseCaseMock.execute(idCharacter, 0)).thenReturn(Flowable.just(expectedList));
        listComicsPresenter.onViewReady();

        // Assert
        verify(getListComicsUseCaseMock).execute(eq(idCharacter),anyInt());
        verify(lisComicsViewModelMock).showComics((List<ComicView>) any());
        verify(lisComicsViewModelMock).hideLoading();

    }

}