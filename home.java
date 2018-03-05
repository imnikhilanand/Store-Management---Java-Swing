import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

//classpath
//CLASSPATH=$CLASSPATH:/usr/share/java/mysql.jar
//export CLASSPATH

class Frame extends JFrame{

  private JLabel label1,label2,label3;
  private JButton button1,button2,button3;
  private JTextField field1,field2;

  Frame(){
    setTitle("Store Management");
    setSize(1000,1000);
    layout_new();
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void layout_new(){
      JTabbedPane jp = new JTabbedPane();
      JPanel p3=new JPanel();
      jp.add("Search Items",new itemPage());
      jp.add("Add Items",new addItem());
      jp.add("Transactions",new transaction());
      add(jp);
  }

}

class itemPage extends JPanel{
    private JLabel label1,label2;
    private JButton b1;
    private JTextField field1;

    itemPage(){
      setLayout(new GridBagLayout());
      label1 = new JLabel("Search Item");
      field1 = new JTextField(25);
      b1 = new JButton("Search");
      GridBagConstraints c1 = new GridBagConstraints();
      Insets i = new Insets(5, 5, 5, 5);
      c1.gridx = 0;
      c1.gridy = 0;
      c1.insets = i;
      add(label1,c1);
      c1.gridx++;
      add(field1,c1);
      c1.gridx++;
      add(b1,c1);
      //written code
      c1.gridy++;c1.gridx--;
      int l = 0;
      try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost/store","root","toor");
        Statement query3 = con2.createStatement();
        ResultSet result3 = query3.executeQuery("SELECT * FROM products");
        while(result3.next()){
           l++;
        }
      }catch(Exception e){
        System.out.println(e);
      }
      String[] cols = {"Name","Quantity","Price"};
      Object[][] data = new Object[l][3];
      try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost/store","root","toor");
        Statement query2 = con2.createStatement();
        ResultSet result2 = query2.executeQuery("SELECT * FROM products");
        int k =0;
        while(result2.next()){
          String name = result2.getString("name");
          int quantity = result2.getInt("quantity");
          int price = result2.getInt("mrp");
          data[k][0] = name;
          data[k][1] = quantity;
          data[k][2] = price;
          k++;
        }
      }catch(Exception e){
        System.out.println(e);
      }
      JTable table = new JTable(data,cols);
      JScrollPane jsp = new JScrollPane(table);
      add(jsp,c1);

    }



    public void view_item_page(){
      try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost/store","root","toor");
        Statement query2 = con2.createStatement();
        ResultSet result2 = query2.executeQuery("SELECT * FROM products");
        while(result2.next()){
          String name = result2.getString("name");
          int quantity = result2.getInt("quantity");
          int price = result2.getInt("mrp");
          System.out.println(name+" - "+quantity+" - "+price);
        }
      }catch(Exception e){
        System.out.println(e);
      }
    }

}

class addItem extends JPanel{
    private JLabel label1,label2,label3,label4;
    private JButton b1;
    private JTextField field1,field2,field3;

    addItem(){
      setLayout(new GridBagLayout());
      GridBagConstraints c1 = new GridBagConstraints();
      Insets i = new Insets(5, 5, 5, 5);
      c1.gridx = 0;
      c1.gridy = 0;
      c1.insets = i;
      field1 = new JTextField(25);
      field2 = new JTextField(25);
      field3 = new JTextField(25);
      b1 = new JButton("Add item");
      label1 = new JLabel("Add items");
      label2 = new JLabel("Item Name");
      label3 = new JLabel("Item Price");
      label4 = new JLabel("Item Quantity");
      c1.gridx++;
      add(label1,c1);
      c1.gridx--;c1.gridy++;
      add(label2,c1);
      c1.gridx++;
      add(field1,c1);
      c1.gridy++;c1.gridx--;
      add(label3,c1);
      c1.gridx++;
      add(field2,c1);
      c1.gridy++;c1.gridx--;
      add(label4,c1);
      c1.gridx++;
      add(field3,c1);
      c1.gridy++;
      add(b1,c1);
      b1.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
          String field1_value = field1.getText();
          String field2_value = field2.getText();
          String field3_value = field3.getText();
          JOptionPane.showMessageDialog(null, field3_value+" "+field1_value+" worth Rs. "+field2_value+" have been added");
          add_item_sql(field1_value,field2_value,field3_value);
        }
      });
    }

    public void add_item_sql(String name, String quantity,String price){
      try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/store","root","toor");
        Statement query1 = con.createStatement();
        query1.executeUpdate("INSERT INTO products (name,mrp,quantity) VALUES ('"+name+"','"+quantity+"','"+price+"')");
        }catch(Exception e){
          System.out.println(e);
      }
    }
}

class transaction extends JPanel{
  private JLabel label1,label2,label3;
  private JButton b1;
  private JTextField field1,field3;
  transaction(){
    setLayout(new GridBagLayout());
    GridBagConstraints c1 = new GridBagConstraints();
    Insets i = new Insets(5, 5, 5, 5);
    c1.gridx = 0;
    c1.gridy = 0;
    c1.insets = i;
    label1 = new JLabel("Transaction");
    label2 = new JLabel("Item Name");
    field1 = new JTextField(25);
    label3 = new JLabel("Item Quantity");
    field3 = new JTextField(25);
    b1 = new JButton("Sold");
    c1.gridx++;
    add(label1,c1);
    c1.gridy++;c1.gridx--;
    add(label2,c1);
    c1.gridx++;
    add(field1,c1);
    c1.gridy++;
    c1.gridx--;
    add(label3,c1);
    c1.gridx++;
    add(field3,c1);
    c1.gridy++;
    add(b1,c1);
    b1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        String first = field1.getText();
        String second = field3.getText();
        JOptionPane.showMessageDialog(null,second+" "+first+" have been sold");
        sold_items(first,second);

      }
    });
  }

  public void sold_items(String name2, String qty){
    int qty_new = Integer.parseInt(qty);
    int previous_Stock = 0;
    try{
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost/store","root","toor");
      Statement query1 = con.createStatement();
      Statement query0 = con.createStatement();
      ResultSet rs = query0.executeQuery("SELECT quantity FROM products WHERE name = '"+name2+"'");
      while(rs.next()){
           previous_Stock = rs.getInt("quantity");
      }
      int new_stock = previous_Stock - qty_new ;
      query1.executeUpdate("UPDATE products SET quantity = '"+new_stock+"' WHERE name = '"+name2+"'");
      }catch(SQLException se){
        se.printStackTrace();
      }
      catch(Exception e){
        System.out.println(e);
      }
    }


}


public class home{
  public static void main(String args[]){
    Frame f = new Frame();
  }
}
