package cm.security.pagenavi;

import android.os.Bundle;
public interface IScreenView extends IActivityCallback {

    boolean isViewCreated();

    void onCreate(Bundle bundle);

    void onEnter(Bundle data);

    void onLeave();

    // cancellable
    int getFadeOutDuration();

}
