/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.domain.Book;
import app.servlet.FileDownloadServlet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Sara ja Laur
 */
@Controller
@RequestMapping("bibtexinator")
public class FileDownloadController {

    @Autowired
    FileDownloadServlet fileDownloadServlet;

    @RequestMapping(method = RequestMethod.GET, value = "/download")
    public void saveBook(HttpServletResponse response) throws IOException {

        FileInputStream fileIn = null;
        try {
            File file = new File("downloadfilename.txt");
            
            if (file.exists()) {
                System.out.println("Created successfully!");
            } else {
                file.createNewFile();
                System.out.println("File not created!");
            }
            
            if (file.exists()) {
                System.out.println("Created on second try!");
            }
            
            fileIn = new FileInputStream(file);
            ServletOutputStream out = response.getOutputStream();
            byte[] outputByte = new byte[4096];
            while (fileIn.read(outputByte, 0, 4096) != -1) {
                out.write(outputByte, 0, 4096);
            }   fileIn.close();
            out.flush();
            out.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileDownloadController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileIn.close();
            } catch (IOException ex) {
                Logger.getLogger(FileDownloadController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
