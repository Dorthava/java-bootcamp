mkdir target
javac -d target src/java/edu/school21/printer/logic/ImageToCharConverter.java src/java/edu/school21/printer/app/Main.java
cp -r src/resources target/resources
jar cfm target/images-to-chars-printer.jar src/manifest.txt -C target .
java -jar target/images-to-chars-printer.jar '.' '0'