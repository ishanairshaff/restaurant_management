package com.ruh.restaurant.restaurant;

import tools.jackson.databind.ObjectMapper;
import com.ruh.restaurant.restaurant.dto.RestaurantDTO;
import com.ruh.restaurant.restaurant.model.Restaurant;
import com.ruh.restaurant.restaurant.service.RestaurantService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for JSON serialization/deserialization of RestaurantDTO.
 * These tests run without a Spring context or database, exercising
 * Jackson's ObjectMapper directly.
 */
class RestaurantDtoSerializationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void dto_serializes_to_json_with_expected_keys() throws Exception {
        RestaurantDTO dto = new RestaurantDTO(1, "The Grill", "Downtown", 4.5, "BBQ");

        String json = objectMapper.writeValueAsString(dto);

        assertThat(json).contains("\"id\":1");
        assertThat(json).contains("\"name\":\"The Grill\"");
        assertThat(json).contains("\"location\":\"Downtown\"");
        assertThat(json).contains("\"rate\":4.5");
        assertThat(json).contains("\"cuisine\":\"BBQ\"");
    }

    @Test
    void dto_deserializes_from_json() throws Exception {
        String json = "{\"id\":2,\"name\":\"Pasta Palace\",\"location\":\"Uptown\","
                + "\"rate\":3.8,\"cuisine\":\"Italian\"}";

        RestaurantDTO dto = objectMapper.readValue(json, RestaurantDTO.class);

        assertThat(dto.getId()).isEqualTo(2);
        assertThat(dto.getName()).isEqualTo("Pasta Palace");
        assertThat(dto.getLocation()).isEqualTo("Uptown");
        assertThat(dto.getRate()).isEqualTo(3.8);
        assertThat(dto.getCuisine()).isEqualTo("Italian");
    }

    @Test
    void dto_ignores_unknown_json_fields() throws Exception {
        String json = "{\"id\":3,\"name\":\"Sushi Bar\",\"location\":\"Midtown\","
                + "\"rate\":4.9,\"cuisine\":\"Japanese\",\"unknown_field\":\"ignored\"}";

        RestaurantDTO dto = objectMapper.readValue(json, RestaurantDTO.class);

        assertThat(dto.getName()).isEqualTo("Sushi Bar");
    }

    @Test
    void dto_excludes_null_fields_from_json() throws Exception {
        RestaurantDTO dto = new RestaurantDTO(null, "Taco Town", null, null, "Mexican");

        String json = objectMapper.writeValueAsString(dto);

        assertThat(json).doesNotContain("\"id\"");
        assertThat(json).doesNotContain("\"location\"");
        assertThat(json).doesNotContain("\"rate\"");
        assertThat(json).contains("\"name\":\"Taco Town\"");
        assertThat(json).contains("\"cuisine\":\"Mexican\"");
    }

    @Test
    void service_toDTO_maps_all_fields() {
        Restaurant entity = new Restaurant(5, "Burger Barn", "Westside", 4.2, "American");
        RestaurantService service = new RestaurantService();

        RestaurantDTO dto = service.toDTO(entity);

        assertThat(dto.getId()).isEqualTo(5);
        assertThat(dto.getName()).isEqualTo("Burger Barn");
        assertThat(dto.getLocation()).isEqualTo("Westside");
        assertThat(dto.getRate()).isEqualTo(4.2);
        assertThat(dto.getCuisine()).isEqualTo("American");
    }

    @Test
    void service_fromDTO_maps_all_fields() {
        RestaurantDTO dto = new RestaurantDTO(7, "Noodle House", "Eastside", 3.5, "Chinese");
        RestaurantService service = new RestaurantService();

        Restaurant entity = service.fromDTO(dto);

        assertThat(entity.getId()).isEqualTo(7);
        assertThat(entity.getName()).isEqualTo("Noodle House");
        assertThat(entity.getLocation()).isEqualTo("Eastside");
        assertThat(entity.getRate()).isEqualTo(3.5);
        assertThat(entity.getCuisine()).isEqualTo("Chinese");
    }

    @Test
    void service_toDTO_returns_null_for_null_input() {
        RestaurantService service = new RestaurantService();
        assertThat(service.toDTO(null)).isNull();
    }

    @Test
    void service_fromDTO_returns_null_for_null_input() {
        RestaurantService service = new RestaurantService();
        assertThat(service.fromDTO(null)).isNull();
    }

    @Test
    void dto_list_roundtrip() throws Exception {
        List<RestaurantDTO> list = List.of(
                new RestaurantDTO(1, "A", "loc1", 4.0, "Cuisine1"),
                new RestaurantDTO(2, "B", "loc2", 3.0, "Cuisine2")
        );

        String json = objectMapper.writeValueAsString(list);
        List<?> parsed = objectMapper.readValue(json, List.class);

        assertThat(parsed).hasSize(2);
    }
}
