package testscripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTestT;

public class AddTaskTest extends BaseTestT{
  @Test
  public void testAddTask() throws Exception  {
	  
	  driver.get().get(testingUrl);
	  waitForElem(or.getProperty("frm_addtask"));
	  type(or.getProperty("txt_taskName"),"T123");
	  
	  check(or.getProperty("chk_complete"));
	  clear(or.getProperty("txt_deadline"));
	  
	  selectDate(or.getProperty("dd_year"),"03/10/2019");
	  waitForElem(or.getProperty("chk_complete"));
	  
	  click(or.getProperty("btn_add"));
	  
	  Assert.assertTrue(getText(or.getProperty("li_itemAdded")).equals("T123"));
	  Assert.assertTrue(getText(or.getProperty("btn_status")).equals("complete"));
	  Assert.assertTrue(getText(or.getProperty("p_dueDate")).contains("03/10/2019"));
	  Assert.assertTrue(getAttr(or.getProperty("li_item"),"background-color").equals("rgba(238, 238, 255, 1)"));
	  
	  System.out.println("In AddTaskTest -> test1 with thread Id : "+Thread.currentThread().getId());
	  
	  
  }
}
