package com.sohilladhani.loginapp.login.controllers;

import com.sohilladhani.loginapp.admin.controllers.AdminController;
import com.sohilladhani.loginapp.login.entities.option;
import com.sohilladhani.loginapp.login.models.LoginModel;
import com.sohilladhani.loginapp.student.dao.SQLiteStudentDAO;
import com.sohilladhani.loginapp.student.dao.StudentDAO;
import com.sohilladhani.loginapp.student.models.StudentModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private LoginModel loginModel;

    @FXML
    private Label dbStatus;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<option> selectionComboBox;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginStatus;

    public LoginController() {
    }

    private StudentDAO buildStudentDAO() {
        return new SQLiteStudentDAO();
    }

    private StudentModel buildStudentModel() {
        return new StudentModel(buildStudentDAO());
    }

    private AdminController buildAdminController(Stage stage) {
        return new AdminController(buildStudentModel(), stage);
    }

    public LoginController(LoginModel loginModel, Stage stage) {
        this.loginModel = loginModel;
        stage.setOnCloseRequest(e -> loginModel.close());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (this.loginModel.isConnected()) {
            this.dbStatus.setText("Database connected");
        } else {
            this.dbStatus.setText("DB not connected");
        }
        this.selectionComboBox.setItems(FXCollections.observableArrayList(option.values()));
    }

    public void onLoginPressed(ActionEvent actionEvent) {
        if (this.loginModel.isConnected()) {
            try {
                String userNameField = this.userName.getText();
                String passwordField = this.password.getText();
                String divisionField = this.selectionComboBox.getValue().toString();

                if (this.loginModel.isUserLoggedIn(userNameField, passwordField, divisionField)) {
                    Stage stage = (Stage) this.loginButton.getScene().getWindow();
                    stage.hide();
                    switch (divisionField.toLowerCase().trim()) {
                        case "admin":
                            loginAsAnAdmin();
                            break;

                        default:
                            System.out.println("Something went wrong");
                            break;
                    }
                } else {
                    loginStatus.setText("Wrong credentials.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("DB not connected");
        }

    }

    private void loginAsAnAdmin() {
        try {
            Stage adminStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/sohilladhani" +
                    "/loginapp/admin/ui/admin.fxml"));
            fxmlLoader.setControllerFactory(t -> buildAdminController(adminStage));
            Scene scene = new Scene(fxmlLoader.load());
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
