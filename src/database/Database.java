/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author user
 */
public class Database{

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String URL_DB = "jdbc:mysql://localhost/pertemuan_satu";
    static final String username_db = "root";
    static final String pass_db="";
    
    static Connection con;
    static ResultSet rs;
    static Statement st;
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        koneksi();
        try {
            if (login()) {
                    insertdata();
            } else {
                System.out.println("salah");
        }
}catch (Exception e) {
    e.getMessage();
}
    }
        
       
    static void koneksi(){
    try{
        Class.forName(JDBC_DRIVER);
        con = DriverManager.getConnection(URL_DB,username_db,pass_db);
            st = con.createStatement();
            System.out.println("coba koneksi");

        } catch (Exception e) {
        e.getMessage();
        System.out.print("koneksi gagal");
        }
    } 
     static boolean login() {
        boolean cek;
        try {
            cek = false;
            String user = JOptionPane.showInputDialog("username");
            String pass = JOptionPane.showInputDialog("password");
            String sql = "select * from penjualan where total='" + user+ "'and pembeli = '" +pass+ "'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                cek = true;
            }
            return cek;
        } catch (Exception e) {
            cek = false;
            return cek;
                 }
    }
            
    static void tampildata(){
        try {
            String sql = "select * from anggota";
            rs = st.executeQuery(sql);
            while(rs.next()){
                System.out.print(rs.getString(1) + " | ");
                System.out.print(rs.getString(2) + " | ");
                System.out.print(rs.getString(3) + " | ");
                System.out.print(rs.getString(4) + " | ");
                System.out.println(rs.getString(5));
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
     static void insertdata() {

        String id = JOptionPane.showInputDialog("id_anggota");
        String nama = JOptionPane.showInputDialog("nama_anggota");
        String email = JOptionPane.showInputDialog("email");
         String alamat = JOptionPane.showInputDialog("alamat");
        String  tgl = JOptionPane.showInputDialog("tgl_lahir");
        String sql = "insert into anggota (id_anggota,nama_anggota,email,alamt,tgl_lahir) values ('" + id + "','" + nama + "','" + email + "','" + alamat +"','"+tgl+ "')";
        try {
            st.executeUpdate(sql);
            tampildata();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}



