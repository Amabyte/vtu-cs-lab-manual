## Program 11: Create a XHTML form with Name, Address Line 1, Address Line 2, and E-mail text fields. On submitting, store the values in MySQL table. Retrieve and display the data based on Name.
***

### Setting up the database (MySQL):
1. Open a terminal and run the command `mysql`
2. In the MySQL prompt run `show databases;`
3. Choose an existing database by running the command `use test;` where test is the name of an already existing database.
4. Now, create a new table by running the command `create table student(name varchar(20),addr1 varchar(20),addr2 varchar(20),email varchar(20));`
5. Run the command `exit` to exit from the MySQL prompt.

### Code: 
*11.html*

     <html>
     	<h2>Insert Info</h2>
     		<form action="insert.php">
     			Name:<input type=text name=name /><br>
     			Address Line 1:<input type=text name=addr1><br>
     			Address Line 2:<input type=text name=addr2><br>
     			Email-ID:<input type=text name=email><br>
     			<input type=submit value=insert />
     			<input type=reset value=reset />
     		</form>
     	<h2>Search Info</h2>
     		<form action="search.php">
     			Name:<input type=text name=name /><br>
     			<input type=submit value=search />
     			<input type=reset value=reset />
     		</form>
    </html>

*insert.php*

     <?php
     	# take inputs & store in local variables
     	$name=$_REQUEST['name'];
     	$addr1=$_REQUEST['addr1'];
     	$addr2=$_REQUEST['addr2'];
     	$email=$_REQUEST['email'];
     		# create a database handle
     		# mysql_connect() connects the script to MySQL server
     		# the parameters are hostname
     		$con=mysql_connect("localhost") or die('Could not connect');
     			# select a database
     			mysql_select_db("test",$con);
     				# query
     				if(mysql_query("insert into student values('$name','$addr1','$addr2','$email')"))
     					{
     						echo "Data inserted successfully!";
     					}
     		# release the database handle by closing the connection
     		mysql_close($con);
     ?>

*search.php*

     <html>
     	<h2>Search Result</h2>
     		<table border=1>
     		<tr>
     			<th>Name</th><th>Address Line 1</th><th>Address Line 2</th><th>Email-ID</th>
     		</tr>
     			<?php
     				$name=$_REQUEST['name'];
     					$con=mysql_connect("localhost") or die('Could not connect');
     						mysql_select_db("test",$con);
     							# query
     							$result=mysql_query("select * from student where name like '%".$name."%'");
     							# retrieve all rows with matches
     							while($row=mysql_fetch_array($result))
     								{
     									# display result
     									echo "<tr>";
     									echo "<td>".$row['name']."</td>";
     									echo "<td>".$row['addr1']."</td>";
     									echo "<td>".$row['addr2']."</td>";
     									echo "<td>".$row['email']."</td>";
     									echo "</tr>";
     								}
     					mysql_close($con);
     			?>
     		</table>
     </html>
     
### Output:
*Steps for checking output-*

* Save the .html file in the folder `/var/www/html`
* Save the .php files in the folder `/var/www/html`
* Change the file permission of the php files by running the command `sudo chmod 777 insert.php search.php`
* Make sure the database is set before executing the program.
* Open a browser and in the address bar type `localhost/html/11.html`
* Input the `name`,`Address 1`,`Address 2` and `email` which are stored in the database.
* The output is displayed on the browser by retrieving the values from the database.
* To check if the value is stored correctly in the database, 
	* Open a terminal and run `mysql`
	* Run the command `use test;`
	* Run the command `select * from student;` for fetching all the inputs stored in the database.

### Screenshot:

![input](11_1.png)

![output](11_2.png)
