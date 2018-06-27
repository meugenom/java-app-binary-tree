package application;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import application.config.Config;
import application.extended.Line;
import application.extended.TreeNodeExtended;
import application.model.TreeDataModel;


public class ExportSVGController {

	private String out = "";
	private String outLines = "";

	public void exportSVG() throws IOException {
		writeFile();
	}

	public void writeFile() throws IOException {

		getOutput();

		String fileName = "test.svg";
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(out);
		writer.close();

		// Start browser
		// https://stackoverflow.com/questions/5226212/how-to-open-the-default-webbrowser-using-java/28807079

		String url = fileName;

		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec("xdg-open " + url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public String getOutput() {
		out += Config.xmlHead;
		// here is my content
		getContent();
		out += outLines;
		out += Config.xmlEnd;
		return out;

	}

	public void getContent() {
		TreeDataModel model = new TreeDataModel();
		TreeNodeExtended<Line> node = new TreeNodeExtended<Line>();
		node = model.start();
		fillContext(node);
	}

	private void fillContext(TreeNodeExtended<Line> node) {

		if (node.right != null) {
			if (node.parent == null) {
				fillContext((TreeNodeExtended<Line>) node.right);
			} else {
				putLineContext(node);
				fillContext((TreeNodeExtended<Line>) node.right);
			}
		}

		if (node.left != null) {
			if (node.parent == null) {
				fillContext((TreeNodeExtended<Line>) node.left);
			} else {
				putLineContext(node);
				fillContext((TreeNodeExtended<Line>) node.left);
			}
		}
		// last Nodes
		if (node.right == null && node.left == null) {
			putLineContext(node);
		}
		// first node
		if (node.parent == null) {
			putLineContext(node);
		}
	}

	private void putLineContext(TreeNodeExtended<Line> node) {

		/*
		 * "<line x1=\"0\" y1=\"0\" x2=\"200\" y2=\"200\" style=\"stroke:rgb(255,0,0);stroke-width:1\" />"
		 */
		
		// AB
		outLines += String.format(Config.xmlStringFormat + Config.xmlColors +  Config.xmlStrokeWidth + "\" />",				
				node.AB.start.getX(), node.AB.start.getY(), node.AB.end.getX(), node.AB.end.getY());

		// BC
		outLines += String.format(Config.xmlStringFormat + Config.xmlColors +  Config.xmlStrokeWidth + "\" />",
				node.BC.start.getX(), node.BC.start.getY(), node.BC.end.getX(), node.BC.end.getY());

		// CD
		outLines += String.format(Config.xmlStringFormat + Config.xmlColors +  Config.xmlStrokeWidth + "\" />",
				node.CD.start.getX(), node.CD.start.getY(), node.CD.end.getX(), node.CD.end.getY());

		// DA
		outLines += String.format(Config.xmlStringFormat + Config.xmlColors +  Config.xmlStrokeWidth + "\" />",
				node.DA.start.getX(), node.DA.start.getY(), node.DA.end.getX(), node.DA.end.getY());

	}

}
