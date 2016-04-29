/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repositories;

import app.domain.Article;
import app.domain.Manual;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sara ja Laur
 */
public interface ManualRepository extends JpaRepository<Manual, Long>{
    
}
