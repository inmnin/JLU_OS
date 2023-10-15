package PageMgr;

import Algorithm_Module.Dekker.Dekker_Option_Page;
import Algorithm_Module.Dekker.General.Dekker_General_Option_Page;
import Algorithm_Module.Dekker.General.Dekker_General_Page;
import Algorithm_Module.Dekker.Typical.Dekker_Typical_Option_Page;
import Algorithm_Module.Dekker.Typical.Dekker_Typical_Page;
import Algorithm_Module.Eisenberg.Eisenberg_Option_Page;
import Algorithm_Module.Eisenberg.Eisenberg_Page;
import Algorithm_Module.Lamport.Lamport_Option_Page;
import Algorithm_Module.Peterson.General.*;
import Algorithm_Module.Peterson.Peterson_Option_Page;
import Algorithm_Module.Peterson.Typical.Peterson_Typical_Option_Page;
import Algorithm_Module.Peterson.Typical.Peterson_Typical_Page;
import Root_Page.Root_Page;

public class PageFactory {

    public Page create(PageType type){
        switch (type){
            case Root_Page:
                return new Root_Page();
            case Dekker_Option_Page:
                return new Dekker_Option_Page();
            case Dekker_General_Option_Page:
                return new Dekker_General_Option_Page();
            case Dekker_Typical_Option_Page:
                return new Dekker_Typical_Option_Page();
            case Peterson_Option_Page:
                return new Peterson_Option_Page();
            case Peterson_Typical_Option_Page:
                return new Peterson_Typical_Option_Page();
            case Peterson_General_Option_Page:
                return new Peterson_General_Option_Page();
            case Lamport_Option_Page:
                return new Lamport_Option_Page();
            case Eisenberg_Option_Page:
                return new Eisenberg_Option_Page();
            case Dekker_Typical_Page:
                return new Dekker_Typical_Page();
            case Peterson_Typical_Page:
                return new Peterson_Typical_Page();
            default:
                return null;
        }
    }

    public Page create(PageType type,int thread_nums){
        switch (type){
            case Lamport_Page:
                return new Eisenberg_Page(thread_nums);
            case Peterson_General_Page:
                return new Peterson_General_Page(thread_nums);
            case Dekker_General_Page:
                return new Dekker_General_Page(thread_nums);
            case Eisenberg_Page:
                return new Eisenberg_Page(thread_nums);
            default:
                return null;
        }
    }
}
