package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTestT;

public class HomePageTest extends BaseTestT{
  
  @Test()
  public void testHomePageLoad() {
	  
	driver.get().get(testingUrl);
	waitForElem(or.getProperty("frm_addtask"));
	Assert.assertTrue(isElementVisible(or.getProperty("lnk_fortinet")));
	Assert.assertTrue(isElementVisible(or.getProperty("hdr_taskList")));
	Assert.assertTrue(isElementVisible(or.getProperty("txt_taskName")));
	Assert.assertTrue(isElementVisible(or.getProperty("txt_deadline")));
	Assert.assertTrue(isElementVisible(or.getProperty("chk_complete")));
	Assert.assertTrue(isElementVisible(or.getProperty("btn_add")));
	  System.out.println("In HomePageTest -> test1 with thread Id : "+Thread.currentThread().getId());
	
  }
  
  @Test()
  public void test2() {
	  driver.get().get(testingUrl);
	  Assert.assertTrue(true);
	  System.out.println("In HomePageTest -> test2 with thread Id : "+Thread.currentThread().getId());
  }
}
