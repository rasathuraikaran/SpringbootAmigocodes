package com.karax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {



    private final CustomerRepo customerrepo;

    public Main(CustomerRepo customerrepo) {
        this.customerrepo = customerrepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerrepo.findAll();

    }


    record NewCustomerRequest(String name,String email,Integer age){

    }
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer =new Customer();
        customer.setName(request.name());
        customer.setAge(request.age());
        customer.setEmail(request.email());

        customerrepo.save(customer);



    }

    @DeleteMapping("{customerId}")
    public void deleteId(@PathVariable ("customerId") Integer id){
        customerrepo.deleteById(id);

    }

    @PutMapping("{customerId}")
    public void UpdateID(@PathVariable ("customerId") Integer id,@RequestBody NewCustomerRequest request){
        Customer customer =new Customer();
        customer.setName(request.name());
        customer.setAge(request.age());
        customer.setEmail(request.email());

        customerrepo.save(customer);

    }





 /*  public class GreetResponse{
        private String greet;


       public GreetResponse(String greet) {
           this.greet = greet;
       }

       @Override
       public String toString() {
           return "GreetResponse{" +
                   "greet='" + greet + '\'' +
                   '}';
       }

       public String getGreet() {
           return greet;
       }

       @Override
       public boolean equals(Object o) {
           if (this == o) return true;
           if (o == null || getClass() != o.getClass()) return false;
           GreetResponse that = (GreetResponse) o;
           return Objects.equals(greet, that.greet);
       }

       @Override
       public int hashCode() {
           return Objects.hash(greet);
       }
   }*/


}
