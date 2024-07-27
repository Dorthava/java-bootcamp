mkdir target
javac -d target src/java/edu/school21/printer/logic/ImageToCharConverter.java src/java/edu/school21/printer/app/Main.java
java -cp target edu/school21/printer/app/Main ../../../misc/images/it.bmp '#' '.'