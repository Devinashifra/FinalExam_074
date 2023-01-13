/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package final_exam.final_exam;

import com.fasterxml.jackson.databind.ObjectMapper;
import static final_exam.final_exam.Person_.id;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 20200140074
 */
@RestController
@CrossOrigin
public class mController {
    
    Person mydata = new Person();
    PersonJpaController ctrl = new PersonJpaController((EntityManagerFactory) id);
    
    @GetMapping("/{id}")
    public String myName(@PathVariable("id") int id){
        try{
            mydata = ctrl.findPerson(id);
        }
        catch (Exception e){}
        return mydata.getNik();
    }
    
    @RequestMapping(value = "/get",
            method = RequestMethod.GET,
            consumes = APPLICATION_JSON_VALUE)
    public List<Person> getAll(){
        List<Person> data = new ArrayList();
        try{
            data = ctrl.findPersonEntities();
        }catch(Exception e){
            data = List.of();
        }
        return data;
    }
    
    @RequestMapping(value = "/post",
            method = RequestMethod.POST,
            consumes = APPLICATION_JSON_VALUE)
    public String createName(HttpEntity<String> Paket){
        String message = "Data Tersimpan";
        
        try{
            String json_receive = Paket.getBody();
            ObjectMapper objm = new ObjectMapper();
            Person per = new Person();
            per = objm.readValue(json_receive, Person.class);
            ctrl.create(per);
            message = per.getNama() + "Data Tersimpan";
        }catch(Exception e){
            message = "Data gagal Tersimpan";
        }
        return message;
    }
    
    @RequestMapping(value = "/put",
            method = RequestMethod.PUT,
            consumes = APPLICATION_JSON_VALUE)
    public String editName(HttpEntity<String> kirim){
        String message = "Data Edit";
        try{
            String json_receive = kirim.getBody();
            ObjectMapper map = new ObjectMapper();
            Person newper = new Person();
            newper = map.readValue(json_receive, Person.class);
            ctrl.create(newper);
            message = newper.getNama() + "Data Teredit";
        }catch (Exception e){
            message = "Data gagal Teredit";
        }
        return message;
    }
            
    @RequestMapping(value = "/delete",
            method = RequestMethod.DELETE,
            consumes = APPLICATION_JSON_VALUE)
    public String deleteData(HttpEntity<String> kiriman){
        String message = "Data Hapus";
        try {
            String json_receive = kiriman.getBody();
            ObjectMapper mapp = new ObjectMapper();
            Person np = new Person();
            np = mapp.readValue(json_receive, Person.class);
            ctrl.create(np);
            message = np.getNama() + "Data terhapus";
        }catch(Exception e){
            message = "Data gagal Terhapus";
        }
        return message;
    }
    
}
