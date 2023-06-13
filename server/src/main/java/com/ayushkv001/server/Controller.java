package com.ayushkv001.server;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("getData")
public class Controller {

    @GetMapping(path = "{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Map<Integer,Object[]> getProducts(@PathVariable("id") String id) throws SQLException {
        DBHandler dbHandler = DBHandler.getInstance();
        ResultSet res = dbHandler.getResult(id);
        Map<Integer,Object[]> map = new HashMap<>();
        int i=0;
        while(res.next()){
            Object[] a = new Object[4];
            a[0]=res.getString("name");
            a[1]=res.getInt("cost");
            a[2]=res.getString("image");
            a[3] = res.getInt("id");
            map.put(i++,a);
        }
        return map;
    }
    @GetMapping("/serve-image/{name}")
    @CrossOrigin(origins = "http://localhost:3000")
    public void serveImageHandler(@PathVariable("name") String name,HttpServletResponse response) {

        try {
            InputStream fileInputStream= new FileInputStream("D:\\Work_Assignment\\sports-rental-service\\server\\src\\main\\resources\\image/"+name+".jpg");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(fileInputStream,response.getOutputStream());
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @PostMapping("/order")
    public void update(@RequestBody Map<String, String> orderMap) {
        String id = orderMap.get("id");
        String renter_name = orderMap.get("renter_name");
        String email = orderMap.get("email");
        String phone = orderMap.get("phone");
        String duration = orderMap.get("duration");
        String cost = orderMap.get("cost");
        DBHandler dbHandler = DBHandler.getInstance();
        dbHandler.putOrder(id,renter_name,email,phone,duration,cost);
    }

}