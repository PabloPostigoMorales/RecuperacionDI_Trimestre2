package main.exameninterfaces;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.InputStream;
import java.net.URL;
import java.nio.DoubleBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;

public class PrincipalController implements Initializable {
    private Connection con = BddConnection.getCon();
    @FXML
    private BorderPane borderPaneMain;
    @FXML
    private TableView tableViewMain;
    @FXML
    private TableColumn nombreColumn;
    @FXML
    private TableColumn apellidosColumn;
    @FXML
    private TableColumn adColumn;
    @FXML
    private TableColumn sgeColumn;
    @FXML
    private TableColumn diColumn;
    @FXML
    private TableColumn pmdmColumn;
    @FXML
    private TableColumn pspColumn;
    @FXML
    private TableColumn eieColumn;
    @FXML
    private TableColumn hlcColumn;
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField apellidosTextField;
    @FXML
    private TextField adTextField;
    @FXML
    private TextField sgeTextField;
    @FXML
    private TextField diTextField;
    @FXML
    private TextField pmdmTextField;
    @FXML
    private TextField pspTextField;
    @FXML
    private TextField eieTextField;
    @FXML
    private TextField hlcTextField;
    @FXML
    private Button insertButton;
    @FXML
    private Button descargarButton;
    @FXML
    private Button salirButton;
    @FXML
    private Label infoNombreLabel;
    @FXML
    private Label infoNotaMediaLabel;
    @FXML
    private Label infoSuspensosLabel;

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        try {
            String query = "SELECT * FROM exameninterfaces.alumno";
            Statement statement = null;

            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ArrayList<Alumno> alumnosLista = new ArrayList<>();
            while (resultSet.next()) {
                Alumno alumno = new Alumno();
                alumno.setId(resultSet.getInt("id"));
                alumno.setNombre(resultSet.getString("nombre"));
                alumno.setApellidos(resultSet.getString("apellidos"));
                alumno.setAd(resultSet.getDouble("ad"));
                alumno.setDi(resultSet.getDouble("di"));
                alumno.setEie(resultSet.getDouble("eie"));
                alumno.setPmdm(resultSet.getDouble("pmdm"));
                alumno.setPsp(resultSet.getDouble("psp"));
                alumno.setSge(resultSet.getDouble("sge"));
                alumno.setHlc(resultSet.getDouble("hlc"));
                alumnosLista.add(alumno);
            }
            ObservableList<Alumno> alumnos = FXCollections.observableArrayList(alumnosLista);

            tableViewMain.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    Alumno alumno = (Alumno) newValue;
                    String nombreCompleto = "Nombre: "+ alumno.getNombre() + " " + alumno.getApellidos();
                    infoNombreLabel.setText(nombreCompleto);

                    Double notaMedia = alumno.getNotaMedia();
                    if (notaMedia >= 0) {
                        infoNotaMediaLabel.setText("Nota media: " + notaMedia);
                    } else {
                        infoNotaMediaLabel.setText("No tiene todas las asignaturas aprobadas.");
                    }

                    Double numSuspensos = alumno.getNumModulosSuspensos();
                    if (numSuspensos > 0) {
                        infoSuspensosLabel.setText("Suspensos: " + numSuspensos);
                    } else {
                        infoSuspensosLabel.setText("No tiene ningun suspenso");
                    }
                }
            });


            nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));
            apellidosColumn.setCellValueFactory(new PropertyValueFactory("apellidos"));
            adColumn.setCellValueFactory(new PropertyValueFactory("ad"));
            adColumn.setCellFactory(column -> {
                TableCell<Alumno, Double> celda = new TableCell<Alumno, Double>() {
                    @Override
                    protected void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : item.toString());
                    }
                };
                Tooltip tooltip = new Tooltip("Acceso a datos");
                celda.setOnMouseEntered(event -> {
                    if (!celda.isEmpty()) {
                        Bounds bounds = celda.localToScreen(celda.getBoundsInLocal());
                        tooltip.show(celda, bounds.getMaxX(), bounds.getMinY());
                    }
                });
                celda.setOnMouseExited(event -> {
                    tooltip.hide();
                });
                return celda;
            });
            sgeColumn.setCellValueFactory(new PropertyValueFactory("sge"));
            sgeColumn.setCellFactory(column -> {
                TableCell<Alumno, Double> celda = new TableCell<Alumno, Double>() {
                    @Override
                    protected void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : item.toString());
                    }
                };
                Tooltip tooltip = new Tooltip("Sistemas de gestion empresarial");
                celda.setOnMouseEntered(event -> {
                    if (!celda.isEmpty()) {
                        Bounds bounds = celda.localToScreen(celda.getBoundsInLocal());
                        tooltip.show(celda, bounds.getMaxX(), bounds.getMinY());
                    }
                });
                celda.setOnMouseExited(event -> {
                    tooltip.hide();
                });
                return celda;
            });
            diColumn.setCellValueFactory(new PropertyValueFactory("di"));
            diColumn.setCellFactory(column -> {
                TableCell<Alumno, Double> celda = new TableCell<Alumno, Double>() {
                    @Override
                    protected void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : item.toString());
                    }
                };
                Tooltip tooltip = new Tooltip("Desarrollo de interfaces");
                celda.setOnMouseEntered(event -> {
                    if (!celda.isEmpty()) {
                        Bounds bounds = celda.localToScreen(celda.getBoundsInLocal());
                        tooltip.show(celda, bounds.getMaxX(), bounds.getMinY());
                    }
                });
                celda.setOnMouseExited(event -> {
                    tooltip.hide();
                });
                return celda;
            });
            pmdmColumn.setCellValueFactory(new PropertyValueFactory("pmdm"));
            pmdmColumn.setCellFactory(column -> {
                TableCell<Alumno, Double> celda = new TableCell<Alumno, Double>() {
                    @Override
                    protected void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : item.toString());
                    }
                };
                Tooltip tooltip = new Tooltip("Programacion multimedia y dispositivos moviles");
                celda.setOnMouseEntered(event -> {
                    if (!celda.isEmpty()) {
                        Bounds bounds = celda.localToScreen(celda.getBoundsInLocal());
                        tooltip.show(celda, bounds.getMaxX(), bounds.getMinY());
                    }
                });
                celda.setOnMouseExited(event -> {
                    tooltip.hide();
                });
                return celda;
            });
            pspColumn.setCellValueFactory(new PropertyValueFactory("psp"));
            pspColumn.setCellFactory(column -> {
                TableCell<Alumno, Double> celda = new TableCell<Alumno, Double>() {
                    @Override
                    protected void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : item.toString());
                    }
                };
                Tooltip tooltip = new Tooltip("Programacion de servicios y procesos");
                celda.setOnMouseEntered(event -> {
                    if (!celda.isEmpty()) {
                        Bounds bounds = celda.localToScreen(celda.getBoundsInLocal());
                        tooltip.show(celda, bounds.getMaxX(), bounds.getMinY());
                    }
                });
                celda.setOnMouseExited(event -> {
                    tooltip.hide();
                });
                return celda;
            });
            eieColumn.setCellValueFactory(new PropertyValueFactory("eie"));
            eieColumn.setCellFactory(column -> {
                TableCell<Alumno, Double> celda = new TableCell<Alumno, Double>() {
                    @Override
                    protected void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : item.toString());
                    }
                };
                Tooltip tooltip = new Tooltip("Empresas e iniciativa emprendedora");
                celda.setOnMouseEntered(event -> {
                    if (!celda.isEmpty()) {
                        Bounds bounds = celda.localToScreen(celda.getBoundsInLocal());
                        tooltip.show(celda, bounds.getMaxX(), bounds.getMinY());
                    }
                });
                celda.setOnMouseExited(event -> {
                    tooltip.hide();
                });
                return celda;
            });
            hlcColumn.setCellValueFactory(new PropertyValueFactory("hlc"));
            hlcColumn.setCellFactory(column -> {
                TableCell<Alumno, Double> celda = new TableCell<Alumno, Double>() {
                    @Override
                    protected void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : item.toString());
                    }
                };
                Tooltip tooltip = new Tooltip("Hora de libre configuracion");
                celda.setOnMouseEntered(event -> {
                    if (!celda.isEmpty()) {
                        Bounds bounds = celda.localToScreen(celda.getBoundsInLocal());
                        tooltip.show(celda, bounds.getMaxX(), bounds.getMinY());
                    }
                });
                celda.setOnMouseExited(event -> {
                    tooltip.hide();
                });
                return celda;
            });

            tableViewMain.getStylesheets().add(this.getClass().getResource("/main/exameninterfaces/PrincipalCSS.css").toExternalForm());
            tableViewMain.setItems(alumnos);

            tableViewMain.getSelectionModel().clearAndSelect(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onInsertButtonClick (ActionEvent actionEvent) {

        String nombre = null;
        String apellidos = null;
        Double ad = 0.0;
        Double sge = 0.0;
        Double di = 0.0;
        Double pmdm = 0.0;
        Double psp = 0.0;
        Double eie = 0.0;
        Double hlc = 0.0;
        Double media = 0.0;

        try {
            nombre = nombreTextField.getText();
            apellidos = apellidosTextField.getText();
            ad = Double.parseDouble(adTextField.getText());
            sge = Double.parseDouble(sgeTextField.getText());
            di = Double.parseDouble(diTextField.getText());
            pmdm = Double.parseDouble(pmdmTextField.getText());
            psp = Double.parseDouble(pspTextField.getText());
            eie = Double.parseDouble(eieTextField.getText());
            hlc = Double.parseDouble(hlcTextField.getText());
            if (nombre != null && apellidos != null && Alumno.comprobacionNotas(ad, sge, di, pmdm, psp, eie, hlc)) {
                Alumno al = new Alumno(null, nombre, apellidos, ad, sge, di, pmdm, psp, eie, hlc);

                String sql = "INSERT INTO exameninterfaces.alumno (nombre, apellidos, ad, sge, di, pmdm, psp, eie, hlc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, al.getNombre());
                ps.setString(2, al.getApellidos());
                ps.setDouble(3, al.getAd());
                ps.setDouble(4, al.getSge());
                ps.setDouble(5, al.getDi());
                ps.setDouble(6, al.getPmdm());
                ps.setDouble(7, al.getPsp());
                ps.setDouble(8, al.getEie());
                ps.setDouble(9, al.getHlc());

                ps.executeUpdate();
                ps.close();

                tableViewMain.getItems().add(al);

                nombreTextField.clear();
                apellidosTextField.clear();
                adTextField.clear();
                sgeTextField.clear();
                diTextField.clear();
                pmdmTextField.clear();
                pspTextField.clear();
                eieTextField.clear();
                hlcTextField.clear();
            } else {
                System.out.println("Error, los datos introducidos no son correctos");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onDescargarButtonClick (ActionEvent actionEvent) throws JRException {
        HashMap hm = new HashMap();
        String report = "C:\\Users\\PabloPostigoMorales\\IdeaProjects\\ExamenRecuperacionDI_Trimestre2_Parte1\\src\\main\\resources\\main\\exameninterfaces\\calificaciones_alumnos.jasper";
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                report, hm, con
        );
        JRViewer viewer = new JRViewer(jasperPrint);
        JFrame frame = new JFrame("Blank_A4");
        frame.getContentPane().add(viewer);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);

        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("pdf/calificaciones.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();

        System.out.print("PDF Done!");
    }

    @FXML
    public void onSalirButtonClick (ActionEvent actionEvent) {
        System.exit(0);
    }
}