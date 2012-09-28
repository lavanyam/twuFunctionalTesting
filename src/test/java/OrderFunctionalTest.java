/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lavanya
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class OrderFunctionalTest {

    static WebDriver driver;
    static WebElement name;
    static WebElement email;
    static WebElement item;
    static WebElement submitButton;
    static String input;
    static String alphabets[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    static String specialChars[] = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")"};
    static String sites[] = {"thoughtworks", "google", "yahoo"};
    static String domainNames[] = {".com", ".org", ".net"};
    static String countryNames[] = {"in", "sg", "au"};

    public static void main(String[] args) {
        createWebDriverInstance();
        setupPage();                     //Get web page 
        testFormFields();                // Find input elements
    }

    private static void createWebDriverInstance() {
        driver = new HtmlUnitDriver();  // Create a new instance of the html unit driver
    }

    private static void setupPage() {
        getWebPageToTest();
        findInputElements();
    }

    private static void getWebPageToTest() {
        driver.get("http://localhost:8080/twuFunctionalTesting/order/new");// Use this to visit the page we want to test
    }

    private static void findInputElements() {// Find the text input element by its name
        name = driver.findElement(By.name("name"));
        email = driver.findElement(By.name("email"));
        item = driver.findElement(By.name("item"));
        submitButton = driver.findElement(By.name("submitButton"));
    }

    private static void testFormFields() {
        testNameField();
        testEmailField();
    }

    private static void testPasses(String s) {
        System.out.println(s + "\t Test Case Status= PASSES");
    }

    private static void testFails(String s) {
        System.out.println(s + "\t Test Case Status= FAILS");
    }

    private static String randomAlpha() {
        String inputTemp = "";
        for (int i = 0; i < 5; i++) {
            int value = (int) (Math.random() * alphabets.length);
            inputTemp = inputTemp + alphabets[value];
        }
        return inputTemp;
    }

    private static int randomNumeric() {
        int inputTemp = 0;
        for (int i = 0; i < 5; i++) {
            int value = (int) (Math.random() * 10);
            inputTemp = inputTemp + value;
        }
        return inputTemp;
    }

    private static String randomSpecialChars() {
        String inputTemp = "";
        for (int i = 0; i < 4; i++) {
            int value = (int) (Math.random() * specialChars.length);
            inputTemp = inputTemp + specialChars[value];
        }
        return inputTemp;
    }

    private static String randomSites() {
        String inputTemp = "";
        int value = (int) (Math.random() * sites.length);
        inputTemp = inputTemp + sites[value];
        return inputTemp;
    }

    private static String randomDomain() {
        String inputTemp = "";
        int value = (int) (Math.random() * domainNames.length);
        inputTemp = inputTemp + domainNames[value];
        return inputTemp;
    }

    private static String randomCountry() {
        String inputTemp = "";
        int value = (int) (Math.random() * countryNames.length);
        inputTemp = inputTemp + countryNames[value];
        return inputTemp;
    }
    //Keeping email id and item constant and correct, test different input values for Name field

    private static void testNameField() {
        System.out.println("Test for NAME field:");
        alphaName();
        setupPage();

        numericName();
        setupPage();

        blankName();
        setupPage();

        nullName();
        setupPage();

        alphaNumbericName();
        setupPage();

        specialCharactersName();
        setupPage();
        
        spacesInNames();
        setupPage();
    }

    private static void alphaName() {
        input = randomAlpha();
        nameSubmit();      
        if ((driver.getTitle()).equalsIgnoreCase("Saved Order")) {
            testPasses("Input= \"" + input + "\" (Alpha only)\t\t Expected output=  Valid");
        } else {
            testFails("Input= \"" + input + "\" (Alpha only)\t\t Expected output=  Valid");
        }
    }

    private static void numericName() {
        input = "" + randomNumeric();
        if ((driver.getTitle()).equalsIgnoreCase("Saved Order")) {
            testFails("Input= \"" + input + "\" (Numeric only)\t\t Expected output= Invalid ");
        } else {
            testPasses("Input= \"" + input + "\" (Numeric only)\t\t Expected output= Invalid ");
        }
    }

    private static void blankName() {
        input = "";
        if ((driver.getTitle()).equalsIgnoreCase("Saved Order")) {
            testFails("Input= \"" + input + "\" (Blank)\t\t\t Expected output= Invalid");
        } else {
            testPasses("Input= \"" + input + "\" (Blank)\t\t\t Expected output= Invalid");
        }
    }

    private static void nullName() {
        input = null;
        if ((driver.getTitle()).equalsIgnoreCase("Saved Order")) {
            testFails("Input= \"" + input + "\" (Null)\t\t\t Expected output= Invalid");
        } else {
            testPasses("Input= \"" + input + "\" (Null)\t\t\t Expected output= Invalid");
        }
    }

    private static void alphaNumbericName() {
        input = randomAlpha() + randomNumeric();
        nameSubmit();
        if ((driver.getTitle()).equalsIgnoreCase("Saved Order")) {
            testFails("Input= \"" + input + "\" (AlphaNumberic)\t Expected output= Invalid");
        } else {
            testPasses("Input= \"" + input + "\" (AlphaNumberic)\t Expected output= Invalid");
        }
    }

    private static void specialCharactersName() {
        input = randomAlpha() + randomSpecialChars();
        nameSubmit();
        if ((driver.getTitle()).equalsIgnoreCase("Saved Order")) {
            testFails("Input= \"" + input + "\" (Special Characters)\t Expected output= Invalid");
        } else {
            testPasses("Input= \"" + input + "\" (Special Characters)\t Expected output= Invalid");
        }
    }
    
    private static void spacesInNames() {
        input = randomAlpha()+"  "+randomAlpha();
        nameSubmit();
        if ((driver.getTitle()).equalsIgnoreCase("Saved Order")) {
            testPasses("Input= \"" + input + "\" (With Spaces)\t Expected output= Valid\t");
        } else {
            testFails("Input= \"" + input + "\" (With Spaces)\t Expected output= Valid\t");
        }
    }

    private static void nameSubmit() {
        name.clear();
        name.sendKeys(input);
        email.sendKeys("lavanya@gmail.com");
        submitButton.submit();
    }
//---------------------------------------------------------------

    private static void testEmailField() {
        System.out.println("\nTest for EMAIL field:");
        alphaOnlyEmail();
        setupPage();

        alphaNumericEmail();
        setupPage();

        underscoreEmail();
        setupPage();

        nonAplhaStartEmail();
        setupPage();

        specialCharactersEmail();
        setupPage();

        blankEmail();
        setupPage();

        domainCorrectEmail();
        setupPage();

        noAtSymbolEmail();
        setupPage();

        extendedDomain();
        setupPage();
    }

    private static void alphaOnlyEmail() {
        input = randomAlpha();
        input = input + "@gmail.com";
        emailSubmit();
        if ((driver.getTitle()).equalsIgnoreCase("Saved Order")) {
            testPasses("Input= \"" + input + "\" (Alpha only)\t\t\t\t Expected output= Valid\t");
        } else {
            testFails("Input= \"" + input + "\" (Alpha only)\t\t\t\t Expected output= Valid\t");
        }
    }

    private static void alphaNumericEmail() {
        input = randomAlpha() + randomNumeric() + "@gmail.com";
        emailSubmit();
        if ((driver.getTitle()).equalsIgnoreCase("Saved Order")) {
            testPasses("Input= \"" + input + "\" (Alphanumeric)\t\t\t Expected output= Valid\t");
        } else {
            testFails("Input= \"" + input + "\" (Alphanumeric)\t\t\tExpected output= Valid\t");
        }
    }

    private static void underscoreEmail() {
        input = randomAlpha() + "_" + randomNumeric() + "_" + "@gmail.com";
        emailSubmit();
        if ((driver.getTitle()).equalsIgnoreCase("Saved Order")) {
            testPasses("Input= \"" + input + "\" (With underscore)\t\t\t Expected output= Valid\t");
        } else {
            testFails("Input= \"" + input + "\" (With underscore)\t\t\t Expected output= Valid\t");
        }
    }

    private static void nonAplhaStartEmail() {
        input = randomNumeric() + randomAlpha() + "@gmail.com";
        emailSubmit();
        if ((driver.getTitle()).equalsIgnoreCase("Saved Order")) {
            testFails("Input= \"" + input + "\" (Non-alpha starting character)\t Expected output= Invalid");
        } else {
            testPasses("Input= \"" + input + "\" (Non-alpha starting character)\t Expected output= Invalid");
        }
    }

    private static void specialCharactersEmail() {
        input = randomAlpha() + randomSpecialChars() + "@gmail.com";
        emailSubmit();
        if ((driver.getTitle()).equalsIgnoreCase("Saved Order")) {
            testFails("Input= \"" + input + "\" (Special characters character)\t Expected output= Invalid");
        } else {
            testPasses("Input= \"" + input + "\" (Special characters character)\t Expected output= Invalid");
        }
    }

    private static void blankEmail() {
        input = "";
        emailSubmit();
        if ((driver.getTitle()).equalsIgnoreCase("Saved Order")) {
            testFails("Input= \"" + input + "\" (Blank)\t\t\t\t\t\t Expected output= Invalid");
        } else {
            testPasses("Input= \"" + input + "\" (Blank)\t\t\t\t\t\t Expected output= Invalid");
        }
    }

    private static void domainCorrectEmail() {
        input = randomAlpha() + "@" + randomSites() + randomDomain();
        emailSubmit();
        if ((driver.getTitle()).equalsIgnoreCase("Saved Order")) {
            testPasses("Input= \"" + input + "\" (Correct domain)\t\t\t Expected output= Valid\t");
        } else {
            testFails("Input= \"" + input + "\" (Correct Domain)\t\t\t Expected output= Valid\t");
        }
    }

    private static void noAtSymbolEmail() {
        input = randomAlpha() + randomSites() + randomDomain();
        emailSubmit();
        if ((driver.getTitle()).equalsIgnoreCase("Saved Order")) {
            testFails("Input= \"" + input + "\" (No '@' symbol)\t\t\t Expected output= Valid\t");
        } else {
            testPasses("Input= \"" + input + "\" (No '@' symbol)\t\t\t Expected output= Valid\t");
        }
    }

    private static void extendedDomain() {
        input = randomAlpha() + "@" + randomSites() + "1idjefjb";
        emailSubmit();
        if ((driver.getTitle()).equalsIgnoreCase("Saved Order")) {
            testPasses("Input= \"" + input + "\" (Permitted email)\t\t Expected output= Valid\t");
        } else {
            testFails("Input= \"" + input + "\" (Permitted email)\t\t Expected output= Valid\t");
        }
    }

    private static void emailSubmit() {
        name.clear();
        name.sendKeys("lavanya");
        email.clear();
        email.sendKeys(input);
        submitButton.submit();
    }

    
}
