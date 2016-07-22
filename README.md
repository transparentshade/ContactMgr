**Contact Maanger Application**
A very simple application for contact management developed for some company test.
This app has been developed using TRIE data structure. To allow user searching the contacts
based on both first name and last name I used two tries. Each node in the TRIE will have
reference to list of contacts. Junit ITs have been written to test the code.


**Extra Features**
1. Searching based on last name and first name.
2. Ranking of search result based on complete and partial matches
3. Some user input error cases are handled properly



To get the executable jar please follow the below given steps

1. Install maven

2. execute command "mvn package"  from command line within root directory of CMgr app.

3. Execute command "java -cp CMgr-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.cmgr.main.Main" on command line. 

4. If you follow step 1-2 then CMgr-0.0.1-SNAPSHOT-jar-with-dependencies.jar will be in target/ folder of CMgr app.



**Assumption**

 1. No duplicate contact check. I am allowing user to add Contact with same firstname and lastname as its there in database.
 
 2. Spaces are not allowed in contact names. Names are trimmed before saving into db.
 
 3. Search query will be a string without spaces. Even if it has spaces only first string will be concidered and the entire portion after space will be ignored
    
 4. Not persisting the contact to any database. All the data will be lost once user exits the application.
 
 