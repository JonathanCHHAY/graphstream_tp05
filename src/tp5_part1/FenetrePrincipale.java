/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5_part1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.graphstream.graph.Graph;
import org.graphstream.stream.file.FileSinkImages;
import org.graphstream.ui.swingViewer.View;
import org.graphstream.ui.swingViewer.Viewer;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.PngImage;



/**
 *
 * @author brice.effantin
 */
public class FenetrePrincipale extends JFrame {

    Graph grapheCourant;
    PanneauVisu panoVisu;
    PanneauGraphe panoGraphe;
    PanneauActions panoActions;
    PanneauAlgorithmes panoAlgo;

    public FenetrePrincipale() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panoVisu = new PanneauVisu();
        panoGraphe = new PanneauGraphe();
        panoActions = new PanneauActions();
        panoAlgo = new PanneauAlgorithmes();

        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill = GridBagConstraints.BOTH;
        cont.weighty = 1.0;
        cont.gridx = 0;
        cont.gridy = 0;
        cont.gridheight = 3;
        this.getContentPane().add(panoVisu, cont);
        cont.gridx = 1;
        cont.gridheight = 1;
        this.getContentPane().add(panoGraphe, cont);
        cont.gridy = 1;
        this.getContentPane().add(panoActions, cont);
        cont.gridy = 2;
        this.getContentPane().add(panoAlgo, cont);

        this.pack();

    }

    public class PanneauVisu extends JPanel {

        final String styleSheet = "node {"
                + "size-mode: dyn-size;"
                + " fill-color: red;"
                + " size: 10px;"
                + " stroke-mode: plain;"
                + " stroke-color: black;"
                + " stroke-width: 1px;"
                + "}"
                + "node.important {"
                + " fill-color: red;"
                + " size: 30px;"
                + "}"
                + "edge {"
                + "text-alignment: along;"
                + "}"
                + "edge.notintree {"
                + "size:1px;"
                + "fill-color:gray;"
                + "} "
                + "edge.intree {size:3px;fill-color:blue;}";

        public Viewer graphViewer = null;
        public View graphView = null;

        public PanneauVisu() {
            this.setLayout(new BorderLayout());
            this.setPreferredSize(new Dimension(800, 600));
        }

        public void update() {
            if (grapheCourant != null) {
                try {
                    this.remove(graphViewer.getView(Viewer.DEFAULT_VIEW_ID));
                } catch (Exception ex) {
                }
                grapheCourant.addAttribute("ui.stylesheet", styleSheet);
                graphViewer = new Viewer(grapheCourant, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
                graphViewer.enableAutoLayout();
                graphView = graphViewer.addDefaultView(false);
                this.add(graphView);
                graphView.revalidate();
            }
        }
    }

    public class PanneauGraphe extends JPanel {

        public JComboBox<TypeGraphe> comboChoixGraphe = new JComboBox(new DefaultComboBoxModel());
        public JButton btGraphe = new JButton("Générer");
        public int args[];

        public PanneauGraphe() {
            this.setBorder(BorderFactory.createTitledBorder("Choix du graphe"));
            //remplissage du combo
            for (TypeGraphe x : TypeGraphe.values()) {
                ((DefaultComboBoxModel) comboChoixGraphe.getModel()).addElement(x);
            }
            //ajout des éléments graphiques
            this.setLayout(new GridBagLayout());
            GridBagConstraints cont = new GridBagConstraints();
            cont.gridx = cont.gridy = 0;
            this.add(comboChoixGraphe, cont);
            cont.gridx = 1;
            this.add(btGraphe, cont);
            //action bouton
            btGraphe.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    TypeGraphe type = (TypeGraphe) ((DefaultComboBoxModel) comboChoixGraphe.getModel()).getSelectedItem();
                    if (new BoiteParam(PanneauGraphe.this, type.toString(), type.getParam()).isActivee()) {
                        grapheCourant = GrapheFabrique.generer(type, args);
                        panoVisu.update();
                    }
                }
            });
        }

        //pour mettre à jour les données saisies dans la boite de dialogue
        public void maj(int args[]) {
            this.args = new int[args.length];
            for (int i = 0; i < args.length; i++) {
                this.args[i] = args[i];
            }
        }

    }

    
    
    public class PanneauActions extends JPanel {
    	

    	
    	public JButton btExport, btPondere;
    	
    	public PanneauActions() {
    		this.setBorder(BorderFactory.createTitledBorder("Actions"));
    		
    		btExport = new JButton("Export PDF");
    		btPondere = new JButton("Pondérer");
    		
    		this.add(btExport);
    		this.add(btPondere);
    		
    		setActionListeners();
		}
    	
    	public void setActionListeners() {
    		
    		btExport.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					String pngFile = genereImage();
					Document document = genererPdf();
					
				}
			});
    	}
    	

		// Pour créer un png depuis le graphe
    	public String genereImage() {
    		
    		String pngFile = new String("imageGenere.png");
			
			FileSinkImages pic = new FileSinkImages(FileSinkImages.OutputType.PNG,
			FileSinkImages.Resolutions.VGA);
			
			pic.setLayoutPolicy(FileSinkImages.LayoutPolicy.COMPUTED_FULLY_AT_NEW_IMAGE);
			try {
				pic.writeAll(grapheCourant, pngFile);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//pngFile est une variable de type String, grapheCourant est un Graph
			
			//pour créer une image qui sera ajoutable au pdf:
			Image convertBmp;
			try {
				convertBmp = PngImage.getImage(pngFile);
				
				//Redimensionner l’image
				int PAGE_LEFT_MARGIN = 0;
				int PAGE_RIGHT_MARGIN = 75;
				int PAGE_TOP_MARGIN = 0;
				int PAGE_BOTTOM_MARGIN = 0;
				convertBmp.scaleToFit(400, 300);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return pngFile;
    	}
    	
    	public Document genererPdf() {
    		
    		Document document = new Document(PageSize.A4);
    		try {
    			PdfWriter.getInstance(document,
    					new FileOutputStream("./test.pdf"));
    			document.open();
    			document.add(new Paragraph("Hello World"));
    		} catch (DocumentException de) {
    			de.printStackTrace();
    		} catch (IOException ioe) {
    			ioe.printStackTrace();
    		}

    		document.close();
    		

        	return document;
    	}
    	
    }

    public class PanneauAlgorithmes extends JPanel {
    }
}
