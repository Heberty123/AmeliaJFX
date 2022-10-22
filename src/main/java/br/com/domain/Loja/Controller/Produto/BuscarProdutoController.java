package br.com.domain.Loja.Controller.Produto;

import br.com.domain.Loja.Costumize.ProdutoCellFactory;
import br.com.domain.Loja.Models.Produto;
import br.com.domain.Loja.Util.Contraints.Produto.PesquisaRealProduto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class BuscarProdutoController implements Initializable {

    /* Services */

    @Autowired
    private PesquisaRealProduto pesquisaService;

    @Autowired
    private ProdutoCellFactory produtoCellFactory;

    /* FXML */

    @FXML
    private TextField txtBuscar;

    @FXML
    private ListView<Produto> listProduto;

    /* Methods */

    private void clickRowAtList(){
        listProduto.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2){
                System.out.println(listProduto.getSelectionModel().getSelectedItem());
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pesquisaService.pesquisaByNome(txtBuscar, listProduto);
        clickRowAtList();
        listProduto.setCellFactory(produtoCellFactory);
    }
}
