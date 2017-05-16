package cm.security;

import android.os.Looper;

/**
 * Created by wychi on 2017/5/15.
 */

public class Validate {
    public static void asserTrue(boolean b) {
        if(!b)
            throw new RuntimeException("Assertion Fail");
    }

    public static void assertRunOnUiThread() {
        Validate.asserTrue( Looper.myLooper() == Looper.getMainLooper());
    }
}
