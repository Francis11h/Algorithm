import java.util.Scanner;

class heap1 {
    // Key/value pair, used in the heap and its interface.
    static class Pair {
        public int key;
        public int val;

        public Pair( int k, int v ) {
            key = k;
            val = v;
        }
    }

    // Actual representation of the heap
    static class MinHeap {
        // Counter of comparison operations, for comparing performance.
        private long comparisons = 0;

        // Power of 2 used as the branchign factor
        private int p;

        // Representation for the heap.
        Pair[] tree;

        // Number of elements in the heap.
        int n;

        // Capacity of the heap.
        int cap;

        // Function to compare keys, so we can also count key comparisons.
        private boolean keyLess( Pair a, Pair b ) {
            comparisons += 1;
            return a.key < b.key;
        }

        public MinHeap( int p ) {
            this.p = p;
            cap = 5;
            n = 0;
            tree = new Pair [ cap ];
        }

        Pair removeMin() {
            // Remove the minimum value and replace it with the last one.
            Pair v = tree[ 0 ];
            tree[ 0 ] = tree[ n - 1 ];
            n -= 1;

            // We need the branching factor below.
            int branch = 1 << p;

            // Push this value down until it satisfies the ordering constraint.
            int idx = 0;
            int child = ( idx << p ) + 1;
            while ( child < n ) {
                // Find index of smallest child.
                int m = child;
                int end = child + branch;
                if ( end > n )
                    end = n;
                for ( int i = child + 1; i < end; i++ )
                    if ( keyLess( tree[ i ], tree[ m ] ) )
                        m = i;

                // Not happy about this early return.  Would be nice to ahve it in the condition
                // on the loop.  Return early if we hit a point where we don't have to swap.
                if ( ! keyLess( tree[ m ], tree[ idx ] ) )
                    return v;

                // Swap the current vlaue with its smallest child
                Pair temp = tree[ idx ];
                tree[ idx ] = tree[ m ];
                tree[ m ] = temp;

                // Follow the value down into the tree.
                idx = m;
                child = ( idx << p ) + 1;
            }

            return v;
        }

        void insertValue( Pair v ) {
            if ( n >= cap ) {
                // Enlarge the heap array and copy everything over.
                cap *= 2;
                Pair[] t2 = new Pair [ cap ];
                for ( int i = 0; i < n; i++ )
                    t2[ i ] = tree[ i ];
                tree = t2;
            }

            // Put the new value at the end of the heap.
            int idx = n;
            tree[ n++ ] = v;

            // Move it up in the heap until it's as large as its parent.
            int par = ( idx - 1 ) >> p;
            while ( par >= 0 && keyLess( tree[ idx ], tree[ par ] ) ) {
                // Swap this value with its parent.
                Pair temp = tree[ par ];
                tree[ par ] = tree[ idx ];
                tree[ idx ] = temp;

                idx = par;
                par = ( idx - 1 ) >> p;
            }
        }

        /** Return the number of comparisons performed. */
        long ccount() {
            return comparisons;
        }
    }

    private static void usage() {
        System.err.println( "usage: heap [branching-factor (power of two)]\n" );
        System.exit( 1 );
    }

    public static void main( String[] args ) {
        // Power of two that this branching factor is.
        int p = 1;

        // See if we got a branching factor on the command line.
        if ( args.length > 2 )
            usage();
        else if ( args.length == 1 ) {
            // Make sure we got a number and it's a sufficiently large power of two.
            int branch = Integer.valueOf( args[ 0 ] );
            if ( branch <= 1 || ( branch & ( branch - 1 ) ) != 0 )
                usage();

            // Figure out the power of two that this branching factor is.
            p = 0;
            while ( ( 1 << p ) < branch )
                p++;
        }

        MinHeap H = new MinHeap( p );

        // Process each of the inputs.
        Scanner input = new Scanner( System.in );
        while ( input.hasNextInt() ) {
            int v = input.nextInt();
            if ( v >= 0 ) {
                int x = input.nextInt();
                H.insertValue( new Pair( v, x ) );
            } else {
                Pair m = H.removeMin();
                System.out.printf( "%d %d\n", m.key, m.val );
            }
        }

        // Report number of comparisons
        System.out.printf( "key comparisons: %d\n", H.ccount() );
    }
}