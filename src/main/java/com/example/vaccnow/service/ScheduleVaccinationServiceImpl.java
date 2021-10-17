package com.example.vaccnow.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import com.example.vaccnow.entity.ScheduleVaccination;
import com.example.vaccnow.repository.ScheduleVaccinationRepository;
import com.example.vaccnow.util.EmailService;
import com.example.vaccnow.util.PaymentMethodEnum;
import com.example.vaccnow.util.VaccNowUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ScheduleVaccinationServiceImpl
        extends BaseServiceImpl<ScheduleVaccination, Integer, ScheduleVaccinationRepository>
        implements ScheduleVaccinationService {

    private final EmailService emailService;
    private @Value("${mail.from:error No default mail registered}") String mailSender;

    public ScheduleVaccinationServiceImpl(ScheduleVaccinationRepository repo, EmailService emailService) {
        super(repo);
        this.emailService = emailService;
    }

    @Override
    @SneakyThrows
    public ScheduleVaccination scheduleVaccConfirmationByPaymentEmail(PaymentMethodEnum payment, String email) {

        Date vacTime = null;
        Optional<Date> data = findAll().stream().filter(f -> f.getVacTime() != null)
                .map(ScheduleVaccination::getVacTime).max(Date::compareTo);
        if (data.isPresent()) {
            vacTime = VaccNowUtils.addMinuteToDate(data.get(), 15);
        } else
            vacTime = new Date();

        Optional<ScheduleVaccination> scheduleVaccination = repo.findOne((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = List.<Predicate>of(criteriaBuilder.equal(root.get("email"), email),
                    criteriaBuilder.equal(root.get("paymentMethod"), payment));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });

        log.info(" Schedule Vaccination .... ::({})", scheduleVaccination);
        ScheduleVaccination en = null;
        if (scheduleVaccination.isEmpty()) {
            en = ScheduleVaccination.builder().paymentMethod(payment).email(email)
                    .vacDesc("A scheduled vaccination process Done ").vacTime(vacTime).build();
            en = create(en);
        } else {
            en = scheduleVaccination.get();
        }
        en.setVacTime(vacTime);
        en.setRequest(VaccNowUtils.getRandomScheduledVaccinationRequest());
        sendMail(en);
        log.info("Vaccination request scheduled ::({})", en);
        return en;
    }

    /**
     * Must config Mail and un comment line(79 , 80)
     * 
     * @param en
     */
    // apply mail Config. to send Confirmation
    @SneakyThrows
    public void sendMail(ScheduleVaccination en) {

        new Thread(() -> {
            log.info("Send Mail To ({})  and His Request Id ({})", en.getEmail(), en.getRequest());
            log.info("Vaccination request scheduled Confirmation Mail SENT");

            // Email Sender
            if (!(/* "default@domain.com".equals(mailSender) || */
            "error No default mail registered".equals(mailSender))) {
                emailService.sendMessage(en.getEmail(), mailSender, "Vaccination Schedule",
                        String.format("Hello !, %n Your Vaccination Sheduled Date on %tc", en.getVacTime()));
            }

            en.setConfirmed(true);
            update(en);
        }, "Thread-Mail-Sending").start();

    }

    @Override
    public List<ScheduleVaccination> getConfirmedVaccinations(Date from, Date to) {

        List<ScheduleVaccination> scVacc = repo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = List.<Predicate>of(criteriaBuilder.between(root.get("vacTime"), from, to),
                    criteriaBuilder.equal(root.get("confirmed"), Boolean.TRUE));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });

        log.info("Load Scheduled Vaccination from ({}) to ({}) :: ({})", from, to, scVacc);
        return scVacc;
    }

    @Override
    public List<ScheduleVaccination> getAppliedVaccinationByBranchId(Integer branchId) {

        List<ScheduleVaccination> scVacc = repo.findAll(
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("branch").get("id"), branchId));
        log.info("Load Scheduled Vaccination Applied By Branch id: ({}) ::>> ({}) ", branchId, scVacc);
        return scVacc;

    }

}
