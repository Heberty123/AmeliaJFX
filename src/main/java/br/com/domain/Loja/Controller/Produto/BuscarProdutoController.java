package br.com.domain.Loja.Controller.Produto;

import br.com.domain.Loja.Controller.Cliente.ClienteController;
import br.com.domain.Loja.Costumize.ProdutoCellFactory;
import br.com.domain.Loja.Models.Cliente;
import br.com.domain.Loja.Models.Produto;
import br.com.domain.Loja.Services.ChangeView;
import br.com.domain.Loja.Util.Contraints.Produto.PesquisaRealProduto;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Builder;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class BuscarProdutoController implements Initializable {

    /* Services */

    @Autowired
    private PesquisaRealProduto pesquisaService;

    @Autowired
    private ProdutoCellFactory produtoCellFactory;

    @Autowired
    private ChangeView changeView;

    /* FXML */

    @FXML
    private TextField txtBuscar;

    @FXML
    private TableView<Produto> tableView;

    /* Table columns */

    @FXML
    private TableColumn<Produto, Long> tableColumnId;
    @FXML
    private TableColumn<Produto, TextFlow> tableColumnNome;

    /* Methods */

    private void clickRowAtTable() {
        tableView.setRowFactory(new Callback<TableView<Produto>, TableRow<Produto>>(){
            @Override
            public TableRow<Produto> call(TableView<Produto> param) {
                TableRow<Produto> row = new TableRow<Produto>();
                row.setOnMouseClicked(new EventHandler<MouseEvent>(){

                    @Override
                    public void handle(MouseEvent event) {
                        if(event.getClickCount() == 2 && (! row.isEmpty())){

                            Produto produto = row.getItem();

                            try {
                                changeView.change("/gui/Produto/Produto.fxml", (ProdutoController controller) -> {
                                    controller.setProduto(produto);
                                    controller.updateView();
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


    private void setColorsAtContaingText(){

        tableColumnNome.setCellValueFactory(e -> {

            TextFlow txtFlow = new TextFlow();


            for (char ch : e.getValue().getNome().toCharArray()) {

                if(ch == txtBuscar.getText().toCharArray()[0]){
                    System.out.println("chamei aqui color");
                    Text txt = new Text(String.valueOf(ch));
                    txt.setFill(Color.CYAN);
                    txtFlow.getChildren().add(txt);
                }
                else {
                    System.out.println("nãooooo chamei aqui color");
                    Text txt = new Text(String.valueOf(ch));
                    txtFlow.getChildren().add(txt);
                }
            }

            return new SimpleObjectProperty<TextFlow>(txtFlow);
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pesquisaService.pesquisaByNome(txtBuscar, tableView);
        initializeNodes();
        clickRowAtTable();
        setColorsAtContaingText();
    }



    private void initializeNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }
}
