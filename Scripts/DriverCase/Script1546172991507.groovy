import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.junit.After

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectXpath
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

WebUI.openBrowser('')

//Global values declaration
class Public {
	static TestData td = findTestData("Data1")
	static testcase_id = new LinkedHashMap<String,String>()
	static testURL = new LinkedHashMap<String,String>()
	static teststeps = new LinkedHashMap<String,LinkedHashMap<Integer,String>>()
 }
//testscript starts here
ReadtestcaseSelection()
testExecutor()


def ReadtestcaseSelection(){
	int colsize = Public.td.getRowNumbers()
	int j=0
	for(int i=1;i<=colsize;i++){
		if(Public.td.getValue("Enable", i).equalsIgnoreCase("Yes")){
			Public.testcase_id.put(i,Public.td.getValue("TestCase_ID", i))
			Public.testURL.put(Public.td.getValue("TestCase_ID", i),Public.td.getValue("URL", i))
			Public.teststeps.put(Public.td.getValue("TestCase_ID", i),CaseStepNames(Public.td.getValue("Steps", i)))
		}
	}
}

 


def testExecutor(){
	TestData locatorSheet = findTestData("Locator1")
	LinkedHashMap<String,String> steps = new LinkedHashMap<String,String>()
	ArrayList locator_all_data = new ArrayList()
	TestObject tobj = new TestObject("PropertyTransfer")
	locator_all_data = locatorSheet.getAllData()
	
		
	
	for(int i=1;i<=Public.testcase_id.size();i++){
		String case_id = Public.testcase_id.get(i)
		WebUI.navigateToUrl(Public.testURL.get(case_id))
		steps = Public.teststeps.get(case_id)
		for(int j=1;j<=steps.size();j++){
			for(int k=0;k<locator_all_data.size();k++){
				String rowval = locator_all_data.get(k)
				int a = 1
				if(rowval.split(",")[0].replace("[","").replace("]","").equals(steps.get(j))){
					println rowval.split(",")[3].toString().trim()
					switch(rowval.split(",")[3].toString().trim()){
						case "Click":
							tobj = setupXpath(rowval.split(",")[2])
							WebUI.click(tobj)
							println steps.get(j)
							Result(steps.get(j))
								
						break
						
						case "setText":
							tobj = setupXpath(rowval.split(",")[2])
							WebUI.setText(tobj, rowval.split(",")[4].replace("[","").replace("]",""))
						break
						default:
						println "need to sepcify operation-"+rowval.split(",")[3].toString().trim()
						break
					}
				}
			}
		}
	}
}

def Result(String Step){
	TestData ResultSheet = findTestData("Result1")
	ArrayList locator_allResult_data = new ArrayList()
	locator_allResult_data = ResultSheet.getAllData()
	println locator_allResult_data.size()
	for(int k=0;k<locator_allResult_data.size();k++){
		String Resultrowval = locator_allResult_data.get(k)
		println Step
		println Resultrowval.split(",")[1].trim()
		if(Resultrowval.split(",")[1].trim().equals(Step)){
			println Resultrowval.split(",")[2].toString().trim()
			println Resultrowval.split(",")[2].toString().trim()
			ResultsetupXpath(Resultrowval.split(",")[2])
			//boolean elementpresent= WebUI.verifyElementPresent(findTestObject('Page_TestLink/input_Sign up_doEditUser'), 20)
			
				//println elementpresent;
		}
	}
				
}
	


def CaseStepNames(String st){
	LinkedHashMap<Integer,String> steps_names= new LinkedHashMap<Integer,String>()
	String[] arrOfStr = st.split(",", 0);
	int i=1
	for (String a : arrOfStr){
		steps_names.put(i,a)
		i++
		}
	return steps_names
}

def ResultsetupXpath(String Resultxpath_val){
	
	TestObject ResultmyTestObject = new TestObject("customObject");
	println Resultxpath_val.toString().trim()
	
	List<TestObjectProperty> Resultproperties = new ArrayList<TestObjectProperty>();
	Resultproperties.add(new TestObjectProperty("xpath", ConditionType.EQUALS, ""+ Resultxpath_val.toString().trim()));
	//Set the object
	println Resultproperties 
	ResultmyTestObject.setProperties(Resultproperties);
	
	//boolean elementpresent=WebUI.verifyElementAttributeValue(TestObject ResultmyTestObject, Resultxpath_val.toString().trim(), Resultxpath_val.toString().trim(), 20)
	boolean elementpresent = WebUI.verifyElementPresent(ResultmyTestObject, 20)

	//WebUI.setText(myTestObject, "FromPropertiesObject");
	//WebUI.click(myTestObject)
//	boolean elementpresent= WebUI.verifyElementPresent(findTestObject('Resultproperties'), 20);
	println elementpresent;
	println elementpresent;
	println elementpresent;
	if(elementpresent.equals(true)){
		println "Pass"
	}else{
	println "Fail"
	}
	return elementpresent
}


def setupXpath(String xpath_val){
	
	TestObject myTestObject = new TestObject("customObject");
	
	List<TestObjectProperty> properties = new ArrayList<TestObjectProperty>();
	properties.add(new TestObjectProperty("xpath", ConditionType.EQUALS, ""+xpath_val));
	//Set the object
	myTestObject.setProperties(properties);
	//WebUI.setText(myTestObject, "FromPropertiesObject");
	//WebUI.click(myTestObject)
	return myTestObject
}



//WebUI.closeBrowser()

