package j2ee.app.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javax.servlet.annotation.WebServlet;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

@WebServlet(urlPatterns = {"", "/pdf"})
public class PDFServlet extends HttpServlet {
 
    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            // Get the text that will be added to the PDF
            //String text = request.getParameter("text");
            //if (text == null || text.trim().length() == 0) {
            //     text = "You didn't enter any text.";
            //}
            // step 1
            Document document = new Document();
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            // step 3
            document.open();
            // step 4
            //document.add(new Paragraph(String.format(
            //    "You have submitted the following text using the %s method:",
            //    request.getMethod())));
            //document.add(new Paragraph(text));
            
            Font blue24 = new Font(FontFamily.HELVETICA, 24, Font.BOLD, BaseColor.BLUE);
            Chunk blueText = new Chunk("Moor Cat", blue24);
            
            Paragraph p2 = new Paragraph();
            p2.setAlignment(Element.ALIGN_CENTER);
            p2.add(blueText);
            document.add(p2);
            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );

            
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(40);
            table.setWidths(new int[]{1, 3, 5});
            
            
            PdfPCell cell = new PdfPCell();
            Paragraph p = new Paragraph("1");
            //p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            //cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            //cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            //table.addCell(createImageCell("1.jpg"));
            Image img = Image.getInstance("C:/j2ee/PDFWebApplication1/web/images/1.jpg");
            //img.scalePercent(10);
            cell = new PdfPCell(img, true);
            cell.setUseBorderPadding(true);
            table.addCell(cell);
            
            cell = new PdfPCell();
            p = new Paragraph("This picture was taken at Java One.");
            //p.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(p);
            //cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            //cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            document.add(table);
            //table.addCell(createTextCell("This picture was taken at Java One.\nIt shows the iText crew at Java One in 2013."));
                // step 5
            document.close();
 
            // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
        }
        catch(DocumentException e) {
            throw new IOException(e.getMessage());
        }
    }
 
    /**
     * Serial version UID.
     */
    //private static final long serialVersionUID = 6067021675155015602L;
 
}


