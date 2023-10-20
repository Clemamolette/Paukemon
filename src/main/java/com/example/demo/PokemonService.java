package com.example.demo;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

    private String apiURL = "https://api.pokemontcg.io/v2/cards?select=id,name,hp,types,nationalPokedexNumbers,images";
    private String apiKEY = "e030d28b-a210-4552-bec5-3b63be6b970b";

    public JsonNode getCards() {
        HttpResponse<JsonNode> jsonResponse;
        {
            try {
                jsonResponse = Unirest.get(apiURL)
                        .header("X-Api-Key", apiKEY)
                        .asJson();
            } catch (UnirestException e) {
                throw new RuntimeException(e);
            }
            return jsonResponse.getBody();
        }
    }
}
