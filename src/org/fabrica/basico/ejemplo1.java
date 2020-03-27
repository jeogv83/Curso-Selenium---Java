package org.fabrica.basico;//es donde se encuentr ala clase localizada, es este caso es el empaquetado

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ejemplo1 {//SE crea la clase publica para que sea visible entre otros archivos
    //con este metodo se indica que se puede ejecutar cualquier codigo
    public static void main (String[] args){
        //instanciar un objeto del tipo web driver
        WebDriver driver;
        //variables
        String baseURL = "http://www.quilatours.com/"; //se debe colocar el http o https si aplica
        String actualResult = "";
        String esperadoResult = "Quilatours";
        //Indicar donde se encuentra el archivo chromeDriver para que Selenium sepa que se van a ejecutar sobre navegador Chrome
        //Para lograrlo uso el metodo System.getProperty(user.dir) asi automaticamente sabra donde esta localizado el proyecto
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Driver\\chromedriver.exe");
        //Abrir navegador chrome
        driver = new ChromeDriver();
        //Navegar la pagina que se va automatizar
        driver.get(baseURL);
        //Se debe colocar el titulo ( etiqueta title) de la pagina web (extraerlo por herramienta del desarrollador) en la variable
        //esperadoResult
        actualResult = driver.getTitle();
        //Imprimir el resultado y compararlo con un operador ternario
        System.out.println(actualResult.contentEquals(esperadoResult)?"prueba pasada " + actualResult : "Prueba fallo");
        //Cerrar el navegador
        driver.close();


    }
}
