package cm.security.pagenavi;

import android.os.Bundle;
import android.os.Handler;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by wychi on 2017/5/15.
 */
public class RouterTest {

    @Mock
    Handler mMockHandler;

    @Mock
    PageFactory mMockFactory;

    @Mock
    BasePage page1;

    @Mock
    BasePage page2;

    @Mock
    BasePage page3;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(mMockFactory.createNewPage(1)).thenReturn(page1);
        when(mMockFactory.createNewPage(2)).thenReturn(page2);
        when(mMockFactory.createNewPage(3)).thenReturn(page3);
    }

    @Test
    public void goTo_firstPage() throws Exception {
        Router router = new Router(mMockFactory, mMockHandler);

        Bundle bundle = mock(Bundle.class);
        when(bundle.getInt("TO")).thenReturn(1);

        router.goTo(bundle);

        verify(page1).onCreate(bundle);
        verify(page1).onEnter(bundle);
        Assert.assertTrue(router.canGoBack());
    }

    @Test
    public void goTo_secondPage() throws Exception {
        Router router = new Router(mMockFactory, mMockHandler);

        {
            Bundle bundle = mock(Bundle.class);
            when(bundle.getInt("TO")).thenReturn(1);
            when(page1.getFadeOutDuration()).thenReturn(600);
            router.goTo(bundle);
        }

        {
            Bundle bundle2 = mock(Bundle.class);
            when(bundle2.getInt("TO")).thenReturn(2);

            final ArgumentCaptor<Runnable> runnableCaptor = ArgumentCaptor.forClass(Runnable.class);
            router.goTo(bundle2);

            // in transition
            // TODO: check in-transition conditions

            // wait for page1 fade-out animation
            verify(mMockHandler).postDelayed(runnableCaptor.capture(), anyLong());
            List<Runnable> runnables = runnableCaptor.getAllValues();
            for(Runnable r : runnables) {
                r.run();
            }

            verify(page2).onCreate(bundle2);
            verify(page2).onEnter(bundle2);
        }

        Assert.assertEquals(2, router.getStackNum());
        Assert.assertTrue(router.canGoBack());
    }

    @Test
    public void goTo_and_back_key() throws Exception {
        Router router = new Router(mMockFactory, mMockHandler);

        {
            Bundle bundle = mock(Bundle.class);
            when(bundle.getInt("TO")).thenReturn(1);
            when(page1.getFadeOutDuration()).thenReturn(600);
            router.goTo(bundle);
        }

        {
            Bundle bundle2 = mock(Bundle.class);
            when(bundle2.getInt("TO")).thenReturn(2);

            final ArgumentCaptor<Runnable> runnableCaptor = ArgumentCaptor.forClass(Runnable.class);
            router.goTo(bundle2);

            Assert.assertTrue(router.onBackPressed());

            // wait for page1 fade-out animation
            verify(mMockHandler).postDelayed(runnableCaptor.capture(), anyLong());
            Runnable r = runnableCaptor.getValue();
            r.run();

            verify(page2).onCreate(bundle2);
            verify(page2).onEnter(bundle2);
        }

    }
}