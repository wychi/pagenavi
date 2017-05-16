package cm.security.pagenavi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Stack;

import cm.security.Validate;

public class Router implements IRouter {

    private final PageFactory mFactory;
    private final Handler mHandler;
    HashMap<Integer, IScreenView> mPages;
    Stack<Bundle> mStacks = new Stack<>();
    boolean mInTransition = false;

    public Router(PageFactory factory, Handler handler) {
        mFactory = factory;
        mHandler = handler;
        mPages = new HashMap<>();
    }

    @Nullable
    private IScreenView getPage(Bundle bundle) {
        int pageId = bundle.getInt("TO");
        IScreenView page = mPages.get(pageId);
        if(page== null) {
            page = mFactory.createNewPage(pageId);
            page.onCreate(bundle);
            mPages.put(pageId, page);
        }
        return page;
    }

    @Nullable
    private IScreenView getTopPage() {
        return mStacks.isEmpty() ? null : getPage(mStacks.peek());
    }

    @Override
    public void goTo(final Bundle bundle) {
        if(mInTransition) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    goToImpl(bundle);
                }
            });
        } else {
            goToImpl(bundle);
        }
    }

    private void goToImpl(final Bundle bundle) {
        //Validate.assertRunOnUiThread();

        IScreenView curPage = getTopPage();
        final IScreenView nextPage = getPage(bundle);
        if(nextPage == null) {
            return;
        }

        mInTransition = true;
        int fadeOut = 0;
        if (curPage != null) {
            curPage.onLeave();
            fadeOut = curPage.getFadeOutDuration();
        }

        Runnable r = new Runnable() {
            @Override
            public void run() {
                mStacks.push(bundle);
                nextPage.onEnter(bundle);
                mInTransition = false;
            }
        };

        if(fadeOut > 0) {
            mHandler.postDelayed(r, fadeOut);
        } else {
            r.run();
        }
    }

    @Override
    public void replaceWith(Bundle bundle) {
    }

    @Override
    public void goBack() {

    }

    @Override
    public boolean canGoBack() {
        return !mInTransition && !mStacks.isEmpty();
    }

    @Override
    public boolean hasActiveScreen() {
        return false;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onLanguageChanged() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onHomePressed() {

    }
}
