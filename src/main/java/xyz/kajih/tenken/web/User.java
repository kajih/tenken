package xyz.kajih.tenken.web;

import net.datafaker.Faker;

public record User(long id, String name, String lastName, int age) {

    private static final Faker faker = new Faker();

    public static User randomUser() {
        return new User(
                faker.number().randomNumber(),               // random long id
                faker.name().firstName(),                    // random first name
                faker.name().lastName(),                     // random surname
                faker.number().numberBetween(18, 99)         // random age between 18 and 99
        );
    }
}
