package cm.security.pagenavi;

/**
 * Created by wychi on 2017/5/15.
 */

abstract class PageFactory {
    abstract IScreenView createNewPage(int pageId);
}
