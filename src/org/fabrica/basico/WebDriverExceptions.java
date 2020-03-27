package org.fabrica.basico;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverExceptions {
    static WebDriver driver; // se queda fuera del try para que cuando sea llamado en cualquier parte del codigo no de error
    //se el coloca de tipo static ya que el metodo mai en de ese tipo, de lo contrario no es necesario
    public static void main (String[] args){
        //indicar que ese codigo se va a ejecutar con normalidad
        try {
            String baseURL = "http://live.demoguru99.com/index.php/checkout/cart/";
            String actualResult = "";
            String esperadoResult = "$615.00";
            //el chrome driver para no llamarlo como en el ejemplo1, se almacenará en una variable y quedara en el setProperty
            String chromePath = System.getProperty("user.dir")+"\\Driver\\chromedriver.exe";

            //Con esto se entiende que debe abrir el navegador y donde va a ejecutar chrome driver
            System.setProperty("webdriver.chrome.driver", chromePath);
            driver = new ChromeDriver();//abrir navegador
            driver.get(baseURL);//para poder navegar en la pagina
            driver.manage().window().maximize();//Para maximizar la pantalla
            //Para hacer clic en el link TV
            WebElement lnkTV =  driver.findElement(By.linkText("TV"));
            lnkTV.click();

            //clic en botón add to card,
            WebElement btnAddToCard = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[1]/div/div[3]/button/span/span"));
            btnAddToCard.click();

            //Obtener precio del objeto (para esto se obtiene el texto con getText),
            //con ctrl+f verifico en la inspección que se encontó el mismo
            //aqui cambio "#shopping-cart-table" por "#sho1" para generar un error de expecion y no consiga el elemento
            WebElement lblSubtotal = driver.findElement(By.cssSelector("#sho1>tbody>tr>td.product-cart-total>span>span"));
            actualResult = lblSubtotal.getText();

            //comparo los resultados
            if (actualResult.contentEquals(esperadoResult)){
                System.out.println("Prueba pasada, el resultado actual es: " + actualResult + " es igual a: " + esperadoResult);
            }else{
                System.out.println("Prueba fallo, el resultado actual es: " + actualResult + " no es igual a: " + esperadoResult);
            }
        }catch (NoSuchElementException ne){ // aca se comienzan a crear las exepciones, las que tengan que ver con el codigo creado
            //El error que se espera controlar es que no existan elemento web en la pagina
            //Con el printLn saldra en color rojo el mensaje de error
            System.err.println("No se encontró el WebElement: " + ne.getMessage());
        }catch (WebDriverException we){ //el navegador de se detuvo, o el webdriver fallo al iniciar entre otras
            System.err.println("WebDriver fallo: " + we.getMessage());
        }catch (Exception ex){ // creo este bloque de exepcion para que me diga si no esta pasando nada con WebDriver diga que esta ocurriendo,
                    // cual es el error, tiempo de ejecución o sintaxis...
            System.err.println(ex.getMessage());
        }
        finally { // No importa si se ejecuta bien o no un bloque de codigo el script va a entrar forzado en finally
            //Cierro el navegador:
            driver.close();
        }

    }
}
