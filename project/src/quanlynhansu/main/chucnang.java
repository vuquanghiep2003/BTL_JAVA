package quanlynhansu.main;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class chucnang implements Initializable {

    public Button close1;
    public Button save;

    @FXML
    public TableView<nhanvien> table;

    @FXML
    public TableColumn<nhanvien, Integer> idColumn;

    @FXML
    public TableColumn<nhanvien, String> nameColumn;

    @FXML
    public TableColumn<nhanvien, String> emailColumn;

    @FXML
    public TableColumn<nhanvien, Integer> ageColumn;

    @FXML
    public TableColumn<nhanvien, String> addressColumn;

    @FXML
    public TableColumn<nhanvien, Integer> workdayColumn;

    @FXML
    public TableColumn<nhanvien, String> sexColumn;

    @FXML
    public TableColumn<nhanvien, String> partColumn;

    @FXML
    public TableColumn<nhanvien, String> positionColumn;

    @FXML
    public TableColumn<nhanvien, Integer> salaryColumn;

    @FXML
    public TextField idText;

    @FXML
    public TextField nameText;

    @FXML
    public TextField emailText;

    @FXML
    public TextField ageText;

    @FXML
    public TextField addressText;

    @FXML
    public TextField workdayText;

    @FXML
    public TextField searchText;

    @FXML
    public ComboBox<String> sexText;

    @FXML
    public ComboBox<String> partText;

    @FXML
    public ComboBox<String> positionText;

    ObservableList<String> sexTextList = FXCollections.observableArrayList("Nam", "Nữ", "Khác");
    ObservableList<String> partTextList = FXCollections.observableArrayList("Technology", "Accounting", "Marketing", "Engineering");
    ObservableList<String> positionList = FXCollections.observableArrayList("CEO", "Founder", "Manager", "Director");
    ObservableList<nhanvien> nhanvienList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<nhanvien, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<nhanvien, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<nhanvien, String>("email"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<nhanvien, Integer>("age"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<nhanvien, String>("address"));
        workdayColumn.setCellValueFactory(new PropertyValueFactory<nhanvien, Integer>("workday"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<nhanvien, String>("sex"));
        partColumn.setCellValueFactory(new PropertyValueFactory<nhanvien, String>("part"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<nhanvien, String>("position"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<nhanvien, Integer>("salary"));
        table.setItems(nhanvienList);
        sexText.setItems(sexTextList);
        partText.setItems(partTextList);
        positionText.setItems(positionList);

        // lang nghe Listener de tim kiem
        searchText.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                searchList((String) oldValue, (String) newValue);
            }
        });
    }

    public void sexAction(ActionEvent e) {
    }

    public void partAction(ActionEvent e) {
    }

    public void positionAction(ActionEvent e) {
    }

    // Tìm kiếm theo ID, name
    public void searchList(String oldValue, String newValue) {
        ObservableList<nhanvien> filteredList = FXCollections.observableArrayList();
        if (searchText == null || (newValue.length() < oldValue.length()) || newValue == null) {
            table.setItems(nhanvienList);
        } else {
            newValue = newValue.toUpperCase();
            for (nhanvien nhanvien : table.getItems()) {
                String idSearch = String.valueOf(nhanvien.getId());
                String emailSearch = nhanvien.getEmail();
                if (idSearch.toUpperCase().contains(newValue) || emailSearch.toUpperCase().contains(newValue)) {
                    filteredList.add(nhanvien);
                }
            }
            table.setItems(filteredList);
        }
    }

    // điều kiện ko trùng id
    public boolean isValidInput(ActionEvent event) {
        Boolean validInput = true;

        for (nhanvien nhanvien : table.getItems()) {
            String idSearch = valueOf(nhanvien.getId());
            if (idSearch.contains(idText.getText())) {
                validInput = false;
                Alert emptyFirstName = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
                Window owner = ((Node) event.getTarget()).getScene().getWindow();
                emptyFirstName.setContentText("Duplicate id please enter another id.");
                emptyFirstName.initModality(Modality.APPLICATION_MODAL);
                emptyFirstName.initOwner(owner);
                emptyFirstName.showAndWait();
                if (emptyFirstName.getResult() == ButtonType.OK) {
                    emptyFirstName.close();
                    idText.requestFocus();
                }
            }
        }

        if (idText == null || idText.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyFirstName = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyFirstName.setContentText("Id is empty or duplicate");
            emptyFirstName.initModality(Modality.APPLICATION_MODAL);
            emptyFirstName.initOwner(owner);
            emptyFirstName.showAndWait();
            if (emptyFirstName.getResult() == ButtonType.OK) {
                emptyFirstName.close();
                idText.requestFocus();
            }
        }
        if (nameText == null || nameText.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyLastName = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyLastName.setContentText("Name is EMPTY");
            emptyLastName.initModality(Modality.APPLICATION_MODAL);
            emptyLastName.initOwner(owner);
            emptyLastName.showAndWait();
            if (emptyLastName.getResult() == ButtonType.OK) {
                emptyLastName.close();
                nameText.requestFocus();
            }
        }
        if (emailText == null || emailText.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyUIN = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyUIN.setContentText("Email is EMPTY");
            emptyUIN.initModality(Modality.APPLICATION_MODAL);
            emptyUIN.initOwner(owner);
            emptyUIN.showAndWait();
            if (emptyUIN.getResult() == ButtonType.OK) {
                emptyUIN.close();
                emailText.requestFocus();
            }
        }
        if (ageText == null || ageText.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyNetID = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyNetID.setContentText("Age is EMPTY");
            emptyNetID.initModality(Modality.APPLICATION_MODAL);
            emptyNetID.initOwner(owner);
            emptyNetID.showAndWait();
            if (emptyNetID.getResult() == ButtonType.OK) {
                emptyNetID.close();
                ageText.requestFocus();
            }
        }
        if (addressText == null || addressText.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyMajor = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyMajor.setContentText("Address is EMPTY");
            emptyMajor.initModality(Modality.APPLICATION_MODAL);
            emptyMajor.initOwner(owner);
            emptyMajor.showAndWait();
            if (emptyMajor.getResult() == ButtonType.OK) {
                emptyMajor.close();
                addressText.requestFocus();
            }
        }
        if (workdayText == null || workdayText.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyUIN = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyUIN.setContentText("Workday is EMPTY");
            emptyUIN.initModality(Modality.APPLICATION_MODAL);
            emptyUIN.initOwner(owner);
            emptyUIN.showAndWait();
            if (emptyUIN.getResult() == ButtonType.OK) {
                emptyUIN.close();
                workdayText.requestFocus();
            }
        }
        if (sexText.getValue() == null || sexText.getValue().trim().isEmpty()) {
            validInput = false;
            Alert emptyAge = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyAge.setContentText("Sex is EMPTY");
            emptyAge.initModality(Modality.APPLICATION_MODAL);
            emptyAge.initOwner(owner);
            emptyAge.showAndWait();
            if (emptyAge.getResult() == ButtonType.OK) {
                emptyAge.close();
                sexText.requestFocus();
            }
        }
        if (partText.getValue() == null || partText.getValue().trim().isEmpty()) {
            validInput = false;
            Alert emptyGPA = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyGPA.setContentText("Part is EMPTY");
            emptyGPA.initModality(Modality.APPLICATION_MODAL);
            emptyGPA.initOwner(owner);
            emptyGPA.showAndWait();
            if (emptyGPA.getResult() == ButtonType.OK) {
                emptyGPA.close();
                partText.requestFocus();
            }
        }
        if (positionText.getValue() == null || positionText.getValue().isEmpty()) {
            validInput = false;
            Alert emptyGender = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyGender.setContentText("Position is EMPTY");
            emptyGender.initModality(Modality.APPLICATION_MODAL);
            emptyGender.initOwner(owner);
            emptyGender.showAndWait();
            if (emptyGender.getResult() == ButtonType.OK) {
                emptyGender.close();
                positionText.requestFocus();
            }
        }
        return validInput;
    }

    public void resetText() {
        idText.setText("");
        nameText.setText("");
        emailText.setText("");
        ageText.setText("");
        addressText.setText("");
        workdayText.setText("");
        sexText.setValue("");
        partText.setValue("");
        positionText.setValue("");
    }

    // Thêm
    public void add(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Xác nhận thêm nhân viên");
        alert.setContentText("Bạn có muốn thêm nhân viên này không?");
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (isValidInput(e)) {
            if (result.get() == buttonTypeYes) {
                nhanvien newNhanvien = new nhanvien();
                newNhanvien.setId(Integer.parseInt(idText.getText()));
                newNhanvien.setName(nameText.getText());
                newNhanvien.setEmail(emailText.getText());
                newNhanvien.setAge(Integer.parseInt(ageText.getText()));
                newNhanvien.setAddress(addressText.getText());
                newNhanvien.setWorkday(Integer.parseInt(workdayText.getText()));
                newNhanvien.setSex(sexText.getValue());
                newNhanvien.setPart(partText.getValue());
                newNhanvien.setPosition(positionText.getValue());

                // Nhân hệ số lương tương ứng
                if (partText.getValue() == "Technology") {
                    if (positionText.getValue() == "CEO") {
                        newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 10000);
                    }
                    if (positionText.getValue() == "Founder") {
                        newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 5000);
                    }
                    if (positionText.getValue() == "Manager") {
                        newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 4000);
                    }
                    if (positionText.getValue() == "Director") {
                        newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 3000);
                    }
                }
                if (partText.getValue() == "Accounting") {
                    if (positionText.getValue() == "CEO") {
                        newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 9000);
                    }
                    if (positionText.getValue() == "Founder") {
                        newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 6000);
                    }
                    if (positionText.getValue() == "Manager") {
                        newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 5000);
                    }
                    if (positionText.getValue() == "Director") {
                        newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 3000);
                    }
                }
                if (partText.getValue() == "Marketing") {
                    if (positionText.getValue() == "CEO") {
                        newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 7000);
                    }
                    if (positionText.getValue() == "Founder") {
                        newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 5000);
                    }
                    if (positionText.getValue() == "Manager") {
                        newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 4000);
                    }
                    if (positionText.getValue() == "Director") {
                        newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 2000);
                    }
                }
                if (partText.getValue() == "Engineering") {
                    if (positionText.getValue() == "CEO") {
                        newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 20000);
                    }
                    if (positionText.getValue() == "Founder") {
                        newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 15000);
                    }
                    if (positionText.getValue() == "Manager") {
                        newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 10000);
                    }
                    if (positionText.getValue() == "Director") {
                        newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 9000);
                    }
                }

                nhanvienList.add(newNhanvien);
            } else {
                resetText();
                Alert alertAdd = new Alert(Alert.AlertType.INFORMATION);
                alertAdd.setTitle("Thông báo");
                alertAdd.setContentText("Chưa được thêm vào");
                alertAdd.show();
            }
        }
    }

    // Xoá
    public void delete(ActionEvent e) {
        nhanvien selected = table.getSelectionModel().getSelectedItem();
        nhanvienList.remove(selected);
    }

    // Đóng
    public void close(ActionEvent actionEvent) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm", ButtonType.OK, ButtonType.CANCEL);
        Stage stage = (Stage) close1.getScene().getWindow();
        exitAlert.setContentText("Are you sure you want to exit?");
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.initOwner(stage);
        exitAlert.showAndWait();

        if (exitAlert.getResult() == ButtonType.OK) {
            Platform.exit();
        } else {
            exitAlert.close();
        }
    }


    // Ghi dữ liệu xuống file
    public void handleSave(ActionEvent event) {
        File file = new File("data.txt");
        try {
            FileWriter fw = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveFile(table.getItems(), file);
    }
    public void saveFile(ObservableList<nhanvien> staffList, File file) {
        try {
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));
            for (nhanvien staff : staffList) {
                outWriter.write(staff.toString());
                outWriter.newLine();
            }
            outWriter.close();
        } catch (IOException e) {
            Alert ioAlert = new Alert(Alert.AlertType.ERROR, "OOPS!", ButtonType.OK);
            ioAlert.setContentText("File write error!");
            ioAlert.showAndWait();
            if (ioAlert.getResult() == ButtonType.OK) {
                ioAlert.close();
            }
        }
    }

    public void clickView(MouseEvent mouseEvent) {
        nhanvien nhanvien = table.getSelectionModel().getSelectedItem();
        String temp = String.valueOf(nhanvien.getId());
        if (nhanvien != null) {
            idText.setText(temp);
            nameText.setText(nhanvien.getName());
            emailText.setText(nhanvien.getEmail());
            ageText.setText(String.valueOf(nhanvien.getAge()));
            addressText.setText(nhanvien.getAddress());
            workdayText.setText(String.valueOf(nhanvien.getWorkday()));
            sexText.setValue(nhanvien.getSex());
            partText.setValue(nhanvien.getPart());
            positionText.setValue(nhanvien.getPosition());
        }
    }

    // Sửa
    public void revisionAction(ActionEvent event) {
        Alert alertEdit1 = new Alert(Alert.AlertType.INFORMATION);
        alertEdit1.setTitle("Xác nhận");
        alertEdit1.setHeaderText("Xác nhận sửa thông tin?");
        alertEdit1.setContentText("Bạn có xác nhận sửa thông tin này?");
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alertEdit1.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);
        alertEdit1.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);

        Optional<ButtonType> result = alertEdit1.showAndWait();
        if (result.get() == buttonTypeYes) {
            nhanvien select = table.getSelectionModel().getSelectedItem();
            nhanvien newNhanvien = new nhanvien();
            for (nhanvien nhanvien : nhanvienList) {
                if (nhanvien == select) {
                    newNhanvien.setId(Integer.parseInt(idText.getText()));
                    newNhanvien.setName(nameText.getText());
                    newNhanvien.setEmail(emailText.getText());
                    newNhanvien.setAge(Integer.parseInt(ageText.getText()));
                    newNhanvien.setAddress(addressText.getText());
                    newNhanvien.setWorkday(Integer.parseInt(workdayText.getText()));
                    newNhanvien.setSex(sexText.getValue());
                    newNhanvien.setPart(partText.getValue());
                    newNhanvien.setPosition(positionText.getValue());

                    if (partText.getValue() == "Technology") {
                        if (positionText.getValue() == "CEO") {
                            newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 10000);
                        }
                        if (positionText.getValue() == "Founder") {
                            newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 5000);
                        }
                        if (positionText.getValue() == "Manager") {
                            newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 4000);
                        }
                        if (positionText.getValue() == "Director") {
                            newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 3000);
                        }
                    }
                    if (partText.getValue() == "Accounting") {
                        if (positionText.getValue() == "CEO") {
                            newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 9000);
                        }
                        if (positionText.getValue() == "Founder") {
                            newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 6000);
                        }
                        if (positionText.getValue() == "Manager") {
                            newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 5000);
                        }
                        if (positionText.getValue() == "Director") {
                            newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 3000);
                        }
                    }
                    if (partText.getValue() == "Marketing") {
                        if (positionText.getValue() == "CEO") {
                            newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 7000);
                        }
                        if (positionText.getValue() == "Founder") {
                            newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 5000);
                        }
                        if (positionText.getValue() == "Manager") {
                            newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 4000);
                        }
                        if (positionText.getValue() == "Director") {
                            newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 2000);
                        }
                    }
                    if (partText.getValue() == "Engineering") {
                        if (positionText.getValue() == "CEO") {
                            newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 20000);
                        }
                        if (positionText.getValue() == "Founder") {
                            newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 15000);
                        }
                        if (positionText.getValue() == "Manager") {
                            newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 10000);
                        }
                        if (positionText.getValue() == "Director") {
                            newNhanvien.setSalary(Integer.parseInt(workdayText.getText()) * 9000);
                        }
                    }

                    nhanvienList.set(nhanvienList.indexOf(nhanvien), newNhanvien);
                }
            }
        } else {
            resetText();
            Alert alertEdit2 = new Alert(Alert.AlertType.INFORMATION);
            alertEdit2.setTitle("Information");
            alertEdit2.setHeaderText("Sửa thành công");
            alertEdit2.show();
        }
    }

    public void showData(ActionEvent actionEvent) throws IOException {
        File file = new File("data.txt");
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        String line = "";
        while((line = reader.readLine()) != null){
            System.out.println(line);
        }
    }
}