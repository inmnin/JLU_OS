package PageMgr;

import Dekker.Dekker_Option_Page;
import Dekker.General.Dekker_General_Option_Page;
import Dekker.General.Dekker_General_Page;
import Dekker.Typical.Dekker_Typical_Option_Page;
import Dekker.Typical.Dekker_Typical_Page;
import Eisenberg.Eisenberg_Option_Page;
import Eisenberg.Eisenberg_Page;
import Lamport.Lamport_Option_Page;
import Lamport.Lamport_Page;
import Peterson.General.Peterson_General_Option_Page;
import Peterson.General.Peterson_General_Page;
import Peterson.Peterson_Option_Page;
import Peterson.Typical.Peterson_Typical_Option_Page;
import Peterson.Typical.Peterson_Typical_Page;
import Root_Page.Root_Page;

public enum PageType{
    Root_Page,
    Dekker_Option_Page,
    Dekker_Typical_Option_Page,
    Dekker_General_Option_Page,
    Peterson_Option_Page,
    Peterson_Typical_Option_Page,
    Peterson_General_Option_Page,
    Lamport_Option_Page,
    Eisenberg_Option_Page,

    Dekker_Typical_Page,
    Dekker_General_Page,
    Peterson_Typical_Page,
    Peterson_General_Page,
    Lamport_Page,
    Eisenberg_Page;
}
