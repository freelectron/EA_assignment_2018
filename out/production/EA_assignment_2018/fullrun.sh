javac -cp contest.jar group62.java Mutant.java Var.java Population.java EvolutionAlgorithm.java
jar cmf MainClass.txt submission.jar group62.class Mutant.class Var.class Population.class EvolutionAlgorithm.class
java -jar testrun.jar -submission=group62 -evaluation=BentCigarFunction -seed=1
java -jar testrun.jar -submission=group62 -evaluation=KatsuuraEvaluation -seed=1
java -jar testrun.jar -submission=group62 -evaluation=SchaffersEvaluation -seed=1
