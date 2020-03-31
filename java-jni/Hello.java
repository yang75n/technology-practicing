public class Hello{

static {
    System.loadLibrary("Hello");
}


public native int  say(int i);


public static void main(String args[]){
	System.out.println("Hello World!");
	int i = new Hello().say(123);
	System.out.println("i="+ i);
}

}
