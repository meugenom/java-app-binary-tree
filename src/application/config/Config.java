package application.config;

public final class Config {

	public static String ApplicationName = "Pythagoras Tree ";
	public static String Version = "v. 0.3";

	// dimension of scene
	public static int SceneWidth = 900; // as BorderPane
	public static int SceneHeight = 400;

	// dimension of canvas
	public static double CanvasWidth = 600.0;
	public static double CanvasHeight = 400.0;

	// quadrants sides a,b
	public static double a = 80.0;
	public static double b = 120.0;

	public static double pointAX = 250.0;
	public static double pointAY = 380.0;

	// borderPane regions
	public static double leftBorderPaneRegion = 300.0;

	// for future realization
	public static int Iteration = 1;
	public static boolean Random = false;
	public static int Delay = 1;

	// orientation of vector
	public static double ConvertAngle = Math.PI / 180;
	public static double Angle = 60.0;

	// xml exports tags
	public static String xmlHead = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n"
			+ "<svg version = \"1.1\"\r\n" + "     baseProfile=\"full\"\r\n"
			+ "     xmlns = \"http://www.w3.org/2000/svg\" \r\n"
			+ "     xmlns:xlink = \"http://www.w3.org/1999/xlink\"\r\n"
			+ "     xmlns:ev = \"http://www.w3.org/2001/xml-events\"\r\n"
			+ "     height = \"400px\"  width = \"600px\">\r\n"
			+ "     <rect x=\"0\" y=\"0\" width=\"600\" height=\"400\" \r\n"
			+ "          fill=\"none\" stroke=\"black\" stroke-width=\"1px\" stroke-opacity=\"0.5\"/>\r\n"
			+ "     <g fill-opacity=\"0.6\" stroke=\"black\" stroke-width=\"0.5px\">\r\n";

	public static String xmlEnd = "</g>\r\n" + "</svg>";
	public static String xmlStringFormat = "<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\" style=\"stroke:";
	public static String xmlColors = "rgb(90, 30, 243);";
	public static String xmlStrokeWidth = "stroke-width:1";

}