package br.com.domain.Loja.Controller.Cliente;

import br.com.domain.Loja.Components.TableRowEndereco;
import br.com.domain.Loja.Models.Cliente;
import br.com.domain.Loja.Models.Endereco;
import br.com.domain.Loja.Services.ClienteService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class ClienteController {

    /* Atributes */

    private Cliente cliente;


    /* Services */

    @Autowired
    private ClienteService clienteService;

    /* FXML */
    @FXML
    private Label labelName;

    @FXML
    private Button button;

    @FXML
    private ListView<AnchorPane> listViewEnderecos;

    /* Components */

    @Autowired
    private TableRowEndereco tableRowEndereco;


    /* Methods */

    public void buttonAction() throws IOException {

        tableRowEndereco.getFormattedRowEndereco(new Endereco(), listViewEnderecos);

    }

    public void updateView(){
        labelName.setText(cliente.getNome());
    }


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
