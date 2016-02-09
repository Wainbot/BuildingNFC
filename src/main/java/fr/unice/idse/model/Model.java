package fr.unice.idse.model;

public class Model {
	private static Model model = null;
	private App app;
	
	private Model() {
		app = new App();
	}
	
	public static Model getInstance() {
		if(model == null)
			model = new Model();
		return model;
	}
	
	public App getApp() {
		return app;
	}
}
