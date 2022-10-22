package br.com.domain.Loja.Controller.Produto;

import br.com.domain.Loja.Models.Marca;
import br.com.domain.Loja.Models.Produto;
import br.com.domain.Loja.Services.ProdutoService;
import br.com.domain.Loja.StageInitializer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class CadastroProdutoController implements Initializable {

    /* Services */

    @Autowired
    private ProdutoService produtoService;

    /* FXML */

    @FXML
    private Label labelInformation;
    @FXML
    private Label labelId;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtReferencia;
    @FXML
    private TextField txtBarcode;
    @FXML
    private ComboBox<Marca> comboBoxMarca;
    @FXML
    private TextField txtPreco;

    /* Methods */

    public void buttonSaveAction(){
        System.out.println("Teste");
        Produto produto = new Produto();
        produto.setNome(txtNome.getText());
        produto.setPreco(BigDecimal.valueOf(Long.valueOf(txtPreco.getText())));
        produto.setReferencia(txtReferencia.getText());
        produto.setMarca(comboBoxMarca.getValue());
        produtoService.save(produto);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        attComboBoxMarca();

    }

    private void attComboBoxMarca(){
        ObservableList<Marca> strings = FXCollections.observableArrayList(produtoService.getAllMarcas());
        comboBoxMarca.setItems(strings);
    }
}
