package gui.Controller;

import gui.WebServerGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.io.IOException;

public class WebServerController {

    public CheckBox checkSwitch;

    @FXML
    private Label welcomeText;

    @FXML
    private Label serverStatusLabel;

    @FXML
    private Label serverAddressLabel;

    @FXML
    private Label serverListeningPortLabel;

    @FXML
    private Button buttonServerStatus;

    @FXML
    protected void onHelloButtonClick() throws InterruptedException, IOException {
        if(WebServerGUI.webServerGUI.SERVER_STATUS.equals("RUN_SERVER")) {
            WebServerGUI.webServerGUI.SERVER_STATUS = "STOP_SERVER";
            WebServerGUI.serverSocketGUI.close();
            setTexts();
        } else {
            WebServerGUI.webServerGUI.SERVER_STATUS = "RUN_SERVER";
            setTexts();
        }
        System.out.println("CURRENT SERVER STATUS:" + WebServerGUI.webServerGUI.SERVER_STATUS);
    }

    public void initialize(){
        setTexts();
    }
    private void setTexts() {
        welcomeText.setText("Server Status: " + WebServerGUI.webServerGUI.SERVER_STATUS);

        switch (WebServerGUI.webServerGUI.SERVER_STATUS) {
            case "RUN_SERVER":
                buttonServerStatus.setText("Stop server");
                serverStatusLabel.setText("running...");
                serverAddressLabel.setText(WebServerGUI.SERVER_ADDRESS);
                serverListeningPortLabel.setText(String.valueOf(WebServerGUI.SERVER_LISTENING_PORT));
                checkSwitch.setDisable(false);
                break;
            case "MAINTENANCE_SERVER":
                serverStatusLabel.setText("maintenance");
                serverAddressLabel.setText(WebServerGUI.SERVER_ADDRESS);
                serverListeningPortLabel.setText(String.valueOf(WebServerGUI.SERVER_LISTENING_PORT));
                break;
            case "STOP_SERVER":
                buttonServerStatus.setText("Start server");
                serverStatusLabel.setText("not running");
                serverAddressLabel.setText("not running");
                serverListeningPortLabel.setText("not running");
                checkSwitch.setDisable(true);
                break;
        }
    }


    public void checkSwitchMode(ActionEvent actionEvent) {
        if(!checkSwitch.isSelected()) {
            WebServerGUI.webServerGUI.SERVER_STATUS = "RUN_SERVER";
            setTexts();
        } else {
            WebServerGUI.webServerGUI.SERVER_STATUS = "MAINTENANCE_SERVER";
            setTexts();
        }
    }

}