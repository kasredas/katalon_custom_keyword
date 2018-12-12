import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

/*
 * Test html table
 */

WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.w3schools.com/html/html_tables.asp')

WebUI.waitForPageLoad(0)

def result_array = [
	[''],
	['Alfreds Futterkiste', 'Maria Anders', 'Germany'], 
	['Centro comercial Moctezuma', 'Francisco Chang' , 'Mexico'], 
	['Ernst Handel', 'Roland Mendel', 'Austria'], 
	['Island Trading', 'Helen Bennett', 'UK'], 
	['Laughing Bacchus Winecellars', 'Yoshi Tannamuri', 'Canada'], 
	['Magazzini Alimentari Riuniti', 'Giovanni Rovelli', 'Italy']
	]

CustomKeywords.'table.verify.table_verify'(result_array, 'main')

WebUI.closeBrowser()

