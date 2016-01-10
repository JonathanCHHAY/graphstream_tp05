package boite;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.graphstream.graph.Graph;

import graphstream.TypeGraph;

public class BoiteGenCycle extends AbstractBoiteGenGraph{

public BoiteGenCycle(JFrame own) {
	
		// On bloque la fenêtre principale
		super(own);

		setTitle("Cycle");
		creationElem();
		placementElem();
		redimFen();
		setListeners();
	}
	
	@Override
	public int[] showDialog() {
		
		//System.out.println("show");
		this.setVisible(true);
		
		super.params = new int[2];
		params[0] = Integer.parseInt(tf1.getText());
		params[1] = -1;
		
		return params;
	}

	@Override
		public void creationElem() {
			lab1 = new JLabel("Nombre de sommets ");
			
			tf1 = new JTextField();	
			tf1.setColumns(10);
			
			btAnnuler = new JButton("Annuler");
			btValider = new JButton("Valider");
		}
	
	@Override
		public void placementElem() {
			this.setLayout(new GridBagLayout());
			GridBagConstraints cont = new GridBagConstraints();
			cont.fill = GridBagConstraints.CENTER;
			
			cont.gridx = 0;
			cont.gridy = 0;
			this.add(lab1, cont);
			
			cont.gridx = 1;
			cont.gridy = 0;
			this.add(tf1, cont);
			
			cont.gridx = 0;
			cont.gridy = 1;
			this.add(btAnnuler, cont);
			
			cont.gridx = 1;
			cont.gridy = 1;
			this.add(btValider, cont);
		}
	
	@Override
		public void redimFen() {
			this.pack();
		}
	
	@Override
		public void setListeners() {
		
			btValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				setVisible(false);				
			}
		});
			
		}
}
