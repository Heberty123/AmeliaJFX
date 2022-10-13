package br.com.domain.Loja.Services;

import br.com.domain.Loja.StageInitializer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.function.Consumer;

@Service
public class ChangeView {

    private ApplicationContext applicationContext;


    public ChangeView(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    public synchronized <T> void change(String absoluteName, Consumer<T> initializingAction) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
        loader.setControllerFactory(aClass -> applicationContext.getBean(aClass));
        AnchorPane newAnchor = loader.load();

        Scene mainScene = StageInitializer.getScene();
        AnchorPane mainAnchor = (AnchorPane) ((ScrollPane) mainScene.getRoot()).getContent();

        Node mainMenu = mainAnchor.getChildren().get(0);
        mainAnchor.getChildren().clear();
        mainAnchor.getChildren().add(mainMenu);
        mainAnchor.getChildren().addAll(newAnchor.getChildren());

        T controller = loader.getController();
        initializingAction.accept(controller);

    }
}
