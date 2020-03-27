package org.fabrica.basico;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CloseQuit {
    static WebDriver driver; // instanciamos este objeto del tipo WebDriver
    static  String baseURL = "http://www.popuptest.com/popuptest2.html";
    //el chromedriver se almacenará en una variable y quedara en el setProperty
    static String chromePath = System.getProperty("user.dir")+"\\Driver\\chromedriver.exe";

    public static void close(){
        driver = new ChromeDriver(); // para abrir chrome
        driver.navigate().to(baseURL); // este comando obtiene (abre) la psgina especificada en el parentesis igual qu el get
        driver.close(); //cierra solo la ventana del navegador que Webdriver esta controlando en este momento
    }

    public static void quit() throws InterruptedException{ // cuando se usa Thread es necesario añadir la execpcion InterruptedException
        driver = new ChromeDriver();
        driver.get(baseURL); // obtiene (abre) la pagina specificada entre parentesis
        // funciona como un timer, no se recomieda usar con WebDriver,
        Thread.sleep( 2000); // solo escribo el num entre parentesis
        driver.quit(); //cierra todas las ventanas que WebDriver ha abierto
    }

    public static void main(String[] args) throws InterruptedException{
        //Con esto se entiende que debe abrir el navegador y donde va a ejecutar chrome driver
        System.setProperty("webdriver.chrome.driver", chromePath);
        //close(); // aqui llamo el metodo close que cree previamente
        quit(); // llamometodo quit ue cree para probarlo
    }
}
