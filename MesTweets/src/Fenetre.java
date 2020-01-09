import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Fenetre extends Application {
	//i'm going to post a request on callmestudent 
	//on créé nos objet utile pour creer l'interface
	public BaseDeNombreTweet bar;
	public BaseDeUtilisateurs tab_uti;
	public BaseDeHashtag tab_hash;
	static BaseDeTweets bdt = new BaseDeTweets();
	
	
	public static void main(String[] args) throws Exception
	{
		System.setProperty( "file.encoding", "UTF-8" );
		System.out.println("Importation de la base en cours, veuillez patienter quelques instants...");
		bdt.initialise();
		//bdt.importation("foot.txt");
		System.out.println("finis !");
		
		launch(args);
	}
	
	
	public void start(Stage primaryStage)
	{

		Stage myStage = primaryStage;
		primaryStage.setTitle("Projet JAVA : Charlie Camenen et Clement Le Padellec");
		primaryStage.setScene(construitScene());
		primaryStage.sizeToScene();
		primaryStage.show();
	}

	
	public Scene construitScene() {

		GridPane grid = new GridPane();
		GridPane grid_contenue = new GridPane();
		MenuBar menuBar = new MenuBar();
		
		Button button_import_climat = new Button("Importer données climat");
		Button button_import_foot = new Button("Importer données foot");
		
		Menu menu_edition = new Menu("Edition");
		menuBar.getMenus().addAll(menu_edition);
		
		HBox hbox_menu = new HBox();
		hbox_menu.getChildren().add(menuBar);
		
		//On ajoute des sous menu
		MenuItem menuItem_tweet = new MenuItem("Tweet");
		MenuItem menuItem_utilisateur = new MenuItem("Utilisateur");
		MenuItem menuItem_hashtag = new MenuItem("Hashtags");
		menu_edition.getItems().addAll(menuItem_tweet, menuItem_utilisateur, menuItem_hashtag);
		
		HBox Hbox_filtre_graph = new HBox();
		HBox Hbox_filtre_users = new HBox();
		
		ChoiceBox<String> choiceBox_mois;
		ChoiceBox<String> choiceBox_semaine;
		ChoiceBox<String> choiceBox_jour; 
		choiceBox_mois = bdt.setChoiceBox("mois");
		choiceBox_semaine =  bdt.setChoiceBox("semaine");
		choiceBox_jour = bdt.setChoiceBox("jour");

		Hbox_filtre_graph.getChildren().add(choiceBox_mois);

		ChoiceBox<String> choiceBox_utilisateur = new ChoiceBox<>();
		choiceBox_utilisateur.getItems().addAll("Nombre de tweet","Nombre de mentions","Nombre de retweet");
		choiceBox_utilisateur.setValue("Nombre de tweet");
		Hbox_filtre_users.getChildren().clear();
		Hbox_filtre_users.getChildren().add(choiceBox_utilisateur);
		
		// barchart
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Temps");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Nombre de tweet");

		BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
		
		graph(barChart);
		
		
		
		choiceBox_mois.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent m) {
				if (Hbox_filtre_graph.getChildren().contains(choiceBox_semaine)) {
					choiceBox_semaine.setValue("Aucun");
					Hbox_filtre_graph.getChildren().remove(choiceBox_jour);
				} else {
					Hbox_filtre_graph.getChildren().add(choiceBox_semaine);
					choiceBox_semaine.setValue("Aucun");
				}
				
				//ici on applique les filtres
				bdt.setF_jour("Aucun");
				bdt.setF_semaine("Aucun");
				bdt.setF_mois(choiceBox_mois.getValue());
				//ici on crÃƒÂ©ÃƒÂ© l'objet barchart et on affiche le graphiques
				graph(barChart);
			}
		});

		choiceBox_semaine.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent s) {

				if (Hbox_filtre_graph.getChildren().contains(choiceBox_jour)) {
					choiceBox_jour.setValue("Aucun");
				} else {
					Hbox_filtre_graph.getChildren().add(choiceBox_jour);
					choiceBox_jour.setValue("Aucun");
				}
				bdt.setF_jour("Aucun");
				bdt.setF_semaine(choiceBox_semaine.getValue());
				graph(barChart);
			}
		});

		
		choiceBox_jour.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent j) {
				bdt.setF_jour(choiceBox_jour.getValue());
				graph(barChart);
			}
		});
		
		
		button_import_foot.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent j) {
				bdt.initialise();
				try {
					bdt.importation("foot.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				Hbox_filtre_graph.getChildren().addAll(choiceBox_mois);
				
				grid_contenue.getChildren().clear();

				GridPane.setConstraints(Hbox_filtre_graph, 0, 0);
				grid_contenue.getChildren().add(Hbox_filtre_graph);

				GridPane.setConstraints(barChart, 0, 1);
				grid_contenue.getChildren().add(barChart);
			}
		});
		
		button_import_climat.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent j) {
				bdt.initialise();
				try {
					bdt.importation("climat.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				Hbox_filtre_graph.getChildren().addAll(choiceBox_mois);
				grid_contenue.getChildren().clear();

				GridPane.setConstraints(Hbox_filtre_graph, 0, 0);
				grid_contenue.getChildren().add(Hbox_filtre_graph);

				GridPane.setConstraints(barChart, 0, 1);
				grid_contenue.getChildren().add(barChart);
			}
		});
		
		
		/*************************** Tableau ********************************/
		

		TableView<utilisateur> tableview_userTwitter = new TableView<>();

		tableau_utilisateur(tableview_userTwitter,choiceBox_utilisateur.getValue());
				
		choiceBox_utilisateur.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent j) {
				grid_contenue.getChildren().clear();
				tableau_utilisateur(tableview_userTwitter, choiceBox_utilisateur.getValue());
				
				GridPane.setConstraints(Hbox_filtre_users, 0, 0);
				grid_contenue.getChildren().add(Hbox_filtre_users);
				GridPane.setConstraints(tableview_userTwitter, 0, 1);
				grid_contenue.getChildren().add(tableview_userTwitter);
			}
		});

		TableView<hashtag> tableview_hashtag = new TableView<>();

		tableau_hashtag(tableview_hashtag);
				

		/************************* MENU *************************/


		menuItem_tweet.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent me) {
				grid_contenue.getChildren().clear();

				GridPane.setConstraints(Hbox_filtre_graph, 0, 0);
				grid_contenue.getChildren().add(Hbox_filtre_graph);

				GridPane.setConstraints(barChart, 0, 1);
				grid_contenue.getChildren().add(barChart);

			}
		});

		menuItem_utilisateur.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent me) {
				grid_contenue.getChildren().clear();

				GridPane.setConstraints(Hbox_filtre_users, 0, 0);
				grid_contenue.getChildren().add(Hbox_filtre_users);

				GridPane.setConstraints(tableview_userTwitter, 0, 1);
				grid_contenue.getChildren().add(tableview_userTwitter);
			}
		});

		menuItem_hashtag.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent me) {
				
				grid_contenue.getChildren().clear();
				GridPane.setConstraints(tableview_hashtag, 0, 0);
				grid_contenue.getChildren().add(tableview_hashtag);
			}
		});

		/********************* Affichage de l'interface *********************/
		
		GridPane.setConstraints(button_import_climat, 0, 0);
		GridPane.setConstraints(button_import_foot, 0, 1);
		grid_contenue.getChildren().addAll(button_import_climat,button_import_foot);


		//On affiche l'entete et le contenue
		GridPane.setConstraints(hbox_menu, 0, 0);
		GridPane.setConstraints(grid_contenue, 0, 1);
		grid.getChildren().addAll(hbox_menu, grid_contenue);

		//root prend tout les element de grid
		StackPane root = new StackPane();
		root.getChildren().add(grid);

		//la fenetre avec ses dimensions
		Scene scene = new Scene(root, 600, 300);

		return scene;
	}

	
	/*Fonction pour les graphiques*/
	public void graph(BarChart<String, Number> barChart) {
		bar = bdt.creer_donnee_barchart();
		/*Objet permettant d'alimenter les donnÃƒÂ©es du barchart*/
		barChart.getData().clear();
		barChart.getData().add(bar.remplir_donnee());
		
		barChart.setTitle(bar.getTitle_chart());
	}


	/*Fonction pour les tableaux*/
	@SuppressWarnings("unchecked")
	public void tableau_utilisateur(TableView<utilisateur> tableview, String option_de_tri){

		tableview.getItems().clear();
		tableview.getColumns().clear();
		TableColumn<utilisateur, String> pseudoColumn = new TableColumn<>("Utilisateur");
		pseudoColumn.setCellValueFactory(new PropertyValueFactory<>("u_pseudo_users"));
		//modifier le titre dynamiquement
		TableColumn<utilisateur, Integer> nombre_retweet = new TableColumn<>("Nombre d'occurence");
		nombre_retweet.setCellValueFactory(new PropertyValueFactory<>("u_nombre_tweet"));
		//modifier le titre dynamiquement
		
		tab_uti = bdt.creer_donnee_tableau_utilisateurs(option_de_tri);
		tableview.setItems(tab_uti.ajouteUtilisateur_tableau());
		tableview.getColumns().addAll(pseudoColumn, nombre_retweet);

	}

	@SuppressWarnings("unchecked")
	public void tableau_hashtag(TableView<hashtag> tableview) {
		tableview.getItems().clear();
		tableview.getColumns().clear();
		TableColumn<hashtag, String> libele_hashtag = new TableColumn<>("Hashtag");
		libele_hashtag.setCellValueFactory(new PropertyValueFactory<>("h_libele"));
		//modifier le titre dynamiquement
		TableColumn<hashtag, Integer> nombre_hashtag = new TableColumn<>("Nombre d'occurence");
		nombre_hashtag.setCellValueFactory(new PropertyValueFactory<>("h_nombre_occurence"));
		//modifier le titre dynamiquement
		
		tab_hash = bdt.rempli_bdh(bdt.intermediaire_rempli_bdh());
		tableview.setItems(tab_hash.ajouteHashtag_tableau());
		tableview.getColumns().addAll(libele_hashtag, nombre_hashtag);
	}
}