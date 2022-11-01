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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

        tableColumnNome.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produto, TextFlow>, ObservableValue<TextFlow>>() {
            @Override
            public ObservableValue<TextFlow> call(TableColumn.CellDataFeatures<Produto, TextFlow> param) {

                TextFlow txtFlow = new TextFlow();
                txtFlow.setPrefHeight(txtFlow.prefHeight(tableColumnNome.getWidth()) + 4);
                Font f = Font.font("Verdana", FontWeight.BOLD, 12);


                /* ---------------------------------------------------------------------- */

                StringBuilder sb = new StringBuilder();
                char[] arrays = param.getValue().getNome().toCharArray();
                boolean right = false;

                for(int i = 0 ; i<=param.getValue().getNome().length() -1 ; i++){

                    int j = i;
                    for (char ch : txtBuscar.getText().toCharArray()) {

                        if(!(j > (arrays.length -1))) {

                            sb.append(arrays[j]);

                            if (sb.toString().toUpperCase().equals(txtBuscar.getText().toUpperCase() )) {
                                right = true;
                                break;
                            }

                            j++;
                        }
                        else {
                            break;
                        }
                    }

                    if(right == true){
                        Text txt1 = new Text(sb.toString());
                        txt1.setFill(Color.RED);
                        txt1.setFont(f);
                        txtFlow.getChildren().add(txt1);
                        i += (sb.length() -1);
                        sb.delete(0, sb.length());
                        right = false;
                    }
                    else {
                        Text txt2 = new Text(String.valueOf(arrays[i]));
                        txt2.setFont(f);
                        txtFlow.getChildren().add(txt2);
                        sb.delete(0, sb.length());
                    }
                }

                return new SimpleObjectProperty<TextFlow>(txtFlow);
            }
        });

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pesquisaService.pesquisaByNome(txtBuscar, tableView);
        initializeNodes();
        clickRowAtTable();
        setColorsAtContaingText();
        tableColumnNome.setStyle("-fx-font: 24 arial;");
    }



    private void initializeNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }

}
