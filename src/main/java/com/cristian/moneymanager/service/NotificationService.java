package com.cristian.moneymanager.service;

import com.cristian.moneymanager.entity.ProfileEntity;
import com.cristian.moneymanager.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final ExpenseService expenseService;
    private final ProfileRepository profileRepository;
    private final EmailService emailService;

    @Value("${money.manager.frontendUrl}")
    private String frontendUrl;

    @Scheduled(cron = "0 * * * * *", zone = "Europe/Madrid") // Send every minute to test
    //@Scheduled(cron = "0 0 9 * * *", zone = "Europe/Madrid")
    public void sendDailyIncomeExpenseReminder() {
        log.info("Job started: sendDailyIncomeExpenseReminder()");
        List<ProfileEntity> profiles = profileRepository.findAll();
        for (ProfileEntity profile : profiles) {
            String body = "Hi " + profile.getFullName() + ",<br><br>"
                    + "This is a friendly reminder to add your incomes and expenses for today in Money Manager.<br><br>"
                    + "<a href=" + frontendUrl + " style='display:inline-block;padding:10px 20px;background-color:#4CAF50;color:#fff:text-decoration:none;border-radius:5px;font-weight:bold'>Go to Money Manager<a/>"
                    + "<br><br>Best regards, <br>Money Manager Team";

            emailService.sendEmail(profile.getEmail(), "Daily remainder: Add your income and expenses", body);
        }
    }
}
