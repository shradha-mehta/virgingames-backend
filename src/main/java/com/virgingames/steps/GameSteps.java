package com.virgingames.steps;

import com.virgingames.constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import static io.restassured.RestAssured.defaultParser;



public class GameSteps {

// below method is reusable steps to get all games information from end point

    @Step("Getting  all  games information ")

    public ValidatableResponse gettingAllGamesInfo() {

        return SerenityRest.rest().given()
                .when()
                .log()
                .all()
                .get(EndPoints.GET_BINGO_LOBBY_FEED)
                .then().log().ifValidationFails()
                .parser("text/plain;charset=UTF-8", defaultParser.JSON);

    }

}
