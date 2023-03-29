package java8.completable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    static List<Object> objects;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        System.out.println("(sync) Message before sayHelloSynchronous");
//        try {
//            sayHelloSynchronous(3);
//        } catch (InterruptedException exception) {
//            exception.printStackTrace();
//        }
//        System.out.println("(sync) Message after sayHelloSynchronous");

//        try {
//            auxAsync();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        objects = new ArrayList<>();
        System.out.println("Before sending users request");
        CompletableFuture<Void> cfString = CompletableFuture.runAsync(() -> {
            System.out.println("Sent request for users");
//            try {
//                Thread.sleep(5 * 1000); // wait for request
            for (int i =0 ; i < 10 * 1000 * 1000; i++) {
                objects.add(new Object());
            }
            System.out.println("Users received");
//            } catch (InterruptedException exception) {
//                exception.printStackTrace();
//            }
        });
        System.out.println("After sending users request");

        try {
                Thread.sleep(100); // wait for request
            System.out.println("No. of objects = " + objects.size());
            for (int i = 0 ; i < objects.size(); i++) {
//                System.out.println("Object " + i + ": " + objects.get(i));
            }
            cfString.get();

        } catch (InterruptedException | ExecutionException exception) {
            exception.printStackTrace();
        }

        int idUser = 4;
        System.out.println("User data is going to be retrieved");
        CompletableFuture<Object> cfUserData = CompletableFuture.supplyAsync(() -> {
            // thread nou
            return requestUserData(idUser);
        }).thenApply(userData -> {
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(userData);
//            JsonArray user = element.getAsJsonArray();
            return element;
        });
        //
        Object userArray = cfUserData.get();
        System.out.println(userArray);

    }

    static String requestUserData(int id) {
        URL url = null;
        String userData = null;
        try {
            url = new URL("https://jsonplaceholder.typicode.com/users/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            userData = response.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return userData;
    }

    static void sayHelloSynchronous(int noDelaySeconds) throws InterruptedException {
        Thread.sleep(noDelaySeconds * 1000);
        System.out.println("Hello from sayHello");
    }

    static void auxAsync() throws ExecutionException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        System.out.println("(Async) Message before sayHelloAsynchronous");
        LaterCf laterCf = new LaterCf(completableFuture, 5);
        laterCf.start();
        System.out.println("(Async) Message after LaterCf instance");
        try {
            String response = completableFuture.get();
            System.out.println("Response = " + response);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
