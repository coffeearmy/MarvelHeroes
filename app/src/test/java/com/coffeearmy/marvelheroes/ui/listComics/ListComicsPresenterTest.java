package com.coffeearmy.marvelheroes.ui.listComics;

import com.coffeearmy.marvelheroes.model.Comic;
import com.coffeearmy.marvelheroes.model.mapper.ComicDomainMapper;
import com.coffeearmy.marvelheroes.useCases.GetListComicsUseCase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.Mockito.mock;
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

    @Before
    public void setUp() throws Exception {
        getListComicsUseCaseMock = mock(GetListComicsUseCase.class);
        comicDomainMapperMock = mock(ComicDomainMapper.class);
        idCharacter = "1009415";
        listComicsPresenter = new ListComicsPresenter(getListComicsUseCaseMock
                , comicDomainMapperMock, idCharacter);
    }

    @Test
    public void ListComicsPresenter_OnViewReady_GetComicList() {



    }

}