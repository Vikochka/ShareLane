import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ShareLaneTest {
    String URL_MAIN = "https://www.sharelane.com ";
    String URL_REGISTER = "https://www.sharelane.com/cgi-bin/register.py";

    @Test
    public void correctDatsTest() {
        open(URL_MAIN);
        $(By.xpath("//*[text()='ENTER']"));
        open(URL_REGISTER);

        $(By.name("zip_code")).sendKeys("11111");
        $(By.cssSelector("[value=Continue]")).click();
        $(By.name("first_name")).sendKeys("Vasya");
        $(By.name("last_name")).sendKeys("Pupkin");
        $(By.name("email")).sendKeys("james_khan@527.93.sharelane.com");
        $(By.name("password1")).sendKeys("11111");
        $(By.name("password2")).sendKeys("11111");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".confirmation_message")).getText();
        Assert.assertEquals(result, "Account is created!");
    }

    @Test
    public void passwordless1Test() {
        open(URL_MAIN);
        $(By.xpath("//*[text()='ENTER']"));
        open(URL_REGISTER);

        $(By.name("zip_code")).sendKeys("11111");
        $(By.cssSelector("[value=Continue]")).click();
        $(By.name("first_name")).sendKeys("Vasya");
        $(By.name("last_name")).sendKeys("Pupkin");
        $(By.name("email")).sendKeys("james_khan@527.93.sharelane.com");
        $(By.name("password1")).sendKeys("");
        $(By.name("password2")).sendKeys("1111");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. Some of your fields have invalid data or email was " +
                "previously used");

    }

    @Test
    public void passwordless2Test() {
        open(URL_MAIN);
        $(By.xpath("//*[text()='ENTER']"));
        open(URL_REGISTER);

        $(By.name("zip_code")).sendKeys("11111");
        $(By.cssSelector("[value=Continue]")).click();
        $(By.name("first_name")).sendKeys("Vasya");
        $(By.name("last_name")).sendKeys("Pupkin");
        $(By.name("email")).sendKeys("james_khan@527.93.sharelane.com");
        $(By.name("password1")).sendKeys("11111");
        $(By.name("password2")).sendKeys(" ");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. Some of your fields have invalid data or email was " +
                "previously used");
    }

    @Test
    public void differentРasswordsTest() {
        open(URL_MAIN);
        $(By.xpath("//*[text()='ENTER']"));
        open(URL_REGISTER);

        $(By.name("zip_code")).sendKeys("11111");
        $(By.cssSelector("[value=Continue]")).click();
        $(By.name("first_name")).sendKeys("Vasya");
        $(By.name("last_name")).sendKeys("Pupkin");
        $(By.name("email")).sendKeys("james_khan@527.93.sharelane.com");
        $(By.name("password1")).sendKeys("11111");
        $(By.name("password2")).sendKeys("12345");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. Some of your fields have invalid data or email was " +
                "previously used");
    }

    @Test
    public void mailWithoutDomainTest() {
        open(URL_MAIN);
        $(By.xpath("//*[text()='ENTER']"));
        open(URL_REGISTER);
        $(By.name("zip_code")).sendKeys("11111");
        $(By.cssSelector("[value=Continue]")).click();
        $(By.name("first_name")).sendKeys("Vasya");
        $(By.name("last_name")).sendKeys("Pupkin");
        $(By.name("email")).sendKeys("james_khan@");
        $(By.name("password1")).sendKeys("11111");
        $(By.name("password2")).sendKeys("12345");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. Some of your fields have invalid data or email was " +
                "previously used");
    }
}

