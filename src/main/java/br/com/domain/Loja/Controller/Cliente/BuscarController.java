package br.com.domain.Loja.Controller.Cliente;

import br.com.domain.Loja.Models.Cliente;
import br.com.domain.Loja.Models.Endereco;
import br.com.domain.Loja.Services.ClienteService;
import br.com.domain.Loja.Util.Contraints.Cliente.PesquisaRealConstraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class BuscarController implements Initializable {

    /* Services */

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PesquisaRealConstraints pequisaService;

    /* FXML */

    @FXML
    private TextField txtPesquisaCliente;
    @FXML
    private TableView tableView;

    /* Table */

    @FXML
    private TableColumn<Cliente, Long> tableColumnId;
    @FXML
    private TableColumn<Cliente, String> tableColumnNome;

    /* Methods */



    private void initializeNodes(){
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeNodes();
        pequisaService.pesquisaByNome(txtPesquisaCliente, tableView);
    }
}
