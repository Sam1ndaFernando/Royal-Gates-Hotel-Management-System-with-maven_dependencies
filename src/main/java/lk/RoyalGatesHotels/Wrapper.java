package lk.RoyalGatesHotels;

import lk.RoyalGatesHotels.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;

public class Wrapper {
    public static void main(String[] args) {

        AppInitializer.main(args);
//        InputStream inputStream = Wrapper.class.getResourceAsStream("report/test.jrxml");
//        try {;
//            JasperReport compileReport = JasperCompileManager.compileReport( JRXmlLoader.load(inputStream));
//            JasperPrint jasperPrint = JasperFillManager.fillReport(
//                    compileReport,null, DBConnection.getInstance().getConnection());
//            JasperViewer.viewReport(jasperPrint, false);
//        } catch (JRException | SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
