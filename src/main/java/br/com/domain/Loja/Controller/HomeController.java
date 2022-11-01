package br.com.domain.Loja.Controller;

import br.com.domain.Loja.Services.ChangeView;
import br.com.domain.Loja.StageInitializer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class HomeController {
    @FXML
    private MenuItem itemCadastroCliente;
    @FXML
    private MenuItem itemBuscarCliente;

    @FXML
    private MenuItem itemCadastroProduto;

    @FXML
    private MenuItem ItemBuscarCliente;

    /* Services */

    @Autowired
    private ChangeView changeView;


    public void itemBuscarClienteAction() throws IOException {
        changeView.change("/gui/Cliente/Buscar.fxml", x -> {});
    }

    public void itemCadastroProdutoAction() throws IOException {
        changeView.change("/gui/Produto/Cadastro.fxml", x -> {});
    }

    public void itemBuscarProdutoAction() throws IOException {
        changeView.change("/gui/Produto/Buscar.fxml", x -> {});
    }


}
