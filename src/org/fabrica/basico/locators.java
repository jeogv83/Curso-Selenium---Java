package org.fabrica.basico;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class locators {
    public static void main (String[] args){
        WebDriver driver;
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
        driver.findElement(By.linkText("TV")).click();
        //
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[1]/div/div[3]/button/span/span")).click();
        //Obtener precio del objeto (para esto se obtiene el texto con getText),
        //con ctrl+f verifico en la inspección que se encontó el mismo
        actualResult = driver.findElement(By.cssSelector("#shopping-cart-table>tbody>tr>td.product-cart-total>span>span")).getText();
        //comparo los resultados
        if (actualResult.contentEquals(esperadoResult)){
            System.out.println("Prueba pasada, el resultado actual es: " + actualResult + " es igual a: " + esperadoResult);
        }else{
            System.out.println("Prueba fallo, el resultado actual es: " + actualResult + " no es igual a: " + esperadoResult);
        }

        //Cierro el navegador:
        driver.close();

    }
}
