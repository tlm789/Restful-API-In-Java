# Restful-API-In-Java
An exercise to practice and learn how to implement Restful APIs in Java using Jersey.

This is designed to act as a cache, posting and getting data via Restful APIs and stored in-memory in a HashMap. A seperate thrread searches the hashMap every 30 seconds to delete documents stored for more than 30 seconds. It includes a model class Message to create the data. The MessageServices class holds all the methods used by the APIs in MessageResouces. A database was not implemented as data is stored in-memory in a HashMap. 
