package PageMgr;

import Root_Page.Root_Page;

public class PageMgr {
    private static volatile PageMgr instance;
    public PageFactory factory = new PageFactory();
    public Page activepage = null;
    private PageMgr() {
        // 防止通过反射机制调用私有构造器
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static PageMgr getInstance() {
        // 第一次检查，如果实例不存在，则进入同步代码块
        if (instance == null) {
            synchronized (PageMgr.class) {
                // 第二次检查，这次是在同步代码块中
                if (instance == null) {
                    instance = new PageMgr();
                }
            }
        }
        return instance;
    }

    public void setActivepage(PageType type){
        activepage.dispose();
        activepage = factory.create(type);
        activepage.setVisible(true);
    }
    
    public void setActivepage(PageType type,int thread_nums){
        activepage.dispose();
        activepage = factory.create(type,thread_nums);
        activepage.setVisible(true);
    }

    public void special_set(Page page){
        this.activepage = page;
    }
    public void setturn(int turn){
        activepage.turn = turn;
    }
    public Page getActivepage(){
        return activepage;
    }
    public void start(){
        activepage = new Root_Page();
    }
}

