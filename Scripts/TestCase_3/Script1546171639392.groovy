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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.amazon.in/')

WebUI.click(findTestObject('Object Repository/TC_003/Page_Online Shopping site in India/span_Hello. Sign in'))

String input2 = "//input[@id='ap_email']"
Login(input2)
//WebUI.modifyObjectProperty(findTestObject('null'), 'xpath', 'equals', '//*[@ID=\"button\"]', true)

//WebUI.setText(findTestObject('Object Repository/TC_003/Page_Amazon Sign In/input_Email or mobile phone nu'), 'shfghsfdgsdfgsdfgsdfg')

//WebUI.setText(findTestObject('Object Repository/CustomProp/CustomTester'), '65431654321654')

/*WebUI.click(findTestObject('Object Repository/TC_003/Page_Amazon Sign In/input_Enter your email or mobi'))

WebUI.setEncryptedText(findTestObject('Object Repository/TC_003/Page_Amazon Sign In/input_Forgot Password_password'), 'fM7t3BtWcg7XWlXgGQCOow==')

WebUI.click(findTestObject('Object Repository/TC_003/Page_Amazon Sign In/input_Enter your password_sign'))

WebUI.click(findTestObject('Object Repository/TC_003/Page_Online Shopping site in India/span_Hello Sathish'))

WebUI.click(findTestObject('Object Repository/TC_003/Page_Your Account/div_Your Orders'))

WebUI.closeBrowser()*/

void Login(String xpather){
	
	TestObject myTestObject = new TestObject("customObject");
	
	List<TestObjectProperty> properties = new ArrayList<TestObjectProperty>();
	properties.add(new TestObjectProperty("xpath", ConditionType.EQUALS, ""+xpather));
	//properties.add(new TestObjectProperty("name", ConditionType.EQUALS, "email"));
	
	//Set the object
	myTestObject.setProperties(properties);
	WebUI.setText(myTestObject, "FromPropertiesObject");

}