package cn.com.do1.component.basis.label.ui;
import cn.com.do1.dqdp.creater.ComponentCreater;

public class Main {
    static public void main(String[] args) throws Exception {
		ComponentCreater createrTester = new ComponentCreater("F:\\src\\main\\java", "fwtsystemmgr.fwtPersonChanneAlter", "default");
		createrTester.addTable(new String[] { "fwtms", "FWT_PERSON_CHANNE_ALTER"});
        try {
            createrTester.buildPO();
            createrTester.buildResources();
            createrTester.buildJavaCode();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        Cache cache = manager.getCache("sampleCache1");
//        cache.put();

    }
}
