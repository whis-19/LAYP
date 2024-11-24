package Alpaca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class AlpacaApiTest {

    private static final String BASE_URL = "https://paper-api.alpaca.markets/v2";
    private static final String API_KEY = "PKATIZ45TAFKQAPJ27AJ";
    private static final String SECRET_KEY = "EVQDyQ707ANf3ZQySyl2BMWUic3V5rhypfOI7cit";

    private HttpRequest.Builder getAuthenticatedRequestBuilder() {
        return HttpRequest.newBuilder()
            .header("APCA-API-KEY-ID", API_KEY)
            .header("APCA-API-SECRET-KEY", SECRET_KEY)
            .header("Accept", "application/json");
    }

    @Test
    @Order(1)
    @DisplayName("POST /orders - Create an order")
    public void testCreateOrder() throws Exception {
        URI uri = URI.create(BASE_URL + "/orders");
        HttpClient client = HttpClient.newHttpClient();

        String jsonPayload = """
            {
                "symbol": "AAPL",
                "qty": 10,
                "side": "buy",
                "type": "market",
                "time_in_force": "gtc"
            }
        """;

        HttpRequest request = getAuthenticatedRequestBuilder()
            .uri(uri)
            .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
            .header("Content-Type", "application/json")
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("AAPL"));
    }

    @Test
    @Order(2)
    @DisplayName("GET /orders - Get all orders")
    public void testGetAllOrders() throws Exception {
        URI uri = URI.create(BASE_URL + "/orders");
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = getAuthenticatedRequestBuilder()
            .uri(uri)
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
    }

    @Test
    @Order(3)
    @DisplayName("DELETE /orders - Delete all orders")
    public void testDeleteAllOrders() throws Exception {
        URI getOrdersUri = URI.create(BASE_URL + "/orders");
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest getOrdersRequest = getAuthenticatedRequestBuilder()
            .uri(getOrdersUri)
            .GET()
            .build();

        HttpResponse<String> getOrdersResponse = client.send(getOrdersRequest, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, getOrdersResponse.statusCode());

        String responseBody = getOrdersResponse.body();
        
        Pattern pattern = Pattern.compile("\"id\":\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(responseBody);

        while (matcher.find()) {
            String orderId = matcher.group(1);

            URI deleteOrderUri = URI.create(BASE_URL + "/orders/" + orderId);

            HttpRequest deleteOrderRequest = getAuthenticatedRequestBuilder()
                .uri(deleteOrderUri)
                .DELETE()
                .build();

            HttpResponse<String> deleteOrderResponse = client.send(deleteOrderRequest, HttpResponse.BodyHandlers.ofString());

            assertEquals(204, deleteOrderResponse.statusCode(), "Failed to delete order with ID: " + orderId);
        }
    }
}



