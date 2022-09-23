package br.com.domain.Loja.Controller;

import br.com.domain.Loja.StageInitializer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class HomeController {
    @Value("classpath:/gui/Cliente/Cadastro.fxml")
    private Resource cadastroCliente;
    @FXML
    private MenuItem itemCadastroCliente;
    private ApplicationContext applicationContext;

    public HomeController(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    public void itemCadastroClienteAction() throws IOException {
        ChangeFXML(cadastroCliente);
    }

    public void ChangeFXML(Resource absoluteName) throws IOException {

        FXMLLoader loader = new FXMLLoader(absoluteName.getURL());
        loader.setControllerFactory(aClass -> applicationContext.getBean(aClass));
        AnchorPane newAnchor = loader.load();

        Scene mainScene = StageInitializer.getScene();
        AnchorPane mainAnchor = (AnchorPane) ((ScrollPane) mainScene.getRoot()).getContent();

        Node mainMenu = mainAnchor.getChildren().get(0);
        mainAnchor.getChildren().clear();
        mainAnchor.getChildren().add(mainMenu);
        mainAnchor.getChildren().addAll(newAnchor.getChildren());

    }
}
