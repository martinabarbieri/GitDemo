package com.modulodatitest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
public class ListaModuloDatiNotevoli extends LoginModuloDati {
	public WebDriverWait wait;
	public WebDriver driver;
	//(groups= {"GroupA"})
	@BeforeSuite 
	public void ModuliDatiNotevoli() throws IOException {
	driver=LoginModuloDatiNotevoli(); 
	}
	
	//(groups= {"GroupA"})
	@Test 
	public void AddModuli() throws InterruptedException {
		// Click bottom "Nuovo Modulo"
		
		wait = new WebDriverWait(driver, 10000); // Wait time for events
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-filter-dsModulo")));
	
		// Add new Module 1ST
  	    Thread.sleep(2000);
  		driver.findElement(By.id("btn-add-modulo")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='row grey-bg ng-scope borderModalInfo']")));
		//Add name 1ST Modulo
		WebElement firstModulo= driver.findElement(By.id("input-dsModulo"));
		String namefirstModulo="AAA test automatizzati";
		firstModulo.sendKeys(namefirstModulo);
		//Choose cd-template for 1ST Modulo
		driver.findElement(By.id("select-cdTemplate")).click();
		Thread.sleep(2000);
		Select defaultTemplate = new Select(driver.findElement(By.id("select-cdTemplate")));
		defaultTemplate.selectByVisibleText("Default");
		driver.findElement(By.cssSelector(".btn.btn-lg.btn-primario")).click();
		Thread.sleep(2000);

		// Add new Module 2ND
  	    Thread.sleep(2000);
  		driver.findElement(By.id("btn-add-modulo")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='row grey-bg ng-scope borderModalInfo']")));
		//Add name 2ND Modulo
		WebElement secondModulo= driver.findElement(By.id("input-dsModulo"));
		String namesecondModulo="BBB test automatizzati";
		secondModulo.sendKeys(namesecondModulo);
		driver.findElement(By.cssSelector(".btn.btn-lg.btn-primario")).click();
		Thread.sleep(2000);
		
		// Define first element in list and check if It's the first one in list
		WebElement firstplace= driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/span[1]"));
		String namefirstplace= firstplace.getText();	
		if (namefirstplace.equals(namesecondModulo)) {
			Assert.assertTrue(true);
			System.out.println("OK THE LAST MODULE JUST CREATED IS THE FIRST ONE IN LIST");}
		else {
			System.out.println("KO THE LAST MODULE JUST CREATED ISN'T THE FIRST ONE IN LIST");
			Assert.assertFalse(false);}
	
		//Display of modal error in case of adding a module already created
		Thread.sleep(2000);
  		driver.findElement(By.id("btn-add-modulo")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='row grey-bg ng-scope borderModalInfo']")));
		driver.findElement(By.id("input-dsModulo")).sendKeys(namefirstModulo);
		driver.findElement(By.cssSelector(".btn.btn-lg.btn-primario")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='row grey-bg ng-scope mdl-borderModalError']")));
		WebElement modalerror=driver.findElement(By.xpath("//div[@class='row grey-bg ng-scope mdl-borderModalError']"));
		if (modalerror.isDisplayed()) {
			driver.findElement(By.cssSelector(".btn.close.closeModal")).click();
			System.out.println("Modal error: Module AAA already exists");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		}else {	
		System.out.println("Modal error not displayed");
		Assert.assertTrue(false);
		}
	}	
	//(groups= {"GroupA"})
	@Test 
	public void ChangeNameModulo() throws InterruptedException {
	//Change name - Rinomina
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[text()='Rinomina']")).click();
	wait.until(ExpectedConditions
			.visibilityOfElementLocated(By.xpath("//div[@class='row grey-bg ng-scope borderModalInfo']")));
	WebElement RinominaModulo= driver.findElement(By.id("input-dsModulo"));
	RinominaModulo.clear();
	RinominaModulo.sendKeys("Modulo Dati Rinominato");
	driver.findElement(By.xpath("//button[@class='btn btn-lg btn-primario']")).click();
	Thread.sleep(2000);
	//Click on Rinomina and cancel
	driver.findElement(By.xpath("//*[text()='Rinomina']")).click();
	driver.findElement(By.cssSelector(".btn.close.closeModal")).click();
	Thread.sleep(2000);
	System.out.println("Rename cancelled");
	Thread.sleep(2000);
}
	//(groups= {"GroupA"})
/*@Test 
public void RemoveModulo() throws InterruptedException {
	driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[2]/div[4]/button[1]/span[1]")).click();
	wait.until(ExpectedConditions
			.visibilityOfElementLocated(By.xpath("//div[@class='row grey-bg ng-scope borderModalInfo']")));
	Assert.assertEquals(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/span[1]")).getText(), "Confermi di voler eliminare");
	driver.findElement(By.xpath("//button[@class='btn btn-lg btn-primario ng-scope']")).click();
	System.out.println("OK Modulo Dati Rinominato created earlier now it's removed");
	//Click on Cancel - Cancel RemoveModule Procedure
	Thread.sleep(2000);
	driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[2]/div[4]/button[1]/span[1]")).click();
	wait.until(ExpectedConditions
			.visibilityOfElementLocated(By.xpath("//div[@class='row grey-bg ng-scope borderModalInfo']")));
	driver.findElement(By.cssSelector(".btn.close.closeModal")).click();
	System.out.println("Remove module cancelled");
	
}
/*(groups= {"GroupA"})
@Test 
public WebDriver SAccessModuleDetails() throws InterruptedException  {
System.out.println("Dettaglio Modulo");
driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]")).click();
Thread.sleep(2000);
return driver;
}*/

@Test
public void SectionAdding() throws InterruptedException {
	//Add Section in Module in which visibilit‡ is not selected - stampabilit‡ is selected - check
	driver=SAccessModuleDetails();
	driver.findElement(By.id("btn-add-modulo")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row grey-bg ng-scope borderModalInfo']")));
	driver.findElement(By.id("input-dsSezione")).sendKeys("Sezione");
	driver.findElement(By.id("input-fgVisualizzazione")).click();
	Assert.assertFalse(driver.findElement(By.id("input-fgVisualizzazione")).isSelected());
	System.out.println("Not selected Checkbox Visibilit‡");
	driver.findElement(By.xpath("//button[@ng-click=\'insertSezione()\']")).click();	
	Thread.sleep(2000);
	Assert.assertTrue(driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/span[@class='glyphicon glyphicon-eye-open']")).isDisplayed());
	System.out.println("Not selected Checkbox Visibilit‡ - display grey eye icon");
	Thread.sleep(2000);
	Assert.assertTrue(driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/span[@class='glyphicon glyphicon-print ute-ok']")).isDisplayed());
	System.out.println("Selected Checkbox Stampabilit‡ - display green print icon");
	//driver.WebDriver.attachToSession(executor, session_id);
	System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	System.out.println("modifico getstuff aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
}


@Test
public void UploadFileDomandaSemplice() throws InterruptedException{
	//Add Requisito in a section (first one in Section List) - metodo da richiamare in tutti i requisiti
	System.out.println("We're creating a generic reference in the first section of a module");
	driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[5]/button[1]")).click();
	Thread.sleep(2000);	
	Select TipoRequisito=new Select(driver.findElement(By.id("requisito-cdTipoRequisito")));
	TipoRequisito.selectByVisibleText("Domanda Semplice");
	Select TipoOggetto=new Select(driver.findElement(By.id("reqTipoOggetto-tipoOggetto")));
	TipoOggetto.selectByVisibleText("Upload File");
	Assert.assertTrue(driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[3]/select[@disabled='disabled']")).isDisplayed());
	System.out.println("menu a tendina disabilitato");
}


/*
@Test
public void UploadFileDomandaSempliceCampiObbligatori() throws InterruptedException {
	//check if mandatory fields are red highlighted --class errorFormField--
	driver.findElement(By.cssSelector("[ng-click=\'salva()\']")).click();
	WebElement label=driver.findElement(By.id("input-dsLabelRequisito"));
	if (label.getAttribute("class").contains("errorFormField")) {System.out.println("---write label---now it's correctly empty");} else {Assert.assertTrue(false);}
	
	WebElement descrizione=driver.findElement(By.id("requisito-dsRequisito"));
	if (descrizione.getAttribute("class").contains("errorFormField")) {System.out.println("---write descrizione---now it's correctly empty");} else {Assert.assertTrue(false);}
	
	WebElement tipologiaRichiestaEl=driver.findElement(By.name("uploadFile-tipologiaRichiesta"));
	if (tipologiaRichiestaEl.getAttribute("class").contains("errorFormField")) {System.out.println("---select tipologia richiesta---now it's correctly empty");} else {Assert.assertTrue(false);}
	
	WebElement tipoObbligatoriet‡El=driver.findElement(By.name("uploadFile-tipologiaRichiesta"));
	if (tipoObbligatoriet‡El.getAttribute("class").contains("errorFormField")) {System.out.println("---select tipo obbligatoriet‡---now it's correctly empty");} else {Assert.assertTrue(false);}
	
	WebElement tipoInvioRispostaEl=driver.findElement(By.name("uploadFile-tipologiaRichiesta"));
	if (tipoInvioRispostaEl.getAttribute("class").contains("errorFormField")) {System.out.println("---select tipo invio risposta---now it's correctly empty");} else {Assert.assertTrue(false);}
	
	WebElement facSimile=driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/ng-form[1]/div[3]/div[1]/span[1]"));
	if (facSimile.getText().contains("fac-simile obbligatorio")){System.out.println("---select a fac simile file---now it's correctly empty");} else {Assert.assertTrue(false);}
	
	// creation of three select elements, upload facsimile and filling in form fields
	label.sendKeys("LABEL");
	descrizione.sendKeys("DESCRIZIONE");
	Select tipologiaRichiesta=new Select(driver.findElement(By.name("uploadFile-tipologiaRichiesta")));
	Select tipoObbligatoriet‡=new Select(driver.findElement(By.name("uploadFile-tipoObbligatorieta")));
	Select tipoInvioRisposta=new Select(driver.findElement(By.name("uploadFile-tipoInvioRisposta")));
	
	tipologiaRichiesta.selectByVisibleText("DOMANDA ABILITAZIONE");
	tipoObbligatoriet‡.selectByVisibleText("Obbligatorio");
	tipoInvioRisposta.selectByVisibleText("Invio Telematico");
	
	
	WebElement uploadFacSimile  = driver.findElement(By.xpath("//input[@id='uploadFile-input']"));
	uploadFacSimile.sendKeys("C:\\Users\\Francesco\\eclipse-workspaceNew\\ModuloDati\\src\\com\\consip\\trasformazione2\\modulodati\\testo.txt");
	
	Thread.sleep(2000);
	driver.findElement(By.cssSelector("[ng-click=\'salva()\']")).click();
	
	
}

@Test
public void ZZTestoLiberoNumericoDomandaSemplice() throws InterruptedException{
			SectionRequisitoAdding();
			wait = new WebDriverWait(driver, 10000); // Wait time for events
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='ANNULLA']")));
			
			Select TipoRequisito=new Select(driver.findElement(By.id("requisito-cdTipoRequisito")));
			TipoRequisito.selectByVisibleText("Domanda Semplice");
			Select TipoOggetto=new Select(driver.findElement(By.id("reqTipoOggetto-tipoOggetto")));
			TipoOggetto.selectByVisibleText("Testo Libero");
			//Assert.assertTrue(driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[3]/select[@disabled='disabled']")).isDisplayed());
			//Thread.sleep(4000);
			//System.out.println("menu a tendina disabilitato");
			//driver.findElement(By.xpath("//*[text()='ANNULLA']")).click();
			Thread.sleep(2000);
		}

	*/
}

