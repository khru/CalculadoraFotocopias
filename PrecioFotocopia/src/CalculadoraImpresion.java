import java.awt.*;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.*;
import java.awt.event.ActionEvent;

public class CalculadoraImpresion 
{

	private JFrame frmCalcularElPrecio;
	private JTextField textField_1;
	private JTextField textField_2;
	private Double total;
	private JTextField textField;

	/**
	 * Lanzamos la aplicación.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					CalculadoraImpresion window = new CalculadoraImpresion();
					window.frmCalcularElPrecio.setVisible(true);
				}
				catch(NumberFormatException error_formato)
				{
					error_formato.printStackTrace();
				}
				catch (Exception error_fatal) 
				{
					error_fatal.printStackTrace();
				}
			}
		}
		);
	}

	/**
	 * Create the application.
	 */
	public CalculadoraImpresion() throws NumberFormatException, NumberFormatException
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		//----------------------------------------------------------------------------------------------------------------------------------------------
		//Inicializamos el frame
		frmCalcularElPrecio = new JFrame();
		frmCalcularElPrecio.setResizable(false);
		frmCalcularElPrecio.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmCalcularElPrecio.setTitle("Calcula Precio Fotocopias");
		frmCalcularElPrecio.setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\Google Drive\\dise\u00F1o web\\iconos\\Euro-Coin-icon.png"));
		frmCalcularElPrecio.setAlwaysOnTop(true);
		frmCalcularElPrecio.setBounds(100, 100, 349, 242);
		frmCalcularElPrecio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalcularElPrecio.getContentPane().setLayout(null);
		
		 FocusListener highlighter = new FocusListener() 
		 {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				e.getComponent().setBackground(Color.GREEN);
			}

			@Override
			public void focusLost(FocusEvent e) {
				  e.getComponent().setBackground(UIManager.getColor("TextField.background"));
			}

         };
		//----------------------------------------------------------------------------------------------------------------------------------------------
		//Creamos la primera etiqueta
		JLabel label = new JLabel("Precio de la fotocopia:");
		label.setBounds(24, 23, 140, 14);
		frmCalcularElPrecio.getContentPane().add(label);
		
		//Cremaos un campo de TTexto con formato, el cual nos permite hasta 2 decimales.
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##.##"))));
		formattedTextField.setToolTipText("Este texto admite número con 2 decimales");
		formattedTextField.setBounds(155, 20, 110, 20);
		formattedTextField.addFocusListener(highlighter);
		frmCalcularElPrecio.getContentPane().add(formattedTextField);
		//----------------------------------------------------------------------------------------------------------------------------------------------
		
		JLabel lable1 = new JLabel("Número de p\u00E1ginas:");
		lable1.setBounds(24, 48, 121, 14);
		frmCalcularElPrecio.getContentPane().add(lable1);
		
		
		JFormattedTextField formattedTextField1 = new JFormattedTextField();
		formattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
		formattedTextField1.setToolTipText("Este texto solo admite números enteros");
		formattedTextField1.setBounds(155, 48, 110, 20);
		formattedTextField1.addFocusListener(highlighter);
		frmCalcularElPrecio.getContentPane().add(formattedTextField1);
		
		//----------------------------------------------------------------------------------------------------------------------------------------------
		JLabel label2 = new JLabel("Precio de la Encuadernación:");
		label2.setBounds(24, 79, 189, 14);
		frmCalcularElPrecio.getContentPane().add(label2);
		
		JFormattedTextField formattedTextField2 = new JFormattedTextField();
		formattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##.##"))));
		formattedTextField2.setToolTipText("Este texto admite número con 2 decimales");
		formattedTextField2.setBounds(199, 76, 110, 20);
		formattedTextField2.addFocusListener(highlighter);
		frmCalcularElPrecio.getContentPane().add(formattedTextField2);
		

		//----------------------------------------------------------------------------------------------------------------------------------------------
		
		//Etiqueta del Total
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setForeground(Color.RED);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTotal.setBounds(45, 164, 69, 14);
		frmCalcularElPrecio.getContentPane().add(lblTotal);
		
		textField = new JTextField();
		textField.setBounds(103, 164, 130, 20);
		frmCalcularElPrecio.getContentPane().add(textField);
		textField.setColumns(10);
		//----------------------------------------------------------------------------------------------------------------------------------------------
		
		//Creamos un checkBox, del cual sabremos si debemos calcular también el precio de la encuadernación o simplemente ignoraremos el precio de la encuadernación
		JCheckBox chckbxSi = new JCheckBox("Encuadernación");
		chckbxSi.setBounds(18, 100, 158, 23);
		frmCalcularElPrecio.getContentPane().add(chckbxSi);
		
		
		//Cremaos el Botón
		JButton btnCalcular = new JButton("Calcular");
		//Preparamos un Listener para el evento
		btnCalcular.addActionListener(new ActionListener() 
		{
			//Cuando el evento ocurre
			public void actionPerformed(ActionEvent arg0) throws NumberFormatException, NumberFormatException
			{
				//Puesto que debemos convertir el texto que nos entra por los JFormattedField para poder convertirlo a tipos numericos con los cuales poder hacer operaciones
				if(chckbxSi.isSelected())
				{
					if(formattedTextField.getText().equals("") || formattedTextField1.getText().equals("") || formattedTextField2.getText().equals(""))
					{
						System.out.println("Falta algun parametro");
					}
					else
					{

						Double precioFotocopia = new Double(formattedTextField.getText().replace(",", "."));
						Double nPaginas = new Double(formattedTextField1.getText());
						Double precioEncuadernacion = new Double(formattedTextField2.getText().replace(",", "."));
						
						total = ((precioFotocopia*nPaginas)+precioEncuadernacion);
						if(total > Integer.MAX_VALUE || total < Integer.MIN_VALUE)
						{
							textField.setBackground(Color.RED);
							textField.setText("ERROR");
							JOptionPane.showMessageDialog(null, "Alguna de las cantidades es demasiado elevadas", "Cantidad erronea", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							textField.setBackground(Color.GREEN);;
							textField.setText(total.floatValue()+"€");
						}
					}
				}
				else
				{
					if(formattedTextField.getText().equals("") || formattedTextField1.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Para poder calcular el precio de la impresión es necesario\nque comprete como minimo los campos necesarios", "Número de parametros Erroneo", JOptionPane.WARNING_MESSAGE);
					}
					else
					{

						double precioFotocopia = new Double(formattedTextField.getText().replace(",", "."));
						double nPaginas = new Double(formattedTextField1.getText());
						total = (precioFotocopia*nPaginas);
						if(total > Double.MAX_VALUE || total < Double.MIN_VALUE)
						{
							System.out.println("El Precio es incalculable");
						}
						else
						{
							System.out.println("El precio de la impresión es de: " + total);
						}
					}
					
				}
			}
		});
		btnCalcular.setBounds(117, 130, 89, 23);
		frmCalcularElPrecio.getContentPane().add(btnCalcular);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				JOptionPane.showMessageDialog(null, "En esta aplicación, para utilizar números decimales\nsu utilizan \",\"", "Ayuda", JOptionPane.INFORMATION_MESSAGE );

			}
		});
		btnNewButton.setIcon(new ImageIcon("F:\\Google Drive\\dise\u00F1o web\\iconos\\1438727020_Info.png"));
		btnNewButton.setBounds(294, 11, 29, 23);
		frmCalcularElPrecio.getContentPane().add(btnNewButton);
		
		
		
		
		
		
	}
}
