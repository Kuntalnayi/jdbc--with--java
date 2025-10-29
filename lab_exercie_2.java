// Answer:
// • The program uses the Type 4 (Thin) Driver: com.mysql.cj.jdbc.Driver.
// • Type 4 drivers are pure Java, platform-independent, and directly communicate with the database using TCP/IP.
// • They offer best performance, require no native libraries, and are recommended for all modern Java versions (JDK 8+).
// • Example in code:

Class.forName("com.mysql.cj.jdbc.Driver");