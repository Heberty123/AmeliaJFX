package br.com.domain.Loja.Controller.Cliente;

import br.com.domain.Loja.Controller.HomeController;
import br.com.domain.Loja.Models.Cliente;
import br.com.domain.Loja.Services.ChangeView;
import br.com.domain.Loja.Services.ClienteService;
import br.com.domain.Loja.Util.Contraints.Cliente.PesquisaRealConstraints;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class BuscarClienteController implements Initializable {


    /* Services */
    @Autowired
    private HomeController homeController;

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PesquisaRealConstraints pequisaService;

    @Autowired
    private ChangeView changeView;

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

                            Cliente cliente = row.getItem();

                            try {
                                changeView.change("/gui/Cliente/Cliente.fxml", (ClienteController controller) -> {
                                    controller.setCliente(cliente);
                                    try {
                                        controller.updateView();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
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
