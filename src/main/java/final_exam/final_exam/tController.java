/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package final_exam.final_exam;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author 20200140074
 */
@Controller
@ResponseBody
public class tController {
    
    Person data = new Person();
    PersonJpaController bctrl = new PersonJpaController();
    
    @RequestMapping("/getPerson/{id}")
    public String getPerson(@PathVariable("id") int id){
        try{
            data = bctrl.findPerson(id);
            return data.getNama()+ " " + data.getNik().toString();
        }
        catch (Exception error){
            return "Data tidak ada";
        }  
    }
    

    @RequestMapping("/getall")
    public List<Person> viewAll()
    {
        return bctrl.findPersonEntities();
    }
    
     @RequestMapping("/delete/{id}")
    public String deleteId(@PathVariable("id") int id){
        try{
        bctrl.destroy(id);
        return id + "Terhapus";
        }
        catch (Exception error){
            return "error";
        }
    }
    
    //create data
    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String createData(@RequestBody Person person){
        try{
            bctrl.create(person);
            return "Data berhasil ditambahkan";
        } catch(Exception e){
            return "Data gagal ditambahkan";
        }
    } 
    
    //update data
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public String editData(@RequestBody Person person, @PathVariable("id") String id){
        try{
            data.setId(Integer.parseInt(id));
            data.setNama(person.getNama());
            bctrl.edit(data);
            return "Data berhasil di ubah";
        } catch (Exception e){
            return "Data gagagl di ubah";
        }
    }
}
