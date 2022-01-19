import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class test_for_HT_1 {

    private WebDriver driver;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();               //создали экзаемпляр хром драйвера
        driver.manage().window().maximize();       //открыли браузер на весь экран (по умолчанию на процентов 80 открывает
        driver.get("https://www.bbc.com");         //открыли сайт  BBC1
    }

    @Test(priority = 1)
    public void checkTestPart1BBC1() {
        WebElement element = driver.findElement(xpath("//div[@id='orb-nav-links']//li[@class='orb-nav-newsdotcom']/a[@href='https://www.bbc.com/news']"));
        element.click();
        WebElement elementNews = driver.findElement(xpath("//div[@class='gs-c-promo-body gs-u-display-none gs-u-display-inline-block@m gs-u-mt@xs gs-u-mt0@m gel-1/3@m']//a[@class='gs-c-promo-heading gs-o-faux-block-link__overlay-link gel-paragon-bold nw-o-link-split__anchor']/h3[@class='gs-c-promo-heading__title gel-paragon-bold nw-o-link-split__text']"));
        String elementText = elementNews.getText();
        assertEquals("Tonga says tsunami was 'unprecedented disaster'", elementText);
    }

    @Test(priority = 2)
    public void checkTest2Part1BBC1() {
        String[] articles  = new String[43];
        articles[0] = "Tonga tsunami: Before and after eruption";
        articles[1] = "Nobody told me drinks event broke rules - Johnson";
        articles[2] = "Saudis warned of jail time for posting rumours";
        // amount = 43 articles
        WebElement element = driver.findElement(xpath("//div[@id='orb-nav-links']//li[@class='orb-nav-newsdotcom']/a[@href='https://www.bbc.com/news']"));
        element.click();
        List<WebElement> elementNewsArticles = driver.findElements(xpath("//a[@class='gs-c-promo-heading gs-o-faux-block-link__overlay-link gel-pica-bold nw-o-link-split__anchor']"));
        for (int i=0; i<elementNewsArticles.size(); i++){
            assertEquals(articles[i], elementNewsArticles.get(i).getText());
        }

        int actualElementsSize = elementNewsArticles.size();
        assertEquals(actualElementsSize, 43);
    }


    @Test(priority = 3)
    public void checkTest3Part1BBC1() {
        WebElement element = driver.findElement(xpath("//div[@id='orb-nav-links']//li[@class='orb-nav-newsdotcom']/a[@href='https://www.bbc.com/news']"));
        element.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-entityid='container-top-stories#1']")));

        String url = driver.getCurrentUrl();
            //https://www.bbc.com/news/world-asia-60034179
            int index1 = url.indexOf("/", 22);
            int index2 = url.indexOf("-", 22);
            String keyword = url.substring(index1,index2);

        driver.findElement(xpath("//input[@placeholder='Search']")).sendKeys(keyword);

        List<WebElement> elementNewsArticles = driver.findElements(xpath("//a[@class='ssrcss-atl1po-PromoLink e1f5wbog0']/span/p"));
            assertEquals("Word", elementNewsArticles.get(0).getText());
    }


    @Test(priority = 4)
    public void checkTest1Part2BBC1() {
        WebElement element = driver.findElement(xpath("//div[@id='orb-nav-links']//li[@class='orb-nav-newsdotcom']/a[@href='https://www.bbc.com/news']"));
        element.click();

        WebElement elementCorona = driver.findElement(xpath("//li[contains(@class, 'gs-u-float-left nw-c-nav__wide-menuitem-container')]//a[@href='/news/coronavirus']"));
        elementCorona.click();

        WebElement elementCoronaStory = driver.findElement(xpath("//li[@class='gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__secondary-menuitem-container']/a[@class='nw-o-link'][@href='/news/have_your_say']"));
        elementCoronaStory.click();

        WebElement elementSignIn = driver.findElement(xpath("//a[@id='idcta-link']"));
        elementSignIn.click();

        driver.findElement(xpath("//input[@type='email']")).sendKeys("ssantonenko@gmail.com");
        driver.findElement(xpath("//input[@type='password']")).sendKeys("password");

        WebElement elementSignInButton = driver.findElement(xpath("//button[@class='button button--full-width']"));
        elementSignInButton.click();

        WebElement webElementAns = driver.findElement(xpath("//p[@class='form-message__text']/span/span"));
        assertTrue(webElementAns.getText().contains("Sorry, that password isn't valid"));
    }

    @Test(priority = 5)
    public void checkTest2Part2BBC1() {
        WebElement element = driver.findElement(xpath("//div[@id='orb-nav-links']//li[@class='orb-nav-newsdotcom']/a[@href='https://www.bbc.com/news']"));
        element.click();

        WebElement elementCorona = driver.findElement(xpath("//li[contains(@class, 'gs-u-float-left nw-c-nav__wide-menuitem-container')]//a[@href='/news/coronavirus']"));
        elementCorona.click();

        WebElement elementCoronaStory = driver.findElement(xpath("//li[@class='gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__secondary-menuitem-container']/a[@class='nw-o-link'][@href='/news/have_your_say']"));
        elementCoronaStory.click();

        WebElement elementSignIn = driver.findElement(xpath("//a[@id='idcta-link']"));
        elementSignIn.click();

        driver.findElement(xpath("//input[@type='email']")).sendKeys("ssantonenko");
        driver.findElement(xpath("//input[@type='password']")).sendKeys("password");

        WebElement elementSignInButton = driver.findElement(xpath("//button[@class='button button--full-width']"));
        elementSignInButton.click();

        WebElement webElementAns = driver.findElement(xpath("//p[@class='form-message__text']/span/span"));
        assertTrue(webElementAns.getText().contains("Sorry"));
    }



    @AfterMethod
    public void tearDown() {
        driver.close();// driver close
    }


}
