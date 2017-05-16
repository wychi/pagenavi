package cm.security.pagenavi;

import android.content.Intent;
import android.os.Bundle;

interface IActivityCallback {

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onSaveInstanceState(Bundle outState);

    void onRestoreInstanceState(Bundle savedInstanceState);

    void onLanguageChanged();

    void onActivityResult(int requestCode, int resultCode, final Intent data);

    boolean onBackPressed();

    void onHomePressed();
}
