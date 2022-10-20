package br.com.domain.Loja.Controller.Produto;

import br.com.domain.Loja.Models.Marca;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

@Controller
public class CadastroProdutoController {

    /* FXML */

    @FXML
    private Label labelId;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtReferencia;
    @FXML
    private TextField txtBarcode;
    @FXML
    private ChoiceBox choiceBoxMarca;
    @FXML
    private TextField txtPreco;

    /* Methods */

    public void buttonSaveAction(){
        System.out.println("Teste apenas");
    }
}
