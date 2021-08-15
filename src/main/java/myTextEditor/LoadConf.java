package myTextEditor;

import java.awt.Color;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;

import javax.swing.JOptionPane;

import org.yaml.snakeyaml.Yaml;

public class LoadConf {
	String fileName;
	Map<String, Object> allValues;
	Map<String, Object> font;
	Map<String, Object> menu;

	@SuppressWarnings("unchecked")
	public LoadConf(String fileName) {
		super();
		this.fileName = fileName;
		Yaml yaml = new Yaml();
		try {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
		allValues = yaml.load(inputStream);
		font = (Map<String, Object>) allValues.get("font");
		menu = (Map<String, Object>) allValues.get("menu");
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Unable to load configuration file.");
		}
	}

	public String loadTitle() {
		return (String) allValues.get("title");
	}

	public String loadWelcome() {
		return (String) allValues.get("welcome_content");
	}

	public Color loadMenuFColor() {
		String colorName = (String) menu.get("menu_foreground_color");
		Color color;
		try {
			Field field = Class.forName("java.awt.Color").getField(colorName);
			color = (Color) field.get(null);
		} catch (Exception e) {
			color = null; // Not defined
		}
		return color;
	}

	public Color loadMenuBColor() {
		String colorName = (String) menu.get("menu_background_color");
		Color color;
		try {
			Field field = Class.forName("java.awt.Color").getField(colorName);
			color = (Color) field.get(null);
		} catch (Exception e) {
			color = null; // Not defined
		}
		return color;
	}

	public String loadFontName() {
		return (String) font.get("font_name");
	}

	public int loadFontSize() {
		return (int) font.get("font_size");
	}

	public Color loadFontColor() {
		String colorName = (String) font.get("font_color");
		Color color;
		try {
			Field field = Class.forName("java.awt.Color").getField(colorName);
			color = (Color) field.get(null);
		} catch (Exception e) {
			color = null; // Not defined
		}
		return color;
	}
}
