package org.fabrica.basico;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class ejemplo2 {
    static WebDriver driver; // se queda fuera del try para que cuando sea llamado en cualquier parte del codigo no de error
    //el chrome driver para no llamarlo como en el ejemplo1, se almacenará en una variable y quedara en el setProperty
    static String chromePath = System.getProperty("user.dir")+"\\Driver\\chromedriver.exe";

    public static void main (String[] args){
        //Con esto se entiende que debe abrir el navegador y donde va a ejecutar chrome driver
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();//abrir navegador
        String baseURL = "https://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt";
        driver.get(baseURL);//para poder navegar en la pagina
        driver.manage().window().maximize();//Para maximizar la pantalla

        //tiempo de espera entre cada linea de ejecución
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebDriverWait waitVar = new WebDriverWait(driver, 10);



        try {
                //Para seleccionar cualquier elemento dentro del inframe es necesario estar dentro de el, para eso se genera la siguiente linea
                driver.switchTo().frame("iframeResult");

                WebElement btnTry = driver.findElement(By.xpath("/html/body/button"));
                waitVar.until(ExpectedConditions.elementToBeClickable(btnTry));
                btnTry.click();
                Thread.sleep(2000);

                //Para verificar que la alerta se muestre
                waitVar.until(ExpectedConditions.alertIsPresent());
                //Para manipuar las aleras usamos la clase Alert, para lo que se crea un objeto de tipo Alert
                Alert alrWindow = driver.switchTo().alert();
                String alertText = alrWindow.getText();
                System.out.println(alertText);
                //escribimos nuestro nombre dentro del pront de la alerta que se mostrara
                alrWindow.sendKeys("Jennifer");
                alrWindow.accept();
                //Verifico el texto que nos da el resultado
                String finalText = driver.findElement(By.id("demo")).getText();
                System.out.println(finalText.contains("Jennifer")?finalText: "Prueba Fallida");


            }catch (NoSuchElementException ne){
                //El error que se espera controlar es que no existan elemento web en la pagina
                System.err.println("No se encontró el WebElement: " + ne.getMessage());
            }catch (NoSuchFrameException nf){
                //Si no se encuentraalgun iframe
                System.err.println("No se encontró el frame: " + nf.getMessage());
            }catch (NoAlertPresentException na){
                //Si no se encuentra la alerta
                System.err.println("No se encontró la alerta: " + na.getMessage());
            }catch (TimeoutException te){
                //No e encontro una linea de codigo en el tiempo especificado
                System.err.println("tiempo de espera exedido: " + te.getMessage());
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
