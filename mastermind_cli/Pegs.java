import java.util.Iterator;

/**
 * Author Tom Karlsson
 * Created 2021-1015
 * 
 * The Peg class creates a bag of Pegs. 
 * There are 6 pegs of each color: Red, Yellow, Green, Blue, Brown, Purple.
 * There is a total of 6 * 6 = 36 pegs
 * 
 * When the bag is created they are shuffled and 4 of them are added to an iterable stack.
 *
 * 
 */

 public class Pegs implements Iterable<String>{
    private static final int PEGSNR = 36;
    private static String[] pegs;
    private CircularList pegBag;


    private static class CircularList implements Iterable<String>{
        private Node head;
        private int size;

        private static class Node{
            public String color;
            public Node next;
            public Node prev;
        }

        public CircularList(){
            this.head = null;
            this.size = 0;
        }

        public void add(String color){
            Node tmp = new Node();
            tmp.color = color;
            if (this.size > 0){
                tmp.next = this.head;
                tmp.prev = this.head.prev;
                this.head.prev.next = tmp;
                this.head.prev = tmp;
            }
            else{
                tmp.next = tmp;
                tmp.prev = tmp;
            }
            this.head = tmp;
            this.size++;
        }
        public String removeAt(int index){
            if(this.size < 1){
                throw new IndexOutOfBoundsException("Can't remove, pegbag is empty");
            }
            Node tmp = this.head;
            //System.out.println("Size: " +this.size);
            //System.out.println(index + tmp.color);
            while(index-- > 0){
                tmp = tmp.next;
            }
            
            tmp.prev.next = tmp.next;
            tmp.next.prev = tmp.prev;

            if(this.head.equals(tmp)){
                this.head = tmp.next;
            }

            tmp.next = null;
            tmp.prev = null;

            this.size--;
            return tmp.color;
        }

        public Iterator<String> iterator(){
            return new CustomIterator(this.head, this.size);
        }
        public class CustomIterator implements Iterator<String>{
            private Node x;
            private int size;
            public CustomIterator(Node head, int size){
                this.x = head;
                this.size = size;
            }
            public boolean hasNext(){
                return (size > 0);
            }
            public String next(){
                String color = x.color;
                x = x.next;
                size--;
                return color;
            }

        }

    }

    public Pegs(){
        pegs = new String[PEGSNR];
        int a = PEGSNR;
        pegBag = new CircularList();

        while(a>0){
            pegBag.add("red");
            pegBag.add("yellow");
            pegBag.add("green");
            pegBag.add("blue");
            pegBag.add("brown");
            pegBag.add("purple");
            a -= 6;
        }
        for(int i = 0; i < PEGSNR; i++){
            pegs[i] = pegBag.removeAt(getIndex(PEGSNR-i));
        }
    }
    public void printBag(){
        for(String color : this.pegBag){
            System.out.println(color);
        }
    }
    public void printArray(){
        for (int i = 0; i < PEGSNR; i++){
            System.out.println(pegs[i]);
        }
    }
    public Iterator<String> iterator(){
        return new PopList();
    }
    public class PopList implements Iterator<String>{
        private int i = 0;
        private String[] colors;
        public PopList(){
            colors = pegs;
        }
        public boolean hasNext(){
            return (i < PEGSNR);
        }
        public String next(){
            return(this.colors[i++]);
        }
    }
    public String getPeg(int index){
        return pegs[index];
    }
    public int getIndex(int mod){
        return (int) (System.nanoTime() & 0x7FFFFFFF) % mod;
    }

    public static void main(String[] args){
        
        // TEST UNIT - CHECKING RANDOMNESS

        int[] red = new int[36];
        int[] yellow = new int[36];
        int[] green = new int[36];
        int[] blue = new int[36];
        int[] brown = new int[36];
        int[] purple = new int[36];

        int testruns = 1000;

        while(testruns > 0){
            Pegs peg = new Pegs();
            int i = 0;
            for(String color : peg){

                switch(color.toLowerCase()){
                    case "red":
                        red[i++]++;
                        break;
                    case "yellow":
                        yellow[i++]++;
                        break;
                    case "green":
                        green[i++]++;
                        break;
                    case "blue":
                        blue[i++]++;
                        break;
                    case "brown":
                        brown[i++]++;
                        break;
                    case "purple":
                        purple[i++]++;
                        break;
                }
            }
            testruns--;

        }
        for(int i = 0; i < 36; i++){
            System.out.println(i + ". Red: " + red[i]/10 + "%, Yellow: " + yellow[i]/10 + "%, Green: " + green[i]/10 +"%, Blue: " + blue[i]/10 + "%, Brown: " + brown[i]/10 + "%, Purple: " + purple[i]/10 + "%");
        }

    }

 }