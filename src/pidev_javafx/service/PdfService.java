/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.service;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev_javafx.entitie.Commande;
import pidev_javafx.entitie.LigneCommande;

/**
 *
 * @author marni
 */
public class PdfService {
    
    public void genererPdf(Commande c){
       Document doc =new Document();
       CommandeService CS =new CommandeService();
       LigneCommandeService LCS = new LigneCommandeService();
       ProduitService PS =new ProduitService();
       
       List<LigneCommande> ListLigneCommande =LCS.getLigneCommandesParCommande(c);
       
        try {
           
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\marni\\OneDrive\\Bureau\\Facture_N°"+c.getId()+".pdf"));
            doc.open();
            //
            Chunk Title = new Chunk("Facture N° "+c.getId());
            Title.setFont(FontFactory.getFont("Comic Sans MS",17));
            Paragraph TitleParagraph = new Paragraph(Title);
            TitleParagraph.setAlignment(Element.ALIGN_CENTER);

            doc.add(TitleParagraph);
            
            //
            Image img =Image.getInstance("C:\\Users\\marni\\OneDrive\\Documents\\NetBeansProjects\\Pidev_javaFX\\src\\pidev_javafx\\assets\\logo.png");
            img.scaleAbsolute(200, 100);
            //Image img = new Image(new FileInputStream("/pidev_javafx/assets/logo.png"))); 
            doc.add(img);
            
            doc.add(new Paragraph("----------------------------"));
            doc.add(new Paragraph("Date Commande : "+c.getDate_commande()));
            doc.add(new Paragraph("Adresse De Livraison : "+c.getAdresse_livraison()));
            doc.add(new Paragraph("Méthode De Paiement : "+c.getMethode_paiement()));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Client : "+c.getUser().getNom()+" "+c.getUser().getPrenom()));
            doc.add(new Paragraph("Adresse E-mail : "+c.getUser().getEmail()));
            doc.add(new Paragraph("Téléphone : "+c.getTelephone()));
            
             doc.add(new Paragraph(" "));
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            
            ///////////////////////////////////////////////////////////////////
            PdfPCell cell;
            
            cell = new PdfPCell(new Phrase(""));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Nom", FontFactory.getFont("Comic Sans MS",14)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            cell.setPaddingBottom(10);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Quantité", FontFactory.getFont("Comic Sans MS",14)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Prix Unitaire", FontFactory.getFont("Comic Sans MS",14)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Total", FontFactory.getFont("Comic Sans MS",14)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            table.addCell(cell);
            
            ////////////////////////////////////////////////////////////
            for (int i=0;i<ListLigneCommande.size();i++){
                //cell = new PdfPCell(new Phrase("img", FontFactory.getFont("Comic Sans MS",12)));
                //Image image =new Image(new FileInputStream(ListLigneCommande.get(i).getProduit().getImage_produit()));
                Image image = Image.getInstance("C:\\Users\\marni\\OneDrive\\Documents\\NetBeansProjects\\Pidev_javaFX\\src\\pidev_javafx\\assets\\whey-63f4b48509ee1.jpg");
                image.scalePercent(20);
                cell =new PdfPCell();
                cell.addElement(image);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(cell);

                cell = new PdfPCell(new Phrase(ListLigneCommande.get(i).getProduit().getNom(), FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(ListLigneCommande.get(i).getQuantite_produit()), FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Float.toString(ListLigneCommande.get(i).getPrix_unitaire())+" DT", FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Float.toString(ListLigneCommande.get(i).getPrix_unitaire()*ListLigneCommande.get(i).getQuantite_produit())+" DT", FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(cell);

            }


                   cell = new PdfPCell(new Phrase(""));
                   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                   cell.setColspan(3); // fusionne les 4 premières colonnes en une seule
                   cell.setBorder(Rectangle.NO_BORDER);
                   table.addCell(cell);
                   
                   cell = new PdfPCell(new Phrase(" Total à Payer :",FontFactory.getFont("Comic Sans MS",12,BaseColor.RED)));
                   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                   cell.setBorder(Rectangle.NO_BORDER);
                   table.addCell(cell);
                   cell = new PdfPCell(new Phrase(Float.toString(c.getPrix_commande())+" DT", FontFactory.getFont("Comic Sans MS",12,BaseColor.RED)));
                   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                   cell.setPaddingBottom(10);
                   cell.setBorder(Rectangle.NO_BORDER);
                   table.addCell(cell);
                
                
            /////////////////////////////////////////////////////////////
            doc.add(table);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));

            
            Chunk remarqueChunk = new Chunk("Remarque: MERCI D’AVOIR MAGASINÉ SUR Golden Gym!");
            remarqueChunk.setBackground(BaseColor.LIGHT_GRAY);
            remarqueChunk.setFont(FontFactory.getFont("Comic Sans MS",15));
            //remarqueChunk.setFontSize(16);

            Paragraph remarqueParagraph = new Paragraph(remarqueChunk);
            remarqueParagraph.setAlignment(Element.ALIGN_CENTER);

            doc.add(remarqueParagraph);
            
            LineSeparator line = new LineSeparator();
             doc.add(new Paragraph(" "));
            doc.add(line);
            ////////////Lien de la page web
            Paragraph centerPara = new Paragraph();
            centerPara.setAlignment(Element.ALIGN_CENTER);
            Anchor anchor = new Anchor("www.golengym.com", FontFactory.getFont(FontFactory.COURIER, 12, Font.UNDERLINE,BaseColor.BLUE));
            anchor.setReference("https://www.golengym.com");
            centerPara.add(anchor);
            doc.add(centerPara);
            
            
            
            
            doc.close();

            Desktop.getDesktop().open(new File("C:\\Users\\marni\\OneDrive\\Bureau\\Facture_N°"+c.getId()+".pdf"));
            
        } catch (DocumentException ex) {
               Logger.getLogger(PdfService.class.getName()).log(Level.SEVERE, null, ex);
        }catch (FileNotFoundException ex) {
            Logger.getLogger(PdfService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PdfService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
