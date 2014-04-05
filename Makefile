all:
	javac -cp ./src ./src/Go.java

clean:
	find . -iname "*.class" -exec rm '{}' ';'

run:
	java -cp ./src Go
