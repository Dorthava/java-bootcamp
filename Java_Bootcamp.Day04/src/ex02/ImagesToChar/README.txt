mkdir lib
curl -o lib/jcommander-1.81.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.81/jcommander-1.81.jar
curl -o lib/JColor-5.5.1.jar https://repo1.maven.org/maven2/com/diogonunes/JColor/5.5.1/JColor-5.5.1.jar
mkdir target
cp -r lib target/lib
javac -d target src/java/edu/school21/printer/logic/ImageToCharConverter.java src/java/edu/school21/printer/app/Main.java src/java/edu/school21/printer/logic/ArgsToColorConverter.java -cp lib/JColor-5.5.1.jar:lib/jcommander-1.81.jar
cp -r src/resources target/resources
jar cfm target/images-to-chars-printer.jar src/manifest.txt -C target .
java -jar target/images-to-chars-printer.jar --white=RED --black=WHITE