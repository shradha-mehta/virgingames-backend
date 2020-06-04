package com.virgingames.virgingamestest;

import com.virgingames.steps.GameSteps;
import com.virgingames.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;


@RunWith(SerenityRunner.class)
public class VirginGamesTest extends TestBase {

    static String defaultGameFrequency;
    static String timeStamp;
    static String startTime;

    @Steps
    GameSteps gameSteps;

    @Title("This test will get and verify that all Games has defaultgamefrequency = 300000")
    @Test
    public void test001() {

        ValidatableResponse response = gameSteps.gettingAllGamesInfo().statusCode(200);
        defaultGameFrequency = response.extract().path("bingoLobbyInfoResource.streams.findAll{it.defaultGameFrequency='300000'}.defaultGameFrequency").toString();
        Assert.assertTrue(defaultGameFrequency.contains("300000"));
        System.out.println("The Default Game Frequency  values in the list are : " + defaultGameFrequency);

    }

    @Title("This test will verify that the startTime for all Games is always future timestamp i.e. startTimes is greater than timeStamp ")
    @Test
    public void test002() {
        ValidatableResponse response = gameSteps.gettingAllGamesInfo().statusCode(200);
        timeStamp = response.extract().response().body().path("timestamp").toString();
        startTime = response.extract().response().body().path("bingoLobbyInfoResource.streams.startTime").toString();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Timestamp is : " + timeStamp);
        System.out.println("The startTime is : " + startTime);
        System.out.println("------------------End of Test---------------------------");
        assertThat(startTime, greaterThan(timeStamp));
    }

}
