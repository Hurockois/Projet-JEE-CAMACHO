package edu.orsys.jee.tests;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class AuthentificationTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
	System.setProperty("webdriver.chrome.driver", "c:/users/chromedriver.exe");
	ChromeOptions chromeOptions = new ChromeOptions();
	chromeOptions.addArguments("headless");
	driver = new ChromeDriver(chromeOptions);
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void fT00101() {
    driver.get("http://localhost:8080/Projet-JEE-CAMACHO/");
    driver.manage().window().setSize(new Dimension(1731, 1093));
    assertThat(driver.getTitle(), is("Page d\'authentification"));
  }
  @Test
  public void fT00102() {
    driver.get("http://localhost:8080/Projet-JEE-CAMACHO/");
    driver.manage().window().setSize(new Dimension(1731, 1093));
    driver.findElement(By.cssSelector(".container")).click();
    driver.findElement(By.cssSelector(".btn")).click();
    driver.findElement(By.id("login")).sendKeys("kubgqsiuhgsq");
    assertThat(driver.findElement(By.cssSelector(".card-body:nth-child(2)")).getText(), is("Desole, utilisateur non reconnu"));
    driver.findElement(By.cssSelector(".card-body:nth-child(2)")).click();
  }
  @Test
  public void fT00103() {
    driver.get("http://localhost:8080/Projet-JEE-CAMACHO/");
    driver.manage().window().setSize(new Dimension(1731, 1093));
    driver.findElement(By.id("login")).sendKeys("hugo31");
    driver.findElement(By.id("pwd")).sendKeys("uhgoiuh");
    driver.findElement(By.cssSelector(".container")).click();
    driver.findElement(By.cssSelector(".btn")).click();
    assertThat(driver.findElement(By.cssSelector(".card-body:nth-child(2)")).getText(), is("Desole, le mot de passe est erroné pour cet utilisateur"));
    driver.findElement(By.cssSelector(".card-body:nth-child(2)")).click();
  }
  @Test
  public void fT00104() {
    driver.get("http://localhost:8080/Projet-JEE-CAMACHO/");
    driver.findElement(By.id("login")).sendKeys("hugo31");
    driver.findElement(By.id("pwd")).sendKeys("toto");
    driver.findElement(By.id("pwd")).sendKeys(Keys.ENTER);
    assertThat(driver.getTitle(), is("Bienvenue administrateur!"));
  }
  @Test
  public void fT00105() {
    driver.get("http://localhost:8080/Projet-JEE-CAMACHO/");
    driver.findElement(By.id("login")).sendKeys("meli");
    driver.findElement(By.id("pwd")).sendKeys("toto");
    driver.findElement(By.id("pwd")).sendKeys(Keys.ENTER);
    assertThat(driver.getTitle(), is("Bienvenue user!"));
  }
}
