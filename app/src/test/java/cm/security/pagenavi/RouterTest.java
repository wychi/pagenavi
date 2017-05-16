package cm.security.pagenavi;

import android.os.Bundle;
import android.os.Handler;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by wychi on 2017/5/15.
 */
public class RouterTest {

    @Mock
    PageFactory mockFactory;

    @Mock
    BasePage page1;

    @Mock
    BasePage page2;

    @Mock
    BasePage page3;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(mockFactory.createNewPage(1)).thenReturn(page1);
        when(mockFactory.createNewPage(2)).thenReturn(page2);
        when(mockFactory.createNewPage(3)).thenReturn(page3);
    }

    @Test
    public void goTo_firstPage() throws Exception {
        Bundle bundle = mock(Bundle.class);
        when(bundle.getInt("TO")).thenReturn(1);

        when(page1.getFadeOutDuration()).thenReturn(1400);


        final ArgumentCaptor<Handler> handlerCaptor = ArgumentCaptor.forClass(Handler.class);
        final Handler handler = mock(Handler.class);

        Router router = new Router(mockFactory, handler);
        router.goTo(bundle);

        verify(page1).onCreate(bundle);
        verify(page1).onEnter(bundle);

        Assert.assertTrue(router.canGoBack());

        Bundle bundle2 = mock(Bundle.class);
        when(bundle2.getInt("TO")).thenReturn(2);

        router.goTo(bundle2);
        verify(handler.postDelayed()).

        verify(page2).onCreate(bundle2);
        verify(page2).onEnter(bundle2);
    }
}