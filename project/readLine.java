BufferedReader
1. 它有一个很特别的方法：readLine()，使用起来特别方便，每次读回来的都是一行，省了很多手动拼接buffer的琐碎
2. 它比较高效，相对于一个字符/字节地读取、转换、返回来说，它有一个缓冲区，读满缓冲区才返回；
	一般情况下，都建议使用它们把其它Reader/InputStream包起来，使得读取数据更高效。
3. 对于文件来说，经常遇到一行一行的，特别相符情景





Socket server = serverSocket.accept();

InputStreamReader in = new InputStreamReader(server.getInputStream());
BufferedReader input = new BufferedReader(in);

OutputStream out= server.getOutputStream();
PrintWriter output = new PrintWriter(out, true);
server.close();




误以为readLine()是读取到没有数据时就返回null(因为其它read方法当读到没有数据时返回-1)，
而实际上readLine()是一个阻塞函数，当没有数据读取时，就一直会阻塞在那，而不是返回null



List<String> message = new ArrayList<>();
String line;
int i = 0;

try {
    while (i <= 5) {
        line = input.readLine();
        i++;
        System.out.println(line);
        message.add(line);
    }
} catch (IOException e) {
    e.printStackTrace();
}



