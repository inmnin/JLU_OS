

import PageMgr.*;
import Root_Page.Root_Page;


public class Main {
    public static void main(String[] args) {
        PageMgr.getInstance().special_set(new Root_Page());
    }
}
