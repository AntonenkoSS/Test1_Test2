import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class test_for_HT_1 {

    private WebDriver driver;
    private String LINK_TO_NEWS = "//div[@id='orb-nav-links']//li[@class='orb-nav-newsdotcom']/a[@href='https://www.bbc.com/news']";
    private String LINK_TO_NEWS_FROM_FIRST_ARTICLE = "//li[@class='ssrcss-so3uhw-GlobalNavigationProduct eki2hvo15']/a[@href='https://www.bbc.com/news']";
    private String FIRST_ARTICLE_XPATH_A = "//a[@class='gs-c-promo-heading gs-o-faux-block-link__overlay-link gel-paragon-bold nw-o-link-split__anchor']";
    private String FIRST_ARTICLE_XPATH = "//div[@class='gs-c-promo-body gs-u-display-none gs-u-display-inline-block@m gs-u-mt@xs gs-u-mt0@m gel-1/3@m']//a[@class='gs-c-promo-heading gs-o-faux-block-link__overlay-link gel-paragon-bold nw-o-link-split__anchor']/h3[@class='gs-c-promo-heading__title gel-paragon-bold nw-o-link-split__text']";
    private String FIRST_ARTICLE_NAME = "Prince Andrew denies he was close to Maxwell";
    private String SECONDARY_ARTICLES = "//a[@class='gs-c-promo-heading gs-o-faux-block-link__overlay-link gel-pica-bold nw-o-link-split__anchor']/h3";
    private String SEARCH_AFTER = "//input[@placeholder='Search']";
    private String EXPECTED_NAME_ARTICLE_WITH_KEYWORD = "//a[@class='ssrcss-atl1po-PromoLink e1f5wbog0']/span/p";
    private String EXPECTED_FOOTER = "//div[@class='ws-c-social-slice__secondary-links gel-layout gel-layout__item']";
    private String EXPECTED_FOOTER_SIGNIN = "//li[@class='footer__item']";
    private String BUTTON_X = "//button[@aria-label='Close']";
    private String CORONA = "//li[contains(@class, 'gs-u-float-left nw-c-nav__wide-menuitem-container')]//a[@href='/news/coronavirus']";
    private String CORONA_STORY = "//li[@class='gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__secondary-menuitem-container']/a[@class='nw-o-link'][@href='/news/have_your_say']";
    private String SIGN_IN = "//a[@id='idcta-link']";
    private String EMAIL_FIELD = "//input[@type='email']";
    private String PASS_FIELD = "//input[@type='password']";
    private String CORRECT_EMAIL = "ssantonenko@gmail.com";
    private String INCORRECT_EMAIL = "ssantonenko";
    private String CORRECT_PASSWORD = "password";
    private String MODAL = "//*[@class='tp-modal-open']";
    private String SIGN_BUTTON = "//button[@class='button button--full-width']";
    private String ANSWER = "//p[@class='form-message__text']/span/span";
    private String EXPECTED_ANSWER = "Sorry, that password isn't valid";
    private String EXPECTED_ANSWER_2 = "Sorry";
    
    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.bbc.com");         //open  BBC1
    }

    @Test(priority = 1)
    public void checkTestPart1BBC1() throws InterruptedByTimeoutException {
        WebElement element = driver.findElement(xpath(LINK_TO_NEWS));
        element.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FIRST_ARTICLE_XPATH)));
        WebElement elementNews = driver.findElement(xpath(FIRST_ARTICLE_XPATH));
        String elementText = elementNews.getText();
        assertEquals(FIRST_ARTICLE_NAME, elementText);
    }

    @Test(priority = 2)
    public void checkTest2Part1BBC1(){
        WebElement element = driver.findElement(xpath(LINK_TO_NEWS));
        element.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath(FIRST_ARTICLE_XPATH)));
        List<WebElement> elementsArticle = driver.findElements(By.xpath(SECONDARY_ARTICLES));
        List<String> titlesArticlesList = new ArrayList<>();
        titlesArticlesList.add("US rejects Russian demand to bar Ukraine from Nato");
        titlesArticlesList.add("Biden to nominate black woman to top US court");
        titlesArticlesList.add("UK PM vows to fight amid parties row");
        List<String> textsElements = elementsArticle.stream().map(WebElement::getText).collect(Collectors.toList());
        assertTrue(textsElements.containsAll(titlesArticlesList));
    }


    @Test(priority = 3)
    public void checkTest3Part1BBC1() {
        WebElement element = driver.findElement(xpath(LINK_TO_NEWS));
        element.click();
        WebDriverWait wait = new WebDriverWait(driver, 80);
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath(EXPECTED_FOOTER)));
        WebElement elementFirstArticle = driver.findElement(xpath(FIRST_ARTICLE_XPATH_A));
        elementFirstArticle.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath(MODAL)));
        WebElement elementX = driver.findElement(xpath(BUTTON_X));
        elementX.click();
        String url = driver.getCurrentUrl();
            //https://www.bbc.com/news/world-us-canada-60149024
            int index1 = url.indexOf("/", 22);
            int index2 = url.indexOf("-", 22);
            String keyword = url.substring(index1+1,index2);
        WebElement elementNewsBack = driver.findElement(xpath(LINK_TO_NEWS_FROM_FIRST_ARTICLE));
        elementNewsBack.click();        //back to news
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath(EXPECTED_FOOTER)));
        driver.findElement(xpath(SEARCH_AFTER)).sendKeys(keyword, Keys.ENTER);   //sendKeys(text, Keys.ENTER)
        //wait.until(ExpectedConditions.visibilityOfElementLocated(xpath(EXPECTED_FOOTER)));
        List<WebElement> elementNewsArticles = driver.findElements(xpath(EXPECTED_NAME_ARTICLE_WITH_KEYWORD));
            assertEquals("World", elementNewsArticles.get(0).getText());
        }

    @Test(priority = 4)
    public void checkTest1Part2BBC1() {
        WebElement element = driver.findElement(xpath(LINK_TO_NEWS));
        element.click();
        WebDriverWait wait = new WebDriverWait(driver, 80);
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath(EXPECTED_FOOTER)));
        WebElement elementCorona = driver.findElement(xpath(CORONA));
        elementCorona.click();
        WebElement elementCoronaStory = driver.findElement(xpath(CORONA_STORY));
        elementCoronaStory.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath(SIGN_IN)));
        WebElement elementSignIn = driver.findElement(xpath(SIGN_IN));
        elementSignIn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath(EXPECTED_FOOTER_SIGNIN)));
        driver.findElement(xpath(EMAIL_FIELD)).sendKeys(CORRECT_EMAIL);
        driver.findElement(xpath(PASS_FIELD)).sendKeys(CORRECT_PASSWORD);
        WebElement elementSignInButton = driver.findElement(xpath(SIGN_BUTTON));
        elementSignInButton.click();
        WebElement webElementAns = driver.findElement(xpath(ANSWER));
        assertTrue(webElementAns.getText().contains(EXPECTED_ANSWER));
    }

    @Test(priority = 5)
    public void checkTest2Part2BBC1() {
        WebElement element = driver.findElement(xpath(LINK_TO_NEWS));
        element.click();
        WebDriverWait wait = new WebDriverWait(driver, 80);
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath(EXPECTED_FOOTER)));
        WebElement elementCorona = driver.findElement(xpath(CORONA));
        elementCorona.click();
        WebElement elementCoronaStory = driver.findElement(xpath(CORONA_STORY));
        elementCoronaStory.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath(SIGN_IN)));
        WebElement elementSignIn = driver.findElement(xpath(SIGN_IN));
        elementSignIn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath(EXPECTED_FOOTER_SIGNIN)));
        driver.findElement(xpath(EMAIL_FIELD)).sendKeys(INCORRECT_EMAIL);
        driver.findElement(xpath(PASS_FIELD)).sendKeys(CORRECT_PASSWORD);
        WebElement elementSignInButton = driver.findElement(xpath(SIGN_BUTTON));
        elementSignInButton.click();
        WebElement webElementAns = driver.findElement(xpath(ANSWER));
        assertTrue(webElementAns.getText().contains(EXPECTED_ANSWER_2));
    }
    
    @AfterMethod
    public void tearDown() {
        driver.close();// driver close
    }
}
