package com.sohilladhani.loginapp.admin.controllers;

import com.sohilladhani.loginapp.student.entities.Student;
import com.sohilladhani.loginapp.student.models.StudentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private TextField studentFirstName;
    @FXML
    private TextField studentLastName;
    @FXML
    private TextField studentEmail;
    @FXML
    private DatePicker studentDOB;
    @FXML
    private TableView<Student> studentModelTableView;
    @FXML
    private TableColumn<Student, Integer> studentIdColumn;
    @FXML
    private TableColumn<Student, String> studentFirstNameColumn;
    @FXML
    private TableColumn<Student, String> studentLastNameColumn;
    @FXML
    private TableColumn<Student, String> studentEmailColumn;
    @FXML
    private TableColumn<Student, String> studentDOBColumn;

    private StudentModel studentModel;

    public AdminController() {
    }

    public AdminController(StudentModel studentModel, Stage stage) {
        this.studentModel = studentModel;
        stage.setOnCloseRequest(e -> studentModel.close());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadStudentDataInTableView();
    }

    public void onAddStudentButtonPressed(ActionEvent actionEvent) {
        String firstName = studentFirstName.getText();
        String lastName = studentLastName.getText();
        String email = studentEmail.getText();
        String DOB = studentDOB.getEditor().getText();

        this.studentModel.addStudent(firstName, lastName, email, DOB);
        loadStudentDataInTableView();
    }

    public void onClearFormButtonPressed(ActionEvent actionEvent) {
        studentFirstName.clear();
        studentLastName.clear();
        studentEmail.clear();
        studentDOB.getEditor().clear();
    }

    public void onLoadDataButtonPressed(ActionEvent actionEvent) {
        loadStudentDataInTableView();
    }

    private void loadStudentDataInTableView() {
        List<Student> listOfAllStudents = studentModel.findAllStudents();
        ObservableList<Student> studentObservableList =
                FXCollections.observableList(listOfAllStudents);

        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        studentFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>(
                "fName"));
        studentLastNameColumn.setCellValueFactory(new PropertyValueFactory<>(
                "lName"));
        studentEmailColumn.setCellValueFactory(new PropertyValueFactory<>(
                "email"));
        studentDOBColumn.setCellValueFactory(new PropertyValueFactory<>(
                "DOB"
        ));

        studentModelTableView.getColumns().setAll(studentIdColumn, studentFirstNameColumn,
                studentLastNameColumn, studentEmailColumn, studentDOBColumn);
        studentModelTableView.setItems(studentObservableList);

    }
}
