package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;

public class App {
    public static void main(String[] args) throws Exception {
        
        JSONObject result = new JSONObject();
        JSONObject position = new JSONObject();

        try {
            String boardApi = System.getenv("BOARD_API");
            String commandsApi = System.getenv("COMMANDS_API");
            
            if (boardApi == null || commandsApi == null) {
                System.err.println("Error: Environment variables BOARD_API and COMMANDS_API must be set.");
                return;
            }
            //get data from URL
            String stringboard = fetchData(boardApi);
            String stringcommands = fetchData(commandsApi);
            // convert string data to json
            JSONObject boardJson = new JSONObject(stringboard);
            JSONObject commandsJson = new JSONObject(stringcommands);

            // create variables
            List<obstacle> obstaclesList = new ArrayList<>();
            String sentence;
            String[] words;

            int width = boardJson.getInt("width");
            int height = boardJson.getInt("height");
            JSONArray obstacles = boardJson.getJSONArray("obstacles");
            JSONArray commands = commandsJson.getJSONArray("commands");

            // adding elements to the obstacles list
            for (int i = 0; i < obstacles.length(); i++) {
                JSONObject obstacle = obstacles.getJSONObject(i);
                int x = obstacle.getInt("x");
                int y = obstacle.getInt("y");
                obstaclesList.add(new obstacle(x, y));
            }    


            // create the board and the knight objects
            board b = new board(width, height, obstaclesList);
            knight k = new knight();

            // for each command in the commands array
            for(int i=0; i<commands.length(); i++){
                // get the command number i
                sentence = (String) commands.get(i);
                // split the command into words
                words = sentence.trim().split("[ ,]+");

                // the START command must be the first one and should appear only once.
                if(((i == 0)&&(!words[0].equals("START"))) || ((i != 0)&&(words[0].equals("START")))){
                    result.put("status", "GENERIC_ERROR");
                    System.out.println(result.toString(4));
                    return;
                    
                }else{
                    switch (words[0]) {
                        case "START":
                            // if the start position out of the board or it is an obstacle.
                            if((Integer.parseInt(words[1]) > b.width) || (Integer.parseInt(words[2]) > b.height) || (b.isObstacle(Integer.parseInt(words[1]), Integer.parseInt(words[2])))){
                                result.put("status", "INVALID_START_POSITION");
                                System.out.println(result.toString(4));
                                return;
                            }
                            k.start(Integer.parseInt(words[1]), Integer.parseInt(words[2]), words[3]);
                            break;

                        case "ROTATE":
                            k.rotate(words[1]);
                            break;

                        case "MOVE":
                            for(int j=1; j<=Integer.parseInt(words[1]); j++){
                                k.move();
                                // if the knight will be out of the board
                                if((k.x > b.width)||(k.y > b.height)){
                                    result.put("status", "OUT_OF_THE_BOARD");
                                    System.out.println(result.toString(4));
                                    return;
                                }else{
                                    // if the new position is an obstacle should make a step back (reverse function) 
                                    if(b.isObstacle(k.x, k.y)){
                                        k.reverse();
                                        break;
                                    }
                                }
                            }
                            break;
                    
                        default:
                            result.put("status", "GENERIC_ERROR");
                            System.out.println(result.toString(4));
                            return;
                    }
                }
            }
            position.put("x", k.x);
            position.put("y", k.y);
            position.put("direction", k.dir);

            result.put("position", position);
            result.put("status", "SUCCESS");

            System.out.println(result.toString(4));

        } catch (Exception e) {
            result.put("status", "GENERIC_ERROR");
            System.out.println(result.toString(4));
        }
    }

    // create http request to get data from URL 
    private static String fetchData(String URL) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
