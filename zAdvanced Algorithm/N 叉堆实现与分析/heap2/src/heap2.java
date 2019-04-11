import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

class Element {
    int key;
    int value;

    public Element(final int key, final int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}

public class heap2 {
    private static int numOfComparison = 0;
    private static int branchingFactor = 2;
    private static int bitShifting = 1;
    private static ArrayList<Element> elements = new ArrayList<>();

    /**
     * See if the input branching factor is power of 2
     */
    private static boolean isPowerOfTwo(final int n) {
        if (n <= 0) return false;
        return (n&(n - 1)) == 0;
    }

    /**
     * Apply log on the branching factor to get the number of bit shifting needed for replacement of multiplication/division
     */
    private static int getNumberOfBitShifting(final int branchingFactor) {
        return (int)Math.floor(Math.log(branchingFactor)/Math.log(2.0));
    }

    /**
     * Insert a new pair of key and value as an element into the heap
     */
    private static void insertValue(final int insertKey, final int insertValue) {
        final Element element = new Element(insertKey, insertValue);
        elements.add(element);
        int currentIndex = elements.size() - 1;
        if (currentIndex == 0) {
            return;
        }

        while (currentIndex != 0) {
            // compare current element and current element's parent key
            final int parentIndex = (((int) (Math.floor((double) currentIndex - 1))) >> bitShifting);
            numOfComparison++;
            if (elements.get(currentIndex).getKey() < elements.get(parentIndex).getKey()) {
                // swap current element with parent element
                final Element temp = elements.get(currentIndex);
                elements.set(currentIndex, elements.get(parentIndex));
                elements.set(parentIndex, temp);
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
    }

    /**
     * Remove the min element in the heap and do heapify if needed
     */
    private static Element removeMin() {
        final Element result = elements.get(0);
        if (elements.size() == 1) {
            elements.remove(0);
        } else {
            // Move the last element to the top
            elements.set(0, elements.get(elements.size() - 1));
            elements.remove(elements.size() - 1);
            boolean needHeapify = true;
            int currentIndex = 0;

            while (needHeapify) {
                int minChildIndex = 1;
                needHeapify = false;
                for (int i = 1; i <= branchingFactor; i++) {
                    // traverse the child element of current element and find the min child
                    final int currentChildIndex = (currentIndex << bitShifting) + i;
                    if (currentChildIndex >= elements.size()) {
                        break;
                    }
                    numOfComparison++;
                    if (elements.get(currentChildIndex).getKey() < elements.get((currentIndex << bitShifting) + minChildIndex).getKey()) {
                        minChildIndex = i;
                    }
                }

                if (((currentIndex << bitShifting) + 1) >= elements.size()) {
                    break;
                }

                final int currentMinChildIndex = (currentIndex << bitShifting) + minChildIndex;
                numOfComparison++;
                if (elements.get(currentMinChildIndex).getKey() < elements.get(currentIndex).getKey()) {
                    // swap current element with child element
                    needHeapify = true;
                    final Element temp = elements.get(currentIndex);
                    elements.set(currentIndex, elements.get(currentMinChildIndex));
                    elements.set(currentMinChildIndex, temp);
                }
                currentIndex = currentMinChildIndex;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        final int commandLineArgLength = args.length;
        if (commandLineArgLength > 1) {
            System.out.println("Too many command line argument input, allow only one");
            return;
        } else if (commandLineArgLength == 1) {
            branchingFactor = Integer.valueOf(args[0]);
            if (!isPowerOfTwo(branchingFactor)) {
                System.out.println("The branching factor is not a power of 2");
                return;
            }
        }
        bitShifting = getNumberOfBitShifting(branchingFactor);

        final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String[] currentLine = line.split(" ");
                final int currentLineArgs = currentLine.length;
                if (currentLineArgs == 2) {
                    final int key = Integer.valueOf(currentLine[0]);
                    final int value = Integer.valueOf(currentLine[1]);
                    insertValue(key, value);
                } else if (currentLineArgs == 1) {
                    final Element minElement = removeMin();
                    System.out.println(minElement.getKey() + " " + minElement.getValue());
                }
            }

            System.out.println("key comparisons: " + numOfComparison);
        } catch (final IOException ioe) {
            System.out.println("Something went wrong when trying to read input file");
        }
    }
}
