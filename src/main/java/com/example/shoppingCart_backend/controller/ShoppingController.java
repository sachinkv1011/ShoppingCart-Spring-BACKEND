package com.example.shoppingCart_backend.controller;

import com.example.shoppingCart_backend.Dao.ProductDao;
import com.example.shoppingCart_backend.Dao.UserDao;
import com.example.shoppingCart_backend.model.ProductModel;
import com.example.shoppingCart_backend.model.UserRegModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
public class ShoppingController {

    @Autowired
    private UserDao udao;
    @Autowired
    private ProductDao pdao;

    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public String HomePage(){
        return "Welcome to Shopping Cart API";
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/userRegistration", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> UserRegistration(@RequestBody UserRegModel um){
        System.out.println(um);
        List<UserRegModel> result = (List<UserRegModel>) udao.FindUserLogin(um.getEmail());
        System.out.println(result);
        HashMap<String, String> st = new HashMap<>();
        if(result.size()!=0){
            st.put("status","success");
            st.put("message","user already exists");
        }else{
            udao.save(um);
            st.put("status","success");
            st.put("message","user added successfully");
        }
        return st;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/userLogin", produces = "application/json", consumes = "application/json")
    public HashMap<String, String> UserLogin(@RequestBody UserRegModel um){
        System.out.println(um.getEmail());
        List<UserRegModel> result = (List<UserRegModel>) udao.FindUserLogin(um.getEmail());
        HashMap<String, String> st = new HashMap<>();
        if(result.size()==0){
            st.put("status","failed");
            st.put("message","user doesn't exist");
        }else{
            if(Objects.equals(result.get(0).getPassword(), um.getPassword())){
                st.put("status","success");
                st.put("message","user login success");
            }else{
                st.put("status","failed");
                st.put("message", "wrong password");
            }
        }

        return st;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/fetchProducts")
    public List<ProductModel> FetchProducts(){
        return (List<ProductModel>) pdao.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addProducts", produces = "application/json", consumes = "application/json")
    public HashMap<String, String> AddProduct(@RequestBody ProductModel pm){
        pdao.save(pm);
        HashMap<String, String> st = new HashMap<>();
        st.put("status","success");
        st.put("message","product added successfully");
        return st;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/searchProducts", produces = "application/json", consumes = "application/json")
    public List<ProductModel> SearchProduct(@RequestBody ProductModel pm){
        return (List<ProductModel>) pdao.SearchProduct(pm.getTitle());
    }

}
