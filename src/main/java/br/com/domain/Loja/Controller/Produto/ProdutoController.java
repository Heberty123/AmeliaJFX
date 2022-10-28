package br.com.domain.Loja.Controller.Produto;

import br.com.domain.Loja.Models.Produto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

@Controller
public class ProdutoController {

    /* Attributes */

    private Produto produto;

    /* FXML */

    @FXML
    private Label labelId;

    @FXML
    private Label labelProduto;

    @FXML
    private Label labelDescricao;

    @FXML
    private Label labelReferencia;

    @FXML
    private Label labelCodigoBarra;

    @FXML
    private Label labelMarca;

    @FXML
    private Label labelPreço;




    public void updateView(){
        labelId.setText(String.valueOf(this.produto.getId()));
        labelProduto.setText(this.produto.getNome());
        labelDescricao.setText(this.produto.getDescricao());
        labelReferencia.setText(this.produto.getReferencia());
        labelCodigoBarra.setText(String.valueOf(this.produto.getCodigoBarra()));
    }


    public void setProduto(Produto produto){
        this.produto = produto;
    }
}
