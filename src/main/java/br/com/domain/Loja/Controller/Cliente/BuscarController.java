package br.com.domain.Loja.Controller.Cliente;

import br.com.domain.Loja.Models.Cliente;
import br.com.domain.Loja.Models.Endereco;
import br.com.domain.Loja.Services.ClienteService;
import br.com.domain.Loja.Util.Contraints.Cliente.PesquisaRealConstraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
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
    private TableView<Cliente> tableView;

    /* Table */

    @FXML
    private TableColumn<Cliente, Long> tableColumnId;
    @FXML
    private TableColumn<Cliente, String> tableColumnNome;

    /* Methods */


    private void clickRowAtTable(){
        tableView.setRowFactory(new Callback<TableView<Cliente>, TableRow<Cliente>>(){

            @Override
            public TableRow<Cliente> call(TableView<Cliente> param) {
                TableRow<Cliente> row = new TableRow<Cliente>();
                row.setOnMouseClicked(new EventHandler<MouseEvent>(){

                    @Override
                    public void handle(MouseEvent event) {
                        if(event.getClickCount() == 2 && (! row.isEmpty())){
                            System.out.println(row.getIndex());
                            Cliente cliente = row.getItem();
                            System.out.println(cliente.getNome());
                        }
                    }
                });
                return row;
            }
        });
    }


    private void initializeNodes(){
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeNodes();
        pequisaService.pesquisaByNome(txtPesquisaCliente, tableView);
        clickRowAtTable();

    }
}
