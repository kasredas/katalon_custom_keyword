package table
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable
import net.sourceforge.htmlunit.corejs.javascript.ast.NewExpression
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.By.ByClassName
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.util.KeywordUtil

class verify {

	/**
	 * 
	 * Verify table content 
	 * html table
	 * result_array is defined in TC
	 * like:
	 * def result_array[[''],['']]
	 * 
	 **/

	@Keyword
	def table_verify(result_array, String outerTagName){

		WebDriver driver = DriverFactory.getWebDriver()

		WebElement Webtable=driver.findElement(By.cssSelector('#' + outerTagName +' table'));

		List<WebElement> TotalRowCount=Webtable.findElements(By.cssSelector('#' + outerTagName + ' tbody tr'));

		// Iterate the Table

		int RowIndex=0;
		for(WebElement rowElement:TotalRowCount)
		{
			List<WebElement> TotalColumnCount=rowElement.findElements(By.xpath("td"));
			int ColumnIndex=0;
			for(WebElement colElement:TotalColumnCount)
			{
				// Make sure array and table values is equal
				if ( result_array[RowIndex][ColumnIndex] == colElement.getText() ) {
					KeywordUtil.markPassed('SUCCESS: table match')
				} else {

					// Mark as failed if values do not match
					// Show where error occurred
					KeywordUtil.markFailed( "FAILED on row "+RowIndex+" and column "+ColumnIndex+" expected '"+result_array[RowIndex][ColumnIndex]+"' and got: '"+colElement.getText()+"'", FailureHandling.STOP_ON_FAILURE)
				}

				// Next column
				ColumnIndex=ColumnIndex+1;
			}
			// Next row
			RowIndex=RowIndex+1;
		}


	}

	/*
	 * Verify DIV table content (passing locator)
	 * DIV table
	 * result_array is defined in TC
	 * like:
	 * def result_array[[''],['']]
	 * 
	 */

	@Keyword
	def div_table_verify(result_array,TestObject TableLocator){

		WebDriver driver = DriverFactory.getWebDriver()
		WebElement Webtable=WebUiBuiltInKeywords.findWebElement(TableLocator);
		List<WebElement> TotalRowCount=Webtable.findElements(By.cssSelector("div.ss-dt-row"));

		'To calculate no of rows'
		int row_count = TotalRowCount.size()

		int RowIndex=0;
		for(WebElement rowElement:TotalRowCount)
		{
			List<WebElement> TotalColumnCount=rowElement.findElements(By.cssSelector("div.ss-dt-row div"));

			'To calculate no of Cells'
			int cell_count = TotalColumnCount.size()
			println((('Rows: ' + row_count) + ' with cells: ') + cell_count)

			int ColumnIndex=0;
			for(WebElement colElement:TotalColumnCount)
			{
				// Make sure array and table values is equal
				if ( result_array[RowIndex][ColumnIndex] == colElement.getText() ) {
					KeywordUtil.markPassed('SUCCESS: table match')
				} else {

					// Mark as failed if values do not match
					// Show where error occurred
					KeywordUtil.markFailed( "FAILED on row "+RowIndex+" and column "+ColumnIndex+" expected '"+result_array[RowIndex][ColumnIndex]+"' and got: '"+colElement.getText()+"'", FailureHandling.STOP_ON_FAILURE )

				}

				// Next column
				ColumnIndex=ColumnIndex+1;
			}
			// Next row
			RowIndex=RowIndex+1;
		}

	}
}
