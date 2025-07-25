package business;

import core.Helper;
import dao.CustomerDao;
import entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerController {

    private final CustomerDao customerDao = new CustomerDao();

    public ArrayList<Customer> findAll() {
        return this.customerDao.findAll();
    }

    public boolean save(Customer customer) {
        return this.customerDao.save(customer);
    }

    public Customer getById(int id) {
        return this.customerDao.getById(id);
    }

    public boolean update(Customer customer) {
        if (this.getById(customer.getId()) == null) {
            Helper.showMessage(customer.getId() + " ID kayıtlı müşteri bulunamadı!");
            return false;
        }
        return this.customerDao.update(customer);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMessage(id + " ID kayıtlı müşteri bulunamadı!");
            return false;
        }
        return this.customerDao.delete(id);
    }

    public ArrayList<Customer> filter(String name, Customer.TYPE type) {
        //SELECT * FROM customer WHERE name = 'TEST' AND type = 'PERSON';
        String query = "SELECT * FROM customer";
        ArrayList<String> whereList = new ArrayList<>();

        if (!name.isEmpty()) {
            whereList.add("name LIKE '%" + name + "%' ");
        }

        if (type != null) {
            whereList.add("type = '" + type + "'");
        }

        if (!whereList.isEmpty()){
            String whereQuery = String.join(" AND ", whereList);
            query += " WHERE " + whereQuery;
        }

        return this.customerDao.query(query);
    }


}
