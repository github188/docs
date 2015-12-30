
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("10.15.44.135", 2343);
            String file = "d:\\test10000_req(1).dat";
//                        String file="d:\\test10000_req.dat";
            //向本机的4700端口发出客户请求
            // BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
            BufferedReader sin = new BufferedReader(new FileReader(file));
            //由系统标准输入设备构造BufferedReader对象
            PrintWriter os = new PrintWriter(socket.getOutputStream());
            //由Socket对象得到输出流，并构造PrintWriter对象
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //由Socket对象得到输入流，并构造相应的BufferedReader对象
            String readline;
            readline = sin.readLine(); //从系统标准输入读入一字符串
            int index = 0;
            while (readline != null && !readline.equals("bye")) {
                ++index;
                System.err.println("index=" +index);
                //若从标准输入读入的字符串为 "bye"则停止循环
                os.println(readline);
                //将从系统标准输入读入的字符串输出到Server
                os.flush();
                //刷新输出流，使Server马上收到该字符串
//                System.out.println("Client:" + readline);
                //在系统标准输出上打印读入的字符串
                                System.out.println("Server:" + is.readLine());
                //从Server读入一字符串，并打印到标准输出上
                readline = sin.readLine(); //从系统标准输入读入一字符串
            } //继续循环

            os.close(); //关闭Socket输出流
            is.close(); //关闭Socket输入流
            sin.close();
            socket.close(); //关闭Socket
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
