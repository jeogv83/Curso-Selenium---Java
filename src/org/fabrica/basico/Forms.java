package org.fabrica.basico;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

//Para print de pantalla



public class Forms {
    static WebDriver driver; // se queda fuera del try para que cuando sea llamado en cualquier parte del codigo no de error

    public static void main (String[] args){
        //el chrome driver para no llamarlo como en el ejemplo1, se almacenará en una variable y quedara en el setProperty
        String chromePath = System.getProperty("user.dir")+"\\Driver\\chromedriver.exe";
        String baseURL = "http://www.newtours.demoaut.com/";
        System.setProperty("webdriver.chrome.driver", chromePath);//Con esto se entiende que debe abrir el navegador y donde va a ejecutar chrome driver
        driver = new ChromeDriver();//abrir navegador
        driver.get(baseURL);//para poder navegar en la pagina
        driver.manage().window().maximize();//Para maximizar la pantalla
        //indicar que ese codigo se va a ejecutar con normalidad
        try {

            driver.findElement(By.linkText("REGISTER")).click(); //hago clic en el link REGISTER
            WebElement txtFirstName = driver.findElement(By.name("firstName")); //creo mi objeto
            txtFirstName.sendKeys("Gil");//Le digo que escriba un texto dentro de texbox correpondiente al objeto creado en a linea anterior
            Thread.sleep(1500);
            txtFirstName.clear(); //luego que borre el texto creado
            Thread.sleep(1500);
            txtFirstName.sendKeys("Gilberto");
            driver.findElement(By.name("address1")).sendKeys("test Adress");

            //para el drop down se crea un objeto select
            Select drpCountry = new Select(driver.findElement(By.name("country")));
            Thread.sleep(1500);
            drpCountry.selectByVisibleText("MEXICO"); //se refiere al texto dentro de la lista
            driver.findElement(By.id("email")).sendKeys("gilberto@gmail.com");
            driver.findElement(By.name("password")).sendKeys("123");
            WebElement txtConfirmPassw = driver.findElement(By.name("confirmPassword"));
            txtConfirmPassw.sendKeys("123");
            txtConfirmPassw.submit(); // con eto no necesito indicar que presiones boton SUBMIT de la página
            System.out.println("Prueba exitosa: " + driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[3]/a/font/b")).getText());

           //Prueba captura de pantalla
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("C:\\Users\\Jennifer Vunic\\Documents\\SeleniumArch\\printSeleniumPrueba1\\screenshot.png"));


            }catch (NoSuchElementException ne){
                //El error que se espera controlar es que no existan elemento web en la pagina
                System.err.println("No se encontró el WebElement: " + ne.getMessage());
            }catch (WebDriverException we){ //el navegador de se detuvo, o el webdriver fallo al iniciar entre otras
                System.err.println("WebDriver fallo: " + we.getMessage());
            }catch (Exception ex){ // creo este bloque de exepcion para que me diga si no esta pasando nada con WebDriver diga que esta ocurriendo,
                        // cual es el error, tiempo de ejecución o sintaxis...
                System.err.println(ex.getMessage());
            }
            finally { // No importa si se ejecuta bien o no un bloque de codigo el script va a entrar forzado en finally
                //Cierro todas las ventanas
                driver.quit();
        }

    }
}
