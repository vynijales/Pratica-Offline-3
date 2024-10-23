package test;

import services.Message.Message;
import services.Message.Request;
import services.Message.Response;

public class MessageTest {
    public static void main(String[] args) {
        // String input =
        // "aaaaabbbbbbbbbccccccccccccdddddddddddddeeeeeeeeeeeeeeeefffffffffffffffffffffffffffffffffffffffffffff";
        String input = "ffffffffffffffffffffffffffffffffffffffeeeefffffffeeeeeeeeeeeedddddddddddddccccccccccccbbbbbbbbbaaaaa";

        System.out.println("Mensagem base:" + input);

        Message request = new Request(input);

        System.out.println("Mensagem codificada: " + request);

        Message response = new Response(request.tree, request);

        System.out.println("Mensagem decodificada: " + response);

    }

}
