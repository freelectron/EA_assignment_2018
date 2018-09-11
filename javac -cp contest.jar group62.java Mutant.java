javac -cp contest.jar group62.java Mutant.java
jar cmf MainClass.txt submission.jar group62.class Mutant.class
java -jar testrun.jar -submission=group62 -evaluation=BentCigarFunction -seed=1