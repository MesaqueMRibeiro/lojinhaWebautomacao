package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutosTest {
    private WebDriver navegador;

    @BeforeEach
    public void beforeEach(){
        // Abrir o navegador
        System.setProperty("webdriver.chrome.driver","C:\\drivers\\chromedriver-win64\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        // Maximizar a tela
        this.navegador.manage().window().maximize();

        // Definir um tempo de espera padrão de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navegar para a pagina da Lojinha Web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @Test
    @DisplayName("Nao e permitido registar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorZerado() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterUsuarioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarOValorDoProduto("000")
                .informarAsCoresDoProduto("Preto,Branco")
                .submeterFormularioDeAdicaoComErro()
                .capiturarMesagemApresetada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Nao e permitido registar um produto com valor acima de 7.000,00")
    public void testNaoEPremitidoRegistrarProdutoComValorAcimaDeSeteMil(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterUsuarioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarOValorDoProduto("700001")
                .informarAsCoresDoProduto("Preto,Branco")
                .submeterFormularioDeAdicaoComErro()
                .capiturarMesagemApresetada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

    }


    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de R$ 0,01")
    public void testPossoAdicionarProdutosComValorDeUmCentavo(){
       String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterUsuarioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarOValorDoProduto("001")
                .informarAsCoresDoProduto("Preto, Branco")
                .submeterFormularioDeAdicaoComSucesso()
                .capiturarMesagemApresetada();

       Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);


    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de R$ 7000,00")
    public void testPossoAdicionarProdutoComValorDeSeteMil(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterUsuarioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Play Station 5")
                .informarOValorDoProduto("700000")
                .informarAsCoresDoProduto("Preto, Branco")
                .submeterFormularioDeAdicaoComSucesso()
                .capiturarMesagemApresetada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

        // Fechar o navegador
        @AfterEach
        public void afterEach(){
           navegador.quit();
    }
}
