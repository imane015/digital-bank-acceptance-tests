package co.wedevx.digitalbank.automation.ui.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.text.SimpleDateFormat;
import java.util.*;

public class MockData {

    private FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"), new RandomService());

    public Map<String, String> generateRandomUser() {
        Faker faker = new Faker();

        String email = faker.internet().emailAddress();

        String title = faker.name().title();

        String firstName = faker.name().firstName();

        String lastName = faker.name().lastName();

        String gender = faker.options().option("M", "F");

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date d = faker.date().birthday(18, 99);

        String dob = dateFormat.format(d);

        String ssn = faker.idNumber().ssnValid();

        String password = faker.internet().password();

        String address = faker.address().streetAddress();

        String locality = faker.address().city();

        String region = faker.address().stateAbbr();

        String postalCode = faker.address().zipCode();

        String country = faker.address().countryCode();

        String phone = faker.phoneNumber().cellPhone();

        Map<String, String> user = new HashMap<>();
        user.put("title", title);
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("gender", gender);
        user.put("dob", dob);
        user.put("ssn", ssn);
        user.put("email", email);
        user.put("password", password);
        user.put("confirmPassword", password);
        user.put("address", address);
        user.put("locality", locality);
        user.put("region", region);
        user.put("postalCode", postalCode);
        user.put("country", country);
        user.put("homePhone",phone);

        return user;
    }
}


