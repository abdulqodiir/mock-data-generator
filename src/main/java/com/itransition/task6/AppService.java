package com.itransition.task6;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Abdulqodir Ganiev 6/9/2022 3:15 PM
 */

@Service
public class AppService {

    public List<User> generateUser(String country, String error, String seed) {

        Random random = new Random(Integer.parseInt(seed));

        List<User> users = new ArrayList<>();

        Faker faker = new Faker(new Locale(country), random);
        for (int i = 1; i <= 20; i++) {
            User user = new User(
                    faker.number().numberBetween(100000, 999999),
                    faker.name().fullName(),
                    generateFullAddress(country, faker),
                    generatePhoneNumber(country, faker),
                    i
            );

            users.add(user);
        }

        return users;
    }

    private String generateFullAddress(String country, Faker faker) {
        return country.equals("ru") ? faker.address().fullAddress().replaceFirst("###### ", "") : faker.address().fullAddress();
    }

    private String generatePhoneNumber(String country, Faker faker) {
        return country.equals("ru") ? faker.phoneNumber().phoneNumber() : faker.phoneNumber().cellPhone();
    }

    public List<User> generateNext10Data(DataRequest request) {
        int seed = Integer.parseInt(request.getSeed());
        String locale = request.getCountry();
        int dataLength = Integer.parseInt(request.getDataLength());
        double error = Double.parseDouble(request.getError());

        Random random = new Random(seed);

        List<User> users = new ArrayList<>();

        System.out.println(dataLength);
        Faker faker = new Faker(new Locale(locale), random);
        for (int i = 1; i < dataLength + 10; i++) {
            if (i >= dataLength) {
                User user = new User(
                        faker.number().numberBetween(100000, 999999),
                        faker.name().fullName(),
                        generateFullAddress(locale, faker),
                        generatePhoneNumber(locale, faker),
                        i
                );
                users.add(user);
            } else
                faker.name().fullName();

        }

        return users;
    }
}
