
how-do-i-generate-random-integers-within-a-specific-range-in-java



In Java 1.7 or later, the standard way to do this is as follows:

	import java.util.concurrent.ThreadLocalRandom;

	// nextInt is normally exclusive of the top value,
	// so add 1 to make it inclusive
	int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);



Before Java 1.7, the standard way to do this is as follows:	

	import java.util.Random;

	Random rand = new Random();
    int randomNum = rand.nextInt((max - min) + 1) + min;
