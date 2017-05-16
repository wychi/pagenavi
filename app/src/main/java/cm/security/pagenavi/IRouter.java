package cm.security.pagenavi;

import android.os.Bundle;

public interface IRouter extends IActivityCallback {

    void goTo(Bundle bundle);

    void replaceWith(Bundle bundle);

    void goBack();

    /**
     * @return return false if no page to go back, or in transition. otherwise return true.
     */
    boolean canGoBack();

    boolean hasActiveScreen();

}
