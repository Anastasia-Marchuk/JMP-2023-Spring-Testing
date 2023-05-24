package jmp.amarchuk.web;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import jmp.amarchuk.facade.BookingFacade;
import jmp.amarchuk.model.Ticket;
import jmp.amarchuk.model.User;
import jmp.amarchuk.service.UserService;
import jmp.amarchuk.web.handler.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;


/**
 * PDFController implementation - annotation-based controller that will delegate to BookingFacade methods.
 *
 * @author Anastasiya Marchuk
 *
 */
@Controller
public class PdfController {

    @Autowired
    UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfController.class);

//    todo: check the result
    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    public FileSystemResource getFileUsers( ) throws DocumentException, FileNotFoundException, HandlerException {
        Document document = new Document();
        document.open();
        PdfWriter.getInstance(document, new FileOutputStream("html.pdf"));
        List <User> userList=userService.getAllUsers();
        Paragraph para = new Paragraph();
        for (User u : userList) {
            para.add(new Chunk(String.valueOf(u.toString())));
        }
        document.add(para);
        document.close();
        return new FileSystemResource("html.pdf");
    }


    @GetMapping("/preparePDF")
    public String create() {
        LOGGER.debug("Prepare PDF ");
        return "generate_pdf_tickets";
    }

//    todo: error during closing document
//    @GetMapping("/application/pdf")
//    public FileSystemResource generateTicketsUserById(@RequestParam("id") long id) throws DocumentException, IOException {
//
//        Document document = new Document();
//        document.open();
//        PdfWriter.getInstance(document, new FileOutputStream("new.pdf"));
//        User user=userService.getUserById(id);
//        List <Ticket> ticketList=userService.getBookedTickets(user, 1,1);
//        Paragraph para = new Paragraph();
//        for (Ticket t : ticketList) {
//            para.add(new Chunk(String.valueOf(t.toString())));
//        }
//        document.add(para);
//        document.close();
//        return new FileSystemResource("new.pdf");
//
////    }
//
//    @GetMapping("/createPDF")
//    public FileSystemResource getFileTickets(@RequestParam("id") long id ) throws FileNotFoundException, DocumentException {
//        LOGGER.debug("Get pdf of tickets");
//        Document document = new Document();
//        document.open();
//        PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));
//        User user=userService.getUserById(id);
//        List <Ticket> ticketList=userService.getBookedTickets(user, 1,1);
//        LOGGER.debug("Size of user list with id {} = {}",user, ticketList.size());
//
//        Paragraph para = new Paragraph();
//        for (Ticket t : ticketList) {
//            para.add(new Chunk(String.valueOf(t.toString())));
//        }
//        document.add(para);
//        document.close();
//        return new FileSystemResource("iTextHelloWorld.pdf");
//    }
}

