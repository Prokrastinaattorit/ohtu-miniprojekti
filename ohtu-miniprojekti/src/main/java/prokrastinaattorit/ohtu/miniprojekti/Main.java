
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author talon
 */
public class Main {

    public static void main(String[] args) {
        Main p = new Main();
        p.start(args);
    }

    private void start(String[] args) {
        ApplicationContext context = 
            new ClassPathXmlApplicationContext("META-INF/config.xml");
    }
    
}