package cm.security.pagenavi;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by wychi on 2017/5/15.
 */

public class BasePage implements IScreenView {

    @Override
    public boolean isViewCreated() {
        return false;
    }

    @Override
    public void onCreate(Bundle bundle) {

    }

    @Override
    public void onEnter(Bundle data) {
        // FIXME check state first
        onStart();
        // FIXME check state first
        onResume();
    }

    @Override
    public void onLeave() {

        // FIXME check state first
        onPause();

        // TODO check state first
        onStop();
    }

    @Override
    public int getFadeOutDuration() {
        return 0;
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
