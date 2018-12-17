package bin;

import bin.Board.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Random;

public class Server {

    private int PORT;

    private static HashSet<String> names = new HashSet<String>();

    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();


    public Server(int players, int size, String rules, int PORT) throws IOException {

        this.PORT = PORT;


        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept(), players, size, rules).start();
            }
        } finally {
            listener.close();
        }
    }


    private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        private int players;
        private int size;
        public int color;
        static int counter = 0;
        static int[] p;
        private String rules;
        private Board board;


        public Handler(Socket socket, int players, int size, String rules) {
            this.socket = socket;
            this.players = players;
            this.size = size;
            this.rules = rules;
        }

        public void run() {
            try {

                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                out.println("SIZE " + size);
                out.println("FIRST " + setCurP(players));
                out.println("PLAYERS " + players);
                out.println("COLOR " + nextPl());
                try {
                    sleep(200);
                } catch (Exception e) {
                }
                out.println("RULES " + rules);
                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (names) {
                        if (!names.contains(name)) {
                            names.add(name);
                            break;
                        }
                    }

                }

                board = Trylma.board;

                writers.add(out);

                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
                    for (PrintWriter writer : writers) {
                        writer.println("MESSAGE " + name + ": " + input);
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                // This client is going down!  Remove its name and its print
                // writer from the sets, and close its socket.
                if (name != null) {
                    names.remove(name);
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }

        private static int setCurP(int players) {
            p = new int[6];

            for (int i = 0; i < 6; i++) {
                p[i] = -1;
            }
            if (players == 2) {
                p[0] = 0;
                p[3] = 3;
            }
            if (players == 3) {
                p[0] = 0;
                p[2] = 2;
                p[4] = 4;
            }
            if (players == 4) {
                p[0] = 0;
                p[1] = 1;
                p[3] = 3;
                p[4] = 4;
            }
            if (players == 6) {
                for (int i = 0; i < 6; i++) {
                    p[i] = i;
                }
            }

            Random rand = new Random();
            int a = rand.nextInt(6);
            while (p[a] == -1) {
                a = rand.nextInt(6);
            }

            return a;
        }

        private int nextPl(){
            int n;
            for (int i = counter; i < 6; i++) {
                if (p[i] != -1) {
                    n = i;
                    counter++;
                    return n;
                }
            }
            return 0;
        }

    }

}
