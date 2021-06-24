import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main extends JFrame {
	public static JFrame frame;
	static String bomdia = "Bom dia";
	static Color c = new Color(100, 149, 237);
	static Color c2 = new Color(100 + 20, 149 + 20, 237 + 10);
	static ArrayList<String> elements = new ArrayList<>();
	static ArrayList<String> elements2 = new ArrayList<>();
	static ArrayList<String> elements3 = new ArrayList<>();

	private static Point point = new Point();
	private static BorderLayout b = new BorderLayout();
	private static GridLayout g = new GridLayout(0, 1);
	private static GridLayout g2 = new GridLayout(1, 0);
	private static JPanel p1 = new JPanel();
	private static JPanel p2 = new JPanel();
	private static JPanel p3 = new JPanel();
	private static JPanel p4 = new JPanel();
	private static JPanel p5 = new JPanel();
	private static JPanel p6 = new JPanel();
	private static JPanel p7 = new JPanel();
	private static JPanel p8 = new JPanel();
	private static JPanel p9 = new JPanel();
	private static JPanel p10 = new JPanel();
	private static JPanel p11 = new JPanel();

	private static JLabel l1 = new JLabel("  Jaguarão");
	private static JLabel l2 = new JLabel("Data");
	private static JLabel l3 = new JLabel("  Noticias De Hoje:");
	private static JLabel l4 = new JLabel("Bolsa:");

	private static JLabel max = new JLabel("");
	private static JLabel min = new JLabel("");

	private static JLabel data = new JLabel("");

	private static JLabel dolar = new JLabel("");

	private static JLabel umidade = new JLabel("");
	private static JLabel pressao = new JLabel("");
	private static JLabel ventos = new JLabel("");

	private static JLabel b1 = new JLabel("");
	private static JLabel b2 = new JLabel("");
	private static JLabel b3 = new JLabel("");

	private static JLabel n = new JLabel("");
	private static JLabel n1 = new JLabel("");
	private static JLabel n2 = new JLabel("");
	private static JLabel n3 = new JLabel("");
	private static JLabel n4 = new JLabel("");

	static Font f = new Font("Serif", Font.BOLD, 30);
	static Font f2 = new Font("Serif", Font.BOLD, 15);
	static Font f3 = new Font("Serif", Font.BOLD, 25);

	public static void main(String[] args) throws IOException {

		frame();

	}

	public static void frame() throws IOException {
		Dolar();
		Data();
		Temperatura();
		Not();
		Prev();
		Bolsa();

		frame = new JFrame("");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		JButton button = new JButton("Fechar");
		button.addActionListener(e -> System.exit(0));
		frame.addMouseListener((MouseListener) new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				point.x = e.getX();
				point.y = e.getY();
			}
		});
		frame.addMouseMotionListener((MouseMotionListener) new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point p = frame.getLocation();
				frame.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
			}
		});
		frame.setSize(600, 600);
		frame.setLayout(b);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(button, BorderLayout.SOUTH);
		frame.getContentPane().add(p4);
		p4.setLayout(g);
		p4.add(p2);
		p4.add(p3);
		p2.setLayout(g2);
		p2.add(p5);
		p5.setLayout(new GridLayout(8, 0));
		p5.add(new JLabel(""));
		p5.add(l1);
		p5.add(new JLabel(""));
		p5.add(max);
		p5.add(min);
		p5.add(umidade);
		p5.add(pressao);
		p5.add(ventos);

		data.setFont(f3);
		p6.add(new JLabel(""));
		p6.add(data);
		p6.add(dolar);
		p6.add(new JLabel(""));
		p6.add(l4);
		p6.add(b1);
		p6.add(b2);
		p6.add(b3);
		l4.setFont(f);

		p6.setLayout(new GridLayout(8, 0));
		p2.add(p6);
		p5.setBackground(c);
		p6.setBackground(c);
		p3.setBackground(c);
		p3.add(l3);
		p3.setLayout(new GridLayout(6, 0));
		p3.add(p7);
		p3.add(p8);
		p3.add(p9);
		p3.add(p10);
		p3.add(p11);
		p7.add(n);
		p8.add(n1);
		p9.add(n2);
		p10.add(n3);
		p11.add(n4);

		p7.setBackground(c2);
		p8.setBackground(c2);
		p9.setBackground(c2);
		p10.setBackground(c2);
		p11.setBackground(c2);

		l1.setFont(f);
		l3.setFont(f3);

		frame.setVisible(true);

	}

	public static void Data() throws IOException {

		Document doc = Jsoup.connect("https://www.calendarr.com/brasil/").get();

		Elements elementosTag = doc.select("b");

		for (Element el : elementosTag)

			data = new JLabel(elementosTag.text());

	}

	public static void Temperatura() throws IOException {

		Document doc = Jsoup.connect("https://www.climatempo.com.br/previsao-do-tempo/cidade/1397/jaguarao-rs").get();
		Element tag = doc.getElementById("mainContent");
		Elements inputElements = tag.getElementsByTag("span");

		for (Element inputElement : inputElements) {

			min = new JLabel("  Mínima hoje: " + inputElements.get(17).text() + "C");
			min.setFont(f2);
			max = new JLabel("  Máxima hoje: " + inputElements.get(18).text() + "C");
			max.setFont(f2);

		}
	}

	public static void Dolar() throws IOException {

		Document doc = Jsoup.connect("https://dolarhoje.com/").get();
		Element newsHeadlines = doc.select("#nacional").first();
		String dolarHoje = newsHeadlines.attr("value");
		dolar = new JLabel("Dólar hoje: R$ " + dolarHoje);
		dolar.setFont(f2);
	}

	public static void Not() throws IOException {

		Document doc = Jsoup.connect("https://www.folha.uol.com.br").get();

		Elements resultLinks = doc.getElementsByClass("c-headline__content").select("a > h2"); // direct a after h3

		for (Element el : resultLinks) {

			elements.add(el.html());
			// System.out.println(" "+elements.get(0));

		}
		n = new JLabel("- " + elements.get(0) + ".");
		n.setFont(f2);
		n1 = new JLabel("- " + elements.get(1) + ".");
		n1.setFont(f2);
		n2 = new JLabel("- " + elements.get(2) + ".");
		n2.setFont(f2);
		n3 = new JLabel("- " + elements.get(3) + ".");
		n3.setFont(f2);
		n4 = new JLabel("- " + elements.get(4) + ".");
		n4.setFont(f2);
	}

	public static void Prev() throws IOException {
		Document doc = Jsoup.connect("http://tempo.clic.com.br/rs/jaguarao").get();

		Elements resultLinks = doc.getElementsByClass("weatherDetail inliner mainItem").select("span"); // direct a
																										// after h3

		for (Element el : resultLinks) {

			elements2.add(el.html());

		}
		umidade = new JLabel("  Umidade: " + elements2.get(1));
		umidade.setFont(f2);
		pressao = new JLabel("  Pressão: " + elements2.get(3));
		pressao.setFont(f2);
		ventos = new JLabel("  Ventos: " + elements2.get(5));
		ventos.setFont(f2);

	}

	public static void Bolsa() throws IOException {
		Document doc = Jsoup.connect("https://economia.uol.com.br/cotacoes/bolsas/").get();

		Elements resultLinks = doc.getElementsByClass("col-lg-8 col-md-8 col-sm-8 col-xs-8").select("a"); // direct a
																											// after h3

		for (Element el : resultLinks) {

			elements3.add(el.html());

		}
		b1 = new JLabel(elements3.get(0) + " " + elements3.get(1));
		b1.setFont(f2);
		b2 = new JLabel(elements3.get(3) + " " + elements3.get(4));
		b2.setFont(f2);
		b3 = new JLabel(elements3.get(6) + " " + elements3.get(7));
		b3.setFont(f2);

	}

}
