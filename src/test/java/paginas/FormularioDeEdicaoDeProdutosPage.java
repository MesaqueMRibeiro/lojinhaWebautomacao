package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeEdicaoDeProdutosPage {
    private WebDriver navegador;

    public FormularioDeEdicaoDeProdutosPage(WebDriver navegador){
        this.navegador = navegador;
    }

    public String capiturarMesagemApresetada(){
        return navegador.findElement(By.cssSelector(".toast.rounded")).getText();

    }
}
