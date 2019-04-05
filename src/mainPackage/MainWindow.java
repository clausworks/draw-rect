/*
 * Nicholas Brunet and Jonathan Smith
 * CIS 111
 * 
 * Lab 7
 * Due 4/11/19
 * 
 * Description: The user inputs the width and height of a rectangle. The
 * program graphs the rectangle on an xy-coordinate plane to scale, and
 * calculates the perimeter and area.
 */




package mainPackage;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class MainWindow {

	private JFrame frame;
	private JButton drawButton;
	//	private JPanel canvasPanel;
	Canvas graph;
	private JSpinner widthSpinner;
	private JSpinner heightSpinner;
	private JLabel area;
	private JLabel perimeter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100,100,450,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);

		JLabel lblEnterWidth = new JLabel("Enter width:");
		GridBagConstraints gbc_lblEnterWidth = new GridBagConstraints();
		gbc_lblEnterWidth.anchor = GridBagConstraints.EAST;
		gbc_lblEnterWidth.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterWidth.gridx = 0;
		gbc_lblEnterWidth.gridy = 0;
		frame.getContentPane().add(lblEnterWidth, gbc_lblEnterWidth);

		widthSpinner = new JSpinner(new SpinnerNumberModel(100, 10, graph.GRAPH_WIDTH, 10));
		GridBagConstraints gbc_widthSpinner = new GridBagConstraints();
		gbc_widthSpinner.anchor = GridBagConstraints.WEST;
		gbc_widthSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_widthSpinner.gridx = 1;
		gbc_widthSpinner.gridy = 0;
		frame.getContentPane().add(widthSpinner, gbc_widthSpinner);

		JLabel lblEnterHeight = new JLabel("Enter height:");
		GridBagConstraints gbc_lblEnterHeight = new GridBagConstraints();
		gbc_lblEnterHeight.anchor = GridBagConstraints.EAST;
		gbc_lblEnterHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterHeight.gridx = 0;
		gbc_lblEnterHeight.gridy = 1;
		frame.getContentPane().add(lblEnterHeight, gbc_lblEnterHeight);

		heightSpinner = new JSpinner(new SpinnerNumberModel(100, 10, graph.GRAPH_HEIGHT, 10));
		GridBagConstraints gbc_heightSpinner = new GridBagConstraints();
		gbc_heightSpinner.anchor = GridBagConstraints.WEST;
		gbc_heightSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_heightSpinner.gridx = 1;
		gbc_heightSpinner.gridy = 1;
		frame.getContentPane().add(heightSpinner, gbc_heightSpinner);



		drawButton = new JButton("Draw");
		drawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int width = (Integer)widthSpinner.getValue();
				int height  = (Integer)heightSpinner.getValue();

				// reset canvas
				graph.clearCanvas();
				graph.initializeCanvas();
				graph.repaint();

				// draw rectangle
				graph.canvas.setStroke(new java.awt.BasicStroke(0));
				graph.canvas.setColor(Color.ORANGE);
				graph.drawRect(new CartesianCoordinate(0,0), new CartesianCoordinate(width,height));

				// update canvas
				graph.repaint();
				graph.canvas.dispose();

				// calculate & display perimeter and area
				perimeter.setText("Perimeter: " + GetPerimeter(height, width));
				area.setText("Area: " + GetArea(height, width));

			}
		});
		GridBagConstraints gbc_drawButton = new GridBagConstraints();
		gbc_drawButton.gridwidth = 2;
		gbc_drawButton.insets = new Insets(0, 0, 5, 0);
		gbc_drawButton.gridx = 0;
		gbc_drawButton.gridy = 2;
		frame.getContentPane().add(drawButton, gbc_drawButton);
		GridBagConstraints gbc_Canvas = new GridBagConstraints();
		gbc_Canvas.gridwidth = 2;
		gbc_Canvas.fill = GridBagConstraints.BOTH;
		gbc_Canvas.gridx = 0;
		gbc_Canvas.gridy = 3;

		graph = new Canvas();
		GridBagConstraints gbc_graphCanvas = new GridBagConstraints();
		gbc_graphCanvas.anchor = GridBagConstraints.NORTHWEST;
		gbc_graphCanvas.gridwidth = 2;
		gbc_graphCanvas.insets = new Insets(20, 25, 25, 25);
		gbc_graphCanvas.gridx = 0;
		gbc_graphCanvas.gridy = 3;
		frame.getContentPane().add(graph, gbc_graphCanvas);

		perimeter = new JLabel("Perimeter:");
		GridBagConstraints gbc_perimeter = new GridBagConstraints();
		gbc_perimeter.anchor = GridBagConstraints.WEST;
		gbc_perimeter.insets = new Insets(0, 25, 5, 5);
		gbc_perimeter.gridx = 0;
		gbc_perimeter.gridy = 5;
		frame.getContentPane().add(perimeter, gbc_perimeter);

		area = new JLabel("Area:");
		GridBagConstraints gbc_area = new GridBagConstraints();
		gbc_area.anchor = GridBagConstraints.WEST;
		gbc_area.insets = new Insets(0, 25, 10, 5);
		gbc_area.gridx = 0;
		gbc_area.gridy = 6;
		frame.getContentPane().add(area, gbc_area);
		//		graph.initializeCanvas();


	}




	public static int GetPerimeter(int length, int width)
	{
		int perimeter = (length + width) * 2;
		return perimeter;
	}

	public static int GetArea(int length, int width)
	{

		int area = length * width;
		return area;
	}


}
