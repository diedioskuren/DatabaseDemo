/* 
gcc -o output-file $(mysql_config --cflags) mysql-c-api.c $(mysql_config --libs)
*/
#include <mysql.h>
#include <stdio.h>

int main(void) {
   MYSQL *conn;
   MYSQL_RES *res;
   MYSQL_ROW row;

   char *server = "localhost";
   char *user = "root";
   char *password = "mysql";
   char *database = "hellowork";
   
   conn = mysql_init(NULL);
   
   /* Connect to database */
   if (!mysql_real_connect(conn, server,
         user, password, database, 0, NULL, 0)) {
      fprintf(stderr, "%s\n", mysql_error(conn));
      exit(1);
   }

   /* send SQL query */
   if (mysql_query(conn, "select * from jobsearch")) {
      fprintf(stderr, "%s\n", mysql_error(conn));
      exit(1);
   }

   res = mysql_use_result(conn);
   
   /* output table name */
   printf("MySQL Tables in mysql database:\n");
   while ((row = mysql_fetch_row(res)) != NULL)
      printf("%s \n", row[6]);

   /* close connection */
   mysql_free_result(res);
   mysql_close(conn);
  
  return 0;
}
