package br.com.domain.Loja.Controller;

import br.com.domain.Loja.Services.ChangeView;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.io.IOException;

@Controller
public class HomeController {


    @FXML
    private MenuItem itemCadastroCliente;
    @FXML
    private MenuItem itemBuscarCliente;

    @FXML
    private MenuItem itemCadastroProduto;

    @FXML
    private MenuItem ItemBuscarCliente;

    @FXML
    private GridPane gridPane;


    /* Services */

    @Autowired
    private ChangeView changeView;


    public void itemBuscarClienteAction() throws IOException {
        changeView.change("/gui/Cliente/Buscar.fxml", x -> {});
    }

    public void itemBuscarProdutoAction() throws IOException {
        changeView.change("/gui/Produto/Buscar.fxml", x -> {});
    }


}
