package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.dto.Hall;
import lk.RoyalGatesHotels.dto.Room;
import lk.RoyalGatesHotels.model.HallsModel;
import lk.RoyalGatesHotels.model.RoomsModel;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.RegExPattern;
import lk.RoyalGatesHotels.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminBanquetHallsController implements Initializable {
    public AnchorPane adminHallContext;
    public Label lblTime;
    public Label lblDate;
    public JFXTextField txtHallNumber;
    public JFXTextField txtHallType;
    public JFXTextField txtPrice;

    public JFXComboBox cmbAvailability;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Pulse(adminHallContext).play();
        setAdminHall();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

    }

    private void setAdminHall() {

        try {
            String lastAdminHallId = HallsModel.getLastAdminHallId();
            String[] split = lastAdminHallId.split("[H]");
            if (split.length > 1) {
                int lastDigits = Integer.parseInt(split[1]);
                lastDigits++;
                String newAdminHallId = String.format("H%04d", lastDigits);
                txtHallNumber.setText(newAdminHallId);
            } else {

            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error : Invalid format for hallNumber").show();
        } catch (SQLException | ClassNotFoundException e) {
        }


       /* try {
            String lastAdminHallId = HallsModel.getLastAdminHallId();
            if (lastAdminHallId == null) {
                txtHallNumber.setText("H0001");
            } else {
                String[] split = lastAdminHallId.split("[H]");
                int lastDigits = Integer.parseInt(split[1]);
                lastDigits++;
                String newAdminHallId = String.format("H%04d", lastDigits);
                txtHallNumber.setText(newAdminHallId);
            }
        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }*/
    }


    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINDASHBOARD,adminHallContext);
    }

    public void btnRooms(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINROOM,adminHallContext);
    }

    public void btnMealPlans(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPLAN,adminHallContext);
    }

    public void btnUsers(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINUSER,adminHallContext);
    }

    public void btnEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINEMPLOYEE,adminHallContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,adminHallContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,adminHallContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,adminHallContext);
    }

    public void btnBanquetHalls(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINHALLS,adminHallContext);
    }

    public void btnReports(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT,adminHallContext);
    }

    public void btnAddHall(ActionEvent actionEvent) throws IOException {

        boolean isPriceMatched = RegExPattern.getPricePattern().matcher(txtPrice.getText()).matches();

        if(isPriceMatched){
            Hall hall = new Hall(
                    txtHallNumber.getText(),
                    txtHallType.getText(),
                    "Available",
                    Double.parseDouble(txtPrice.getText())
            );

            try {
                boolean isAdd = HallsModel.addHall(hall);
                if(isAdd){
                    new Alert(Alert.AlertType.CONFIRMATION,"Hall Added Successfully!").show();
                    clearAll();
                    setAdminHall();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Hall Not Added!").show();
                }

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else{
            txtPrice.requestFocus();
        }
    }


    public void btnUpdateHall(ActionEvent actionEvent) throws IOException {
        boolean isPriceMatched = RegExPattern.getPricePattern().matcher(txtPrice.getText()).matches();

        if(isPriceMatched){
            Hall hall = new Hall(
                    txtHallNumber.getText(),
                    txtHallType.getText(),
                    String.valueOf(cmbAvailability.getValue()),
                    Double.parseDouble(txtPrice.getText())
            );

            try {
                boolean isUpdate = HallsModel.updateHall(hall);
                if(isUpdate){
                    new Alert(Alert.AlertType.CONFIRMATION,"Hall Updated Successfully!").show();
                    clearAll();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Hall Not Updated!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else{
            txtPrice.requestFocus();
        }
    }

    private void clearAll() {
        txtHallNumber.clear();
        txtHallType.clear();
        txtPrice.clear();
        cmbAvailability.setPromptText(null);
    }

    public void btnCancelHall(ActionEvent actionEvent){

        clearAll();

    }

    public void txtHallNumberOnAction(ActionEvent actionEvent) {
        try {
            ResultSet result = HallsModel.searchHall(txtHallNumber.getText());
            if(result.next()){
                txtHallType.setText(result.getString("hall_type"));
                txtPrice.setText(result.getString("price"));
                cmbAvailability.setPromptText(result.getString("status"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void AvailabilityOnAction(ActionEvent actionEvent) {
    }
}
